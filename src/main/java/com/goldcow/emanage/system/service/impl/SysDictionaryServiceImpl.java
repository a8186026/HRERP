package com.goldcow.emanage.system.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldcow.emanage.system.persist.SysDictionaryDao;
import com.goldcow.emanage.system.service.ISysDictionaryService;
import com.goldcow.emanage.util.gen.SysTools;
import com.goldcow.emanage.util.gen.entity.SysDictionary;
import com.goldcow.emanage.util.gen.entity.SysDictionarySub;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Maps;

@Service
public class SysDictionaryServiceImpl implements ISysDictionaryService {
	private static Logger log = LoggerFactory.getLogger(SysUserServiceImpl.class);
	/** 数据字典操作 */
	@Autowired
	private SysDictionaryDao dao;

	/**
	 * 查询数据字典
	 * 
	 * @param bean 查询条件
	 * @return 查询结果
	 */
	@Override
	public List<Map<String, Object>> list(SysDictionary bean) {
		
		bean.setDict_name(SysUtil.getSqlLikeParam(bean.getDict_name()));
		bean.setDict_code(SysUtil.getSqlLikeParam(bean.getDict_code()));

		// 执行分页查询
		List<Map<String, Object>> list = dao.mapList(bean);
		// 构建前台需要数据结构

		return list;
	}

	/**
	 * 按类别查询
	 * 
	 * @param code 类别
	 * @return 查询结果
	 */
	@Override
	public List<SysDictionarySub> getByCode(String code) {
		return dao.getByCode(code);
	}
	
	/**
	 * 按类别查询
	 * 
	 * @param code 类别
	 * @param value 值
	 * @return 查询结果
	 */
	@Override
	public SysDictionarySub getByCodeAndValue(String code, String value) {
		Map<String, Object> param = Maps.newHashMap();
		param.put("code", code);
		param.put("value", value);

		return dao.getByCodeAndValue(param);
	}

	/**
	 * 新增数据字典
	 * 
	 * @param bean 数据字典信息
	 * @param request HttpServletRequest
	 * @return 数据字典信息
	 */
	public SysDictionary add(SysDictionary bean, HttpServletRequest request) {
		log.debug("新增数据字典");
		SysUtil.checkInput(bean);
		bean.setStatus(GlobalVal.RECORD_STATUS.ENABLE);
		bean.setCreate_user(SysUtil.getLoginUserId(request));
		bean.setCreate_time(new Date());
		bean.setLast_modify_user(SysUtil.getLoginUserId(request));
		bean.setLast_modify_time(new Date());
		dao.add(bean);
		log.debug("新增数据字典成功 => dict_id : " + bean.getDict_id());
		return bean;
	}

	/**
	 * 修改数据字典
	 * 
	 * @param bean 数据字典信息
	 * @param request HttpServletRequest
	 * @return 数据字典信息
	 */
	public SysDictionary update(SysDictionary bean, HttpServletRequest request) {
		log.debug("修改数据字典");
		SysUtil.checkInput(bean);
		bean.setLast_modify_user(SysUtil.getLoginUserId(request));
		bean.setLast_modify_time(new Date());
		dao.update(bean);
		log.debug("修改数据字典成功 => dict_id : " + bean.getDict_id());
		return bean;
	}

	/**
	 * 根据ID查询数据字典
	 * 
	 * @param id ID
	 * @return 查询结果
	 */
	@Override
	public SysDictionary getById(Integer id) {
		return dao.get(id);
	}

	/**
	 * 根据ID删除数据字典
	 * 
	 * @param id 数据字典ID
	 * @param request HttpServletRequest
	 */
	@Override
	public void delete(Integer id, HttpServletRequest request) {
		log.debug("删除数据字典");
		SysDictionary bean = dao.get(id);
		bean.setStatus(9);
		bean.setLast_modify_user(SysUtil.getLoginUserId(request));
		bean.setLast_modify_time(new Date());
		dao.update(bean);
		log.debug("删除数据字典成功 => dict_id : " + id);
	}

	/**
	 * 根据ID查询数据字典子项
	 * 
	 * @param id ID
	 * @return 查询结果
	 */
	@Override
	public SysDictionarySub getSubById(Integer id) {
		return dao.getSub(id);
	}

	/**
	 * 新增数据字典子项
	 * 
	 * @param bean 数据字典信息
	 * @param request HttpServletRequest
	 * @return 数据字典子项信息
	 */
	@Override
	public SysDictionarySub addSub(SysDictionarySub bean, HttpServletRequest request) {
		log.debug("新增数据字典");
		SysUtil.checkInput(bean);
		bean.setStatus(GlobalVal.RECORD_STATUS.ENABLE);
		bean.setCreate_user(SysUtil.getLoginUserId(request));
		bean.setCreate_time(new Date());
		bean.setLast_modify_user(SysUtil.getLoginUserId(request));
		bean.setLast_modify_time(new Date());
		dao.saveSub(bean);
		log.debug("新增数据字典成功 => dict_sub_id : " + bean.getDict_sub_id());
		return bean;
	}

	/**
	 * 修改数据字典子项
	 * 
	 * @param bean 数据数据字典子项
	 * @param request HttpServletRequest
	 * @return 数据字典子项信息
	 */
	public SysDictionarySub updateSub(SysDictionarySub bean, HttpServletRequest request) {
		log.debug("修改数据字典子项");
		SysUtil.checkInput(bean);
		bean.setLast_modify_user(SysUtil.getLoginUserId(request));
		bean.setLast_modify_time(new Date());
		dao.updateSub(bean);
		log.debug("修改数据字典子项成功 => dict_sub_id : " + bean.getDict_sub_id());
		return bean;
	}

	/**
	 * 根据ID删除数据字典子项
	 * 
	 * @param id ID
	 * @param request HttpServletRequest
	 */
	@Override
	public void deleteSub(Integer id, HttpServletRequest request) {
		log.debug("删除数据字典子项");
		SysDictionarySub bean = dao.getSub(id);
		bean.setCreate_user(SysUtil.getLoginUserId(request));
		bean.setCreate_time(new Date());
		bean.setLast_modify_user(SysUtil.getLoginUserId(request));
		bean.setLast_modify_time(new Date());
		dao.updateSub(bean);
		log.debug("删除数据字典子项成功 => dict_sub_id : " + id);
	}

}
