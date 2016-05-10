package com.goldcow.emanage.accept.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goldcow.emanage.accept.service.IPurAcceptCheckService;
import com.goldcow.emanage.purchase.service.IPurOrderListService;
import com.goldcow.emanage.util.gen.entity.PurAcceptCheck;
import com.goldcow.emanage.util.gen.entity.PurOrderList;
import com.goldcow.emanage.util.gen.entity.valueObject.accept.PurAcceptCheckVO;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Maps;

/**
 * 订单收货确认信息
 * 
 * @author gaoxiang
 * @version v1.0
 * @since 2015-7-15
 */
@Controller
@RequestMapping(value = "/accept/receipt")
public class PurReceiptCheckController {
	/** 订单收货确认信息管理服务 */
	@Autowired
	protected IPurAcceptCheckService purAcceptCheckService;
	@Autowired
	protected IPurOrderListService purOrderListService;

	/**
	 * 订单收货确认信息管理页面
	 * 
	 * @param request HttpServletRequest
	 * @return 订单收货确认信息页面路径
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String view(HttpServletRequest request) {
		if (SysUtil.hasRight(request, "purAcceptCheckVO",
				GlobalVal.MENU_FUNCTION.VIEW)) {
			return "accept/receiptCheck/purReceiptCheckList.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}

	/**
	 * 添加收货订单确认信息
	 * 
	 * @param request HttpServletRequest
	 * @param id 收货订单信息ID
	 * @param model Model
	 * @return 查看收货订单信息页面路径
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public String viewDetail(HttpServletRequest request,
			@PathVariable("id") Integer id, Model model) {
		if (SysUtil.hasRight(request, "purAcceptCheck",
				GlobalVal.MENU_FUNCTION.VIEW)) {
			PurOrderList purOrderList = purOrderListService.getById(id);
			model.addAttribute("purOrderList", purOrderList);
			model.addAttribute("method", "PUT");
			model.addAttribute("formUrl", "/accept/receipt/" + id);
			SysUtil.getToken(request);
			return "accept/receiptCheck/purReceiptCheckEdit.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}


	/**
	 * 添加收货订单确认信息
	 * 
	 * @param request HttpServletRequest
	 * @param id 收货订单信息ID
	 * @param bean 收货订单信息
	 * @return 操作结果
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Map<String, Object> update(HttpServletRequest request, @PathVariable("id") Integer id, PurAcceptCheck bean) {
		Map<String, Object> result = Maps.newHashMap();
		request.getParameterMap();
		if (SysUtil.hasRight(request, "purAcceptCheck", GlobalVal.MENU_FUNCTION.UPDATE)) {
			if (SysUtil.checkToken(request)) {
				
				// 添加收货订单确认信息
				purAcceptCheckService.add(bean, request);

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
	 * 确认收货——查询大单下的所有小单信息
	 * 
	 * @param request HttpServletRequest
	 * @param bean 查询条件
	 * @return 操作结果
	 */
	@RequestMapping(value = "listOrderList", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> listOrderList(HttpServletRequest request, PurAcceptCheckVO bean) {
		if (SysUtil.hasRight(request, "purAcceptCheckVO", GlobalVal.MENU_FUNCTION.VIEW)) {	
			return purAcceptCheckService.listOrderList(bean);
		} else {
			return Maps.newHashMap();
		}
	}
}