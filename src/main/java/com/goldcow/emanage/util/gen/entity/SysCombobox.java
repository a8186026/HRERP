package com.goldcow.emanage.util.gen.entity;

import java.util.Date;

import com.goldcow.sframe.util.DataGridModel;
import com.goldcow.sframe.util.mybatis.BaseEntity;

/**
 * 下拉框实体类
 * 
 * @author wubin
 * @version v1.0
 * @since 2015-6-1
 */
public class SysCombobox extends DataGridModel implements BaseEntity {
	private static final long serialVersionUID = 1L;
	/** 下拉框ID */
	private Integer cbs_id;
	/** 下拉框父级所属 */
	private Integer cbs_pid;
	/** 下拉框编号 */
	private String cbs_code;
	/** 下拉框名称 */
	private String cbs_chn;
	/** 下拉框说明 */
	private String cbs_dec;
	/** 下拉框类型 */
	private String cbs_type;
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
	public Integer getCbs_id() {
		return cbs_id;
	}
	public void setCbs_id(Integer cbs_id) {
		this.cbs_id = cbs_id;
	}
	public Integer getCbs_pid() {
		return cbs_pid;
	}
	public void setCbs_pid(Integer cbs_pid) {
		this.cbs_pid = cbs_pid;
	}
	public String getCbs_code() {
		return cbs_code;
	}
	public void setCbs_code(String cbs_code) {
		this.cbs_code = cbs_code;
	}
	public String getCbs_chn() {
		return cbs_chn;
	}
	public void setCbs_chn(String cbs_chn) {
		this.cbs_chn = cbs_chn;
	}
	public String getCbs_dec() {
		return cbs_dec;
	}
	public void setCbs_dec(String cbs_dec) {
		this.cbs_dec = cbs_dec;
	}
	public String getCbs_type() {
		return cbs_type;
	}
	public void setCbs_type(String cbs_type) {
		this.cbs_type = cbs_type;
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
