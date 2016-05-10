package com.goldcow.emanage.system.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldcow.emanage.system.persist.SysCheckSettingDao;
import com.goldcow.emanage.system.service.ISysCheckSettingService;
import com.goldcow.emanage.util.gen.SysTools;
import com.goldcow.emanage.util.gen.entity.SysCheckSetting;
import com.goldcow.emanage.util.gen.entity.SysCheckTitle;
import com.goldcow.emanage.util.gen.entity.valueObject.SysCheck.SysCheckVO;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Maps;
/**
 * 审核次数设置服务层接口实现
 * 
 * @author wangqi
 * @version v1.0
 * @since 2015-6-1
 */
@Service
public class SysCheckSettingServiceImpl implements ISysCheckSettingService {
	private static Logger log = LoggerFactory.getLogger(SysCheckSettingServiceImpl.class);
	/** 审核操作 */
	@Autowired
	private SysCheckSettingDao dao;

	/**
	 * 根据ID查询
	 * 
	 * @param id ID
	 * @return审核次数信息
	 */
	@Override
	public SysCheckSetting getById(Integer id) {
		log.debug("取得审核次数信息 => ID : " + id);
		return dao.get(id);
	}
	

	/**
	 * 根据ID查询审核标题和组
	 * 
	 * @param id ID
	 * @return 审核标题和组
	 */
	@Override
	public SysCheckVO getCheckVO(Integer id) {
		log.debug("根据审核ID获取审核VO => ID : " + id);
		SysCheckSetting scs = getById(id);
		List<SysCheckTitle> sct = dao.getTitlesById(id);
		
		SysCheckVO sc = new SysCheckVO();
		sc.setCheck_id(scs.getCheck_id());
		sc.setCheck_name(scs.getCheck_name());
		sc.setCheck_code(scs.getCheck_code());
		sc.setCheck_times(scs.getCheck_times());
		String titles = "",groups = "",ids = "",cks ="";
		for(int i=0;i<sct.size();i++){
			titles = titles  + sct.get(i).getCheck_title();
			groups = groups + sct.get(i).getCheck_group();
			ids = ids + sct.get(i).getCheck_title_id();
			cks = cks + sct.get(i).getCheck_type();
			
			if(i<sct.size()-1){
				titles = titles  + ",";
				groups = groups + ",";
				ids = ids + ",";
				cks = cks + ",";
			}
		}
		sc.setCheck_titles(titles);
		sc.setCheck_groups(groups);
		sc.setCheck_title_ids(ids);
		sc.setCheck_types(cks);
		
		return sc;
	}


	/**
	 * 查询审核列表
	 * 
	 * @param bean 查询条件
	 * @return 审核列表
	 */
	@Override
	public Map<String, Object> list(SysCheckSetting bean) {
		log.debug("查询审核次数列表");
		bean.setCheck_name(SysUtil.getSqlLikeParam(bean.getCheck_name()));
		bean.setCheck_code(SysUtil.getSqlLikeParam(bean.getCheck_code()));
		
		List<SysCheckSetting> scs =dao.list(bean) ;
		int count = dao.count(bean);
		
		// 封装分页对象
		Map<String, Object> result = Maps.newHashMap();
		result.put("total", count);
		result.put("rows", scs);
		
		
		return result;
	}

	/**
	 * 新增审核
	 * 
	 * @param bean 用户组信息
	 * @param request HttpServletRequest
	 * @return 审核设置信息
	 */
	@Override
	public SysCheckVO add(SysCheckVO bean, HttpServletRequest request) {
		log.debug("新增审核信息"); 
		SysUtil.checkInput(bean);
		bean.setStatus(GlobalVal.RECORD_STATUS.ENABLE);
		bean.setCreate_user(SysUtil.getLoginUserId(request));
		bean.setCreate_time(new Date());
		bean.setLast_modify_user(SysUtil.getLoginUserId(request));
		bean.setLast_modify_time(new Date());
		
		//封装SysCheckSetting对象并且添加
		SysCheckSetting scs = new SysCheckSetting();
		scs.setCheck_id(bean.getCheck_id());
		scs.setCheck_code(bean.getCheck_code());
		scs.setCheck_name(bean.getCheck_name());
		scs.setCheck_times(bean.getCheck_times());
		scs.setStatus(GlobalVal.RECORD_STATUS.ENABLE);
		scs.setCreate_user(bean.getCreate_user());
		scs.setCreate_time(bean.getCreate_time());
		scs.setLast_modify_user(bean.getLast_modify_user());
		scs.setLast_modify_time(bean.getLast_modify_time());
		
		//封装SysCheckTitle对象并且添加
		String[] titles = bean.getCheck_titles().split(",");
		String[] groups = bean.getCheck_groups().split(",");
		String[] types = bean.getCheck_types().split(",");
		//先添加sys_checkSetting表获取添加ID
		dao.add(scs);
		
		//后添加sys_checkTitle表
		for(int i=0;i<titles.length;i++){
			SysCheckTitle sct = new SysCheckTitle();
			sct.setCheck_id(scs.getCheck_id());
			sct.setCheck_title(titles[i]);
			sct.setCheck_group(Integer.parseInt(groups[i]));
			sct.setCheck_order(i);
			sct.setStatus(GlobalVal.RECORD_STATUS.ENABLE);
			sct.setCreate_user(bean.getCreate_user());
			sct.setCreate_time(bean.getCreate_time());
			sct.setLast_modify_user(bean.getLast_modify_user());
			sct.setLast_modify_time(bean.getLast_modify_time());
			
			//如果是选中的，则是流水审核，否则是平行审核，用0和1表示
			if(types[i].equals("true")){
				sct.setCheck_type(0);//流水审核
			}else
				sct.setCheck_type(1);
			
			dao.addCheckTitle(sct);
		}
		
		log.debug("新增审核信息成功 => check_id : " + bean.getCheck_id());
		return bean;
	}

