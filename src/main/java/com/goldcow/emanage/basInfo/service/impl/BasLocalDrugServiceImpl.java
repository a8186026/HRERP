package com.goldcow.emanage.basInfo.service.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldcow.emanage.basInfo.persist.BasLocalDrugDao;
import com.goldcow.emanage.basInfo.service.IBasLocalDrugService;
import com.goldcow.emanage.util.gen.SysTools;
import com.goldcow.emanage.util.gen.entity.TCatalog;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Maps;
/**
 * 地区药监信息
 * 
 * @author wangqi
 * @version v1.0
 * @since 2015-7-9
 */

@Service
public class BasLocalDrugServiceImpl implements IBasLocalDrugService {
	private static Logger log = LoggerFactory.getLogger(BasLocalDrugServiceImpl.class);
	/** 地区药监信息操作  */
	@Autowired
	private BasLocalDrugDao dao;

	/**
	 * 根据ID查询
	 * 
	 * @param id ID
	 * @return 地区药监信息
	 */
	@Override
	public TCatalog getById(Integer id) {
		log.debug("取得地区药监信息 => ID : " + id);
		return dao.get(id);
	}

	/**
	 * 查询地区药监信息列表-分页
	 * 
	 * @param bean 查询条件
	 * @return 地区药监信息列表
	 */
	@Override
	public Map<String, Object> list(TCatalog bean){	
		log.debug("查询地区药监信息列表");		
		
		//插入查询条件-地区药监信息编码 
		bean.setCa_gysmc(SysUtil.getSqlLikeParam(SysTools.decode(bean.getCa_gysmc())));
		//bean.setDrug_commonname(SysUtil.getSqlLikeParam(SysTools.decode(bean.getDrug_commonname())));
		
		List<TCatalog> list = dao.list(bean);
		int count = dao.count(bean);

		// 封装分页对象
		Map<String, Object> result = Maps.newHashMap();
		result.put("total", count);
		result.put("rows", list);
				
		return result;
	}

	@Override
	public TCatalog add(TCatalog bean, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer id, HttpServletRequest request) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TCatalog update(TCatalog bean, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
	
}