package com.goldcow.emanage.util.gen.entity.valueObject.quality;

import com.goldcow.emanage.util.gen.entity.QltVarietyCheck;
/**
 * 重点养护品种信息实体类
 * 
 * @author gaoxiang
 * @version v1.0
 * @since 2015-11-16
 */
public class QltVarietyCheckVO extends QltVarietyCheck{
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
	
	
}