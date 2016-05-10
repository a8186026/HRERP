package com.goldcow.emanage.giftCard.persist;

import org.apache.ibatis.annotations.Param;

import com.goldcow.emanage.util.gen.entity.GiftCardManage;
import com.goldcow.sframe.util.mybatis.BaseDao;
import com.goldcow.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface GiftCardDao extends BaseDao<GiftCardManage> {

	
	/**
	 * 查询当前消费金额能满足的代金卡规则
	 * 
	 * @param department_id 部门Id
	 * @param Amount 消费金额
	 */
	public Double getOffsetAmount(@Param(value="department_id")Integer department_id,@Param(value="gift_card_fulfilAmount")Double gift_card_fulfilAmount);
}