package com.goldcow.emanage.quality.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldcow.emanage.product.persist.ProInfoManageDao;
import com.goldcow.emanage.quality.persist.QltVarietyCheckDao;
import com.goldcow.emanage.quality.service.IQltVarietyCheckService;
import com.goldcow.emanage.util.gen.entity.ProInfoManage;
import com.goldcow.emanage.util.gen.entity.QltVarietyCheck;
import com.goldcow.emanage.util.gen.entity.valueObject.quality.QltVarietyCheckVO;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Maps;
/**
 * 重点养护品种信息实体类
 * 
 * @author gaoxiang
 * @since 2015-11-13
 */

@Service
public class QltVarietyCheckServiceImpl implements IQltVarietyCheckService {
	private static Logger log = LoggerFactory.getLogger(QltVarietyCheckServiceImpl.class);
	/** 重点养护品种信息操作  */
	@Autowired
	private QltVarietyCheckDao dao;
	@Autowired
	private ProInfoManageDao proDao;

	/**
	 * 根据ID查询
	 * 
	 * @param id ID
	 * @return 重点养护品种信息
	 */
	@Override
	public QltVarietyCheckVO getById(Integer id) {
		log.debug("取得重点养护品种信息> ID : " + id);
		return dao.getVOById(id);
	}

	/**
	 * 查询重点养护品种信息列表-分页
	 * 
	 * @param bean 查询条件
	 * @return 重点养护品种信息列表
	 */
	@Override
	public Map<String, Object> list(QltVarietyCheckVO bean){	
		log.debug("查询重点养护品种信息列表");		
		
		//插入查询条件
	    bean.setProduct_code(SysUtil.getSqlLikeParam(bean.getProduct_code())); 
		 
		List<QltVarietyCheckVO> list = dao.lists(bean);
		int count = dao.count(bean);

		// 封装分页对象
		Map<String, Object> result = Maps.newHashMap();
		result.put("total", count);
		result.put("rows", list);
				
		return result;
	}
	
	/**
	 * 新增重点养护品种信息
	 * 
	 * @param bean 重点养护品种信息
	 * @param request HttpServletRequest
	 * @return 重点养护品种信息	 */
	@Override
	public QltVarietyCheck add(QltVarietyCheck bean, HttpServletRequest request) {
		
		log.debug("新增重点养护品种信息");
		SysUtil.checkInput(bean);
		
		bean.setStatus(GlobalVal.RECORD_STATUS.ENABLE);
		bean.setCreate_user(SysUtil.getLoginUserId(request));
		bean.setCreate_time(new Date()); 
		bean.setLast_modify_user(SysUtil.getLoginUserId(request));
		bean.setLast_modify_time(new Date());	
		bean.setVariety_checkPerson(SysUtil.getLoginUser(request).getDisplay_name());
		bean.setVariety_checkTime(new Date()); 
		
		dao.add(bean);
		//System.out.println(bean.getProduct_id());
		ProInfoManage product = new ProInfoManage();
		
		product.setProduct_id(bean.getProduct_id());
		product.setProduct_immaintain(1);
		proDao.update(product);
		
		log.debug("新增重点养护品种信息成功 => id : " + bean.getVariety_checkId());
		return bean;
	}

	/**
	 * 修改重点养护品种信息
	 * 
	 * @param bean 重点养护品种信息
	 * @param brand_id ID
	 * @return 重点养护品种信息
	 */
	@Override
	public QltVarietyCheck update(QltVarietyCheck bean, HttpServletRequest request) {
		log.debug("修改重点养护品种信息");
		SysUtil.checkInput(bean);
		bean.setLast_modify_user(SysUtil.getLoginUserId(request)); 
		bean.setLast_modify_time(new Date()); 

		dao.update(bean);
		log.debug("修改重点养护品种信息成功 => id : " + bean.getVariety_checkId());
		return bean;
	}

	/**
	 * 删除重点养护品种信息
	 * 
	 * @param brand_id ID
	 * @param request HttpServletRequest
	 */
	@Override
	public void delete(Integer id, HttpServletRequest request) {
		
		log.debug("删除重点养护品种信息");
		
		dao.delete(id);
		
		log.debug("删除重点养护品种信息成功 => ID : " + id);
	}
	
}