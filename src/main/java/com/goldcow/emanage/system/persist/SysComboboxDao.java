package com.goldcow.emanage.system.persist;

import java.util.List;

import com.goldcow.emanage.util.gen.entity.SysCombobox;
import com.goldcow.emanage.util.gen.entity.SysComponentCtrl;
import com.goldcow.emanage.util.gen.entity.SysPageCtrl;
import com.goldcow.emanage.util.gen.entity.SysUser;
import com.goldcow.emanage.util.gen.entity.SysUserHabit;
import com.goldcow.emanage.util.gen.entity.valueObject.SysUser.UserHabitsVO;
import com.goldcow.sframe.util.mybatis.BaseDao;
import com.goldcow.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface SysComboboxDao extends BaseDao<SysCombobox> {
	
	/**
	 * 获取树形下拉框列表
	 * 
	 * @param bean 下拉框bean
	 * @return 所有属性下拉框
	 */
	public List<SysCombobox> listTree(SysCombobox bean);
	/**
	 * 根据编码和父级ID查询树形下拉框
	 * 
	 * @param bean 下拉框实体类
	 * @return 下拉框信息
	 */
	public SysCombobox getComboTreeByCode(SysCombobox bean);
}