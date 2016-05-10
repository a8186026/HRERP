package com.goldcow.emanage.wholeSale.service.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldcow.emanage.stock.persist.StockInfoDao;
import com.goldcow.emanage.stock.service.impl.StockInfoServiceImpl;
import com.goldcow.emanage.util.gen.entity.StockInfo;
import com.goldcow.emanage.util.gen.entity.WholeSaleOrder;
import com.goldcow.emanage.wholeSale.persist.WholeSaleOrderDao;
import com.goldcow.emanage.wholeSale.service.IWholeSaleOrderService;
import com.goldcow.sframe.util.GlobalVal;
import com.google.common.collect.Maps;

@Service
public class WholeSaleOrderServiceImpl implements IWholeSaleOrderService {
	private static Logger log = LoggerFactory.getLogger(WholeSaleOrderServiceImpl.class);
	/** 订单操作 */
	@Autowired
	private WholeSaleOrderDao dao;
	
	@Override
	public WholeSaleOrder getById(Integer id) {
		log.debug("获取订单ID： " + id);
		return dao.get(id);
	}
	
	@Override
	public Map<String, Object> list(WholeSaleOrder bean) {
		log.debug("查询列表");
		
		
		List<WholeSaleOrder> list = dao.list(bean);
		int count = dao.count(bean);
		// 封装分页对象
		Map<String, Object> result = Maps.newHashMap();
		result.put("total", count);
		result.put("rows", list);
		return  result;	
	}
	
	@Override
	public WholeSaleOrder add(WholeSaleOrder bean, HttpServletRequest request) {
		log.debug("新增订单");
	    
		dao.add(bean);
		log.debug("新增订单成功 => id : " + bean.getWholeSale_order_id());
		return bean;
	}
	@Override
	public void delete(Integer id, HttpServletRequest request) {
		log.debug("删除订单");
		dao.delete(id);
		log.debug("删除订单成功 => id : " + id);
	}
	@Override
	public WholeSaleOrder update(WholeSaleOrder bean, HttpServletRequest request) {
		log.debug("修改订单");
		dao.update(bean);
		log.debug("修改订单成功 => id : " + bean.getWholeSale_order_id());
		return bean;
	}
}
