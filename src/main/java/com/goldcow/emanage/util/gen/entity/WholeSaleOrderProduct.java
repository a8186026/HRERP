package com.goldcow.emanage.util.gen.entity;


import java.util.Date;

import com.goldcow.sframe.util.DataGridModel;
import com.goldcow.sframe.util.mybatis.BaseEntity;
/**
 * 批发产品实体类
 * 
 * @author cyx
 * @version v1.0
 * @since 2015-12-23
 */
public class WholeSaleOrderProduct extends DataGridModel implements BaseEntity{
	private static final long serialVersionUID = 1L;
	/** 批发产品ID */
	private Integer wholeSale_order_product_id;			//自增主键
	/** 票号 */
	private String wholeSale_order_ticketId;
	/** 序号 */
	private Integer wholeSale_productId;					//对应票号的自增序号
	/** 产品序号 */
	private Integer product_id;
	/** 产品编号 */
	private String product_code;
	/** 产品名称 */
	private String product_name;
	/** 规格 */
	private String product_specification;
	/** 产地 */
	private String product_productarea;
	/** 单位 */
	private String product_unit;
	/** 包装量 */
	private Integer product_packingamount;
	/** 剂型 */
	private String product_dosagetype;
	/** 分类 */
	private String product_category;
	/** 库存数量 */
	private Double product_stocknum;
	/** 批发价 */
	private Double product_tradeprice;
	/** 零售价 */
	private Double product_retailprice;
	/** 市场价 */
	private Double product_marketprice;
	/** 最低价 */
	private Double product_lowprice;
	/** 销售价1 */
	private Double product_saleprice1;
	/** 销售价2 */
	private Double product_saleprice2;
	/** 销售价3 */
	private Double product_saleprice3;
	/** 销售价4 */
	private Double product_saleprice4;
	/** 最后进价 */
	private Double product_lastprice;
	/** 数量 */
	private Double wholeSale_product_quantity;
	/** 单价 */
	private Double wholeSale_product_unitPrice;
	/** 减差价 */
	private Double wholeSale_product_cutDifference;
	/** 金额 */
	private Double wholeSale_product_amount;
	/** 毛利 */
	private Double wholeSale_product_grossProfit;
	/** 库房 */
	private Integer wholeSale_product_storage;
	/** 库房审核 */
	private Integer wholeSale_product_storageCheck;
	/** 库审时间 */
	private Date wholeSale_product_storageCheckTime;
	/** 审核人 */
	private String wholeSale_product_checkPerson;
	/** 赠品票号 */
	private String wholeSale_product_giftTicketId;
	/** 折让 */
	private Double wholeSale_product_discountAmount;
	/** 下浮 */
	private Double wholeSale_product_decrease;
	/** 返点额 */
	private Double wholeSale_product_debateAmount;
	/** 销售序号 */
	private Integer wholeSale_product_saleId;
	/** 销售类型 */
	private String wholeSale_product_saleType;
	/** 单付数量 */
	private Double wholeSale_product_singlePayQuantity;
	/** 接收 */
	private Integer wholeSale_product_receive;
	
