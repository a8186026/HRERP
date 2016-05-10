package com.goldcow.emanage.util.gen.entity;

import java.util.Date;

import com.goldcow.sframe.util.DataGridModel;
import com.goldcow.sframe.util.mybatis.BaseEntity;

/**
 * 用户习惯类
 * 
 * @author wubin
 * @version v1.0
 * @since 2015-6-10
 *
 */
public class SysUserHabit extends DataGridModel implements BaseEntity {
	private static final long serialVersionUID = 1L;
	/** ID */
	private Integer habit_id;
	/** 用户ID */
	private Integer user_id;
	/** 页面ID(jsp) */
	private String page_id;
	/** 控件ID(jsp) */
	private String ctrl_id;
	/** 字段名称 (sql)-*/
	private String habit_field;
	/** 字段顺序 */
	private Integer habit_order;
	/** 字段宽度 */
	private Integer habit_width;
	/** 字段中文名称 */
	private String habit_field_name;
	/** 字段是否可排序 */
	private String habit_sortable;
	/** 字段是否隐藏 */
	private String habit_hidden;
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

	public Integer getHabit_id() {
		return habit_id;
	}
	public void setHabit_id(Integer habit_id) {
		this.habit_id = habit_id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getPage_id() {
		return page_id;
	}
	public void setPage_id(String page_id) {
		this.page_id = page_id;
	}
	public String getCtrl_id() {
		return ctrl_id;
	}
	public void setCtrl_id(String ctrl_id) {
		this.ctrl_id = ctrl_id;
	}
	public String getHabit_field() {
		return habit_field;
	}
	public void setHabit_field(String habit_field) {
		this.habit_field = habit_field;
	}
	public Integer getHabit_order() {
		return habit_order;
	}
	public void setHabit_order(Integer habit_order) {
		this.habit_order = habit_order;
	}
	public Integer getHabit_width() {
		return habit_width;
	}
	public void setHabit_width(Integer habit_width) {
		this.habit_width = habit_width;
	}
	public String getHabit_field_name() {
		return habit_field_name;
	}
	public void setHabit_field_name(String habit_field_name) {
		this.habit_field_name = habit_field_name;
	}
	public String isHabit_sortable() {
		return habit_sortable;
	}
	public void setHabit_sortable(String habit_sortable) {
		this.habit_sortable = habit_sortable;
	}
	public String isHabit_hidden() {
		return habit_hidden;
	}
	public void setHabit_hidden(String habit_hidden) {
		this.habit_hidden = habit_hidden;
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

	
}