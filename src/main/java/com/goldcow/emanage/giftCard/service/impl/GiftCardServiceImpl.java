package com.goldcow.emanage.giftCard.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldcow.emanage.giftCard.persist.GiftCardDao;
import com.goldcow.emanage.giftCard.service.IGiftCardService;
import com.goldcow.emanage.util.gen.entity.GiftCardManage;
import com.goldcow.emanage.util.gen.entity.RetailOrderProduct;
import com.goldcow.emanage.util.gen.entity.valueObject.retail.RetailOrderVO;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Maps;

@Service
public class GiftCardServiceImpl implements IGiftCardService{
	private static Logger log = LoggerFactory.getLogger(GiftCardServiceImpl.class);
	/** 代金卡信息操作 */
	@Autowired
	private GiftCardDao dao;


	/**
	 * 根据ID查询
	 * 
	 * @param id ID
	 * @return 代金卡
	 */
	@Override
	public GiftCardManage getById(Integer id) {
		log.debug("取得代金卡信息 => ID : " + id);
		return dao.get(id);
	}

	/**
	 * 查询代金卡信息列表
	 * 
	 * @param bean 查询条件
	 * @return 代金卡信息列表
	 */
	@Override
	public Map<String, Object> list(GiftCardManage bean){	
		log.debug("查询代金卡信息信息列表");		

		List<GiftCardManage> list = dao.list(bean);
		int count = dao.count(bean);

		// 封装分页对象
		Map<String, Object> result = Maps.newHashMap();
		result.put("total", count);
		result.put("rows", list);
		return result;
	}

	/**
	 * 新增代金卡信息
	 * 
	 * @param bean 代金卡信息
	 * @return 代金卡信息
	 */
	@Override
	public GiftCardManage add(GiftCardManage bean, HttpServletRequest request) {
		log.debug("新增代金卡信息");
		SysUtil.checkInput(bean);
		bean.setStatus(GlobalVal.RECORD_STATUS.ENABLE);
		bean.setCreate_user(SysUtil.getLoginUserId(request));
		bean.setCreate_time(new Date());
		bean.setLast_modify_user(SysUtil.getLoginUserId(request));
		bean.setLast_modify_time(new Date());
		dao.add(bean);
		log.debug("新增代金卡信息成功 => id : " + bean.getGift_card_id());
		return bean;
	}

	/**
	 * 修改零售订单产品
	 * 
	 * @param bean 零售订单产品信息
	 * @return 零售订单产品信息
	 */
	@Override
	public GiftCardManage update(GiftCardManage bean, HttpServletRequest request) {
		log.debug("修改代金卡信息");
		SysUtil.checkInput(bean);
		bean.setLast_modify_user(SysUtil.getLoginUserId(request));
		bean.setLast_modify_time(new Date());
		dao.update(bean);
		log.debug("修改代金卡信息成功 => id : " + bean.getGift_card_id());
		return bean;
	}

	/**
	 * 删除零售订单产品信息
	 * 
	 * @param id ID
	 */
	@Override
	public void delete(Integer id, HttpServletRequest request) {
		log.debug("删除代金卡信息");
		System.out.println("id++++++++++++++++++++++++++++"+id);
		dao.delete(id);
		log.debug("删除代金卡信息成功 => id : " + id);
	}
	
	/**
	 * 查询当前消费金额能满足的代金卡规则
	 * 
	 * @param department_id 部门Id
	 * @param Amount 消费金额
	 */
	public Double getOffsetAmount(Integer department_id, Double Amount) {
		return dao.getOffsetAmount(department_id,Amount);
	}

}