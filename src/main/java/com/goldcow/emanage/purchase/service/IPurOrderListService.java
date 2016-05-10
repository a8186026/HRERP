package com.goldcow.emanage.purchase.service;



import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.goldcow.emanage.util.gen.entity.PurOrderList;
import com.goldcow.emanage.util.gen.entity.valueObject.purchase.PurOrderListAndProInfo;
import com.goldcow.emanage.util.gen.entity.valueObject.purchase.PurOrderListVO;
import com.goldcow.sframe.util.service.BaseIService;


/**
 * 服务层接口
 * 
 * @author chenyuxuan
 * @version v1.0
 * @since 2015-07-03
 */
public interface IPurOrderListService  extends BaseIService<PurOrderList> {
	 /**
	 * 查询订单信息-分页
	 * @param bean 查询条件
	 * @return 订单信息列表
	 */
	public List<PurOrderList> list(PurOrderList bean);
	/**
	 * 查询最近某产品三次进货
	 * @param bean 查询条件
	 * @return 订单信息列表
	 */
	public List<PurOrderListVO> listRecent(PurOrderList bean);
	
	public List<PurOrderList> getOrderList(String id);
	
	/**
	 * 查询通过订单审核 需要进行收货的小单信息
	 * @bean 条件 
	 */
	public Map<String, Object> listCheckOrderList(PurOrderListAndProInfo bean);
	/**
	 * 查询通过订单审核 需要进行收货的小单信息
	 * @bean 条件 
	 */
	public Map<String, Object> listRecepitOrderList(PurOrderListAndProInfo bean);
	
	/**
	 * 查询小单中产品是否为特殊品种
	 * 
	 * */
	public int getSpecialvariety(int product_id);
	PurOrderList addForStraight(PurOrderList bean, HttpServletRequest request);
}