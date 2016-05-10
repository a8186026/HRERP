package com.goldcow.emanage.refund.service.impl;


import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldcow.emanage.refund.persist.RefundDao;
import com.goldcow.emanage.refund.service.IRefundService;
import com.goldcow.emanage.util.gen.SysTools;
import com.goldcow.emanage.util.gen.entity.ProInfoManage;
import com.goldcow.emanage.util.gen.entity.Refund;
import com.goldcow.emanage.util.gen.entity.SupInfoManage;
import com.goldcow.emanage.util.gen.entity.valueObject.Refund.RefundVO;
import com.goldcow.emanage.util.gen.entity.valueObject.SupInfoManage.SupInfoManageVO;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.GlobalVal.REFUND_STATUS;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Maps;


@Service
public class RefundServiceImpl implements IRefundService {
	private static Logger log = LoggerFactory.getLogger(RefundServiceImpl.class);
	@Autowired
   private RefundDao dao;
   //dht

	@Override
	public Refund add(Refund bean, HttpServletRequest request) {
		// TODO Auto-generated method stub
		bean.setRefund_date(new Date());
		bean.setRefund_registerDate(new Date());
		bean.setRefund_accountTime(new Date());
		bean.setRefund_reviewTime(new Date());
		bean.setRefund_deliveryTime(new Date());
		bean.setRefund_checkoutTime(new Date());
		bean.setRefund_payCheckTime(new Date());
		bean.setRefund_cancelDate(new Date());
		bean.setStatus(0);
		
		//bean.setRefund_status(REFUND_STATUS.REFUND_REGISTER_SUCCESS);
		//记录返货登记完成状态码
		bean.setRefund_register(REFUND_STATUS.REFUND_REGISTER_SUCCESS);
		//返货付货和返货审核 状态码记为未完成
		bean.setRefund_deliveryCheck(REFUND_STATUS.REFUND_UNCHECK);
		bean.setRefund_payCheck(REFUND_STATUS.REFUND_UNDELIVERY);
		
		log.debug("新增返货记录");
		dao.add(bean);
		return bean;
	}

	@Override
	public void delete(Integer id, HttpServletRequest request) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Refund update(Refund bean, HttpServletRequest request) {
		// TODO Auto-generated method stub
		log.debug("修改返货记录");
		dao.update(bean);
		return bean;
	}
    public int updateList(List<RefundVO> list){
    	for (RefundVO bean : list) {

			bean.setRefund_cancel(REFUND_STATUS.REFUND_CANCEL_SUCCESS);
			bean.setRefund_cancelDate(new Date());
			bean.setStatus(9);
			System.out.println("111111"+bean.getRefund_payCheckPerson());

			System.out.println("ceshi"+bean.toString());
		    dao.update(bean);
		}
		return 1;
    }
	
	@Override
	public Refund getById(Integer id) {
		// TODO Auto-generated method stub
		return dao.get(id);
	}

	@Override
	public Map<String, Object> getStockListtoRefund(Refund bean) {
		
		List<RefundVO> list = dao.getStockListtoRefund(bean);
		int count = dao.getStockListtoRefundCount(bean);
		// 封装分页对象
		Map<String, Object> result = Maps.newHashMap();
		result.put("total", count);
		result.put("rows", list);
		return result;	
	}

	@Override
	public Map<String, Object> getStockProList(ProInfoManage bean, String code) {
		log.debug("查询库存产品信息列表");		
		bean.setProduct_chnpy(SysUtil.getSqlLikeParam(SysTools.decode(bean.getProduct_chnpy())));
		//插入查询条件-产品编号
		if(bean.getProduct_code()==null||bean.getProduct_code()=="")
			bean.setProduct_code(null);
		else
			bean.setProduct_code(SysUtil.getSqlLikeParam(bean.getProduct_code())); 
		if(code!=null&&code!="")
			code = "'"+code +"%'";
		else
			code = null;
	
		bean.setProduct_check(bean.getProduct_check());
		bean.setProduct_dsurveillanceid(bean.getProduct_dsurveillanceid()); 
 
		List<ProInfoManage> list = dao.getStockProList(bean,code);
		int count = dao.getStockProListCount(bean);

		// 封装分页对象
		Map<String, Object> result = Maps.newHashMap();
		result.put("total", count);
		result.put("rows", list);
				
		return result;
	}
   
