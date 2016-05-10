package com.goldcow.emanage.util.gen.entity.valueObject.SysCheck;

import java.util.Date;

import com.goldcow.sframe.util.DataGridModel;
import com.goldcow.sframe.util.mybatis.BaseEntity;

/**
 * 系统审核
 * 
 * @author wubin
 * @version v1.0
 * @since 2015-6-16
 */
public class SysCheckVO extends DataGridModel implements BaseEntity{
	private static final long serialVersionUID = -7970636854771442541L;
	/** ID */
	private Integer check_id;
	/** 名称 */
	private String check_name;
	/** 编码 */
	private String check_code;
	/** 审核次数 */
	private Integer check_times;
	/** 相关机构意见*/
	private String check_titles;
	/** 审核组*/
	private String check_groups;
	/** 审核id集合*/
	private String check_title_ids;
	/** 所有审核类型*/
	private String check_types;
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
	
	
	public Integer getCheck_id() {
		return check_id;
	}
	public void setCheck_id(Integer check_id) {
		this.check_id = check_id;
	}
	public String getCheck_name() {
		return check_name;
	}
	public void setCheck_name(String check_name) {
		this.check_name = check_name;
	}
	public String getCheck_code() {
		return check_code;
	}
	public void setCheck_code(String check_code) {
		this.check_code = check_code;
	}
	public Integer getCheck_times() {
		return check_times;
	}
	public void setCheck_times(Integer check_times) {
		this.check_times = check_times;
	}
	public String getCheck_titles() {
		return check_titles;
	}
	public void setCheck_titles(String check_titles) {
		this.check_titles = check_titles;
	}
	public String getCheck_groups() {
		return check_groups;
	}
	public void setCheck_groups(String check_groups) {
		this.check_groups = check_groups;
	}
	public String getCheck_title_ids() {
		return check_title_ids;
	}
	public void setCheck_title_ids(String check_title_ids) {
		this.check_title_ids = check_title_ids;
	}
	public String getCheck_types() {
		return check_types;
	}
	public void setCheck_types(String check_types) {
		this.check_types = check_types;
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
