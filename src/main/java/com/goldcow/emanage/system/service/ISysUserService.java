package com.goldcow.emanage.system.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.goldcow.emanage.util.gen.bean.LoginUser;
import com.goldcow.emanage.util.gen.entity.SysUser;
import com.goldcow.emanage.util.gen.entity.SysUserHabit;
import com.goldcow.emanage.util.gen.entity.valueObject.SysUser.SysUserVO;

/**
 * 服务层接口
 * 
 * @author wubin
 * @version v1.0
 * @since 2015-06-01
 */
public interface ISysUserService {

	/**
	 * 查询用户列表
	 * 
	 * @param bean 查询条件
	 * @return 用户列表
	 */
	public Map<String, Object> list(SysUser bean);

	/**
	 * 新增用户
	 * 
	 * @param bean 用户信息VO
	 * @param request HttpServletRequest
	 * @return 用户信息VO
	 */
	public SysUserVO add(SysUserVO bean, HttpServletRequest request);

	/**
	 * 修改用户
	 * 
	 * @param bean 用户信息
	 * @param request HttpServletRequest
	 * @return 用户信息
	 */
	public SysUserVO update(SysUserVO bean, HttpServletRequest request);

	/**
	 * 删除用户
	 * 
	 * @param id ID
	 * @param request HttpServletRequest
	 */
	public void delete(Integer id, HttpServletRequest request);

	/**
	 * 根据ID查询
	 * 
	 * @param id ID
	 * @return map
	 */
	public SysUserVO getById(Integer id);

	/**
	 * 启用帐号
	 * 
	 * @param id ID
	 * @param request HttpServletRequest
	 * @return 用户信息
	 */
	public SysUser enable(Integer id, HttpServletRequest request);

	/**
	 * 停用帐号
	 * 
	 * @param id ID
	 * @param request HttpServletRequest
	 * @return 用户信息
	 */
	public SysUser disable(Integer id, HttpServletRequest request);

	/**
	 * 用户登录
	 * 
	 * @param loginUser 用户名及密码信息
	 * @return 登录结果
	 */
	public Map<String, Object> login(LoginUser loginUser);

	
	/**
	 * 获取用户习惯
	 * 
	 * @param ID 用户ID
	 * @param page_id 页面ID
	 * @param ctrl_id datagrid的ID
	 * @return 用户列表的所有用户习惯
	 */
	public List<SysUserHabit> getHabits(Integer id,String page_id,String ctrl_id);
	
	/**
	 * 保存用户习惯
	 * 
	 * @param fields 用户习惯所有字段
	 * @param fieldNames 字段中文名
	 * @param widths 字段宽度
	 * @param page_id 页面ID
	 * @param ctrl_id datagrid的ID
	 * @param request 
	 * @return 用户列表的所有用户习惯
	 */
	public Integer saveHabits(String fields,String fieldNames,String widths,String page_id,String ctrl_id,HttpServletRequest request);
	
	/**
	 * 新增配置用户习惯—页面属性显示
	 * 
	 * @param bean 用户信息
	 * @param habit_field 字段名称
	 * @param habit_field_name 字段中文名称
	 * @param page_id 页面ID 
	 * @param ctrl_id datagrid的ID
	 * @param request 
	 * @return 新增配置用户习惯—页面属性信息
	 */
	public void addUserHabits(Integer id, String habit_field, String habit_field_name, String page_id, String ctrl_id, HttpServletRequest request);
	
	/**
	 * 根据组ID 以及 User bean 信息查询所有符合结果的返回 
	 * @param 包含groupID 的 SysUserVO bean
	 */
	public List<SysUserVO> getSysUserContainGroupID (SysUserVO bean);

	SysUser getByUserId(Integer userId);

	List<SysUser> lists(SysUser bean);



}