package com.goldcow.emanage.util.gen.entity;

import java.util.Date;

import com.goldcow.sframe.util.DataGridModel;
import com.goldcow.sframe.util.mybatis.BaseEntity;
/**
 * 入库购进发票
 * 
 * @author zhanxiaotong
 * @version v1.0
 * @since 2015-12-15
 */
public class FinancialInvoice extends DataGridModel implements BaseEntity{
	private static final long serialVersionUID = 1L;
	/** 入库发票主序号*/
	private Integer rkfp_id;
	/** 发票票号*/
	private String rkfp_ticket;
	/** 发票号*/
	private String rkfp_code;
	/** 发票税率*/
	private Integer rkfp_taxrate;
	/** 单位名头*/
	private String rkfp_accepter;
	/** 开票单位*/
	private String rkfp_supplier;
	/** 登记日期*/
	private Date rkfp_registerTime;
	/** 发票日期*/
	private Date rkfp_invoiceDate;
	/** 发票总金额*/
	private Double rkfp_sumMoney;
	/** 发票总数量*/
	private Double rkfp_sumNum;
	/** 摘要*/
	private String rkfp_abstract;
	/** 种类*/
	private String rkfp_category;
	/** 折让金额*/
	private Double rkfp_discount;
	/** 是否作废*/
	private Integer rkfp_cancellation;
	/** 备注*/
	private String rkfp_remark;
	/** 创建人*/
	private Integer rkfp_creater;
	/** 创建时间 */
	private Date rkfp_createTime;
	/** 最后修改人*/
	private Integer rkfp_last_modify_user;
	/** 最后修改时间*/
	private Date rkfp_last_modify_time;

	/** 开户关联ID,包括库房id，供方id等*/
	private Integer sup_id;
	
	
	public Integer getRkfp_id() {
		return rkfp_id;
	}
	public void setRkfp_id(Integer rkfp_id) {
		this.rkfp_id = rkfp_id;
	}
	public String getRkfp_ticket() {
		return rkfp_ticket;
	}
	public void setRkfp_ticket(String rkfp_ticket) {
		this.rkfp_ticket = rkfp_ticket;
	}
	public String getRkfp_code() {
		return rkfp_code;
	}
	public void setRkfp_code(String rkfp_code) {
		this.rkfp_code = rkfp_code;
	}
	public Integer getRkfp_taxrate() {
		return rkfp_taxrate;
	}
	public void setRkfp_taxrate(Integer rkfp_taxrate) {
		this.rkfp_taxrate = rkfp_taxrate;
	}
	public String getRkfp_accepter() {
		return rkfp_accepter;
	}
	public void setRkfp_accepter(String rkfp_accepter) {
		this.rkfp_accepter = rkfp_accepter;
	}
	public String getRkfp_supplier() {
		return rkfp_supplier;
	}
	public void setRkfp_supplier(String rkfp_supplier) {
		this.rkfp_supplier = rkfp_supplier;
	}
	public Integer getSup_id() {
		return sup_id;
	}
	public void setSup_id(Integer sup_id) {
		this.sup_id = sup_id;
	}
	public Date getRkfp_registerTime() {
		return rkfp_registerTime;
	}
	public void setRkfp_registerTime(Date rkfp_registerTime) {
		this.rkfp_registerTime = rkfp_registerTime;
	}
	public Date getRkfp_invoiceDate() {
		return rkfp_invoiceDate;
	}
	public void setRkfp_invoiceDate(Date rkfp_invoiceDate) {
		this.rkfp_invoiceDate = rkfp_invoiceDate;
	}
	public Double getRkfp_sumMoney() {
		return rkfp_sumMoney;
	}
	public void setRkfp_sumMoney(Double rkfp_sumMoney) {
		this.rkfp_sumMoney = rkfp_sumMoney;
	}
	public Double getRkfp_sumNum() {
		return rkfp_sumNum;
	}
	public void setRkfp_sumNum(Double rkfp_sumNum) {
		this.rkfp_sumNum = rkfp_sumNum;
	}
	public String getRkfp_abstract() {
		return rkfp_abstract;
	}
	public void setRkfp_abstract(String rkfp_abstract) {
		this.rkfp_abstract = rkfp_abstract;
	}
	public String getRkfp_category() {
		return rkfp_category;
	}
	public void setRkfp_category(String rkfp_category) {
		this.rkfp_category = rkfp_category;
	}
	public Double getRkfp_discount() {
		return rkfp_discount;
	}
	public void setRkfp_discount(Double rkfp_discount) {
		this.rkfp_discount = rkfp_discount;
	}
	public Integer getRkfp_cancellation() {
		return rkfp_cancellation;
	}
	public void setRkfp_cancellation(Integer rkfp_cancellation) {
		this.rkfp_cancellation = rkfp_cancellation;
	}
	public String getRkfp_remark() {
		return rkfp_remark;
	}
	public void setRkfp_remark(String rkfp_remark) {
		this.rkfp_remark = rkfp_remark;
	}
	public Integer getRkfp_creater() {
		return rkfp_creater;
	}
	public void setRkfp_creater(Integer rkfp_creater) {
		this.rkfp_creater = rkfp_creater;
	}
	public Date getRkfp_createTime() {
		return rkfp_createTime;
	}
	public void setRkfp_createTime(Date rkfp_createTime) {
		this.rkfp_createTime = rkfp_createTime;
	}
	public Integer getRkfp_last_modify_user() {
		return rkfp_last_modify_user;
	}
	public void setRkfp_last_modify_user(Integer rkfp_last_modify_user) {
		this.rkfp_last_modify_user = rkfp_last_modify_user;
	}
	public Date getRkfp_last_modify_time() {
		return rkfp_last_modify_time;
	}
	public void setRkfp_last_modify_time(Date rkfp_last_modify_time) {
		this.rkfp_last_modify_time = rkfp_last_modify_time;
	}
	

}