	/**
	 * 修改审核
	 * 
	 * @param bean 用户组信息
	 * @param request HttpServletRequest
	 * @return 用户组信息
	 */
	@Override
	public SysCheckVO update(SysCheckVO bean, HttpServletRequest request) {
		log.debug("修改审核信息");
		SysUtil.checkInput(bean);
		bean.setLast_modify_user(SysUtil.getLoginUserId(request));
		bean.setLast_modify_time(new Date());
		
		//封装SysCheckSetting对象并且添加
		SysCheckSetting scs = new SysCheckSetting();
		scs.setCheck_id(bean.getCheck_id());
		scs.setCheck_code(bean.getCheck_code());
		scs.setCheck_name(bean.getCheck_name());
		scs.setCheck_times(bean.getCheck_times());
		scs.setLast_modify_user(bean.getLast_modify_user());
		scs.setLast_modify_time(bean.getLast_modify_time());
		
		//封装SysCheckTitle对象并且添加
		String[] titles = bean.getCheck_titles().split(",");
		String[] groups = bean.getCheck_groups().split(",");
		String[] ids = bean.getCheck_title_ids().split(",");
		String[] types = bean.getCheck_types().split(",");
		
		System.out.println("types+++++++"+bean.getCheck_types());
		
		int order = 0;
		
		
		for(int i=0;i<titles.length;i++){
			//如果id不存在或者ID为空，即是新添加项
			if(ids.length<=i||ids[i].equals("")){
				SysCheckTitle sct = new SysCheckTitle();
				sct.setCheck_id(bean.getCheck_id());
				sct.setCheck_title(titles[i]);
				sct.setCheck_group(Integer.parseInt(groups[i]));
				sct.setCheck_order(order);
				sct.setStatus(GlobalVal.RECORD_STATUS.ENABLE);
				sct.setCreate_user(SysUtil.getLoginUserId(request));
				sct.setCreate_time(new Date());
				sct.setLast_modify_user(SysUtil.getLoginUserId(request));
				sct.setLast_modify_time(new Date());
				//如果是选中的，则是流水审核，否则是平行审核，用0和1表示
				if(types[i].equals("true")){
					sct.setCheck_type(0);//流水审核
				}else
					sct.setCheck_type(1);
				
				dao.addCheckTitle(sct);
				order++;
			}else if(titles[i].equals("#")){
				//如果title为#，表示是需要删除的项
				dao.deleteCheckTitle(Integer.parseInt(ids[i]));
			}else{
				//表示需要更新的项
				SysCheckTitle sct = new SysCheckTitle();
				sct.setCheck_title_id(Integer.parseInt(ids[i]));
				sct.setCheck_id(bean.getCheck_id());
				sct.setCheck_title(titles[i]);
				sct.setCheck_group(Integer.parseInt(groups[i]));
				sct.setCheck_order(order);
				sct.setLast_modify_user(bean.getLast_modify_user());
				sct.setLast_modify_time(bean.getLast_modify_time());
				order++;
				//如果是选中的，则是流水审核，否则是平行审核，用0和1表示
				if(types[i].equals("true")){
					sct.setCheck_type(0);//流水审核
				}else
					sct.setCheck_type(1);
				
				dao.updateTitle(sct);
			}
			
		}
		dao.update(scs);
		log.debug("修改审核信息成功 => check_id : " + bean.getCheck_id());
		return bean;
	}

	/**
	 * 删除审核
	 * 
	 * @param group_id ID
	 * @param request HttpServletRequest
	 */
	@Override
	public void delete(Integer check_id, HttpServletRequest request) {
		log.debug("删除审核设置信息");
		SysCheckSetting bean = new SysCheckSetting();
		bean.setCheck_id(check_id);
		bean.setStatus(GlobalVal.RECORD_STATUS.DELETED);
		bean.setLast_modify_user(SysUtil.getLoginUserId(request));
		bean.setLast_modify_time(new Date());
		dao.update(bean);
		log.debug("删除审核设置信息成功 => check_id : " + check_id);
	}

	/**
	 * 根据编号获取正在审核的数量
	 * 
	 * @param code 审核标号
	 * @return 如果存在正在审核的，则返回false，如果没有真正审核的，则返回true
	 */
	@Override
	public boolean getCheckNumber(String code) {
		Integer number = dao.getCheckNumber(code);
		if(number>0){
			return false;
		}else
			return true;

	}


	
}