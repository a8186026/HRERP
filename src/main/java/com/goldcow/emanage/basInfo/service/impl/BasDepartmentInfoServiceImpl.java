package com.goldcow.emanage.basInfo.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldcow.emanage.basInfo.persist.BasDepartmentInfoDao;
import com.goldcow.emanage.basInfo.service.IBasDepartmentInfoService;
import com.goldcow.emanage.promotion.persist.MemDayManageDao;
import com.goldcow.emanage.util.gen.entity.BasDepartmentInfo;
import com.goldcow.emanage.util.gen.entity.MemDayManage;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
/**
 * 部门档案信息
 * 
 * @author wangqi
 * @version v1.0
 * @since 2015-6-3
 */

@Service
public class BasDepartmentInfoServiceImpl implements IBasDepartmentInfoService {
	private static Logger log = LoggerFactory.getLogger(BasDepartmentInfoServiceImpl.class);
	/** 部门档案信息操作  */
	@Autowired
	private BasDepartmentInfoDao dao;
	@Autowired
	private MemDayManageDao memDayManageDao;
	/**
	 * 根据ID查询
	 * 
	 * @param id ID
	 * @return 部门档案信息信息
	 */
	@Override
	public BasDepartmentInfo getById(Integer id) {
		log.debug("取得部门档案信息 => ID : " + id);
		return dao.get(id);
	}

	/**
	 * 查询部门档案信息列表-分页
	 * 
	 * @param bean 查询条件
	 * @return 部门档案信息列表
	 */
	@Override
	public Map<String, Object> list(BasDepartmentInfo bean){	
		log.debug("查询部门档案信息列表");		
		
		//插入查询条件-部门档案信息编码
		bean.setDepartment_number(SysUtil.getSqlLikeParam(bean.getDepartment_number())); 
		//bean.setFactory_name(SysUtil.getSqlLikeParam(SysTools.decode(bean.getFactory_name())));
		
		List<BasDepartmentInfo> list = dao.list(bean);
		int count = dao.count(bean);

		// 封装分页对象
		Map<String, Object> result = Maps.newHashMap();
		result.put("total", count);
		result.put("rows", list);
				
		return result;
	}
	
	/**
	 * 新增部门档案信息信息
	 * 
	 * @param bean 部门档案信息信息
	 * @param request HttpServletRequest
	 * @return 部门档案信息信息
	 */
	@Override
	public BasDepartmentInfo add(BasDepartmentInfo bean, HttpServletRequest request) {
		
		log.debug("新增部门档案信息");
		SysUtil.checkInput(bean);
		bean.setDepartment_receivable(0);
		bean.setDepartment_payable(0);
		bean.setDepartment_update_time(new Date());
		bean.setDepartment_create_time(new Date());
		bean.setDepartment_memberday_mode("0");
		bean.setDepartment_memberday("0");
		bean.setDepartment_memberday_integral(0);
		bean.setDepartment_memberprice_time("0");
		bean.setDepartment_balancedate(new Date());
		bean.setDepartment_memberdaytype("0");
		
		
		bean.setStatus(GlobalVal.RECORD_STATUS.ENABLE);
		bean.setCreate_user(SysUtil.getLoginUserId(request));
		bean.setCreate_time(new Date()); 
		bean.setLast_modify_user(SysUtil.getLoginUserId(request));
		bean.setLast_modify_time(new Date());	
		dao.add(bean);
		log.debug("新增部门档案信息成功 => id : " + bean.getDepartment_id());
		return bean;
	}

	/**
	 * 修改部门档案信息信息
	 * 
	 * @param bean 部门档案信息信息
	 * @param brand_id ID
	 * @return 部门档案信息信息
	 */
	@Override
	public BasDepartmentInfo update(BasDepartmentInfo bean, HttpServletRequest request) {
		log.debug("修改部门档案信息");
		SysUtil.checkInput(bean);
		bean.setLast_modify_user(SysUtil.getLoginUserId(request)); 
		bean.setLast_modify_time(new Date()); 

		dao.update(bean);
		log.debug("修改部门档案信息成功 => id : " + bean.getDepartment_id());
		return bean;
	}

	/**
	 * 删除部门档案信息
	 * 
	 * @param brand_id ID
	 * @param request HttpServletRequest
	 */
	@Override
	public void delete(Integer id, HttpServletRequest request) {
		
		log.debug("删除部门档案信息");
		
		BasDepartmentInfo bean = new BasDepartmentInfo();
		
		bean.setDepartment_id(id);
		bean.setStatus(GlobalVal.RECORD_STATUS.DELETED);
		bean.setLast_modify_user(SysUtil.getLoginUserId(request)); 
		bean.setLast_modify_time(new Date()); 
		
		log.debug("删除bm档案信息成功 => ID : " + id);
		dao.update(bean);
	}
	
	/**
	 * 验证部门编号
	 * 
	 * @param department_number 被删除的部门档案信息信息ID
	 * @return 验证结果
	 * 
	 * @author RiverYao
	 * @since 2015-06-10
	 */
	@Override
	public Boolean checkDepartmentNumber(String department_number) {
		if(dao.checkDepartmentNumber(department_number) == 0){
			return true;
		} else{
			return false;
		}
	}

	/**
	 * 返回数组的查询方法
	 * 
	 * @param bean 查询条件
	 * @return 验证结果
	 * 
	 * @author RiverYao
	 * @since 2015-06-10
	 */
	@Override
	public List<BasDepartmentInfo> getList(BasDepartmentInfo bean) {
		return dao.list(bean);
	}

	/**
	 * 返回所有未参加某活动的部门
	 * 
	 * @param request HttpServletRequest
	 * @param depts 已参加的部门
	 * @return 结果
	 * 
	 * @author wubin
	 * @since 2015-10-27
	 */
	@Override
	public List<BasDepartmentInfo> getListByDepts(String depts) {
		List<Integer> list = new ArrayList<Integer>();
		log.debug("查询未参加某活动的部门");
		if(depts!=null&&!depts.equals("")){
			String[] strs = depts.split("_");
			for(int i=0;i<strs.length;i++){
				list.add(Integer.parseInt(strs[i]));
			}
		}
		return dao.getListByDepts(list);
	}
	/**
	 * 返回所有参加某活动的部门
	 * 
	 * @param mem_day_id 会员日ID
	 * @return 结果
	 * 
	 * @author wubin
	 * @since 2015-10-28
	 */
	@Override
	public List<BasDepartmentInfo> getListByPromotion(Integer mem_day_id) {
		log.debug("查询参加某活动的部门");
		MemDayManage mdm = memDayManageDao.get(mem_day_id);
		List<Integer> depts = new ArrayList<Integer>();
		if(mdm!=null&&mdm.getDepartment_ids()!=null&&!mdm.getDepartment_ids().equals("")){
			String[] dept_list = mdm.getDepartment_ids().substring(1, mdm.getDepartment_ids().length()-1).split(",");
			if(dept_list[0]!=null&&!dept_list[0].equals("")){
				for(int i=0;i<dept_list.length;i++)
					depts.add(Integer.parseInt(dept_list[i]));
			}
			return dao.getListByPromotion(depts);
		}else
			return Lists.newArrayList();
		
		
	}	
}