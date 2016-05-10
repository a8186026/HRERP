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

import com.goldcow.emanage.basInfo.service.IBasAccountService;
import com.goldcow.emanage.util.gen.entity.BasAccount;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Maps;

/**
 * 财务信息
 * 
 * @author wangqi
 * @version v1.0
 * @since 2015-7-1
 */
@Controller
@RequestMapping(value = "/basInfo/basAccount")
public class BasAccountController {
	/** 财务信息管理服务 */
	@Autowired
	protected IBasAccountService basAccountService;
	
	/**
	 * 财务信息管理页面
	 * 
	 * @param request HttpServletRequest
	 * @return 财务信息页面路径
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String view(HttpServletRequest request) {
		if (SysUtil.hasRight(request, "basAccount", GlobalVal.MENU_FUNCTION.VIEW)) {
			return "basInfo/basAccount/basAccountList.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}

	/**
	 * 查询财务信息
	 * 
	 * @param request HttpServletRequest
	 * @param bean 查询条件
	 * @return 操作结果
	 */
	@RequestMapping(value = "lists", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> list(HttpServletRequest request, BasAccount bean,Integer acc_type_id,String acc_type) {
		if (SysUtil.hasRight(request, "basAccount", GlobalVal.MENU_FUNCTION.VIEW)) {	
			bean.setAcc_type(acc_type);
			bean.setAcc_type_id(acc_type_id);
			return basAccountService.lists(bean);
		} else {
			return Maps.newHashMap();
		}
	}
	
	/**
	 * 新增财务信息
	 *  
	 * @param request HttpServletRequest
	 * @param model Model
	 * @return 新增财务信息页面路径
	 */
	@RequestMapping(value = "new", method = RequestMethod.GET)
	public String newMenu(HttpServletRequest request, Model model,Integer acc_type_id,String acc_type) {
		if (SysUtil.hasRight(request, "basAccount", GlobalVal.MENU_FUNCTION.VIEW)) {			
			BasAccount basAccount = new BasAccount(); 
			basAccount.setAcc_type_id(acc_type_id);
			basAccount.setAcc_type(acc_type);
			model.addAttribute("basAccount", basAccount);
			model.addAttribute("method", "POST");
			model.addAttribute("formUrl", "/basInfo/basAccount/new");
			return "basInfo/basAccount/basAccountEdit.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}
	
	/**
	 * 新增财务信息
	 * 
	 * @param request HttpServletRequest
	 * @param bean 财务信息
	 * @return 操作结果
	 */
	@RequestMapping(value = "new", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> add(HttpServletRequest request, BasAccount bean) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "basAccount", GlobalVal.MENU_FUNCTION.UPDATE)) {
			// 新增财务信息
			basAccountService.add(bean, request);
			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		
		return result;
	}
		
		 
	
	/**
	 * 查看财务信息页面
	 * 
	 * @param request HttpServletRequest
	 * @param id 财务信息ID
	 * @param model Model
	 * @return 查看财务信息页面路径
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public String viewDetail(HttpServletRequest request, @PathVariable("id") Integer id, Model model) {
		if (SysUtil.hasRight(request, "basAccount", GlobalVal.MENU_FUNCTION.VIEW)) {
			
			BasAccount basAccount = basAccountService.getById(id);
			model.addAttribute("basAccount", basAccount);
			model.addAttribute("method", "PUT");
			model.addAttribute("formUrl", "/basInfo/basAccount/" + id);
			return "basInfo/basAccount/basAccountEdit.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}



	/**
	 * 修改财务信息
	 * 
	 * @param request HttpServletRequest
	 * @param id 财务信息ID
	 * @param bean 财务信息
	 * @return 操作结果
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Map<String, Object> update(HttpServletRequest request, @PathVariable("id") Integer id, BasAccount bean) {
		Map<String, Object> result = Maps.newHashMap();
		request.getParameterMap();
		if (SysUtil.hasRight(request, "basAccount", GlobalVal.MENU_FUNCTION.UPDATE)) {
				// 修改财务信息
			basAccountService.update(bean, request);

			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}

		return result;
	}

	/**
	 * 删除财务信息
	 * 
	 * @param request HttpServletRequest
	 * @param id 被删除的财务信息ID
 	 */
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request, @PathVariable("id") Integer id) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "basAccount", GlobalVal.MENU_FUNCTION.DELETE)) {
			// 删除财务信息
			basAccountService.delete(id, request);
			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		return result;
	}
	

}