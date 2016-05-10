package com.goldcow.emanage.system.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.goldcow.emanage.util.gen.entity.SysGroupMenu;
import com.goldcow.emanage.util.gen.entity.SysGroupMenuLog;
import com.goldcow.emanage.util.gen.entity.SysMenu;
import com.goldcow.emanage.util.gen.entity.SysPermission;

/**
 * 服务层接口
 * 
 * @author Yaotaihang
 * @version v1.0
 * @since 2015-06-26iyhbnug
 */
public interface ISysPermissionService {

	/**
	 * 查询权限列表
	 * 
	 * @param bean 查询条件
	 * @return 权限列表
	 */
	public List<SysPermission> list(SysPermission bean);

	/**
	 * 新增权限
	 * 
	 * @param id 用户组ID
	 * @param permissions 菜单字符串
	 * @param oldPermissions 旧的菜单字符串
	 * @param functions 功能字符串
	 */
	public void add(Integer id, String permissions, String oldPermissions);

	/**
	 * 删除权限
	 * 
	 * @param group_id ID
	 */
	public void delete(Integer group_id);

	/**
	 * 根据ID查询
	 * 
	 * @param id ID
	 * @return 权限信息
	 */
	public SysPermission getById(Integer id);

	/**
	 * 查询用户级、菜单及功能
	 * 
	 * @param id 用户组ID
	 * @return
	 */
	public List<SysGroupMenu> getMenuFuncs(Integer id);
	
	/**
	 * 查询用户组菜单权限
	 * 
	 * @param sgm_Enable 用户组ID
	 * @return
	 */
	public List<SysMenu> getGroupMenuForEnable(Integer group_id);
	public List<SysMenu> getGroupMenu(Integer id);
	/**
	 * 查询用户组菜单权限
	 * 
	 * @param 查询条件
	 * @return
	 */
	public List<SysGroupMenu> getGroupMenuList(SysGroupMenu bean);
	/**
	 * 查询菜单权限审核
	 * 
	 * @param group_menu_id 用户菜单ID
	 * @param sgm_status 当前状态
	 * @param checkContent 审核结果
	 * @return 查询结果
	 */
	public void permissionCheck(String group_menu_id, String sgm_status, String checkContent, HttpServletRequest request);
	/**
	 * 添加用户组权限记录
	 * 
	 * @param id ID
	 * @return 权限信息
	 */
	public SysGroupMenuLog addMenusLog(SysGroupMenuLog bean, HttpServletRequest request);
	/**
	 * 查询菜单权限审核记录
	 * 
	 * @param request HttpServletRequest
	 * @param bean 查询条件
	 * @return 查询结果
	 */
	public Map<String, Object> getSysGroupMenuLogList(SysGroupMenuLog bean);
	

}