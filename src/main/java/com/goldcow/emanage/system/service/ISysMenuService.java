package com.goldcow.emanage.system.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.goldcow.emanage.util.gen.entity.SysMenu;

/**
 * 服务层接口
 * 
 * @author chengrongchang
 * @version v1.0
 * @since 2014-10-14
 */
public interface ISysMenuService {

	/**
	 * 查询菜单列表
	 * 
	 * @param bean 查询条件
	 * @return 菜单列表
	 */
	public List<SysMenu> list(SysMenu bean);

	/**
	 * 新增菜单
	 * 
	 * @param bean 菜单信息
	 * @param request HttpServletRequest
	 * @return 菜单信息
	 */
	public SysMenu add(SysMenu bean, HttpServletRequest request);

	/**
	 * 修改菜单
	 * 
	 * @param bean 菜单信息
	 * @param request HttpServletRequest
	 * @return 菜单信息
	 */
	public SysMenu update(SysMenu bean, HttpServletRequest request);

	/**
	 * 删除菜单
	 * 
	 * @param user_id ID
	 * @param request HttpServletRequest
	 */
	public void delete(Integer id, HttpServletRequest request);

	/**
	 * 根据ID查询
	 * 
	 * @param id ID
	 * @return 菜单信息
	 */
	public SysMenu getById(Integer id);

	/**
	 * 启用菜单
	 * 
	 * @param id ID
	 * @param request HttpServletRequest
	 * @return 菜单信息
	 */
	public SysMenu enable(Integer id, HttpServletRequest request);

	/**
	 * 停用菜单
	 * 
	 * @param id ID
	 * @param request HttpServletRequest
	 * @return 菜单信息
	 */
	public SysMenu disable(Integer id, HttpServletRequest request);
	public List<SysMenu> getNoPageGroupMenu(Integer id);
}