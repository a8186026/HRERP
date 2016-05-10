package com.goldcow.emanage.basInfo.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldcow.emanage.basInfo.persist.EthicalPersonDao;
import com.goldcow.emanage.basInfo.service.IEthicalPersonService;
import com.goldcow.emanage.util.gen.entity.BasAccount;
import com.goldcow.emanage.util.gen.entity.EthicalPerson;
import com.goldcow.sframe.util.DecriptUtil;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Maps;
/**
 * 处方药人员管理
 * 
 * @author wubin
 * @version v1.0
 * @since 2015-12-30
 */

@Service
public class EthicalPersonServiceImpl implements IEthicalPersonService {
	private static Logger log = LoggerFactory.getLogger(EthicalPersonServiceImpl.class);
	/** 处方药人员信息操作  */
	@Autowired
	private EthicalPersonDao dao;

	/**
	 * 根据ID查询
	 * 
	 * @param id ID
	 * @return 处方药人员信息
	 */
	@Override
	public EthicalPerson getById(Integer id) {
		log.debug("取得处方药人员信息 => ID : " + id);
		return dao.get(id);
	}
	/**
	 * 查询处方药人员信息列表-分页
	 * 
	 * @param bean 查询条件
	 * @return 处方药人员信息列表
	 */
	@Override
	public Map<String, Object> lists(EthicalPerson bean){	
		log.debug("查询处方药人员信息列表");		
		
		//插入查询条件-供方客户信息编码
	/*	bean.setAcc_no(SysUtil.getSqlLikeParam(bean.getAcc_no())); 
		bean.setAcc_name(SysUtil.getSqlLikeParam(bean.getAcc_name()));
		bean.setAcc_bank(SysUtil.getSqlLikeParam(bean.getAcc_bank()));*/
		
		List<EthicalPerson> list = dao.lists(bean);
		int count = dao.count(bean);

		// 封装分页对象
		Map<String, Object> result = Maps.newHashMap();
		result.put("total", count);
		result.put("rows", list);
				
		return result;
	}
	/**
	 * 新增处方药人员信息信息
	 * 
	 * @param bean 处方药人员信息
	 * @param request HttpServletRequest
	 * @return 处方药人员信息
	 */
	@Override
	public EthicalPerson add(EthicalPerson bean, HttpServletRequest request) {
		
		log.debug("新增处方药人员信息");
		SysUtil.checkInput(bean);
		//密码加密
		bean.setEthical_personPassword(DecriptUtil.SHA1(bean.getEthical_personPassword()));
		bean.setStatus(GlobalVal.RECORD_STATUS.ENABLE);
		bean.setCreate_user(SysUtil.getLoginUserId(request));
		bean.setCreate_time(new Date()); 
		bean.setLast_modify_user(SysUtil.getLoginUserId(request));
		bean.setLast_modify_time(new Date());	
		dao.add(bean);
		log.debug("新增处方药人员信息成功 => id : " + bean.getEthical_personId());
		return bean;
	}

	/**
	 * 修改处方药人员信息信息
	 * 
	 * @param bean 处方药人员信息信息
	 * @param brand_id ID
	 * @return 处方药人员信息
	 */
	@Override
	public EthicalPerson update(EthicalPerson bean, HttpServletRequest request) {		
		log.debug("修改处方药人员信息");

		SysUtil.checkInput(bean);
		bean.setLast_modify_user(SysUtil.getLoginUserId(request)); 
		bean.setLast_modify_time(new Date()); 

		dao.update(bean);
		log.debug("修改处方药人员信息成功 => id : " + bean.getEthical_personId());
		return bean;
	}

	/**
	 * 删除处方药人员信息
	 * 
	 * @param brand_id ID
	 * @param request HttpServletRequest
	 */
	@Override
	public void delete(Integer id, HttpServletRequest request) {
		
		log.debug("删除处方药人员信息");
		
		EthicalPerson bean = new EthicalPerson();
		
		bean.setEthical_personId(id);
		bean.setStatus(GlobalVal.RECORD_STATUS.DELETED);
		bean.setLast_modify_user(SysUtil.getLoginUserId(request)); 
		bean.setLast_modify_time(new Date()); 
		
		log.debug("删除处方药人员信息成功 => ID : " + id);
		dao.update(bean);
	}

}