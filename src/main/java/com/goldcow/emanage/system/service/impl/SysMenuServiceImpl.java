package com.goldcow.emanage.system.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldcow.emanage.system.persist.SysMenuDao;
import com.goldcow.emanage.system.service.ISysMenuService;
import com.goldcow.emanage.util.gen.SysTools;
import com.goldcow.emanage.util.gen.entity.SysMenu;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;

/**
 * 菜单管理服务层
 * 
 * @author chengrongchang
 * @version v1.0
 * @since 2014-10-16
 */
@Service
public class SysMenuServiceImpl implements ISysMenuService {
	private static Logger log = LoggerFactory.getLogger(SysMenuServiceImpl.class);
	/** 菜单操作 */
	@Autowired
	private SysMenuDao dao;

	/**
	 * 根据ID查询
	 * 
	 * @param id ID
	 * @return 菜单信息
	 */
	@Override
	public SysMenu getById(Integer id) {
		log.debug("取得菜单信息 => ID : " + id);
		return dao.get(id);
	}

	/**
	 * 查询菜单列表
	 * 
	 * @param bean 查询条件
	 * @return 菜单列表
	 */
	@Override
	public List<SysMenu> list(SysMenu bean) {
		log.debug("查询菜单列表");
		bean.setMenu_name(SysUtil.getSqlLikeParam(bean.getMenu_name()));
		bean.setMenu_code(SysUtil.getSqlLikeParam(bean.getMenu_code()));
		return dao.list(bean);
	}

	/**
	 * 新增菜单
	 * 
	 * @param bean 菜单信息
	 * @param request HttpServletRequest
	 * @return 菜单信息
	 */
	@Override
	public SysMenu add(SysMenu bean, HttpServletRequest request) {
		log.debug("新增菜单");
		SysUtil.checkInput(bean);
		bean.setStatus(GlobalVal.RECORD_STATUS.ENABLE);
		bean.setCreate_user(SysUtil.getLoginUserId(request));
		bean.setCreate_time(new Date());
		bean.setLast_modify_user(SysUtil.getLoginUserId(request));
		bean.setLast_modify_time(new Date());
		dao.add(bean);
		log.debug("新增菜单成功 => ID : " + bean.getMenu_id());
		return bean;
	}

	/**
	 * 修改菜单
	 * 
	 * @param bean 菜单信息
	 * @param request HttpServletRequest
	 * @return 菜单信息
	 */
	@Override
	public SysMenu update(SysMenu bean, HttpServletRequest request) {
		log.debug("修改菜单");
		SysUtil.checkInput(bean);
		bean.setLast_modify_user(SysUtil.getLoginUserId(request));
		bean.setLast_modify_time(new Date());
		dao.update(bean);
		log.debug("修改菜单成功 => ID : " + bean.getMenu_id());
		return bean;
	}

	/**
	 * 删除菜单
	 * 
	 * @param user_id ID
	 * @param request HttpServletRequest
	 */
	@Override
	public void delete(Integer id, HttpServletRequest request) {
		log.debug("删除菜单");
		SysMenu bean = new SysMenu();
		bean.setMenu_id(id);
		bean.setStatus(GlobalVal.RECORD_STATUS.DELETED);
		bean.setLast_modify_user(SysUtil.getLoginUserId(request));
		bean.setLast_modify_time(new Date());
		log.debug("删除菜单成功 => ID : " + id);
		dao.update(bean);
	}

	/**
	 * 启用菜单
	 * 
	 * @param id ID
	 * @param request HttpServletRequest
	 * @return 菜单信息
	 */
	public SysMenu enable(Integer id, HttpServletRequest request) {
		log.debug("启用帐号");
		SysMenu bean = new SysMenu();
		bean.setMenu_id(id);
		bean.setStatus(GlobalVal.RECORD_STATUS.ENABLE);
		bean.setLast_modify_user(SysUtil.getLoginUserId(request));
		bean.setLast_modify_time(new Date());
		log.debug("启用帐号成功 => ID : " + id);
		dao.update(bean);

		return bean;
	}

	/**
	 * 停用菜单
	 * 
	 * @param id ID
	 * @param request HttpServletRequest
	 * @return 菜单信息
	 */
	public SysMenu disable(Integer id, HttpServletRequest request) {
		log.debug("停用帐号");
		SysMenu bean = new SysMenu();
		bean.setMenu_id(id);
		bean.setStatus(GlobalVal.RECORD_STATUS.DISABLE);
		bean.setLast_modify_user(SysUtil.getLoginUserId(request));
		bean.setLast_modify_time(new Date());
		log.debug("停用帐号成功 => ID : " + id);
		dao.update(bean);

		return bean;
	}

	@Override
	public List<SysMenu> getNoPageGroupMenu(Integer id) {
		// TODO Auto-generated method stub
		return dao.getNoPageGroupMenu(id);
	}
}