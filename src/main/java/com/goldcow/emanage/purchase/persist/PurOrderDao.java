package com.goldcow.emanage.purchase.persist;

import java.util.List;

import com.goldcow.emanage.util.gen.entity.PurOrder;
import com.goldcow.emanage.util.gen.entity.PurOrderList;
import com.goldcow.emanage.util.gen.entity.valueObject.purchase.PurOrderListAndProInfo;
import com.goldcow.sframe.util.mybatis.BaseDao;
import com.goldcow.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface PurOrderDao extends BaseDao<PurOrder> {
	/** 获得最大票号*/
	Integer getMaxTicketID(String date);
	/** 查询订单小单关联产品*/
	List<PurOrderListAndProInfo> getPurOrderListAndProductInfo(PurOrder bean);
	/** 根据bean中的信息进行删除*/
	void deletePurderByBean(PurOrder bean);
	/** 根据大单操作添加日志记录*/
	public int addlog(PurOrder purOrder);
	
	/** 查询需要进行订单审核的大单信息*/
	public List<PurOrder> listCheckOrder(PurOrder bean);
	/** 查询通过订单审核 需要进行收货的大单信息*/
	public List<PurOrder> listRecepitOrder(PurOrder bean);
	
	/** 通过ticket_id查询大单id*/
	public int getOrderIdByTicketid(String ticket_id);

	List<PurOrderListAndProInfo> getPurOrderListAndProductInfoFor(
			PurOrderList bean);
}