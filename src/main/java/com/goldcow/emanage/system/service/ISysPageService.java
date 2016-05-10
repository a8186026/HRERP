package com.goldcow.emanage.system.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.goldcow.emanage.util.gen.entity.SysPageCtrl;

/**
 * 服务层接口
 * 
 * @author zheng
 * @version v1.0
 * @since 2015-05-26
 */
public interface ISysPageService {

	/**
	 * 查询注册页面
	 * 
	 * @param bean 查询条件
	 * @return 页面列表
	 */
	public List<SysPageCtrl> list(SysPageCtrl bean);

	/**
	 * 新增注册页面
	 * 
	 * @param bean
	 * @param request
	 */
	public SysPageCtrl add(SysPageCtrl bean, HttpServletRequest request);

	/**
	 * 删除注册页面
	 * 
	 * @param  ID
	 * @param request
	 */
	public void delete(Integer id, HttpServletRequest request);

	/**
	 * 根据ID得到实体
	 * 
	 * @param id ID
	 * @return bean SysPageCtrl
	 */
	public SysPageCtrl getById(Integer id);

	/**
	 * 更新注册页面
	 * 
	 * @param bean SysPageCtrl
	 * @param request
	 * @return
	 */
	
	public SysPageCtrl  update(SysPageCtrl bean, HttpServletRequest request);

}