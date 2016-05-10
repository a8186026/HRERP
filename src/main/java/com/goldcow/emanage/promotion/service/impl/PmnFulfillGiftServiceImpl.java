package com.goldcow.emanage.promotion.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldcow.emanage.promotion.persist.PmnFulfillGiftDao;
import com.goldcow.emanage.promotion.persist.PmnGiftInfoDao;
import com.goldcow.emanage.promotion.persist.PmnGiftSaleDao;
import com.goldcow.emanage.promotion.service.IPmnFulfillGiftService;
import com.goldcow.emanage.promotion.service.IPmnGiftInfoService;
import com.goldcow.emanage.util.gen.entity.PmnFulfillGift;
import com.goldcow.emanage.util.gen.entity.PmnGiftInfo;
import com.goldcow.emanage.util.gen.entity.PmnGiftSale;
import com.goldcow.emanage.util.gen.entity.valueObject.promotion.GiftInfoVO;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Maps;

@Service
public class PmnFulfillGiftServiceImpl implements IPmnFulfillGiftService {
	private static Logger log = LoggerFactory.getLogger(PmnFulfillGiftServiceImpl.class);
	/** 满额赠信息操作 */
	@Autowired
	private PmnFulfillGiftDao dao;

	/**
	 * 根据ID查询
	 * 
	 * @param id ID
	 * @return 满额赠信息
	 */
	@Override
	public PmnFulfillGift getById(Integer id) {
		log.debug("取得满额赠信息 => ID : " + id);
		return dao.get(id);
	}

	/**
	 * 查询满额赠信息列表
	 * 
	 * @param bean 查询条件
	 * @return 满额赠信息列表
	 */
	@Override
	public Map<String, Object> list(PmnFulfillGift bean){	
		log.debug("查询满额赠信息信息列表");		
		if(bean.getFull_gift_joinDepartment()!=null)
			bean.setFull_gift_joinDepartment(SysUtil.getSqlLikeParam(","+bean.getFull_gift_joinDepartment()+",")); 

		List<PmnFulfillGift> list = dao.list(bean);
		int count = dao.count(bean);

		// 封装分页对象
		Map<String, Object> result = Maps.newHashMap();
		result.put("total", count);
		result.put("rows", list);
		return result;
	}
	

	/**
	 * 新增满额赠信息
	 * 
	 * @param bean 满额赠信息
	 * @return 满额赠信息
	 */
	@Override
	public PmnFulfillGift add(PmnFulfillGift bean, HttpServletRequest request) {
		log.debug("新增满额赠信息");
		SysUtil.checkInput(bean);
		bean.setFull_gift_joinDepartment(","+bean.getFull_gift_joinDepartment()+",");
		bean.setFull_gift_joinProduct(","+bean.getFull_gift_joinProduct()+",");
		dao.add(bean);
		log.debug("新增满额赠信息成功 => id : " + bean.getFull_gift_id());
		return bean;
	}

	/**
	 * 修改满额赠
	 * 
	 * @param bean 满额赠信息
	 * @return 满额赠信息
	 */
	@Override
	public PmnFulfillGift update(PmnFulfillGift bean, HttpServletRequest request) {
		log.debug("修改满额赠信息");
		SysUtil.checkInput(bean);
		bean.setFull_gift_joinDepartment(","+bean.getFull_gift_joinDepartment()+",");
		bean.setFull_gift_joinProduct(","+bean.getFull_gift_joinProduct()+",");
		dao.update(bean);
		log.debug("修改满额赠信息成功 => id : " + bean.getFull_gift_id());
		return bean;
	}

	/**
	 * 删除满额赠信息
	 * 
	 * @param id ID
	 */
	@Override
	public void delete(Integer id, HttpServletRequest request) {
		log.debug("删除满额赠信息");
		System.out.println("id++++++++++++++++++++++++++++"+id);
		dao.delete(id);
		log.debug("删除满额赠信息成功 => id : " + id);
	}

	@Override
	public List<PmnFulfillGift> getFullFillGifts(PmnFulfillGift bean,
			List<String> proIds) {
		log.debug("获得满额赠信息");
		return dao.getFullFillGifts(bean, proIds);
	}

}