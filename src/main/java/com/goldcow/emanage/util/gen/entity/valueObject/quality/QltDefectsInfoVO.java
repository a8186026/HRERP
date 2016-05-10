package com.goldcow.emanage.util.gen.entity.valueObject.quality;

import java.util.Date;

import com.goldcow.emanage.util.gen.entity.QltDefectsInfo;
/**
 * 不合格品种信息实体类
 * 
 * @author gaoxiang
 * @version v1.0
 * @since 2015-11-30
 */
public class QltDefectsInfoVO extends QltDefectsInfo{
	private static final long serialVersionUID = 1L;
	/** 产品编号 */
	private String product_code;
	/** 产品名称 */
	private String product_name;
	/** 规格 */
	private String product_specification;
	/** 单位 */
	private String product_unit;
	/** 产地 */
	private String product_productarea;
	/** 生产厂家 */
	private String product_factoryname;
	/** 批准文号 */
	private String product_approvalnum;
	/** 剂型 */
	private String product_dosagetype;
	
	/** 入库票号 */
	private String stock_intakeTicket;
	/** 入库小号 */
	private Integer stock_intakeSmallNumber;
	/** 批号 */
	private String stock_batchCode;
	/** 失效期 */
	private Date stock_invalidDate;
	/** 生产日期 */
	private Date stock_produceDate;
	/** 灭菌批号 */
	private String stock_sterilizationbnum;
	/** 灭菌日期 */
	private Date stock_sterilizationtime;
	/** 库存数量 */
	private Double stock_storageNumber;
	/** 库房 */
	private Integer stock_storage;
	
	public String getProduct_code() {
		return product_code;
	}
	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getProduct_specification() {
		return product_specification;
	}
	public void setProduct_specification(String product_specification) {
		this.product_specification = product_specification;
	}
	public String getProduct_unit() {
		return product_unit;
	}
	public void setProduct_unit(String product_unit) {
		this.product_unit = product_unit;
	}
	public String getProduct_productarea() {
		return product_productarea;
	}
	public void setProduct_productarea(String product_productarea) {
		this.product_productarea = product_productarea;
	}
	public String getProduct_factoryname() {
		return product_factoryname;
	}
	public void setProduct_factoryname(String product_factoryname) {
		this.product_factoryname = product_factoryname;
	}
	public String getProduct_approvalnum() {
		return product_approvalnum;
	}
	public void setProduct_approvalnum(String product_approvalnum) {
		this.product_approvalnum = product_approvalnum;
	}
	public String getProduct_dosagetype() {
		return product_dosagetype;
	}
	public void setProduct_dosagetype(String product_dosagetype) {
		this.product_dosagetype = product_dosagetype;
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
	public Date getStock_produceDate() {
		return stock_produceDate;
	}
	public void setStock_produceDate(Date stock_produceDate) {
		this.stock_produceDate = stock_produceDate;
	}
	public String getStock_sterilizationbnum() {
		return stock_sterilizationbnum;
	}
	public void setStock_sterilizationbnum(String stock_sterilizationbnum) {
		this.stock_sterilizationbnum = stock_sterilizationbnum;
	}
	public Date getStock_sterilizationtime() {
		return stock_sterilizationtime;
	}
	public void setStock_sterilizationtime(Date stock_sterilizationtime) {
		this.stock_sterilizationtime = stock_sterilizationtime;
	}
	public Double getStock_storageNumber() {
		return stock_storageNumber;
	}
	public void setStock_storageNumber(Double stock_storageNumber) {
		this.stock_storageNumber = stock_storageNumber;
	}
	public Integer getStock_storage() {
		return stock_storage;
	}
	public void setStock_storage(Integer stock_storage) {
		this.stock_storage = stock_storage;
	}
	
}