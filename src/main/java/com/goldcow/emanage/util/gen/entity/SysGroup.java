package com.goldcow.emanage.util.gen.entity;

import java.util.Date;

import com.goldcow.sframe.util.DataGridModel;
import com.goldcow.sframe.util.mybatis.BaseEntity;

/**
 * 用户组实体类
 * 
 * @author chengrongchang
 * @version v1.0
 * @since 2014-10-15
 */
public class SysGroup extends DataGridModel implements BaseEntity {
	private static final long serialVersionUID = -6839480669389995082L;
	
	/** ID */
	private Integer group_id;
	/** 用户组名 */
	private String group_name;
	/** 默认用户组 */
	private Integer default_group;
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

	public Integer getDefault_group() {
		return default_group;
	}

	public void setDefault_group(Integer default_group) {
		this.default_group = default_group;
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
}