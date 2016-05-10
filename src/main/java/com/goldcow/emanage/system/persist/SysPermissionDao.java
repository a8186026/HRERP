package com.goldcow.emanage.system.persist;

import java.util.List;

import com.goldcow.emanage.util.gen.entity.SysGroupMenu;
import com.goldcow.emanage.util.gen.entity.SysGroupMenuLog;
import com.goldcow.emanage.util.gen.entity.SysMenu;
import com.goldcow.emanage.util.gen.entity.SysPermission;
import com.goldcow.emanage.util.gen.entity.SysUser;
import com.goldcow.sframe.util.mybatis.BaseDao;
import com.goldcow.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface SysPermissionDao extends BaseDao<SysPermission> {
	public List<SysPermission> list(SysPermission bean);
	public List<SysMenu> getGroupMenu(Integer group_id);
	public void add(SysGroupMenu gm);
	public List<SysGroupMenu> getMenuFuncs(Integer id);
	public List<SysGroupMenu> getGroupMenuList(SysGroupMenu bean);
	public void updateSysGroupMenu(SysGroupMenu gm);
	public void deleteByGroupMenuId(Integer group_menu_id);
	
	/** 添加组与权限记录**/
	public int addSysGroupMenuLog(SysGroupMenuLog bean);
	/** 获取所有组合权限的历史记录**/
	public List<SysGroupMenuLog> listLogs();
	/** 得到所有的菜单权限审核记录*/
	public List<SysGroupMenuLog> getSysGroupMenuLogList(SysGroupMenuLog bean);
	public int getSysGroupMenuLogCount(SysGroupMenuLog bean);
}