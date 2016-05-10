package com.goldcow.emanage.log.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goldcow.emanage.system.service.ISysPermissionService;
import com.goldcow.emanage.util.gen.entity.SysGroupMenuLog;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Maps;

/**
 * 日志记录管理
 * 
 * @author RIVERYAO
 * @version v1.0
 * @since 2015-6-30
 */
@Controller
@RequestMapping(value = "/log/logManage")
public class LogManageController {
	/** 用户组菜单权限管理服务 */
	@Autowired
	protected ISysPermissionService sysPermissionService;

	/**
	 * 菜单权限审核记录
	 * 
	 * @param request HttpServletRequest
	 * @return 菜单权限审核记录页面路径
	 */
	@RequestMapping(value = "menuPermissionCheckLog", method = RequestMethod.GET)
	public String view(HttpServletRequest request) {
		if (SysUtil.hasRight(request, "menuPermissionCheckLog", GlobalVal.MENU_FUNCTION.VIEW)) {
			return "log/logMenuPermissionCheck/logMenuPermissionCheckList.jsp";
		} else {
			return GlobalVal.STATIC_MENU.NO_RIGHT_PAGE;
		}
	}
	
	/**
	 * 查询菜单权限审核记录
	 * 
	 * @param request HttpServletRequest
	 * @param bean 查询条件
	 * @return 查询结果
	 */
	@RequestMapping(value = "menuPermissionCheckLogList", method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> list(HttpServletRequest request, SysGroupMenuLog bean) {
		if (SysUtil.hasRight(request, "sysPermission", GlobalVal.MENU_FUNCTION.VIEW)) {
			return sysPermissionService.getSysGroupMenuLogList(bean);
		} else {
			return Maps.newHashMap(); 
		}
	}

	
}