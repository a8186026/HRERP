package com.goldcow.emanage.quality.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goldcow.emanage.quality.service.IQltMaintainInfoService;
import com.goldcow.emanage.util.gen.entity.QltMaintainInfo;
import com.goldcow.emanage.util.gen.entity.valueObject.quality.QltMaintainInfoVO;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Maps;

/**
 * 养护品种信息
 * 
 * @author gaoxiang
 * @since 2015-11-23
 */

@Controller
@RequestMapping(value = "/quality/maintainInfo")
public class QltMaintainInfoController {
	/** 养护品种信息管理服务 */
	@Autowired
	protected IQltMaintainInfoService qltMaintainInfoService;
	
	/**
	 * 养护品种信息管理页面
	 * 
	 * @param request HttpServletRequest
	 * @return 养护品种信息页面路径
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String view(HttpServletRequest request) {
		if (SysUtil.hasRight(request, "qltMaintainInfoVO", GlobalVal.MENU_FUNCTION.VIEW)) {
			return "/quality/maintainInfo/qltMaintainInfoList.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}

	/**
	 * 查询养护品种信息
	 * 
	 * @param request HttpServletRequest
	 * @param bean 查询条件
	 * @return 操作结果
	 */
	@RequestMapping(value = "lists", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> listMaintainInfoVO(HttpServletRequest request, QltMaintainInfoVO bean) {
		if (SysUtil.hasRight(request, "qltMaintainInfoVO", GlobalVal.MENU_FUNCTION.VIEW)) {	
			return qltMaintainInfoService.list(bean);
		} else {
			return Maps.newHashMap();
		}
	}
	
	/**
	 * 新增养护品种信息
	 *  
	 * @param request HttpServletRequest
	 * @param model Model
	 * @return 新增养护品种信息页面路径
	 */
	@RequestMapping(value = "new", method = RequestMethod.GET)
	public String newMenu(HttpServletRequest request, Model model) {
		if (SysUtil.hasRight(request, "qltMaintainInfo", GlobalVal.MENU_FUNCTION.VIEW)) {			
			QltMaintainInfo qltMaintainInfo = new QltMaintainInfo(); 
			 
			model.addAttribute("qltMaintainInfo", qltMaintainInfo);
			model.addAttribute("method", "POST");
			model.addAttribute("formUrl", "/quality/maintainInfo/new");
			SysUtil.getToken(request);
			return "/quality/maintainInfo/qltMaintainInfoEdit.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}
	
	
	/**
	 * 查看养护品种信息页面
	 * 
	 * @param request HttpServletRequest
	 * @param id 养护品种信息ID
	 * @param model Model
	 * @return 查看养护品种信息页面路径
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public String viewDetail(HttpServletRequest request, @PathVariable("id") Integer id, Model model) {
		if (SysUtil.hasRight(request, "qltMaintainInfoVO", GlobalVal.MENU_FUNCTION.VIEW)) {
			QltMaintainInfoVO qltMaintainInfoVO = qltMaintainInfoService.getById(id);
			model.addAttribute("qltMaintainInfoVO", qltMaintainInfoVO);
			model.addAttribute("method", "PUT");
			model.addAttribute("formUrl", "/quality/maintainInfo/" + id);
			SysUtil.getToken(request);
			return "/quality/maintainInfo/qltMaintainInfoEdit.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}



	/**
	 * 修改养护品种信息
	 * 
	 * @param request HttpServletRequest
	 * @param id 养护品种信息ID
	 * @param bean 养护品种信息
	 * @return 操作结果
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Map<String, Object> update(HttpServletRequest request, @PathVariable("id") Integer id, QltMaintainInfoVO bean) {
		Map<String, Object> result = Maps.newHashMap();
		request.getParameterMap();
		if (SysUtil.hasRight(request, "qltMaintainInfoVO", GlobalVal.MENU_FUNCTION.UPDATE)) {
			if (SysUtil.checkToken(request)) {
				// 修改养护品种信息
				qltMaintainInfoService.update(bean, request);

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
	 * 删除养护品种信息
	 * 
	 * @param request HttpServletRequest
	 * @param id 被删除的养护品种信息ID
	 * @return 操作结果
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request, @PathVariable("id") Integer id) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "qltMaintainInfo", GlobalVal.MENU_FUNCTION.DELETE)) {
			// 删除养护品种信息
			qltMaintainInfoService.delete(id, request);
			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		return result;
	}
	
}