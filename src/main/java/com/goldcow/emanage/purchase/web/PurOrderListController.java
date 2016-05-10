package com.goldcow.emanage.purchase.web;

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

import com.goldcow.emanage.purchase.service.IPurOrderListService;
import com.goldcow.emanage.purchase.service.IPurOrderService;
import com.goldcow.emanage.util.gen.entity.PurOrderList;
import com.goldcow.emanage.util.gen.entity.valueObject.purchase.PurOrderListAndProInfo;
import com.goldcow.emanage.util.gen.entity.valueObject.purchase.PurOrderListVO;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 进货订单
 * 
 * @author RiverYao
 * @version v1.0
 * @since 2015-07-02
 */
@Controller
@RequestMapping(value = "/purchase/orderList")
public class PurOrderListController {

	@Autowired
	private IPurOrderListService purOrderListService;
	@Autowired
	private IPurOrderService purOrderService;
	/**
	 * 进货订单
	 * 
	 * @param request HttpServletRequest
	 * @param model Model
	 * @return 进货订单页面路径
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String view(HttpServletRequest request, Model model) {
		if (SysUtil.hasRight(request, "purOrderList", GlobalVal.MENU_FUNCTION.VIEW)) {
			// 待生成的订单票号
			String purchase_ID ="000000000001";
			/*
			 * 生成订单票号算法
			 */
			model.addAttribute("purchase_ID", purchase_ID);
			//当前登录用户
			model.addAttribute("user",SysUtil.getLoginUser(request));
			return "purchase/purOrder/purOrderEdit.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}
	/**
	 * 查询产品进货订单
	 * 
	 * @param request HttpServletRequest
	 * @param bean 查询条件
	 * @return 操作结果
	 */
	@RequestMapping(value = "lists", method = RequestMethod.GET)
	@ResponseBody
	public List<PurOrderList> list(HttpServletRequest request,PurOrderList bean) {
		if (SysUtil.hasRight(request, "purOrderList", GlobalVal.MENU_FUNCTION.VIEW)) {	
			return purOrderListService.list(bean);
		} else {
			return Lists.newArrayList();
		}
	}
	
	/**
	 * 查询最近某产品三次进货
	 * 
	 * @param request HttpServletRequest
	 * @param bean 查询条件
	 * @return 操作结果
	 */
	@RequestMapping(value = "listRecent", method = RequestMethod.GET)
	@ResponseBody
	public List<PurOrderListVO> listRecent(HttpServletRequest request,PurOrderList bean) {
		if (SysUtil.hasRight(request, "purOrderList", GlobalVal.MENU_FUNCTION.VIEW)) {
			return purOrderListService.listRecent(bean);
		} else {
			return Lists.newArrayList();
		}
	}
	
	/**
	 * 新增订购产品信息
	 * 
	 * @param request HttpServletRequest
	 * @param bean 订购产品信息
	 * @return 操作结果
	 */
	@RequestMapping(value = "newlist", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> add(HttpServletRequest request, PurOrderList bean) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "purOrderList", GlobalVal.MENU_FUNCTION.UPDATE)) {			
			purOrderListService.add(bean, request);
			result.put("result", "success");
			result.put("message", "操作成功！");
			
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		
		return result;
	}

	/**
	 * 修改订购产品信息
	 * 
	 * @param request HttpServletRequest
	 * @param id 订购产品信息ID
	 * @param bean 订购产品信息
	 * @return 操作结果
	 */
	@RequestMapping(value = "list/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Map<String, Object> update(HttpServletRequest request, @PathVariable("id") Integer id, PurOrderList bean) {
		Map<String, Object> result = Maps.newHashMap();
		request.getParameterMap();
		if (SysUtil.hasRight(request, "purOrderList", GlobalVal.MENU_FUNCTION.UPDATE)) {
			purOrderListService.update(bean, request);
			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		return result;
	}
	/**
	 * 删除订购产品信息
	 * 
	 * @param request HttpServletRequest
	 * @param id 被删除的订购产品信息ID
	 * @return 操作结果
	 */
	@RequestMapping(value = "list/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request, @PathVariable("id") Integer id) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "purOrderList", GlobalVal.MENU_FUNCTION.DELETE)) {
			// 删除供方客户信息
			purOrderListService.delete(id, request);
			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		return result;
	}
	/**
	 * 查询产品进货订单
	 * 
	 * @param request HttpServletRequest
	 * @param bean 查询条件
	 * @return 操作结果
	 */
	@RequestMapping(value = "listCheckOrderList", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> listCheckOrderList(HttpServletRequest request,PurOrderListAndProInfo bean) {
		if (SysUtil.hasRight(request, "purOrderList", GlobalVal.MENU_FUNCTION.VIEW)) {	
			return purOrderListService.listCheckOrderList(bean);
		} else {
			return Maps.newHashMap();
		}
	}
	/**
	 * 查询通过订单审核的小单信息
	 * 
	 * * @author gaoxiang
	 * 
	 * @param request HttpServletRequest
	 * @param bean 查询条件
	 * @return 操作结果
	 */
	@RequestMapping(value = "listRecepitOrderList", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> listRecepitOrderList(HttpServletRequest request,PurOrderListAndProInfo bean) {
		if (SysUtil.hasRight(request, "purOrderList", GlobalVal.MENU_FUNCTION.VIEW)) {	
			return purOrderListService.listRecepitOrderList(bean);
		} else {
			return Maps.newHashMap();
		}
	}
}