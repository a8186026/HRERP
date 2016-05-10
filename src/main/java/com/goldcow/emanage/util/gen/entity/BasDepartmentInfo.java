package com.goldcow.emanage.util.gen.entity;

import java.util.Date;

import com.goldcow.sframe.util.DataGridModel;
import com.goldcow.sframe.util.mybatis.BaseEntity;

/**
 * 部门实体类
 * 
 * @author gaoxiang
 * @version v1.0
 * @since 2015-6-2
 */
public class BasDepartmentInfo extends DataGridModel implements BaseEntity {

	private static final long serialVersionUID = -941735311989810181L;
	/** 部门序号ID */
	private Integer department_id;
	/** 部门编号 */
	private String department_number;
	/** 部门全称 */
	private String department_fullname;
	/** 部门拼音码 */
	private String department_chnpy;
	/** 部门简称 */
	private String department_shortname;
	/** 部门负责人 */
	private String department_chiefperson;
	/** 部门地址 */
	private String department_address;
	/** 部门邮编 */
	private String department_zipcode;
	/** 部门电话 */
	private String department_tel;
	/** 部门传真 */
	private String department_fax;
	/** 部门邮箱 */
	private String department_email;
	/** 部门开户行 */
	private String department_bank;
	/** 部门帐号 */
	private String department_account;
	/** 部门税号 */
	private String department_tax;
	/** 配送部门 */
	private String department_distributionhouse;
	/** 部门应收 */
	private double department_receivable;
	/** 部门应付 */
	private double department_payable;    
	/** 部门零整 0零 1整 3可零可整 */
	private Integer department_zerowhole;
	/** 是否出库审核 */
	private Integer department_deliverycheck;
	/** 是否入库审核 */
	private Integer department_storagecheck;
	/** 部门添加时间 */
	private Date department_create_time;
	/** 部门更新时间 */
	private Date department_update_time;
	/** 会员日时间方式 */
	private String department_memberday_mode;
	/** 会员日日期 */
	private String department_memberday;
	/** 会员日积分倍数 */
	private Integer department_memberday_integral;
	/** 会员价执行日期 */
	private String department_memberprice_time;
	/** 是否按店定价 */
	private Integer department_shopprice;
	/** 按店提成 */
	private Integer department_shopcommission;
	/** 配货权重 */
	private double department_distributionweight; 
	/** 部门结算日期 */
	private Date department_balancedate;
	/** 会员日类型 */
	private String department_memberdaytype;
	/** 是否控制价格 */
	private Integer department_pricecontrol;	
	/** 低温 */
	private double department_lowtemperature;
	/** 高温 */
	private double department_hightemperature;   
	/** 是否冷藏 */
	private Integer department_refrige;  
	/** 门店类型 */
	private String department_storetype;
	/** 企业编码 */
	private String department_enterprisecode; 
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
	/** 折扣可改0不可改1可改 */
	private Integer department_discountModify;
	/** 能开的折扣 */
	private Double deparment_acceptAmountDiscount;
	
