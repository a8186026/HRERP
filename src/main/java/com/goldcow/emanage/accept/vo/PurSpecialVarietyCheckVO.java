package com.goldcow.emanage.accept.vo;

import java.util.Date;

import com.goldcow.sframe.util.DataGridModel;
import com.goldcow.sframe.util.mybatis.BaseEntity;

public class PurSpecialVarietyCheckVO extends DataGridModel implements BaseEntity {

	private static final long serialVersionUID = 1L;
	/** 小单ID */
	private Integer id;
	/** 票号 */
	private String ticket_id;
	/** 产品序号 */
	private Integer product_id;
	/** 收货验收ID  */
	private Integer accept_check_id;
	/** 产品编号 */
	private String product_code;
	/** 数量 */
	private Double quantity;
	/** 单价 */
	private Double unitprice;
	/** 金额 */
	private String sum;
	/** 结算价 */
	private Double settlementPrice;
	/** 参考进价（只记录订单生成时的参考进价） */
	private Double referencePrice;
	/** 订货结算价 */
	private Double orderSettlementPrice;
	/** 到货时间 */
	private Date arrivalDate;
	/** 是否到货(0未到货 1到货) */
	private Integer isArrival;
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
	/** 小单预付款 */
	private Double prepaidForList;
	/** 到货数量  */
	private Integer arrivalNumber;
	
	
	
	public Double getPrepaidForList() {
		return prepaidForList;
	}
	public void setPrepaidForList(Double prepaidForList) {
		this.prepaidForList = prepaidForList;
	}
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
	public Integer getProduct_id() {
		return product_id;
	}
	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}
	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double quantity) {
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
	public Integer getArrivalNumber() {
		return arrivalNumber;
	}
	public void setArrivalNumber(Integer arrivalNumber) {
		this.arrivalNumber = arrivalNumber;
	}
	public String getProduct_code() {
		return product_code;
	}
	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}
	public Integer getAccept_check_id() {
		return accept_check_id;
	}
	public void setAccept_check_id(Integer accept_check_id) {
		this.accept_check_id = accept_check_id;
	}
}
