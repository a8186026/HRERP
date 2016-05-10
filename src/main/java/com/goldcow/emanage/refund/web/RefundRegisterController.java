package com.goldcow.emanage.refund.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goldcow.emanage.accept.vo.PurSupAndProVO;
import com.goldcow.emanage.refund.service.IRefundService;
import com.goldcow.emanage.stock.service.IStockInfoService;
import com.goldcow.emanage.util.gen.entity.ProInfoManage;
import com.goldcow.emanage.util.gen.entity.PurOrderList;
import com.goldcow.emanage.util.gen.entity.Refund;
import com.goldcow.emanage.util.gen.entity.StockInfo;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Maps;


@Controller
@RequestMapping(value = "/refund/register")
public class RefundRegisterController {

	
	@Autowired
	private IRefundService refundService;
	@Autowired
	private IStockInfoService stockInfoService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String view(HttpServletRequest request, Model model) {
		System.out.println("打开返货登记页面");
		if (SysUtil.hasRight(request, "/refund/register", GlobalVal.MENU_FUNCTION.VIEW)) {
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
			model.addAttribute("ticket_id", formatter.format(date));
			model.addAttribute("user",SysUtil.getLoginUser(request));
			model.addAttribute("formUrl","/refund/register/new");
			model.addAttribute("method","POST");
			
			
			SysUtil.getToken(request);
			return "refund/refundRegister/refundRegister.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}
	
	
	@RequestMapping(value = "/lists", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> lists(HttpServletRequest request,
			Refund bean,
			Model model) {
		if (SysUtil.hasRight(request, "/refund/register", GlobalVal.MENU_FUNCTION.VIEW)) {
			return refundService.getStockListtoRefund(bean);
		} else {
			return Maps.newHashMap();
		}
	}
	
	
	@RequestMapping(value = "viewStockProList", method = RequestMethod.GET)
	public String viewStockProList(HttpServletRequest request, Model model, String callback, String proContent) {
		if (SysUtil.hasRight(request, "viewStockProList", GlobalVal.MENU_FUNCTION.VIEW)) {
			System.out.println("查询库房产品信息");
			model.addAttribute("callback", callback);
			model.addAttribute("proContent", proContent);
			return "refund/refundRegister/refundViewStockProList.jsp";
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
	@RequestMapping(value = "getStockProList", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getStockProList(HttpServletRequest request, ProInfoManage bean,String code) {
		if (SysUtil.hasRight(request, "proInfoManage", GlobalVal.MENU_FUNCTION.VIEW)) {
			if(bean.getProduct_chnpy()=="")
				bean.setProduct_chnpy(null);
			return refundService.getStockProList(bean,code);
		} else {
			return Maps.newHashMap();
		}
	}
	/**
	 * 新增返货信息
	 * 
	 * @param request HttpServletRequest
	 * @param bean 订购产品信息
	 * @return 操作结果
	 */
	@RequestMapping(value = "new", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> add(HttpServletRequest request, Refund bean) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "Refund", GlobalVal.MENU_FUNCTION.UPDATE)) {	
			//原库房减掉返货的库存量
			StockInfo stockInfo = new StockInfo();
			stockInfo = stockInfoService.getById(bean.getStock_info_id());
			//stockInfo.setStock_storage(bean.getStock_info_id());
			stockInfo.setStock_storageNumber(stockInfo.getStock_storageNumber()-bean.getRefund_number());
			
			stockInfoService.update(stockInfo, request);
			
		
			refundService.add(bean, request);
			
			
			result.put("result", "success");
			result.put("message", "操作成功！");
			
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		
		return result;
	}
	
}
