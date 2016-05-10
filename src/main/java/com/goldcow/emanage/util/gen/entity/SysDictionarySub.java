package com.goldcow.emanage.util.gen.entity;

import java.util.Date;

import com.goldcow.sframe.util.DataGridModel;
import com.goldcow.sframe.util.mybatis.BaseEntity;

public class SysDictionarySub extends DataGridModel implements BaseEntity {
	private static final long serialVersionUID = 1L;
	/** ID */
	private Integer dict_sub_id;
	/** 名称 */
	private String dict_sub_name;
	/** 值 */
	private String dict_sub_value;
	/** 顺序 */
	private Integer orders;
	/** 父项目 */
	private Integer dict_parent;
	/** 备注 */
	private String dict_sub_note;
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

	public Integer getDict_sub_id() {
		return dict_sub_id;
	}

	public void setDict_sub_id(Integer dict_sub_id) {
		this.dict_sub_id = dict_sub_id;
	}

	public String getDict_sub_name() {
		return dict_sub_name;
	}

	public void setDict_sub_name(String dict_sub_name) {
		this.dict_sub_name = dict_sub_name;
	}

	public String getDict_sub_value() {
		return dict_sub_value;
	}

	public void setDict_sub_value(String dict_sub_value) {
		this.dict_sub_value = dict_sub_value;
	}

	public String getDict_sub_note() {
		return dict_sub_note;
	}

	public void setDict_sub_note(String dict_sub_note) {
		this.dict_sub_note = dict_sub_note;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getOrders() {
		return orders;
	}

	public void setOrders(Integer orders) {
		this.orders = orders;
	}

	public Integer getDict_parent() {
		return dict_parent;
	}

	public void setDict_parent(Integer dict_parent) {
		this.dict_parent = dict_parent;
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