package com.goldcow.emanage.basInfo.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldcow.emanage.basInfo.persist.BasAccountDao;
import com.goldcow.emanage.basInfo.service.IBasAccountService;
import com.goldcow.emanage.util.gen.SysTools;
import com.goldcow.emanage.util.gen.entity.BasAccount;
import com.goldcow.emanage.util.gen.entity.BasFactoryInfo;
import com.goldcow.emanage.util.gen.entity.SupInfoManage;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Maps;
/**
 * 财务信息
 * 
 * @author wangqi
 * @version v1.0
 * @since 2015-7-1
 */

@Service
public class BasAccountServiceImpl implements IBasAccountService {
	private static Logger log = LoggerFactory.getLogger(BasFactoryInfoServiceImpl.class);
	/** 财务信息操作  */
	@Autowired
	private BasAccountDao dao;

	/**
	 * 根据ID查询
	 * 
	 * @param id ID
	 * @return 财务信息
	 */
	@Override
	public BasAccount getById(Integer id) {
		log.debug("取得财务信息 => ID : " + id);
		return dao.get(id);
	}
	/**
	 * 查询供方客户信息列表-分页
	 * 
	 * @param bean 查询条件
	 * @return 供方客户信息列表
	 */
	@Override
	public Map<String, Object> lists(BasAccount bean){	
		log.debug("查询供方信息列表");		
		
		//插入查询条件-供方客户信息编码
		bean.setAcc_no(SysUtil.getSqlLikeParam(bean.getAcc_no())); 
		bean.setAcc_name(SysUtil.getSqlLikeParam(bean.getAcc_name()));
		bean.setAcc_bank(SysUtil.getSqlLikeParam(bean.getAcc_bank()));
		
		List<BasAccount> list = dao.lists(bean);
		int count = dao.count(bean);

		// 封装分页对象
		Map<String, Object> result = Maps.newHashMap();
		result.put("total", count);
		result.put("rows", list);
				
		return result;
	}
	/**
	 * 新增财务信息信息
	 * 
	 * @param bean 财务信息信息
	 * @param request HttpServletRequest
	 * @return 财务信息信息
	 */
	@Override
	public BasAccount add(BasAccount bean, HttpServletRequest request) {
		
		log.debug("新增财务信息");
		SysUtil.checkInput(bean);
		bean.setAcc_bond(Float.parseFloat("0"));
		bean.setStatus(GlobalVal.RECORD_STATUS.ENABLE);
		bean.setCreate_user(SysUtil.getLoginUserId(request));
		bean.setCreate_time(new Date()); 
		bean.setLast_modify_user(SysUtil.getLoginUserId(request));
		bean.setLast_modify_time(new Date());	
		dao.add(bean);
		log.debug("新增财务信息成功 => id : " + bean.getAcc_id());
		return bean;
	}

	/**
	 * 修改财务信息信息
	 * 
	 * @param bean 财务信息信息
	 * @param brand_id ID
	 * @return 财务信息信息
	 */
	@Override
	public BasAccount update(BasAccount bean, HttpServletRequest request) {		
		log.debug("修改财务信息");

		SysUtil.checkInput(bean);
		bean.setLast_modify_user(SysUtil.getLoginUserId(request)); 
		bean.setLast_modify_time(new Date()); 

		dao.update(bean);
		log.debug("修改财务信息成功 => id : " + bean.getAcc_id());
		return bean;
	}

	/**
	 * 删除财务信息
	 * 
	 * @param brand_id ID
	 * @param request HttpServletRequest
	 */
	@Override
	public void delete(Integer id, HttpServletRequest request) {
		
		log.debug("删除财务信息");
		
		BasAccount bean = new BasAccount();
		
		bean.setAcc_id(id);
		bean.setStatus(GlobalVal.RECORD_STATUS.DELETED);
		bean.setLast_modify_user(SysUtil.getLoginUserId(request)); 
		bean.setLast_modify_time(new Date()); 
		
		log.debug("删除财务信息成功 => ID : " + id);
		dao.update(bean);
	}

}