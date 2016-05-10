package com.goldcow.emanage.basInfo.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldcow.emanage.basInfo.persist.BasFactoryInfoDao;
import com.goldcow.emanage.basInfo.service.IBasFactoryInfoService;
import com.goldcow.emanage.util.gen.SysTools;
import com.goldcow.emanage.util.gen.entity.BasFactoryInfo;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Maps;
/**
 * 厂家档案信息
 * 
 * @author gaoxiang
 * @version v1.0
 * @since 2015-6-2
 */

@Service
public class BasFactoryInfoServiceImpl implements IBasFactoryInfoService {
	private static Logger log = LoggerFactory.getLogger(BasFactoryInfoServiceImpl.class);
	/** 厂家档案信息操作  */
	@Autowired
	private BasFactoryInfoDao dao;

	/**
	 * 根据ID查询
	 * 
	 * @param id ID
	 * @return 厂家档案信息信息
	 */
	@Override
	public BasFactoryInfo getById(Integer id) {
		log.debug("取得厂家档案信息 => ID : " + id);
		return dao.get(id);
	}

	/**
	 * 查询厂家档案信息列表-分页
	 * 
	 * @param bean 查询条件
	 * @return 厂家档案信息列表
	 */
	@Override
	public Map<String, Object> list(BasFactoryInfo bean){	
		log.debug("查询厂家档案信息列表");		
		
		//插入查询条件-厂家档案信息编码
		bean.setFactory_code(SysUtil.getSqlLikeParam(bean.getFactory_code())); 
		bean.setFactory_shortname(SysUtil.getSqlLikeParam(bean.getFactory_shortname()));
		bean.setFactory_chnpy(SysUtil.getSqlLikeParam(bean.getFactory_chnpy()));
		 
		List<BasFactoryInfo> list = dao.list(bean);
		int count = dao.count(bean);

		// 封装分页对象
		Map<String, Object> result = Maps.newHashMap();
		result.put("total", count);
		result.put("rows", list);
				
		return result;
	}
	
	/**
	 * 新增厂家档案信息信息
	 * 
	 * @param bean 厂家档案信息信息
	 * @param request HttpServletRequest
	 * @return 厂家档案信息信息
	 */
	@Override
	public BasFactoryInfo add(BasFactoryInfo bean, HttpServletRequest request) {
		
		log.debug("新增厂家档案信息");
		SysUtil.checkInput(bean);
		
		bean.setStatus(GlobalVal.RECORD_STATUS.ENABLE);
		bean.setCreate_user(SysUtil.getLoginUserId(request));
		bean.setCreate_time(new Date()); 
		bean.setLast_modify_user(SysUtil.getLoginUserId(request));
		bean.setLast_modify_time(new Date());	
		dao.add(bean);
		log.debug("新增厂家档案信息成功 => id : " + bean.getFactory_id());
		return bean;
	}

	/**
	 * 修改厂家档案信息信息
	 * 
	 * @param bean 厂家档案信息信息
	 * @param brand_id ID
	 * @return 厂家档案信息信息
	 */
	@Override
	public BasFactoryInfo update(BasFactoryInfo bean, HttpServletRequest request) {
		log.debug("修改厂家档案信息");
		SysUtil.checkInput(bean);
		bean.setLast_modify_user(SysUtil.getLoginUserId(request)); 
		bean.setLast_modify_time(new Date()); 

		dao.update(bean);
		log.debug("修改厂家档案信息成功 => id : " + bean.getFactory_id());
		return bean;
	}

	/**
	 * 删除厂家档案信息
	 * 
	 * @param brand_id ID
	 * @param request HttpServletRequest
	 */
	@Override
	public void delete(Integer id, HttpServletRequest request) {
		
		log.debug("删除厂家档案信息");
		
		BasFactoryInfo bean = new BasFactoryInfo();
		
		bean.setFactory_id(id);
		bean.setStatus(GlobalVal.RECORD_STATUS.DELETED);
		bean.setLast_modify_user(SysUtil.getLoginUserId(request)); 
		bean.setLast_modify_time(new Date()); 
		
		log.debug("删除厂家档案信息成功 => ID : " + id);
		dao.update(bean);
	}
	
	/**
	 * 查询厂家编码最大值
	 * 
	 * @param request HttpServletRequest
	 * @return 自增厂家编码 
	 */
	@Override
	public String getMaxMedicineCode() {
		
		log.debug("获得当前最大的编码");
		
		Integer factory_code = dao.getMaxFactoryCode();
		String out;
		if(factory_code != null){
			out = String.format("%06d",factory_code + 1);
		}
		else{
			out = "0001";
		}
		return out;
	}

	/**
	 * 检验添加过程中的厂家编号是否是重复的
	 * 
	 * @param factory_code 厂家编码
	 * 
	 * @author RiverYao
	 * @since 2015-06-10
	 * */
	@Override
	public Boolean checkFactoryCode(String factory_code) {
		Integer count = dao.checkFactoryCodeNum(factory_code);
		if(count == 0){
			return true;
		}else{
			return false;
		}
	}
}