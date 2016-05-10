package com.goldcow.emanage.promotion.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.goldcow.emanage.util.gen.entity.PmnDepartmentInfo;
import com.goldcow.sframe.util.service.BaseIService;
/**
 * 部门档案信息
 * 
 * @author wangqi
 * @version v1.0
 * @since 2015-6-3
 */
public interface IPmnDepartmentInfoService extends BaseIService<PmnDepartmentInfo>{

	/**
	 * 查询部门档案信息-分页
	 * @param bean 查询条件
	 * @return 部门档案信息列表
	 */
	public Map<String, Object> list(PmnDepartmentInfo bean);
	/**
	 * 查询档案信息下-分页
	 * @param bean 查询条件
	 * @return 部门档案信息列表
	 */
	public Map<String, Object> uplist(PmnDepartmentInfo bean);
	/**
	 * 验证部门编号
	 * 
	 * @param department_number 被删除的部门档案信息信息ID
	 * @return 验证结果
	 * 
	 * @author RiverYao
	 * @since 2015-06-10
	 */
	public Boolean checkDepartmentNumber(String department_number);
	
	/**
	 * 返回数组的查询方法
	 * 
	 * @param bean 查询条件
	 * @return 验证结果
	 * 
	 * @author RiverYao
	 * @since 2015-06-10
	 */
	public List<PmnDepartmentInfo> getList(PmnDepartmentInfo bean);
	public PmnDepartmentInfo enable(Integer id, HttpServletRequest request);
	public PmnDepartmentInfo disable(Integer id, HttpServletRequest request);
	
	
}