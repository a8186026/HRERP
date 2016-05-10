package com.goldcow.emanage.promotion.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldcow.emanage.promotion.persist.MemDayManageDao;
import com.goldcow.emanage.promotion.service.IMemDayManageService;
import com.goldcow.emanage.util.gen.SysTools;
import com.goldcow.emanage.util.gen.entity.MemDayManage;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Maps;

@Service
public class MemDayManageServiceImpl implements IMemDayManageService {
	private static Logger log = LoggerFactory.getLogger(MemDayManageServiceImpl.class);
	/** 会员日操作 */
	@Autowired
	private MemDayManageDao dao;

	/**
	 * 根据ID查询会员日
	 * 
	 * @param id ID
	 * @return 会员日信息
	 */
	@Override
	public MemDayManage getById(Integer id) {
		log.debug("取得会员日信息 => ID : " + id);
		return dao.get(id);
	}

	/**
	 * 查询会员日列表
	 * 
	 * @param bean 查询条件
	 * @return 控件列表
	 */
	@Override
	public Map<String, Object> list(MemDayManage bean) {
		log.debug("查询会员日列表");
		bean.setMem_day_type(SysTools.getSqlLikeParam(bean.getMem_day_type()));
		List<MemDayManage> list = dao.list(bean);
		int count = dao.count(bean);

		// 封装分页对象
		Map<String, Object> result = Maps.newHashMap();
		result.put("total", count);
		result.put("rows", list);
		return result;
	}

	/**
	 * 新增会员日
	 * 
	 * @param bean 会员日信息
	 * @return 会员日信息
	 */
	@Override
	public MemDayManage add(MemDayManage bean, HttpServletRequest request) {
		log.debug("新增会员日");
		SysUtil.checkInput(bean);
		
		bean.setMem_day_priority(dao.getMaxPriority()==null?1:dao.getMaxPriority());
		bean.setMem_day_date(","+bean.getMem_day_date()+",");
		bean.setCreate_user(SysUtil.getLoginUserId(request));
		bean.setCreate_time(new Date());
		bean.setLast_modify_time(new Date());
		bean.setStatus(GlobalVal.RECORD_STATUS.ENABLE);
		bean.setLast_modify_user(SysUtil.getLoginUserId(request));
		dao.add(bean);
		log.debug("新增会员日成功 => id : " + bean.getMem_day_id());
		return bean;
	}

	/**
	 * 修改会员日
	 * 
	 * @param bean 会员日信息
	 * @return 会员日信息
	 */
	@Override
	public MemDayManage update(MemDayManage bean, HttpServletRequest request) {
		log.debug("修改会员日");
		SysUtil.checkInput(bean);
		bean.setMem_day_date(","+bean.getMem_day_date()+",");
		bean.setLast_modify_time(new Date());
		bean.setLast_modify_user(SysUtil.getLoginUserId(request));
		dao.update(bean);
		log.debug("修改会员日成功 => id : " + bean.getMem_day_id());
		return bean;
	}

	/**
	 * 删除会员日
	 * 
	 * @param id ID
	 */
	@Override
	public void delete(Integer id, HttpServletRequest request) {
		log.debug("逻辑删除会员日");
		MemDayManage bean = new MemDayManage();
		bean.setMem_day_id(id);
		bean.setStatus(GlobalVal.RECORD_STATUS.DELETED);
		bean.setLast_modify_time(new Date());
		bean.setLast_modify_user(SysUtil.getLoginUserId(request));
		dao.update(bean);
		log.debug("逻辑删除会员日成功 => id : " + id);
	}

	@Override
	public MemDayManage isMemDay(Integer dept_id) {
		//获取日期时间
		Date date = new Date();
		String week = null;
		int weekDay = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
		switch(weekDay){
		case 1:
			week = "%日%";
			break;
		case 2:
			week = "%一%";
			break;
		case 3:
			week = "%二%";
			break;
		case 4:
			week = "%三%";
			break;
		case 5:
			week = "%四%";
			break;
		case 6:
			week = "%五%";
			break;
		case 7:
			week = "%六%";
			break;
		}
		return dao.isMemDay(date, week, "%,"+Calendar.getInstance().get(Calendar.DATE)+",%","%,"+dept_id+",%");
	}
	/**
	 * 保存折扣规则信息优先级顺序
	 * @param String data
	 * @return PmnRuleProduct
	 */
	@Override
	public String savePriority(String data, HttpServletRequest request) {
		//需要添加的List
		List<MemDayManage> memDayManage = new ArrayList<MemDayManage>();
			
		System.out.println("data::"+data);	
		String datas[] = data.split("\\|"); 
		System.out.println("data.length::"+datas.length);
		
		for(int i = 0;i < datas.length; i++){
			
			String subdatas[] = datas[i].split("_");
			System.out.println("subdatas.length::"+subdatas[0]);
			System.out.println("subdatas.length::"+subdatas[1]);
	
			MemDayManage memDayManage1 = new MemDayManage(); 
			memDayManage1.setMem_day_id(Integer.parseInt(subdatas[0]));
			memDayManage1.setMem_day_priority(Integer.parseInt(subdatas[1]));
			 
			memDayManage.add(memDayManage1);
		}
		dao.savePriority(memDayManage);
		
		return null;
	}
}