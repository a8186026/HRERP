package com.goldcow.emanage.stock.web;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goldcow.emanage.stock.service.IStockInfoService;
import com.goldcow.emanage.util.gen.entity.StockInfo;
import com.goldcow.emanage.util.gen.entity.SysUser;
import com.goldcow.emanage.util.gen.entity.valueObject.stock.StockBatchVO;
import com.goldcow.emanage.util.gen.entity.valueObject.stock.StockInfoVO;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.JsonUtil;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Maps;


@Controller
@RequestMapping(value = "/stock/stockInfo")
public class StockInfoController {
	
	@Autowired
	protected IStockInfoService stockInfoService;
	
	
	/**
	 * 批次停售信息管理页面
	 * 
	 * @param request HttpServletRequest
	 * @return 批次停售信息页面路径
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String view(HttpServletRequest request,Model model) {
		if (SysUtil.hasRight(request, "StockInfo", GlobalVal.MENU_FUNCTION.VIEW)) {
			model.addAttribute("method", "PUT");
			model.addAttribute("formUrl", "/stock/stockInfo");
			model.addAttribute("user",SysUtil.getLoginUser(request));
			return "batch/batchSuspension/batchSaleStopList.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}

	/**
	 * 批次效期修改管理页面
	 * 
	 * @param request HttpServletRequest
	 * @return 批次效期修改页面路径
	 */
	@RequestMapping(value = "viewBatch", method = RequestMethod.GET)
	public String viewModifyBatch(HttpServletRequest request,Model model) {
		if (SysUtil.hasRight(request, "StockInfo", GlobalVal.MENU_FUNCTION.VIEW)) {
			model.addAttribute("method", "PUT");
			model.addAttribute("formUrl", "/stock/modifyBatch");
			return "dept/modifyBatch/modifyBatchList.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}
	
	
	
	
	/**
	 * 查询库存信息
	 * 
	 * @param request HttpServletRequest
	 * @param bean 查询条件
	 * @return 操作结果
	 */
	@RequestMapping(value = "lists", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object>list(HttpServletRequest request, StockInfo bean) {
		if (SysUtil.hasRight(request, "stockInfo", GlobalVal.MENU_FUNCTION.VIEW)) {
			return stockInfoService.list(bean);
		} else {
			return Maps.newHashMap();
		}
	}
	
	/**
	 * 查询批号效期信息
	 * 
	 * @param request HttpServletRequest
	 * @param bean 查询条件
	 * @return 操作结果
	 */
	@RequestMapping(value = "listBatch", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object>listBatch(HttpServletRequest request, StockInfoVO bean) {
		if (SysUtil.hasRight(request, "stockInfo", GlobalVal.MENU_FUNCTION.VIEW)) {
			return stockInfoService.listBatch(bean);
		} else {
			return Maps.newHashMap();
		}
	}
	
	/**
	 * 查看批次效期信息页面
	 * 
	 * @param request HttpServletRequest
	 * @param id 库存批此ID
	 * @param model Model
	 * @return 查看批次效期信息页面路径
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public String viewDetail(HttpServletRequest request, @PathVariable("id") Integer id, Model model) {
		if (SysUtil.hasRight(request, "stockInfo", GlobalVal.MENU_FUNCTION.VIEW)) {
			
			StockBatchVO stockInfo = stockInfoService.getDetailById(id);
			model.addAttribute("stockInfo", stockInfo);
			model.addAttribute("method", "PUT");
			model.addAttribute("formUrl", "/stock/stockInfo/" + id);
			return "dept/modifyBatch/modifyBatchEdit.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}
	
	/**
	 * 修改批次效期信息
	 * 
	 * @param request HttpServletRequest
	 * @param id 库存批次ID
	 * @param bean 批次效期信息
	 * @return 操作结果
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Map<String, Object> update(HttpServletRequest request, @PathVariable("id") Integer id, StockInfo bean) {
		Map<String, Object> result = Maps.newHashMap();
		request.getParameterMap();
		if (SysUtil.hasRight(request, "stockInfo", GlobalVal.MENU_FUNCTION.UPDATE)) {
			// 修改批次效期信息
			stockInfoService.update(bean, request);

			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}

		return result;
	}
	
	/**
	 *  获取某库房某产品有效库存
	 * 
	 * @param request HttpServletRequest
	 * @param bean 查询条件
	 * @return 操作结果
	 */
	@RequestMapping(value = "getValidatStocks", method = RequestMethod.GET)
	@ResponseBody
	public List<StockInfo> getValidatStocks(HttpServletRequest request, StockInfo bean) {
		if (SysUtil.hasRight(request, "stockInfo", GlobalVal.MENU_FUNCTION.VIEW)) {
			return stockInfoService.getValidatStocks(bean);
		} else {
			return new ArrayList<StockInfo>();
		}
	}

	

	/**
	 * 批次停售操作
	 * 
	 * @param request HttpServletRequest
	 * @param bean 查询条件
	 * @return 操作结果
	 */

	@RequestMapping(value="saleStop",  method = RequestMethod.GET)
	@ResponseBody
	public Integer saleStop(HttpServletRequest request, Model model,@RequestParam("dataArr")String dataArr){
		try {
			dataArr=java.net.URLDecoder.decode(dataArr,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
			System.out.println(dataArr);
			List<StockInfo> list=JsonUtil.jsonArrayToList(dataArr, StockInfo.class);
			SysUser user = SysUtil.getLoginUser(request);
		if (SysUtil.hasRight(request, "/stock/stockInfo/saleStop", GlobalVal.MENU_FUNCTION.VIEW)) {
				return stockInfoService.saleStop(list,user);
		}
		return 0;
	}
	
	/**
	 * 查询所有产品信息
	 * 
	 * @param request HttpServletRequest
	 * @param model Model
	 * @param callback 回调方法
	 * @param proContent 查询参数
	 * @return 供方信息页面路径
	 */
	@RequestMapping(value = "viewProList", method = RequestMethod.GET)
	public String viewProList(HttpServletRequest request, Model model, String callback, String proContent) {
		if (SysUtil.hasRight(request, "viewProList", GlobalVal.MENU_FUNCTION.VIEW)) {
			System.out.println("查询所有产品信息");
			model.addAttribute("callback", callback);
			model.addAttribute("proContent", proContent);
			return "batch/batchSuspension/batchViewProList.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}
	

	/**
	 * 查询库存停售批次信息
	 * 
	 * @param request HttpServletRequest
	 * @param bean 查询条件
	 * @return 操作结果
	 */
	@RequestMapping(value = "listSaleStop", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object>listSaleStop(HttpServletRequest request, StockInfo bean) {
		if (SysUtil.hasRight(request, "stockInfo", GlobalVal.MENU_FUNCTION.VIEW)) {
			return stockInfoService.listSaleStop(bean);
		} else {
			return Maps.newHashMap();
		}
	}

}
