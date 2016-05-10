package com.goldcow.emanage.basInfo.service;

import java.util.List;
import java.util.Map;

import com.goldcow.emanage.util.gen.entity.BasDepartmentInfo;
import com.goldcow.sframe.util.service.BaseIService;
/**
 * 部门档案信息
 * 
 * @author wangqi
 * @version v1.0
 * @since 2015-6-3
 */
public interface IBasDepartmentInfoService extends BaseIService<BasDepartmentInfo>{

	/**
	 * 查询部门档案信息-分页
	 * @param bean 查询条件
	 * @return 部门档案信息列表
	 */
	public Map<String, Object> list(BasDepartmentInfo bean);
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
	public List<BasDepartmentInfo> getList(BasDepartmentInfo bean);
	
	/**
	 * 返回所有未参加某活动的部门
	 * 
	 * @param request HttpServletRequest
	 * @param depts 已参加的部门
	 * @return 结果
	 * 
	 * @author wubin
	 * @since 2015-10-27
	 */
	public List<BasDepartmentInfo> getListByDepts(String depts);
	/**
	 * 返回所有参加某活动的部门
	 * 
	 * @param mem_day_id 会员日ID
	 * @return 结果
	 * 
	 * @author wubin
	 * @since 2015-10-28
	 */
	public List<BasDepartmentInfo> getListByPromotion(Integer mem_day_id);
}