package com.goldcow.emanage.purchase.service.impl;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldcow.emanage.purchase.persist.PurOrderDao;
import com.goldcow.emanage.purchase.persist.PurOrderListDao;
import com.goldcow.emanage.purchase.service.IPurOrderListService;
import com.goldcow.emanage.purchase.service.IPurOrderService;
import com.goldcow.emanage.util.gen.SysTools;
import com.goldcow.emanage.util.gen.entity.PurOrder;
import com.goldcow.emanage.util.gen.entity.PurOrderList;
import com.goldcow.emanage.util.gen.entity.valueObject.purchase.PurOrderListAndProInfo;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Maps;

@Service
public class PurOrderServiceImpl implements IPurOrderService {
	private static Logger log = LoggerFactory.getLogger(PurOrderServiceImpl.class);

	@Autowired
	private PurOrderDao dao;
	@Autowired
	private PurOrderListDao purOrderListDao;
	@Autowired
	private IPurOrderListService purorderservice;
	
	
	@Override
	public PurOrder add(PurOrder bean, HttpServletRequest request) {
		// TODO Auto-generated method stub
		log.debug("新增订单");
		SysUtil.checkInput(bean);
		bean.setStatus(GlobalVal.RECORD_STATUS.ENABLE);
		bean.setGoodsReceive(GlobalVal.ACCEPT_STATUS.UNRECEIVE);
		bean.setCheckStatus(GlobalVal.ACCEPT_STATUS.ORDER_UNCHECKED);
		bean.setCreate_user(SysUtil.getLoginUserId(request));
		bean.setCreate_time(new Date());
		bean.setLast_modify_user(SysUtil.getLoginUserId(request));
		bean.setLast_modify_time(new Date());
		
		//初始化Date数据
		bean.setOrderDate(new Date());
		bean.setCheckTime(new Date());
		bean.setGoodsReceiveTime(new Date());
		bean.setMakeoutInvoiceTime(new Date());
		bean.setArrivalDate(new Date());
		bean.setOrderDepartureTime(new Date());
		
		dao.add(bean); 
		log.debug("新增订单成功 => id : " + bean.getId());
		dao.addlog(bean);
		log.debug("新增记录插入日志成功 => id : " + bean.getId());

		return bean;
	}

	@Override
	public void delete(Integer id, HttpServletRequest request) {
		// TODO Auto-generated method stub
		PurOrder bean = this.getById(id);
		PurOrder pur = this.getById(bean.getId());
		

		List<PurOrderList> list = purOrderListDao.getlist(bean.getTicket_id());
		System.out.println("list.size()::::::"+list.size());
		for(int i=0;i<list.size();i++){
			System.out.println("list.get(i).getId()::::::::::"+list.get(i).getId());
			purorderservice.delete(list.get(i).getId(), null);
		}

		dao.addlog(pur);
		log.debug("修改记录插入日志成功 => id : " + pur.getId());
		
		dao.delete(id);
		log.debug("删除功能成功 => id : " + id);
	}
	/**
	 * 修改订单大单信息
	 * @bean 条件 
	 */
	@Override
	public PurOrder update(PurOrder bean, HttpServletRequest request) {
		log.debug("修改订单大单功能");
		// 汉字转码
	/*	bean.setOperator(SysTools.decode(bean.getOperator()));
		bean.setOrderabstract(SysTools.decode(bean.getOrderabstract()));
		bean.setKnotStyle(SysTools.decode(bean.getKnotStyle()));
		bean.setPolicyStyle(SysTools.decode(bean.getPolicyStyle()));
		bean.setRemarks(SysTools.decode(bean.getRemarks()));
		bean.setCarryMode(SysTools.decode(bean.getCarryMode()));
		bean.setOrderTransMode(SysTools.decode(bean.getOrderTransMode()));
		bean.setCarryCompany(SysTools.decode(bean.getCarryCompany()));
		bean.setDelivaryPlace(SysTools.decode(bean.getDelivaryPlace()));
		bean.setSupply_fullname(SysTools.decode(bean.getSupply_fullname()));
		*/
		
		SysUtil.checkInput(bean);
		
		//bean.setCreate_time(new Date());
		
		bean.setLast_modify_user(SysUtil.getLoginUserId(request));
		bean.setLast_modify_time(new Date());
		
		dao.update(bean);
		log.debug("修改订单大单功能成功 => id : " + bean.getId());
		
		
		PurOrder pur = this.getById(bean.getId());
		dao.addlog(pur);
		log.debug("修改记录插入日志成功 => id : " + pur.getId());
		
		return bean;
	}
	/**
	 * 订单审核修改订单大单信息
	 * @bean 条件 
	 */
	@Override
	public PurOrder checkUpdate(PurOrder bean, HttpServletRequest request) {
		log.debug("订单审核修改订单大单信息");
		
		SysUtil.checkInput(bean);
		
		
		bean.setLast_modify_user(SysUtil.getLoginUserId(request));
		bean.setLast_modify_time(new Date());
		
		dao.update(bean);
		log.debug("订单审核修改订单大单信息成功 => id : " + bean.getId());
		
		
		PurOrder pur = this.getById(bean.getId());
		dao.addlog(pur);
		log.debug("修改记录插入日志成功 => id : " + pur.getId());
		
		return bean;
	}
	
