package com.goldcow.emanage.basInfo.service.impl;


import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldcow.emanage.basInfo.persist.BasCheckDao;
import com.goldcow.emanage.basInfo.service.IBasCheckSaleService;
import com.goldcow.emanage.sale.persist.SalInfoManageDao;
import com.goldcow.emanage.util.gen.entity.BasCheck;
import com.goldcow.emanage.util.gen.entity.BasCheckLog;
import com.goldcow.emanage.util.gen.entity.SalInfoManage;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;

@Service
public class BasCheckSaleServiceImpl implements IBasCheckSaleService {
	private static Logger log = LoggerFactory.getLogger(BasCheckSaleServiceImpl.class);
	/** 功能操作 */
	@Autowired
	private BasCheckDao checkSaleDao;
	
	@Autowired
	private SalInfoManageDao saleInfoDao;
	
	/**
	 * 根据ID查询销方
	 * 
	 * @param id ID
	 * @return 信息记录
	 */
	@Override
	public BasCheck getById(Integer id) {
		log.debug("通过id得到审批记录");
		return checkSaleDao.get(id);
	}
	
	/**
	 * 查询已有审批条目数量
	 * @param id 销方ID
	 * @return 数量
	 */
	@Override
	public Integer count(BasCheck bascheck) {
		log.debug("得到当前审批环节");
		return checkSaleDao.counts(bascheck);
	}
	
	 /**
	  * 查询审批列表
	  * @param bean 查询条件
	  * @return 查询结果list
	  */	
	@Override
	public List<BasCheck> getSalesCheck(BasCheck bean) {
		log.debug("查询审批列表");
		return checkSaleDao.list(bean);	
	}
	/**
	 * 查询所有销方
	 * @param bean 查询条件
	 * @return 查询结果List
	 */
	@Override
	public List<SalInfoManage> getSales() {
		System.out.println("getSales------");
		SalInfoManage bean = new SalInfoManage();
		return saleInfoDao.list(bean);
	}

	@Override
	public BasCheck add(BasCheck bean, HttpServletRequest request) {
		log.debug("新增审批");
		SysUtil.checkInput(bean);
		bean.setCheck_time(new Date());
		bean.setCheck_person_id(SysUtil.getLoginUserId(request));
		bean.setCheck_person_name(SysUtil.getLoginUser(request).getDisplay_name());
		bean.setStatus(GlobalVal.RECORD_STATUS.ENABLE);
		bean.setCreate_user(SysUtil.getLoginUserId(request));
		bean.setCreate_time(new Date());
		bean.setLast_modify_user(SysUtil.getLoginUserId(request));
		bean.setLast_modify_time(new Date());
		bean.setCheck_modified(0);//默认提交审批后不可修改
		System.out.println("000000"+bean.getCheck_comment_id());
		checkSaleDao.add(bean);
		
		log.debug("新增审核信息成功 => id : " + bean.getCheck_id());
		return bean;
	}
	
	/**
	 * 修改销方信息记录
	 * 
	 * @param bean 销方信息记录
	 * @param request HttpServletRequest
	 * @return 销方信息记录
	 */
	@Override
	public BasCheck update(BasCheck bean, HttpServletRequest request) {
		log.debug("修改功能");
		SysUtil.checkInput(bean);
		

		
		//更新前的审批bean
		BasCheck oldbean = checkSaleDao.get(bean.getCheck_id());
		System.out.println("check_id:"+bean.getCheck_id());
		
		//销方审批Log实体bean
		BasCheckLog baschecklog = new BasCheckLog();
		System.out.println("oldbean:" +oldbean.getCheck_person_name());
		
		baschecklog.setLog_modifiedperson(oldbean.getCheck_person_name());
		baschecklog.setLog_modifiedtime(oldbean.getLast_modify_time());
		baschecklog.setLog_type(oldbean.getCheck_type());
		baschecklog.setLog_typeid(oldbean.getCheck_type_id());
		baschecklog.setLog_checkid(oldbean.getCheck_id());
		baschecklog.setLog_checkcomment(oldbean.getCheck_content());
		baschecklog.setLog_checkperson(oldbean.getCheck_person_name());
		baschecklog.setLog_checktime(oldbean.getCheck_time());
		baschecklog.setLog_checkresult(oldbean.getCheck_result());
		baschecklog.setStatus(GlobalVal.RECORD_STATUS.ENABLE);
		baschecklog.setCreate_user(SysUtil.getLoginUserId(request));
		baschecklog.setCreate_time(new Date());
		baschecklog.setLast_modify_user(SysUtil.getLoginUserId(request));
		baschecklog.setLast_modify_time(new Date());
		checkSaleDao.addlog(baschecklog);	
		
		//销方审批bean
		bean.setCheck_time(new Date());
		bean.setCheck_person_id(SysUtil.getLoginUserId(request));
		bean.setCheck_person_name(SysUtil.getLoginUser(request).getDisplay_name());
		bean.setLast_modify_user(SysUtil.getLoginUserId(request));
		bean.setLast_modify_time(new Date());
		checkSaleDao.update(bean);
		log.debug("修改功能成功 => id : " + bean.getCheck_id());
		
		System.out.println("bean.getCheck_modified()::::::"+bean.getCheck_modified());
		return bean;
	}
	
	/**
	 * 删除销方信息记录
	 * 
	 * @param id ID
	 * @param request HttpServletRequest
	 */
	@Override
	public void delete(Integer id, HttpServletRequest request) {
		log.debug("删除功能");
		checkSaleDao.delete(id);
		log.debug("删除功能成功 => id : " + id);
	}
	
}