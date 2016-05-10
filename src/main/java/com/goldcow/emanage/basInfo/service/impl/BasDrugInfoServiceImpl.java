package com.goldcow.emanage.basInfo.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldcow.emanage.basInfo.persist.BasDrugInfoDao;
import com.goldcow.emanage.basInfo.service.IBasDrugInfoService;
import com.goldcow.emanage.util.gen.SysTools;
import com.goldcow.emanage.util.gen.entity.BasDrugInfo;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Maps;
/**
 * 药监局药品信息
 * 
 * @author gaoxiang
 * @version v1.0
 * @since 2015-6-30
 */

@Service
public class BasDrugInfoServiceImpl implements IBasDrugInfoService {
	private static Logger log = LoggerFactory.getLogger(BasDrugInfoServiceImpl.class);
	/** 药监局药品信息操作  */
	@Autowired
	private BasDrugInfoDao dao;

	/**
	 * 根据ID查询
	 * 
	 * @param id ID
	 * @return 药监局药品信息
	 */
	@Override
	public BasDrugInfo getById(Integer id) {
		log.debug("取得药监局药品信息 => ID : " + id);
		return dao.get(id);
	}

	/**
	 * 查询药监局药品信息列表-分页
	 * 
	 * @param bean 查询条件
	 * @return 药监局药品信息列表
	 */
	@Override
	public Map<String, Object> list(BasDrugInfo bean){	
		log.debug("查询药监局药品信息列表");		
		
		//插入查询条件-药监局药品信息编码 
		bean.setEth_pzwh(SysUtil.getSqlLikeParam(bean.getEth_pzwh()));
		bean.setEth_tym(SysUtil.getSqlLikeParam(bean.getEth_tym()));
		
		List<BasDrugInfo> list = dao.list(bean);
		int count = dao.count(bean);

		// 封装分页对象
		Map<String, Object> result = Maps.newHashMap();
		result.put("total", count);
		result.put("rows", list);
				
		return result;
	}

	@Override
	public BasDrugInfo add(BasDrugInfo bean, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer id, HttpServletRequest request) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BasDrugInfo update(BasDrugInfo bean, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
	
}