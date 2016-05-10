package com.goldcow.emanage.util.gen.entity.valueObject.stock;

import java.util.Date;

import com.goldcow.emanage.util.gen.entity.StockInfo;


/**
 * 库存批次详细信息VO
 * 
 * @author wubin
 * @version v1.0
 * @since 2016-1-6
 */

public class StockBatchVO extends StockInfo {
	private static final long serialVersionUID = 1L;
	/** 产品编号 */
	private String product_code;
	/** 产品名称 */
	private String product_name;
	/** 通用名 */
	private String product_commonname;
	/** 规格 */
	private String product_specification;
	/** 单位 */
	private String product_unit;
	/** 产地 */
	private String product_productarea;
	/** 供方全称 */
	private String sup_name;
	/** 入库数量 */
	private Integer intake_number;
	/** 入库时间 */
	private Date intake_Date;
	
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
	public String getProduct_commonname() {
		return product_commonname;
	}
	public void setProduct_commonname(String product_commonname) {
		this.product_commonname = product_commonname;
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
	public String getSup_name() {
		return sup_name;
	}
	public void setSup_name(String sup_name) {
		this.sup_name = sup_name;
	}
	public Integer getIntake_number() {
		return intake_number;
	}
	public void setIntake_number(Integer intake_number) {
		this.intake_number = intake_number;
	}
	public Date getIntake_Date() {
		return intake_Date;
	}
	public void setIntake_Date(Date intake_Date) {
		this.intake_Date = intake_Date;
	}
	
}
