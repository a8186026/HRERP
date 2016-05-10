package com.goldcow.emanage.supply.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldcow.emanage.supply.persist.SupInfoManageDao;
import com.goldcow.emanage.supply.service.ISupInfoManageService;
import com.goldcow.emanage.util.gen.SysTools;
import com.goldcow.emanage.util.gen.entity.SupInfoManage;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Maps;
/**
 * 供方客户信息
 * 
 * @author wangqi
 * @version v1.0
 * @since 2015-6-8
 */

@Service
public class SupInfoManageServiceImpl implements ISupInfoManageService {
	private static Logger log = LoggerFactory.getLogger(SupInfoManageServiceImpl.class);
	/** 供方客户信息操作  */
	@Autowired
	private SupInfoManageDao dao;

	/**
	 * 根据ID查询
	 * 
	 * @param id ID
	 * @return 供方客户信息信息
	 */
	@Override
	public SupInfoManage getById(Integer id) {
		log.debug("取得供方客户信息 => ID : " + id);
		return dao.get(id);
	}

	/**
	 * 查询供方客户信息列表-分页
	 * 
	 * @param bean 查询条件
	 * @return 供方客户信息列表
	 */
	@Override
	public Map<String, Object> list(SupInfoManage bean){	
		log.debug("查询供方信息列表");		
		
		//插入查询条件-供方客户信息编码
		bean.setSup_code(SysUtil.getSqlLikeParam(bean.getSup_code())); 
		bean.setSup_name(SysUtil.getSqlLikeParam(bean.getSup_name()));
		bean.setSup_shortname(SysUtil.getSqlLikeParam(bean.getSup_shortname()));
		bean.setSup_chnpy(SysUtil.getSqlLikeParam(bean.getSup_chnpy()));
		
		List<SupInfoManage> list = dao.list(bean);
		int count = dao.count(bean);

		// 封装分页对象
		Map<String, Object> result = Maps.newHashMap();
		result.put("total", count);
		result.put("rows", list);
				
		return result;
	}
	
	/**
	 * 供方客户信息
	 * 
	 * @param bean 供方客户信息
	 * @param request HttpServletRequest
	 * @return 供方信息
	 */
	@Override
	public SupInfoManage add(SupInfoManage bean, HttpServletRequest request) {
		
		log.debug("新增供方信息");
		SysUtil.checkInput(bean);

		//bean.setSup_fillingdate(new Date());
		//bean.setSup_paydate(new Date());
		//bean.setSup_pmtvaliddate(new Date());
		//bean.setSup_bnlsanndeaddate(new Date());
		//bean.setSup_bnlsvaliddate(new Date());
		//bean.setSup_qltycatevaliddate(new Date());
		//bean.setSup_letvaliddate(new Date());
		//bean.setSup_clkvaliddate(new Date());
		//bean.setSup_checkdate(new Date());
		//System.out.println("#################sup_code:"+bean.getSup_code());
		if(bean.getSup_shouldpay()==null){
			bean.setSup_shouldpay(Double.parseDouble("0"));
		}
		if(bean.getSup_advancepay()==null){
			bean.setSup_advancepay(Double.parseDouble("0"));	
		}
		if(bean.getSup_bond()==null){
			bean.setSup_bond(Double.parseDouble("0"));
		}
		if(bean.getSup_bnlsregistmoney()==null){
			bean.setSup_bnlsregistmoney(Double.parseDouble("0"));
		}
		
		bean.setSup_lastpurchasedate(new Date());		
		bean.setStatus(GlobalVal.RECORD_STATUS.ENABLE);
		bean.setCreate_user(SysUtil.getLoginUserId(request));
		bean.setCreate_time(new Date()); 
		bean.setLast_modify_user(SysUtil.getLoginUserId(request));
		bean.setLast_modify_time(new Date());	
		dao.add(bean);
		log.debug("新增供方信息成功 => id : " + bean.getSup_id());
		return bean;
	}

	/**
	 * 修改供方客户信息信息
	 * 
	 * @param bean 供方客户信息信息
	 * @param brand_id ID
	 * @return 供方客户信息信息
	 */
	@Override
	public SupInfoManage update(SupInfoManage bean, HttpServletRequest request) {
		log.debug("修改供方客户信息");
		SysUtil.checkInput(bean);
		bean.setLast_modify_user(SysUtil.getLoginUserId(request)); 
		bean.setLast_modify_time(new Date()); 
		SupInfoManage sim = dao.get(bean.getSup_id());
		//dao.addlog(sim);
		dao.update(bean);
		log.debug("修改供方客户信息成功 => id : " + bean.getSup_id());
		return bean;
	}

	/**
	 * 删除供方客户信息
	 * 
	 * @param brand_id ID
	 * @param request HttpServletRequest
	 */
	@Override
	public void delete(Integer id, HttpServletRequest request) {
		
		log.debug("删除供方信息");
		
		SupInfoManage bean = new SupInfoManage();
		
		bean.setSup_id(id);
		bean.setStatus(GlobalVal.RECORD_STATUS.DELETED);
		bean.setLast_modify_user(SysUtil.getLoginUserId(request)); 
		bean.setLast_modify_time(new Date()); 
		
		log.debug("删除供方信息成功 => ID : " + id);
		dao.update(bean);
	}

	@Override
	public List<SupInfoManage> lists(SupInfoManage bean) {
		// TODO Auto-generated method stub
		return dao.list(bean);
	}
}