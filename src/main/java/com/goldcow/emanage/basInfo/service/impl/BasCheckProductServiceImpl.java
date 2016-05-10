package com.goldcow.emanage.basInfo.service.impl;


import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldcow.emanage.basInfo.persist.BasCheckDao;
import com.goldcow.emanage.basInfo.service.IBasCheckProductService;
import com.goldcow.emanage.product.persist.ProInfoManageDao;
import com.goldcow.emanage.util.gen.entity.BasCheck;
import com.goldcow.emanage.util.gen.entity.BasCheckLog;
import com.goldcow.emanage.util.gen.entity.ProInfoManage;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;

@Service
public class BasCheckProductServiceImpl implements IBasCheckProductService {
	private static Logger log = LoggerFactory.getLogger(BasCheckProductServiceImpl.class);
	/** 功能操作 */
	@Autowired
	private BasCheckDao checkProductDao;
	
	@Autowired
	private ProInfoManageDao productInfoDao;
	
	@Override
	public List<BasCheck> getProductsCheck(BasCheck bean) {
		log.debug("查询审批列表");
		
		return checkProductDao.list(bean);	
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
		checkProductDao.add(bean);
		
		log.debug("新增审核信息成功 => id : " + bean.getCheck_id());
		return bean;
	}
	@Override
	public void delete(Integer id, HttpServletRequest request) {
		log.debug("删除功能");
		checkProductDao.delete(id);
		log.debug("删除功能成功 => id : " + id);
	}
	@Override
	public BasCheck update(BasCheck bean, HttpServletRequest request) {
		log.debug("修改功能");
		SysUtil.checkInput(bean);
		

		
		//更新前的审批bean
		BasCheck oldbean = checkProductDao.get(bean.getCheck_id());
		System.out.println("check_id:"+bean.getCheck_id());
		
		//产品审批Log实体bean
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
		checkProductDao.addlog(baschecklog);	
		
		//产品审批bean
		bean.setCheck_time(new Date());
		bean.setCheck_person_id(SysUtil.getLoginUserId(request));
		bean.setCheck_person_name(SysUtil.getLoginUser(request).getDisplay_name());
		bean.setLast_modify_user(SysUtil.getLoginUserId(request));
		bean.setLast_modify_time(new Date());
		checkProductDao.update(bean);
		log.debug("修改功能成功 => id : " + bean.getCheck_id());
		
		System.out.println("bean.getCheck_modified()::::::"+bean.getCheck_modified());
		return bean;
	}
	@Override
	public List<ProInfoManage> getProducts() {
		System.out.println("getProducts------");
		ProInfoManage bean = new ProInfoManage();
		return productInfoDao.list(bean,null);
	}
	@Override
	public BasCheck getById(Integer id) {
		return checkProductDao.get(id);
	}
	@Override
	public Integer count(BasCheck bascheck) {
		log.debug("得到当前审批环节");
		return checkProductDao.counts(bascheck);
	}	
}