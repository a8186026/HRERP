package com.goldcow.sframe.util.tools;

import java.util.List;
import java.util.Map;

import com.goldcow.sframe.util.mybatis.BaseDao;
import com.google.common.collect.Maps;
/**
 * Map<String, Object>格式 表单查询显示通用类
 * 
 * @author Yao Taihang
 * @version v1.0
 * @since 2014-12-19
 */
public class DataTools<T>{
	/**
	 * 根据参数的类型，将数据库查询的对象List封装为前台显示用的Map分页对象,自动填入count以及row键值对
	 * */
	public Map<String, Object> queryMap(T bean , BaseDao<T> dao){
		List<T> list = dao.list(bean);
		int count = dao.count(bean);
		// 封装分页对象
		Map<String, Object> result = Maps.newHashMap();
		result.put("total", count);					
		result.put("rows", list);
		return result;
	}
	
	
	/**
	 * 有条件查询返回该节点以及该节点的所有父节点-默认只有三级
	 * */
	public List<T> queryListForTree(T bean){
		return null;
	}
}
