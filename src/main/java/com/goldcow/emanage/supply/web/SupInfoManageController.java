package com.goldcow.emanage.supply.web;

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

import com.goldcow.emanage.supply.service.ISupInfoManageService;
import com.goldcow.emanage.util.gen.SysTools;
import com.goldcow.emanage.util.gen.entity.SupInfoManage;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 供方客户信息管理
 * 
 * @author wangqi
 * @version v1.0
 * @since 2015-6-8
 */
@Controller
@RequestMapping(value = "/supply/supInfoManage")
public class SupInfoManageController {
	/** 供方客户信息信息管理服务 */
	@Autowired
	protected ISupInfoManageService supInfoManageService;
	
	/**
	 * 供方客户信息管理页面
	 * 
	 * @param request HttpServletRequest
	 * @return 供方客户信息页面路径
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String view(HttpServletRequest request) {
		if (SysUtil.hasRight(request, "supInfoManage", GlobalVal.MENU_FUNCTION.VIEW)) {
			return "supply/supInfoManage/supInfoManageList.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}

	/**
	 * 查询供方客户信息信息
	 * 
	 * @param request HttpServletRequest
	 * @param bean 查询条件
	 * @return 操作结果
	 */
	@RequestMapping(value = "lists", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> list(HttpServletRequest request, SupInfoManage bean) {
		if (SysUtil.hasRight(request, "supInfoManage", GlobalVal.MENU_FUNCTION.VIEW)) {	
			return supInfoManageService.list(bean);
		} else {
			return Maps.newHashMap();
		}
	}
	
	/**
	 * 查询供方客户信息信息
	 * 
	 * @param request HttpServletRequest
	 * @param bean 查询条件
	 * @return 操作结果List
	 */
	@RequestMapping(value = "getList", method = RequestMethod.GET)
	@ResponseBody
	public List<SupInfoManage> getList(HttpServletRequest request, SupInfoManage bean) {
		if (SysUtil.hasRight(request, "supInfoManage", GlobalVal.MENU_FUNCTION.VIEW)) {	
			return supInfoManageService.lists(bean);
		} else {
			return Lists.newArrayList();
		}
	}
	
	/**
	 * 新增供方客户信息
	 *  
	 * @param request HttpServletRequest
	 * @param model Model
	 * @return 新增供方客户信息页面路径
	 */
	@RequestMapping(value = "new", method = RequestMethod.GET)
	public String newMenu(HttpServletRequest request, Model model) {
		if (SysUtil.hasRight(request, "supInfoManage", GlobalVal.MENU_FUNCTION.VIEW)) {			
			SupInfoManage supInfoManage = new SupInfoManage(); 
			model.addAttribute("supInfoManage", supInfoManage);
			model.addAttribute("method", "POST");
			model.addAttribute("formUrl", "/supply/supInfoManage/new");
			SysUtil.getToken(request);
			return "supply/supInfoManage/supInfoManageEdit.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}
	
	/**
	 * 经营范围选择页面
	 *  
	 * @param request HttpServletRequest
	 * @param model Model
	 * @return 新增供方客户信息页面路径
	 */
	@RequestMapping(value = "scope", method = RequestMethod.GET)
	public String newScope(HttpServletRequest request, String callback,Model model,String value) {
		if (SysUtil.hasRight(request, "supInfoManage", GlobalVal.MENU_FUNCTION.VIEW)) {			
			model.addAttribute("callback", callback);
			model.addAttribute("value", SysTools.decode(value));
			return "supply/supInfoManage/manageScopeEdit.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}
	/**
	 * 地区药监ID选择页面
	 *  
	 * @param request HttpServletRequest
	 * @param model Model
	 * @return 新增供方客户信息页面路径
	 */
	@RequestMapping(value = "localDrugChoose", method = RequestMethod.GET)
	public String newLocalDrug(HttpServletRequest request, String callback,Model model,String value) {
		if (SysUtil.hasRight(request, "supInfoManage", GlobalVal.MENU_FUNCTION.VIEW)) {			
			model.addAttribute("callback", callback);
			model.addAttribute("value", SysTools.decode(value));
			return "supply/supInfoManage/localDrugEdit.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}
	
	/**
	 * 新增供方客户信息
	 * 
	 * @param request HttpServletRequest
	 * @param bean 供方客户信息
	 * @return 操作结果
	 */
	@RequestMapping(value = "new", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> add(HttpServletRequest request, SupInfoManage bean) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "supInfoManage", GlobalVal.MENU_FUNCTION.UPDATE)) {			
			// 新增供方客户信息				
			supInfoManageService.add(bean, request);
			result.put("result", "success");
			result.put("message", "操作成功！");
			
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		
		return result;
	}
		
		 
	
	/**
	 * 查看供方客户信息页面
	 * 
	 * @param request HttpServletRequest
	 * @param id 供方客户信息ID
	 * @param model Model
	 * @return 查看供方客户信息页面路径
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public String viewDetail(HttpServletRequest request, @PathVariable("id") Integer id, Model model) {
		if (SysUtil.hasRight(request, "supInfoManage", GlobalVal.MENU_FUNCTION.VIEW)) {
			SupInfoManage supInfoManage = supInfoManageService.getById(id);
			model.addAttribute("supInfoManage", supInfoManage);
			model.addAttribute("method", "PUT");
			model.addAttribute("formUrl", "/supply/supInfoManage/" + id);
			return "supply/supInfoManage/supInfoManageEdit.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}



	/**
	 * 修改供方客户信息
	 * 
	 * @param request HttpServletRequest
	 * @param id 供方客户信息ID
	 * @param bean 供方客户信息
	 * @return 操作结果
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Map<String, Object> update(HttpServletRequest request, @PathVariable("id") Integer id, SupInfoManage bean) {
		Map<String, Object> result = Maps.newHashMap();
		request.getParameterMap();
		if (SysUtil.hasRight(request, "supInfoManage", GlobalVal.MENU_FUNCTION.UPDATE)) {
			// 修改厂家档案信息
			supInfoManageService.update(bean, request);

			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}

		return result;
	}

	/**
	 * 删除供方客户信息信息
	 * 
	 * @param request HttpServletRequest
	 * @param id 被删除的供方客户信息信息ID
	 * @return 操作结果
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request, @PathVariable("id") Integer id) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "supInfoManage", GlobalVal.MENU_FUNCTION.DELETE)) {
			// 删除供方客户信息
			supInfoManageService.delete(id, request);
			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		return result;
	}
}