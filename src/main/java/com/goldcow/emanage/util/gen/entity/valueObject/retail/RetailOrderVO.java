package com.goldcow.emanage.util.gen.entity.valueObject.retail;

import java.util.Date;

import com.goldcow.emanage.util.gen.entity.ProInfoManage;
import com.goldcow.sframe.util.DataGridModel;

public class RetailOrderVO extends DataGridModel implements Cloneable{
	private static final long serialVersionUID = 1L;
	/** 销售序号 */
	private Integer order_product_saleId;
	/** 销售类型 */
	private String order_product_saleType;
	/** 单价 */
	private Double order_product_unitPrice;
	/** 会员折后价 */
	private Double order_product_memUnitPrice;
	/** 能开价 */
	private Double accept_Amount;
	/** 可用数 */
	private Double accept_Number;
	/** 满额赠系数 */
	private Double fullGift_radio;
	/** 数量 */
	private Double number;
	
	/** 库房数量 */
	private Double stock_storageNumber;
	/** 占用数量 */
	private Double stock_occupyNumber;


	/** 入库小号 */
	private Integer stock_intakeSmallNumber;
	/** 是否会员日0表示是会员日，1表示不是会员日 */
	private Integer is_memberDay;
	/** 积分倍数 */
	private Integer order_batch_pointMutiple;
	/** 折扣可改 */
	private Integer discount_modify;
	/** 库房 */
	private Integer stock_storage;
	/** 库存id*/
	private Integer stock_info_id;
	/** 入库票号 */
	private String stock_intakeTicket;
	/** 失效期 */
	private Date stock_invalidDate;
	/** 批号 */
	private String stock_batchCode;
	/** 买赠特殊批次 */
	private Integer stock_buyPresentSpecial;
	
	/** 会员卡ID */
	private String mem_card_id;
	
	/** 折扣日价格 */
	private Double discountDayAmount;
	
	/** 手续1 */
	private Integer procedure1;
	/** 手续2 */
	private Integer procedure2;
	/** 手续3 */
	private Integer procedure3;

	
	
	/** 减差价(会员卡减少价格) */
	private Double memDescCost;
	
	/** 能开的折扣 */
	private Double deparment_acceptAmountDiscount;
	
	/** 折后价 */
	private Double discountedPrice;
	
	/** 积分 */
	private Integer point;
	
	
	//11.27新增
	//RetailOrderProduct表
	/** 金额 */
	private Double order_product_amount;
	/** 毛利 */
	private Double order_product_grossProfit;
	/** 折让 */
	private Double order_product_discountAmount;
	/** 票号 */
	private String retail_order_ticketId;
	/** 单付数量 */
	private Double order_product_singlePayQuantity;
	/** 零售产品ID */
	private Integer retail_order_product_id;
	/** 序号 */
	private Integer order_productId;
	
	
	
