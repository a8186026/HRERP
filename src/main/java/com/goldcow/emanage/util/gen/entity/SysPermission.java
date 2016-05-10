package com.goldcow.emanage.util.gen.entity;

import java.util.List;

import com.goldcow.sframe.util.DataGridModel;
import com.goldcow.sframe.util.mybatis.BaseEntity;

/**
 * 权限配置实体类
 * 
 * @author chengrongchang
 * @version v1.0
 * @since 2014-10-17
 */
public class SysPermission extends DataGridModel implements BaseEntity {
	private static final long serialVersionUID = -2201773775200380691L;
	/** 用户组ID */
	private Integer group_id;
	/** 用户组名 */
	private String group_name;
	/** 备注 */
	private String note;
	/** 菜单 */
	private List<SysMenu> gmenus;

	public Integer getGroup_id() {
		return group_id;
	}

	public void setGroup_id(Integer group_id) {
		this.group_id = group_id;
	}

	public String getGroup_name() {
		return group_name;
	}

	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public List<SysMenu> getGmenus() {
		return gmenus;
	}

	public void setGmenus(List<SysMenu> gmenus) {
		this.gmenus = gmenus;
	}

}