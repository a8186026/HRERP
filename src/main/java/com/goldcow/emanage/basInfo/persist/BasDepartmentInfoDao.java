package com.goldcow.emanage.basInfo.persist;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.goldcow.emanage.util.gen.entity.BasDepartmentInfo;
import com.goldcow.sframe.util.mybatis.BaseDao;
import com.goldcow.sframe.util.mybatis.MyBatisRepository;
/**
 * 
 * 部门档案信息
 * 
 * @author wangqi
 * @version v1.0
 * @since 2015-6-3
 */

@MyBatisRepository
public interface BasDepartmentInfoDao extends BaseDao<BasDepartmentInfo> {
	int checkDepartmentNumber = 0;

	/** 分页查询 */
	public List<BasDepartmentInfo> list(BasDepartmentInfo bean);
	
	public int count(BasDepartmentInfo bean);

	public Integer checkDepartmentNumber(String department_number);
	/**
	 * 返回所有未参加某活动的部门
	 * 
	 * @param depts 已参加的部门
	 * @return 结果
	 * 
	 * @author wubin
	 * @since 2015-10-27
	 */
	public List<BasDepartmentInfo> getListByDepts(@Param(value="depts") List<Integer> depts);
	/**
	 * 返回所有参加某活动的部门
	 * 
	 * @param mem_day_id 会员日ID
	 * @return 结果
	 * 
	 * @author wubin
	 * @since 2015-10-28
	 */
	public List<BasDepartmentInfo> getListByPromotion(@Param(value="depts") List<Integer> depts);
	
}