   //wyc
	@Override
	public Map<String, Object> getDeliveryToCheckList(SupInfoManageVO bean) {
		// TODO Auto-generated method stub
		
/*		refund.setRefund_register(GlobalVal.REFUND_STATUS.REFUND_REGISTER_SUCCESS);
		refund.setRefund_payCheck(GlobalVal.REFUND_STATUS.REFUND_CHECK_SUCCESS);
		refund.setRefund_deliveryCheck(GlobalVal.REFUND_STATUS.REFUND_UNDELIVERY);*/
		
		//审核为未完成的（1）的已登记条目（0）
		bean.setRefund_register(GlobalVal.REFUND_STATUS.REFUND_REGISTER_SUCCESS);
		bean.setRefund_payCheck(GlobalVal.REFUND_STATUS.REFUND_UNCHECK);
		bean.setStatus(GlobalVal.REFUND_STATUS.REFUND_STATUS_CANCEL);
		
		List<SupInfoManageVO> list=dao.getDeliveryToCheckList(bean);
		for (SupInfoManageVO refundTemp : list) {
			System.out.println(refundTemp.toString());
		}
		int count=dao.getDeliveryToCheckListCount(bean);
		Map<String, Object> result = Maps.newHashMap();
		//往map里面插入键值对
		result.put("rows", list);
		result.put("total", count);
		return result;
	}
   
	@Override
	public Map<String, Object> getDeliveryToCheckListDetail(RefundVO bean) {
		// TODO Auto-generated method stub
		//审核为未完成的（1）的已登记条目（0）
		bean.setRefund_register(GlobalVal.REFUND_STATUS.REFUND_REGISTER_SUCCESS);
		bean.setRefund_payCheck(GlobalVal.REFUND_STATUS.REFUND_UNCHECK);
		bean.setRefund_deliveryCheck(GlobalVal.REFUND_STATUS.REFUND_UNDELIVERY);
		bean.setStatus(GlobalVal.REFUND_STATUS.REFUND_STATUS_CANCEL);
		
		List<RefundVO> list=dao.getDeliveryToCheckListDetail(bean);
		for (RefundVO refundTemp : list) {
			System.out.println(refundTemp.toString());
		}
		int count=dao.getDeliveryToCheckListDetailCount(bean);
		Map<String, Object> result = Maps.newHashMap();
		//往map里面插入键值对
		result.put("rows", list);
		result.put("total", count);
		return result;
	}
	
	@Override
	public int passAuditToSetStatus(List<RefundVO> list) {
		// TODO Auto-generated method stub
		for (RefundVO bean : list) {
/*			bean.setRefund_deliveryTime(new Date());
			bean.setRefund_deliveryCheck(GlobalVal.REFUND_STATUS.REFUND_DELIVERY_SUCCESS);*/
			bean.setRefund_payCheck(GlobalVal.REFUND_STATUS.REFUND_CHECK_SUCCESS);
			bean.setRefund_payCheckTime(new Date());
			
			System.out.println("111111"+bean.getRefund_payCheckPerson());
			
			
/*			bean.setRefund_payCheckPerson(refund_payCheckPerson);*/
			System.out.println("ceshi"+bean.toString());
		    dao.passAuditToSetStatus(bean);
		}
		return 1;
	}
	
	
	@Override
	public Map<String, Object> getRefundSupplyInfo(SupInfoManageVO bean) {
		// TODO Auto-generated method stub
		
		//这次出错的原因在我查询的时候  没有对输入的字符串做模糊查询处理，粘错了函数，粘出了一个lists，实际是在service里面的list函数，那里面有一个没用的lists函数，迷惑了我
		//插入查询条件-供方客户信息编码
		bean.setSup_code(SysUtil.getSqlLikeParam(bean.getSup_code())); 
		bean.setSup_name(SysUtil.getSqlLikeParam(bean.getSup_name()));
		bean.setSup_shortname(SysUtil.getSqlLikeParam(bean.getSup_shortname()));
		bean.setSup_chnpy(SysUtil.getSqlLikeParam(bean.getSup_chnpy()));
		
		
		//审核为未完成的（1）的已登记条目（0）
		bean.setRefund_register(GlobalVal.REFUND_STATUS.REFUND_REGISTER_SUCCESS);
		bean.setRefund_payCheck(GlobalVal.REFUND_STATUS.REFUND_UNCHECK);
		bean.setStatus(GlobalVal.REFUND_STATUS.REFUND_STATUS_CANCEL);
		
		
		List<SupInfoManageVO> list=dao.getRefundSupplyInfo(bean);
		for (SupInfoManage refundTemp : list) {
			System.out.println("这里");
			System.out.println(refundTemp.toString());
		}
		
		int count=dao.getRefundSupplyInfoCount(bean);
		Map<String, Object> result = Maps.newHashMap();
		//往map里面插入键值对
		result.put("rows", list);
		result.put("total", count);
		return result;
	}
	
