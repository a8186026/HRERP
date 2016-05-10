package com.goldcow.emanage.system.persist;

import java.util.List;

import com.goldcow.emanage.util.gen.entity.SysMenu;
import com.goldcow.sframe.util.mybatis.BaseDao;
import com.goldcow.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface SysMenuDao extends BaseDao<SysMenu> {

	public List<SysMenu> getGroupMenu(Integer group_id);
	public List<SysMenu> getNoPageGroupMenu(Integer group_id);
}