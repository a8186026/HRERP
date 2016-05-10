package com.goldcow.emanage.util.gen.entity;

import java.util.Date;

import com.goldcow.sframe.util.DataGridModel;
import com.goldcow.sframe.util.mybatis.BaseEntity;
/**
 * 批发订单实体类
 * 
 * @author cyx
 * @version v1.0
 * @since 2015-12-23
 */
public class WholeSaleOrder extends DataGridModel implements BaseEntity{
	private static final long serialVersionUID = 1L;
	/** id */
	private Integer wholeSale_order_id;
	/** 税率 */
	private Double wholeSale_order_taxRate;
	/** 订货时间 */
	private Date wholeSale_order_orderTime;
	/** 约定时间 */
	private Date wholeSale_order_appointTime;
	/** 票号 */
	private String wholeSale_order_ticketId;
	/** 销方id */
	private Integer sal_id;
	/** 客户全称 */
	private String wholeSale_order_customername;
	/** 业务员 */
	private String wholeSale_order_salesman;
	/** 前期应收 */
	private Double wholeSale_order_formerReceivable;
	/** 此次应收 */
	private Double wholeSale_order_currentReceivable;
	/** 折让 */
	private Double wholeSale_order_discountAmount;
	/** 抹零 */
	private Double wholeSale_order_wipeZero;
	/** 结算金额 */
	private Double wholeSale_order_settlementAmount;
	/** 结算方式 */
	private String wholeSale_order_settlementStyle;
	/** 结算单号 */
	private String wholeSale_order_settlementOrderId;
	/** 单号 */
	private String wholeSale_order_OrderId;
	/** 发票号 */
	private String wholeSale_order_receiptNumber;
	/** 会员卡号 */
	private String mem_card_id;
	/** 摘要 */
	private String wholeSale_order_brief;
	/** 经手人 */
	private String wholeSale_order_brokerage;
	/** 操作员 */
	private String wholeSale_order_operator;
	/** 结账 */
	private Integer wholeSale_order_checkout;
	/** 结账人 */
	private String wholeSale_order_checkoutPerson;
	/** 结账时间 */
	private Date wholeSale_order_checkoutTime;
	/** 毛利 */
	private Double wholeSale_order_grossProfit;
	/** 上传 */
	private Integer wholeSale_order_upload;
	/** 草稿0未完成1已完成 */
	private Integer wholeSale_order_draft;
	/** 信誉卡 */
	private Integer wholeSale_order_reputationCard;
	/** 记账 */
	private Integer wholeSale_order_account;
	/** 记账金额 */
	private Double wholeSale_order_accountAmount;
	/** 应收余额 */
	private Double wholeSale_order_amountReceivable;
	/** 记账时间 */
	private Date wholeSale_order_accountTime;
	/** 记账人 */
	private String wholeSale_order_accountPerson;
	/** 记账票号 */
	private String wholeSale_order_accountTicketId;
	/** 发车票号 */
	private String wholeSale_order_departTicketId;
	/** 发车 */
	private Integer wholeSale_order_depart;
	/** 发车方式 */
	private String wholeSale_order_departStyle;
	/** 发车备注 */
	private String wholeSale_order_departRemark;
	/** 自提 */
	private Integer wholeSale_order_pickUp;
	/** 配送 */
	private Integer wholeSale_order_dispatch;
	/** 装车 */
	private Integer wholeSale_order_entruck;
	/** 包装数 */
	private Integer wholeSale_order_packingNumber;
	/** 开验收单 */
	private Integer wholeSale_order_printReceipt;
	/** 机器名 */
	private String wholeSale_order_machineName;
	/** 接收 */
	private Integer wholeSale_order_receive;
	/** 接收人 */
	private String wholeSale_order_receivePerson;
	/** 接收时间 */
	private Date wholeSale_order_receiveTime;
	/** 开信誉卡 */
	private Integer wholeSale_order_handleReputationCard;
	/** 开验收单 */
	private Integer wholeSale_order_printReceiveList;
	/** 付数 */
	private Integer wholeSale_order_doseNumber;
	/** 实收金额 */
	private Double wholeSale_order_realIncomeAmount;
	/** 找零 */
	private Double wholeSale_order_change;
	/** 代金卡 */
	private Double wholeSale_order_giftCard;
	/** 打折 */
	private Double wholeSale_order_discount;
	/** 刷卡方式 */
	private String wholeSale_order_swingCardStyle;
	/** 刷卡金额 */
	private Double wholeSale_order_swingCardAmount;
	/** 现金 */
	private Double wholeSale_order_cash;
	/** 积分倍数 */
	private Integer wholeSale_order_pointMultiple;
	/** 刷卡姓名 */
	private String wholeSale_order_swingCardName;
	/** 刷卡卡号 */
	private String wholeSale_order_swingCardId;
	/** 刷卡余额 */
	private Double wholeSale_order_swingCardRemain;
	/** 积分 */
	private Double wholeSale_order_point;
	/** 打印次数 */
	private Integer wholeSale_order_printTimes;
	/** 修改备注 */
	private String wholeSale_order_modifyRemark;
	/** 刷卡人员性质 */
	private String wholeSale_order_swingCardPersonPropery;
	/** 代金卡最大 */
	private Double wholeSale_order_giftCardMax;
	/** 发代金卡金额 */
	private Double wholeSale_order_giftCardInitAmount;
	/** 购药人 */
	private String wholeSale_order_purchasePersonName;
	/** 购药人身份证 */
	private String wholeSale_order_purchasePersonID;
	/** 购药人电话 */
	private String wholeSale_order_purchasePersonPhone;
	/** 药师姓名 */
	private String wholeSale_order_pharmacistName;
	/** 药师电子签名 */
	private String wholeSale_order_pharmacistElectronicSignature;
	/** 药监编号 */
	private String wholeSale_order_drugTicketId;
	/** 存盘时间 */
	private Date wholeSale_order_saveTime;
	/** 单服金额 */
	private Double wholeSale_order_SingleDoseAmount;
	/** 满额赠金额 */
	private Double wholeSale_order_fullGiftAmount;
	/** 满额赠兑换 */
	private Double wholeSale_order_fullGiftExchange;
	/** 满额赠剩余 */
	private Double wholeSale_order_fullGiftRemain;
	/** 其它收费 */
	private Double wholeSale_order_otherCharge;
	/** 应收 */
	private Double wholeSale_order_receivableAmount;
	/** 积分当钱 */
	private Double wholeSale_order_pointPay;
	
	

