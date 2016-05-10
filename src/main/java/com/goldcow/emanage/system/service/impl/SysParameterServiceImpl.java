package com.goldcow.emanage.system.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldcow.emanage.system.persist.SysParameterDao;
import com.goldcow.emanage.system.service.ISysParameterService;
import com.goldcow.emanage.util.gen.entity.SysParameter;
import com.goldcow.emanage.util.gen.entity.SysParameterSub;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;

@Service
public class SysParameterServiceImpl implements ISysParameterService {
	private static Logger log = LoggerFactory.getLogger(SysUserServiceImpl.class);
	/** 系统参数操作 */
	@Autowired
	private SysParameterDao dao;

	/**
	 * 查询系统参数
	 * 
	 * @param bean 查询条件
	 * @return 查询结果
	 */
	@Override
	public List<Map<String, Object>> list(SysParameter bean) {
		bean.setParam_name(SysUtil.getSqlLikeParam(bean.getParam_name()));
		bean.setParam_code(SysUtil.getSqlLikeParam(bean.getParam_code()));

		// 执行查询
		List<Map<String, Object>> list = dao.mapList(bean);

		return list;
	}

	/**
	 * 按类别查询
	 * 
	 * @param code 类别
	 * @return 查询结果
	 */
	@Override
	public List<SysParameter> getByCode(String code) {
		return dao.getByCode(code);
	}

//	/**
//	 * 按类别查询
//	 * @param typeCode 类别
//	 * @return 查询结果
//	 */
//	@Override
//	public SysParameter getByCodeAndValue(String typeCode,String value) {
//		Map<String, Object> paramMap = new HashMap<String, Object>();
//		paramMap.put("typeCode", typeCode);
//		paramMap.put("value", value);
//		return dao.getByCodeAndValue(paramMap);
//	}
//	/**
//	 * 保存前校验
//	 * @param bean 实体
//	 * @return 操作结果
//	 */
//	@Override
//	public String save(SysParameter bean) {
//			// ID为空时为新增，否则为修改
//		if (bean.getId() == null) {
//			dao.save(bean);
//		} else {
//			dao.update(bean);
//		}
//		return "success";
//	}

	/**
	 * 新增系统参数
	 * 
	 * @param bean 系统参数信息
	 * @param request HttpServletRequest
	 * @return 系统参数信息
	 */
	public SysParameter add(SysParameter bean, HttpServletRequest request) {
		log.debug("新增系统参数");
		SysUtil.checkInput(bean);
		bean.setStatus(GlobalVal.RECORD_STATUS.ENABLE);
		bean.setCreate_user(SysUtil.getLoginUserId(request));
		bean.setCreate_time(new Date());
		bean.setLast_modify_user(SysUtil.getLoginUserId(request));
		bean.setLast_modify_time(new Date());
		dao.add(bean);
		log.debug("新增系统参数成功 => param_id : " + bean.getParam_id());
		return bean;
	}

	/**
	 * 修改系统参数
	 * 
	 * @param bean 系统参数信息
	 * @param request HttpServletRequest
	 * @return 系统参数信息
	 */
	public SysParameter update(SysParameter bean, HttpServletRequest request) {
		log.debug("修改系统参数");
		SysUtil.checkInput(bean);
		bean.setLast_modify_user(SysUtil.getLoginUserId(request));
		bean.setLast_modify_time(new Date());
		dao.update(bean);
		log.debug("修改系统参数成功 => param_id : " + bean.getParam_id());
		return bean;
	}

	/**
	 * 根据ID查询系统参数
	 * 
	 * @param id ID
	 * @return 查询结果
	 */
	@Override
	public SysParameter getById(Integer id) {
		return dao.get(id);
	}

	/**
	 * 根据ID删除系统参数
	 * 
	 * @param id ID
	 * @param request HttpServletRequest
	 */
	@Override
	public void delete(Integer id, HttpServletRequest request) {
		log.debug("删除系统参数");
		SysParameter bean = dao.get(id);
		bean.setStatus(9);
		bean.setLast_modify_user(SysUtil.getLoginUserId(request));
		bean.setLast_modify_time(new Date());
		dao.update(bean);
		log.debug("删除系统参数成功 => param_id : " + id);
	}

	/**
	 * 根据ID查询系统参数子项
	 * 
	 * @param id ID
	 * @return 查询结果
	 */
	@Override
	public SysParameterSub getSubById(Integer id) {
		return dao.getSub(id);
	}

	/**
	 * 新增系统参数子项
	 * 
	 * @param bean 系统参数信息
	 * @param request HttpServletRequest
	 * @return 系统参数子项信息
	 */
	@Override
	public SysParameterSub addSub(SysParameterSub bean, HttpServletRequest request) {
		log.debug("新增系统参数");
		SysUtil.checkInput(bean);
		bean.setStatus(GlobalVal.RECORD_STATUS.ENABLE);
		bean.setCreate_user(SysUtil.getLoginUserId(request));
		bean.setCreate_time(new Date());
		bean.setLast_modify_user(SysUtil.getLoginUserId(request));
		bean.setLast_modify_time(new Date());
		dao.saveSub(bean);
		log.debug("新增系统参数成功 => param_sub_id : " + bean.getParam_sub_id());
		return bean;
	}

	/**
	 * 修改系统参数子项
	 * 
	 * @param bean 数据系统参数子项
	 * @param request HttpServletRequest
	 * @return 系统参数子项信息
	 */
	public SysParameterSub updateSub(SysParameterSub bean, HttpServletRequest request) {
		log.debug("修改系统参数子项");
		SysUtil.checkInput(bean);
		bean.setLast_modify_user(SysUtil.getLoginUserId(request));
		bean.setLast_modify_time(new Date());
		dao.updateSub(bean);
		log.debug("修改系统参数子项成功 => param_sub_id : " + bean.getParam_sub_id());
		return bean;
	}

	/**
	 * 根据ID删除系统参数子项
	 * 
	 * @param id ID
	 * @param request HttpServletRequest
	 */
	@Override
	public void deleteSub(Integer id, HttpServletRequest request) {
		log.debug("删除系统参数子项");
		SysParameterSub bean = dao.getSub(id);
		bean.setCreate_user(SysUtil.getLoginUserId(request));
		bean.setCreate_time(new Date());
		bean.setLast_modify_user(SysUtil.getLoginUserId(request));
		bean.setLast_modify_time(new Date());
		dao.updateSub(bean);
		log.debug("删除系统参数子项成功 => param_sub_id : " + id);
	}
	
	/**
	 * 根据系统参数主表的编码获得所有的子表信息
	 * 
	 * 荣斌 2014-11-13日添加
	 * 
	 * @param code
	 * @return
	 */
	public List<SysParameterSub> getByParamCode(String code){
		return this.dao.getByParamCode(code);
	}
	
	/**
	 * 查询系统参数表中的信息
	 * 
	 * @param param
	 * @return
	 */
	public Map<String, Object> getParamCode(String param){
		Map<String, Object> map = new HashMap<String, Object>();
		List<SysParameterSub> list = this.getByParamCode(param);
		if (list == null) {
			map.put("results", "failure");
			map.put("data", "");
			map.put("msg", "未获得到系统参数表信息，请核实！");
			return map;
		}
		
		map.put("data", list);
		map.put("results", "success");
		map.put("msg", "获得系统参数表信息成功！");
		return map;
	}

}
