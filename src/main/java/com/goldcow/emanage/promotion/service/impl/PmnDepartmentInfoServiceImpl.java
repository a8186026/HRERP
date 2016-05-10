package com.goldcow.emanage.promotion.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldcow.emanage.promotion.persist.PmnDepartDao;
import com.goldcow.emanage.promotion.service.IPmnDepartmentInfoService;
import com.goldcow.emanage.util.gen.entity.PmnDepartmentInfo;
import com.goldcow.emanage.util.gen.entity.SysUser;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Maps;
/**
 * 部门档案信息
 * 
 * @author wangqi
 * @version v1.0
 * @since 2015-6-3
 */

@Service
public class PmnDepartmentInfoServiceImpl implements IPmnDepartmentInfoService {
	private static Logger log = LoggerFactory.getLogger(PmnDepartmentInfoServiceImpl.class);
	/** 部门档案信息操作  */
	@Autowired
	private PmnDepartDao dao;

	/**
	 * 根据ID查询
	 * 
	 * @param id ID
	 * @return 部门档案信息信息
	 */
	@Override
	public PmnDepartmentInfo getById(Integer id) {
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
	public Map<String, Object> list(PmnDepartmentInfo bean){	
		log.debug("查询未参加部门档案信息列表");		

		//插入查询条件-部门档案信息编码
		bean.setDepartment_number(SysUtil.getSqlLikeParam(bean.getDepartment_number())); 
		List<PmnDepartmentInfo> list = dao.list(bean);
		int count = dao.count(bean);

		// 封装分页对象
		Map<String, Object> result = Maps.newHashMap();
		result.put("total", count);
		result.put("rows", list);
		return result;
	}


	/**
	 * 查询部门档案信息上-分页
	 * 
	 * @param bean 查询条件
	 * @return 部门档案信息列表
	 */
	@Override
	public Map<String, Object> uplist(PmnDepartmentInfo bean){	
		log.debug("查询已参加部门档案信息列表上");		
		//插入查询条件-部门档案信息编码
		bean.setDepartment_number(SysUtil.getSqlLikeParam(bean.getDepartment_number())); 

		List<PmnDepartmentInfo> list = dao.uplist(bean);
		int count = dao.countup(bean);

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
	public PmnDepartmentInfo add(PmnDepartmentInfo bean, HttpServletRequest request) {

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
	public PmnDepartmentInfo update(PmnDepartmentInfo bean, HttpServletRequest request) {
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

		PmnDepartmentInfo bean = new PmnDepartmentInfo();

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
	public List<PmnDepartmentInfo> getList(PmnDepartmentInfo bean) {
		return dao.list(bean);
	}

	@Override
	public PmnDepartmentInfo enable(Integer id, HttpServletRequest request) {
		log.debug("启用部门");
		PmnDepartmentInfo pmnDepartmentInfo = new PmnDepartmentInfo();
		pmnDepartmentInfo.setDepartment_id(id);
		pmnDepartmentInfo.setStatus(GlobalVal.RECORD_STATUS.DISABLE);
		log.debug("启用部门成功 => id : " + id);
		dao.update(pmnDepartmentInfo);

		return pmnDepartmentInfo;
	}

	@Override
	public PmnDepartmentInfo disable(Integer id, HttpServletRequest request){
		log.debug("禁用部门");
		PmnDepartmentInfo pmnDepartmentInfo = new PmnDepartmentInfo();
		pmnDepartmentInfo.setDepartment_id(id);
		pmnDepartmentInfo.setStatus(GlobalVal.RECORD_STATUS.ENABLE);
		log.debug("禁用部门成功 => id : " + id);
		dao.update(pmnDepartmentInfo);

		return pmnDepartmentInfo;
	}
}