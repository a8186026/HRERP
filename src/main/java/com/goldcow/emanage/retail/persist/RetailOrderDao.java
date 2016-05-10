package com.goldcow.emanage.retail.persist;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.goldcow.emanage.util.gen.entity.RetailOrder;
import com.goldcow.emanage.util.gen.entity.StockInfo;
import com.goldcow.emanage.util.gen.entity.valueObject.retail.RetailOrderVO;
import com.goldcow.sframe.util.mybatis.BaseDao;
import com.goldcow.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface RetailOrderDao extends BaseDao<RetailOrder> {
	
	/** 获得当前最大的零售订单编码
	 * @param 当前日期
	 * @return 返回最大流水号
	 * */
	public Integer getMaxRetailOrderCode(String currentDate);
	
	/** 
	 * 根据票号删除
	 * @param 票号
	 * */
	public void deleteByTicketId(String ticketId);
	
	/** 
	 * 根据productId查询批次库存
	 * @param bean
	 * @return 产品批次库存VO
	 * */
	public List<RetailOrderVO> getStockByProductId(@Param(value="bean")RetailOrderVO bean,@Param(value="list")List<Integer> list);
	
	/** 
	 * 根据票号查询已完成的订单信息
	 * @param retail_order_ticketId 票号
	 * @return 产品批次库存VO
	 * */
	public List<RetailOrderVO> getDraftInfo(String retail_order_ticketId);
	
	/** 获得当前销售订单包含的产品数量
	 * @param 订单票号
	 * @return 数量
	 * */
	public Integer countProduct(String retail_order_ticketId);
	

}