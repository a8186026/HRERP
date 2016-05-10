package com.goldcow.emanage.util.gen.entity.valueObject.SysUser;

import java.util.Date;

import com.goldcow.sframe.util.DataGridModel;
import com.goldcow.sframe.util.mybatis.BaseEntity;

/**
 * 用户实体类
 * 
 * @author chenyuxuan
 * @version v1.0
 * @since 2015-5-27
 */
/**
 * @author Administrator
 *
 */
public class UserHabitsVO implements BaseEntity {
	private static final long serialVersionUID = 1L;
	/** ID */
	private Integer user_id;
	/** 页面ID(页面) */
	private String page_id;
	/** 控件ID(页面) */
	private String ctrl_id;
	/** 字段名称 */
	private String field;
	/** 字段名称 */
	private String fieldName;
	/** 字段是否可排序 */
	private String habit_sortable;
	/** 字段是否隐藏 */
	private String habit_hidden;
	/** 字段宽度 */
	private Integer width;
	/** 字段顺序 */
	private Integer order;
	/** 修改人ID */
	private Integer last_modify_user;
	/** 修改时间 */
	private Date last_modify_time;
	
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
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getHabit_sortable() {
		return habit_sortable;
	}
	public void setHabit_sortable(String habit_sortable) {
		this.habit_sortable = habit_sortable;
	}
	public String getHabit_hidden() {
		return habit_hidden;
	}
	public void setHabit_hidden(String habit_hidden) {
		this.habit_hidden = habit_hidden;
	}
	public Integer getWidth() {
		return width;
	}
	public void setWidth(Integer width) {
		this.width = width;
	}
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
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