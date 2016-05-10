package com.goldcow.emanage.system.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldcow.emanage.system.persist.SysMenuDao;
import com.goldcow.emanage.system.persist.SysPermissionDao;
import com.goldcow.emanage.system.service.ISysPermissionService;
import com.goldcow.emanage.system.web.SysPermissionController;
import com.goldcow.emanage.util.gen.SysTools;
import com.goldcow.emanage.util.gen.entity.SysGroupMenu;
import com.goldcow.emanage.util.gen.entity.SysGroupMenuLog;
import com.goldcow.emanage.util.gen.entity.SysMenu;
import com.goldcow.emanage.util.gen.entity.SysPermission;
import com.goldcow.emanage.util.gen.entity.SysUser;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Maps;

@Service
public class SysPermissionServiceImpl implements ISysPermissionService {
	private static Logger log = LoggerFactory.getLogger(SysPermissionServiceImpl.class);
		
	/** 权限操作 */
	@Autowired
	private SysPermissionDao dao;
	@Autowired
	private SysMenuDao sysMenuDao;
	/**
	 * 根据ID查询
	 * 
	 * @param id ID
	 * @return 权限信息
	 */
	@Override
	public SysPermission getById(Integer id) {
		log.debug("取得权限信息 => ID : " + id);
		SysPermission sp = dao.get(id);
		sp.setGmenus(dao.getGroupMenu(sp.getGroup_id()));

		return sp;
	}

	/**
	 * 查询权限列表
	 * 
	 * @param bean 查询条件
	 * @return 权限列表
	 */
	@Override
	public List<SysPermission> list(SysPermission bean) {
		log.debug("查询权限列表");
		bean.setGroup_name(SysUtil.getSqlLikeParam(bean.getGroup_name()));
		
		List<SysPermission> list = dao.list(bean);
		
		for (SysPermission sp : list) {
			sp.setGmenus(dao.getGroupMenu(sp.getGroup_id()));
		}

		return list;
	}

	/**
	 * 新增权限
	 * 
	 * @param id 用户组ID
	 * @param permissions 菜单字符串
	 * @param oldPersmissions 原始的菜单字符串
	 * @param functions 功能字符串
	 */
	@Override
	public void add(Integer id, String permissions, String oldPersmissions) {
		log.debug("更改权限");
		//List<String> permissionsList = Arrays.asList(permissions.split("_"));
		//List<String> oldPersmissionsList = Arrays.asList(oldPersmissions.split("_"));
		//切割字符串
		String[] permissionsArray = permissions.split("_");
		String[] oldPersmissionsArray = oldPersmissions.split("_");
		//转换为整数数组,以便排序
		List<Integer> permissionsList = new ArrayList<Integer>();
		List<Integer> oldPersmissionsList = new ArrayList<Integer>();
		for(int i = 0; i<permissionsArray.length ; i++){
			if(!permissionsArray[i].equals("")){
				permissionsList.add(new Integer(permissionsArray[i]));
			}
			
		}
		for(int i = 0; i<oldPersmissionsArray.length ; i++){
			if(!oldPersmissionsArray[i].equals("")){
				oldPersmissionsList.add(new Integer(oldPersmissionsArray[i]));
			}
		}
		//排序，以便比对
		Collections.sort(permissionsList);
		Collections.sort(oldPersmissionsList);
		
		
		//差异比对
		for(int i=0,j=0; i<oldPersmissionsList.size() || j<permissionsList.size();){
			if((i<oldPersmissionsList.size()&&j>=permissionsList.size()) || (i<oldPersmissionsList.size()&&j<permissionsList.size()&&(oldPersmissionsList.get(i).compareTo(permissionsList.get(j))<0) ) ){
				//该权限要被删除
				SysGroupMenu gm = new SysGroupMenu();
				gm.setGroup_id(id);
				gm.setMenu_id(Integer.valueOf(oldPersmissionsList.get(i)));
				gm.setSgm_status(SysPermissionController.SGM_STATUS_YTON_CHECK);
				dao.updateSysGroupMenu(gm);
				i++;
			}else if(i<oldPersmissionsList.size()&&j<permissionsList.size()&&oldPersmissionsList.get(i).compareTo(permissionsList.get(j))==0){
				//该权限无变动
				i++;
				j++;
			}else if((i>=oldPersmissionsList.size()&&j<permissionsList.size()) ||  (i<oldPersmissionsList.size()&&j<permissionsList.size()&&(oldPersmissionsList.get(i).compareTo(permissionsList.get(j))>0) )  ){
				//该权限要被增加
				//验重，为防止重复提交申请
				SysGroupMenu gm = new SysGroupMenu();
				gm.setGroup_id(id);
				gm.setMenu_id(Integer.valueOf(permissionsList.get(j)));
				gm.setSgm_status(SysPermissionController.SGM_STATUS_NTOY_CHECK);
				if(dao.getGroupMenuList(gm).size() == 0){
					//不重复再执行
					dao.add(gm);
				}
				j++;
			}
		}
		log.debug("更改权限进入审核阶段，以待审核");
	}

	/**
	 * 删除权限
	 * 
	 * @param group_id ID
	 */
	@Override
	public void delete(Integer group_id) {
		log.debug("删除权限");
		dao.delete(group_id);
		log.debug("删除权限成功 => group_id : " + group_id);
	}

	/**
	 * 查询用户级、菜单及功能
	 * 
	 * @param id 用户组ID
	 * @return
	 */
	@Override
	public List<SysGroupMenu> getMenuFuncs(Integer id) {
		log.debug("查询用户级、菜单及功能");
		return dao.getMenuFuncs(id);
	}


