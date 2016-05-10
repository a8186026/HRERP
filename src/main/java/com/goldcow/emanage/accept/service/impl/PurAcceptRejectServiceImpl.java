package com.goldcow.emanage.accept.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldcow.emanage.accept.persist.PurAcceptRejectDao;
import com.goldcow.emanage.accept.service.IPurAcceptRejectService;
import com.goldcow.emanage.basInfo.service.impl.BasFactoryInfoServiceImpl;
import com.goldcow.emanage.util.gen.entity.PurAcceptReject;
import com.goldcow.emanage.util.gen.entity.StockPrice;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Maps;
/**
 * 收货拒收处理
 * 
 * @author zhanxiaotong
 * @since15-11-27
 * @version v1.0
 */
@Service
public class PurAcceptRejectServiceImpl implements IPurAcceptRejectService {
	private static Logger log = LoggerFactory.getLogger(BasFactoryInfoServiceImpl.class);
	/** 拒收信息操作  */
	@Autowired
	private PurAcceptRejectDao dao;

	/**
	 * 根据ID查询
	 * 
	 * @param id ID
	 * @return 拒收信息
	 */
	@Override
	public PurAcceptReject getById(Integer id) {
		log.debug("取得拒收信息 => ID : " + id);
		return dao.get(id);
	}
	/**
	 * 查询供方客户信息列表-分页
	 * 
	 * @param bean 查询条件
	 * @return 供方客户信息列表
	 */
	@Override
	public Map<String, Object> lists(PurAcceptReject bean){	
		log.debug("0000");		
		List<PurAcceptReject> list = dao.list(bean);
		log.debug("11111");	
		int count = dao.count(bean);

		// 封装分页对象
		Map<String, Object> result = Maps.newHashMap();
		result.put("total", count);
		result.put("rows", list);
		return result;
	}
	/**
	 * 新增拒收信息信息
	 * 
	 * @param bean 拒收信息信息
	 * @param request HttpServletRequest
	 * @return 拒收信息信息
	 */
	@Override
	public boolean add(String data, HttpServletRequest request) {
		
		log.debug("新增拒收信息");
//		SysUtil.checkInput(bean);
//		bean.setAcc_bond(Float.parseFloat("0"));
//		bean.setStatus(GlobalVal.RECORD_STATUS.ENABLE);
//		bean.setCreate_user(SysUtil.getLoginUserId(request));
//		bean.setCreate_time(new Date()); 
//		bean.setLast_modify_user(SysUtil.getLoginUserId(request));
//		bean.setLast_modify_time(new Date());	
		PurAcceptReject bean = new PurAcceptReject();
		//解析前台数据
		String result[] = data.split("_");
		bean.setProduct_id(Integer.parseInt(result[0]));
		bean.setSup_id(Integer.parseInt(result[1]));
		bean.setReject_batchnum(result[2]);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
		java.util.Date dt=new Date();
		try {
			dt = sdf.parse(result[3]);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		bean.setReject_validtime(dt);
		try {
			dt = sdf.parse(result[4]);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bean.setReject_productdate(dt);
		bean.setReject_num(Double.parseDouble(result[5]));
		bean.setReject_price(Double.parseDouble(result[6]));
		bean.setReject_reason(result[7]);
		bean.setReject_quality(result[8]);
		bean.setReject_status("1");
		bean.setReject_person(SysUtil.getLoginUserId(request));
		bean.setReject_time(new Date());
		dao.add(bean);
//		log.debug("新增拒收信息成功 => id : " + bean.getAcc_id());
		return true;
	}

	/**
	 * 修改拒收信息信息
	 * 
	 * @param bean 拒收信息信息
	 * @param brand_id ID
	 * @return 拒收信息信息
	 */
	@Override
	public PurAcceptReject update(PurAcceptReject bean, HttpServletRequest request) {		
		log.debug("修改拒收信息");
		bean.setReject_time(new Date());
		bean.setReject_person(SysUtil.getLoginUserId(request));
		dao.update(bean);
		return bean;
	}

	/**
	 * 删除拒收信息
	 * 
	 * @param brand_id ID
	 * @param request HttpServletRequest
	 */
	@Override
	public void delete(Integer id, HttpServletRequest request) {
		log.debug("删除拒收信息");
		dao.delete(id);
	}
	@Override
	public PurAcceptReject add(PurAcceptReject bean, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void submit(Integer reject_id, HttpServletRequest request) {
		// TODO Auto-generated method stub
		log.debug("提交拒收信息");
		PurAcceptReject bean = dao.get(reject_id);
		String maxTicket = getMaxRejectTicket();
		bean.setReject_ticket(maxTicket);
		bean.setReject_status("0");
		bean.setReject_time(new Date());
		bean.setReject_person(SysUtil.getLoginUserId(request));
		dao.update(bean);
	}

	/**
	 * 获取最大计划票号
	 * */
	@Override
	public String getMaxRejectTicket() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String date = dateFormat.format(new Date());
        StringBuilder out  = new StringBuilder(date);
		Integer ticketID =  dao.getMaxRejectTicket(date);
		if(ticketID != null){
			out.append(String.format("%04d",ticketID+1));
		}
		else{
			out.append("0001");
		}
		return "REJECT"+out.toString();
	}
}