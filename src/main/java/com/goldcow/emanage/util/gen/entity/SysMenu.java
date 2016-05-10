package com.goldcow.emanage.util.gen.entity;

import java.util.Date;

import com.goldcow.sframe.util.DataGridModel;
import com.goldcow.sframe.util.mybatis.BaseEntity;

/**
 * 菜单实体类
 * 
 * @author chengrongchang
 * @version v1.0
 * @since 2014-10-16
 */
public class SysMenu extends DataGridModel implements BaseEntity {
	private static final long serialVersionUID = -4924515774096864613L;

	/** ID */
	private Integer menu_id;
	/** 菜单名称 */
	private String menu_name;
	/** 菜单链接 */
	private String menu_url;
	/** 菜单编码 */
	private String menu_code;
	/** 上级菜单 */
	private Integer parent_menu;
	/** 顺序 */
	private Integer orders;
	/** 备注 */
	private String note;
	/** 状态 0启用 1停用 9删除 */
	private Integer status;
	/** 创建人 */
	private Integer create_user;
	/** 创建时间 */
	private Date create_time;
	/** 最后修改人 */
	private Integer last_modify_user;
	/** 最后修改时间 */
	private Date last_modify_time;

	/* 菜单功能 */
	private Integer menu_func;

	public Integer getMenu_id() {
		return menu_id;
	}

	public void setMenu_id(Integer menu_id) {
		this.menu_id = menu_id;
	}

	public String getMenu_name() {
		return menu_name;
	}

	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}

	public String getMenu_url() {
		return menu_url;
	}

	public void setMenu_url(String menu_url) {
		this.menu_url = menu_url;
	}

	public String getMenu_code() {
		return menu_code;
	}

	public void setMenu_code(String menu_code) {
		this.menu_code = menu_code;
	}

	public Integer getParent_menu() {
		return parent_menu;
	}

	public void setParent_menu(Integer parent_menu) {
		this.parent_menu = parent_menu;
	}

	public Integer getOrders() {
		return orders;
	}

	public void setOrders(Integer orders) {
		this.orders = orders;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public Integer getLast_modify_user() {
		return last_modify_user;
	}

	public void setLast_modify_user(Integer last_modify_user) {
		this.last_modify_user = last_modify_user;
	}

	public Date getLast_modify_time() {
		return last_modify_time;
	}

	public void setLast_modify_time(Date last_modify_time) {
		this.last_modify_time = last_modify_time;
	}

	public Integer getMenu_func() {
		return menu_func;
	}

	public void setMenu_func(Integer menu_func) {
		this.menu_func = menu_func;
	}

}