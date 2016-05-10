package com.goldcow.emanage.system.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.goldcow.emanage.util.gen.entity.SysCheckSetting;
import com.goldcow.emanage.util.gen.entity.SysCheckTitle;
import com.goldcow.emanage.util.gen.entity.valueObject.SysCheck.SysCheckVO;

/**
 * 审核次数服务层接口
 * 
 * @author wangqi
 * @version v1.0
 * @since 2015-6-1
 */
public interface ISysCheckSettingService {

	/**
	 * 查询审核设置列表
	 * 
	 * @param bean 查询条件
	 * @return 审核设置列表
	 */
	public Map<String, Object> list(SysCheckSetting bean);

	/**
	 * 新增审核信息
	 * 
	 * @param bean 审核信息
	 * @param request HttpServletRequest
	 * @return 审核信息
	 */
	public SysCheckVO add(SysCheckVO bean, HttpServletRequest request);

	/**
	 * 修改审核设置
	 * 
	 * @param bean 审核信息
	 * @param request HttpServletRequest
	 * @return 审核信息
	 */
	public SysCheckVO update(SysCheckVO bean, HttpServletRequest request);

	/**
	 * 删除审核
	 * 
	 * @param check_id ID
	 * @param request HttpServletRequest
	 */
	public void delete(Integer id, HttpServletRequest request);

	/**
	 * 根据ID查询
	 * 
	 * @param id ID
	 * @return 审核信息
	 */
	public SysCheckSetting getById(Integer id);
	/**
	 * 根据ID获取封装的审核VO
	 * 
	 * @param id ID
	 * @return 审核VO
	 */
	public SysCheckVO getCheckVO(Integer id);
	
	/**
	 * 根据编号获取正在审核的数量
	 * 
	 * @param code 审核标号
	 * @return 如果存在正在审核的，则返回false，如果没有真正审核的，则返回true
	 */
	public boolean getCheckNumber(String code);
}