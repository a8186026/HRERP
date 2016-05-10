package com.goldcow.emanage.retail.service;

import java.util.Map;

import com.goldcow.emanage.util.gen.entity.RetailOrderProduct;
import com.goldcow.emanage.util.gen.entity.valueObject.retail.RetailOrderVO;
import com.goldcow.sframe.util.service.BaseIService;


/**
 * 服务层接口
 * 
 * @author cyx
 * @version v1.0
 * @since 2015-11-17
 */
public interface IRetailOrderProductService extends BaseIService<RetailOrderProduct>{
	
	/**
	 * 查询零售订单产品信息-分页
	 * @param bean 查询条件
	 * @return 零售订单产品信息列表
	 */
	public Map<String, Object> list(RetailOrderProduct bean);
	

	
	/**
	 * 依据VO中的数据进行插入
	 * @param bean RetailOrderVO
	 * @return 零售订单产品信息
	 */
	public RetailOrderVO addVO(RetailOrderVO bean,Double number);
	
	
	/** 获得当前销售订单产品包含的批次数量
	 * @param 订单票号
	 * @param 订单产品序号
	 * @return 数量
	 * */
	public Integer countBatch(String retail_order_ticketId,Integer order_productId);
}