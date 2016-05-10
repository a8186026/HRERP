package com.goldcow.emanage.system.service.impl;

import java.io.InputStream;
import java.util.ArrayList;
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

import com.goldcow.emanage.system.persist.SysGroupDao;
import com.goldcow.emanage.system.persist.SysMenuDao;
import com.goldcow.emanage.system.persist.SysUserDao;
import com.goldcow.emanage.system.service.ISysUserService;
import com.goldcow.emanage.util.gen.SysTools;
import com.goldcow.emanage.util.gen.bean.LoginUser;
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
public class SysUserServiceImpl implements ISysUserService {
	private static Logger log = LoggerFactory.getLogger(SysUserServiceImpl.class);
	/** 用户操作 */
	@Autowired
	private SysUserDao userDao;
	/** 用户组操作 */
	@Autowired
	private SysGroupDao groupDao;
	/** 菜单操作 */
	@Autowired
	private SysMenuDao menuDao;

	/**
	 * 根据ID查询
	 * 
	 * @param id ID
	 * @return SysUserVO
	 */
	@Override
	public SysUserVO getById(Integer id) {
		log.debug("取得用户信息 => ID : " + id);
		
		//通过id获得用户和用户组
		
		SysUser user =  userDao.get(id);
		SysGroup sysgroup = groupDao.getUserGroup(id);
		
		SysUserVO bean = new SysUserVO();
		
		//构建SysUserVO
		bean.setUser_id(user.getUser_id());
		bean.setAddress(user.getAddress());
		bean.setDisplay_name(user.getDisplay_name());
		bean.setEmail(user.getEmail());
		bean.setSex(user.getSex());
		bean.setPassword(user.getPassword());
		bean.setTel(user.getTel());
		bean.setUser_name(user.getUser_name());	
		bean.setStatus(user.getStatus());
		bean.setCreate_user(user.getCreate_user());
		bean.setCreate_time(user.getCreate_time());
		bean.setLast_modify_user(user.getLast_modify_user());
		bean.setLast_modify_time(user.getLast_modify_time());			
		bean.setGroup_id(sysgroup.getGroup_id());
		bean.setDepart_id(user.getDepart_id());
		return bean;				
	}
	
	/**
	 * 根据UserID查询
	 * 
	 * @param id ID
	 * @return SysUserVO
	 */
	@Override
	public SysUser getByUserId(Integer USER_ID) {
		log.debug("通过UserId获得用户和用户组=> UserId : " + USER_ID);
		
		SysUser bean = new SysUser();
		bean = userDao.getByUserId(USER_ID);
		return bean;				
	}

	/**
	 * 查询用户列表
	 * 
	 * @param bean 查询条件
	 * @return 用户列表
	 */
	@Override
	public Map<String, Object> list(SysUser bean) {
		log.debug("查询用户列表");
		// 设置查询条件
		bean.setUser_name(SysUtil.getSqlLikeParam(bean.getUser_name()));
		bean.setDisplay_name(SysUtil.getSqlLikeParam(bean.getDisplay_name()));
		bean.setTel(SysUtil.getSqlLikeParam(bean.getTel()));
		
		// 获取分页数据和总记录数
		List<SysUser> list = userDao.list(bean);
		int count = userDao.count(bean);
		
		// 封装分页对象
		Map<String, Object> result = Maps.newHashMap();
		result.put("total", count);
		result.put("rows", list);
		
		return result;
	}
	/**
	 * 查询用户For Combo
	 * 
	 * @param bean 查询条件
	 * @return 用户列表
	 */
	@Override
	public List<SysUser> lists(SysUser bean) {
		log.debug("查询用户列表");
		
		List<SysUser> list = userDao.list(bean);
		
		return list;
	}

