package com.goldcow.emanage.system.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.goldcow.emanage.util.gen.entity.SysGroup;
import com.goldcow.emanage.util.gen.entity.SysUser;

/**
 * 服务层接口
 * 
 * @author chengrongchang
 * @version v1.0
 * @since 2014-10-14
 */
public interface ISysGroupService {

	/**
	 * 查询用户组列表
	 * 
	 * @param bean 查询条件
	 * @return 用户组列表
	 */
	public List<SysGroup> list(SysGroup bean);

	/**
	 * 新增用户组
	 * 
	 * @param bean 用户组信息
	 * @param request HttpServletRequest
	 * @return 用户组信息
	 */
	public SysGroup add(SysGroup bean, HttpServletRequest request);

	/**
	 * 修改用户组
	 * 
	 * @param bean 用户组信息
	 * @param request HttpServletRequest
	 * @return 用户组信息
	 */
	public SysGroup update(SysGroup bean, HttpServletRequest request);

	/**
	 * 删除用户组
	 * 
	 * @param group_id ID
	 * @param request HttpServletRequest
	 */
	public void delete(Integer id, HttpServletRequest request);

	/**
	 * 根据ID查询
	 * 
	 * @param id ID
	 * @return 用户组信息
	 */
	public SysGroup getById(Integer id);

	/**
	 * 设为默认用户组
	 * 
	 * @param id ID
	 * @param request HttpServletRequest
	 */
	void setDefault(Integer group_id, HttpServletRequest request);

	/**
	 * 删除用户组内用户组
	 * 
	 * @param id 用户组ID
	 * @param user_id 用户组ID
	 */
	public void deleteUser(Integer id, Integer user_id);

	/**
	 * 向用户组内添加用户组
	 * 
	 * @param id 用户组ID
	 * @param user_id 用户组ID
	 */
	public void addUser(Integer id, Integer user_id);

	/**
	 * 取得用户组内用户组列表
	 * 
	 * @param id 用户组ID
	 * @return 用户组列表
	 */
	public List<SysUser> getGroupUsers(Integer id);

	/**
	 * 取得可向用户组内添加的用户组列表
	 * 
	 * @param id 用户组ID
	 * @return 用户组列表
	 */
	public List<SysUser> getUsersForAdd(Integer id);
	
	/**
	 * 取得用户所在用户组
	 * 
	 * @param user_id 用户ID
	 * @return 用户组bean
	 */
	public SysGroup getUserGroup(Integer user_id);
	public SysGroup getUserGroup(HttpServletRequest request);

}