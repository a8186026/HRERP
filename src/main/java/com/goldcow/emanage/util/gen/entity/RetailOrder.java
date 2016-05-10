package com.goldcow.emanage.util.gen.entity;

import java.util.Date;

import com.goldcow.sframe.util.DataGridModel;
import com.goldcow.sframe.util.mybatis.BaseEntity;
/**
 * 零售订单实体类
 * 
 * @author cyx
 * @version v1.0
 * @since 2015-11-16
 */
public class RetailOrder extends DataGridModel implements BaseEntity{
	private static final long serialVersionUID = 1L;
	/** 序号 */
	private Integer retail_order_id;
	/** 订货时间 */
	private Date retail_order_orderTime;		//第一次插数据库时间
	/** 票号 */
	private String retail_order_ticketId;
	/** 此次应收 */
	private Double retail_order_currentReceivable;		//总金额
	/** 折让 */
	private Double retail_order_discountAmount;
	/** 抹零 */
	private Double retail_order_wipeZero;
	/** 结算金额 */
	private Double retail_order_settlementAmount;		//应收
	/** 发票号 */
	private String retail_order_receiptNumber;			//
	/** 会员卡号 */
	private String mem_card_id;
	/** 操作员 */
	private String retail_order_operator;
	/** 结账 */
	private Integer retail_order_checkout;			
	/** 结账人 */
	private String retail_order_checkoutPerson;
	/** 结账时间 */
	private Date retail_order_checkoutTime;
	/** 毛利 */
	private Double retail_order_grossProfit;
	/** 上传 */
	private Integer retail_order_upload;
	/** 草稿0未完成1已完成 */
	private Integer retail_order_draft;
	/** 开验收单 */
	private Integer retail_order_printReceipt;
	/** 机器名 */
	private String retail_order_machineName;
	/** 接收 */
	private Integer retail_order_receive;
	/** 付数 */
	private Integer retail_order_doseNumber;
	/** 实收金额 */
	private Double retail_order_realIncomeAmount;
	/** 找零 */
	private Double retail_order_change;
	/** 代金卡 */
	private Double retail_order_giftCard;
	/** 打折 */
	private Double retail_order_discount;
	/** 刷卡方式 */
	private String retail_order_swingCardStyle;
	/** 刷卡金额 */
	private Double retail_order_swingCardAmount;
	/** 现金 */
	private Double retail_order_cash;
	/** 积分倍数 */
	private Integer retail_order_pointMultiple;
	/** 刷卡姓名 */
	private String retail_order_swingCardName;
	/** 刷卡卡号 */
	private String retail_order_swingCardId;
	/** 刷卡余额 */
	private Double retail_order_swingCardRemain;
	/** 积分 */
	private Double retail_order_point;
	/** 打印次数 */
	private Integer retail_order_printTimes;
	/** 修改备注 */
	private String retail_order_modifyRemark;
	/** 刷卡人员性质 */
	private String retail_order_swingCardPersonPropery;
	/** 代金卡最大 */
	private Double retail_order_giftCardMax;
	/** 发代金卡金额 */
	private Double retail_order_giftCardInitAmount;
	/** 购药人 */
	private String retail_order_purchasePersonName;
	/** 购药人身份证 */
	private String retail_order_purchasePersonID;
	/** 购药人电话 */
	private String retail_order_purchasePersonPhone;
	/** 药师姓名 */
	private String retail_order_pharmacistName;
	/** 药师电子签名 */
	private String retail_order_pharmacistElectronicSignature;
	/** 药监编号 */
	private String retail_order_drugTicketId;
	/** 存盘时间 */
	private Date retail_order_saveTime;
	/** 单服金额 */
	private Double retail_order_SingleDoseAmount;
	/** 满额赠金额 */
	private Double retail_order_fullGiftAmount;
	/** 满额赠兑换 */
	private Double retail_order_fullGiftExchange;
	/** 满额赠剩余 */
	private Double retail_order_fullGiftRemain;
	/** 其它收费 */
	private Double retail_order_otherCharge;
	/** 应收 */
	private Double retail_order_receivableAmount;
	/** 积分当钱 */
	private Double retail_order_pointPay;
	
