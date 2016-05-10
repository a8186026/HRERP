package com.goldcow.emanage.purchase.service;

import java.util.Map;

import com.goldcow.emanage.util.gen.entity.PurAcceptCheck;
import com.goldcow.sframe.util.service.BaseIService;
/**
 * 订单收货确认信息
 * 
 * @author zhanxiaotong
 * @version v1.0
 * @since 2015-7-15
 */
public interface IPurStockAcceptanceCheckService extends BaseIService<PurAcceptCheck>{

	 /**
	 * 查询订单收货确认信息息-分页
	 * @param bean 查询条件
	 * @return 订单收货确认信息列表
	 */
	public Map<String, Object> list(PurAcceptCheck bean);

}