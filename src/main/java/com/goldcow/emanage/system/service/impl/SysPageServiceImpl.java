package com.goldcow.emanage.system.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldcow.emanage.system.persist.SysPageCtrlDao;
import com.goldcow.emanage.system.service.ISysPageService;
import com.goldcow.emanage.util.gen.SysTools;
import com.goldcow.emanage.util.gen.entity.SysPageCtrl;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;

@Service
public class SysPageServiceImpl implements ISysPageService {
	private static Logger log = LoggerFactory.getLogger(SysPageServiceImpl.class);
	/** 页面注册操作 */
	@Autowired
	private SysPageCtrlDao dao;

	/**
	 * 根据ID查询
	 * 
	 * @param id ID
	 * @return 页面信息
	 */
	@Override
	public SysPageCtrl getById(Integer id) {
		log.debug("取得页面信息 => ID : " + id);
		return dao.get(id);
	}

	/**
	 * 查询页面列表
	 * 
	 * @param bean 查询条件
	 * @return 页面列表
	 */
	@Override
	public List<SysPageCtrl> list(SysPageCtrl bean) {
		log.debug("查询页面列表");
		bean.setPage_name(SysUtil.getSqlLikeParam(bean.getPage_name()));

		return dao.lists(bean);
	}

	/**
	 * 新增页面
	 * 
	 * @param bean 页面信息
	 * @return 页面信息
	 */
	@Override
	public SysPageCtrl add(SysPageCtrl bean, HttpServletRequest request) {
		log.debug("新增页面");
		SysUtil.checkInput(bean);
		bean.setStatus(GlobalVal.RECORD_STATUS.ENABLE);
		bean.setCreate_user(SysUtil.getLoginUserId(request));
		bean.setCreate_time(new Date());
		bean.setLast_modify_user(SysUtil.getLoginUserId(request));
		bean.setLast_modify_time(new Date());
		dao.add(bean);
		log.debug("新增页面成功 => id : " + bean.getId());
		return bean;
	}

	/**
	 * 修改页面
	 * 
	 * @param bean 页面信息
	 * @return 页面信息
	 */
	@Override
	public SysPageCtrl update(SysPageCtrl bean, HttpServletRequest request) {
		log.debug("修改页面");
		SysUtil.checkInput(bean);
		bean.setLast_modify_user(SysUtil.getLoginUserId(request));
		bean.setLast_modify_time(new Date());
		dao.update(bean);
		log.debug("修改页面成功 => id : " + bean.getId());
		return bean;
	}

	/**
	 * 删除页面
	 * 
	 * @param id ID
	 */
	@Override
	public void delete(Integer id, HttpServletRequest request) {
		log.debug("删除页面");
		dao.delete(id);
		log.debug("删除页面成功 => id : " + id);
		
	}

}