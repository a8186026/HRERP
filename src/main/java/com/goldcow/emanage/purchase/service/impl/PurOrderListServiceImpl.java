package com.goldcow.emanage.purchase.service.impl;



import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldcow.emanage.purchase.persist.PurOrderListDao;
import com.goldcow.emanage.purchase.service.IPurOrderListService;
import com.goldcow.emanage.util.gen.entity.PurOrderList;
import com.goldcow.emanage.util.gen.entity.valueObject.purchase.PurOrderListAndProInfo;
import com.goldcow.emanage.util.gen.entity.valueObject.purchase.PurOrderListVO;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Maps;

@Service
public class PurOrderListServiceImpl implements IPurOrderListService {
	private static Logger log = LoggerFactory.getLogger(PurOrderListServiceImpl.class);

	@Autowired
	private PurOrderListDao dao;
	
	@Override
	public PurOrderList add(PurOrderList bean, HttpServletRequest request) {
		// TODO Auto-generated method stub
		log.debug("新增订单");
		SysUtil.checkInput(bean);
		bean.setStatus(GlobalVal.RECORD_STATUS.ENABLE);
		bean.setCreate_user(SysUtil.getLoginUserId(request));
		bean.setCreate_time(new Date());
		bean.setLast_modify_user(SysUtil.getLoginUserId(request));
		bean.setLast_modify_time(new Date());
		bean.setArrivalNumber(0.0);
		bean.setIsArrival(GlobalVal.ACCEPT_STATUS.UNARRAIVAL);
		//初始化时间和浮点数
		bean.setArrivalDate(new Date());
		if(bean.getUnitprice()==null)
			bean.setUnitprice(new Double(0));
		if(bean.getSettlementPrice()==null)
			bean.setSettlementPrice(new Double(0));
		if(bean.getReferencePrice()==null)
			bean.setReferencePrice(new Double(0));
		if(bean.getOrderSettlementPrice()==null)
			bean.setOrderSettlementPrice(new Double(0));
		
		
		dao.add(bean);
		log.debug("新增订单成功 => id : " + bean.getId());
		dao.addlog(bean);
		log.debug("新增记录插入日志成功 => id : " + bean.getId());
		return bean;
	}
	@Override
	public PurOrderList addForStraight(PurOrderList bean, HttpServletRequest request) {
		// TODO Auto-generated method stub
		log.debug("新增直接入库订单");
		SysUtil.checkInput(bean);
		bean.setStatus(GlobalVal.RECORD_STATUS.ENABLE);
	
		bean.setArrivalNumber(0.0);
		bean.setIsArrival(GlobalVal.ACCEPT_STATUS.UNARRAIVAL);
		//初始化时间和浮点数
		
		if(bean.getUnitprice()==null)
			bean.setUnitprice(new Double(0));
		if(bean.getSettlementPrice()==null)
			bean.setSettlementPrice(new Double(0));
		if(bean.getReferencePrice()==null)
			bean.setReferencePrice(new Double(0));
		if(bean.getOrderSettlementPrice()==null)
			bean.setOrderSettlementPrice(new Double(0));
		
		
		dao.add(bean);
		log.debug("新增订单成功 => id : " + bean.getId());
		dao.addlog(bean);
		log.debug("新增记录插入日志成功 => id : " + bean.getId());
		return bean;
	}
	@Override
	public void delete(Integer id, HttpServletRequest request) {
		// TODO Auto-generated method stub
		PurOrderList bean = this.getById(id);
		
		dao.addlog(bean);
		log.debug("删除记录插入日志成功 => id : " + id);
		dao.delete(id);
		log.debug("删除功能成功 => id : " + id);
		
	}

	@Override
	public PurOrderList update(PurOrderList bean, HttpServletRequest request) {
		log.debug("修改功能");
		SysUtil.checkInput(bean);
		bean.setLast_modify_user(SysUtil.getLoginUserId(request));
		bean.setLast_modify_time(new Date());
		dao.update(bean);
		log.debug("修改功能成功 => id : " + bean.getId());
		dao.addlog(bean);
		log.debug("修改记录插入日志成功 => id : " + bean.getId());
		return bean;
	}

	@Override
	public PurOrderList getById(Integer id) {
		log.debug("获取进货条目ID： " + id);
		return dao.get(id);
	}

	@Override
	public List<PurOrderList> list(PurOrderList bean) {
		log.debug("查询列表");
		List<PurOrderList> list = dao.list(bean);
		return list;
	}
	
	/**
	 * 查询最近某产品三次进货
	 * @param bean 查询条件
	 * @return 订单信息列表
	 */
	@Override
	public List<PurOrderListVO> listRecent(PurOrderList bean) {
		log.debug("查询最近某产品三次进货");
		
		List<PurOrderListVO> list = dao.listRecent(bean);
		return list;
	}
	@Override
	public List<PurOrderList> getOrderList(String id) {
		log.debug("查询子级订单");
		List<PurOrderList> list = dao.getlist(id);
		return list;
	}
	
	/**
	 * 查询通过订单审核 需要进行收货的小单信息
	 * @bean 条件 
	 */
	@Override
	public Map<String, Object> listCheckOrderList(PurOrderListAndProInfo bean) {
		log.debug("查询列表");
		List<PurOrderListAndProInfo> list = dao.listCheckOrderList(bean);
		
		int count = list.size();
		// 封装分页对象
		Map<String, Object> result = Maps.newHashMap();
		result.put("total", count);
		result.put("rows", list);
		
		return result;
	}
	
	/**
	 * 查询通过订单审核 需要进行收货的小单信息
	 * @bean 条件 
	 */
	@Override
	public Map<String, Object> listRecepitOrderList(PurOrderListAndProInfo bean) {
		log.debug("查询列表");
		List<PurOrderListAndProInfo> list = dao.listRecepitOrderList(bean);
		
		int count = list.size();
		// 封装分页对象
		Map<String, Object> result = Maps.newHashMap();
		result.put("total", count);
		result.put("rows", list);
		
		return result;
	}

	/**
	 * 查询小单中产品是否为特殊品种
	 * 
	 * */
	@Override
	public int getSpecialvariety(int product_id) {
		log.debug("查询小单中产品是否为特殊品种");
		return dao.getSpecialvariety(product_id);
	}
	
	
}