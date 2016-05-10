package com.goldcow.emanage.purchase.service;



import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.goldcow.emanage.util.gen.entity.PurOrder;
import com.goldcow.emanage.util.gen.entity.PurOrderList;
import com.goldcow.sframe.util.service.BaseIService;


/**
 * 服务层接口
 * 
 * @author yuanxin
 * @version v1.0
 * @since 2015-07-06
 */
public interface IPurOrderService  extends BaseIService<PurOrder> {
	 /**
	 * 查询订单信息-分页
	 * @param bean 查询条件
	 * @return 订单信息列表
	 */
	public Map<String, Object> list(PurOrder bean);

	/**
	 * 查询最大票号
	 * 
	 * */
	public String getMaxTicketID();
	/**
	 * 查询订单信息 不分页
	 * @param bean 查询条件
	 * @return 订单信息列表
	 */
	public List<PurOrder> getList(PurOrder bean);

	/**
	* 查询订单小单关联产品
	* @param bean 查询条件
	* @return 订单小单关联产品列表
	* @author RiverYao 2015-07-15
	*/
	public Map<String, Object> getPurOrderListAndProductInfo(PurOrder bean);

	/**
	 * 根据大单信息删除所有的大单以及小单信息
	 * @bean 条件 
	 */
	public Boolean deletePurOrderAll(PurOrder bean);
	/**
	 * 订单审核修改订单大单信息
	 * @bean 条件 
	 */
	public PurOrder checkUpdate(PurOrder bean, HttpServletRequest request);
	
	
	/**
	 * 查询需要进行订单审核的大单信息
	 * @bean 条件 
	 */
	public Map<String, Object> listCheckOrder(PurOrder bean);
	
	/**
	 * 查询通过订单审核 需要进行收货的大单信息
	 * @bean 条件 
	 */
	public Map<String, Object> listRecepitOrder(PurOrder bean);


	Map<String, Object> getPurOrderListAndProductInfoForStaright(
			PurOrderList bean);
	
}