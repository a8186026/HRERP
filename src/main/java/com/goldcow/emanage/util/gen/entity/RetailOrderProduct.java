package com.goldcow.emanage.util.gen.entity;


import com.goldcow.sframe.util.DataGridModel;
import com.goldcow.sframe.util.mybatis.BaseEntity;
/**
 * 零售产品实体类
 * 
 * @author cyx
 * @version v1.0
 * @since 2015-11-17
 */
public class RetailOrderProduct extends DataGridModel implements BaseEntity{
	private static final long serialVersionUID = 1L;
	/** 零售产品ID */
	private Integer retail_order_product_id;			//自增主键
	/** 票号 */
	private String retail_order_ticketId;
	/** 序号 */
	private Integer order_productId;					//对应票号的自增序号
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
	private Double order_product_quantity;
	/** 单价 */
	private Double order_product_unitPrice;
	/** 减差价 */
	private Double order_product_cutDifference;
	/** 金额 */
	private Double order_product_amount;
	/** 毛利 */
	private Double order_product_grossProfit;
	/** 库房 */
	private Integer order_product_storage;
	/** 折让 */
	private Double order_product_discountAmount;
	/** 销售序号 */
	private Integer order_product_saleId;
	/** 销售类型 */
	private String order_product_saleType;
	/** 单付数量 */
	private Double order_product_singlePayQuantity;
	/** 接收 */
	private Integer order_product_receive;
	
	public Integer getRetail_order_product_id() {
		return retail_order_product_id;
	}
	public void setRetail_order_product_id(Integer retail_order_product_id) {
		this.retail_order_product_id = retail_order_product_id;
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
	public Double getOrder_product_quantity() {
		return order_product_quantity;
	}
	public void setOrder_product_quantity(Double order_product_quantity) {
		this.order_product_quantity = order_product_quantity;
	}
	public Double getOrder_product_unitPrice() {
		return order_product_unitPrice;
	}
	public void setOrder_product_unitPrice(Double order_product_unitPrice) {
		this.order_product_unitPrice = order_product_unitPrice;
	}
	public Double getOrder_product_cutDifference() {
		return order_product_cutDifference;
	}
	public void setOrder_product_cutDifference(Double order_product_cutDifference) {
		this.order_product_cutDifference = order_product_cutDifference;
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
	public Integer getOrder_product_storage() {
		return order_product_storage;
	}
	public void setOrder_product_storage(Integer order_product_storage) {
		this.order_product_storage = order_product_storage;
	}
	public Double getOrder_product_discountAmount() {
		return order_product_discountAmount;
	}
	public void setOrder_product_discountAmount(Double order_product_discountAmount) {
		this.order_product_discountAmount = order_product_discountAmount;
	}
	public Integer getOrder_product_saleId() {
		return order_product_saleId;
	}
	public void setOrder_product_saleId(Integer order_product_saleId) {
		this.order_product_saleId = order_product_saleId;
	}
	public String getOrder_product_saleType() {
		return order_product_saleType;
	}
	public void setOrder_product_saleType(String order_product_saleType) {
		this.order_product_saleType = order_product_saleType;
	}
	public Double getOrder_product_singlePayQuantity() {
		return order_product_singlePayQuantity;
	}
	public void setOrder_product_singlePayQuantity(
			Double order_product_singlePayQuantity) {
		this.order_product_singlePayQuantity = order_product_singlePayQuantity;
	}
	public Integer getOrder_product_receive() {
		return order_product_receive;
	}
	public void setOrder_product_receive(Integer order_product_receive) {
		this.order_product_receive = order_product_receive;
	}
}