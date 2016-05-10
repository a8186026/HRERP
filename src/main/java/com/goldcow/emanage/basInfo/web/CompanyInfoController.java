package com.goldcow.emanage.basInfo.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goldcow.emanage.basInfo.service.ICompanyInfoService;
import com.goldcow.emanage.util.gen.entity.BasAccount;
import com.goldcow.emanage.util.gen.entity.CompanyInfo;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Maps;

/**
 * 公司信息
 * 
 * @author wubin
 * @version v1.0
 * @since 2015-1-4
 */
@Controller
@RequestMapping(value = "/basInfo/companyInfo")
public class CompanyInfoController {
	/** 公司信息管理服务 */
	@Autowired
	protected ICompanyInfoService companyInfoService;
	
	/**
	 * 公司信息管理页面
	 * 
	 * @param request HttpServletRequest
	 * @return 公司信息页面路径
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String view(HttpServletRequest request) {
		if (SysUtil.hasRight(request, "companyInfo", GlobalVal.MENU_FUNCTION.VIEW)) {
			return "basInfo/companyInfo/companyInfoList.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}

	/**
	 * 查询公司信息
	 * 
	 * @param request HttpServletRequest
	 * @param bean 查询条件
	 * @return 操作结果
	 */
	@RequestMapping(value = "lists", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> list(HttpServletRequest request, CompanyInfo bean) {
		if (SysUtil.hasRight(request, "companyInfo", GlobalVal.MENU_FUNCTION.VIEW)) {	
			return companyInfoService.lists(bean);
		} else {
			return Maps.newHashMap();
		}
	}
	
	/**
	 * 新增公司信息
	 *  
	 * @param request HttpServletRequest
	 * @param model Model
	 * @return 新增公司信息页面路径
	 */
	@RequestMapping(value = "new", method = RequestMethod.GET)
	public String newMenu(HttpServletRequest request, Model model) {
		if (SysUtil.hasRight(request, "companyInfo", GlobalVal.MENU_FUNCTION.VIEW)) {			
			model.addAttribute("method", "POST");
			model.addAttribute("formUrl", "/basInfo/companyInfo/new");
			return "basInfo/companyInfo/companyInfoEdit.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}
	
	/**
	 * 新增公司信息
	 * 
	 * @param request HttpServletRequest
	 * @param bean 公司信息
	 * @return 操作结果
	 */
	@RequestMapping(value = "new", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> add(HttpServletRequest request, CompanyInfo bean) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "companyInfo", GlobalVal.MENU_FUNCTION.UPDATE)) {
			// 新增公司信息
			companyInfoService.add(bean, request);
			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		
		return result;
	}
		
		 
	
	/**
	 * 查看公司信息页面
	 * 
	 * @param request HttpServletRequest
	 * @param id 公司信息ID
	 * @param model Model
	 * @return 查看公司信息页面路径
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public String viewDetail(HttpServletRequest request, @PathVariable("id") Integer id, Model model) {
		if (SysUtil.hasRight(request, "companyInfo", GlobalVal.MENU_FUNCTION.VIEW)) {
			
			CompanyInfo companyInfo = companyInfoService.getById(id);
			model.addAttribute("companyInfo", companyInfo);
			model.addAttribute("method", "PUT");
			model.addAttribute("formUrl", "/basInfo/companyInfo/" + id);
			return "basInfo/companyInfo/companyInfoEdit.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}



	/**
	 * 修改公司信息
	 * 
	 * @param request HttpServletRequest
	 * @param id 公司信息ID
	 * @param bean 公司信息
	 * @return 操作结果
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Map<String, Object> update(HttpServletRequest request, @PathVariable("id") Integer id, CompanyInfo bean) {
		Map<String, Object> result = Maps.newHashMap();
		request.getParameterMap();
		if (SysUtil.hasRight(request, "companyInfo", GlobalVal.MENU_FUNCTION.UPDATE)) {
			// 修改公司信息
			companyInfoService.update(bean, request);

			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}

		return result;
	}

	/**
	 * 删除公司信息
	 * 
	 * @param request HttpServletRequest
	 * @param id 被删除的公司信息ID
 	 */
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request, @PathVariable("id") Integer id) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "companyInfo", GlobalVal.MENU_FUNCTION.DELETE)) {
			// 删除公司信息
			companyInfoService.delete(id, request);
			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		return result;
	}
	

}