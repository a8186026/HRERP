package com.goldcow.emanage.member.service.impl;


import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldcow.emanage.member.persist.MemCardManageDao;
import com.goldcow.emanage.member.service.IMemCardManageService;
import com.goldcow.emanage.util.gen.entity.MemCardManage;
import com.goldcow.emanage.util.gen.entity.valueObject.MemCard.MemCardVO;
import com.goldcow.emanage.util.gen.entity.valueObject.retail.RetailBatchVO;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Maps;

@Service
public class MemCardManageServiceImpl implements IMemCardManageService {
	private static Logger log = LoggerFactory.getLogger(MemCardManageServiceImpl.class);
	/** 会员卡操作 */
	@Autowired
	private MemCardManageDao dao;
	
	@Override
	public MemCardManage getById(Integer id) {
		log.debug("获取会员卡ID： " + id);
		return dao.get(id);
	}
	
	@Override
	public MemCardManage getMemCardByNumber(String number) {
		log.debug("获取会员卡号： " + number);
		return dao.getMemCardByNumber(number);
	}
	
	@Override
	public Map<String, Object> list(MemCardManage bean) {
		log.debug("查询会员卡列表");
		bean.setMem_card_name(SysUtil.getSqlLikeParam(bean.getMem_card_name()));
		bean.setMem_card_chn(SysUtil.getSqlLikeParam(bean.getMem_card_chn()));
		
		List<MemCardManage> list = dao.list(bean);
		int count = dao.count(bean);

		// 封装分页对象
		Map<String, Object> result = Maps.newHashMap();
		result.put("total", count);
		result.put("rows", list);
		
		return result;
		
	}
	@Override
	public MemCardManage add(MemCardManage bean, HttpServletRequest request) {
		log.debug("新增会员卡");
		
		//手动添加float数据
		bean.setMem_card_allCost(new Float(0));
		bean.setMem_card_applyDate(new Date());
		bean.setMem_card_failedDate(new Date());
		bean.setMem_card_lastGiftTime(new Date());
		bean.setMem_card_birthday(new Date());
		
		//会员卡关于积分与金额的相关信息，进行添加初值
		if(bean.getMem_card_allCost()==null){
			bean.setMem_card_allCost(new Float(0));
		}
		if(bean.getMem_card_pointCost()==null){
			bean.setMem_card_pointCost(new Integer(0));
		}
		if(bean.getMem_card_point()==null){
			bean.setMem_card_point(new Integer(0));
		}
		if(bean.getMem_card_usedPoint()==null){
			bean.setMem_card_usedPoint(new Integer(0));
		}
		if(bean.getMem_card_originalPoint()==null){
			bean.setMem_card_originalPoint(new Integer(0));
		}
		if(bean.getMem_card_giftCard()==null){
			bean.setMem_card_giftCard(new Integer(0));
		}
		if(bean.getMem_card_addPoint()==null){
			bean.setMem_card_addPoint(new Integer(0));
		}
		if(bean.getMem_point_toMoney() == null){
			bean.setMem_point_toMoney(new Float(0));
		}
		if(bean.getMem_card_acountMoney() == null){
			bean.setMem_card_acountMoney(new Float(0));
		}
		if(bean.getMem_card_startMoney() == null){
			bean.setMem_card_startMoney(new Float(0));
		}
		
		
		//添加状态
		bean.setStatus(GlobalVal.RECORD_STATUS.ENABLE);
		bean.setCreate_user(SysUtil.getLoginUserId(request));
		bean.setCreate_time(new Date());
		bean.setLast_modify_user(SysUtil.getLoginUserId(request));
		bean.setLast_modify_time(new Date());
		dao.add(bean);
		log.debug("新增会员卡成功 => id : " + bean.getMem_card_id());
		return bean;
	}
	@Override
	public void delete(Integer id, HttpServletRequest request) {
		log.debug("删除会员卡");
		MemCardManage mm = dao.get(id);
		MemCardManage bean = new MemCardManage();
		bean.setMem_card_id(id);
		bean.setLast_modify_user(SysUtil.getLoginUserId(request));
		bean.setLast_modify_time(new Date());
		bean.setStatus(GlobalVal.RECORD_STATUS.DELETED);
		dao.update(bean);
		dao.addLog(mm);
		log.debug("删除会员卡成功 => id : " + id);
	}
	@Override
	public MemCardManage update(MemCardManage bean, HttpServletRequest request) {
		log.debug("修改会员卡");
		MemCardManage mm = dao.get(bean.getMem_card_id());
		
		System.out.println("++++++++++++++++++++++++++++++"+mm.getMem_point_toMoney());
		
		
		bean.setLast_modify_user(SysUtil.getLoginUserId(request));
		bean.setLast_modify_time(new Date());
		dao.update(bean);
		dao.addLog(mm);
		log.debug("修改会员卡成功 => id : " + bean.getMem_card_id());
		return bean;
	}