	/**
	 * 新增用户
	 * 
	 * @param bean 用户信息VO
	 * @param request HttpServletRequest
	 * @return 用户信息VO
	 */
	@Override
	public SysUserVO add(SysUserVO bean, HttpServletRequest request) {
		log.debug("新增用户");
		
		
		bean.setPassword(DecriptUtil.SHA1(bean.getPassword()));
		SysUtil.checkInput(bean);
		SysUser userbean = new SysUser(); 
		SysUserGroup usergroupbean = new SysUserGroup();
		
		//用户属性设置
		userbean.setAddress(bean.getAddress());
		userbean.setDisplay_name(bean.getDisplay_name());
		userbean.setEmail(bean.getEmail());
		userbean.setSex(bean.getSex());
		userbean.setPassword(bean.getPassword());
		userbean.setTel(bean.getTel());
		userbean.setUser_name(bean.getUser_name());	
		userbean.setStatus(GlobalVal.RECORD_STATUS.ENABLE);
		userbean.setCreate_user(SysUtil.getLoginUserId(request));
		userbean.setCreate_time(new Date());
		userbean.setLast_modify_user(SysUtil.getLoginUserId(request));
		userbean.setLast_modify_time(new Date());	
		userbean.setDepart_id(bean.getDepart_id());
		Integer user_id = userDao.add(userbean);	
		//用户组属性设置
		usergroupbean.setGroup_id(bean.getGroup_id());
		usergroupbean.setUser_id(userbean.getUser_id());
		groupDao.addUser(usergroupbean);
		
		log.debug("新增用户成功 => user_id : " + bean.getUser_id());
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
	public SysUserVO update(SysUserVO bean, HttpServletRequest request) {
		log.debug("修改用户");
		SysUtil.checkInput(bean);
		if (StringUtils.isBlank(bean.getPassword())) {
			bean.setPassword(null);
		} else {
			bean.setPassword(DecriptUtil.SHA1(bean.getPassword()));
		}	
		SysUser userbean = new SysUser(); 
		SysUserGroup usergroupbean = new SysUserGroup();
		userbean.setUser_id(bean.getUser_id());
		userbean.setAddress(bean.getAddress());
		userbean.setDisplay_name(bean.getDisplay_name());
		userbean.setEmail(bean.getEmail());
		userbean.setSex(bean.getSex());
		userbean.setPassword(bean.getPassword());
		userbean.setTel(bean.getTel());
		userbean.setUser_name(bean.getUser_name());	
		userbean.setLast_modify_user(SysUtil.getLoginUserId(request));
		userbean.setLast_modify_time(new Date());
		userbean.setDepart_id(bean.getDepart_id());
		userDao.update(userbean);
		
		
		usergroupbean.setGroup_id(bean.getGroup_id());
		usergroupbean.setUser_id(userbean.getUser_id());
		groupDao.deleteUser(usergroupbean);
		groupDao.addUser(usergroupbean);
		
		//groupDao.update_user_group(usergroupbean);
		log.debug("修改用户成功 => user_id : " + bean.getUser_id());
		return bean;
	}

	/**
	 * 删除用户
	 * 
	 * @param user_id ID
	 * @param request HttpServletRequest
	 */
	@Override
	public void delete(Integer user_id, HttpServletRequest request) {
		log.debug("删除用户");
		SysUser sysUser = new SysUser();
		sysUser.setUser_id(user_id);
		sysUser.setStatus(GlobalVal.RECORD_STATUS.DELETED);
		sysUser.setLast_modify_user(SysUtil.getLoginUserId(request));
		sysUser.setLast_modify_time(new Date());
		userDao.update(sysUser);
		
		//从关联表中删除数据
		SysUserGroup sysUsergroup = new SysUserGroup();
		sysUsergroup.setUser_id(user_id);
		groupDao.deleteUser(sysUsergroup);
		
		log.debug("删除用户成功 => user_id : " + user_id);

	}

	/**
	 * 启用帐号
	 * 
	 * @param id ID
	 * @param request HttpServletRequest
	 * @return 用户信息
	 */
	@Override
	public SysUser enable(Integer user_id, HttpServletRequest request) {
		log.debug("启用帐号");
		SysUser sysUser = new SysUser();
		sysUser.setUser_id(user_id);
		sysUser.setStatus(GlobalVal.RECORD_STATUS.ENABLE);
		sysUser.setLast_modify_user(SysUtil.getLoginUserId(request));
		sysUser.setLast_modify_time(new Date());
		log.debug("启用帐号成功 => user_id : " + user_id);
		userDao.update(sysUser);

		return sysUser;
	}

	/**
	 * 停用帐号
	 * 
	 * @param id ID
	 * @param request HttpServletRequest
	 * @return 用户信息
	 */
	@Override
	public SysUser disable(Integer user_id, HttpServletRequest request) {
		log.debug("停用帐号");
		SysUser sysUser = new SysUser();
		sysUser.setUser_id(user_id);
		sysUser.setStatus(GlobalVal.RECORD_STATUS.DISABLE);
		sysUser.setLast_modify_user(SysUtil.getLoginUserId(request));
		sysUser.setLast_modify_time(new Date());
		log.debug("停用帐号成功 => user_id : " + user_id);
		userDao.update(sysUser);

		return sysUser;
	}

	/**
	 * 用户登录
	 * 
	 * @param loginUser 用户名及密码信息
	 * @return 登录结果
	 */
	@Override
	public Map<String, Object> login(LoginUser loginUser) {
		Map<String, Object> result = Maps.newHashMap();
		
		String password = loginUser.getSysUser().getPassword();
		
		List<SysUser> list = userDao.getByUserName(loginUser.getSysUser().getUser_name());

		if (list.size() == 1) {
			loginUser.setSysUser(list.get(0));
			if (StringUtils.equals(DecriptUtil.SHA1(password), loginUser.getSysUser().getPassword())) {
				Integer user_id = loginUser.getSysUser().getUser_id();
				//根据用户ID获取所属组
				loginUser.setGroup(groupDao.getUserGroup(user_id));
				//根据组获取对应的菜单
				loginUser.setMenuList(menuDao.getGroupMenu(loginUser.getGroup().getGroup_id()));
				
				result.put("result", "success");
				result.put("message", "登录成功！");
			} else {
				result.put("result", "failure");
				result.put("message", "密码不正确！");
			}
		} else {
			result.put("result", "failure");
			result.put("message", "用户不存在！");
		}
		
		return result;
	}

	
	/**
	 * 获取用户习惯
	 * 
	 * @param ID 用户ID
	 * @param page_id 页面ID
	 * @param ctrl_id datagrid的ID
	 * @return 用户列表的所有用户习惯
	 */
	@Override
	public List<SysUserHabit> getHabits(Integer id,String page_id,String ctrl_id) {
		log.debug("获取用户习惯");
		UserHabitsVO uh = new UserHabitsVO();
		uh.setUser_id(id);
		uh.setPage_id(page_id);
		uh.setCtrl_id(ctrl_id);

		//获取用户习惯
		List<SysUserHabit> suhs = userDao.getHabits(uh);
		
		//如果没有用户习惯，则设置默认
		if(suhs.size()==0){
			log.debug("无用户习惯，获取默认用户习惯");
			SysUserDefaultHabits sax = new SysUserDefaultHabits();  
	        InputStream input = this.getClass().getClassLoader().getResourceAsStream("userDefaultHabits.xml");  
	        try {
				List<SysUserHabit> sysUserHabits = sax.getHabits(input);
				//过滤掉XML中非此datagrid的用户习惯
				for(int i=0;i<sysUserHabits.size();i++){
					if(sysUserHabits.get(i).getCtrl_id().equals(ctrl_id)&&sysUserHabits.get(i).getPage_id().equals(page_id)){
						suhs.add(sysUserHabits.get(i));
					}
				}
			} catch (Exception e) {
				log.debug("解析XML出错!!!");
				e.printStackTrace();;
			}
		}
		return suhs;
	}


	/**
	 * 新增配置用户习惯—页面属性显示
	 * 
	 * @param ID 用户ID
	 * @param habit_field 字段名称
	 * @param habit_field_name 字段中文名称
	 * @param page_id 页面ID 
	 * @param ctrl_id datagrid的ID
	 * @param request HttpServletRequest
	 * @return 新增配置用户习惯—页面属性信息
	 */
	
	@Override

	public void addUserHabits(Integer id, String habit_field, String habit_field_name, String page_id, String ctrl_id, HttpServletRequest request){
		log.debug("新增配置用户习惯—页面属性显示");
		 
		String[] hf = habit_field.split(",");
		String[] hfn = habit_field_name.split(",");
		
		UserHabitsVO uh = new UserHabitsVO();
		uh.setUser_id(id);
		uh.setPage_id(page_id);
		uh.setCtrl_id(ctrl_id);	
		userDao.deleteHabits(uh);	

		for(int i=0;i<hf.length;i++){	
			SysUserHabit suh = new SysUserHabit();
			suh.setCtrl_id(ctrl_id);
			suh.setPage_id(page_id);
			suh.setUser_id(id);
			suh.setHabit_field(hf[i]);
			suh.setHabit_order(i);
			suh.setHabit_width(80);
			suh.setHabit_field_name((SysTools.decode(hfn[i])));
			suh.setStatus(GlobalVal.RECORD_STATUS.ENABLE);
			suh.setCreate_user(SysUtil.getLoginUserId(request));
			suh.setCreate_time(new Date());
			suh.setLast_modify_user(SysUtil.getLoginUserId(request));
			suh.setLast_modify_time(new Date());
			userDao.addUserHabits(suh);
		}
		
	}				
	

	/**
	 * 获取用户习惯
	 * 
	 * @param fields 用户习惯所有字段
	 * @param fieldNames 字段中文名
	 * @param widths 字段宽度
	 * @param page_id 页面ID
	 * @param ctrl_id datagrid的ID
	 * @param request 
	 * @return 用户列表的所有用户习惯
	 */
	@Override
	public Integer saveHabits(String fields, String fieldNames, String widths, String page_id, String ctrl_id, HttpServletRequest request) {

		log.debug("保存用户习惯");
		List<UserHabitsVO> uhs = new ArrayList<UserHabitsVO>();
		
		//对传过来的字符串进行分割
		String[] f=fields.split(",");
		String[] fn=fieldNames.split(",");
		String[] w=widths.split(",");
		
		for(int i=0;i<f.length;i++){
			UserHabitsVO uh = new UserHabitsVO();
			uh.setUser_id(SysUtil.getLoginUserId(request));
			uh.setPage_id(page_id);
			uh.setCtrl_id(ctrl_id);
			uh.setField(f[i]);
			uh.setFieldName((fn[i]));
			uh.setWidth(Integer.parseInt(w[i]));
			uh.setOrder(i+1);
			uh.setLast_modify_user(SysUtil.getLoginUserId(request));
			uh.setLast_modify_time(new Date());
			//循环保存用户习惯
			
			uhs.add(uh);
			
		}
		return userDao.saveHabits(uhs);
	}

	/**
	 * 根据组ID 以及 User bean 信息查询所有符合结果的返回 
	 * @param 包含groupID 的 SysUserVO bean
	 */
	@Override
	public List<SysUserVO> getSysUserContainGroupID(SysUserVO bean) {
		log.debug("查询用户列表");
		// 设置查询条件
		bean.setUser_name(SysUtil.getSqlLikeParam(bean.getUser_name()));
		bean.setDisplay_name(SysUtil.getSqlLikeParam(SysTools.decode(bean.getDisplay_name())));
		bean.setTel(SysUtil.getSqlLikeParam(bean.getTel()));
		
		// 获取分页数据和总记录数
		List<SysUserVO> list = userDao.getSysUserContainGroupID(bean);
		return list;
	}



}