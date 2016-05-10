package com.goldcow.emanage.wholeSale.service;

import java.util.Map;

import com.goldcow.emanage.util.gen.entity.StockInfo;
import com.goldcow.emanage.util.gen.entity.WholeSaleOrder;
import com.goldcow.sframe.util.service.BaseIService;

public interface IWholeSaleOrderService  extends BaseIService<WholeSaleOrder>  {
	/**
	 * 
	 * 查询库存信息-分页
	 * @param bean 查询条件
	 * @return 库存信息列表
	 */
	public Map<String, Object> list(WholeSaleOrder bean);
}
