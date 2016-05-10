package com.goldcow.emanage.system.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.goldcow.emanage.util.gen.entity.SysParameter;
import com.goldcow.emanage.util.gen.entity.SysParameterSub;

/**
 * 服务层接口
 * 
 * @author chengrongchang
 * @version v1.0
 * @since 2014-10-14
 */
public interface ISysParameterService {

	/**
	 * 查询系统参数
	 * 
	 * @param bean 查询条件
	 * @return 查询结果
	 */
	public List<Map<String, Object>> list(SysParameter bean);

	/**
	 * 新增系统参数
	 * 
	 * @param bean 系统参数信息
	 * @param request HttpServletRequest
	 * @return 系统参数信息
	 */
	public SysParameter add(SysParameter bean, HttpServletRequest request);

	/**
	 * 修改系统参数
	 * 
	 * @param bean 系统参数信息
	 * @param request HttpServletRequest
	 * @return 系统参数信息
	 */
	public SysParameter update(SysParameter bean, HttpServletRequest request);

	/**
	 * 根据ID删除系统参数
	 * 
	 * @param id ID
	 * @param request HttpServletRequest
	 */
	public void delete(Integer id, HttpServletRequest request);

	/**
	 * 根据ID查询系统参数
	 * 
	 * @param id ID
	 * @return 查询结果
	 */
	public SysParameter getById(Integer id);

	/**
	 * 按类别查询
	 * 
	 * @param code 类别
	 * @return 查询结果
	 */
	public List<SysParameter> getByCode(String code);

	/**
	 * 根据ID查询系统参数子项
	 * 
	 * @param id ID
	 * @return 查询结果
	 */
	public SysParameterSub getSubById(Integer id);

	/**
	 * 新增系统参数子项
	 * 
	 * @param bean 系统参数信息
	 * @param request HttpServletRequest
	 * @return 系统参数子项信息
	 */
	public SysParameterSub addSub(SysParameterSub bean, HttpServletRequest request);

	/**
	 * 修改系统参数子项
	 * 
	 * @param bean 数据系统参数子项
	 * @param request HttpServletRequest
	 * @return 系统参数子项信息
	 */
	public SysParameterSub updateSub(SysParameterSub bean, HttpServletRequest request);

	/**
	 * 根据ID删除系统参数子项
	 * 
	 * @param id ID
	 * @param request HttpServletRequest
	 */
	public void deleteSub(Integer id, HttpServletRequest request);
	
	/**
	 * 查询系统参数表中的信息
	 * 
	 * @param param
	 * @return
	 */
	public Map<String, Object> getParamCode(String param);
	
	/**
	 * 根据系统参数主表的编码获得所有的子表信息
	 * 
	 * 荣斌 2014-11-13日添加
	 * 
	 * @param code
	 * @return
	 */
	public List<SysParameterSub> getByParamCode(String code);

}