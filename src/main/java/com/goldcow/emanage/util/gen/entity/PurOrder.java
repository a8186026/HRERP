package com.goldcow.emanage.util.gen.entity;

import java.math.BigDecimal;
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


public class PurOrder extends DataGridModel implements BaseEntity {

	private static final long serialVersionUID = 1L;
	/** 序号 */
	private Integer id;
	/** 种类(直接入库还是正常订单) */
	private String type;
	/** 票号 */
	private String ticket_id;
	/** 订货时间 （默认时间）*/
	private Date orderDate;
	/** 联系人 */
	private String contactPerson;
	/** 订单金额 */
	private Double sum;
	/** 摘要 */
	private String orderabstract;
	/** 经手人 */
	private String brokerage;
	/** 负责人 */
	private String personinCharge;
	/** 操作员 */
	private String operator;
	/** 草稿 （判断此单是否存盘 0是已存 1是未存）*/
	private Integer draft;	
	/** 打印次数 */
	private Integer printCount;
	/** 预付款票号 */
	private String prepaidTicketId;
	/** 说明 */
	private String explain;
	/** 审核人 */
	private String checkPerson;
	/** 审核时间 */
	private Date checkTime;
	/** 备注 */
	private String remarks;
	/** 预付款 */
	private Double prepaid;
	/** 结款方式 */
	private String knotStyle;
	/** 政策形式 */
	private String policyStyle;
	/** 价格原因 */
	private String priceReason;
	/** 收货  0-未收货 1-已收货**/
	private Integer goodsReceive;
	/** 收货人 */
	private String goodsReceivePerson;
	/** 收货时间 */
	private Date goodsReceiveTime;
	/** 订单温控方式 */
	private String tempCtrlMode;
	/** 订单温控情况 */
	private String tempCtrlSituation;
	/** 订单运输单位 */
	private String transUnit;
	/** 订单环境温度 */
	private Double ambientTemp;
	/** 接收 （判断往另一个数据库里查）*/
	private Integer receive;
	/** 订单承运方式 */
	private String carryMode;
	/** 订单承运单位 */
	private String carryCompany;
	/** 订单发货地点 */
	private String delivaryPlace;
	/** 订单到货时间  */
	private Date arrivalDate;
	/** 订单启运时间 */
	private Date orderDepartureTime;
	/** 订单运输方式 */
	private String orderTransMode;
	/** 订单运输工具 */
	private String orderTransFunc;
	/** 开票时间 */
	private Date makeoutInvoiceTime;
	private Integer status;
	/** 创建人 */
	private Integer create_user;
	/** 创建时间 */
	private Date create_time;
	/** 最后修改人 */
	private Integer last_modify_user;
	/** 最后修改时间 */
	private Date last_modify_time;
	/** 供方序号 */
	private Integer supply_id;
	/** 供方全称 */
	private String supply_fullname;
	/** 供方编码 */
	private String supply_code;
	/** 大单审核状态码 0-未审核 1-已审核*/
	private Integer checkStatus;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTicket_id() {
		return ticket_id;
	}
	public void setTicket_id(String ticket_id) {
		this.ticket_id = ticket_id;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public String getContactPerson() {
		return contactPerson;
	}
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	public Double getSum() {
		return sum;
	}
	public void setSum(Double sum) {
		this.sum = sum;
	}
	public String getOrderabstract() {
		return orderabstract;
	}
	public void setOrderabstract(String orderabstract) {
		this.orderabstract = orderabstract;
	}
	public String getBrokerage() {
		return brokerage;
	}
	public void setBrokerage(String brokerage) {
		this.brokerage = brokerage;
	}
	public String getPersoninCharge() {
		return personinCharge;
	}
	public void setPersoninCharge(String personinCharge) {
		this.personinCharge = personinCharge;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public Integer getDraft() {
		return draft;
	}
	public void setDraft(Integer draft) {
		this.draft = draft;
	}
	public Integer getPrintCount() {
		return printCount;
	}
	public void setPrintCount(Integer printCount) {
		this.printCount = printCount;
	}
	public String getPrepaidTicketId() {
		return prepaidTicketId;
	}
	public void setPrepaidTicketId(String prepaidTicketId) {
		this.prepaidTicketId = prepaidTicketId;
	}
	public String getExplain() {
		return explain;
	}
	public void setExplain(String explain) {
		this.explain = explain;
	}
	public String getCheckPerson() {
		return checkPerson;
	}
	public void setCheckPerson(String checkPerson) {
		this.checkPerson = checkPerson;
	}
	public Date getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Double getPrepaid() {
		return prepaid;
	}
	public void setPrepaid(Double prepaid) {
		this.prepaid = prepaid;
	}
	public String getKnotStyle() {
		return knotStyle;
	}
	public void setKnotStyle(String knotStyle) {
		this.knotStyle = knotStyle;
	}
	public String getPolicyStyle() {
		return policyStyle;
	}
	public void setPolicyStyle(String policyStyle) {
		this.policyStyle = policyStyle;
	}
	public String getPriceReason() {
		return priceReason;
	}
	public void setPriceReason(String priceReason) {
		this.priceReason = priceReason;
	}
	public Integer getGoodsReceive() {
		return goodsReceive;
	}
	public void setGoodsReceive(Integer goodsReceive) {
		this.goodsReceive = goodsReceive;
	}
	public String getGoodsReceivePerson() {
		return goodsReceivePerson;
	}
	public void setGoodsReceivePerson(String goodsReceivePerson) {
		this.goodsReceivePerson = goodsReceivePerson;
	}
	public Date getGoodsReceiveTime() {
		return goodsReceiveTime;
	}
	public void setGoodsReceiveTime(Date goodsReceiveTime) {
		this.goodsReceiveTime = goodsReceiveTime;
	}
	public String getTempCtrlMode() {
		return tempCtrlMode;
	}
	public void setTempCtrlMode(String tempCtrlMode) {
		this.tempCtrlMode = tempCtrlMode;
	}
	public String getTempCtrlSituation() {
		return tempCtrlSituation;
	}
	public void setTempCtrlSituation(String tempCtrlSituation) {
		this.tempCtrlSituation = tempCtrlSituation;
	}
	public String getTransUnit() {
		return transUnit;
	}
	public void setTransUnit(String transUnit) {
		this.transUnit = transUnit;
	}
	public Double getAmbientTemp() {
		return ambientTemp;
	}
	public void setAmbientTemp(Double ambientTemp) {
		this.ambientTemp = ambientTemp;
	}
	public Integer getReceive() {
		return receive;
	}
	public void setReceive(Integer receive) {
		this.receive = receive;
	}
	public String getCarryMode() {
		return carryMode;
	}
	public void setCarryMode(String carryMode) {
		this.carryMode = carryMode;
	}
	public String getCarryCompany() {
		return carryCompany;
	}
	public void setCarryCompany(String carryCompany) {
		this.carryCompany = carryCompany;
	}
	public String getDelivaryPlace() {
		return delivaryPlace;
	}
	public void setDelivaryPlace(String delivaryPlace) {
		this.delivaryPlace = delivaryPlace;
	}
	public Date getArrivalDate() {
		return arrivalDate;
	}
	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	public Date getOrderDepartureTime() {
		return orderDepartureTime;
	}
	public void setOrderDepartureTime(Date orderDepartureTime) {
		this.orderDepartureTime = orderDepartureTime;
	}
	public String getOrderTransMode() {
		return orderTransMode;
	}
	public void setOrderTransMode(String orderTransMode) {
		this.orderTransMode = orderTransMode;
	}
	public String getOrderTransFunc() {
		return orderTransFunc;
	}
	public void setOrderTransFunc(String orderTransFunc) {
		this.orderTransFunc = orderTransFunc;
	}
	public Date getMakeoutInvoiceTime() {
		return makeoutInvoiceTime;
	}
	public void setMakeoutInvoiceTime(Date makeoutInvoiceTime) {
		this.makeoutInvoiceTime = makeoutInvoiceTime;
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
	public Integer getCheckStatus() {
		return checkStatus;
	}
	public void setCheckStatus(Integer checkStatus) {
		this.checkStatus = checkStatus;
	}

}