	@Override
	public PurOrder getById(Integer id) {
		log.debug("获取进货条目ID： " + id);
		return dao.get(id);
	}

	@Override
	public Map<String, Object> list(PurOrder bean) {
		log.debug("查询列表");
		
		bean.setTicket_id(SysUtil.getSqlLikeParam(bean.getTicket_id())); 
		
		List<PurOrder> list = dao.list(bean);
		//Integer length = list.size(); 
		for(int i =0;i<list.size();i++){
			String ticketId = list.get(i).getTicket_id();
			PurOrderList purOrderList = new PurOrderList();
			purOrderList.setTicket_id(ticketId);
			Integer num = purOrderListDao.count(purOrderList);
			if(num==0){
				dao.delete(list.get(i).getId());
				list.remove(i);
				i--;
			}
		}
		int count = list.size();

		// 封装分页对象
		Map<String, Object> result = Maps.newHashMap();
		result.put("total", count);
		result.put("rows", list);
		
		return result;
	}
	//不分页
	@Override
	public List<PurOrder> getList(PurOrder bean) {
		return dao.list(bean);
	}


	
	/**
	 * 获取最大票号
	 * */
	@Override
	public String getMaxTicketID() {
		// TODO Auto-generated method stub
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String date = dateFormat.format(new Date());
        StringBuilder out  = new StringBuilder(date);
		Integer ticketID =  dao.getMaxTicketID(date);
		if(ticketID != null){
			out.append(String.format("%04d",ticketID+1));
		}
		else{
			out.append("0001");
		}
		return out.toString();
	}

	/**
	* 查询订单小单关联产品
	* @param bean 查询条件
	* @return 订单小单关联产品列表
	* @author RiverYao 2015-07-15
	*/
	@Override
	public Map<String, Object> getPurOrderListAndProductInfo(PurOrder bean) {
		List<PurOrderListAndProInfo> list = dao.getPurOrderListAndProductInfo(bean);
		int count = dao.count(bean);
		// 封装分页对象
		Map<String, Object> result = Maps.newHashMap();
		result.put("total", count);
		result.put("rows", list);
		return result;
	}

	/**
	* 查询订单小单关联产品
	* @param bean 查询条件
	* @return 订单小单关联产品列表
	* @author RiverYao 2015-07-15
	*/
	@Override
	public Map<String, Object> getPurOrderListAndProductInfoForStaright(PurOrderList bean) {
		List<PurOrderListAndProInfo> list = dao.getPurOrderListAndProductInfoFor(bean);
		int count = purOrderListDao.count(bean);
		// 封装分页对象
		Map<String, Object> result = Maps.newHashMap();
		result.put("total", count);
		result.put("rows", list);
		return result;
	}
	/**
	 * 根据大单信息删除所有的大单以及小单信息
	 * @bean 条件 
	 */
	@Override
	public Boolean deletePurOrderAll(PurOrder bean) {
		// 删除大单信息
		dao.deletePurderByBean(bean);
		// 删除小单信息
		List<PurOrderListAndProInfo> list = dao.getPurOrderListAndProductInfo(bean);
		/*for(int i=0;i<list.size();i++){
			
		}*/
		PurOrderList purOrderListBean = new PurOrderList();
		purOrderListBean.setTicket_id(bean.getTicket_id());
		purOrderListDao.deletePurderListByBean(purOrderListBean);
		return true;
	}
	
	
	/**
	 * 查询需要进行订单审核的大单信息
	 * @bean 条件 
	 */
	@Override
	public Map<String, Object> listCheckOrder(PurOrder bean) {
		log.debug("查询列表");
		List<PurOrder> list = dao.listCheckOrder(bean);
		
		int count = list.size();
		// 封装分页对象
		Map<String, Object> result = Maps.newHashMap();
		result.put("total", count);
		result.put("rows", list);
		
		return result;
	}
	/**
	 * 查询通过订单审核 需要进行收货的大单信息
	 * @bean 条件 
	 */
	@Override
	public Map<String, Object> listRecepitOrder(PurOrder bean) {
		log.debug("查询列表");
		List<PurOrder> list = dao.listRecepitOrder(bean);
		
		int count = list.size();
		// 封装分页对象
		Map<String, Object> result = Maps.newHashMap();
		result.put("total", count);
		result.put("rows", list);
		
		return result;
	}
}