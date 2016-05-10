package com.goldcow.emanage.quality.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goldcow.emanage.quality.service.IQltDefectsInfoService;
import com.goldcow.emanage.util.gen.entity.valueObject.quality.QltDefectsInfoVO;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Maps;

/**
 * 不合格品种信息确认
 * 
 * @author gaoxiang
 * @since 2015-12-1
 */

@Controller
@RequestMapping(value = "/quality/confirm")
public class QltDefectsInfoConfirmController {
	/** 不合格品种信息管理服务 */
	@Autowired
	protected IQltDefectsInfoService qltDefectsInfoService;
	
	/**
	 * 不合格品种信息确认页面
	 * 
	 * @param request HttpServletRequest
	 * @return 不合格品种信息页面路径
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String view(HttpServletRequest request) {
		if (SysUtil.hasRight(request, "qltDefectsInfoVO", GlobalVal.MENU_FUNCTION.VIEW)) {
			return "/quality/defectsInfo/qltDefectsInfoConfirmList.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}

	/**
	 * 查询不合格品种信息
	 * 
	 * @param request HttpServletRequest
	 * @param bean 查询条件
	 * @return 操作结果
	 */
	@RequestMapping(value = "lists", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> listDefectsInfoVO(HttpServletRequest request, QltDefectsInfoVO bean) {
		if (SysUtil.hasRight(request, "qltDefectsInfoVO", GlobalVal.MENU_FUNCTION.VIEW)) {	
			return qltDefectsInfoService.list(bean);
		} else {
			return Maps.newHashMap();
		}
	}
	
	/**
	 * 不合格品种信息确认通过
	 * 
	 * @param request HttpServletRequest
	 * @param id 审核通过品种的id
	 * @return 操作结果
	 *//*
	@RequestMapping(value = "pass", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> pass(HttpServletRequest request,  Integer id) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "qltDefectsInfoVO", GlobalVal.MENU_FUNCTION.UPDATE)) {
			QltDefectsInfo qltDefectsInfo = new QltDefectsInfo(); 
			qltDefectsInfo.setDefects_id(id);
			qltDefectsInfo.setDefects_check(0);
			qltDefectsInfoService.update(qltDefectsInfo, request);
			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		return result;
	}*/
	
	@RequestMapping(value = "check", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> check(HttpServletRequest request, String data) {
		Map<String, Object> result = Maps.newHashMap();
		request.getParameterMap();
		if (SysUtil.hasRight(request, "qltDefectsInfoVO", GlobalVal.MENU_FUNCTION.UPDATE)) {
			
			qltDefectsInfoService.check(data, request);
			
			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}

		return result;
	}
	
	/**
	 * 删除不合格品种信息
	 * 
	 * @param request HttpServletRequest
	 * @param id 被删除的养护品种信息ID
	 * @return 操作结果
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request, @PathVariable("id") Integer id) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "qltDefectsInfoVO", GlobalVal.MENU_FUNCTION.DELETE)) {
			// 删除养护品种信息
			qltDefectsInfoService.delete(id, request);
			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		return result;
	}
}