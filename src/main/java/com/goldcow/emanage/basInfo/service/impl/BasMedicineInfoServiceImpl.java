package com.goldcow.emanage.basInfo.service.impl;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldcow.emanage.basInfo.persist.BasMedicineInfoDao;
import com.goldcow.emanage.basInfo.service.IBasMedicineInfoService;
import com.goldcow.emanage.util.gen.SysTools;
import com.goldcow.emanage.util.gen.entity.BasMedicineInfo;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Maps;

@Service
public class BasMedicineInfoServiceImpl implements IBasMedicineInfoService {
	private static Logger log = LoggerFactory.getLogger(BasMedicineInfoServiceImpl.class);
	/** 功能操作 */
	@Autowired
	private BasMedicineInfoDao dao;
	
	@Override
	public BasMedicineInfo getById(Integer id) {
		log.debug("获取药品ID： " + id);
		return dao.get(id);
	}
	@Override
	public Map<String, Object> list(BasMedicineInfo bean) {
		log.debug("查询列表");
		bean.setMedicine_code(SysUtil.getSqlLikeParam(bean.getMedicine_code()));
		bean.setMedicine_name(SysUtil.getSqlLikeParam(bean.getMedicine_name()));
		//bean.setMedicine_chnpy(SysUtil.getSqlLikeParam(bean.getMedicine_chnpy()));
		
		List<BasMedicineInfo> list = dao.list(bean);
		int count = dao.count(bean);

		// 封装分页对象
		Map<String, Object> result = Maps.newHashMap();
		result.put("total", count);
		result.put("rows", list);
		
		System.out.println("+++++++++++++++++++++++++"+count);
				
		return result;
		
	}
	@Override
	public BasMedicineInfo add(BasMedicineInfo bean, HttpServletRequest request) {
		log.debug("新增药品");
		SysUtil.checkInput(bean);
		bean.setMedicine_update_time(new Date());
		bean.setStatus(GlobalVal.RECORD_STATUS.ENABLE);
		bean.setCreate_user(SysUtil.getLoginUserId(request));
		bean.setCreate_time(new Date());
		bean.setLast_modify_user(SysUtil.getLoginUserId(request));
		bean.setLast_modify_time(new Date());
		dao.add(bean);
		log.debug("新增药品成功 => id : " + bean.getMedicine_id());
		return bean;
	}
	@Override
	public void delete(Integer id, HttpServletRequest request) {
		log.debug("删除功能");
		dao.delete(id);
		log.debug("删除功能成功 => id : " + id);
	}
	@Override
	public BasMedicineInfo update(BasMedicineInfo bean, HttpServletRequest request) {
		log.debug("修改功能");
		SysUtil.checkInput(bean);
		bean.setMedicine_update_time(new Date());
		bean.setLast_modify_user(SysUtil.getLoginUserId(request));
		bean.setLast_modify_time(new Date());
		dao.update(bean);
		log.debug("修改功能成功 => id : " + bean.getMedicine_id());
		return bean;
	}
	@Override
	public String getMaxMedicineCode() {
		// TODO Auto-generated method stub
		
		log.debug("获得当前最大的编码");
		SimpleDateFormat dateFormat = new SimpleDateFormat("MMdd");
        String date = dateFormat.format(new Date());
        StringBuilder out  = new StringBuilder(date);
		Integer medicine_code =  dao.getMaxMedicineCode(date);
		if(medicine_code != null)
		{
			out.append(String.format("%04d",medicine_code+1));
		}
		else
			out.append("0001");
		return out.toString();
	}
	
}