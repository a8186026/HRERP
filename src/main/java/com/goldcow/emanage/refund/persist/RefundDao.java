package com.goldcow.emanage.refund.persist;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.goldcow.emanage.util.gen.entity.ProInfoManage;
import com.goldcow.emanage.util.gen.entity.Refund;
import com.goldcow.emanage.util.gen.entity.SupInfoManage;
import com.goldcow.emanage.util.gen.entity.valueObject.Refund.RefundVO;
import com.goldcow.emanage.util.gen.entity.valueObject.SupInfoManage.SupInfoManageVO;
import com.goldcow.sframe.util.mybatis.BaseDao;
import com.goldcow.sframe.util.mybatis.MyBatisRepository;


@MyBatisRepository
public interface RefundDao extends BaseDao<Refund> {

	
	//dht
	public List<RefundVO>getStockListtoRefund(Refund bean);
	public Integer getStockListtoRefundCount(Refund bean);
	public List<ProInfoManage> getStockProList(@Param(value="bean") ProInfoManage bean,@Param(value="code") String code);
	public Integer getStockProListCount(ProInfoManage bean);
	//WJJ
	public List<Refund> getDeliveryList(Refund bean);
	public Integer getDeliveryCount(Refund bean);
	public Integer saveDeliveryStatus(Refund bean);
	
	//wyc
	public List<SupInfoManageVO> getDeliveryToCheckList(SupInfoManageVO bean);
	public Integer getDeliveryToCheckListCount(SupInfoManageVO bean);
	
	public List<RefundVO> getDeliveryToCheckListDetail(RefundVO bean);
	public Integer getDeliveryToCheckListDetailCount(RefundVO bean);
	public int passAuditToSetStatus(RefundVO bean);
	
	
	public List<SupInfoManageVO> getRefundSupplyInfo(SupInfoManageVO bean);
	public int getRefundSupplyInfoCount(SupInfoManageVO bean);
	
	public List<RefundVO> searchRefundItemBySupcode(RefundVO bean);
	public int searchRefundItemBySupcodeCount(RefundVO bean);
	
	
	
	public List<RefundVO> listVO(Refund bean);

}
