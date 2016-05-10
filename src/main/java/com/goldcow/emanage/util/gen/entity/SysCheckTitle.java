package com.goldcow.emanage.util.gen.entity;

import java.util.Date;

import com.goldcow.sframe.util.DataGridModel;
import com.goldcow.sframe.util.mybatis.BaseEntity;

public class SysCheckTitle extends DataGridModel implements BaseEntity{

	private static final long serialVersionUID = 1L;
	/** ID */
	private Integer check_title_id;
	/** 审核ID */
	private Integer check_id;
	/** 审核名称*/
	private String check_title;
	/** 审核组*/
	private Integer check_group;
	/** 审核顺序*/
	private Integer check_order;
	/** 审核类型0流水审核，1平行审核*/
	private Integer check_type;
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

	public Integer getCheck_title_id() {
		return check_title_id;
	}
	public void setCheck_title_id(Integer check_title_id) {
		this.check_title_id = check_title_id;
	}
	public Integer getCheck_id() {
		return check_id;
	}
	public void setCheck_id(Integer check_id) {
		this.check_id = check_id;
	}
	public String getCheck_title() {
		return check_title;
	}
	public void setCheck_title(String check_title) {
		this.check_title = check_title;
	}
	public Integer getCheck_group() {
		return check_group;
	}
	public void setCheck_group(Integer check_group) {
		this.check_group = check_group;
	}
	public Integer getCheck_order() {
		return check_order;
	}
	public void setCheck_order(Integer check_order) {
		this.check_order = check_order;
	}
	public Integer getCheck_type() {
		return check_type;
	}
	public void setCheck_type(Integer check_type) {
		this.check_type = check_type;
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
