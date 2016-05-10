package com.goldcow.emanage.system.web;

import java.util.ArrayList;
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

import com.goldcow.emanage.system.service.ISysGroupService;
import com.goldcow.emanage.system.service.ISysMenuService;
import com.goldcow.emanage.system.service.ISysPermissionService;
import com.goldcow.emanage.util.gen.SysTools;
import com.goldcow.emanage.util.gen.entity.SysGroupMenu;
import com.goldcow.emanage.util.gen.entity.SysMenu;
import com.goldcow.emanage.util.gen.entity.SysPermission;
import com.goldcow.emanage.util.gen.entity.SysUser;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 用户组菜单权限管理
 * 
 * @author wubin
 * @version v1.1
 * @since 2015-05-22
 */
@Controller
@RequestMapping(value = "/system/permission")
public class SysPermissionController {
	public static Integer SGM_STATUS_ENABLE= 0; 
	public static Integer SGM_STATUS_DISABLE= 1; 
	public static Integer SGM_STATUS_YTON_CHECK= 2; 
	public static Integer SGM_STATUS_NTOY_CHECK= 3; 
	
	/** 用户组菜单权限管理服务 */
	@Autowired
	protected ISysPermissionService sysPermissionService;

	@Autowired
	protected ISysGroupService sysGroupService;
	@Autowired
	protected ISysMenuService sysMenuService;
	/**
	 * 用户组菜单权限管理页面
	 * 
	 * @param request HttpServletRequest
	 * @return 用户组菜单权限管理页面路径
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String view(HttpServletRequest request) {
		if (SysUtil.hasRight(request, "sysPermission", GlobalVal.MENU_FUNCTION.VIEW)) {
			return "system/sysPermission/sysPermissionList.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}

	/**
	 * 查询用户组菜单权限
	 * 
	 * @param request HttpServletRequest
	 * @param bean 查询条件
	 * @return 查询结果
	 */
	@RequestMapping(value = "groups", method = RequestMethod.GET)
	@ResponseBody
	public List<SysPermission> list(HttpServletRequest request, SysPermission bean) {
		if (SysUtil.hasRight(request, "sysPermission", GlobalVal.MENU_FUNCTION.VIEW)) {
			return sysPermissionService.list(bean);
		} else {
			return Lists.newArrayList();
		}
	}
	/**
	 * 查询用户组菜单权限
	 * 
	 * @param request HttpServletRequest
	 * @param group_id 组ID
	 * @return
	 */
	@RequestMapping(value = "getGroupMenu", method = RequestMethod.GET)
	@ResponseBody
	public List<SysMenu> getGroupMenu(HttpServletRequest request, Integer group_id) {
		if (SysUtil.hasRight(request, "sysPermission", GlobalVal.MENU_FUNCTION.VIEW)) {
			return sysPermissionService.getGroupMenuForEnable(group_id);
		} else {
			return Lists.newArrayList();
		}
	}
	/**
	 * 查询用户组菜单权限
	 * 
	 * @param request HttpServletRequest
	 * @param group_id 组ID
	 * @return
	 */
	@RequestMapping(value = "getNoPageGroupMenu", method = RequestMethod.GET)
	@ResponseBody
	public List<SysMenu> getNoPageGroupMenu(HttpServletRequest request, Integer group_id) {
		if (SysUtil.hasRight(request, "sysPermission", GlobalVal.MENU_FUNCTION.VIEW)) {
			return sysMenuService.getNoPageGroupMenu(group_id);
		} else {
			return Lists.newArrayList();
		}
	}