	@Override
	public List<SysMenu> getGroupMenu(Integer id) {
		// TODO Auto-generated method stub
		log.debug("查询用户组菜单权限");
		return dao.getGroupMenu(id);

	}

	@Override
	public List<SysGroupMenu> getGroupMenuList(SysGroupMenu bean) {
		// TODO Auto-generated method stub
		log.debug("查询用户组菜单权限");
		return dao.getGroupMenuList(bean);
	}

	@Override
	public List<SysMenu> getGroupMenuForEnable(Integer group_id) {
		log.debug("查询用户组菜单权限");
		return sysMenuDao.getGroupMenu(group_id);
	}
	
	
	/**
	 * 生成审核记录
	 * 
	 * @param group_menu_id 用户菜单ID
	 * @param sgm_status 当前状态
	 * @param checkContent 审核结果
	 * @return 
	 */
	private void CreateMenuPermissionLog(String group_menu_id, String sgm_status, String checkContent,HttpServletRequest request){
		//根据group_menu_id获得Bean
		SysGroupMenu bean = new SysGroupMenu();
		bean.setGroup_menu_id(new Integer(group_menu_id));
		List<SysGroupMenu> sysGroupMenuList = dao.getGroupMenuList(bean);
		//数据安全验证
		if(sysGroupMenuList.size()==1){
			//构建log
			SysGroupMenuLog sysGroupMenuLog = new SysGroupMenuLog();
			sysGroupMenuLog.setGroup_id(sysGroupMenuList.get(0).getGroup_id());
			sysGroupMenuLog.setMenu_id(sysGroupMenuList.get(0).getMenu_id());
			sysGroupMenuLog.setCreate_user(SysUtil.getLoginUserId(request));
			sysGroupMenuLog.setCreate_time(new Date());
			sysGroupMenuLog.setStatus(GlobalVal.RECORD_STATUS.ENABLE);
			//构建备注说明信息
			String remark = "";
			if(checkContent.equals("pass")){
				if(sgm_status.equals(SysPermissionController.SGM_STATUS_NTOY_CHECK.toString())){
					remark = "审核通过：申请权限通过";
				}else if(sgm_status.equals(SysPermissionController.SGM_STATUS_YTON_CHECK.toString())){
					remark = "审核通过：删除权限通过";
				}
			}else if(checkContent.equals("nopass")){
				//不通过
				if(sgm_status.equals(SysPermissionController.SGM_STATUS_NTOY_CHECK.toString())){
					remark = "审核不通过：申请权限不通过";
				}else if(sgm_status.equals(SysPermissionController.SGM_STATUS_YTON_CHECK.toString())){
					remark = "审核不通过：删除权限不通过";
				}
			}
			sysGroupMenuLog.setRemark(remark);
			dao.addSysGroupMenuLog(sysGroupMenuLog);
		}
		
		
	}
	
	/**
	 * 查询菜单权限审核
	 * 
	 * @param group_menu_id 用户菜单ID
	 * @param sgm_status 当前状态
	 * @param checkContent 审核结果
	 * @return 查询结果
	 */
	@Override
	public void permissionCheck(String group_menu_id, String sgm_status, String checkContent , HttpServletRequest request) {
		SysGroupMenu gm = new SysGroupMenu();
		gm.setGroup_menu_id(new Integer(group_menu_id));
		CreateMenuPermissionLog(group_menu_id,sgm_status,checkContent,request);
		//通过
		if(checkContent.equals("pass")){
			if(sgm_status.equals(SysPermissionController.SGM_STATUS_NTOY_CHECK.toString())){
				gm.setSgm_status(SysPermissionController.SGM_STATUS_ENABLE);
				dao.updateSysGroupMenu(gm);
			}else if(sgm_status.equals(SysPermissionController.SGM_STATUS_YTON_CHECK.toString())){
				dao.deleteByGroupMenuId(new Integer(group_menu_id));
			}
		}else if(checkContent.equals("nopass")){
			//不通过
			if(sgm_status.equals(SysPermissionController.SGM_STATUS_NTOY_CHECK.toString())){
				dao.deleteByGroupMenuId(new Integer(group_menu_id));
			}else if(sgm_status.equals(SysPermissionController.SGM_STATUS_YTON_CHECK.toString())){
				gm.setSgm_status(SysPermissionController.SGM_STATUS_ENABLE);
				dao.updateSysGroupMenu(gm);
			}
		}
	}

	/**
	 * 添加用户组权限记录
	 * 
	 * @param id ID
	 * @return 权限信息
	 */
	@Override
	public SysGroupMenuLog addMenusLog(SysGroupMenuLog bean,HttpServletRequest request) {
		log.debug("新增用户组权限记录");
		bean.setCreate_time(new Date());
		bean.setCreate_user(SysUtil.getLoginUserId(request));
		dao.addSysGroupMenuLog(bean);
		log.debug("新增用户组权限记录 => log_id : " + bean.getGroup_menu_id());
		return bean;
	}

	/**
	 * 查询菜单权限审核记录
	 * 
	 * @param request HttpServletRequest
	 * @param bean 查询条件
	 * @return 查询结果
	 */
	@Override
	public Map<String, Object> getSysGroupMenuLogList(SysGroupMenuLog bean) {
		// TODO Auto-generated method stub
		// 获取分页数据和总记录数
		bean.setMenu_name(SysUtil.getSqlLikeParam(bean.getMenu_name()));
		List<SysGroupMenuLog> list = dao.getSysGroupMenuLogList(bean);
		int count = dao.getSysGroupMenuLogCount(bean);
		
		// 封装分页对象
		Map<String, Object> result = Maps.newHashMap();
		result.put("total", count);
		result.put("rows", list);
		return result;
	}
	
	
	

}