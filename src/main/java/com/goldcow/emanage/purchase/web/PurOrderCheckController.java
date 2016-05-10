package com.goldcow.emanage.purchase.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goldcow.emanage.purchase.service.IPurOrderListService;
import com.goldcow.emanage.purchase.service.IPurOrderService;
import com.goldcow.emanage.util.gen.entity.PurOrder;
import com.goldcow.emanage.util.gen.entity.PurOrderList;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Maps;

/**
 * 订单收货确认信息
 * 
 * @author YuanXin
 * @version v1.0
 * @since 2015-9-16
 */
@Controller
@RequestMapping(value = "/purchase/check")
public class PurOrderCheckController {
	/** 订单审核相关 */
	@Autowired
	protected IPurOrderService purOrderService;
	@Autowired
	protected IPurOrderListService purOrderListService;
	
	/**
	 * 订单审核时改动页面
	 * 
	 * @param request HttpServletRequest
	 * @return 订单审核时改动路径
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public String view(HttpServletRequest request,@PathVariable("id") Integer id, Model model) {
		if (SysUtil.hasRight(request, "purOrderCheck", GlobalVal.MENU_FUNCTION.VIEW)) {
			PurOrderList purOrderList = purOrderListService.getById(id);
			model.addAttribute("purOrderList", purOrderList);
			model.addAttribute("method", "PUT");
			model.addAttribute("formUrl", "/purchase/check/" + id);
			SysUtil.getToken(request);
			return "purchase/purOrder/purOrderListCheckEdit.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}



	/**
	 * 修改订单收货确认信息
	 * 
	 * @param request HttpServletRequest
	 * @param id 订单收货确认信息ID
	 * @param bean 订单收货确认信息
	 * @return 操作结果
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Map<String, Object> update(HttpServletRequest request, @PathVariable("id") Integer id, PurOrderList bean) {
		Map<String, Object> result = Maps.newHashMap();
		request.getParameterMap();
		PurOrderList pur = purOrderListService.getById(id);
		pur.setUnitprice(bean.getUnitprice());
		pur.setSettlementPrice(bean.getSettlementPrice());
		pur.setQuantity(bean.getQuantity());
		pur.setSum(bean.getSum());
		
		if (SysUtil.hasRight(request, "purOrderCheck", GlobalVal.MENU_FUNCTION.UPDATE)) {
			if (SysUtil.checkToken(request)) {
				// 修改订单收货确认信息
				purOrderListService.update(pur, request);

				result.put("result", "success");
				result.put("message", "操作成功！");
			} else {
				result.put("result", "failure");
				result.put("message", "非法的请求！");
			}
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}

		return result;
	}
	/**
	 * 订单审核通过
	 * 
	 * @param request HttpServletRequest
	 * @param id 审核通过订单的id
	 * @return 操作结果
	 */
	@RequestMapping(value = "pass", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> pass(HttpServletRequest request,  Integer id) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "purOrderCheck", GlobalVal.MENU_FUNCTION.UPDATE)) {
			PurOrder purOrder = new PurOrder();
			purOrder.setId(id);
		    purOrder.setCheckStatus(GlobalVal.ACCEPT_STATUS.RECEIVED);
		    purOrderService.update(purOrder, request);
			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		return result;
	}
	/**
	 * 删除订单收货确认信息
	 * 
	 * @param request HttpServletRequest
	 * @param id 被删除的订单收货确认信息ID
	 * @return 操作结果
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request, @PathVariable("id") Integer id) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "purOrderCheck", GlobalVal.MENU_FUNCTION.DELETE)) {
			// 删除订单收货确认信息
			purOrderListService.delete(id, request);
			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		return result;
	}
	
}