	/**
	 * 新增用户组菜单权限
	 * 
	 * @param request HttpServletRequest
	 * @param model Model
	 * @return 页面路径
	 */
	@RequestMapping(value = "new", method = RequestMethod.GET)
	public String newPermission(HttpServletRequest request, Model model) {
		if (SysUtil.hasRight(request, "sysPermission", GlobalVal.MENU_FUNCTION.VIEW)) {
			model.addAttribute("method", "POST");
			model.addAttribute("formUrl", "/system/permission/new");
			SysUtil.getToken(request);
			return "system/sysPermission/sysPermissionEdit.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}

	/**
	 * 查看用户组菜单权限信息页面
	 * 
	 * @param request HttpServletRequest
	 * @param id ID
	 * @param model Model
	 * @return
	 */
	@RequestMapping(value = "getUsersByGroup", method = RequestMethod.GET)
	@ResponseBody
	public List<SysUser> viewDetail(HttpServletRequest request, Integer group_id, Model model) {
		if (SysUtil.hasRight(request, "sysPermission", GlobalVal.MENU_FUNCTION.VIEW)) {
			return sysGroupService.getGroupUsers(group_id);	
		} else {
			return Lists.newArrayList();
		}
	}

	/**
	 * 新增用户组菜单权限
	 * 
	 * @param request HttpServletRequest
	 * @param group_id 用户组ID
	 * @param permissions 权限ID字符串
	 * @return 操作结果
	 */
	@RequestMapping(value = "saveGroupMenu", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> setPermission(HttpServletRequest request, Integer group_id, String permissions , String oldPermissions) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "sysPermission", GlobalVal.MENU_FUNCTION.UPDATE)) {
			// 新增用户组菜单权限
			sysPermissionService.add(group_id, permissions, oldPermissions);

			result.put("result", "success");
			result.put("message", "操作成功！");

		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		return result;
	}

	/**
	 * 设置菜单功能
	 * 
	 * @param request HttpServletRequest
	 * @param id 菜单ID
	 * @param value 功能值
	 * @param model Model
	 * @return 菜单功能设置页面
	 */
	@RequestMapping(value = "function/{id}/{value}", method = RequestMethod.GET)
	public String setFunction(HttpServletRequest request, @PathVariable("id") Integer id, @PathVariable("value") Integer value, Model model) {
		if (SysUtil.hasRight(request, "sysPermission", GlobalVal.MENU_FUNCTION.UPDATE)) {
			model.addAttribute("value", value);
			model.addAttribute("id", id);
			return "system/sysPermission/selectFuncion.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}
	
	/**
	 * 菜单权限审核页面
	 * 
	 * @param request HttpServletRequest
	 * @return菜单权限审核页面页面路径
	 */
	@RequestMapping(value = "permissionCheckView", method = RequestMethod.GET)
	public String permissionCheck_View(HttpServletRequest request) {
		if (SysUtil.hasRight(request, "sysPermission", GlobalVal.MENU_FUNCTION.VIEW)) {
			return "system/sysPermission/sysPermissionCheckList.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}
	
	/**
	 * 查询菜单权限审核
	 * 
	 * @param request HttpServletRequest
	 * @param bean 查询条件
	 * @return 查询结果
	 */
	@RequestMapping(value = "permissionCheckList", method = RequestMethod.GET)
	@ResponseBody
	public List<SysGroupMenu> permissionCheck_List(HttpServletRequest request, SysGroupMenu bean) {
		if (SysUtil.hasRight(request, "sysPermission", GlobalVal.MENU_FUNCTION.VIEW)) {
			//查询菜单权限审核列表
			bean.setMenu_name(SysUtil.getSqlLikeParam(SysTools.decode(bean.getMenu_name())));	//模糊
			//查询Y To N的所有待审条目
			bean.setSgm_status(SGM_STATUS_YTON_CHECK);
			List<SysGroupMenu> YTON = sysPermissionService.getGroupMenuList(bean);
			//查询N To Y的所有待审条目
			bean.setSgm_status(SGM_STATUS_NTOY_CHECK);
			List<SysGroupMenu> NTOY = sysPermissionService.getGroupMenuList(bean);
			//合并所有查询条目
			List<SysGroupMenu> allCheckList = new ArrayList<SysGroupMenu>();
			allCheckList.addAll(YTON);
			allCheckList.addAll(NTOY);
			return allCheckList;
		} else {
			return Lists.newArrayList();
		}
	}
	
	/**
	 * 查询菜单权限审核
	 * 
	 * @param group_menu_id 用户菜单ID
	 * @param sgm_status 当前状态
	 * @param checkContent 审核结果
	 * @return 查询结果
	 */
	@RequestMapping(value = "permissionCheckCheck", method = RequestMethod.PUT)
	@ResponseBody
	public Map<String, Object> permissionCheck_Check(HttpServletRequest request, String group_menu_id, String sgm_status,String checkContent) {
		Map<String, Object> result = Maps.newHashMap();
		if (SysUtil.hasRight(request, "sysPermission", GlobalVal.MENU_FUNCTION.UPDATE)) {
			// 新增用户组菜单权限
			sysPermissionService.permissionCheck(group_menu_id, sgm_status,checkContent,request);
			result.put("result", "success");
			result.put("message", "操作成功！");

		} else {
			result.put("result", "failure");
			result.put("message", "没有权限!");
		}
		return result;
	}
}