package com.goldcow.emanage.purchase.service.impl;


import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldcow.emanage.purchase.persist.PurStockAcceptanceCheckDao;
import com.goldcow.emanage.purchase.service.IPurStockAcceptanceCheckService;
import com.goldcow.emanage.util.gen.entity.PurAcceptCheck;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Maps;
import com.goldcow.sframe.util.GlobalVal;

@Service
public class PurStockAcceptanceCheckServiceImpl implements IPurStockAcceptanceCheckService {
	private static Logger log = LoggerFactory.getLogger(PurStockAcceptanceCheckServiceImpl.class);

	@Autowired
	private PurStockAcceptanceCheckDao dao;
	
	/**
	 * 根据ID查询
	 * 
	 * @param id ID
	 * @return 库房收货确认信息
	 */
	@Override
	public PurAcceptCheck getById(Integer id) {
		log.debug("取得库房收货确认 => ID : " + id);
		return dao.get(id);
	}

	/**
	 * 查询库房收货确认列表-分页
	 * 
	 * @param bean 查询条件
	 * @return 库房收货确认列表
	 */
	@Override
	public Map<String, Object> list(PurAcceptCheck bean){	
		log.debug("查询库房收货确认列表");		
		
		//插入查询条件-库房收货确认编码
		bean.setDepartment_id(bean.getDepartment_id());
		bean.setAccept_groupNo(bean.getAccept_groupNo());
		
		List<PurAcceptCheck> list = dao.list(bean);
		int count = dao.count(bean);
		
		// 封装分页对象
		Map<String, Object> result = Maps.newHashMap();
		result.put("total", count);
		result.put("rows", list);
				
		return result;
	}
	
	@Override
	public PurAcceptCheck update(PurAcceptCheck bean, HttpServletRequest request) {
		log.debug("库房收货确认");
		SysUtil.checkInput(bean);
//		bean.setLast_modify_user(SysUtil.getLoginUserId(request)); 
//		bean.setLast_modify_time(new Date()); 
		bean.setAccept_checkStockTime(new Date());
		bean.setAccept_checkStockPerson(SysUtil.getLoginUser(request).getUser_name());
		log.debug("bean.getAccept_checkStockTime()=>  : " + bean.getAccept_checkStockTime());
		log.debug("bean.getAccept_checkStockPerson=>  : " + bean.getAccept_checkStockPerson());
		log.debug("bean.getAccept_checkStockStatus=>  : " + bean.getAccept_stockStatus());
		dao.update(bean);
//		log.debug("修改库房收货确认成功 => id : " + bean.getRecepit_id());
		return bean;
	}

@Override
public PurAcceptCheck add(PurAcceptCheck bean, HttpServletRequest request) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public void delete(Integer id, HttpServletRequest request) {
	// TODO Auto-generated method stub
	
}

}