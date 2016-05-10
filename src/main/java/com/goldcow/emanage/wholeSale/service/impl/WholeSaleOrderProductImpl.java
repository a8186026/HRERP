package com.goldcow.emanage.wholeSale.service.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldcow.emanage.util.gen.entity.RetailOrderProduct;
import com.goldcow.emanage.util.gen.entity.WholeSaleOrderProduct;
import com.goldcow.emanage.util.gen.entity.valueObject.retail.RetailOrderVO;
import com.goldcow.emanage.wholeSale.persist.WholeSaleOrderProductDao;
import com.goldcow.emanage.wholeSale.service.IWholeSaleOrderProductService;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Maps;

@Service
public class WholeSaleOrderProductImpl implements IWholeSaleOrderProductService{
	private static Logger log = LoggerFactory.getLogger(WholeSaleOrderProductImpl.class);
	/** 零售订单产品信息操作 */
	@Autowired
	private WholeSaleOrderProductDao dao;


	/**
	 * 根据ID查询
	 * 
	 * @param id ID
	 * @return 零售订单产品
	 */
	@Override
	public WholeSaleOrderProduct getById(Integer id) {
		log.debug("取得零售订单产品信息 => ID : " + id);
		return dao.get(id);
	}

	/**
	 * 查询零售订单产品信息列表
	 * 
	 * @param bean 查询条件
	 * @return 零售订单产品信息列表
	 */
	@Override
	public Map<String, Object> list(WholeSaleOrderProduct bean){	
		log.debug("查询零售订单产品信息信息列表");		

		List<WholeSaleOrderProduct> list = dao.list(bean);
		int count = dao.count(bean);

		// 封装分页对象
		Map<String, Object> result = Maps.newHashMap();
		result.put("total", count);
		result.put("rows", list);
		return result;
	}

	/**
	 * 新增零售订单产品信息
	 * 
	 * @param bean 零售订单产品信息
	 * @return 零售订单产品信息
	 */
	@Override
	public WholeSaleOrderProduct add(WholeSaleOrderProduct bean, HttpServletRequest request) {
		log.debug("新增零售订单产品信息");
		SysUtil.checkInput(bean);
		dao.add(bean);
		log.debug("新增零售订单产品信息成功 => id : " + bean.getWholeSale_order_product_id());
		return bean;
	}

	/**
	 * 修改零售订单产品
	 * 
	 * @param bean 零售订单产品信息
	 * @return 零售订单产品信息
	 */
	@Override
	public WholeSaleOrderProduct update(WholeSaleOrderProduct bean, HttpServletRequest request) {
		log.debug("修改零售订单产品信息");
		SysUtil.checkInput(bean);
		dao.update(bean);
		log.debug("修改零售订单产品信息成功 => id : " + bean.getWholeSale_order_product_id());
		return bean;
	}

	/**
	 * 删除零售订单产品信息
	 * 
	 * @param id ID
	 */
	@Override
	public void delete(Integer id, HttpServletRequest request) {
		log.debug("删除零售订单产品信息");
		System.out.println("id++++++++++++++++++++++++++++"+id);
		dao.delete(id);
		log.debug("删除零售订单产品信息成功 => id : " + id);
	}

/*	@Override
	public RetailOrderVO addVO(RetailOrderVO bean,Double number) {
		RetailOrderProduct rop = new RetailOrderProduct();
		rop.setRetail_order_ticketId(bean.getRetail_order_ticketId());
		rop.setOrder_productId(this.getMaxOrderProductId(bean.getRetail_order_ticketId()));
		rop.setProduct_id(bean.getProduct_id());
		rop.setProduct_code(bean.getProduct_code());
		rop.setProduct_name(bean.getProduct_name());
		rop.setProduct_specification(bean.getProduct_specification());
		rop.setProduct_productarea(bean.getProduct_productarea());
		rop.setProduct_unit(bean.getProduct_unit());
		rop.setProduct_packingamount(bean.getProduct_packingamount());
		rop.setProduct_dosagetype(bean.getProduct_dosagetype());
		rop.setProduct_category(bean.getProduct_category());
		rop.setProduct_stocknum(bean.getStock_storageNumber());
		rop.setProduct_tradeprice(bean.getProduct_tradeprice());
		rop.setProduct_retailprice(bean.getProduct_retailprice());
		rop.setProduct_marketprice(bean.getProduct_marketprice());
		rop.setProduct_lowprice(bean.getProduct_lretaillprice());
		rop.setProduct_saleprice1(bean.getProduct_saleprice1());
		rop.setProduct_saleprice2(bean.getProduct_saleprice2());
		rop.setProduct_saleprice3(bean.getProduct_saleprice3());
		rop.setProduct_saleprice4(bean.getProduct_saleprice4());
		rop.setProduct_lastprice(bean.getProduct_lastprice());
		rop.setOrder_product_quantity(number);
		rop.setOrder_product_unitPrice(bean.getOrder_product_unitPrice());
		rop.setOrder_product_cutDifference(bean.getMemDescCost());
		rop.setOrder_product_amount(bean.getOrder_product_unitPrice()*number);
		rop.setOrder_product_grossProfit(bean.getProduct_retailprice()-bean.getProduct_lastprice());		//毛利
		rop.setOrder_product_storage(bean.getStock_storage());
		rop.setOrder_product_discountAmount(bean.getOrder_product_discountAmount());
		rop.setOrder_product_saleId(bean.getOrder_product_saleId());
		rop.setOrder_product_saleType(bean.getOrder_product_saleType());
		rop.setOrder_product_singlePayQuantity(bean.getOrder_product_singlePayQuantity());
		rop.setOrder_product_receive(0);
		rop.setOrder_product_saleType(bean.getOrder_product_saleType());
		
		dao.add(rop);
		log.debug("增加零售订单产品信息成功 => id : " + rop.getRetail_order_product_id());
		bean.setOrder_productId(rop.getOrder_productId());
		return bean;
		
	}
	
	
	*//** 获得当前销售订单最大的Product序号
	 * @param 订单票号
	 * @return 返回最大序号
	 * *//*
	public Integer getMaxOrderProductId(String ticketId){
		Integer order_productId = dao.getMaxOrderProductId(ticketId);
		if(order_productId == null){
			order_productId = 1;
		}else
			order_productId++;
		return order_productId;
	}
	
	*//** 获得当前销售订单产品包含的批次数量
	 * @param 订单票号
	 * @param 订单产品序号
	 * @return 数量
	 * *//*
	public Integer countBatch(String retail_order_ticketId,Integer order_productId){
		return dao.countBatch(retail_order_ticketId,order_productId);
		
	}*/
}