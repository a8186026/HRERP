package com.goldcow.emanage.system.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.goldcow.emanage.util.gen.entity.valueObject.sysScreening.SysScreeningVO;

/**
 * 系统筛选管理
 * 
 * @author wubin
 * @version v1.0
 * @since 2015-11-3
 */
public interface ISysScreeningService {
	/**
	 * 处理前台传来的字段
	 * 
	 * @author wubin
	 * @param field 字段
	 * @param fieldName 字段名
	 * @param className 类名
	 * @return SysScreeningVO list
	 */
	public List<SysScreeningVO> deal(String fields, String fieldNames, String className);
	/**
	 * 数据筛选
	 * 
	 * @param data 数据
	 * @param className 类名
	 * @param sqlName 数据库名
	 * @param ids 过滤指定ids数组
	 * @return 实体bean list
	 */
	public List<Object> search(HttpServletRequest request,String data,String sqlName,String className,String ids);
}