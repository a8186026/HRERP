package com.goldcow.emanage.refund.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.goldcow.emanage.util.gen.entity.ProInfoManage;
import com.goldcow.emanage.util.gen.entity.Refund;
import com.goldcow.emanage.util.gen.entity.SupInfoManage;
import com.goldcow.emanage.util.gen.entity.valueObject.Refund.RefundVO;
import com.goldcow.emanage.util.gen.entity.valueObject.SupInfoManage.SupInfoManageVO;
import com.goldcow.sframe.util.service.BaseIService;

public interface IRefundService extends BaseIService<Refund> {

	
	//dht
	/**
	 * 退货登记页面获取库存信息
	 * @param bean
	 * @return
	 */
	public Map<String, Object>getStockListtoRefund(Refund bean);
	public Map<String, Object> getStockProList(ProInfoManage bean,String code);
	
	//wjj
	public Map<String, Object> getDeliveryList(Refund bean);
	public Integer saveDeliveryStatus(List<RefundVO> bean); 

	public Map<String,Object> listCancel(RefundVO refund);
	
	//wyc
	public Map<String, Object> getDeliveryToCheckList(SupInfoManageVO bean);
	public Map<String, Object> getDeliveryToCheckListDetail(RefundVO bean);
	/**
	 * 特殊药品审核
	 * @param bean 查询条件
	 * @return 条数
	 */
	public int passAuditToSetStatus(List<RefundVO> bean);
	
	public Map<String, Object> getRefundSupplyInfo(SupInfoManageVO bean);
	
	public Map<String, Object> searchRefundItemBySupcode(RefundVO bean);
	public int updateList(List<RefundVO> list);
	
}
