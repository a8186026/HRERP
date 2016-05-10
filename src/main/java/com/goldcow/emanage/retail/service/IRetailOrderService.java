package com.goldcow.emanage.retail.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.goldcow.emanage.util.gen.entity.MemCardManage;
import com.goldcow.emanage.util.gen.entity.PmnFulfillGift;
import com.goldcow.emanage.util.gen.entity.PmnGiftSale;
import com.goldcow.emanage.util.gen.entity.RetailOrder;
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
public interface IRetailOrderService extends BaseIService<RetailOrder>{
	
	/**
	 * 查询零售订单信息-分页
	 * @param bean 查询条件
	 * @return 零售订单信息列表
	 */
	public Map<String, Object> list(RetailOrder bean);
	
	/** 
	 * 获得当前最大的零售订单编码
	 * @return 返回最大流水号
	 * */
	public String getMaxRetailOrderCode(String currentDate,HttpServletRequest request);
	
	/** 
	 * 根据票号删除
	 * @param 票号
	 * */
	public void deleteByTicketId(String ticketId);
	
	

	public List<RetailOrderVO> getStockByProductId(RetailOrderVO stockInfo,List<Integer> product_ids);
	
	/** 
	 * 查询零售产品VO
	 * @param bean
	 * @param type 类型，选择产品或是选择批次
	 * @param memCardManage	会员卡信息
	 * @return 产品批次库存VO
	 * */
	public List<RetailOrderVO> getRetailOrderVO(RetailOrderVO bean,String ids,String type,MemCardManage memCardManage);

	/** 
	 * 获取满赠活动信息
	 * @param request
	 * */
	public Map<String, Object> getGiftSales(HttpServletRequest request,String member_id,String data);
	
	/** 
	 * 获取满额赠活动信息
	 * @param request
	 * */
	public Map<String, Object> getFullFillGifts(HttpServletRequest request,String member_id,String data);

	/** 
	 * 根据票号查询已完成的订单信息
	 * @param sale_order_ticketId 票号
	 * @return 产品批次库存VO
	 * */
	public List<RetailOrderVO> getDraftInfo(String sale_order_ticketId);
	
	
	/** 获得当前销售订单包含的产品数量
	 * @param 订单票号
	 * @return 数量
	 * */
	public Integer countProduct(String retail_order_ticketId);
	
	/**
	 * 保存活动订单产品信息
	 * @param product_id 产品ID
	 * @param number 销售数量
	 * @param discountedPrice 折后价
	 * @param retail_order_ticketId 票号
	 * @return 零售订单批次信息
	 */
	public List<RetailOrderVO> salePromotion(String product_id, String number,
			String discountedPrice, String retail_order_ticketId,String retail_type,HttpServletRequest request );
}