	//RetailOrderBatch表
	/** 进价 */
	private Double stock_purchasePrice;
	/** 结算价 */
	private Double stock_settlementPrice;
	/** 小号 */
	private Integer order_batch_smallNumber;
	/** 出库小号 */
	private Integer order_batch_id;	
	/** 打折授权人 */
	private String discountPerson;	
	/** 打折原因 */
	private String discountReason;	
	/** 入库摘要 */
	private String stock_intakeBrief;
	/** 满额赠金额 */
	private Double order_batch_fullGiftAmount;
	/** 兑换满额赠金额 */
	private Double order_batch_fullGiftExchange;
	
	
	//ProInfoManage表
	/** 产品序号 */
	private Integer product_id;
	/** 产品编号 */
	private String product_code;
	/** 产品条码 */
	private String product_barcode;
	/** 产品名称 */
	private String product_name;
	/** 通用名 */
	private String product_commonname;
	/** 拼音码 */
	private String product_chnpy;
	/** 规格 */
	private String product_specification;
	/** 单位 */
	private String product_unit;
	/** 包装量 */
	private Integer product_packingamount;
	/** 包装单位 */
	private String product_packingunit;
	/** 零售价 */
	private Double product_retailprice;
	/** 最后进价 */
	private Double product_lastprice;
	/** 价格格式 */
	private String product_amountFormat;
	/** 数量格式 */
	private String product_numberFormat;
	/** 记分金额 */
	private Double product_moneyPerPoint;
	/** 零售提成 */
	private Double product_retailcommission;
	/** 拆零组数 */
	private Integer product_tinynum;	
	/** 药监编号 */
	private String product_dsurveillanceno;	
	/** 药监id */
	private Integer product_dsurveillanceid;
	/** 销售价1 */
	private Double product_saleprice1;
	/** 医保品种 */
	private Integer product_medinsuvariety;
	/** 特殊品种 */
	private Integer product_specialvariety;
	/** 处方药 */
	private Integer product_prescriptiondrug;
	/** 促销品种 */
	private Integer product_promotionvariety;
	/** 会员不积分 */
	private Integer product_mbernointegral;
	/** 产地 */
	private String product_productarea;
	/** 剂型 */
	private String product_dosagetype;
	/** 分类 */
	private String product_category;
	/** 库存数量 */
	private Double product_stocknum;
	/** 批发价 */
	private Double product_tradeprice;
	/** 市场价 */
	private Double product_marketprice;
	/** 零售最低价 */
	private Double product_lretaillprice;
	/** 销售价2 */
	private Double product_saleprice2;
	/** 销售价3 */
	private Double product_saleprice3;
	/** 销售价4 */
	private Double product_saleprice4;
	/** 生产厂家 */
	private String product_factoryname;
	/** 批准文号 */
	private String product_approvalname;
	/** 批准文号 */
	private String product_approvalnum;
	/** 停售 */
	private Integer product_salestop;
	
	
	
	
	
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
	public Integer getProcedure1() {
		return procedure1;
	}
	public void setProcedure1(Integer procedure1) {
		this.procedure1 = procedure1;
	}
	public Integer getProcedure2() {
		return procedure2;
	}
	public void setProcedure2(Integer procedure2) {
		this.procedure2 = procedure2;
	}
	public Integer getProcedure3() {
		return procedure3;
	}
	public void setProcedure3(Integer procedure3) {
		this.procedure3 = procedure3;
	}
	public String getStock_intakeBrief() {
		return stock_intakeBrief;
	}

	public void setStock_intakeBrief(String stock_intakeBrief) {
		this.stock_intakeBrief = stock_intakeBrief;
	}

	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

	public Double getDiscountDayAmount() {
		return discountDayAmount;
	}

	public void setDiscountDayAmount(Double discountDayAmount) {
		this.discountDayAmount = discountDayAmount;
	}

	public String getMem_card_id() {
		return mem_card_id;
	}

	public void setMem_card_id(String mem_card_id) {
		this.mem_card_id = mem_card_id;
	}

	public Integer getProduct_salestop() {
		return product_salestop;
	}

