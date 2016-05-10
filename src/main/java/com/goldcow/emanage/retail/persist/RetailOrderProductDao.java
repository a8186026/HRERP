package com.goldcow.emanage.retail.persist;

import org.apache.ibatis.annotations.Param;

import com.goldcow.emanage.util.gen.entity.RetailOrderProduct;
import com.goldcow.sframe.util.mybatis.BaseDao;
import com.goldcow.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface RetailOrderProductDao extends BaseDao<RetailOrderProduct> {
	
	
	/** 获得当前销售订单最大的Product序号
	 * @param 订单票号
	 * @return 返回最大序号
	 * */
	public Integer getMaxOrderProductId(String ticketId);
	
	/** 获得当前销售订单产品包含的批次数量
	 * @param 订单票号
	 * @param 订单产品序号
	 * @return 数量
	 * */
	public Integer countBatch(@Param(value="retail_order_ticketId")String retail_order_ticketId,@Param(value="order_productId")Integer order_productId);
	
	/** 通过票号和序号删除订单产品
	 * @param 订单票号
	 * @param 订单产品序号
	 * @return 数量
	 * */
	public void deleteByTicketProduct(@Param(value="retail_order_ticketId")String retail_order_ticketId,@Param(value="order_productId")Integer order_productId);
	
	/** 通过票号批量更新订单产品信息
	 * @param 订单票号
	 * @param 付数
	 * */
	public void updateByTicketId(@Param(value="retail_order_ticketId")String retail_order_ticketId,@Param(value="retail_order_doseNumber")Integer retail_order_doseNumber);
}