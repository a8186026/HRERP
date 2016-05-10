package com.goldcow.emanage.promotion.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.goldcow.emanage.util.gen.entity.PmnRuleInfo;
import com.goldcow.emanage.util.gen.entity.PmnRuleProduct;
import com.goldcow.sframe.util.service.BaseIService;
/**
 * 折扣规则设定
 * 
 * @author gaoxiang
 * @since 2015-10-27
 */
public interface IPmnRuleInfoService extends BaseIService<PmnRuleInfo>{

	 /**
	 * 查询折扣规则信息-分页
	 * @param bean 查询条件
	 * @return 折扣规则信息列表
	 */
	public Map<String, Object> list(PmnRuleInfo bean);
	/**
	 * 添加产品折扣规则信息
	 * @param bean 
	 * @return PmnRuleProduct
	 */
	public PmnRuleProduct addRuleProduct(PmnRuleProduct bean, HttpServletRequest request);
	/**
	 * 修改折扣规则信息-分页
	 * @param bean 
	 * @return PmnRuleProduct
	 */
	public PmnRuleProduct updateRuleProduct(PmnRuleProduct bean, HttpServletRequest request);
	/**
	 * 批量添加产品折扣规则信息
	 * @param String data ,Integer rule_id
	 * @return PmnRuleProduct
	 */
	public String chooseProducts(String data ,Integer rule_id , HttpServletRequest request);
	/**
	 * 删除产品折扣规则信息
	 * @param Integer rule_id
	 * @return PmnRuleProduct
	 */
	void deleteRuleProduct(Integer rule_id, Integer product_id ,HttpServletRequest request);
	/**
	 * 保存折扣规则信息优先级顺序
	 * @param String data
	 * @return PmnRuleProduct
	 */
	public String savePriority(String data , HttpServletRequest request);
	
	/**
	 * 查询满足条件的折扣规则
	 * @param bean 查询条件
	 * @return 折扣规则信息列表
	 */
	public PmnRuleInfo isRuleProduct(Integer product_id);

}