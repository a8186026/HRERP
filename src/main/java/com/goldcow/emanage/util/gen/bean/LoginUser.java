package com.goldcow.emanage.util.gen.bean;

import java.util.List;

import com.goldcow.emanage.util.gen.entity.SysGroup;
import com.goldcow.emanage.util.gen.entity.SysMenu;
import com.goldcow.emanage.util.gen.entity.SysUser;

public class LoginUser {
	private SysUser SysUser;
	private SysGroup group;
	private List<SysMenu> menuList;

	public SysUser getSysUser() {
		return SysUser;
	}

	public void setSysUser(SysUser sysUser) {
		SysUser = sysUser;
	}

	public SysGroup getGroup() {
		return group;
	}

	public void setGroup(SysGroup group) {
		this.group = group;
	}

	public List<SysMenu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<SysMenu> menuList) {
		this.menuList = menuList;
	}

}
