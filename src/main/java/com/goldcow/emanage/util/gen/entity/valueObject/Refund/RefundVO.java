package com.goldcow.emanage.util.gen.entity.valueObject.Refund;
import java.util.Date;

import com.goldcow.emanage.util.gen.entity.Refund;
import com.goldcow.sframe.util.mybatis.BaseEntity;

	/**
	*	*@author 
	*/

	public class RefundVO  extends Refund implements BaseEntity{
		
	private static final long serialVersionUID = 1L;
	
	/**
	 * 工具字段 数据库没有实体对应
	 * @return
	 */
	
	//产品名称
	private String product_name;
	//库房数量 
	private Double stock_storageNumber;
	/** 供方编码 */
	private String sup_code;
	/**产品编码*/
	private String product_code;
	/**供方全称*/
	private String sup_name;

	/** 规格*/
	private String product_specification;
	/** 包装量*/
	private String product_packingamount;
	/** 单位*/
	private String product_unit;
	/** 摘要*/
	private String stock_intakeBrief;
	/** 剂型*/
	private String product_dosagetype;	
	/** 简介*/
	private String product_description;
	/** 批准文号*/
	private String product_approvalnum;
	/** 规格*/
	private String product_category;
	/** 联系人 */
	private String sup_ctactperson;
	/** 库房 */
	private String stock_storage;
	/** 入库票号 */
	private String stock_intakeTicket;
	/** 入库小号 */
	private Integer stock_intakeSmallNumber;
	/**批号*/
	private String stock_batchCode;
	/** 失效期 */
	private Date stock_invalidDate;
	/** 批号小号 */
	private String stock_batchSmallCode;
	/** 灭菌批号 */
	private String stock_sterilizationbnum;
	/** 生产日期 */
	private Date stock_produceDate;
	/** 客户全称 */
	private String sup_customername;
	/** 电话 */
	private String sup_tel;
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public Double getStock_storageNumber() {
		return stock_storageNumber;
	}
	public void setStock_storageNumber(Double stock_storageNumber) {
		this.stock_storageNumber = stock_storageNumber;
	}
	public String getSup_code() {
		return sup_code;
	}
	public void setSup_code(String sup_code) {
		this.sup_code = sup_code;
	}
	public String getProduct_code() {
		return product_code;
	}
	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}
	public String getSup_name() {
		return sup_name;
	}
	public void setSup_name(String sup_name) {
		this.sup_name = sup_name;
	}
	public String getProduct_specification() {
		return product_specification;
	}
	public void setProduct_specification(String product_specification) {
		this.product_specification = product_specification;
	}
	public String getProduct_packingamount() {
		return product_packingamount;
	}
	public void setProduct_packingamount(String product_packingamount) {
		this.product_packingamount = product_packingamount;
	}
	public String getProduct_unit() {
		return product_unit;
	}
	public void setProduct_unit(String product_unit) {
		this.product_unit = product_unit;
	}
	public String getStock_intakeBrief() {
		return stock_intakeBrief;
	}
	public void setStock_intakeBrief(String stock_intakeBrief) {
		this.stock_intakeBrief = stock_intakeBrief;
	}
	public String getProduct_dosagetype() {
		return product_dosagetype;
	}
	public void setProduct_dosagetype(String product_dosagetype) {
		this.product_dosagetype = product_dosagetype;
	}
	public String getProduct_description() {
		return product_description;
	}
	public void setProduct_description(String product_description) {
		this.product_description = product_description;
	}
	public String getProduct_approvalnum() {
		return product_approvalnum;
	}
	public void setProduct_approvalnum(String product_approvalnum) {
		this.product_approvalnum = product_approvalnum;
	}
	public String getProduct_category() {
		return product_category;
	}
	public void setProduct_category(String product_category) {
		this.product_category = product_category;
	}
	public String getSup_ctactperson() {
		return sup_ctactperson;
	}
	public void setSup_ctactperson(String sup_ctactperson) {
		this.sup_ctactperson = sup_ctactperson;
	}
	public String getStock_storage() {
		return stock_storage;
	}
	public void setStock_storage(String stock_storage) {
		this.stock_storage = stock_storage;
	}
	public String getStock_intakeTicket() {
		return stock_intakeTicket;
	}
	public void setStock_intakeTicket(String stock_intakeTicket) {
		this.stock_intakeTicket = stock_intakeTicket;
	}
	public Integer getStock_intakeSmallNumber() {
		return stock_intakeSmallNumber;
	}
	public void setStock_intakeSmallNumber(Integer stock_intakeSmallNumber) {
		this.stock_intakeSmallNumber = stock_intakeSmallNumber;
	}
	public String getStock_batchCode() {
		return stock_batchCode;
	}
	public void setStock_batchCode(String stock_batchCode) {
		this.stock_batchCode = stock_batchCode;
	}
	public Date getStock_invalidDate() {
		return stock_invalidDate;
	}
	public void setStock_invalidDate(Date stock_invalidDate) {
		this.stock_invalidDate = stock_invalidDate;
	}
	public String getStock_batchSmallCode() {
		return stock_batchSmallCode;
	}
	public void setStock_batchSmallCode(String stock_batchSmallCode) {
		this.stock_batchSmallCode = stock_batchSmallCode;
	}
	public String getStock_sterilizationbnum() {
		return stock_sterilizationbnum;
	}
	public void setStock_sterilizationbnum(String stock_sterilizationbnum) {
		this.stock_sterilizationbnum = stock_sterilizationbnum;
	}
	public Date getStock_produceDate() {
		return stock_produceDate;
	}
	public void setStock_produceDate(Date stock_produceDate) {
		this.stock_produceDate = stock_produceDate;
	}
	public String getSup_customername() {
		return sup_customername;
	}
	public void setSup_customername(String sup_customername) {
		this.sup_customername = sup_customername;
	}
	public String getSup_tel() {
		return sup_tel;
	}
	public void setSup_tel(String sup_tel) {
		this.sup_tel = sup_tel;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "RefundVO [product_name=" + product_name
				+ ", stock_storageNumber=" + stock_storageNumber
				+ ", sup_code=" + sup_code + ", product_code=" + product_code
				+ ", sup_name=" + sup_name + ", product_specification="
				+ product_specification + ", product_packingamount="
				+ product_packingamount + ", product_unit=" + product_unit
				+ ", stock_intakeBrief=" + stock_intakeBrief
				+ ", product_dosagetype=" + product_dosagetype
				+ ", product_description=" + product_description
				+ ", product_approvalnum=" + product_approvalnum
				+ ", product_category=" + product_category
				+ ", sup_ctactperson=" + sup_ctactperson + ", stock_storage="
				+ stock_storage + ", stock_intakeTicket=" + stock_intakeTicket
				+ ", stock_intakeSmallNumber=" + stock_intakeSmallNumber
				+ ", stock_batchCode=" + stock_batchCode
				+ ", stock_invalidDate=" + stock_invalidDate
				+ ", stock_batchSmallCode=" + stock_batchSmallCode
				+ ", stock_sterilizationbnum=" + stock_sterilizationbnum
				+ ", stock_produceDate=" + stock_produceDate
				+ ", sup_customername=" + sup_customername + ", sup_tel="
				+ sup_tel + "]";
	}
	
	
	
	
}