	@Override
	public Map<String, Object> searchRefundItemBySupcode(RefundVO bean) {
		// TODO Auto-generated method stub
		
		//审核为未完成的（1）的已登记条目（0）
		bean.setRefund_register(GlobalVal.REFUND_STATUS.REFUND_REGISTER_SUCCESS);
		bean.setRefund_payCheck(GlobalVal.REFUND_STATUS.REFUND_UNCHECK);
		bean.setStatus(GlobalVal.REFUND_STATUS.REFUND_STATUS_CANCEL);
		
		List<RefundVO> list=dao.searchRefundItemBySupcode(bean);
		for (RefundVO refundTemp : list) {
			System.out.println(refundTemp.toString());
		}
		int count=dao.searchRefundItemBySupcodeCount(bean);
		Map<String, Object> result = Maps.newHashMap();
		//往map里面插入键值对
		result.put("rows", list);
		result.put("total", count);
		return result;
	}
	
   //wjj
	
	@Override
	public Map<String, Object> getDeliveryList(Refund refund){
		refund.setRefund_register(GlobalVal.REFUND_STATUS.REFUND_REGISTER_SUCCESS);
		refund.setRefund_payCheck(GlobalVal.REFUND_STATUS.REFUND_CHECK_SUCCESS);
		refund.setRefund_deliveryCheck(GlobalVal.REFUND_STATUS.REFUND_UNDELIVERY);
		refund.setStatus(GlobalVal.RECORD_STATUS.ENABLE);
		List<Refund> list=dao.getDeliveryList(refund);
		/*for (Refund Refund : list) {
			System.out.println("111111111111111111+       "+Refund.getSup_ctactperson());
		}
		*/
		int count=dao.getDeliveryCount(refund);
		Map<String, Object> result = Maps.newHashMap();
		result.put("rows", list);
		result.put("total", count);
		return result;
	}

	
	@Override
	public Map<String, Object> listCancel(RefundVO refund) {
		if(refund.getRefund_ticketId()!=null){
		refund.setRefund_ticketId(SysUtil.getSqlLikeParam(refund.getRefund_ticketId()));
		}
		refund.setProduct_name(SysUtil.getSqlLikeParam(refund.getProduct_name()));
		refund.setSup_name(SysUtil.getSqlLikeParam(refund.getSup_name()));
		
		List<RefundVO> list=dao.listVO(refund);
		int count=dao.count(refund);
		Map<String, Object> result = Maps.newHashMap();
		result.put("rows", list);
		result.put("total", count);
		return result;
	}

	@Override
	public Integer saveDeliveryStatus(List<RefundVO> list) {
		for (RefundVO bean : list) {
			bean.setRefund_deliveryTime(new Date());
			bean.setRefund_deliveryCheck(GlobalVal.REFUND_STATUS.REFUND_DELIVERY_SUCCESS);
			dao.saveDeliveryStatus(bean);	
			}
		return 1;
	}
	
}
