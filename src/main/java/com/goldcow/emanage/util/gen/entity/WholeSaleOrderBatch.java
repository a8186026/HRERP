package com.goldcow.emanage.util.gen.entity;

import java.util.Date;

import com.goldcow.sframe.util.DataGridModel;
import com.goldcow.sframe.util.mybatis.BaseEntity;
/**
 * 零售产品批次实体类
 * 
 * @author zyx
 * @version v1.5
 * @since 2016-05-12
 */
public class WholeSaleOrderBatch extends DataGridModel implements BaseEntity{
	private static final long serialVersionUID = 1L;
	
	/** 出库小号 */
	private Integer wholeSale_batch_id;			//零售产品批次主键
	/** 票号 */
	private String wholeSale_order_ticketId;		//零售订单票号
	/** 序号 */
	private Integer wholeSale_productId;		//零售产品Id
	/** 小号 */
	private Integer wholeSale_batch_smallNumber;	//对应零售产品的自增序号
	/** 库存id */
	private Integer stock_info_id;
	/** 产品序号 */
	private Integer product_id;					//产品
	/** 库房 */
	private Integer stock_storage;			//库存
	/** 包装量 */
	private Integer stock_packunit;		//产品
	/** 批号 */
	private String stock_batchCode;		//库存
	/** 失效期 */
	private Date stock_invalidDate;
	/** 条码 */
	private String product_barcode;
	/** 库房数量 */
	private Double stock_storageNumber;
	/** 进价 */
	private Double stock_purchasePrice;		//库存
	/** 结算价 */
	private Double stock_settlementPrice;		//库存
	/** 入库票号 */
	private String stock_intakeTicket;		//库存
	/** 入库小号 */
	private Integer stock_intakeSmallNumber;	//库存表中入库小号
	/** 数量 */
	private Double wholeSale_batch_quantity;
	/** 单价 */
	private Double wholeSale_batch_unitPrice;
	/** 减差价 */
	private Double wholeSale_batch_cutDifference;
	/** 金额 */
	private Double wholeSale_batch_amount;
	/** 毛利 */
	private Double wholeSale_batch_grossProfit;
	/** 应回款日 */
	private Date wholeSale_batch_paymentTime;
	/** 库房结算 */
	private Integer wholeSale_batch_storageSettlement;
	/** 结算时间 */
	private Date wholeSale_batch_settlementTime;
	/** 退回数量 */
	private Double wholeSale_batch_refundQuantity;
	/** 库房接收 */
	private Integer wholeSale_batch_storageReceive;
	/** 接收时间 */
	private Date wholeSale_batch_receiveTime;
	/** 日期 */
	private Date wholeSale_batch_date;
	/** 库房审核 */
	private Integer wholeSale_batch_storageCheck;
	/** 审核人 */
	private String wholeSale_batch_storageCheckPerson;
	/** 审核时间 */
	private Date wholeSale_batch_storageCheckTime;
	/** 折让 */
	private Double wholeSale_batch_discountAmount;
	/** 经手人 */
	private String wholeSale_batch_brokerage;
	/** 柜组 */
	private String wholeSale_batch_groupNum;
	/** 经手人2 */
	private String wholeSale_batch_brokerage2;
	/** 促销品种 */
	private Integer wholeSale_batch_promotionVariety;
	/** 零售提成 */
	private Double wholeSale_batch_retailCommission;
	/** 会员不积分 */
	private Integer wholeSale_batch_mbernointegral;
	/** 单据分组号 */
	private Integer wholeSale_batch_documentGroupNum;
	/** 原出库票号 */
	private String wholeSale_batch_originalDeliveryTicketId;
	/** 积分 */
	private Double wholeSale_batch_point;
	/** 积分倍数 */
	private Integer wholeSale_batch_pointMutiple;
	/** 记分金额 */
	private Integer wholeSale_batch_pointAmount;
	/** 原出库小号 */
	private Integer wholeSale_batch_originalDeliverySmallNum;
	/** 允许退货 */
	private Integer wholeSale_batch_allowReturn;
	/** 允许退货人 */
	private String wholeSale_batch_allowReturnPerson;
	/** 允许退货时间 */
	private Date wholeSale_batch_allowReturnTime;
	/** 允许退货原因 */
	private String wholeSale_batch_aloowReturnReason;
	/** 其它分类2 */
	private String wholeSale_batch_othercategory2;
	/** 销售序号 */
	private Integer wholeSale_batch_saleId;
	/** 销售类型 */
	private String wholeSale_batch_saleType;
	/** 会员 */
	private String wholeSale_batch_member;
	/** 门店售价 */
	private Double wholeSale_batch_storePrice;
	/** 满额赠金额 */
	private Double wholeSale_batch_fullGiftAmount;
	/** 兑换满额赠金额 */
	private Double wholeSale_batch_fullGiftExchange;
	/** 单品打折授权人 */
	private String wholeSale_batch_singleDiscountAuthoPerson;
	/** 单品打折原因 */
	private String wholeSale_batch_singleDiscountAuthoReason;
	/** 入库摘要 */
	private String wholeSale_batch_intakeBrief;
	/** 接收 */
	private Integer wholeSale_batch_receive;
	/** 已拆零 */
	private Integer wholeSale_batch_dismounting;
	public Integer getWholeSale_batch_id() {
		return wholeSale_batch_id;
	}
	public void setWholeSale_batch_id(Integer wholeSale_batch_id) {
		this.wholeSale_batch_id = wholeSale_batch_id;
	}
	public String getWholeSale_order_ticketId() {
		return wholeSale_order_ticketId;
	}
	public void setWholeSale_order_ticketId(String wholeSale_order_ticketId) {
		this.wholeSale_order_ticketId = wholeSale_order_ticketId;
	}
	public Integer getWholeSale_productId() {
		return wholeSale_productId;
	}
	public void setWholeSale_productId(Integer wholeSale_productId) {
		this.wholeSale_productId = wholeSale_productId;
	}
	public Integer getWholeSale_batch_smallNumber() {
		return wholeSale_batch_smallNumber;
	}
	public void setWholeSale_batch_smallNumber(Integer wholeSale_batch_smallNumber) {
		this.wholeSale_batch_smallNumber = wholeSale_batch_smallNumber;
	}
	public Integer getStock_info_id() {
		return stock_info_id;
	}
	public void setStock_info_id(Integer stock_info_id) {
		this.stock_info_id = stock_info_id;
	}
	public Integer getProduct_id() {
		return product_id;
	}
	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}
	public Integer getStock_storage() {
		return stock_storage;
	}
	public void setStock_storage(Integer stock_storage) {
		this.stock_storage = stock_storage;
	}
	public Integer getStock_packunit() {
		return stock_packunit;
	}
	public void setStock_packunit(Integer stock_packunit) {
		this.stock_packunit = stock_packunit;
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
	public String getProduct_barcode() {
		return product_barcode;
	}
	public void setProduct_barcode(String product_barcode) {
		this.product_barcode = product_barcode;
	}
	public Double getStock_storageNumber() {
		return stock_storageNumber;
	}
	public void setStock_storageNumber(Double stock_storageNumber) {
		this.stock_storageNumber = stock_storageNumber;
	}
	public Double getStock_purchasePrice() {
		return stock_purchasePrice;
	}
	public void setStock_purchasePrice(Double stock_purchasePrice) {
		this.stock_purchasePrice = stock_purchasePrice;
	}
	public Double getStock_settlementPrice() {
		return stock_settlementPrice;
	}
	public void setStock_settlementPrice(Double stock_settlementPrice) {
		this.stock_settlementPrice = stock_settlementPrice;
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
	public Double getWholeSale_batch_quantity() {
		return wholeSale_batch_quantity;
	}
	public void setWholeSale_batch_quantity(Double wholeSale_batch_quantity) {
		this.wholeSale_batch_quantity = wholeSale_batch_quantity;
	}
	public Double getWholeSale_batch_unitPrice() {
		return wholeSale_batch_unitPrice;
	}
	public void setWholeSale_batch_unitPrice(Double wholeSale_batch_unitPrice) {
		this.wholeSale_batch_unitPrice = wholeSale_batch_unitPrice;
	}
	public Double getWholeSale_batch_cutDifference() {
		return wholeSale_batch_cutDifference;
	}
	public void setWholeSale_batch_cutDifference(
			Double wholeSale_batch_cutDifference) {
		this.wholeSale_batch_cutDifference = wholeSale_batch_cutDifference;
	}
	public Double getWholeSale_batch_amount() {
		return wholeSale_batch_amount;
	}
	public void setWholeSale_batch_amount(Double wholeSale_batch_amount) {
		this.wholeSale_batch_amount = wholeSale_batch_amount;
	}
	public Double getWholeSale_batch_grossProfit() {
		return wholeSale_batch_grossProfit;
	}
	public void setWholeSale_batch_grossProfit(Double wholeSale_batch_grossProfit) {
		this.wholeSale_batch_grossProfit = wholeSale_batch_grossProfit;
	}
	public Date getWholeSale_batch_paymentTime() {
		return wholeSale_batch_paymentTime;
	}
	public void setWholeSale_batch_paymentTime(Date wholeSale_batch_paymentTime) {
		this.wholeSale_batch_paymentTime = wholeSale_batch_paymentTime;
	}
	public Integer getWholeSale_batch_storageSettlement() {
		return wholeSale_batch_storageSettlement;
	}
	public void setWholeSale_batch_storageSettlement(
			Integer wholeSale_batch_storageSettlement) {
		this.wholeSale_batch_storageSettlement = wholeSale_batch_storageSettlement;
	}
	public Date getWholeSale_batch_settlementTime() {
		return wholeSale_batch_settlementTime;
	}
	public void setWholeSale_batch_settlementTime(
			Date wholeSale_batch_settlementTime) {
		this.wholeSale_batch_settlementTime = wholeSale_batch_settlementTime;
	}
	public Double getWholeSale_batch_refundQuantity() {
		return wholeSale_batch_refundQuantity;
	}
	public void setWholeSale_batch_refundQuantity(
			Double wholeSale_batch_refundQuantity) {
		this.wholeSale_batch_refundQuantity = wholeSale_batch_refundQuantity;
	}
	public Integer getWholeSale_batch_storageReceive() {
		return wholeSale_batch_storageReceive;
	}
	public void setWholeSale_batch_storageReceive(
			Integer wholeSale_batch_storageReceive) {
		this.wholeSale_batch_storageReceive = wholeSale_batch_storageReceive;
	}
	public Date getWholeSale_batch_receiveTime() {
		return wholeSale_batch_receiveTime;
	}
	public void setWholeSale_batch_receiveTime(Date wholeSale_batch_receiveTime) {
		this.wholeSale_batch_receiveTime = wholeSale_batch_receiveTime;
	}
	public Date getWholeSale_batch_date() {
		return wholeSale_batch_date;
	}
	public void setWholeSale_batch_date(Date wholeSale_batch_date) {
		this.wholeSale_batch_date = wholeSale_batch_date;
	}
	public Integer getWholeSale_batch_storageCheck() {
		return wholeSale_batch_storageCheck;
	}
	public void setWholeSale_batch_storageCheck(Integer wholeSale_batch_storageCheck) {
		this.wholeSale_batch_storageCheck = wholeSale_batch_storageCheck;
	}
	public String getWholeSale_batch_storageCheckPerson() {
		return wholeSale_batch_storageCheckPerson;
	}
	public void setWholeSale_batch_storageCheckPerson(
			String wholeSale_batch_storageCheckPerson) {
		this.wholeSale_batch_storageCheckPerson = wholeSale_batch_storageCheckPerson;
	}
	public Date getWholeSale_batch_storageCheckTime() {
		return wholeSale_batch_storageCheckTime;
	}
	public void setWholeSale_batch_storageCheckTime(
			Date wholeSale_batch_storageCheckTime) {
		this.wholeSale_batch_storageCheckTime = wholeSale_batch_storageCheckTime;
	}
	public Double getWholeSale_batch_discountAmount() {
		return wholeSale_batch_discountAmount;
	}
	public void setWholeSale_batch_discountAmount(
			Double wholeSale_batch_discountAmount) {
		this.wholeSale_batch_discountAmount = wholeSale_batch_discountAmount;
	}
	public String getWholeSale_batch_brokerage() {
		return wholeSale_batch_brokerage;
	}
	public void setWholeSale_batch_brokerage(String wholeSale_batch_brokerage) {
		this.wholeSale_batch_brokerage = wholeSale_batch_brokerage;
	}
	public String getWholeSale_batch_groupNum() {
		return wholeSale_batch_groupNum;
	}
	public void setWholeSale_batch_groupNum(String wholeSale_batch_groupNum) {
		this.wholeSale_batch_groupNum = wholeSale_batch_groupNum;
	}
	public String getWholeSale_batch_brokerage2() {
		return wholeSale_batch_brokerage2;
	}
	public void setWholeSale_batch_brokerage2(String wholeSale_batch_brokerage2) {
		this.wholeSale_batch_brokerage2 = wholeSale_batch_brokerage2;
	}
	public Integer getWholeSale_batch_promotionVariety() {
		return wholeSale_batch_promotionVariety;
	}
	public void setWholeSale_batch_promotionVariety(
			Integer wholeSale_batch_promotionVariety) {
		this.wholeSale_batch_promotionVariety = wholeSale_batch_promotionVariety;
	}
	public Double getWholeSale_batch_retailCommission() {
		return wholeSale_batch_retailCommission;
	}
	public void setWholeSale_batch_retailCommission(
			Double wholeSale_batch_retailCommission) {
		this.wholeSale_batch_retailCommission = wholeSale_batch_retailCommission;
	}
	public Integer getWholeSale_batch_mbernointegral() {
		return wholeSale_batch_mbernointegral;
	}
	public void setWholeSale_batch_mbernointegral(
			Integer wholeSale_batch_mbernointegral) {
		this.wholeSale_batch_mbernointegral = wholeSale_batch_mbernointegral;
	}
	public Integer getWholeSale_batch_documentGroupNum() {
		return wholeSale_batch_documentGroupNum;
	}
	public void setWholeSale_batch_documentGroupNum(
			Integer wholeSale_batch_documentGroupNum) {
		this.wholeSale_batch_documentGroupNum = wholeSale_batch_documentGroupNum;
	}
	public String getWholeSale_batch_originalDeliveryTicketId() {
		return wholeSale_batch_originalDeliveryTicketId;
	}
	public void setWholeSale_batch_originalDeliveryTicketId(
			String wholeSale_batch_originalDeliveryTicketId) {
		this.wholeSale_batch_originalDeliveryTicketId = wholeSale_batch_originalDeliveryTicketId;
	}
	public Double getWholeSale_batch_point() {
		return wholeSale_batch_point;
	}
	public void setWholeSale_batch_point(Double wholeSale_batch_point) {
		this.wholeSale_batch_point = wholeSale_batch_point;
	}
	public Integer getWholeSale_batch_pointMutiple() {
		return wholeSale_batch_pointMutiple;
	}
	public void setWholeSale_batch_pointMutiple(Integer wholeSale_batch_pointMutiple) {
		this.wholeSale_batch_pointMutiple = wholeSale_batch_pointMutiple;
	}
	public Integer getWholeSale_batch_pointAmount() {
		return wholeSale_batch_pointAmount;
	}
	public void setWholeSale_batch_pointAmount(Integer wholeSale_batch_pointAmount) {
		this.wholeSale_batch_pointAmount = wholeSale_batch_pointAmount;
	}
	public Integer getWholeSale_batch_originalDeliverySmallNum() {
		return wholeSale_batch_originalDeliverySmallNum;
	}
	public void setWholeSale_batch_originalDeliverySmallNum(
			Integer wholeSale_batch_originalDeliverySmallNum) {
		this.wholeSale_batch_originalDeliverySmallNum = wholeSale_batch_originalDeliverySmallNum;
	}
	public Integer getWholeSale_batch_allowReturn() {
		return wholeSale_batch_allowReturn;
	}
	public void setWholeSale_batch_allowReturn(Integer wholeSale_batch_allowReturn) {
		this.wholeSale_batch_allowReturn = wholeSale_batch_allowReturn;
	}
	public String getWholeSale_batch_allowReturnPerson() {
		return wholeSale_batch_allowReturnPerson;
	}
	public void setWholeSale_batch_allowReturnPerson(
			String wholeSale_batch_allowReturnPerson) {
		this.wholeSale_batch_allowReturnPerson = wholeSale_batch_allowReturnPerson;
	}
	public Date getWholeSale_batch_allowReturnTime() {
		return wholeSale_batch_allowReturnTime;
	}
	public void setWholeSale_batch_allowReturnTime(
			Date wholeSale_batch_allowReturnTime) {
		this.wholeSale_batch_allowReturnTime = wholeSale_batch_allowReturnTime;
	}
	public String getWholeSale_batch_aloowReturnReason() {
		return wholeSale_batch_aloowReturnReason;
	}
	public void setWholeSale_batch_aloowReturnReason(
			String wholeSale_batch_aloowReturnReason) {
		this.wholeSale_batch_aloowReturnReason = wholeSale_batch_aloowReturnReason;
	}
	public String getWholeSale_batch_othercategory2() {
		return wholeSale_batch_othercategory2;
	}
	public void setWholeSale_batch_othercategory2(
			String wholeSale_batch_othercategory2) {
		this.wholeSale_batch_othercategory2 = wholeSale_batch_othercategory2;
	}
	public Integer getWholeSale_batch_saleId() {
		return wholeSale_batch_saleId;
	}
	public void setWholeSale_batch_saleId(Integer wholeSale_batch_saleId) {
		this.wholeSale_batch_saleId = wholeSale_batch_saleId;
	}
	public String getWholeSale_batch_saleType() {
		return wholeSale_batch_saleType;
	}
	public void setWholeSale_batch_saleType(String wholeSale_batch_saleType) {
		this.wholeSale_batch_saleType = wholeSale_batch_saleType;
	}
	public String getWholeSale_batch_member() {
		return wholeSale_batch_member;
	}
	public void setWholeSale_batch_member(String wholeSale_batch_member) {
		this.wholeSale_batch_member = wholeSale_batch_member;
	}
	public Double getWholeSale_batch_storePrice() {
		return wholeSale_batch_storePrice;
	}
	public void setWholeSale_batch_storePrice(Double wholeSale_batch_storePrice) {
		this.wholeSale_batch_storePrice = wholeSale_batch_storePrice;
	}
	public Double getWholeSale_batch_fullGiftAmount() {
		return wholeSale_batch_fullGiftAmount;
	}
	public void setWholeSale_batch_fullGiftAmount(
			Double wholeSale_batch_fullGiftAmount) {
		this.wholeSale_batch_fullGiftAmount = wholeSale_batch_fullGiftAmount;
	}
	public Double getWholeSale_batch_fullGiftExchange() {
		return wholeSale_batch_fullGiftExchange;
	}
	public void setWholeSale_batch_fullGiftExchange(
			Double wholeSale_batch_fullGiftExchange) {
		this.wholeSale_batch_fullGiftExchange = wholeSale_batch_fullGiftExchange;
	}
	public String getWholeSale_batch_singleDiscountAuthoPerson() {
		return wholeSale_batch_singleDiscountAuthoPerson;
	}
	public void setWholeSale_batch_singleDiscountAuthoPerson(
			String wholeSale_batch_singleDiscountAuthoPerson) {
		this.wholeSale_batch_singleDiscountAuthoPerson = wholeSale_batch_singleDiscountAuthoPerson;
	}
	public String getWholeSale_batch_singleDiscountAuthoReason() {
		return wholeSale_batch_singleDiscountAuthoReason;
	}
	public void setWholeSale_batch_singleDiscountAuthoReason(
			String wholeSale_batch_singleDiscountAuthoReason) {
		this.wholeSale_batch_singleDiscountAuthoReason = wholeSale_batch_singleDiscountAuthoReason;
	}
	public String getWholeSale_batch_intakeBrief() {
		return wholeSale_batch_intakeBrief;
	}
	public void setWholeSale_batch_intakeBrief(String wholeSale_batch_intakeBrief) {
		this.wholeSale_batch_intakeBrief = wholeSale_batch_intakeBrief;
	}
	public Integer getWholeSale_batch_receive() {
		return wholeSale_batch_receive;
	}
	public void setWholeSale_batch_receive(Integer wholeSale_batch_receive) {
		this.wholeSale_batch_receive = wholeSale_batch_receive;
	}
	public Integer getWholeSale_batch_dismounting() {
		return wholeSale_batch_dismounting;
	}
	public void setWholeSale_batch_dismounting(Integer wholeSale_batch_dismounting) {
		this.wholeSale_batch_dismounting = wholeSale_batch_dismounting;
	}
	
}