package com.goldcow.emanage.util.gen.entity;

import java.util.Date;

import com.goldcow.sframe.util.DataGridModel;
import com.goldcow.sframe.util.mybatis.BaseEntity;

public class BasLicenseInfo extends DataGridModel implements BaseEntity{
	/**
	 * 证照信息实体类
	 * 
	 * @author gaoxiang
	 * @version v1.0
	 * @since 2015-6-30
	 */
	private static final long serialVersionUID = -7970636854771442541L;
	/** 证照ID */
	private Integer license_id;
	/** 证照关联ID,包括产品id，供方id等 */
	private Integer license_type_id;
	/** 证照名称 */
	private String license_name;
	/** 证照类型,包括产品证照，供方证照等 */
	private String license_type;
	/** 证照编号 */
	private String license_no;
	/** 证照认证时间 */
	private Date license_starttime;
	/** 证照有效期至 */
	private Date license_endtime;
	/** 证照提示天数 */
	private Integer license_tipdays;
	/** 发证机关 */
	private String license_issuer;
	/** 证照其他 */
	private String license_other;
	/** 证照备注 */
	private String license_remark;
	/** 证照范围 */
	private String license_scope;
	/** 证照性质 */
	private String license_property;
	/** 证照资金 */
	private Integer license_fund;
	/** 证照相关人员 */
	private String license_person;
	/** 年检截至 */
	private Date license_checkyeartime;
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
	public Integer getLicense_id() {
		return license_id;
	}
	public void setLicense_id(Integer license_id) {
		this.license_id = license_id;
	}
	public Integer getLicense_type_id() {
		return license_type_id;
	}
	public void setLicense_type_id(Integer license_type_id) {
		this.license_type_id = license_type_id;
	}
	public String getLicense_name() {
		return license_name;
	}
	public void setLicense_name(String license_name) {
		this.license_name = license_name;
	}
	public String getLicense_type() {
		return license_type;
	}
	public void setLicense_type(String license_type) {
		this.license_type = license_type;
	}
	public String getLicense_no() {
		return license_no;
	}
	public void setLicense_no(String license_no) {
		this.license_no = license_no;
	}
	public Date getLicense_starttime() {
		return license_starttime;
	}
	public void setLicense_starttime(Date license_starttime) {
		this.license_starttime = license_starttime;
	}
	public Date getLicense_endtime() {
		return license_endtime;
	}
	public void setLicense_endtime(Date license_endtime) {
		this.license_endtime = license_endtime;
	}
	public Integer getLicense_tipdays() {
		return license_tipdays;
	}
	public void setLicense_tipdays(Integer license_tipdays) {
		this.license_tipdays = license_tipdays;
	}
	public String getLicense_issuer() {
		return license_issuer;
	}
	public void setLicense_issuer(String license_issuer) {
		this.license_issuer = license_issuer;
	}
	public String getLicense_other() {
		return license_other;
	}
	public void setLicense_other(String license_other) {
		this.license_other = license_other;
	}
	public String getLicense_remark() {
		return license_remark;
	}
	public void setLicense_remark(String license_remark) {
		this.license_remark = license_remark;
	}
	public String getLicense_scope() {
		return license_scope;
	}
	public void setLicense_scope(String license_scope) {
		this.license_scope = license_scope;
	}
	public String getLicense_property() {
		return license_property;
	}
	public void setLicense_property(String license_property) {
		this.license_property = license_property;
	}
	public Integer getLicense_fund() {
		return license_fund;
	}
	public void setLicense_fund(Integer license_fund) {
		this.license_fund = license_fund;
	}
	public String getLicense_person() {
		return license_person;
	}
	public void setLicense_person(String license_person) {
		this.license_person = license_person;
	}
	public Date getLicense_checkyeartime() {
		return license_checkyeartime;
	}
	public void setLicense_checkyeartime(Date license_checkyeartime) {
		this.license_checkyeartime = license_checkyeartime;
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
