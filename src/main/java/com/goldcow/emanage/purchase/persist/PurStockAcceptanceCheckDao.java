package com.goldcow.emanage.purchase.persist;

import com.goldcow.emanage.util.gen.entity.PurAcceptCheck;
import com.goldcow.sframe.util.mybatis.BaseDao;
import com.goldcow.sframe.util.mybatis.MyBatisRepository;
/**
 * 订单收货确认信息
 * 
 * @author zhanxiaotong
 * @version v1.0
 * @since 2015-10-15
 */

@MyBatisRepository
public interface PurStockAcceptanceCheckDao extends BaseDao<PurAcceptCheck> {
	
}