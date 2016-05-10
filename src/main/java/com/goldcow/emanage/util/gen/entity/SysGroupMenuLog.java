package com.goldcow.emanage.util.gen.entity;

import java.util.Date;

import com.goldcow.sframe.util.DataGridModel;
import com.goldcow.sframe.util.mybatis.BaseEntity;

/**
 * 组与菜单权限历史记录
 * 
 * @author wubin
 * @version v1.0
 * @since 2015-6-29
 */
public class SysGroupMenuLog extends DataGridModel implements BaseEntity {
	private static final long serialVersionUID = -5813544870364252435L;
	/** ID */
	private Integer group_menu_id;
	/** 用户组 */
	private Integer group_id;
	/** 菜单 */
	private Integer menu_id;
	/** 创建人 */
	private Integer create_user;
	/** 创建时间 */
	private Date create_time;
	/** 状态 */
	private Integer status;
	/** 备注 */
	private String remark;
	/* 组名*/
	private String group_name;
	/* 菜单名称 */
	private String menu_name;
	/* 菜单编码 */
	private String menu_code;
	/* 上级菜单 */
	private String parent_menu;
	
	
	public Integer getGroup_menu_id() {
		return group_menu_id;
	}
	public void setGroup_menu_id(Integer group_menu_id) {
		this.group_menu_id = group_menu_id;
	}
	public Integer getGroup_id() {
		return group_id;
	}
	public void setGroup_id(Integer group_id) {
		this.group_id = group_id;
	}
	public Integer getMenu_id() {
		return menu_id;
	}
	public void setMenu_id(Integer menu_id) {
		this.menu_id = menu_id;
	}
	public Integer getCreate_user() {
		return create_user;
	}
	public void setCreate_user(Integer create_user) {
		this.create_user = create_user;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getGroup_name() {
		return group_name;
	}
	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}
	public String getMenu_name() {
		return menu_name;
	}
	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}
	public String getMenu_code() {
		return menu_code;
	}
	public void setMenu_code(String menu_code) {
		this.menu_code = menu_code;
	}
	public String getParent_menu() {
		return parent_menu;
	}
	public void setParent_menu(String parent_menu) {
		this.parent_menu = parent_menu;
	}

}