	public Integer getWholeSale_order_product_id() {
		return wholeSale_order_product_id;
	}
	public void setWholeSale_order_product_id(Integer wholeSale_order_product_id) {
		this.wholeSale_order_product_id = wholeSale_order_product_id;
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
	public String getProduct_productarea() {
		return product_productarea;
	}
	public void setProduct_productarea(String product_productarea) {
		this.product_productarea = product_productarea;
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
	public Double getProduct_retailprice() {
		return product_retailprice;
	}
	public void setProduct_retailprice(Double product_retailprice) {
		this.product_retailprice = product_retailprice;
	}
	public Double getProduct_marketprice() {
		return product_marketprice;
	}
	public void setProduct_marketprice(Double product_marketprice) {
		this.product_marketprice = product_marketprice;
	}
	public Double getProduct_lowprice() {
		return product_lowprice;
	}
	public void setProduct_lowprice(Double product_lowprice) {
		this.product_lowprice = product_lowprice;
	}
	public Double getProduct_saleprice1() {
		return product_saleprice1;
	}
	public void setProduct_saleprice1(Double product_saleprice1) {
		this.product_saleprice1 = product_saleprice1;
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
	public Double getProduct_lastprice() {
		return product_lastprice;
	}
	public void setProduct_lastprice(Double product_lastprice) {
		this.product_lastprice = product_lastprice;
	}
	public Double getWholeSale_product_quantity() {
		return wholeSale_product_quantity;
	}
	public void setWholeSale_product_quantity(Double wholeSale_product_quantity) {
		this.wholeSale_product_quantity = wholeSale_product_quantity;
	}
	public Double getWholeSale_product_unitPrice() {
		return wholeSale_product_unitPrice;
	}
	public void setWholeSale_product_unitPrice(Double wholeSale_product_unitPrice) {
		this.wholeSale_product_unitPrice = wholeSale_product_unitPrice;
	}
	public Double getWholeSale_product_cutDifference() {
		return wholeSale_product_cutDifference;
	}
	public void setWholeSale_product_cutDifference(
			Double wholeSale_product_cutDifference) {
		this.wholeSale_product_cutDifference = wholeSale_product_cutDifference;
	}
	public Double getWholeSale_product_amount() {
		return wholeSale_product_amount;
	}
	public void setWholeSale_product_amount(Double wholeSale_product_amount) {
		this.wholeSale_product_amount = wholeSale_product_amount;
	}
	public Double getWholeSale_product_grossProfit() {
		return wholeSale_product_grossProfit;
	}
	public void setWholeSale_product_grossProfit(
			Double wholeSale_product_grossProfit) {
		this.wholeSale_product_grossProfit = wholeSale_product_grossProfit;
	}
	public Integer getWholeSale_product_storage() {
		return wholeSale_product_storage;
	}
	public void setWholeSale_product_storage(Integer wholeSale_product_storage) {
		this.wholeSale_product_storage = wholeSale_product_storage;
	}
	public Integer getWholeSale_product_storageCheck() {
		return wholeSale_product_storageCheck;
	}
	public void setWholeSale_product_storageCheck(
			Integer wholeSale_product_storageCheck) {
		this.wholeSale_product_storageCheck = wholeSale_product_storageCheck;
	}
	public Date getWholeSale_product_storageCheckTime() {
		return wholeSale_product_storageCheckTime;
	}
	public void setWholeSale_product_storageCheckTime(
			Date wholeSale_product_storageCheckTime) {
		this.wholeSale_product_storageCheckTime = wholeSale_product_storageCheckTime;
	}
	public String getWholeSale_product_checkPerson() {
		return wholeSale_product_checkPerson;
	}
	public void setWholeSale_product_checkPerson(
			String wholeSale_product_checkPerson) {
		this.wholeSale_product_checkPerson = wholeSale_product_checkPerson;
	}
	public String getWholeSale_product_giftTicketId() {
		return wholeSale_product_giftTicketId;
	}
	public void setWholeSale_product_giftTicketId(
			String wholeSale_product_giftTicketId) {
		this.wholeSale_product_giftTicketId = wholeSale_product_giftTicketId;
	}
	public Double getWholeSale_product_discountAmount() {
		return wholeSale_product_discountAmount;
	}
	public void setWholeSale_product_discountAmount(
			Double wholeSale_product_discountAmount) {
		this.wholeSale_product_discountAmount = wholeSale_product_discountAmount;
	}
	public Double getWholeSale_product_decrease() {
		return wholeSale_product_decrease;
	}
	public void setWholeSale_product_decrease(Double wholeSale_product_decrease) {
		this.wholeSale_product_decrease = wholeSale_product_decrease;
	}
	public Double getWholeSale_product_debateAmount() {
		return wholeSale_product_debateAmount;
	}
	public void setWholeSale_product_debateAmount(
			Double wholeSale_product_debateAmount) {
		this.wholeSale_product_debateAmount = wholeSale_product_debateAmount;
	}
	public Integer getWholeSale_product_saleId() {
		return wholeSale_product_saleId;
	}
	public void setWholeSale_product_saleId(Integer wholeSale_product_saleId) {
		this.wholeSale_product_saleId = wholeSale_product_saleId;
	}
	public String getWholeSale_product_saleType() {
		return wholeSale_product_saleType;
	}
	public void setWholeSale_product_saleType(String wholeSale_product_saleType) {
		this.wholeSale_product_saleType = wholeSale_product_saleType;
	}
	public Double getWholeSale_product_singlePayQuantity() {
		return wholeSale_product_singlePayQuantity;
	}
	public void setWholeSale_product_singlePayQuantity(
			Double wholeSale_product_singlePayQuantity) {
		this.wholeSale_product_singlePayQuantity = wholeSale_product_singlePayQuantity;
	}
	public Integer getWholeSale_product_receive() {
		return wholeSale_product_receive;
	}
	public void setWholeSale_product_receive(Integer wholeSale_product_receive) {
		this.wholeSale_product_receive = wholeSale_product_receive;
	}
	
	
}