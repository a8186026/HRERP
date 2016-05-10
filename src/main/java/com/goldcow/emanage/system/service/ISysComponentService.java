package com.goldcow.emanage.system.service;

import java.util.List;



import javax.servlet.http.HttpServletRequest;

import com.goldcow.emanage.util.gen.entity.SysComponentCtrl;

/**
 * 服务层接口
 * 
 * @author zheng
 * @version v1.0
 * @since 2015-05-26
 */
public interface ISysComponentService {

	/**
	 * 查询控件列表
	 * 
	 * @param bean 查询条件
	 * @return 控件列表
	 */
	public List<SysComponentCtrl> list(SysComponentCtrl bean);

	/**
	 * 新增控件
	 * 
	 * @param bean
	 * @param  request HttpServletRequest
	 */
	public SysComponentCtrl add(SysComponentCtrl bean, HttpServletRequest request);

	/**
	 * 删除单个控件
	 * 
	 * @param id ID
	 * @param  request HttpServletRequest
	 */
	public void delete(Integer id, HttpServletRequest request);

	/**
	 * 根据ID查询
	 * 
	 * @param id ID
	 * @return 控件信息
	 */
	public SysComponentCtrl getById(Integer id);

	/**
	 * 更新控件
	 * 
	 * @param bean SysComponentCtrl
	 * @param request HttpServletRequest
	 * @return
	 */
	
	public SysComponentCtrl  update(SysComponentCtrl bean, HttpServletRequest request);

}