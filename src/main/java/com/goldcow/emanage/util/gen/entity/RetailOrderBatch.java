package com.goldcow.emanage.util.gen.entity;

import java.util.Date;

import com.goldcow.sframe.util.DataGridModel;
import com.goldcow.sframe.util.mybatis.BaseEntity;
/**
 * 零售产品批次实体类
 * 
 * @author cyx
 * @version v1.0
 * @since 2015-11-17
 */
public class RetailOrderBatch extends DataGridModel implements BaseEntity{
	private static final long serialVersionUID = 1L;
	/** 出库小号 */
	private Integer order_batch_id;			//零售产品批次主键
	/** 票号 */
	private String retail_order_ticketId;		//零售订单票号
	/** 序号 */
	private Integer order_productId;		//零售产品Id
	/** 小号 */
	private Integer order_batch_smallNumber;	//对应零售产品的自增序号
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
	private Double order_batch_quantity;
	/** 单价 */
	private Double order_batch_unitPrice;
	/** 减差价 */
	private Double order_batch_cutDifference;
	/** 金额 */
	private Double order_batch_amount;
	/** 毛利 */
	private Double order_batch_grossProfit;
	/** 退回数量 */
	private Double order_batch_refundQuantity;
	/** 日期 */
	private Date order_batch_date;
	/** 折让 */
	private Double order_batch_discountAmount;
	/** 经手人 */
	private String order_batch_brokerage;
	/** 经手人2 */
	private String order_batch_brokerage2;
	/** 促销品种 */
	private Integer order_batch_promotionVariety;
	/** 零售提成 */
	private Double order_batch_retailCommission;
	/** 会员不积分 */
	private Integer order_batch_mbernointegral;
	/** 单据分组号 */
	private Integer order_batch_documentGroupNum;
	/** 积分 */
	private Double order_batch_point;
	/** 积分倍数 */
	private Integer order_batch_pointMutiple;
	/** 记分金额 */
	private Integer order_batch_pointAmount;
	/** 原出库小号 */
	private Integer order_batch_originalDeliverySmallNum;
	/** 允许退货 */
	private Integer order_batch_allowReturn;
	/** 允许退货人 */
	private String order_batch_allowReturnPerson;
	/** 允许退货时间 */
	private Date order_batch_allowReturnTime;
	/** 允许退货原因 */
	private String order_batch_aloowReturnReason;
	/** 其它分类2 */
	private String order_batch_othercategory2;
	/** 销售序号 */
	private Integer order_batch_saleId;
	/** 销售类型 */
	private String order_batch_saleType;
	/** 会员 */
	private String order_batch_member;
	/** 门店售价 */
	private Double order_batch_storePrice;
	/** 满额赠金额 */
	private Double order_batch_fullGiftAmount;
	/** 兑换满额赠金额 */
	private Double order_batch_fullGiftExchange;
	/** 单品打折授权人 */
	private String order_batch_singleDiscountAuthoPerson;
	/** 单品打折原因 */
	private String order_batch_singleDiscountAuthoReason;
	/** 入库摘要 */
	private String order_batch_intakeBrief;
	/** 接收 */
	private Integer order_batch_receive;
	/** 已拆零 */
	private Integer order_batch_dismounting;
	
	public Integer getStock_info_id() {
		return stock_info_id;
	}
	public void setStock_info_id(Integer stock_info_id) {
		this.stock_info_id = stock_info_id;
	}
	public Integer getOrder_batch_id() {
		return order_batch_id;
	}
	public void setOrder_batch_id(Integer order_batch_id) {
		this.order_batch_id = order_batch_id;
	}

