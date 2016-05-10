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
import com.goldcow.emanage.util.gen.entity.PurAcceptCheck;
import com.goldcow.emanage.util.gen.entity.valueObject.accept.PurAcceptCheckVO;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Maps;

/**
 * 不合格审核
 * 
 * @author YuanXin
 * @version v1.0
 * @since 2015-10-13
 */
@Controller
@RequestMapping(value = "/purchase/acceptSchedule")
public class PurAcceptScheduleController {
	
	@Autowired
	protected IPurAcceptCheckService acceptCheckService;
	
	
	/**
	 * 页面跳转
	 * @param request HttpServletRequest
	 * @return 页面路径
	 * 
	 * */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String viewPurOrder(HttpServletRequest request, Model model) {
		if (SysUtil.hasRight(request, "", GlobalVal.MENU_FUNCTION.VIEW)) {
			return "accept/acceptSchedule/acceptScheduleList.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}
	/**
	 * 确认收货——查询大单下的所有小单信息
	 * 
	 * @param request HttpServletRequest
	 * @param bean 查询条件
	 * @return 操作结果
	 */
	@RequestMapping(value = "lists", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> list(HttpServletRequest request, PurAcceptCheckVO bean) {
		if (SysUtil.hasRight(request, "PurAcceptCheck", GlobalVal.MENU_FUNCTION.VIEW)) {
			return acceptCheckService.listDetail(bean);
		} else {
			return Maps.newHashMap();
		}
	}
	
	@RequestMapping(value = "checkView", method = RequestMethod.GET )
		public String check(HttpServletRequest request, Model model,Integer id) {
			if (SysUtil.hasRight(request, "", GlobalVal.MENU_FUNCTION.VIEW)) {
				
				PurAcceptCheck purAcceptCheck = new PurAcceptCheck();
				purAcceptCheck.setAccept_quantityCheckStatus(1);
				purAcceptCheck.setAccept_check_id(id);
				
				model.addAttribute("purAcceptCheck", purAcceptCheck);
				model.addAttribute("method","PUT");
				//不合格品有选择原因和处理方式的页面
				model.addAttribute("formUrl", "/purchase/Unqualitedcheck/" + id );
				SysUtil.getToken(request);
				return "accept/unqualifiedCheck/purUnqualifiedEdit.jsp";
			} else {
				return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
			}
		}
	/**
	 * 不合格
	 * 
	 * @param request HttpServletRequest
	 * @param id 入库信息ID
	 * @param bean 入库信息
	 * @return 操作结果
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Map<String, Object> update(HttpServletRequest request, @PathVariable("id") Integer id, PurAcceptCheck bean) {
		Map<String, Object> result = Maps.newHashMap();
		request.getParameterMap();
		if (SysUtil.hasRight(request, "purStorageCheck", GlobalVal.MENU_FUNCTION.UPDATE)) {
			// 修改销方信息
			bean.setAccept_check_id(id);
		
			acceptCheckService.update(bean, request);

			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		return result;
	}
	/**
	 * 合格
	 * 
	 * @param request HttpServletRequest
	 * @param id 入库信息ID
	 * @param bean 入库信息
	 * @return 操作结果
	 */
	@RequestMapping(value = "pass", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> updatePass(HttpServletRequest request,Integer id, PurAcceptCheck bean) {
		Map<String, Object> result = Maps.newHashMap();
		request.getParameterMap();
		if (SysUtil.hasRight(request, "purStorageCheck", GlobalVal.MENU_FUNCTION.UPDATE)) {
			// 修改销方信息
			bean.setAccept_check_id(id);
			bean.setAccept_quantityStatus(0);//质量验收通过
			bean.setAccept_quantityCheckStatus(0);//质量审核通过
			acceptCheckService.update(bean, request);

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
	 *//*
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
	}*/
	
}