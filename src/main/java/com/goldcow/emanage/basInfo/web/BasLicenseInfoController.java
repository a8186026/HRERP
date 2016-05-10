package com.goldcow.emanage.basInfo.web;

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
 

import com.goldcow.emanage.basInfo.service.IBasLicenseInfoService;
import com.goldcow.emanage.util.gen.entity.BasLicenseInfo;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil; 
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 证照信息
 * 
 * @author gaoxiang
 * @version v1.0
 * @since 2015-6-30
 */
@Controller
@RequestMapping(value = "/basInfo/basLicenseInfo")
public class BasLicenseInfoController {
	/** 证照信息管理服务 */
	@Autowired
	protected IBasLicenseInfoService licenseInfoService;
	
	/**
	 * 证照信息管理页面
	 * 
	 * @param request HttpServletRequest
	 * @return 证照信息页面路径
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String view(HttpServletRequest request) {
		if (SysUtil.hasRight(request, "basLicenseInfo", GlobalVal.MENU_FUNCTION.VIEW)) {
			return "basInfo/basLicenseInfo/basLicenseInfoList.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}

	/**
	 * 查询证照信息
	 * 
	 * @param request HttpServletRequest
	 * @param bean 查询条件
	 * @return 操作结果
	 */
	@RequestMapping(value = "lists", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> list(HttpServletRequest request, BasLicenseInfo bean,Integer license_type_id,String license_type) {
		if (SysUtil.hasRight(request, "basLicenseInfo", GlobalVal.MENU_FUNCTION.VIEW)) {
			bean.setLicense_type_id(license_type_id);
			bean.setLicense_type(license_type);
			return licenseInfoService.lists(bean);
		} else {
			return Maps.newHashMap();
		}
	}
	
	/**
	 * 新增证照信息
	 *  
	 * @param request HttpServletRequest
	 * @param model Model
	 * @return 新增证照信息页面路径
	 */
	@RequestMapping(value = "new", method = RequestMethod.GET)
	public String newMenu(HttpServletRequest request, Model model, Integer license_type_id, String license_type) {
		if (SysUtil.hasRight(request, "basLicenseInfo", GlobalVal.MENU_FUNCTION.VIEW)) {			
			BasLicenseInfo basLicenseInfo = new BasLicenseInfo();
			basLicenseInfo.setLicense_type(license_type);
			basLicenseInfo.setLicense_type_id(license_type_id);
			model.addAttribute("basLicenseInfo", basLicenseInfo);
			model.addAttribute("method", "POST");
			model.addAttribute("formUrl", "/basInfo/basLicenseInfo/new");
			SysUtil.getToken(request);
			return "basInfo/basLicenseInfo/basLicenseInfoEdit.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}
	
	/**
	 * 新增证照信息
	 * 
	 * @param request HttpServletRequest
	 * @param bean 证照信息
	 * @return 操作结果
	 */
	@RequestMapping(value = "new", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> add(HttpServletRequest request, BasLicenseInfo bean) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "basLicenseInfo", GlobalVal.MENU_FUNCTION.UPDATE)) {
			licenseInfoService.add(bean, request);
			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		
		return result;
	}
		
		 
	
	/**
	 * 查看证照信息页面
	 * 
	 * @param request HttpServletRequest
	 * @param id 证照信息ID
	 * @param model Model
	 * @return 查看证照信息页面路径
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public String viewDetail(HttpServletRequest request, @PathVariable("id") Integer id, Model model) {
		if (SysUtil.hasRight(request, "basLicenseInfo", GlobalVal.MENU_FUNCTION.VIEW)) {
			BasLicenseInfo basLicenseInfo = licenseInfoService.getById(id);
			model.addAttribute("basLicenseInfo", basLicenseInfo);
			model.addAttribute("method", "PUT");
			model.addAttribute("formUrl", "/basInfo/basLicenseInfo/" + id);
			SysUtil.getToken(request);
			return "basInfo/basLicenseInfo/basLicenseInfoEdit.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}



	/**
	 * 修改证照信息
	 * 
	 * @param request HttpServletRequest
	 * @param id 证照信息ID
	 * @param bean 证照信息
	 * @return 操作结果
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Map<String, Object> update(HttpServletRequest request, @PathVariable("id") Integer id, BasLicenseInfo bean) {
		Map<String, Object> result = Maps.newHashMap();
		request.getParameterMap();
		if (SysUtil.hasRight(request, "basLicenseInfo", GlobalVal.MENU_FUNCTION.UPDATE)) {
				// 修改证照信息
				licenseInfoService.update(bean, request);
				result.put("result", "success");
				result.put("message", "操作成功！");
			}
		  else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		return result;
	}

	/**
	 * 删除证照信息
	 * 
	 * @param request HttpServletRequest
	 * @param id 被删除的证照信息ID
	 * @return 操作结果
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request, @PathVariable("id") Integer id) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "basLicenseInfo", GlobalVal.MENU_FUNCTION.DELETE)) {
			// 删除证照信息
			licenseInfoService.delete(id, request);
			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		return result;
	}
}