	/**
	 * 启用会员卡
	 * 
	 * @param request HttpServletRequest
	 * @param id 会员卡ID
	 * @return 操作结果
	 */
	@Override
	public MemCardManage enable(Integer id, HttpServletRequest request) {
		log.debug("启用会员卡");
		MemCardManage mm = dao.get(id);
		MemCardManage memCardManage = new MemCardManage();
		memCardManage.setMem_card_id(id);
		memCardManage.setStatus(GlobalVal.RECORD_STATUS.ENABLE);
		memCardManage.setLast_modify_user(SysUtil.getLoginUserId(request));
		memCardManage.setLast_modify_time(new Date());
		log.debug("启用会员卡成功 => user_id : " + id);
		dao.update(memCardManage);
		dao.addLog(mm);
		return memCardManage;
		
	}
	/**
	 * 停用会员卡
	 * 
	 * @param request HttpServletRequest
	 * @param id 会员卡ID
	 * @return 操作结果
	 */
	@Override
	public MemCardManage disable(Integer id, HttpServletRequest request) {
		log.debug("停用会员卡");
		MemCardManage mm = dao.get(id);
		MemCardManage memCardManage = new MemCardManage();
		memCardManage.setMem_card_id(id);
		memCardManage.setStatus(GlobalVal.RECORD_STATUS.DISABLE);
		memCardManage.setLast_modify_user(SysUtil.getLoginUserId(request));
		memCardManage.setLast_modify_time(new Date());
		log.debug("停用会员卡成功 => user_id : " + id);
		dao.update(memCardManage);
		dao.addLog(mm);
		return memCardManage;
	}
	/**
	 * 查询会员卡购买记录
	 * 
	 * @param request HttpServletRequest
	 * @param number 会员卡号
	 * @return 操作结果
	 */
	@Override
	public Map<String, Object> getPurchasesByNum(String number,Date startTime,Date endTime,MemCardManage bean,Integer type) {
		log.debug("查询会员卡购买记录");
		List<RetailBatchVO> list = dao.getPurchasesByNum(number, startTime, endTime,bean,type);
		int count = dao.countPurchasesByNum(number, startTime, endTime,bean,type);

		// 封装分页对象
		Map<String, Object> result = Maps.newHashMap();
		result.put("total", count);
		result.put("rows", list);
		
		
		return result;
	}
	
	
	/**
	 * 批量添加会员卡
	 * 
	 * @param request HttpServletRequest
	 * @param bean 会员卡VO
	 * @return 操作结果
	 */
	@Override
	public void addMembers(MemCardVO bean, HttpServletRequest request) {
		
		MemCardManage memCardManage = new MemCardManage();
		//手动添加float数据
		memCardManage.setMem_card_allCost(new Float(0));
		memCardManage.setMem_card_acountMoney(new Float(1));
		memCardManage.setMem_card_startMoney(new Float(0));
		memCardManage.setMem_point_toMoney(new Float(1));
		
		memCardManage.setMem_card_applyDate(new Date());
		memCardManage.setMem_card_failedDate(new Date());
		memCardManage.setMem_card_lastGiftTime(new Date());
		memCardManage.setMem_card_birthday(new Date());
		memCardManage.setMem_card_pointCost(new Integer(0));
		memCardManage.setMem_card_point(new Integer(0));
		memCardManage.setMem_card_usedPoint(new Integer(0));
		memCardManage.setMem_card_originalPoint(new Integer(0));
		memCardManage.setMem_card_giftCard(new Integer(0));
		memCardManage.setMem_card_addPoint(new Integer(0));
		
		memCardManage.setStatus(GlobalVal.RECORD_STATUS.ENABLE);
		memCardManage.setCreate_user(SysUtil.getLoginUserId(request));
		memCardManage.setCreate_time(new Date());
		memCardManage.setLast_modify_user(SysUtil.getLoginUserId(request));
		memCardManage.setLast_modify_time(new Date());
		
		memCardManage.setMem_card_name(bean.getMem_card_name());
		memCardManage.setMem_card_type(bean.getMem_card_type());
		if(bean.getMem_card_startPoint()!=null)
			memCardManage.setMem_card_originalPoint(bean.getMem_card_startPoint());
		
		//如果提交的是起始和截止的会员卡号
		if(bean.getType()==1){
			for(int i=Integer.parseInt(bean.getMem_card_start());i<=Integer.parseInt(bean.getMem_card_endOrNumber());i++){
				//封装成数据库保存的会员卡号
				String number  = bean.getMem_card_identify()+String.format("%0"+bean.getMem_card_start().length()+"d", i);
				//如果此会员卡ID不存在，则往数据库添加
				if(dao.getMemCardByNumber(number)==null){
					memCardManage.setMem_card_number(number);
					add(memCardManage,request);
				}
			}
		}else{
			//如果提交的是起始会员卡号和数量
			String start = bean.getMem_card_start();
			String number = bean.getMem_card_endOrNumber();
			for(int i=Integer.parseInt(start);i<Integer.parseInt(start)+Integer.parseInt(number);i++){
				//封装成数据库保存的会员卡号
				String num = bean.getMem_card_identify()+String.format("%0"+bean.getMem_card_start().length()+"d", i);
				//如果此会员卡ID不存在，则往数据库添加
				if(dao.getMemCardByNumber(num)==null){
					memCardManage.setMem_card_number(num);
					add(memCardManage,request);
				}
			}
		}
		
		
	}
	
}