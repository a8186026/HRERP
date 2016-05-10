package com.goldcow.emanage.quality.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldcow.emanage.quality.persist.QltBatchCheckDao;
import com.goldcow.emanage.quality.service.IQltBatchCheckService;
import com.goldcow.emanage.util.gen.entity.QltBatchCheck;
import com.goldcow.emanage.util.gen.entity.valueObject.quality.QltBatchCheckVO;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Maps;
/**
 * 重点养护批次信息实体类
 * 
 * @author gaoxiang
 * @since 2015-11-17
 */

@Service
public class QltBatchCheckServiceImpl implements IQltBatchCheckService {
	private static Logger log = LoggerFactory.getLogger(QltBatchCheckServiceImpl.class);
	/** 重点养护批次信息操作  */
	@Autowired
	private QltBatchCheckDao dao;

	/**
	 * 根据ID查询
	 * 
	 * @param id ID
	 * @return 重点养护批次信息
	 */
	@Override
	public QltBatchCheckVO getById(Integer id) {
		log.debug("取得重点养护批次信息> ID : " + id);
		return dao.getVOById(id);
	}

	/**
	 * 查询重点养护批次信息列表-分页
	 * 
	 * @param bean 查询条件
	 * @return 重点养护批次信息列表
	 */
	@Override
	public Map<String, Object> list(QltBatchCheckVO bean){	
		log.debug("查询重点养护批次信息列表");		
		
		//插入查询条件
		bean.setBatch_no(SysUtil.getSqlLikeParam(bean.getBatch_no())); 
	    bean.setProduct_code(SysUtil.getSqlLikeParam(bean.getProduct_code())); 
		 
		List<QltBatchCheckVO> list = dao.lists(bean);
		int count = dao.count(bean);

		// 封装分页对象
		Map<String, Object> result = Maps.newHashMap();
		result.put("total", count);
		result.put("rows", list);
				
		return result;
	}
	
	/**
	 * 新增重点养护批次信息
	 * 
	 * @param bean 重点养护批次信息
	 * @param request HttpServletRequest
	 * @return 重点养护批次信息	 */
	@Override
	public QltBatchCheck add(QltBatchCheck bean, HttpServletRequest request) {
		
		log.debug("新增重点养护批次信息");
		SysUtil.checkInput(bean);
		
		bean.setStatus(GlobalVal.RECORD_STATUS.ENABLE);
		bean.setCreate_user(SysUtil.getLoginUserId(request));
		bean.setCreate_time(new Date()); 
		bean.setLast_modify_user(SysUtil.getLoginUserId(request));
		bean.setLast_modify_time(new Date());	
		bean.setBatch_checkPerson(SysUtil.getLoginUser(request).getDisplay_name());
		bean.setBatch_checkTime(new Date()); 
		
		dao.add(bean);
		log.debug("新增重点养护批次信息成功 => id : " + bean.getBatch_checkId());
		return bean;
	}

	/**
	 * 修改重点养护批次信息
	 * 
	 * @param bean 重点养护批次信息
	 * @param brand_id ID
	 * @return 重点养护批次信息
	 */
	@Override
	public QltBatchCheck update(QltBatchCheck bean, HttpServletRequest request) {
		log.debug("修改重点养护批次信息");
		SysUtil.checkInput(bean);
		bean.setLast_modify_user(SysUtil.getLoginUserId(request)); 
		bean.setLast_modify_time(new Date()); 

		dao.update(bean);
		log.debug("修改重点养护批次信息成功 => id : " + bean.getBatch_checkId());
		return bean;
	}

	/**
	 * 删除重点养护批次信息
	 * 
	 * @param brand_id ID
	 * @param request HttpServletRequest
	 */
	@Override
	public void delete(Integer id, HttpServletRequest request) {
		
		log.debug("删除重点养护批次信息");
		
		dao.delete(id);
		
		log.debug("删除重点养护批次信息成功 => ID : " + id); 
	}

	/**
	 * 查询库存产品信息列表-分页
	 * 
	 * @param bean 查询条件
	 * @return 重点养护批次信息列表
	 */
	@Override
	public List<QltBatchCheckVO> listStock(QltBatchCheckVO bean,
			Integer product_id) {
		log.debug("查询重点养护批次信息列表");		
		
		//插入查询条件
	    bean.setProduct_id(product_id); 

		return dao.listStock(bean);
	}

	
	
	
}