package com.goldcow.emanage.basInfo.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldcow.emanage.basInfo.persist.CompanyInfoDao;
import com.goldcow.emanage.basInfo.service.ICompanyInfoService;
import com.goldcow.emanage.util.gen.entity.BasAccount;
import com.goldcow.emanage.util.gen.entity.CompanyInfo;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Maps;
/**
 * 公司信息
 * 
 * @author wubin
 * @version v1.0
 * @since 2015-1-4
 */

@Service
public class CompanyInfoServiceImpl implements ICompanyInfoService {
	private static Logger log = LoggerFactory.getLogger(CompanyInfoServiceImpl.class);
	/** 公司信息操作  */
	@Autowired
	private CompanyInfoDao dao;

	/**
	 * 根据ID查询
	 * 
	 * @param id ID
	 * @return 公司信息
	 */
	@Override
	public CompanyInfo getById(Integer id) {
		log.debug("取得公司信息 => ID : " + id);
		return dao.get(id);
	}
	/**
	 * 查询公司信息列表-分页
	 * 
	 * @param bean 查询条件
	 * @return 公司信息列表
	 */
	@Override
	public Map<String, Object> lists(CompanyInfo bean){	
		log.debug("查询公司信息列表");		
		
		//插入查询条件-供方客户信息编码
	/*	bean.setAcc_no(SysUtil.getSqlLikeParam(bean.getAcc_no())); 
		bean.setAcc_name(SysUtil.getSqlLikeParam(bean.getAcc_name()));
		bean.setAcc_bank(SysUtil.getSqlLikeParam(bean.getAcc_bank()));*/
		bean.setCompany_fullName(SysUtil.getSqlLikeParam(bean.getCompany_fullName()));
		bean.setCompany_code(SysUtil.getSqlLikeParam(bean.getCompany_code()));
		bean.setCompany_lisenceCode(SysUtil.getSqlLikeParam(bean.getCompany_lisenceCode()));
		
		List<CompanyInfo> list = dao.lists(bean);
		int count = dao.count(bean);

		// 封装分页对象
		Map<String, Object> result = Maps.newHashMap();
		result.put("total", count);
		result.put("rows", list);
				
		return result;
	}
	/**
	 * 新增公司信息
	 * 
	 * @param bean 公司信息
	 * @param request HttpServletRequest
	 * @return 公司信息
	 */
	@Override
	public CompanyInfo add(CompanyInfo bean, HttpServletRequest request) {
		
		log.debug("新增公司信息");
		SysUtil.checkInput(bean);
		bean.setStatus(GlobalVal.RECORD_STATUS.ENABLE);
		bean.setCreate_user(SysUtil.getLoginUserId(request));
		bean.setCreate_time(new Date()); 
		bean.setLast_modify_user(SysUtil.getLoginUserId(request));
		bean.setLast_modify_time(new Date());	
		dao.add(bean);
		log.debug("新增公司信息成功 => id : " + bean.getCompany_id());
		return bean;
	}

	/**
	 * 修改公司信息
	 * 
	 * @param bean 公司信息
	 * @param brand_id ID
	 * @return 公司信息
	 */
	@Override
	public CompanyInfo update(CompanyInfo bean, HttpServletRequest request) {		
		log.debug("修改公司信息");

		SysUtil.checkInput(bean);
		bean.setLast_modify_user(SysUtil.getLoginUserId(request)); 
		bean.setLast_modify_time(new Date()); 

		dao.update(bean);
		log.debug("修改公司信息成功 => id : " + bean.getCompany_id());
		return bean;
	}

	/**
	 * 删除公司信息
	 * 
	 * @param brand_id ID
	 * @param request HttpServletRequest
	 */
	@Override
	public void delete(Integer id, HttpServletRequest request) {
		
		log.debug("删除公司信息");
		
		CompanyInfo bean = new CompanyInfo();
		
		bean.setCompany_id(id);
		bean.setStatus(GlobalVal.RECORD_STATUS.DELETED);
		bean.setLast_modify_user(SysUtil.getLoginUserId(request)); 
		bean.setLast_modify_time(new Date()); 
		
		log.debug("删除公司信息成功 => ID : " + id);
		dao.update(bean);
	}

}