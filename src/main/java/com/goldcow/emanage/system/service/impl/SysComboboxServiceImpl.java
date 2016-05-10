package com.goldcow.emanage.system.service.impl;

import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldcow.emanage.system.persist.SysComboboxDao;
import com.goldcow.emanage.system.persist.SysGroupDao;
import com.goldcow.emanage.system.persist.SysMenuDao;
import com.goldcow.emanage.system.persist.SysUserDao;
import com.goldcow.emanage.system.service.ISysComboboxService;
import com.goldcow.emanage.system.service.ISysUserService;
import com.goldcow.emanage.util.gen.SysTools;
import com.goldcow.emanage.util.gen.bean.LoginUser;
import com.goldcow.emanage.util.gen.entity.SysCombobox;
import com.goldcow.emanage.util.gen.entity.SysGroup;


import com.goldcow.emanage.util.gen.entity.SysUser;
import com.goldcow.emanage.util.gen.entity.SysUserGroup;
import com.goldcow.emanage.util.gen.entity.SysUserHabit;
import com.goldcow.emanage.util.gen.entity.valueObject.SysUser.SysUserVO;
import com.goldcow.emanage.util.gen.entity.valueObject.SysUser.UserHabitsVO;
import com.goldcow.emanage.util.system.SysUserHabits.SysUserDefaultHabits;
import com.goldcow.sframe.util.DecriptUtil;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Maps;

@Service
public class SysComboboxServiceImpl implements ISysComboboxService {
	private static Logger log = LoggerFactory.getLogger(SysComboboxServiceImpl.class);
	/** 用户操作 */
	@Autowired
	private SysComboboxDao comboboxDao;

	/**
	 * 根据ID查询
	 * 
	 * @param id ID
	 * @return 用户信息VO
	 */
	@Override
	public SysCombobox getById(Integer id) {
		log.debug("取得下拉框信息 => ID : " + id);
		SysCombobox sc = comboboxDao.get(id);
		return sc;				
	}

	/**
	 * 查询下拉框列表
	 * 
	 * @param bean 查询条件
	 * @return 下拉框列表
	 */
	@Override
	public List<SysCombobox> list(SysCombobox bean) {
		log.debug("查询下拉框列表");
		// 设置查询条件
		bean.setCbs_dec(SysUtil.getSqlLikeParam(bean.getCbs_dec()));
		bean.setCbs_chn(SysUtil.getSqlLikeParam(bean.getCbs_chn()));
		//bean.setCbs_type(SysUtil.getSqlLikeParam(SysTools.decode(bean.getCbs_type())));
		// 获取分页数据和总记录数
		List<SysCombobox> list = comboboxDao.list(bean);
		
		return list;
	}
	
	
	/**
	 * 查询下拉框列表
	 * 
	 * @param bean 查询条件
	 * @return 下拉框列表
	 */
	@Override
	public List<SysCombobox> listTree(SysCombobox bean) {
		log.debug("查询下拉框列表");
		// 设置查询条件
		bean.setCbs_dec(SysUtil.getSqlLikeParam(bean.getCbs_dec()));
		bean.setCbs_chn(SysUtil.getSqlLikeParam(bean.getCbs_chn()));
		//bean.setCbs_type(SysUtil.getSqlLikeParam(SysTools.decode(bean.getCbs_type())));
		// 获取分页数据和总记录数
		List<SysCombobox> list = comboboxDao.listTree(bean);
		
		return list;
	}
	

	/**
	 * 新增用户
	 * 
	 * @param bean 下拉框信息
	 * @param request HttpServletRequest
	 * @return 下拉框信息
	 */
	@Override
	public SysCombobox add(SysCombobox bean, HttpServletRequest request) {
		log.debug("新增用户");
		//bean.setCbs_type(SysTools.decode(bean.getCbs_type()));
		bean.setStatus(GlobalVal.RECORD_STATUS.ENABLE);
		bean.setCreate_user(SysUtil.getLoginUserId(request));
		bean.setCreate_time(new Date());
		bean.setLast_modify_user(SysUtil.getLoginUserId(request));
		bean.setLast_modify_time(new Date());	
		comboboxDao.add(bean);	
		
		log.debug("新增下拉框成功 => id : " + bean.getCbs_id());
		return bean;
	}

	/**
	 * 修改用户
	 * 
	 * @param bean 用户信息
	 * @param request HttpServletRequest
	 * @return 用户信息
	 */
	@Override
	public SysCombobox update(SysCombobox bean, HttpServletRequest request) {
		log.debug("修改下拉框");
	
		bean.setLast_modify_user(SysUtil.getLoginUserId(request));
		bean.setLast_modify_time(new Date());
		
		comboboxDao.update(bean);
		
		log.debug("修改用户成功 => id : " + bean.getCbs_id());
		return bean;
	}

	/**
	 * 删除用户
	 * 
	 * @param user_id ID
	 * @param request HttpServletRequest
	 */
	@Override
	public void delete(Integer id,Integer pid, HttpServletRequest request) {
		log.debug("删除下拉框");
		SysCombobox sc = new SysCombobox();
		if(pid == 0){
			sc.setCbs_pid(id);
			//删除父节点
			SysCombobox sc1 = new SysCombobox();
			sc1.setCbs_id(id);
			sc1.setStatus(GlobalVal.RECORD_STATUS.DELETED);
			sc1.setLast_modify_user(SysUtil.getLoginUserId(request));
			sc1.setLast_modify_time(new Date());
			comboboxDao.update(sc1);
		}else
			sc.setCbs_id(id);
		sc.setStatus(GlobalVal.RECORD_STATUS.DELETED);
		sc.setLast_modify_user(SysUtil.getLoginUserId(request));
		sc.setLast_modify_time(new Date());
		comboboxDao.update(sc);
		
		log.debug("删除下拉框成功 => id : " + id);

	}

	/**
	 * 根据编码和父级ID查询树形下拉框
	 * 
	 * @param cbs_pid 父级类别ID
	 * @param cbs_code 下拉框编码
	 * @param cbs_type 下拉框类型
	 * @return 是否存在此编码
	 */
	@Override
	public boolean getComboTreeByCode(Integer cbs_pid, String cbs_code,String cbs_type) {
		log.debug("取得下拉框信息 => code : " + cbs_code);
		SysCombobox scb = new SysCombobox();
		scb.setCbs_pid(cbs_pid);
		scb.setCbs_code(cbs_code);
		scb.setCbs_type(cbs_type);
		//如果存在，则返回false，不让添加，反之
		SysCombobox sysCombobox = comboboxDao.getComboTreeByCode(scb);
		if(sysCombobox!=null)
			return false;
		else
			return true;
	}
}