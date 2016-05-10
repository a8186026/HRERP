package com.goldcow.emanage.retail.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.goldcow.emanage.util.gen.entity.RetailOrderBatch;
import com.goldcow.emanage.util.gen.entity.StockInfo;
import com.goldcow.emanage.util.gen.entity.valueObject.retail.RetailOrderVO;
import com.goldcow.sframe.util.service.BaseIService;


/**
 * 服务层接口
 * 
 * @author cyx
 * @version v1.0
 * @since 2015-11-17
 */
public interface IRetailOrderBatchService extends BaseIService<RetailOrderBatch>{
	
	/**
	 * 查询零售批次订单信息-分页
	 * @param bean 查询条件
	 * @return 零售订单批次信息列表
	 */
	public Map<String, Object> list(RetailOrderBatch bean);
	
	/**
	 * 依据VO中的数据进行插入
	 * @param bean RetailOrderVO
	 * @param number 销售数量
	 * @param retailOrderVO 销售的库存VO
	 * @return 零售订单批次信息
	 */
	public RetailOrderVO addVO(RetailOrderVO bean,Double number,RetailOrderVO retailOrderVO);
	/** 更新已卖产品兑换信息
	 * @param ids 更新的产品
	 * @param ticket_id 票号
	 * */
	public void updateOrderBatch(String ids ,String ticket_id,HttpServletRequest request);

}