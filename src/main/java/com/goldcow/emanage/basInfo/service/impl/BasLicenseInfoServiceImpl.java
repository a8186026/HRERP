package com.goldcow.emanage.basInfo.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldcow.emanage.basInfo.persist.BasLicenseInfoDao;
import com.goldcow.emanage.basInfo.service.IBasLicenseInfoService;
import com.goldcow.emanage.util.gen.SysTools;
import com.goldcow.emanage.util.gen.entity.BasLicenseInfo;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Maps;
/**
 * 证照信息
 * 
 * @author gaoxiang
 * @version v1.0
 * @since 2015-6-30
 */

@Service
public class BasLicenseInfoServiceImpl implements IBasLicenseInfoService {
	private static Logger log = LoggerFactory.getLogger(BasLicenseInfoServiceImpl.class);
	/** 证照信息操作  */
	@Autowired
	private BasLicenseInfoDao dao;

	/**
	 * 根据ID查询
	 * 
	 * @param id ID
	 * @return 证照信息信息
	 */
	@Override
	public BasLicenseInfo getById(Integer id) {
		log.debug("取得证照信息 => ID : " + id);
		return dao.get(id);
	}

	/**
	 * 查询证照信息列表-分页
	 * 
	 * @param bean 查询条件
	 * @param license_type_id 
	 * @param license_type 
	 * @return 证照信息列表
	 */
	@Override
	public Map<String, Object> lists(BasLicenseInfo bean){	
		log.debug("查询证照信息列表");		
		
		//插入查询条件-证照信息编码 
		bean.setLicense_name(SysUtil.getSqlLikeParam(bean.getLicense_name()));
		bean.setLicense_no(SysUtil.getSqlLikeParam(bean.getLicense_no()));
		bean.setLicense_issuer(SysUtil.getSqlLikeParam(bean.getLicense_issuer()));
		 
		List<BasLicenseInfo> list = dao.lists(bean);
		int count = dao.count(bean);

		// 封装分页对象
		Map<String, Object> result = Maps.newHashMap();
		result.put("total", count);
		result.put("rows", list);
				
		return result;
	}
	
	/**
	 * 新增证照信息
	 * 
	 * @param bean 证照信息
	 * @param request HttpServletRequest
	 * @return 证照信息
	 */
	@Override
	public BasLicenseInfo add(BasLicenseInfo bean, HttpServletRequest request) {
		
		log.debug("新增证照信息");
		SysUtil.checkInput(bean);
		
		bean.setStatus(GlobalVal.RECORD_STATUS.ENABLE);
		bean.setCreate_user(SysUtil.getLoginUserId(request));
		bean.setCreate_time(new Date()); 
		bean.setLast_modify_user(SysUtil.getLoginUserId(request));
		bean.setLast_modify_time(new Date());	
		dao.add(bean);
		log.debug("新增证照信息成功 => id : " + bean.getLicense_id());
		return bean;
	}

	/**
	 * 修改证照信息
	 * 
	 * @param bean 证照信息
	 * @param brand_id ID
	 * @return 证照信息
	 */
	@Override
	public BasLicenseInfo update(BasLicenseInfo bean, HttpServletRequest request) {
		log.debug("修改证照信息");
		SysUtil.checkInput(bean);
		bean.setLast_modify_user(SysUtil.getLoginUserId(request)); 
		bean.setLast_modify_time(new Date()); 

		dao.update(bean);
		log.debug("修改证照信息成功 => id : " + bean.getLicense_id());
		return bean;
	}

	/**
	 * 删除证照信息
	 * 
	 * @param brand_id ID
	 * @param request HttpServletRequest
	 */
	@Override
	public void delete(Integer id, HttpServletRequest request) {
		
		log.debug("删除证照信息");
		
		BasLicenseInfo bean = new BasLicenseInfo();
		
		bean.setLicense_id(id);
		bean.setStatus(GlobalVal.RECORD_STATUS.DELETED);
		bean.setLast_modify_user(SysUtil.getLoginUserId(request)); 
		bean.setLast_modify_time(new Date()); 
		
		log.debug("删除证照信息成功 => ID : " + id);
		dao.update(bean);
	}
}