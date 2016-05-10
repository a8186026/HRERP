package com.goldcow.emanage.purchase.persist;

import java.util.List;

import com.goldcow.emanage.util.gen.entity.PurOrderList;
import com.goldcow.emanage.util.gen.entity.valueObject.purchase.PurOrderListAndProInfo;
import com.goldcow.emanage.util.gen.entity.valueObject.purchase.PurOrderListVO;
import com.goldcow.sframe.util.mybatis.BaseDao;
import com.goldcow.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface PurOrderListDao extends BaseDao<PurOrderList> {
	public List<PurOrderList> getlist(String id);
	
	/** 根据Bean信息删除小单信息*/
	public void deletePurderListByBean(PurOrderList purOrderListBean);
	/**
	 * 查询最近某产品三次进货
	 * @param bean 查询条件
	 * @return 订单信息列表
	 */
	public List<PurOrderListVO> listRecent(PurOrderList bean);
	/** 根据小单操作添加日志记录*/
	public int addlog(PurOrderList purOrderList);
	/** 查询通过订单审核 需要进行收货的小单信息*/
	public List<PurOrderListAndProInfo> listCheckOrderList(PurOrderListAndProInfo bean);
	/** 查询通过订单审核 需要进行收货的小单信息*/
	public List<PurOrderListAndProInfo> listRecepitOrderList(PurOrderListAndProInfo bean);
	
	/** 返回小单中产品是否为特殊品种 */
	public int getSpecialvariety(int product_id);
}