package com.goldcow.emanage.giftCard.service;

import java.util.Map;

import com.goldcow.emanage.util.gen.entity.GiftCardManage;
import com.goldcow.emanage.util.gen.entity.RetailOrderProduct;
import com.goldcow.emanage.util.gen.entity.valueObject.retail.RetailOrderVO;
import com.goldcow.sframe.util.service.BaseIService;


/**
 * 服务层接口
 * 
 * @author cyx
 * @version v1.0
 * @since 2015-12-11
 */
public interface IGiftCardService extends BaseIService<GiftCardManage>{
	
	/**
	 * 查询代金卡信息-分页
	 * @param bean 查询条件
	 * @return 代金卡信息列表
	 */
	public Map<String, Object> list(GiftCardManage bean);
	
	
	/**
	 * 查询当前消费金额能满足的代金卡规则
	 * 
	 * @param department_id 部门Id
	 * @param Amount 消费金额
	 */
	public Double getOffsetAmount(Integer department_id, Double Amount);
	
}