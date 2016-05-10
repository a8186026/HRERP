package com.goldcow.emanage.util.gen.entity;

import java.util.Date;

import com.goldcow.sframe.util.mybatis.BaseEntity;
/**
 * 控件配置类
 * 
 * @author Zheng
 * @version v1.0
 * @since 2015-05-26
 */
public class SysComponentCtrl implements BaseEntity{
	private static final long serialVersionUID = -3992263243152679571L;
	/** 控件ID */
	private Integer id;
	/** 控件所在PAGE的ID */
	private Integer page_id;
	/** 控件上层包括其label的div的id  */
	private String ctrl_div_id;
	/** 控件中文名  */
	private String ctrl_name;
	/** 控件id */
	private String ctrl_id;
	/** 控件类型 */
	private String ctrl_type;
	/** 创建人 */
	private Integer create_user;
	/** 创建时间 */
	private Date create_time;
	/** 最后修改人 */
	private Integer last_modify_user;
	/** 最后修改时间 */
	private Date last_modify_time;
	/** 状态 0-可用   1-禁用    9-删除 */
	private Integer status;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPage_id() {
		return page_id;
	}
	public void setPage_id(Integer page_id) {
		this.page_id = page_id;
	}
	public String getCtrl_div_id() {
		return ctrl_div_id;
	}
	public void setCtrl_div_id(String ctrl_div_id) {
		this.ctrl_div_id = ctrl_div_id;
	}
	public String getCtrl_name() {
		return ctrl_name;
	}
	public void setCtrl_name(String ctrl_name) {
		this.ctrl_name = ctrl_name;
	}
	public String getCtrl_id() {
		return ctrl_id;
	}
	public void setCtrl_id(String ctrl_id) {
		this.ctrl_id = ctrl_id;
	}
	public String getCtrl_type() {
		return ctrl_type;
	}
	public void setCtrl_type(String ctrl_type) {
		this.ctrl_type = ctrl_type;
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