	public Integer getRetail_order_id() {
		return retail_order_id;
	}
	public void setRetail_order_id(Integer retail_order_id) {
		this.retail_order_id = retail_order_id;
	}
	public Date getRetail_order_orderTime() {
		return retail_order_orderTime;
	}
	public void setRetail_order_orderTime(Date retail_order_orderTime) {
		this.retail_order_orderTime = retail_order_orderTime;
	}
	public String getRetail_order_ticketId() {
		return retail_order_ticketId;
	}
	public void setRetail_order_ticketId(String retail_order_ticketId) {
		this.retail_order_ticketId = retail_order_ticketId;
	}
	public Double getRetail_order_currentReceivable() {
		return retail_order_currentReceivable;
	}
	public void setRetail_order_currentReceivable(
			Double retail_order_currentReceivable) {
		this.retail_order_currentReceivable = retail_order_currentReceivable;
	}
	public Double getRetail_order_discountAmount() {
		return retail_order_discountAmount;
	}
	public void setRetail_order_discountAmount(Double retail_order_discountAmount) {
		this.retail_order_discountAmount = retail_order_discountAmount;
	}
	public Double getRetail_order_wipeZero() {
		return retail_order_wipeZero;
	}
	public void setRetail_order_wipeZero(Double retail_order_wipeZero) {
		this.retail_order_wipeZero = retail_order_wipeZero;
	}
	public Double getRetail_order_settlementAmount() {
		return retail_order_settlementAmount;
	}
	public void setRetail_order_settlementAmount(
			Double retail_order_settlementAmount) {
		this.retail_order_settlementAmount = retail_order_settlementAmount;
	}
	public String getRetail_order_receiptNumber() {
		return retail_order_receiptNumber;
	}
	public void setRetail_order_receiptNumber(String retail_order_receiptNumber) {
		this.retail_order_receiptNumber = retail_order_receiptNumber;
	}
	public String getMem_card_id() {
		return mem_card_id;
	}
	public void setMem_card_id(String mem_card_id) {
		this.mem_card_id = mem_card_id;
	}
	public String getRetail_order_operator() {
		return retail_order_operator;
	}
	public void setRetail_order_operator(String retail_order_operator) {
		this.retail_order_operator = retail_order_operator;
	}
	public Integer getRetail_order_checkout() {
		return retail_order_checkout;
	}
	public void setRetail_order_checkout(Integer retail_order_checkout) {
		this.retail_order_checkout = retail_order_checkout;
	}
	public String getRetail_order_checkoutPerson() {
		return retail_order_checkoutPerson;
	}
	public void setRetail_order_checkoutPerson(String retail_order_checkoutPerson) {
		this.retail_order_checkoutPerson = retail_order_checkoutPerson;
	}
	public Date getRetail_order_checkoutTime() {
		return retail_order_checkoutTime;
	}
	public void setRetail_order_checkoutTime(Date retail_order_checkoutTime) {
		this.retail_order_checkoutTime = retail_order_checkoutTime;
	}
	public Double getRetail_order_grossProfit() {
		return retail_order_grossProfit;
	}
	public void setRetail_order_grossProfit(Double retail_order_grossProfit) {
		this.retail_order_grossProfit = retail_order_grossProfit;
	}
	public Integer getRetail_order_upload() {
		return retail_order_upload;
	}
	public void setRetail_order_upload(Integer retail_order_upload) {
		this.retail_order_upload = retail_order_upload;
	}
	public Integer getRetail_order_draft() {
		return retail_order_draft;
	}
	public void setRetail_order_draft(Integer retail_order_draft) {
		this.retail_order_draft = retail_order_draft;
	}
	public Integer getRetail_order_printReceipt() {
		return retail_order_printReceipt;
	}
	public void setRetail_order_printReceipt(Integer retail_order_printReceipt) {
		this.retail_order_printReceipt = retail_order_printReceipt;
	}
	public String getRetail_order_machineName() {
		return retail_order_machineName;
	}
	public void setRetail_order_machineName(String retail_order_machineName) {
		this.retail_order_machineName = retail_order_machineName;
	}
	public Integer getRetail_order_receive() {
		return retail_order_receive;
	}
	public void setRetail_order_receive(Integer retail_order_receive) {
		this.retail_order_receive = retail_order_receive;
	}
	public Integer getRetail_order_doseNumber() {
		return retail_order_doseNumber;
	}
	public void setRetail_order_doseNumber(Integer retail_order_doseNumber) {
		this.retail_order_doseNumber = retail_order_doseNumber;
	}
	public Double getRetail_order_realIncomeAmount() {
		return retail_order_realIncomeAmount;
	}
	public void setRetail_order_realIncomeAmount(
			Double retail_order_realIncomeAmount) {
		this.retail_order_realIncomeAmount = retail_order_realIncomeAmount;
	}
	public Double getRetail_order_change() {
		return retail_order_change;
	}
	public void setRetail_order_change(Double retail_order_change) {
		this.retail_order_change = retail_order_change;
	}
	public Double getRetail_order_giftCard() {
		return retail_order_giftCard;
	}
	public void setRetail_order_giftCard(Double retail_order_giftCard) {
		this.retail_order_giftCard = retail_order_giftCard;
	}
	public Double getRetail_order_discount() {
		return retail_order_discount;
	}
	public void setRetail_order_discount(Double retail_order_discount) {
		this.retail_order_discount = retail_order_discount;
	}
	public String getRetail_order_swingCardStyle() {
		return retail_order_swingCardStyle;
	}
	public void setRetail_order_swingCardStyle(String retail_order_swingCardStyle) {
		this.retail_order_swingCardStyle = retail_order_swingCardStyle;
	}
	public Double getRetail_order_swingCardAmount() {
		return retail_order_swingCardAmount;
	}
	public void setRetail_order_swingCardAmount(Double retail_order_swingCardAmount) {
		this.retail_order_swingCardAmount = retail_order_swingCardAmount;
	}
	public Double getRetail_order_cash() {
		return retail_order_cash;
	}
	public void setRetail_order_cash(Double retail_order_cash) {
		this.retail_order_cash = retail_order_cash;
	}
	public Integer getRetail_order_pointMultiple() {
		return retail_order_pointMultiple;
	}
	public void setRetail_order_pointMultiple(Integer retail_order_pointMultiple) {
		this.retail_order_pointMultiple = retail_order_pointMultiple;
	}
	public String getRetail_order_swingCardName() {
		return retail_order_swingCardName;
	}
	public void setRetail_order_swingCardName(String retail_order_swingCardName) {
		this.retail_order_swingCardName = retail_order_swingCardName;
	}
	public String getRetail_order_swingCardId() {
		return retail_order_swingCardId;
	}
	public void setRetail_order_swingCardId(String retail_order_swingCardId) {
		this.retail_order_swingCardId = retail_order_swingCardId;
	}
	public Double getRetail_order_swingCardRemain() {
		return retail_order_swingCardRemain;
	}
	public void setRetail_order_swingCardRemain(Double retail_order_swingCardRemain) {
		this.retail_order_swingCardRemain = retail_order_swingCardRemain;
	}
	public Double getRetail_order_point() {
		return retail_order_point;
	}
	public void setRetail_order_point(Double retail_order_point) {
		this.retail_order_point = retail_order_point;
	}
	public Integer getRetail_order_printTimes() {
		return retail_order_printTimes;
	}
	public void setRetail_order_printTimes(Integer retail_order_printTimes) {
		this.retail_order_printTimes = retail_order_printTimes;
	}
	public String getRetail_order_modifyRemark() {
		return retail_order_modifyRemark;
	}
	public void setRetail_order_modifyRemark(String retail_order_modifyRemark) {
		this.retail_order_modifyRemark = retail_order_modifyRemark;
	}
	public String getRetail_order_swingCardPersonPropery() {
		return retail_order_swingCardPersonPropery;
	}
	public void setRetail_order_swingCardPersonPropery(
			String retail_order_swingCardPersonPropery) {
		this.retail_order_swingCardPersonPropery = retail_order_swingCardPersonPropery;
	}
	public Double getRetail_order_giftCardMax() {
		return retail_order_giftCardMax;
	}
	public void setRetail_order_giftCardMax(Double retail_order_giftCardMax) {
		this.retail_order_giftCardMax = retail_order_giftCardMax;
	}
	public Double getRetail_order_giftCardInitAmount() {
		return retail_order_giftCardInitAmount;
	}
	public void setRetail_order_giftCardInitAmount(
			Double retail_order_giftCardInitAmount) {
		this.retail_order_giftCardInitAmount = retail_order_giftCardInitAmount;
	}
	public String getRetail_order_purchasePersonName() {
		return retail_order_purchasePersonName;
	}
	public void setRetail_order_purchasePersonName(
			String retail_order_purchasePersonName) {
		this.retail_order_purchasePersonName = retail_order_purchasePersonName;
	}
	public String getRetail_order_purchasePersonID() {
		return retail_order_purchasePersonID;
	}
	public void setRetail_order_purchasePersonID(
			String retail_order_purchasePersonID) {
		this.retail_order_purchasePersonID = retail_order_purchasePersonID;
	}
	public String getRetail_order_purchasePersonPhone() {
		return retail_order_purchasePersonPhone;
	}
	public void setRetail_order_purchasePersonPhone(
			String retail_order_purchasePersonPhone) {
		this.retail_order_purchasePersonPhone = retail_order_purchasePersonPhone;
	}
	public String getRetail_order_pharmacistName() {
		return retail_order_pharmacistName;
	}
	public void setRetail_order_pharmacistName(String retail_order_pharmacistName) {
		this.retail_order_pharmacistName = retail_order_pharmacistName;
	}
	public String getRetail_order_pharmacistElectronicSignature() {
		return retail_order_pharmacistElectronicSignature;
	}
	public void setRetail_order_pharmacistElectronicSignature(
			String retail_order_pharmacistElectronicSignature) {
		this.retail_order_pharmacistElectronicSignature = retail_order_pharmacistElectronicSignature;
	}
	public String getRetail_order_drugTicketId() {
		return retail_order_drugTicketId;
	}
	public void setRetail_order_drugTicketId(String retail_order_drugTicketId) {
		this.retail_order_drugTicketId = retail_order_drugTicketId;
	}
	public Date getRetail_order_saveTime() {
		return retail_order_saveTime;
	}
	public void setRetail_order_saveTime(Date retail_order_saveTime) {
		this.retail_order_saveTime = retail_order_saveTime;
	}
	public Double getRetail_order_SingleDoseAmount() {
		return retail_order_SingleDoseAmount;
	}
	public void setRetail_order_SingleDoseAmount(
			Double retail_order_SingleDoseAmount) {
		this.retail_order_SingleDoseAmount = retail_order_SingleDoseAmount;
	}
	public Double getRetail_order_fullGiftAmount() {
		return retail_order_fullGiftAmount;
	}
	public void setRetail_order_fullGiftAmount(Double retail_order_fullGiftAmount) {
		this.retail_order_fullGiftAmount = retail_order_fullGiftAmount;
	}
	public Double getRetail_order_fullGiftExchange() {
		return retail_order_fullGiftExchange;
	}
	public void setRetail_order_fullGiftExchange(
			Double retail_order_fullGiftExchange) {
		this.retail_order_fullGiftExchange = retail_order_fullGiftExchange;
	}
	public Double getRetail_order_fullGiftRemain() {
		return retail_order_fullGiftRemain;
	}
	public void setRetail_order_fullGiftRemain(Double retail_order_fullGiftRemain) {
		this.retail_order_fullGiftRemain = retail_order_fullGiftRemain;
	}
	public Double getRetail_order_otherCharge() {
		return retail_order_otherCharge;
	}
	public void setRetail_order_otherCharge(Double retail_order_otherCharge) {
		this.retail_order_otherCharge = retail_order_otherCharge;
	}
	public Double getRetail_order_receivableAmount() {
		return retail_order_receivableAmount;
	}
	public void setRetail_order_receivableAmount(
			Double retail_order_receivableAmount) {
		this.retail_order_receivableAmount = retail_order_receivableAmount;
	}
	public Double getRetail_order_pointPay() {
		return retail_order_pointPay;
	}
	public void setRetail_order_pointPay(Double retail_order_pointPay) {
		this.retail_order_pointPay = retail_order_pointPay;
	}

}