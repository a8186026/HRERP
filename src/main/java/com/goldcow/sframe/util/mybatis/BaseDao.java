package com.goldcow.sframe.util.mybatis;

import java.util.List;
import java.util.Map;

@MyBatisRepository
public interface BaseDao<T> {
	
	/** 根据ID获取实体 */
	public T get(Integer id);

	/** 分页查询DataGrid */
	public List<T> list(T parameters);
	
	/** 分页查询TreeGrid */
	public List<Map<String, Object>> maplist(T parameters);

	/** 保存实体，并将主键值反写回实体，返回受影响的记录数 */
	public int add(T t);

	/** 根据ID删除表中数据 */
	public void delete(Integer id);

	/** 更新实体，返回受影响的记录数 */
	public int update(T t);
	
	/** 得到数据总数*/
	public int count(T bean);
	
	
}
