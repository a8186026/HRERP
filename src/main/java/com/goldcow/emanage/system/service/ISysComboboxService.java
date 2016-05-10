package com.goldcow.emanage.system.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.goldcow.emanage.util.gen.bean.LoginUser;
import com.goldcow.emanage.util.gen.entity.SysCombobox;
import com.goldcow.emanage.util.gen.entity.SysUser;
import com.goldcow.emanage.util.gen.entity.SysUserHabit;
import com.goldcow.emanage.util.gen.entity.valueObject.SysUser.SysUserVO;

/**
 * 服务层接口
 * 
 * @author wubin
 * @version v1.0
 * @since 2015-06-01
 */
public interface ISysComboboxService {

	/**
	 * 查询下拉框列表
	 * 
	 * @param bean 查询条件
	 * @return 下拉框列表
	 */
	public List<SysCombobox> list(SysCombobox bean);
	/**
	 * 查询树形下拉框列表
	 * 
	 * @param bean 查询条件
	 * @return 树形下拉框列表
	 */
	public List<SysCombobox> listTree(SysCombobox bean);

	/**
	 * 新增下拉框
	 * 
	 * @param bean 下拉框信息
	 * @return 下拉框信息
	 */
	public SysCombobox add(SysCombobox bean, HttpServletRequest request);

	/**
	 * 修改下拉框
	 * 
	 * @param bean 下拉框信息
	 * @param request HttpServletRequest
	 * @return 下拉框信息
	 */
	public SysCombobox update(SysCombobox bean, HttpServletRequest request);

	/**
	 * 删除下拉框
	 * 
	 * @param id ID
	 * @param request HttpServletRequest
	 */
	public void delete(Integer id,Integer pid, HttpServletRequest request);

	/**
	 * 根据ID查询
	 * 
	 * @param id ID
	 * @return 下拉框信息
	 */
	public SysCombobox getById(Integer id);

	/**
	 * 根据编码和父级ID查询树形下拉框
	 * 
	 * @param cbs_pid 父级类别ID
	 * @param cbs_code 下拉框编码
	 * @param cbs_type 下拉框类型
	 * @return 是否存在此编码
	 */
	public boolean getComboTreeByCode(Integer cbs_pid,String cbs_code,String cbs_type);

}