	 	/** 批次库存 */
	//private Double stock_batchAmount;
	/** 批号库存 */
	//private Double stock_batchNumAmount;
	/** 品种库存 */
	//private Double stock_varietyAmount;
	/** 品种库房库存 */
	//private Double stock_varietyStorageAmount; 
	
	
	
	
	
	
	
	
	public Integer getWholeSale_order_id() {
		return wholeSale_order_id;
	}
	public void setWholeSale_order_id(Integer wholeSale_order_id) {
		this.wholeSale_order_id = wholeSale_order_id;
	}
	public Double getWholeSale_order_taxRate() {
		return wholeSale_order_taxRate;
	}
	public void setWholeSale_order_taxRate(Double wholeSale_order_taxRate) {
		this.wholeSale_order_taxRate = wholeSale_order_taxRate;
	}
	public Date getWholeSale_order_orderTime() {
		return wholeSale_order_orderTime;
	}
	public void setWholeSale_order_orderTime(Date wholeSale_order_orderTime) {
		this.wholeSale_order_orderTime = wholeSale_order_orderTime;
	}
	public Date getWholeSale_order_appointTime() {
		return wholeSale_order_appointTime;
	}
	public void setWholeSale_order_appointTime(Date wholeSale_order_appointTime) {
		this.wholeSale_order_appointTime = wholeSale_order_appointTime;
	}
	public String getWholeSale_order_ticketId() {
		return wholeSale_order_ticketId;
	}
	public void setWholeSale_order_ticketId(String wholeSale_order_ticketId) {
		this.wholeSale_order_ticketId = wholeSale_order_ticketId;
	}
	public Integer getSal_id() {
		return sal_id;
	}
	public void setSal_id(Integer sal_id) {
		this.sal_id = sal_id;
	}
	public String getWholeSale_order_customername() {
		return wholeSale_order_customername;
	}
	public void setWholeSale_order_customername(String wholeSale_order_customername) {
		this.wholeSale_order_customername = wholeSale_order_customername;
	}
	public String getWholeSale_order_salesman() {
		return wholeSale_order_salesman;
	}
	public void setWholeSale_order_salesman(String wholeSale_order_salesman) {
		this.wholeSale_order_salesman = wholeSale_order_salesman;
	}
	public Double getWholeSale_order_formerReceivable() {
		return wholeSale_order_formerReceivable;
	}
	public void setWholeSale_order_formerReceivable(
			Double wholeSale_order_formerReceivable) {
		this.wholeSale_order_formerReceivable = wholeSale_order_formerReceivable;
	}
	public Double getWholeSale_order_currentReceivable() {
		return wholeSale_order_currentReceivable;
	}
	public void setWholeSale_order_currentReceivable(
			Double wholeSale_order_currentReceivable) {
		this.wholeSale_order_currentReceivable = wholeSale_order_currentReceivable;
	}
	public Double getWholeSale_order_discountAmount() {
		return wholeSale_order_discountAmount;
	}
	public void setWholeSale_order_discountAmount(
			Double wholeSale_order_discountAmount) {
		this.wholeSale_order_discountAmount = wholeSale_order_discountAmount;
	}
	public Double getWholeSale_order_wipeZero() {
		return wholeSale_order_wipeZero;
	}
	public void setWholeSale_order_wipeZero(Double wholeSale_order_wipeZero) {
		this.wholeSale_order_wipeZero = wholeSale_order_wipeZero;
	}
	public Double getWholeSale_order_settlementAmount() {
		return wholeSale_order_settlementAmount;
	}
	public void setWholeSale_order_settlementAmount(
			Double wholeSale_order_settlementAmount) {
		this.wholeSale_order_settlementAmount = wholeSale_order_settlementAmount;
	}
	public String getWholeSale_order_settlementStyle() {
		return wholeSale_order_settlementStyle;
	}
	public void setWholeSale_order_settlementStyle(
			String wholeSale_order_settlementStyle) {
		this.wholeSale_order_settlementStyle = wholeSale_order_settlementStyle;
	}
	public String getWholeSale_order_settlementOrderId() {
		return wholeSale_order_settlementOrderId;
	}
	public void setWholeSale_order_settlementOrderId(
			String wholeSale_order_settlementOrderId) {
		this.wholeSale_order_settlementOrderId = wholeSale_order_settlementOrderId;
	}
	public String getWholeSale_order_OrderId() {
		return wholeSale_order_OrderId;
	}
	public void setWholeSale_order_OrderId(String wholeSale_order_OrderId) {
		this.wholeSale_order_OrderId = wholeSale_order_OrderId;
	}
	public String getWholeSale_order_receiptNumber() {
		return wholeSale_order_receiptNumber;
	}
	public void setWholeSale_order_receiptNumber(
			String wholeSale_order_receiptNumber) {
		this.wholeSale_order_receiptNumber = wholeSale_order_receiptNumber;
	}
	public String getMem_card_id() {
		return mem_card_id;
	}
	public void setMem_card_id(String mem_card_id) {
		this.mem_card_id = mem_card_id;
	}
	public String getWholeSale_order_brief() {
		return wholeSale_order_brief;
	}
	public void setWholeSale_order_brief(String wholeSale_order_brief) {
		this.wholeSale_order_brief = wholeSale_order_brief;
	}
	public String getWholeSale_order_brokerage() {
		return wholeSale_order_brokerage;
	}
	public void setWholeSale_order_brokerage(String wholeSale_order_brokerage) {
		this.wholeSale_order_brokerage = wholeSale_order_brokerage;
	}
	public String getWholeSale_order_operator() {
		return wholeSale_order_operator;
	}
	public void setWholeSale_order_operator(String wholeSale_order_operator) {
		this.wholeSale_order_operator = wholeSale_order_operator;
	}
	public Integer getWholeSale_order_checkout() {
		return wholeSale_order_checkout;
	}
	public void setWholeSale_order_checkout(Integer wholeSale_order_checkout) {
		this.wholeSale_order_checkout = wholeSale_order_checkout;
	}
	public String getWholeSale_order_checkoutPerson() {
		return wholeSale_order_checkoutPerson;
	}
	public void setWholeSale_order_checkoutPerson(
			String wholeSale_order_checkoutPerson) {
		this.wholeSale_order_checkoutPerson = wholeSale_order_checkoutPerson;
	}
	public Date getWholeSale_order_checkoutTime() {
		return wholeSale_order_checkoutTime;
	}
	public void setWholeSale_order_checkoutTime(Date wholeSale_order_checkoutTime) {
		this.wholeSale_order_checkoutTime = wholeSale_order_checkoutTime;
	}
	public Double getWholeSale_order_grossProfit() {
		return wholeSale_order_grossProfit;
	}
	public void setWholeSale_order_grossProfit(Double wholeSale_order_grossProfit) {
		this.wholeSale_order_grossProfit = wholeSale_order_grossProfit;
	}
	public Integer getWholeSale_order_upload() {
		return wholeSale_order_upload;
	}
	public void setWholeSale_order_upload(Integer wholeSale_order_upload) {
		this.wholeSale_order_upload = wholeSale_order_upload;
	}
	public Integer getWholeSale_order_draft() {
		return wholeSale_order_draft;
	}
	public void setWholeSale_order_draft(Integer wholeSale_order_draft) {
		this.wholeSale_order_draft = wholeSale_order_draft;
	}
	public Integer getWholeSale_order_reputationCard() {
		return wholeSale_order_reputationCard;
	}
	public void setWholeSale_order_reputationCard(
			Integer wholeSale_order_reputationCard) {
		this.wholeSale_order_reputationCard = wholeSale_order_reputationCard;
	}
	public Integer getWholeSale_order_account() {
		return wholeSale_order_account;
	}
	public void setWholeSale_order_account(Integer wholeSale_order_account) {
		this.wholeSale_order_account = wholeSale_order_account;
	}
	public Double getWholeSale_order_accountAmount() {
		return wholeSale_order_accountAmount;
	}
	public void setWholeSale_order_accountAmount(
			Double wholeSale_order_accountAmount) {
		this.wholeSale_order_accountAmount = wholeSale_order_accountAmount;
	}
	public Double getWholeSale_order_amountReceivable() {
		return wholeSale_order_amountReceivable;
	}
	public void setWholeSale_order_amountReceivable(
			Double wholeSale_order_amountReceivable) {
		this.wholeSale_order_amountReceivable = wholeSale_order_amountReceivable;
	}
	public Date getWholeSale_order_accountTime() {
		return wholeSale_order_accountTime;
	}
	public void setWholeSale_order_accountTime(Date wholeSale_order_accountTime) {
		this.wholeSale_order_accountTime = wholeSale_order_accountTime;
	}
	public String getWholeSale_order_accountPerson() {
		return wholeSale_order_accountPerson;
	}
	public void setWholeSale_order_accountPerson(
			String wholeSale_order_accountPerson) {
		this.wholeSale_order_accountPerson = wholeSale_order_accountPerson;
	}
	public String getWholeSale_order_accountTicketId() {
		return wholeSale_order_accountTicketId;
	}
	public void setWholeSale_order_accountTicketId(
			String wholeSale_order_accountTicketId) {
		this.wholeSale_order_accountTicketId = wholeSale_order_accountTicketId;
	}
	public String getWholeSale_order_departTicketId() {
		return wholeSale_order_departTicketId;
	}
	public void setWholeSale_order_departTicketId(
			String wholeSale_order_departTicketId) {
		this.wholeSale_order_departTicketId = wholeSale_order_departTicketId;
	}
	public Integer getWholeSale_order_depart() {
		return wholeSale_order_depart;
	}
	public void setWholeSale_order_depart(Integer wholeSale_order_depart) {
		this.wholeSale_order_depart = wholeSale_order_depart;
	}
	public String getWholeSale_order_departStyle() {
		return wholeSale_order_departStyle;
	}
	public void setWholeSale_order_departStyle(String wholeSale_order_departStyle) {
		this.wholeSale_order_departStyle = wholeSale_order_departStyle;
	}
	public String getWholeSale_order_departRemark() {
		return wholeSale_order_departRemark;
	}
	public void setWholeSale_order_departRemark(String wholeSale_order_departRemark) {
		this.wholeSale_order_departRemark = wholeSale_order_departRemark;
	}
	public Integer getWholeSale_order_pickUp() {
		return wholeSale_order_pickUp;
	}
	public void setWholeSale_order_pickUp(Integer wholeSale_order_pickUp) {
		this.wholeSale_order_pickUp = wholeSale_order_pickUp;
	}
	public Integer getWholeSale_order_dispatch() {
		return wholeSale_order_dispatch;
	}
	public void setWholeSale_order_dispatch(Integer wholeSale_order_dispatch) {
		this.wholeSale_order_dispatch = wholeSale_order_dispatch;
	}
	public Integer getWholeSale_order_entruck() {
		return wholeSale_order_entruck;
	}
	public void setWholeSale_order_entruck(Integer wholeSale_order_entruck) {
		this.wholeSale_order_entruck = wholeSale_order_entruck;
	}
	public Integer getWholeSale_order_packingNumber() {
		return wholeSale_order_packingNumber;
	}
	public void setWholeSale_order_packingNumber(
			Integer wholeSale_order_packingNumber) {
		this.wholeSale_order_packingNumber = wholeSale_order_packingNumber;
	}
	public Integer getWholeSale_order_printReceipt() {
		return wholeSale_order_printReceipt;
	}
	public void setWholeSale_order_printReceipt(Integer wholeSale_order_printReceipt) {
		this.wholeSale_order_printReceipt = wholeSale_order_printReceipt;
	}
	public String getWholeSale_order_machineName() {
		return wholeSale_order_machineName;
	}
	public void setWholeSale_order_machineName(String wholeSale_order_machineName) {
		this.wholeSale_order_machineName = wholeSale_order_machineName;
	}
	public Integer getWholeSale_order_receive() {
		return wholeSale_order_receive;
	}
	public void setWholeSale_order_receive(Integer wholeSale_order_receive) {
		this.wholeSale_order_receive = wholeSale_order_receive;
	}
	public String getWholeSale_order_receivePerson() {
		return wholeSale_order_receivePerson;
	}
	public void setWholeSale_order_receivePerson(
			String wholeSale_order_receivePerson) {
		this.wholeSale_order_receivePerson = wholeSale_order_receivePerson;
	}
	public Date getWholeSale_order_receiveTime() {
		return wholeSale_order_receiveTime;
	}
	public void setWholeSale_order_receiveTime(Date wholeSale_order_receiveTime) {
		this.wholeSale_order_receiveTime = wholeSale_order_receiveTime;
	}
	public Integer getWholeSale_order_handleReputationCard() {
		return wholeSale_order_handleReputationCard;
	}
	public void setWholeSale_order_handleReputationCard(
			Integer wholeSale_order_handleReputationCard) {
		this.wholeSale_order_handleReputationCard = wholeSale_order_handleReputationCard;
	}
	public Integer getWholeSale_order_printReceiveList() {
		return wholeSale_order_printReceiveList;
	}
	public void setWholeSale_order_printReceiveList(
			Integer wholeSale_order_printReceiveList) {
		this.wholeSale_order_printReceiveList = wholeSale_order_printReceiveList;
	}
	public Integer getWholeSale_order_doseNumber() {
		return wholeSale_order_doseNumber;
	}
	public void setWholeSale_order_doseNumber(Integer wholeSale_order_doseNumber) {
		this.wholeSale_order_doseNumber = wholeSale_order_doseNumber;
	}
	public Double getWholeSale_order_realIncomeAmount() {
		return wholeSale_order_realIncomeAmount;
	}
	public void setWholeSale_order_realIncomeAmount(
			Double wholeSale_order_realIncomeAmount) {
		this.wholeSale_order_realIncomeAmount = wholeSale_order_realIncomeAmount;
	}
	public Double getWholeSale_order_change() {
		return wholeSale_order_change;
	}
	public void setWholeSale_order_change(Double wholeSale_order_change) {
		this.wholeSale_order_change = wholeSale_order_change;
	}
	public Double getWholeSale_order_giftCard() {
		return wholeSale_order_giftCard;
	}
	public void setWholeSale_order_giftCard(Double wholeSale_order_giftCard) {
		this.wholeSale_order_giftCard = wholeSale_order_giftCard;
	}
	public Double getWholeSale_order_discount() {
		return wholeSale_order_discount;
	}
	public void setWholeSale_order_discount(Double wholeSale_order_discount) {
		this.wholeSale_order_discount = wholeSale_order_discount;
	}
	public String getWholeSale_order_swingCardStyle() {
		return wholeSale_order_swingCardStyle;
	}
	public void setWholeSale_order_swingCardStyle(
			String wholeSale_order_swingCardStyle) {
		this.wholeSale_order_swingCardStyle = wholeSale_order_swingCardStyle;
	}
	public Double getWholeSale_order_swingCardAmount() {
		return wholeSale_order_swingCardAmount;
	}
	public void setWholeSale_order_swingCardAmount(
			Double wholeSale_order_swingCardAmount) {
		this.wholeSale_order_swingCardAmount = wholeSale_order_swingCardAmount;
	}
	public Double getWholeSale_order_cash() {
		return wholeSale_order_cash;
	}
	public void setWholeSale_order_cash(Double wholeSale_order_cash) {
		this.wholeSale_order_cash = wholeSale_order_cash;
	}
	public Integer getWholeSale_order_pointMultiple() {
		return wholeSale_order_pointMultiple;
	}
	public void setWholeSale_order_pointMultiple(
			Integer wholeSale_order_pointMultiple) {
		this.wholeSale_order_pointMultiple = wholeSale_order_pointMultiple;
	}
	public String getWholeSale_order_swingCardName() {
		return wholeSale_order_swingCardName;
	}
	public void setWholeSale_order_swingCardName(
			String wholeSale_order_swingCardName) {
		this.wholeSale_order_swingCardName = wholeSale_order_swingCardName;
	}
	public String getWholeSale_order_swingCardId() {
		return wholeSale_order_swingCardId;
	}
	public void setWholeSale_order_swingCardId(String wholeSale_order_swingCardId) {
		this.wholeSale_order_swingCardId = wholeSale_order_swingCardId;
	}
	public Double getWholeSale_order_swingCardRemain() {
		return wholeSale_order_swingCardRemain;
	}
	public void setWholeSale_order_swingCardRemain(
			Double wholeSale_order_swingCardRemain) {
		this.wholeSale_order_swingCardRemain = wholeSale_order_swingCardRemain;
	}
	public Double getWholeSale_order_point() {
		return wholeSale_order_point;
	}
	public void setWholeSale_order_point(Double wholeSale_order_point) {
		this.wholeSale_order_point = wholeSale_order_point;
	}
	public Integer getWholeSale_order_printTimes() {
		return wholeSale_order_printTimes;
	}
	public void setWholeSale_order_printTimes(Integer wholeSale_order_printTimes) {
		this.wholeSale_order_printTimes = wholeSale_order_printTimes;
	}
	public String getWholeSale_order_modifyRemark() {
		return wholeSale_order_modifyRemark;
	}
	public void setWholeSale_order_modifyRemark(String wholeSale_order_modifyRemark) {
		this.wholeSale_order_modifyRemark = wholeSale_order_modifyRemark;
	}
	public String getWholeSale_order_swingCardPersonPropery() {
		return wholeSale_order_swingCardPersonPropery;
	}
	public void setWholeSale_order_swingCardPersonPropery(
			String wholeSale_order_swingCardPersonPropery) {
		this.wholeSale_order_swingCardPersonPropery = wholeSale_order_swingCardPersonPropery;
	}
	public Double getWholeSale_order_giftCardMax() {
		return wholeSale_order_giftCardMax;
	}
	public void setWholeSale_order_giftCardMax(Double wholeSale_order_giftCardMax) {
		this.wholeSale_order_giftCardMax = wholeSale_order_giftCardMax;
	}
	public Double getWholeSale_order_giftCardInitAmount() {
		return wholeSale_order_giftCardInitAmount;
	}
	public void setWholeSale_order_giftCardInitAmount(
			Double wholeSale_order_giftCardInitAmount) {
		this.wholeSale_order_giftCardInitAmount = wholeSale_order_giftCardInitAmount;
	}
	public String getWholeSale_order_purchasePersonName() {
		return wholeSale_order_purchasePersonName;
	}
	public void setWholeSale_order_purchasePersonName(
			String wholeSale_order_purchasePersonName) {
		this.wholeSale_order_purchasePersonName = wholeSale_order_purchasePersonName;
	}
	public String getWholeSale_order_purchasePersonID() {
		return wholeSale_order_purchasePersonID;
	}
	public void setWholeSale_order_purchasePersonID(
			String wholeSale_order_purchasePersonID) {
		this.wholeSale_order_purchasePersonID = wholeSale_order_purchasePersonID;
	}
	public String getWholeSale_order_purchasePersonPhone() {
		return wholeSale_order_purchasePersonPhone;
	}
	public void setWholeSale_order_purchasePersonPhone(
			String wholeSale_order_purchasePersonPhone) {
		this.wholeSale_order_purchasePersonPhone = wholeSale_order_purchasePersonPhone;
	}
	public String getWholeSale_order_pharmacistName() {
		return wholeSale_order_pharmacistName;
	}
	public void setWholeSale_order_pharmacistName(
			String wholeSale_order_pharmacistName) {
		this.wholeSale_order_pharmacistName = wholeSale_order_pharmacistName;
	}
	public String getWholeSale_order_pharmacistElectronicSignature() {
		return wholeSale_order_pharmacistElectronicSignature;
	}
	public void setWholeSale_order_pharmacistElectronicSignature(
			String wholeSale_order_pharmacistElectronicSignature) {
		this.wholeSale_order_pharmacistElectronicSignature = wholeSale_order_pharmacistElectronicSignature;
	}
	public String getWholeSale_order_drugTicketId() {
		return wholeSale_order_drugTicketId;
	}
	public void setWholeSale_order_drugTicketId(String wholeSale_order_drugTicketId) {
		this.wholeSale_order_drugTicketId = wholeSale_order_drugTicketId;
	}
	public Date getWholeSale_order_saveTime() {
		return wholeSale_order_saveTime;
	}
	public void setWholeSale_order_saveTime(Date wholeSale_order_saveTime) {
		this.wholeSale_order_saveTime = wholeSale_order_saveTime;
	}
	public Double getWholeSale_order_SingleDoseAmount() {
		return wholeSale_order_SingleDoseAmount;
	}
	public void setWholeSale_order_SingleDoseAmount(
			Double wholeSale_order_SingleDoseAmount) {
		this.wholeSale_order_SingleDoseAmount = wholeSale_order_SingleDoseAmount;
	}
	public Double getWholeSale_order_fullGiftAmount() {
		return wholeSale_order_fullGiftAmount;
	}
	public void setWholeSale_order_fullGiftAmount(
			Double wholeSale_order_fullGiftAmount) {
		this.wholeSale_order_fullGiftAmount = wholeSale_order_fullGiftAmount;
	}
	public Double getWholeSale_order_fullGiftExchange() {
		return wholeSale_order_fullGiftExchange;
	}
	public void setWholeSale_order_fullGiftExchange(
			Double wholeSale_order_fullGiftExchange) {
		this.wholeSale_order_fullGiftExchange = wholeSale_order_fullGiftExchange;
	}
	public Double getWholeSale_order_fullGiftRemain() {
		return wholeSale_order_fullGiftRemain;
	}
	public void setWholeSale_order_fullGiftRemain(
			Double wholeSale_order_fullGiftRemain) {
		this.wholeSale_order_fullGiftRemain = wholeSale_order_fullGiftRemain;
	}
	public Double getWholeSale_order_otherCharge() {
		return wholeSale_order_otherCharge;
	}
	public void setWholeSale_order_otherCharge(Double wholeSale_order_otherCharge) {
		this.wholeSale_order_otherCharge = wholeSale_order_otherCharge;
	}
	public Double getWholeSale_order_receivableAmount() {
		return wholeSale_order_receivableAmount;
	}
	public void setWholeSale_order_receivableAmount(
			Double wholeSale_order_receivableAmount) {
		this.wholeSale_order_receivableAmount = wholeSale_order_receivableAmount;
	}
	public Double getWholeSale_order_pointPay() {
		return wholeSale_order_pointPay;
	}
	public void setWholeSale_order_pointPay(Double wholeSale_order_pointPay) {
		this.wholeSale_order_pointPay = wholeSale_order_pointPay;
	}
	
	
	
}