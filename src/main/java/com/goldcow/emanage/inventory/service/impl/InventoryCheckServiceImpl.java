package com.goldcow.emanage.inventory.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldcow.emanage.inventory.persist.InventoryCheckDao;
import com.goldcow.emanage.inventory.service.InventoryCheckService;
import com.goldcow.emanage.stock.persist.StockInfoDao;
import com.goldcow.emanage.util.gen.entity.StockInfo;
import com.goldcow.emanage.util.gen.entity.valueObject.inventory.InventoryVO;
import com.google.common.collect.Maps;
/**
 * 盘点损益申请
 * 
 * @author zhanxiaotong
 * @version v1.0
 * @since 2015-12-3
 */
@Service
public class InventoryCheckServiceImpl implements InventoryCheckService {
	private static Logger log = LoggerFactory.getLogger(InventoryCheckServiceImpl.class);

	@Autowired
	private InventoryCheckDao dao;
	@Autowired
	private StockInfoDao stockInfodao;

	@Override
	public InventoryVO add(InventoryVO bean, HttpServletRequest request) {
		return null;
	}

	@Override
	public void delete(Integer id, HttpServletRequest request) {
		
	}

	@Override
	public InventoryVO update(InventoryVO bean, HttpServletRequest request) {
		return null;
	}

	@Override
	public InventoryVO getById(Integer id) {
		return null;
	}

	@Override
	public Map<String, Object> Checklist(InventoryVO bean) {
		log.debug("查询列表");
		List<InventoryVO> list = dao.getCheckList(bean);
		int count = dao.count(bean);
		// 封装分页对象
		Map<String, Object> result = Maps.newHashMap();
		result.put("total", count);
		result.put("rows", list);
		return  result;
	}

	@Override
	public void checked(Integer inventory_id, HttpServletRequest request) {
		log.debug("审核损益");
		InventoryVO bean = dao.get(inventory_id);
		bean.setInventory_check_status(0);
	//	bean.setInventory_check_person(SysUtil.getLoginUserId(request));
		bean.setInventory_check_time(new Date());
		//dao.update(bean);
		Double lostNum = bean.getInventory_profitLossNumber();
		if(lostNum != null && lostNum != 0){
			Double sum;
			Integer stock_info_id = bean.getStock_info_id();
			StockInfo stockInfobean = stockInfodao.get(stock_info_id);
			sum =  stockInfobean.getStock_storageNumber() + lostNum;
			stockInfobean.setStock_storageNumber(sum);
			stockInfodao.update(stockInfobean);
		}
		dao.update(bean);
	}
}