package com.goldcow.emanage.retail.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldcow.emanage.retail.persist.RetailOrderBatchDao;
import com.goldcow.emanage.retail.persist.RetailOrderProductDao;
import com.goldcow.emanage.retail.service.IRetailOrderBatchService;
import com.goldcow.emanage.util.gen.entity.RetailOrderBatch;
import com.goldcow.emanage.util.gen.entity.StockInfo;
import com.goldcow.emanage.util.gen.entity.valueObject.retail.RetailOrderVO;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Maps;

@Service
public class RetailOrderBatchServiceImpl implements IRetailOrderBatchService{
	private static Logger log = LoggerFactory.getLogger(RetailOrderBatchServiceImpl.class);
	/** 零售订单产品批次信息操作 */
	@Autowired
	private RetailOrderBatchDao dao;
	@Autowired
	private RetailOrderProductDao retailOrderProductDao;

	/**
	 * 根据ID查询
	 * 
	 * @param id ID
	 * @return 零售订单产品批次
	 */
	@Override
	public RetailOrderBatch getById(Integer id) {
		log.debug("取得零售订单产品批次信息 => ID : " + id);
		return dao.get(id);
	}

	/**
	 * 查询零售订单信息列表
	 * 
	 * @param bean 查询条件
	 * @return 零售订单信息列表
	 */
	@Override
	public Map<String, Object> list(RetailOrderBatch bean){	
		log.debug("查询零售订单产品批次信息信息列表");		

		List<RetailOrderBatch> list = dao.list(bean);
		int count = dao.count(bean);

		// 封装分页对象
		Map<String, Object> result = Maps.newHashMap();
		result.put("total", count);
		result.put("rows", list);
		return result;
	}

	/**
	 * 新增零售订单产品批次信息
	 * 
	 * @param bean 零售订单产品批次信息
	 * @return 零售订单产品批次信息
	 */
	@Override
	public RetailOrderBatch add(RetailOrderBatch bean, HttpServletRequest request) {
		log.debug("新增零售订单产品批次信息");
		SysUtil.checkInput(bean);
		dao.add(bean);
		log.debug("新增零售订单产品批次信息成功 => id : " + bean.getOrder_batch_id());
		return bean;
	}

	/**
	 * 修改零售订单产品批次
	 * 
	 * @param bean 零售订单产品批次信息
	 * @return 零售订单产品批次信息
	 */
	@Override
	public RetailOrderBatch update(RetailOrderBatch bean, HttpServletRequest request) {
		log.debug("修改零售订单产品批次信息");
		SysUtil.checkInput(bean);
		dao.update(bean);
		log.debug("修改零售订单产品批次信息成功 => id : " + bean.getOrder_batch_id());
		return bean;
	}

	/**
	 * 删除零售订单产品批次信息
	 * 
	 * @param id ID
	 */
	@Override
	public void delete(Integer id, HttpServletRequest request) {
		RetailOrderBatch bean = this.getById(id);
		log.debug("删除零售订单产品批次信息");
		System.out.println("id++++++++++++++++++++++++++++"+id);
		dao.deleteAndAdd(bean);
		log.debug("删除零售订单产品批次信息成功 => id : " + id);
		
		if(retailOrderProductDao.countBatch(bean.getRetail_order_ticketId(), bean.getOrder_productId()) == 0){
			retailOrderProductDao.deleteByTicketProduct(bean.getRetail_order_ticketId(), bean.getOrder_productId());
		}
	}

