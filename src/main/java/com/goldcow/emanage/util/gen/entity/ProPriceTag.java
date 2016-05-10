package com.goldcow.emanage.util.gen.entity;

import java.util.Date;

import com.goldcow.sframe.util.DataGridModel;
import com.goldcow.sframe.util.mybatis.BaseEntity;

	/**
	 * 进货条目实体类
	 * 
	 * @author chenyuxuan
	 * @version v1.0
	 * @since 2015-7-6
	 */


public class ProPriceTag extends DataGridModel implements BaseEntity {

	private static final long serialVersionUID = 1L;
	/** 序号 */
	private Integer priceTag_id;
	/** 产品序号 */
	private Integer product_id;
	/** 产品编号 */
	private String product_code;
	/** 产品名称 */
	private String product_name;
	/** 规格 */
	private String product_specification;
	/** 单位 */
	private String product_unit;
	/** 剂型 */
	private String product_dosagetype;
	/** 产地 */
	private String product_productarea;
	/** 零售价 */
	private Double product_retailprice;
	/** 会员价 */
	private Double product_memberprice;
	/** 添加日期 */
	private Date priceTag_createTime;
	/** 操作员 */
	private String priceTag_operator;
	/** 其它分类 */
	private String product_othercategory;
	/** 库房 */
	private Integer department_id;
	
	
	public Integer getPriceTag_id() {
		return priceTag_id;
	}
	public void setPriceTag_id(Integer priceTag_id) {
		this.priceTag_id = priceTag_id;
	}
	public Integer getProduct_id() {
		return product_id;
	}
	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}
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
	public String getProduct_dosagetype() {
		return product_dosagetype;
	}
	public void setProduct_dosagetype(String product_dosagetype) {
		this.product_dosagetype = product_dosagetype;
	}
	public String getProduct_productarea() {
		return product_productarea;
	}
	public void setProduct_productarea(String product_productarea) {
		this.product_productarea = product_productarea;
	}
	public Double getProduct_retailprice() {
		return product_retailprice;
	}
	public void setProduct_retailprice(Double product_retailprice) {
		this.product_retailprice = product_retailprice;
	}
	public Double getProduct_memberprice() {
		return product_memberprice;
	}
	public void setProduct_memberprice(Double product_memberprice) {
		this.product_memberprice = product_memberprice;
	}
	public Date getPriceTag_createTime() {
		return priceTag_createTime;
	}
	public void setPriceTag_createTime(Date priceTag_createTime) {
		this.priceTag_createTime = priceTag_createTime;
	}
	public String getPriceTag_operator() {
		return priceTag_operator;
	}
	public void setPriceTag_operator(String priceTag_operator) {
		this.priceTag_operator = priceTag_operator;
	}
	public String getProduct_othercategory() {
		return product_othercategory;
	}
	public void setProduct_othercategory(String product_othercategory) {
		this.product_othercategory = product_othercategory;
	}
	public Integer getDepartment_id() {
		return department_id;
	}
	public void setDepartment_id(Integer department_id) {
		this.department_id = department_id;
	}
	
}