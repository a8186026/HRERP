package com.goldcow.emanage.util.gen.entity;

import java.util.Date;

import com.goldcow.sframe.util.DataGridModel;
import com.goldcow.sframe.util.mybatis.BaseEntity;

public class BasCheck extends DataGridModel implements BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7970636854771442541L;
	/** ID */
	private Integer check_id;
	/** 审核类型，包括产品审核，供方审核，销方审核等 */
	private String check_type;
	/** 审核类型id */
	private Integer check_type_id;
	/** 审核id 命名规范：审批对象id_审批条目id */
	private String check_comment_id;
	/** 审核意见*/
	private String check_content;
	/** 审核人id*/
	private Integer check_person_id;
	/** 审核人name*/
	private String check_person_name;
	/** 当前审批记录是否处于可修改状态（默认为0，可修改为1）*/
	private Integer check_modified;
	/** 审核组id*/
	private Integer check_group_id;
	/** 审核结果 0-同意 1-不同意 */
	private Integer check_result;
	/** 审核时间 */
	private Date check_time;
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
	public Integer getCheck_id() {
		return check_id;
	}
	public void setCheck_id(Integer check_id) {
		this.check_id = check_id;
	}
	public String getCheck_type() {
		return check_type;
	}
	public void setCheck_type(String check_type) {
		this.check_type = check_type;
	}
	public Integer getCheck_type_id() {
		return check_type_id;
	}
	public void setCheck_type_id(Integer check_type_id) {
		this.check_type_id = check_type_id;
	}
	public String getCheck_comment_id() {
		return check_comment_id;
	}
	public void setCheck_comment_id(String check_comment_id) {
		this.check_comment_id = check_comment_id;
	}

	public String getCheck_content() {
		return check_content;
	}
	public void setCheck_content(String check_content) {
		this.check_content = check_content;
	}
	public Integer getCheck_person_id() {
		return check_person_id;
	}
	public void setCheck_person_id(Integer check_person_id) {
		this.check_person_id = check_person_id;
	}
	public Integer getCheck_result() {
		return check_result;
	}
	public void setCheck_result(Integer check_result) {
		this.check_result = check_result;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getCheck_group_id() {
		return check_group_id;
	}
	public void setCheck_group_id(Integer check_group_id) {
		this.check_group_id = check_group_id;
	}
	public String getCheck_person_name() {
		return check_person_name;
	}
	public void setCheck_person_name(String check_person_name) {
		this.check_person_name = check_person_name;
	}
	public Date getCheck_time() {
		return check_time;
	}
	public void setCheck_time(Date check_time) {
		this.check_time = check_time;
	}
	public Integer getCheck_modified() {
		return check_modified;
	}
	public void setCheck_modified(Integer check_modified) {
		this.check_modified = check_modified;
	}
	
}