	@Override
	public RetailOrderVO addVO(RetailOrderVO bean, Double number,RetailOrderVO retailOrderVO) {
		RetailOrderBatch rob = new RetailOrderBatch();
		rob.setRetail_order_ticketId(bean.getRetail_order_ticketId());
		rob.setOrder_productId(bean.getOrder_productId());
		rob.setOrder_batch_smallNumber(this.getMaxOrderBatchId(bean));
		rob.setProduct_id(bean.getProduct_id());
		rob.setStock_info_id(retailOrderVO.getStock_info_id());
		rob.setStock_storage(retailOrderVO.getStock_storage());
		rob.setStock_packunit(bean.getProduct_packingamount());
		rob.setStock_batchCode(retailOrderVO.getStock_batchCode());
		rob.setStock_invalidDate(retailOrderVO.getStock_invalidDate());
		rob.setProduct_barcode(bean.getProduct_barcode());
		rob.setStock_storageNumber(retailOrderVO.getStock_storageNumber());
		rob.setStock_purchasePrice(retailOrderVO.getStock_purchasePrice());
		rob.setStock_settlementPrice(retailOrderVO.getStock_settlementPrice());
		rob.setStock_intakeTicket(retailOrderVO.getStock_intakeTicket());
		rob.setStock_intakeSmallNumber(retailOrderVO.getStock_intakeSmallNumber());
		rob.setOrder_batch_quantity(number);
		rob.setOrder_batch_unitPrice(bean.getOrder_product_unitPrice());
		rob.setOrder_batch_member(bean.getMem_card_id());					//会员卡号
		rob.setOrder_batch_storePrice(bean.getOrder_product_unitPrice());	//门店售价
		rob.setOrder_batch_intakeBrief(retailOrderVO.getStock_intakeBrief());	//入库摘要
		//减差价
		rob.setOrder_batch_cutDifference(bean.getMemDescCost());
		//设置金额
		rob.setOrder_batch_amount(bean.getDiscountedPrice()*number);
		//设置毛利
		rob.setOrder_batch_grossProfit(bean.getProduct_retailprice()-bean.getProduct_lastprice());
		rob.setOrder_batch_date(new Date());
		
		//经手人，经手人2
		rob.setOrder_batch_fullGiftAmount(bean.getOrder_batch_fullGiftAmount());
		rob.setOrder_batch_fullGiftExchange(bean.getOrder_batch_fullGiftExchange());
		rob.setOrder_batch_discountAmount(bean.getOrder_product_discountAmount());
		rob.setOrder_batch_promotionVariety(bean.getProduct_promotionvariety());
		rob.setOrder_batch_retailCommission(bean.getProduct_retailcommission());
		rob.setOrder_batch_mbernointegral(bean.getProduct_mbernointegral());
		rob.setOrder_batch_pointMutiple(bean.getOrder_batch_pointMutiple());
		rob.setOrder_batch_singleDiscountAuthoPerson(bean.getDiscountPerson());
		rob.setOrder_batch_singleDiscountAuthoReason(bean.getDiscountReason());
		rob.setOrder_batch_saleType(bean.getOrder_product_saleType());
		
		
		dao.add(rob);
		log.debug("新增零售订单产品批次信息成功 => id : " + rob.getOrder_batch_id());
		System.out.println("number:"+number);
		bean.setNumber(number);
		bean.setOrder_batch_smallNumber(rob.getOrder_batch_smallNumber());
		bean.setOrder_product_amount(rob.getOrder_batch_amount());
		bean.setStock_invalidDate(rob.getStock_invalidDate());
		bean.setOrder_batch_id(rob.getOrder_batch_id());
		bean.setStock_info_id(rob.getStock_info_id());
		RetailOrderVO newbean = new RetailOrderVO();
		try {
			newbean = bean.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newbean;
	}
	
	/** 获得当前销售订单产品最大的批次编号
	 * @param 订单票号
	 * @return 返回最大序号
	 * */
	public Integer getMaxOrderBatchId(RetailOrderVO bean){
		Integer order_BatchId = dao.getMaxOrderBatchId(bean);
		if(order_BatchId == null){
			order_BatchId = 1;
		}else
			order_BatchId++;
		return order_BatchId;
	}

	@Override
	public void updateOrderBatch(String ids, String ticket_id,
			HttpServletRequest request) {
		log.debug("更新已卖产品兑换信息");
		List<Integer> product_ids = new ArrayList<Integer>();
		String[] pro_ids = ids.split(",");
		if(!pro_ids[0].equals("")){
			for(int i=0;i<pro_ids.length;i++)
				product_ids.add(Integer.parseInt(pro_ids[i]));
		}
		dao.updateOrderBatch(product_ids, ticket_id);
	}

}