package com.goldcow.emanage.dept.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.goldcow.emanage.util.gen.entity.DeptPlanManage;
import com.goldcow.emanage.util.gen.entity.valueObject.dept.ProStockInfoVO;
import com.goldcow.sframe.util.service.BaseIService;
/**
 * 财务信息
 * 
 * @author wangqi
 * @version v1.0
 * @since 2015-7-1
 */
public interface IDeptPlanManageService extends BaseIService<DeptPlanManage>{

	/**
	 * 
	 * 查询供方客户信息-分页
	 * @param bean 查询条件
	 * @return 供方客户信息列表
	 */
	public Map<String, Object> lists(DeptPlanManage bean);

	public Map<String, Object> stockInfoList(ProStockInfoVO bean);

	public boolean update(String data, HttpServletRequest request);
	
	public void check(DeptPlanManage bean, HttpServletRequest request);

	/**
	 * 审核需要
	 * @param data 需要入库的id
	 * */
	public String getMaxDeptPlanTicket();

	public boolean add(String data, HttpServletRequest request);

	public boolean addPlanManual(String data, HttpServletRequest request);
}