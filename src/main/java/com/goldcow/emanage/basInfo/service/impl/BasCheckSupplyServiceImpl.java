package com.goldcow.emanage.basInfo.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldcow.emanage.basInfo.persist.BasCheckDao;
import com.goldcow.emanage.basInfo.service.IBasCheckSupplyService;
import com.goldcow.emanage.system.persist.SysGroupDao;
import com.goldcow.emanage.system.persist.SysUserDao;
import com.goldcow.emanage.util.gen.entity.BasCheck;
import com.goldcow.emanage.util.gen.entity.BasCheckLog;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;
/**
 * 供方审批
 * 
 * @author YuanXin
 * @version v1.0
 * @since 2015-6-5
 */

@Service
public class BasCheckSupplyServiceImpl implements IBasCheckSupplyService {
	private static Logger log = LoggerFactory.getLogger(BasCheckSupplyServiceImpl.class);
	/** 厂家档案信息操作  */
	@Autowired
	private BasCheckDao dao;
	@Autowired
	private SysGroupDao sysGroupDao;
	@Autowired
	private SysUserDao sysUserDao;
	
	
	@Override
	public BasCheck add(BasCheck bean,  HttpServletRequest request) {
		
		bean.setCheck_type("供方审核");
		
		
		bean.setCheck_person_id(SysUtil.getLoginUserId(request));

		bean.setCheck_time(new Date());
		bean.setStatus(GlobalVal.RECORD_STATUS.ENABLE);
		bean.setCreate_user(SysUtil.getLoginUserId(request));
		bean.setCreate_time(new Date());
		bean.setLast_modify_user(SysUtil.getLoginUserId(request));
		bean.setLast_modify_time(new Date());
		bean.setCheck_modified(0);//默认提交审批后不可修改
		//comment_id命名规范：审批对象id_审批组id
	    bean.setCheck_person_name(sysUserDao.get(SysUtil.getLoginUserId(request)).getDisplay_name());
		
		dao.add(bean);
		log.debug("添加审批内容");
		return bean;
	}

	@Override
	public List<BasCheck> lists(BasCheck bean) {
		log.debug("显示审批内容");
		return dao.list(bean);
	}

	@Override
	public BasCheck update(BasCheck bean, HttpServletRequest request) {
		log.debug("修改");
		SysUtil.checkInput(bean);
		

		
		//更新前的审批bean
		BasCheck oldbean = dao.get(bean.getCheck_id());
	
		
		//产品审批Log实体bean
		BasCheckLog baschecklog = new BasCheckLog();
		
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
		dao.addlog(baschecklog);	
		
		//产品审批bean
		bean.setCheck_time(new Date());
		bean.setCheck_person_id(SysUtil.getLoginUserId(request));
		bean.setCheck_person_name(SysUtil.getLoginUser(request).getDisplay_name());
		bean.setLast_modify_user(SysUtil.getLoginUserId(request));
		bean.setLast_modify_time(new Date());
		dao.update(bean);
		log.debug("修改功能成功 => id : " + bean.getCheck_id());
		return bean;
	}

	@Override
	public Integer count(BasCheck bascheck) {
		log.debug("得到当前审批环节");
		return dao.counts(bascheck);
	}
	@Override
	public void delete(Integer id, HttpServletRequest request) {
		log.debug("删除功能");
		dao.delete(id);
		log.debug("删除功能成功 => id : " + id);
	}

	@Override
	public BasCheck get(Integer id) {
		log.debug("通过id得到审批记录");
		return dao.get(id);
	}

	

	
	
}