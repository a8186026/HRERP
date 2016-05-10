package com.goldcow.emanage.product.web;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.goldcow.emanage.product.service.IProInfoManageService;
import com.goldcow.emanage.product.service.IProStockPriceService;
import com.goldcow.emanage.util.gen.entity.BasDepartmentInfo;
import com.goldcow.emanage.util.gen.entity.ProInfoManage;
import com.goldcow.emanage.util.gen.entity.StockPrice;
import com.goldcow.emanage.util.gen.entity.valueObject.product.ProStockPriceVO;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
/**
 * 调价
 * 
 * @author zhanxiaotong	
 * @version v1.0
 * @since 2015-9-15
 */
@Controller
@RequestMapping(value = "/product/stockPrice")

public class ProStockPriceController {
	/** 调整产品价格 */
	@Autowired
	protected IProStockPriceService stockPriceService;
	@Autowired
	protected IProInfoManageService proInfoManageService;
	/**
	 * 分店调价管理页面
	 * 
	 * @param request HttpServletRequest
	 * @return 分店调价信息路径
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String view(HttpServletRequest request) {
		if (SysUtil.hasRight(request, "stockPrice", GlobalVal.MENU_FUNCTION.VIEW)) {
			return "product/proStockPrice/proStockPriceList.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}
	
	/**
	 * 查询产品信息
	 * 
	 * @param request HttpServletRequest
	 * @param bean 查询条件
	 * @return 操作结果
	 */
	@RequestMapping(value = "proLists", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> proLists(HttpServletRequest request, ProInfoManage bean) {
		if (SysUtil.hasRight(request, "proInfoManage", GlobalVal.MENU_FUNCTION.VIEW)) {
			if(bean.getProduct_chnpy()=="")
				bean.setProduct_chnpy(null);
			return proInfoManageService.list(bean,"");
		} else {
			return Maps.newHashMap();
		}
	}
	
	/**
	 * 查询调价信息
	 * 
	 * @param request HttpServletRequest
	 * @param bean 查询条件
	 * @return 操作结果
	 */
	@RequestMapping(value = "stockPriceList", method = RequestMethod.GET)
	@ResponseBody
	public List<StockPrice> stockPriceList(HttpServletRequest request, StockPrice bean) {
		if (SysUtil.hasRight(request, "stockPrice", GlobalVal.MENU_FUNCTION.VIEW)) {
			return stockPriceService.getStockPriceList(bean);
		} else {
			return new ArrayList<StockPrice>();
		}
	}
	
	/**
	 * 修改产品的价格
	 * 
	 * @param request HttpServletRequest
	 * @param id 产品信息ID
	 * @param model Model
	 * @return 查看产品信息页面路径
	 */
	@RequestMapping(value = "proPrice/{id}", method = RequestMethod.GET)
	public String viewDetail(HttpServletRequest request, @PathVariable("id") Integer id, Model model) {
		if (SysUtil.hasRight(request, "proInfoManage", GlobalVal.MENU_FUNCTION.VIEW)) {
			ProInfoManage proInfoManage = proInfoManageService.getById(id);
			model.addAttribute("proInfoManage", proInfoManage);
			model.addAttribute("method", "PUT");
			model.addAttribute("formUrl", "/product/stockPrice/proPrice/" + id);
			SysUtil.getToken(request);
			return "product/proStockPrice/proInfoPriceUpdate.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}

	/**
	 * 修改产品的价格
	 * 
	 * @param request HttpServletRequest
	 * @param id 产品信息ID
	 * @param bean 产品信息
	 * @return 操作结果
	 */
	@RequestMapping(value = "proPrice/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Map<String, Object> update(HttpServletRequest request, @PathVariable("id") Integer id, ProInfoManage bean) {
		Map<String, Object> result = Maps.newHashMap();
		request.getParameterMap();
		if (SysUtil.hasRight(request, "proInfoManage", GlobalVal.MENU_FUNCTION.UPDATE)) {
				// 修改产品价格
				proInfoManageService.update(bean, request);
				result.put("result", "success");
				result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		return result;
	}
	
	/**
	 * 查询分店价格信息
	 * 
	 * @param request HttpServletRequest
	 * @param bean 查询条件
	 * @return 操作结果
	 */
	@RequestMapping(value = "getDeptsByPro", method = RequestMethod.GET)
	@ResponseBody
	public List<ProStockPriceVO> getDeptsByPro(HttpServletRequest request, StockPrice bean,String product_name) {
		if (SysUtil.hasRight(request, "stockPrice", GlobalVal.MENU_FUNCTION.VIEW)) {
			return stockPriceService.getDeptsByPro(bean,product_name);
		}else{
			return Lists.newArrayList();
		}
	}
	
	/**
	 * 修改分店的定价页面
	 * 
	 * @param request HttpServletRequest
	 * @param id 产品ID
	 * @param model Model
	 * @return 查看分店价格信息页面路径
	 */
	@RequestMapping(value = "dept/update", method = RequestMethod.GET)
	public String update(HttpServletRequest request, Model model,StockPrice stockPrice,String product_name,String department_fullname) {
		if (SysUtil.hasRight(request, "stockPrice", GlobalVal.MENU_FUNCTION.VIEW)) {
			if(stockPrice.getProd_id()!=-1){
				stockPrice = stockPriceService.getById(stockPrice.getProd_id());
			}

			model.addAttribute("product_name", product_name);
			model.addAttribute("department_fullname", department_fullname);
			model.addAttribute("stockPrice",stockPrice);
			model.addAttribute("method", "PUT");
			model.addAttribute("formUrl", "/product/stockPrice/dept/" + stockPrice.getProd_id());
			return "product/proStockPrice/proStockPriceUpdate.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}
	/**
	 * 修改分店的定价
	 * 
	 * @param request HttpServletRequest
	 * @param id 产品信息ID
	 * @param bean 产品信息
	 * @return 操作结果
	 */
	@RequestMapping(value = "dept/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Map<String, Object> updateStockPrice(HttpServletRequest request, @PathVariable("id") Integer id, StockPrice bean) {
		Map<String, Object> result = Maps.newHashMap();
		request.getParameterMap();
		if (SysUtil.hasRight(request, "stockPrice", GlobalVal.MENU_FUNCTION.UPDATE)) {
			stockPriceService.update(bean, request);
			
			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		
		return result;
	}

}
