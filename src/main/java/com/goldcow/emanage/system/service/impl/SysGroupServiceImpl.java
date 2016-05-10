package com.goldcow.emanage.system.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldcow.emanage.system.persist.SysGroupDao;
import com.goldcow.emanage.system.service.ISysGroupService;
import com.goldcow.emanage.util.gen.SysTools;
import com.goldcow.emanage.util.gen.entity.SysGroup;
import com.goldcow.emanage.util.gen.entity.SysUser;
import com.goldcow.emanage.util.gen.entity.SysUserGroup;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;

@Service
public class SysGroupServiceImpl implements ISysGroupService {
	private static Logger log = LoggerFactory.getLogger(SysGroupServiceImpl.class);
	/** 用户组操作 */
	@Autowired
	private SysGroupDao dao;

	/**
	 * 根据ID查询
	 * 
	 * @param id ID
	 * @return 用户组信息
	 */
	@Override
	public SysGroup getById(Integer id) {
		log.debug("取得用户组信息 => ID : " + id);
		return dao.get(id);
	}

	/**
	 * 查询用户组列表
	 * 
	 * @param bean 查询条件
	 * @return 用户组列表
	 */
	@Override
	public List<SysGroup> list(SysGroup bean) {
		log.debug("查询用户组列表");
		bean.setGroup_name(SysUtil.getSqlLikeParam(bean.getGroup_name()));
		return dao.list(bean);
	}

	/**
	 * 新增用户组
	 * 
	 * @param bean 用户组信息
	 * @param request HttpServletRequest
	 * @return 用户组信息
	 */
	@Override
	public SysGroup add(SysGroup bean, HttpServletRequest request) {
		log.debug("新增用户组");
		SysUtil.checkInput(bean);
		bean.setStatus(GlobalVal.RECORD_STATUS.ENABLE);
		bean.setDefault_group(0);
		bean.setCreate_user(SysUtil.getLoginUserId(request));
		bean.setCreate_time(new Date());
		bean.setLast_modify_user(SysUtil.getLoginUserId(request));
		bean.setLast_modify_time(new Date());
		dao.add(bean);
		log.debug("新增用户组成功 => group_id : " + bean.getGroup_id());
		return bean;
	}

	/**
	 * 修改用户组
	 * 
	 * @param bean 用户组信息
	 * @param request HttpServletRequest
	 * @return 用户组信息
	 */
	@Override
	public SysGroup update(SysGroup bean, HttpServletRequest request) {
		log.debug("修改用户组");
		SysUtil.checkInput(bean);
		bean.setLast_modify_user(SysUtil.getLoginUserId(request));
		bean.setLast_modify_time(new Date());
		dao.update(bean);
		log.debug("修改用户组成功 => group_id : " + bean.getGroup_id());
		return bean;
	}

	/**
	 * 删除用户组
	 * 
	 * @param group_id ID
	 * @param request HttpServletRequest
	 */
	@Override
	public void delete(Integer group_id, HttpServletRequest request) {
		log.debug("删除用户组");
		SysGroup bean = new SysGroup();
		bean.setGroup_id(group_id);
		bean.setStatus(GlobalVal.RECORD_STATUS.DELETED);
		bean.setLast_modify_user(SysUtil.getLoginUserId(request));
		bean.setLast_modify_time(new Date());
		dao.update(bean);
		log.debug("删除用户组成功 => group_id : " + group_id);
	}

	/**
	 * 设为默认用户组
	 * 
	 * @param group_id ID
	 * @param request HttpServletRequest
	 */
	@Override
	public void setDefault(Integer group_id, HttpServletRequest request) {
		log.debug("设置默认用户组");
		SysGroup bean = new SysGroup();
		bean.setGroup_id(group_id);
		bean.setDefault_group(1);
		bean.setLast_modify_user(SysUtil.getLoginUserId(request));
		bean.setLast_modify_time(new Date());
		dao.clearDefault();
		dao.update(bean);
		log.debug("设置默认用户组成功 => group_id : " + group_id);
	}

	/**
	 * 删除用户组内用户组
	 * 
	 * @param id 用户组ID
	 * @param user_id 用户组ID
	 */
	@Override
	public void deleteUser(Integer id, Integer user_id) {
		log.debug("删除用户组内用户组");
		SysUserGroup userGroup = new SysUserGroup();
		userGroup.setUser_id(user_id);
		userGroup.setGroup_id(id);
		
		dao.deleteUser(userGroup);
		log.debug("删除用户组内用户组成功 => user_id : " + id);
	}

	/**
	 * 向用户组内添加用户组
	 * 
	 * @param id 用户组ID
	 * @param user_id 用户组ID
	 */
	@Override
	public void addUser(Integer id, Integer user_id) {
		log.debug("向用户组中添加用户组");
		SysUserGroup userGroup = new SysUserGroup();
		userGroup.setUser_id(user_id);
		userGroup.setGroup_id(id);
		
		dao.addUser(userGroup);
		log.debug("向用户组中添加用户组 => user_id : " + id);
	}

	/**
	 * 取得用户组内用户组列表
	 * 
	 * @param id 用户组ID
	 * @return 用户组列表
	 */
	@Override
	public List<SysUser> getGroupUsers(Integer id) {
		return dao.getGroupUsers(id);
	}

	/**
	 * 取得可向用户组内添加的用户组列表
	 * 
	 * @param id 用户组ID
	 * @return 用户组列表
	 */
	@Override
	public List<SysUser> getUsersForAdd(Integer id) {
		return dao.getUsersForAdd(id);
	}

	
	/**
	 * 根据组ID 以及 User bean 信息查询所有符合结果的返回 
	 * @param 包含groupID 的 SysUserVO bean
	 */
	@Override
	public SysGroup getUserGroup(HttpServletRequest request) {
		return dao.getUserGroup(SysUtil.getLoginUserId(request));
	}

	@Override
	public SysGroup getUserGroup(Integer user_id) {
		// TODO Auto-generated method stub
	 return dao.getUserGroup(user_id);
	}
}