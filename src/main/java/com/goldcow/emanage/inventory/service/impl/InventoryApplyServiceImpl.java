package com.goldcow.emanage.inventory.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldcow.emanage.inventory.persist.InventoryApplyDao;
import com.goldcow.emanage.inventory.service.InventoryApplyService;
import com.goldcow.emanage.stock.persist.StockInfoDao;
import com.goldcow.emanage.util.gen.entity.Inventory;
import com.goldcow.emanage.util.gen.entity.StockInfo;
import com.goldcow.emanage.util.gen.entity.SysUser;
import com.goldcow.emanage.util.gen.entity.valueObject.inventory.InventoryVO;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Maps;

@Service
public class InventoryApplyServiceImpl implements InventoryApplyService {
	private static Logger log = LoggerFactory.getLogger(InventoryApplyServiceImpl.class);

	@Autowired
	private InventoryApplyDao dao;
	@Autowired
	private StockInfoDao stockInfodao;

	@Override
	public Inventory add(Inventory bean, HttpServletRequest request) {
		log.debug("新增损益清单");
		//bean.setDepartment_id(SysUtil.getSqlLikeParam(bean.getDepartment_id());
		bean.setInventory_apply_time(new Date());
		bean.setInventory_check_time(new Date());
		bean.setInventory_date(new Date());
		bean.setInventory_handleTime(new Date());
		bean.setInventory_check_status(1);
		
		/*Integer stock_info_id =  bean.getStock_info_id();
		StockInfo stockInfobean = stockInfodao.get(stock_info_id);
		System.out.println(stockInfobean.getStock_storageNumber());
		Double  profitLossNum= bean.getInventory_countedNumber()-stockInfobean.getStock_storageNumber();
		bean.setInventory_profitLossNumber(profitLossNum);*/
		
		dao.add(bean);
		return bean; 
	}

	@Override
	public void delete(Integer id, HttpServletRequest request) {
		log.debug("删除库存");
		dao.delete(id);
		log.debug("删除库存成功 => id : " + id);
	}

	@Override
	public Inventory update(Inventory bean, HttpServletRequest request) {
		log.debug("校对库存");
		dao.update(bean);
		return bean;
	}

	@Override
	public Inventory getById(Integer id) {
		log.debug("获取库房小号ID： " + id);
		return dao.get(id);
	}
	

	@Override
	public Map<String, Object> list(InventoryVO bean) {
		log.debug("查询列表");
		List<InventoryVO> list = dao.getInventoryApplyList(bean);
		int count = dao.getInventoryCount(bean);
		// 封装分页对象
		Map<String, Object> result = Maps.newHashMap();
		result.put("total", count);
		result.put("rows", list);
		return  result;	
	}

	@Override
	public InventoryVO getInverntoryVOByID(Integer id) {
		// TODO Auto-generated method stub
		return dao.getInverntoryVOByID(id);
	}

	 


}