package com.goldcow.emanage.util.gen.entity;

import java.util.Date;

import com.goldcow.sframe.util.DataGridModel;
import com.goldcow.sframe.util.mybatis.BaseEntity;

public class SysParameterSub extends DataGridModel implements BaseEntity {
	private static final long serialVersionUID = 1L;
	/** ID */
	private Integer param_sub_id;
	/** 名称 */
	private String param_sub_name;
	/** 值 */
	private String param_sub_value;
	/** 顺序 */
	private String orders;
	/** 父项目 */
	private Integer param_parent;
	/** 备注 */
	private String param_sub_note;
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

	public Integer getParam_sub_id() {
		return param_sub_id;
	}

	public void setParam_sub_id(Integer param_sub_id) {
		this.param_sub_id = param_sub_id;
	}

	public String getParam_sub_name() {
		return param_sub_name;
	}

	public void setParam_sub_name(String param_sub_name) {
		this.param_sub_name = param_sub_name;
	}

	public String getParam_sub_value() {
		return param_sub_value;
	}

	public void setParam_sub_value(String param_sub_value) {
		this.param_sub_value = param_sub_value;
	}

	public String getOrders() {
		return orders;
	}

	public void setOrders(String orders) {
		this.orders = orders;
	}

	public Integer getParam_parent() {
		return param_parent;
	}

	public void setParam_parent(Integer param_parent) {
		this.param_parent = param_parent;
	}

	public String getParam_sub_note() {
		return param_sub_note;
	}

	public void setParam_sub_note(String param_sub_note) {
		this.param_sub_note = param_sub_note;
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