	public void setProduct_salestop(Integer product_salestop) {
		this.product_salestop = product_salestop;
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

	public String getProduct_productarea() {
		return product_productarea;
	}

	public void setProduct_productarea(String product_productarea) {
		this.product_productarea = product_productarea;
	}

	public String getProduct_dosagetype() {
		return product_dosagetype;
	}

	public void setProduct_dosagetype(String product_dosagetype) {
		this.product_dosagetype = product_dosagetype;
	}

	public String getProduct_category() {
		return product_category;
	}

	public void setProduct_category(String product_category) {
		this.product_category = product_category;
	}

	public Double getProduct_stocknum() {
		return product_stocknum;
	}

	public void setProduct_stocknum(Double product_stocknum) {
		this.product_stocknum = product_stocknum;
	}

	public Double getProduct_tradeprice() {
		return product_tradeprice;
	}

	public void setProduct_tradeprice(Double product_tradeprice) {
		this.product_tradeprice = product_tradeprice;
	}

	public Double getProduct_marketprice() {
		return product_marketprice;
	}

	public void setProduct_marketprice(Double product_marketprice) {
		this.product_marketprice = product_marketprice;
	}

	public Double getProduct_lretaillprice() {
		return product_lretaillprice;
	}

	public void setProduct_lretaillprice(Double product_lretaillprice) {
		this.product_lretaillprice = product_lretaillprice;
	}

	public Double getProduct_saleprice2() {
		return product_saleprice2;
	}

	public void setProduct_saleprice2(Double product_saleprice2) {
		this.product_saleprice2 = product_saleprice2;
	}

	public Double getProduct_saleprice3() {
		return product_saleprice3;
	}

	public void setProduct_saleprice3(Double product_saleprice3) {
		this.product_saleprice3 = product_saleprice3;
	}

	public Double getProduct_saleprice4() {
		return product_saleprice4;
	}

	public void setProduct_saleprice4(Double product_saleprice4) {
		this.product_saleprice4 = product_saleprice4;
	}

	public Integer getProduct_promotionvariety() {
		return product_promotionvariety;
	}

	public void setProduct_promotionvariety(Integer product_promotionvariety) {
		this.product_promotionvariety = product_promotionvariety;
	}

	public Integer getProduct_mbernointegral() {
		return product_mbernointegral;
	}

	public void setProduct_mbernointegral(Integer product_mbernointegral) {
		this.product_mbernointegral = product_mbernointegral;
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

	public String getProduct_barcode() {
		return product_barcode;
	}

	public void setProduct_barcode(String product_barcode) {
		this.product_barcode = product_barcode;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getProduct_chnpy() {
		return product_chnpy;
	}

	public void setProduct_chnpy(String product_chnpy) {
		this.product_chnpy = product_chnpy;
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

	public Integer getProduct_packingamount() {
		return product_packingamount;
	}

	public void setProduct_packingamount(Integer product_packingamount) {
		this.product_packingamount = product_packingamount;
	}

	public String getProduct_packingunit() {
		return product_packingunit;
	}

	public void setProduct_packingunit(String product_packingunit) {
		this.product_packingunit = product_packingunit;
	}

	public Double getProduct_retailprice() {
		return product_retailprice;
	}

	public void setProduct_retailprice(Double product_retailprice) {
		this.product_retailprice = product_retailprice;
	}

	public Double getProduct_lastprice() {
		return product_lastprice;
	}

	public void setProduct_lastprice(Double product_lastprice) {
		this.product_lastprice = product_lastprice;
	}

	public String getProduct_amountFormat() {
		return product_amountFormat;
	}

	public void setProduct_amountFormat(String product_amountFormat) {
		this.product_amountFormat = product_amountFormat;
	}

	public String getProduct_numberFormat() {
		return product_numberFormat;
	}

	public void setProduct_numberFormat(String product_numberFormat) {
		this.product_numberFormat = product_numberFormat;
	}

	public Double getProduct_moneyPerPoint() {
		return product_moneyPerPoint;
	}

	public void setProduct_moneyPerPoint(Double product_moneyPerPoint) {
		this.product_moneyPerPoint = product_moneyPerPoint;
	}

	public Double getProduct_retailcommission() {
		return product_retailcommission;
	}

	public void setProduct_retailcommission(Double product_retailcommission) {
		this.product_retailcommission = product_retailcommission;
	}

	public Integer getProduct_tinynum() {
		return product_tinynum;
	}

	public void setProduct_tinynum(Integer product_tinynum) {
		this.product_tinynum = product_tinynum;
	}

	public String getProduct_dsurveillanceno() {
		return product_dsurveillanceno;
	}

	public void setProduct_dsurveillanceno(String product_dsurveillanceno) {
		this.product_dsurveillanceno = product_dsurveillanceno;
	}

	public Integer getProduct_dsurveillanceid() {
		return product_dsurveillanceid;
	}

	public void setProduct_dsurveillanceid(Integer product_dsurveillanceid) {
		this.product_dsurveillanceid = product_dsurveillanceid;
	}

	public Double getProduct_saleprice1() {
		return product_saleprice1;
	}

	public void setProduct_saleprice1(Double product_saleprice1) {
		this.product_saleprice1 = product_saleprice1;
	}

	public Integer getProduct_medinsuvariety() {
		return product_medinsuvariety;
	}

	public void setProduct_medinsuvariety(Integer product_medinsuvariety) {
		this.product_medinsuvariety = product_medinsuvariety;
	}

	public Integer getProduct_specialvariety() {
		return product_specialvariety;
	}

	public void setProduct_specialvariety(Integer product_specialvariety) {
		this.product_specialvariety = product_specialvariety;
	}

	public Integer getProduct_prescriptiondrug() {
		return product_prescriptiondrug;
	}

	public void setProduct_prescriptiondrug(Integer product_prescriptiondrug) {
		this.product_prescriptiondrug = product_prescriptiondrug;
	}

	@Override
	public RetailOrderVO clone() throws CloneNotSupportedException {
		// 实现clone方法
		return (RetailOrderVO)super.clone();
	}
	
	public Integer getOrder_batch_id() {
		return order_batch_id;
	}
	public void setOrder_batch_id(Integer order_batch_id) {
		this.order_batch_id = order_batch_id;
	}
	public Double getDiscountedPrice() {
		return discountedPrice;
	}
	public void setDiscountedPrice(Double discountedPrice) {
		this.discountedPrice = discountedPrice;
	}
	public Integer getOrder_productId() {
		return order_productId;
	}
	public void setOrder_productId(Integer order_productId) {
		this.order_productId = order_productId;
	}
	public String getDiscountPerson() {
		return discountPerson;
	}
	public void setDiscountPerson(String discountPerson) {
		this.discountPerson = discountPerson;
	}
	public String getDiscountReason() {
		return discountReason;
	}
	public void setDiscountReason(String discountReason) {
		this.discountReason = discountReason;
	}
	public Integer getOrder_batch_smallNumber() {
		return order_batch_smallNumber;
	}
	public void setOrder_batch_smallNumber(Integer order_batch_smallNumber) {
		this.order_batch_smallNumber = order_batch_smallNumber;
	}
	public Integer getRetail_order_product_id() {
		return retail_order_product_id;
	}
	public void setRetail_order_product_id(Integer retail_order_product_id) {
		this.retail_order_product_id = retail_order_product_id;
	}
	public Integer getOrder_product_saleId() {
		return order_product_saleId;
	}
	public void setOrder_product_saleId(Integer order_product_saleId) {
		this.order_product_saleId = order_product_saleId;
	}
	public Double getOrder_product_singlePayQuantity() {
		return order_product_singlePayQuantity;
	}
	public void setOrder_product_singlePayQuantity(
			Double order_product_singlePayQuantity) {
		this.order_product_singlePayQuantity = order_product_singlePayQuantity;
	}
	public Double getOrder_product_amount() {
		return order_product_amount;
	}
	public void setOrder_product_amount(Double order_product_amount) {
		this.order_product_amount = order_product_amount;
	}
	public Double getOrder_product_grossProfit() {
		return order_product_grossProfit;
	}
	public void setOrder_product_grossProfit(Double order_product_grossProfit) {
		this.order_product_grossProfit = order_product_grossProfit;
	}
	public Double getOrder_product_discountAmount() {
		return order_product_discountAmount;
	}
	public void setOrder_product_discountAmount(Double order_product_discountAmount) {
		this.order_product_discountAmount = order_product_discountAmount;
	}

	public String getRetail_order_ticketId() {
		return retail_order_ticketId;
	}
	public void setRetail_order_ticketId(String retail_order_ticketId) {
		this.retail_order_ticketId = retail_order_ticketId;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Double getOrder_product_memUnitPrice() {
		return order_product_memUnitPrice;
	}
	public void setOrder_product_memUnitPrice(Double order_product_memUnitPrice) {
		this.order_product_memUnitPrice = order_product_memUnitPrice;
	}
	public Double getDeparment_acceptAmountDiscount() {
		return deparment_acceptAmountDiscount;
	}
	public void setDeparment_acceptAmountDiscount(
			Double deparment_acceptAmountDiscount) {
		this.deparment_acceptAmountDiscount = deparment_acceptAmountDiscount;
	}
	public String getOrder_product_saleType() {
		return order_product_saleType;
	}
	public void setOrder_product_saleType(String order_product_saleType) {
		this.order_product_saleType = order_product_saleType;
	}
	public Double getOrder_product_unitPrice() {
		return order_product_unitPrice;
	}
	public void setOrder_product_unitPrice(Double order_product_unitPrice) {
		this.order_product_unitPrice = order_product_unitPrice;
	}
	public Double getAccept_Amount() {
		return accept_Amount;
	}
	public void setAccept_Amount(Double accept_Amount) {
		this.accept_Amount = accept_Amount;
	}
	public Double getAccept_Number() {
		return accept_Number;
	}
	public void setAccept_Number(Double accept_Number) {
		this.accept_Number = accept_Number;
	}
	public Double getStock_storageNumber() {
		return stock_storageNumber;
	}
	public void setStock_storageNumber(Double stock_storageNumber) {
		this.stock_storageNumber = stock_storageNumber;
	}
	public Double getFullGift_radio() {
		return fullGift_radio;
	}
	public void setFullGift_radio(Double fullGift_radio) {
		this.fullGift_radio = fullGift_radio;
	}
	public Integer getStock_intakeSmallNumber() {
		return stock_intakeSmallNumber;
	}
	public void setStock_intakeSmallNumber(Integer stock_intakeSmallNumber) {
		this.stock_intakeSmallNumber = stock_intakeSmallNumber;
	}

	public Integer getIs_memberDay() {
		return is_memberDay;
	}
	public void setIs_memberDay(Integer is_memberDay) {
		this.is_memberDay = is_memberDay;
	}
	public Integer getOrder_batch_pointMutiple() {
		return order_batch_pointMutiple;
	}
	public void setOrder_batch_pointMutiple(Integer order_batch_pointMutiple) {
		this.order_batch_pointMutiple = order_batch_pointMutiple;
	}
	public Double getStock_occupyNumber() {
		return stock_occupyNumber;
	}
	public void setStock_occupyNumber(Double stock_occupyNumber) {
		this.stock_occupyNumber = stock_occupyNumber;
	}

	public Double getNumber() {
		return number;
	}
	public void setNumber(Double number) {
		this.number = number;
	}
	public Integer getDiscount_modify() {
		return discount_modify;
	}
	public void setDiscount_modify(Integer discount_modify) {
		this.discount_modify = discount_modify;
	}
	public Integer getStock_storage() {
		return stock_storage;
	}
	public void setStock_storage(Integer stock_storage) {
		this.stock_storage = stock_storage;
	}
	public Integer getStock_info_id() {
		return stock_info_id;
	}
	public void setStock_info_id(Integer stock_info_id) {
		this.stock_info_id = stock_info_id;
	}
	public String getStock_intakeTicket() {
		return stock_intakeTicket;
	}
	public void setStock_intakeTicket(String stock_intakeTicket) {
		this.stock_intakeTicket = stock_intakeTicket;
	}
	public Date getStock_invalidDate() {
		return stock_invalidDate;
	}
	public void setStock_invalidDate(Date stock_invalidDate) {
		this.stock_invalidDate = stock_invalidDate;
	}
	public String getStock_batchCode() {
		return stock_batchCode;
	}
	public void setStock_batchCode(String stock_batchCode) {
		this.stock_batchCode = stock_batchCode;
	}
	public Double getMemDescCost() {
		return memDescCost;
	}
	public void setMemDescCost(Double memDescCost) {
		this.memDescCost = memDescCost;
	}
	public Integer getStock_buyPresentSpecial() {
		return stock_buyPresentSpecial;
	}
	public void setStock_buyPresentSpecial(Integer stock_buyPresentSpecial) {
		this.stock_buyPresentSpecial = stock_buyPresentSpecial;
	}
	public String getProduct_commonname() {
		return product_commonname;
	}
	public void setProduct_commonname(String product_commonname) {
		this.product_commonname = product_commonname;
	}
	public String getProduct_approvalname() {
		return product_approvalname;
	}
	public void setProduct_approvalname(String product_approvalname) {
		this.product_approvalname = product_approvalname;
	}
}

