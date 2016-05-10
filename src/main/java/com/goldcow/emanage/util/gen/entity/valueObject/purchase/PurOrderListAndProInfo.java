package com.goldcow.emanage.util.gen.entity.valueObject.purchase;

import java.util.Date;

import com.goldcow.emanage.util.gen.entity.ProInfoManage;

/**
 * 小单管理产品信息实体类
 * 
 * @author Yaotaihang
 * @version v1.0
 * @since 2015-6-5
 */
public class PurOrderListAndProInfo extends ProInfoManage{
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
	/** 结算价 */
	private Double settlementPrice;
	/** 预付款(wubin) */
	private Double prepaidForList;
	/** 参考进价（只记录订单生成时的参考进价） */
	private Double referencePrice;
	/** 订货结算价 */
	private Double orderSettlementPrice;
	/** 到货时间 */
	private Date arrivalDate;
	/** 是否到货(0到货 1未到货) */
	private Integer isArrival;
	/** 到货数量  */
	private Double arrivalNumber;
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
	public Double getSettlementPrice() {
		return settlementPrice;
	}
	public void setSettlementPrice(Double settlementPrice) {
		this.settlementPrice = settlementPrice;
	}
	public Double getPrepaidForList() {
		return prepaidForList;
	}
	public void setPrepaidForList(Double prepaidForList) {
		this.prepaidForList = prepaidForList;
	}
	public Double getReferencePrice() {
		return referencePrice;
	}
	public void setReferencePrice(Double referencePrice) {
		this.referencePrice = referencePrice;
	}
	public Double getOrderSettlementPrice() {
		return orderSettlementPrice;
	}
	public void setOrderSettlementPrice(Double orderSettlementPrice) {
		this.orderSettlementPrice = orderSettlementPrice;
	}
	public Date getArrivalDate() {
		return arrivalDate;
	}
	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	public Integer getIsArrival() {
		return isArrival;
	}
	public void setIsArrival(Integer isArrival) {
		this.isArrival = isArrival;
	}
	public Double getArrivalNumber() {
		return arrivalNumber;
	}
	public void setArrivalNumber(Double arrivalNumber) {
		this.arrivalNumber = arrivalNumber;
	}
	
}