package com.goldcow.emanage.system.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldcow.emanage.system.persist.SysComponentCtrlDao;
import com.goldcow.emanage.system.service.ISysComponentService;
import com.goldcow.emanage.util.gen.SysTools;
import com.goldcow.emanage.util.gen.entity.SysComponentCtrl;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;

@Service
public class SysComponentServiceImpl implements ISysComponentService {
	private static Logger log = LoggerFactory.getLogger(SysComponentServiceImpl.class);
	/** 控件操作 */
	@Autowired
	private SysComponentCtrlDao dao;

	/**
	 * 根据ID查询
	 * 
	 * @param id ID
	 * @return 控件信息
	 */
	@Override
	public SysComponentCtrl getById(Integer id) {
		log.debug("取得控件信息 => ID : " + id);
		return dao.get(id);
	}

	/**
	 * 查询控件列表
	 * 
	 * @param bean 查询条件
	 * @return 控件列表
	 */
	@Override
	public List<SysComponentCtrl> list(SysComponentCtrl bean) {
		log.debug("查询控件列表");
		bean.setPage_id(bean.getPage_id());
	    bean.setCtrl_name(SysUtil.getSqlLikeParam(bean.getCtrl_name()));
	    bean.setCtrl_type(SysUtil.getSqlLikeParam(bean.getCtrl_type()));
		return dao.list(bean);
	}

	/**
	 * 新增控件
	 * 
	 * @param bean 控件信息
	 * @return 控件信息
	 */
	@Override
	public SysComponentCtrl add(SysComponentCtrl bean, HttpServletRequest request) {
		log.debug("新增控件");
		SysUtil.checkInput(bean);
		bean.setStatus(GlobalVal.RECORD_STATUS.ENABLE);
		bean.setCreate_user(SysUtil.getLoginUserId(request));
		bean.setCreate_time(new Date());
		bean.setLast_modify_user(SysUtil.getLoginUserId(request));
		bean.setLast_modify_time(new Date());
		dao.add(bean);
		log.debug("新增控件成功 => id : " + bean.getId());
		return bean;
	}

	/**
	 * 修改控件
	 * 
	 * @param bean 控件信息
	 * @return 控件信息
	 */
	@Override
	public SysComponentCtrl update(SysComponentCtrl bean, HttpServletRequest request) {
		log.debug("修改控件");
		SysUtil.checkInput(bean);
		bean.setLast_modify_user(SysUtil.getLoginUserId(request));
		bean.setLast_modify_time(new Date());
		dao.update(bean);
		log.debug("修改控件成功 => id : " + bean.getId());
		return bean;
	}

	/**
	 * 删除控件
	 * 
	 * @param id ID
	 */
	@Override
	public void delete(Integer id, HttpServletRequest request) {
		log.debug("删除控件");
		System.out.println("id++++++++++++++++++++++++++++"+id);
		
		dao.delete(id);
		log.debug("删除控件成功 => id : " + id);
	}

}