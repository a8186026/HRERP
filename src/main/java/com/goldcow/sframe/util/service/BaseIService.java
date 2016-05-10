package com.goldcow.sframe.util.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.goldcow.emanage.util.gen.entity.PurAcceptCheck;
/**
 * 通用服务层接口
 * 
 * @author Yao Taihang
 * @version v1.0
 * @since 2014-12-19
 */
public interface BaseIService<T> {
	/**
	 * 新增信息记录
	 * 
	 * @param bean 信息记录对象
	 * @return 信息记录
	 */
	public T add(T bean, HttpServletRequest request);
	
	/**
	 * 删除信息记录
	 * 
	 * @param id ID
	 * @param request HttpServletRequest
	 */
	public void delete(Integer id, HttpServletRequest request);
	
	/**
	 * 
	 * @param bean 信息记录
	 * @param request HttpServletRequest
	 * @return 信息记录
	 */
	public T update(T bean, HttpServletRequest request);
	
	/**
	 * 根据ID查询
	 * 
	 * @param id ID
	 * @return 信息记录
	 */
	public T getById(Integer id);

}
