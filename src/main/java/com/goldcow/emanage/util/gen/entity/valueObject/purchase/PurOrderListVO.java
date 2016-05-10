package com.goldcow.emanage.util.gen.entity.valueObject.purchase;

import java.util.Date;

import com.goldcow.emanage.util.gen.entity.ProInfoManage;

/**
 * 小单信息实体类
 * 
 * @author 吴彬
 * @version v1.0
 * @since 2015-9-21
 */
public class PurOrderListVO extends ProInfoManage{
	private static final long serialVersionUID = 1L;
	/** 小单ID */
	private Integer id;
	/** 票号 */
	private String ticket_id;
	/** 数量 */
	private String quantity;
	/** 单价 */
	private Double unitprice;
	/** 金额 */
	private String sum;
	/** 产品编码 */
	private String product_code;
	/** 产品ID */
	private Integer product_id;
	/** 供方ID */
	private Integer supply_id;
	/** 供方全称 */
	private String supply_fullname;
	/** 供方编码 */
	private String supply_code;
	/** 订货日期 */
	private Date arrivalDate;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTicket_id() {
		return ticket_id;
	}
	public void setTicket_id(String ticket_id) {
		this.ticket_id = ticket_id;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public Double getUnitprice() {
		return unitprice;
	}
	public void setUnitprice(Double unitprice) {
		this.unitprice = unitprice;
	}
	public String getSum() {
		return sum;
	}
	public void setSum(String sum) {
		this.sum = sum;
	}
	public String getProduct_code() {
		return product_code;
	}
	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}
	public Integer getProduct_id() {
		return product_id;
	}
	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}
	public Integer getSupply_id() {
		return supply_id;
	}
	public void setSupply_id(Integer supply_id) {
		this.supply_id = supply_id;
	}
	public String getSupply_fullname() {
		return supply_fullname;
	}
	public void setSupply_fullname(String supply_fullname) {
		this.supply_fullname = supply_fullname;
	}
	public String getSupply_code() {
		return supply_code;
	}
	public void setSupply_code(String supply_code) {
		this.supply_code = supply_code;
	}
	public Date getArrivalDate() {
		return arrivalDate;
	}
	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

}