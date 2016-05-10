package com.goldcow.emanage.util.gen.entity;

import java.util.Date;

import com.goldcow.sframe.util.DataGridModel;
import com.goldcow.sframe.util.mybatis.BaseEntity;
/**
 * 公司信息实体类
 * 
 * @author wubin
 * @version v1.0
 * @since 2016-1-4
 */
public class CompanyInfo extends DataGridModel implements BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 公司id*/
	private Integer company_id;
	/** 企业全称*/
	private String company_fullName;
	/** 公司许可证编号*/
	private String company_lisenceCode;
	/** 企业编号*/
	private String company_code;
	/** 国家药监码*/
	private String company_drugCode;
	/** 经营禁售*/
	private String company_forbidDrug;
	/** 药监票号开始*/
	private String company_ticketStart;
	/** 创建人*/
	private Integer create_user;
	/** 创建时间 */
	private Date create_time;
	/** 最后修改人*/
	private Integer last_modify_user;
	/** 最后修改时间*/
	private Date last_modify_time;
	/** 状态 0启用 1停用 9删除 */
	private Integer status;
	
	public Integer getCompany_id() {
		return company_id;
	}
	public void setCompany_id(Integer company_id) {
		this.company_id = company_id;
	}
	public String getCompany_fullName() {
		return company_fullName;
	}
	public void setCompany_fullName(String company_fullName) {
		this.company_fullName = company_fullName;
	}
	public String getCompany_lisenceCode() {
		return company_lisenceCode;
	}
	public void setCompany_lisenceCode(String company_lisenceCode) {
		this.company_lisenceCode = company_lisenceCode;
	}
	public String getCompany_code() {
		return company_code;
	}
	public void setCompany_code(String company_code) {
		this.company_code = company_code;
	}
	public String getCompany_drugCode() {
		return company_drugCode;
	}
	public void setCompany_drugCode(String company_drugCode) {
		this.company_drugCode = company_drugCode;
	}
	public String getCompany_forbidDrug() {
		return company_forbidDrug;
	}
	public void setCompany_forbidDrug(String company_forbidDrug) {
		this.company_forbidDrug = company_forbidDrug;
	}
	public String getCompany_ticketStart() {
		return company_ticketStart;
	}
	public void setCompany_ticketStart(String company_ticketStart) {
		this.company_ticketStart = company_ticketStart;
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
}