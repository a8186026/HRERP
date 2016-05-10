package com.goldcow.emanage.basInfo.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldcow.emanage.basInfo.persist.BasPictureInfoDao;
import com.goldcow.emanage.basInfo.service.IBasPictureInfoService;
import com.goldcow.emanage.util.gen.SysTools;
import com.goldcow.emanage.util.gen.entity.BasPictureInfo;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Maps;
/**
 * 图片信息
 * 
 * @author gaoxiang
 * @version v1.0
 * @since 2015-6-30
 */

@Service
public class BasPictureInfoServiceImpl implements IBasPictureInfoService {
	private static Logger log = LoggerFactory.getLogger(BasPictureInfoServiceImpl.class);
	/** 图片信息操作  */
	@Autowired
	private BasPictureInfoDao dao;

	/**
	 * 根据ID查询
	 * 
	 * @param id ID
	 * @return 图片信息信息
	 */
	@Override
	public BasPictureInfo getById(Integer id) {
		log.debug("取得图片信息 => ID : " + id);
		return dao.get(id);
	}

	/**
	 * 查询图片信息列表-分页
	 * 
	 * @param bean 查询条件
	 * @return 图片信息列表
	 */
	@Override
	public Map<String, Object> lists(BasPictureInfo bean){	
		log.debug("查询图片信息列表");		
		
		//插入查询条件-图片信息编码 
		bean.setPicture_name(SysUtil.getSqlLikeParam(bean.getPicture_name()));
		bean.setPicture_remark(SysUtil.getSqlLikeParam(bean.getPicture_remark()));
		
		List<BasPictureInfo> list = dao.lists(bean);
		int count = dao.count(bean);

		// 封装分页对象
		Map<String, Object> result = Maps.newHashMap();
		result.put("total", count);
		result.put("rows", list);
				
		return result;
	}
	
	/**
	 * 新增图片信息
	 * 
	 * @param bean 图片信息
	 * @param request HttpServletRequest
	 * @return 图片信息
	 */
	@Override
	public BasPictureInfo add(BasPictureInfo bean, HttpServletRequest request) {
		
		log.debug("新增图片信息");
		SysUtil.checkInput(bean);
		
		bean.setStatus(GlobalVal.RECORD_STATUS.ENABLE);
		bean.setCreate_user(SysUtil.getLoginUserId(request));
		bean.setCreate_time(new Date()); 
		bean.setLast_modify_user(SysUtil.getLoginUserId(request));
		bean.setLast_modify_time(new Date());	
		dao.add(bean);
		log.debug("新增图片信息成功 => id : " + bean.getPicture_id());
		return bean;
	}

	/**
	 * 修改图片信息
	 * 
	 * @param bean 图片信息
	 * @param brand_id ID
	 * @return 图片信息
	 */
	@Override
	public BasPictureInfo update(BasPictureInfo bean, HttpServletRequest request) {
		log.debug("修改图片信息");
		SysUtil.checkInput(bean);
		bean.setLast_modify_user(SysUtil.getLoginUserId(request)); 
		bean.setLast_modify_time(new Date()); 

		dao.update(bean);
		log.debug("修改图片信息成功 => id : " + bean.getPicture_id());
		return bean;
	}

	/**
	 * 删除图片信息
	 * 
	 * @param brand_id ID
	 * @param request HttpServletRequest
	 */
	@Override
	public void delete(Integer id, HttpServletRequest request) {
		
		log.debug("删除图片信息");
		
		BasPictureInfo bean = new BasPictureInfo();
		
		bean.setPicture_id(id);
		bean.setStatus(GlobalVal.RECORD_STATUS.DELETED);
		bean.setLast_modify_user(SysUtil.getLoginUserId(request)); 
		bean.setLast_modify_time(new Date()); 
		
		log.debug("删除图片信息成功 => ID : " + id);
		dao.update(bean);
	}
}