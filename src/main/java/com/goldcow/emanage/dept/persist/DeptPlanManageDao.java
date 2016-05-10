package com.goldcow.emanage.dept.persist;

import java.util.List;

import com.goldcow.emanage.util.gen.entity.DeptPlanManage;
import com.goldcow.emanage.util.gen.entity.valueObject.dept.ProStockInfoVO;
import com.goldcow.sframe.util.mybatis.BaseDao;
import com.goldcow.sframe.util.mybatis.MyBatisRepository;
/**
 * 调货计划
 * 
 * @author 战晓桐
 * @version v1.0
 * @since 2015-10-30
 */
@MyBatisRepository
public interface DeptPlanManageDao extends BaseDao<DeptPlanManage> {
	public List<DeptPlanManage> lists(DeptPlanManage bean);

	public List<ProStockInfoVO> stockInfoList(ProStockInfoVO bean);

	public int countStockInfoList(ProStockInfoVO bean);

	public Integer getMaxDeptPlanTicket(String date);

	public List<DeptPlanManage> getBeanByProID(Integer deptPlan_pro_id);

}