	public String getRetail_order_ticketId() {
		return retail_order_ticketId;
	}
	public void setRetail_order_ticketId(String retail_order_ticketId) {
		this.retail_order_ticketId = retail_order_ticketId;
	}
	public Integer getOrder_productId() {
		return order_productId;
	}
	public void setOrder_productId(Integer order_productId) {
		this.order_productId = order_productId;
	}
	public Integer getOrder_batch_smallNumber() {
		return order_batch_smallNumber;
	}
	public void setOrder_batch_smallNumber(Integer order_batch_smallNumber) {
		this.order_batch_smallNumber = order_batch_smallNumber;
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
	public Double getOrder_batch_quantity() {
		return order_batch_quantity;
	}
	public void setOrder_batch_quantity(Double order_batch_quantity) {
		this.order_batch_quantity = order_batch_quantity;
	}
	public Double getOrder_batch_unitPrice() {
		return order_batch_unitPrice;
	}
	public void setOrder_batch_unitPrice(Double order_batch_unitPrice) {
		this.order_batch_unitPrice = order_batch_unitPrice;
	}
	public Double getOrder_batch_cutDifference() {
		return order_batch_cutDifference;
	}
	public void setOrder_batch_cutDifference(Double order_batch_cutDifference) {
		this.order_batch_cutDifference = order_batch_cutDifference;
	}
	public Double getOrder_batch_amount() {
		return order_batch_amount;
	}
	public void setOrder_batch_amount(Double order_batch_amount) {
		this.order_batch_amount = order_batch_amount;
	}
	public Double getOrder_batch_grossProfit() {
		return order_batch_grossProfit;
	}
	public void setOrder_batch_grossProfit(Double order_batch_grossProfit) {
		this.order_batch_grossProfit = order_batch_grossProfit;
	}
	public Double getOrder_batch_refundQuantity() {
		return order_batch_refundQuantity;
	}
	public void setOrder_batch_refundQuantity(Double order_batch_refundQuantity) {
		this.order_batch_refundQuantity = order_batch_refundQuantity;
	}
	public Date getOrder_batch_date() {
		return order_batch_date;
	}
	public void setOrder_batch_date(Date order_batch_date) {
		this.order_batch_date = order_batch_date;
	}
	public Double getOrder_batch_discountAmount() {
		return order_batch_discountAmount;
	}
	public void setOrder_batch_discountAmount(Double order_batch_discountAmount) {
		this.order_batch_discountAmount = order_batch_discountAmount;
	}
	public String getOrder_batch_brokerage() {
		return order_batch_brokerage;
	}
	public void setOrder_batch_brokerage(String order_batch_brokerage) {
		this.order_batch_brokerage = order_batch_brokerage;
	}
	public String getOrder_batch_brokerage2() {
		return order_batch_brokerage2;
	}
	public void setOrder_batch_brokerage2(String order_batch_brokerage2) {
		this.order_batch_brokerage2 = order_batch_brokerage2;
	}
	public Integer getOrder_batch_promotionVariety() {
		return order_batch_promotionVariety;
	}
	public void setOrder_batch_promotionVariety(Integer order_batch_promotionVariety) {
		this.order_batch_promotionVariety = order_batch_promotionVariety;
	}
	public Double getOrder_batch_retailCommission() {
		return order_batch_retailCommission;
	}
	public void setOrder_batch_retailCommission(Double order_batch_retailCommission) {
		this.order_batch_retailCommission = order_batch_retailCommission;
	}
	public Integer getOrder_batch_mbernointegral() {
		return order_batch_mbernointegral;
	}
	public void setOrder_batch_mbernointegral(Integer order_batch_mbernointegral) {
		this.order_batch_mbernointegral = order_batch_mbernointegral;
	}
	public Integer getOrder_batch_documentGroupNum() {
		return order_batch_documentGroupNum;
	}
	public void setOrder_batch_documentGroupNum(Integer order_batch_documentGroupNum) {
		this.order_batch_documentGroupNum = order_batch_documentGroupNum;
	}
	public Double getOrder_batch_point() {
		return order_batch_point;
	}
	public void setOrder_batch_point(Double order_batch_point) {
		this.order_batch_point = order_batch_point;
	}
	public Integer getOrder_batch_pointMutiple() {
		return order_batch_pointMutiple;
	}
	public void setOrder_batch_pointMutiple(Integer order_batch_pointMutiple) {
		this.order_batch_pointMutiple = order_batch_pointMutiple;
	}
	public Integer getOrder_batch_pointAmount() {
		return order_batch_pointAmount;
	}
	public void setOrder_batch_pointAmount(Integer order_batch_pointAmount) {
		this.order_batch_pointAmount = order_batch_pointAmount;
	}
	public Integer getOrder_batch_originalDeliverySmallNum() {
		return order_batch_originalDeliverySmallNum;
	}
	public void setOrder_batch_originalDeliverySmallNum(
			Integer order_batch_originalDeliverySmallNum) {
		this.order_batch_originalDeliverySmallNum = order_batch_originalDeliverySmallNum;
	}
	public Integer getOrder_batch_allowReturn() {
		return order_batch_allowReturn;
	}
	public void setOrder_batch_allowReturn(Integer order_batch_allowReturn) {
		this.order_batch_allowReturn = order_batch_allowReturn;
	}
	public String getOrder_batch_allowReturnPerson() {
		return order_batch_allowReturnPerson;
	}
	public void setOrder_batch_allowReturnPerson(
			String order_batch_allowReturnPerson) {
		this.order_batch_allowReturnPerson = order_batch_allowReturnPerson;
	}
	public Date getOrder_batch_allowReturnTime() {
		return order_batch_allowReturnTime;
	}
	public void setOrder_batch_allowReturnTime(Date order_batch_allowReturnTime) {
		this.order_batch_allowReturnTime = order_batch_allowReturnTime;
	}
	public String getOrder_batch_aloowReturnReason() {
		return order_batch_aloowReturnReason;
	}
	public void setOrder_batch_aloowReturnReason(
			String order_batch_aloowReturnReason) {
		this.order_batch_aloowReturnReason = order_batch_aloowReturnReason;
	}
	public String getOrder_batch_othercategory2() {
		return order_batch_othercategory2;
	}
	public void setOrder_batch_othercategory2(String order_batch_othercategory2) {
		this.order_batch_othercategory2 = order_batch_othercategory2;
	}
	public Integer getOrder_batch_saleId() {
		return order_batch_saleId;
	}
	public void setOrder_batch_saleId(Integer order_batch_saleId) {
		this.order_batch_saleId = order_batch_saleId;
	}
	public String getOrder_batch_saleType() {
		return order_batch_saleType;
	}
	public void setOrder_batch_saleType(String order_batch_saleType) {
		this.order_batch_saleType = order_batch_saleType;
	}
	public String getOrder_batch_member() {
		return order_batch_member;
	}
	public void setOrder_batch_member(String order_batch_member) {
		this.order_batch_member = order_batch_member;
	}
	public Double getOrder_batch_storePrice() {
		return order_batch_storePrice;
	}
	public void setOrder_batch_storePrice(Double order_batch_storePrice) {
		this.order_batch_storePrice = order_batch_storePrice;
	}
	public Double getOrder_batch_fullGiftAmount() {
		return order_batch_fullGiftAmount;
	}
	public void setOrder_batch_fullGiftAmount(Double order_batch_fullGiftAmount) {
		this.order_batch_fullGiftAmount = order_batch_fullGiftAmount;
	}
	public Double getOrder_batch_fullGiftExchange() {
		return order_batch_fullGiftExchange;
	}
	public void setOrder_batch_fullGiftExchange(Double order_batch_fullGiftExchange) {
		this.order_batch_fullGiftExchange = order_batch_fullGiftExchange;
	}
	public String getOrder_batch_singleDiscountAuthoPerson() {
		return order_batch_singleDiscountAuthoPerson;
	}
	public void setOrder_batch_singleDiscountAuthoPerson(
			String order_batch_singleDiscountAuthoPerson) {
		this.order_batch_singleDiscountAuthoPerson = order_batch_singleDiscountAuthoPerson;
	}
	public String getOrder_batch_singleDiscountAuthoReason() {
		return order_batch_singleDiscountAuthoReason;
	}
	public void setOrder_batch_singleDiscountAuthoReason(
			String order_batch_singleDiscountAuthoReason) {
		this.order_batch_singleDiscountAuthoReason = order_batch_singleDiscountAuthoReason;
	}
	public String getOrder_batch_intakeBrief() {
		return order_batch_intakeBrief;
	}
	public void setOrder_batch_intakeBrief(String order_batch_intakeBrief) {
		this.order_batch_intakeBrief = order_batch_intakeBrief;
	}
	public Integer getOrder_batch_receive() {
		return order_batch_receive;
	}
	public void setOrder_batch_receive(Integer order_batch_receive) {
		this.order_batch_receive = order_batch_receive;
	}
	public Integer getOrder_batch_dismounting() {
		return order_batch_dismounting;
	}
	public void setOrder_batch_dismounting(Integer order_batch_dismounting) {
		this.order_batch_dismounting = order_batch_dismounting;
	}

	
}