package com.goldcow.emanage.product.service;

import java.util.List;
import java.util.Map;

import com.goldcow.emanage.util.gen.entity.ProInfoManage;
import com.goldcow.sframe.util.service.BaseIService;
/**
 * 厂家档案信息
 * 
 * @author gaoxiang
 * @version v1.0
 * @since 2015-6-5
 */
public interface IProInfoManageService extends BaseIService<ProInfoManage>{

	 /**
	 * 查询产品信息-分页
	 * @param bean 查询条件
	 * @param code 产品编码(用于树形分类子产品查询)
	 * @return 厂家档案信息列表
	 */
	public Map<String, Object> list(ProInfoManage bean,String code);
	/**查找当前数据库表中最大的产品编码值(前面代表产品类别，后3位代表流水号)
	 * @param ticketNumber  代表前面产品类别
	 * @return 返回最大流水号
	 * */
	public String getMaxProductCode(String ticketNumber);
	 /**
	 * 查询相似产品信息
	 * @param bean 查询条件
	 * @return 厂家档案信息列表
	 * @author wubin
	 */
	public List<ProInfoManage> listSimilar(ProInfoManage bean);
	
	/**
	 * 查询产品折扣信息(去除重复)
	 * @param rule_id 查询条件
	 * @return 产品折扣信息
	 * @author gaoxiang
	 */
	public List<ProInfoManage> listRuleProduct(ProInfoManage bean , Integer rule_id);
	/**
	 * 查询产品折扣信息
	 * @param rule_id 查询条件
	 * @return 产品折扣信息
	 * @author gaoxiang
	 */
	public List<ProInfoManage> listRuleProductInfo(ProInfoManage bean , Integer rule_id);
}