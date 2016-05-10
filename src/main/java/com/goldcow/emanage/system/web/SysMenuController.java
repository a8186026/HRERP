package com.goldcow.emanage.system.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goldcow.emanage.system.service.ISysMenuService;
import com.goldcow.emanage.util.gen.entity.SysMenu;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 菜单管理
 * 
 * @author chengrongchang
 * @version v1.0
 * @since 2014-10-16
 */
@Controller
@RequestMapping(value = "/system/menu")
public class SysMenuController {
	/** 菜单管理服务 */
	@Autowired
	protected ISysMenuService service;

	/**
	 * 菜单管理页面
	 * 
	 * @param request HttpServletRequest
	 * @return 菜单管理页面路径
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String view(HttpServletRequest request) {
		if (SysUtil.hasRight(request, "sysMenu", GlobalVal.MENU_FUNCTION.VIEW)) {
			return "system/sysMenu/sysMenuList.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}

	/**
	 * 查询菜单
	 * 
	 * @param request HttpServletRequest
	 * @param bean 查询条件
	 * @return 操作结果
	 */
	@RequestMapping(value = "menus", method = RequestMethod.GET)
	@ResponseBody
	public List<SysMenu> list(HttpServletRequest request, SysMenu bean) {
		if (SysUtil.hasRight(request, "sysMenu", GlobalVal.MENU_FUNCTION.VIEW)) {
			return service.list(bean);
		} else {
			return Lists.newArrayList();
		}
	}

	/**
	 * 新增菜单页面
	 * 
	 * @param request HttpServletRequest
	 * @param parent_menu 上级菜单ID
	 * @param model Model
	 * @return 新增菜单页面路径
	 */
	@RequestMapping(value = "new", method = RequestMethod.GET)
	public String newMenu(HttpServletRequest request, String parent_menu, Model model) {
		if (SysUtil.hasRight(request, "sysMenu", GlobalVal.MENU_FUNCTION.VIEW)) {
			SysMenu sysMenu = new SysMenu();
			sysMenu.setParent_menu(StringUtils.isEmpty(parent_menu) ? null : Integer.valueOf(parent_menu));
			model.addAttribute("sysMenu", sysMenu);
			model.addAttribute("method", "POST");
			model.addAttribute("formUrl", "/system/menu/new");
			SysUtil.getToken(request);
			return "system/sysMenu/sysMenuEdit.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}

	/**
	 * 查看菜单信息页面
	 * 
	 * @param request HttpServletRequest
	 * @param id 菜单ID
	 * @param model Model
	 * @return 查看菜单信息页面路径
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public String viewDetail(HttpServletRequest request, @PathVariable("id") Integer id, Model model) {
		if (SysUtil.hasRight(request, "sysMenu", GlobalVal.MENU_FUNCTION.VIEW)) {
			SysMenu sysMenu = service.getById(id);
			model.addAttribute("sysMenu", sysMenu);
			model.addAttribute("method", "PUT");
			model.addAttribute("formUrl", "/system/menu/" + id);
			SysUtil.getToken(request);
			return "system/sysMenu/sysMenuEdit.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}

	/**
	 * 新增菜单
	 * 
	 * @param request HttpServletRequest
	 * @param bean 菜单信息
	 * @return 操作结果
	 */
	@RequestMapping(value = "new", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> add(HttpServletRequest request, SysMenu bean) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "sysMenu", GlobalVal.MENU_FUNCTION.UPDATE)) {
			if (SysUtil.checkToken(request)) {
				// 新增菜单
				service.add(bean, request);

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
	 * 修改菜单
	 * 
	 * @param request HttpServletRequest
	 * @param id 菜单ID
	 * @param bean 菜单信息
	 * @return 操作结果
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Map<String, Object> update(HttpServletRequest request, @PathVariable("id") Integer id, SysMenu bean) {
		Map<String, Object> result = Maps.newHashMap();
		request.getParameterMap();
		if (SysUtil.hasRight(request, "sysMenu", GlobalVal.MENU_FUNCTION.UPDATE)) {
			if (SysUtil.checkToken(request)) {
				// 修改菜单
				service.update(bean, request);

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
	 * 启用菜单
	 * 
	 * @param request HttpServletRequest
	 * @param id 菜单ID
	 * @return 操作结果
	 */
	@RequestMapping(value = "{id}/enable", method = RequestMethod.PUT)
	@ResponseBody
	public Map<String, Object> enable(HttpServletRequest request, @PathVariable("id") Integer id) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "sysMenu", GlobalVal.MENU_FUNCTION.UPDATE)) {
			// 删除菜单
			service.enable(id, request);
			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}

		return result;
	}

	/**
	 * 停用菜单
	 * 
	 * @param request HttpServletRequest
	 * @param id 菜单ID
	 * @return 操作结果
	 */
	@RequestMapping(value = "{id}/disable", method = RequestMethod.PUT)
	@ResponseBody
	public Map<String, Object> disable(HttpServletRequest request, @PathVariable("id") Integer id) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "sysMenu", GlobalVal.MENU_FUNCTION.UPDATE)) {
			// 删除菜单
			service.disable(id, request);
			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}

		return result;
	}

	/**
	 * 删除菜单
	 * 
	 * @param request HttpServletRequest
	 * @param id 菜单ID
	 * @return 操作结果
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request, @PathVariable("id") Integer id) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "sysMenu", GlobalVal.MENU_FUNCTION.DELETE)) {
			// 删除菜单
			service.delete(id, request);
			result.put("result", "success");
			result.put("message", "操作成功！");
		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}

		return result;
	}
	
	
	
	/**
	 * 查询菜单信息
	 * 
	 * @param request HttpServletRequest
	 * @param id 菜单ID
	 * @return 查看菜单信息页面路径
	 */
	@RequestMapping(value = "check", method = RequestMethod.GET)
	@ResponseBody
	public SysMenu check(HttpServletRequest request, Integer id) {
		if (SysUtil.hasRight(request, "sysMenu", GlobalVal.MENU_FUNCTION.VIEW)) {
			SysUtil.getToken(request);
			return service.getById(id);
		} else {
			return new SysMenu();
		}
	}
}