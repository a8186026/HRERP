package com.goldcow.emanage.system.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.goldcow.emanage.util.gen.entity.SysDictionary;
import com.goldcow.emanage.util.gen.entity.SysDictionarySub;

/**
 * 服务层接口
 * 
 * @author chengrongchang
 * @version v1.0
 * @since 2014-10-16
 */
public interface ISysDictionaryService {

	/**
	 * 查询数据字典列表
	 * 
	 * @param bean 查询条件
	 * @return 数据字典列表
	 */
	public List<Map<String, Object>> list(SysDictionary bean);

	/**
	 * 新增数据字典
	 * 
	 * @param bean 数据字典信息
	 * @param request HttpServletRequest
	 * @return 数据字典信息
	 */
	public SysDictionary add(SysDictionary bean, HttpServletRequest request);

	/**
	 * 修改数据字典
	 * 
	 * @param bean 数据字典信息
	 * @param request HttpServletRequest
	 * @return 数据字典信息
	 */
	public SysDictionary update(SysDictionary bean, HttpServletRequest request);

	/**
	 * 删除数据字典
	 * 
	 * @param user_id ID
	 * @param request HttpServletRequest
	 */
	public void delete(Integer id, HttpServletRequest request);

	/**
	 * 根据ID查询数据字典
	 * 
	 * @param id 数据字典ID
	 * @return 数据字典信息
	 */
	public SysDictionary getById(Integer id);

	/**
	 * 根据编码查询
	 * 
	 * @param code 类别
	 * @return 数制字典子项列表
	 */
	public List<SysDictionarySub> getByCode(String code);

	/**
	 * 根据ID查询数据字典子项
	 * 
	 * @param id 数据字典子项ID
	 * @return 数据字典子项信息
	 */
	public SysDictionarySub getSubById(Integer id);

	/**
	 * 添加数据字典子项
	 * 
	 * @param bean 数据字典子项信息
	 * @param request HttpServletRequest
	 * @return 数据字典子项信息
	 */
	public SysDictionarySub addSub(SysDictionarySub bean, HttpServletRequest request);

	/**
	 * 修改数据字典子项信息
	 * 
	 * @param bean 数据字典子项信息
	 * @param request HttpServletRequest
	 * @return 数据字典子项信息
	 */
	public SysDictionarySub updateSub(SysDictionarySub bean, HttpServletRequest request);

	/**
	 * 删除字典子项信息
	 * 
	 * @param id 字典子项ID
	 * @param request HttpServletRequest
	 */
	public void deleteSub(Integer id, HttpServletRequest request);

	/**
	 * 根据编码和值查询
	 * 
	 * @param code 类别
	 * @return 数制字典子项
	 */
	public SysDictionarySub getByCodeAndValue(String code, String value);

}