	public Double getDeparment_acceptAmountDiscount() {
		return deparment_acceptAmountDiscount;
	}
	public void setDeparment_acceptAmountDiscount(
			Double deparment_acceptAmountDiscount) {
		this.deparment_acceptAmountDiscount = deparment_acceptAmountDiscount;
	}
	public Integer getDepartment_discountModify() {
		return department_discountModify;
	}
	public void setDepartment_discountModify(Integer department_discountModify) {
		this.department_discountModify = department_discountModify;
	}
	public Integer getDepartment_id() {
		return department_id;
	}
	public void setDepartment_id(Integer department_id) {
		this.department_id = department_id;
	}
	public String getDepartment_number() {
		return department_number;
	}
	public void setDepartment_number(String department_number) {
		this.department_number = department_number;
	}
	public String getDepartment_fullname() {
		return department_fullname;
	}
	public void setDepartment_fullname(String department_fullname) {
		this.department_fullname = department_fullname;
	}
	public String getDepartment_chnpy() {
		return department_chnpy;
	}
	public void setDepartment_chnpy(String department_chnpy) {
		this.department_chnpy = department_chnpy;
	}
	public String getDepartment_shortname() {
		return department_shortname;
	}
	public void setDepartment_shortname(String department_shortname) {
		this.department_shortname = department_shortname;
	}
	public String getDepartment_chiefperson() {
		return department_chiefperson;
	}
	public void setDepartment_chiefperson(String department_chiefperson) {
		this.department_chiefperson = department_chiefperson;
	}
	public String getDepartment_address() {
		return department_address;
	}
	public void setDepartment_address(String department_address) {
		this.department_address = department_address;
	}
	public String getDepartment_zipcode() {
		return department_zipcode;
	}
	public void setDepartment_zipcode(String department_zipcode) {
		this.department_zipcode = department_zipcode;
	}
	public String getDepartment_tel() {
		return department_tel;
	}
	public void setDepartment_tel(String department_tel) {
		this.department_tel = department_tel;
	}
	public String getDepartment_fax() {
		return department_fax;
	}
	public void setDepartment_fax(String department_fax) {
		this.department_fax = department_fax;
	}
	public String getDepartment_email() {
		return department_email;
	}
	public void setDepartment_email(String department_email) {
		this.department_email = department_email;
	}
	public String getDepartment_bank() {
		return department_bank;
	}
	public void setDepartment_bank(String department_bank) {
		this.department_bank = department_bank;
	}
	public String getDepartment_account() {
		return department_account;
	}
	public void setDepartment_account(String department_account) {
		this.department_account = department_account;
	}
	public String getDepartment_tax() {
		return department_tax;
	}
	public void setDepartment_tax(String department_tax) {
		this.department_tax = department_tax;
	}
	public String getDepartment_distributionhouse() {
		return department_distributionhouse;
	}
	public void setDepartment_distributionhouse(String department_distributionhouse) {
		this.department_distributionhouse = department_distributionhouse;
	}
	public double getDepartment_receivable() {
		return department_receivable;
	}
	public void setDepartment_receivable(double department_receivable) {
		this.department_receivable = department_receivable;
	}
	public double getDepartment_payable() {
		return department_payable;
	}
	public void setDepartment_payable(double department_payable) {
		this.department_payable = department_payable;
	}
	public Integer getDepartment_zerowhole() {
		return department_zerowhole;
	}
	public void setDepartment_zerowhole(Integer department_zerowhole) {
		this.department_zerowhole = department_zerowhole;
	}
	public Integer getDepartment_deliverycheck() {
		return department_deliverycheck;
	}
	public void setDepartment_deliverycheck(Integer department_deliverycheck) {
		this.department_deliverycheck = department_deliverycheck;
	}
	public Integer getDepartment_storagecheck() {
		return department_storagecheck;
	}
	public void setDepartment_storagecheck(Integer department_storagecheck) {
		this.department_storagecheck = department_storagecheck;
	}
	public Date getDepartment_create_time() {
		return department_create_time;
	}
	public void setDepartment_create_time(Date department_create_time) {
		this.department_create_time = department_create_time;
	}
	public Date getDepartment_update_time() {
		return department_update_time;
	}
	public void setDepartment_update_time(Date department_update_time) {
		this.department_update_time = department_update_time;
	}
	public String getDepartment_memberday_mode() {
		return department_memberday_mode;
	}
	public void setDepartment_memberday_mode(String department_memberday_mode) {
		this.department_memberday_mode = department_memberday_mode;
	}
	public String getDepartment_memberday() {
		return department_memberday;
	}
	public void setDepartment_memberday(String department_memberday) {
		this.department_memberday = department_memberday;
	}
	public Integer getDepartment_memberday_integral() {
		return department_memberday_integral;
	}
	public void setDepartment_memberday_integral(Integer department_memberday_integral) {
		this.department_memberday_integral = department_memberday_integral;
	}
	public String getDepartment_memberprice_time() {
		return department_memberprice_time;
	}
	public void setDepartment_memberprice_time(String department_memberprice_time) {
		this.department_memberprice_time = department_memberprice_time;
	}
	public Integer getDepartment_shopprice() {
		return department_shopprice;
	}
	public void setDepartment_shopprice(Integer department_shopprice) {
		this.department_shopprice = department_shopprice;
	}
	public Integer getDepartment_shopcommission() {
		return department_shopcommission;
	}
	public void setDepartment_shopcommission(Integer department_shopcommission) {
		this.department_shopcommission = department_shopcommission;
	}
	public double getDepartment_distributionweight() {
		return department_distributionweight;
	}
	public void setDepartment_distributionweight(double department_distributionweight) {
		this.department_distributionweight = department_distributionweight;
	}
	public Date getDepartment_balancedate() {
		return department_balancedate;
	}
	public void setDepartment_balancedate(Date department_balancedate) {
		this.department_balancedate = department_balancedate;
	}
	public String getDepartment_memberdaytype() {
		return department_memberdaytype;
	}
	public void setDepartment_memberdaytype(String department_memberdaytype) {
		this.department_memberdaytype = department_memberdaytype;
	}
	public Integer getDepartment_pricecontrol() {
		return department_pricecontrol;
	}
	public void setDepartment_pricecontrol(Integer department_pricecontrol) {
		this.department_pricecontrol = department_pricecontrol;
	}
	public double getDepartment_lowtemperature() {
		return department_lowtemperature;
	}
	public void setDepartment_lowtemperature(double department_lowtemperature) {
		this.department_lowtemperature = department_lowtemperature;
	}
	public double getDepartment_hightemperature() {
		return department_hightemperature;
	}
	public void setDepartment_hightemperature(double department_hightemperature) {
		this.department_hightemperature = department_hightemperature;
	}
	public Integer getDepartment_refrige() {
		return department_refrige;
	}
	public void setDepartment_refrige(Integer department_refrige) {
		this.department_refrige = department_refrige;
	}
	public String getDepartment_storetype() {
		return department_storetype;
	}
	public void setDepartment_storetype(String department_storetype) {
		this.department_storetype = department_storetype;
	}
	public String getDepartment_enterprisecode() {
		return department_enterprisecode;
	}
	public void setDepartment_enterprisecode(String department_enterprisecode) {
		this.department_enterprisecode = department_enterprisecode;
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

