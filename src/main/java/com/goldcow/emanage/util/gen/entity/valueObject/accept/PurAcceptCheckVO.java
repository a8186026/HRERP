package com.goldcow.emanage.util.gen.entity.valueObject.accept;

import com.goldcow.emanage.util.gen.entity.PurAcceptCheck;

/**
 * 收货审核实体类
 * 
 * @author wubin
 * @version v1.1
 * @since 2015-9-25  2015-10-12
 */

	public class PurAcceptCheckVO extends PurAcceptCheck{

	private static final long serialVersionUID = 1L;
	/** 票号 */
	private String ticket_id;
	/** 数量 */
	private Integer quantity;
	/** 产品ID */
	private Integer product_id;
	/** 产品编号 */
	private String product_code;
	/** 单价*/
	private Double unitprice;
	/** 总金额 */
	private Double sum;
	/** 产品通用名 */
	private String product_commonname;
	/** 小单ID  */
	private Integer id;
	/** 供方ID */
	private Integer supply_id;
	/** 供方编号 */
	private String supply_code;
	/** 供方名称 */
	private String sup_name;
	
	/** 包装量 */
	private Integer product_packingamount;
	/** 结算价 */
	private Double settlementPrice;
	/** 包装单位 */
	private String product_packingunit;
	/** 销售价1 */
	private Double product_saleprice1;
	/** 零售最低价 */
	private Double product_lretaillprice;
	/** 市场价 */
	private Double product_marketprice;
	/** 停售 */
	private Integer product_salestop;
	/** 买赠特殊批次 */
	private Integer product_giftspecialbatch;
	
	
	
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
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
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
	public Double getUnitprice() {
		return unitprice;
	}
	public void setUnitprice(Double unitprice) {
		this.unitprice = unitprice;
	}
	public Double getSum() {
		return sum;
	}
	public void setSum(Double sum) {
		this.sum = sum;
	}
	public String getProduct_commonname() {
		return product_commonname;
	}
	public void setProduct_commonname(String product_commonname) {
		this.product_commonname = product_commonname;
	}
	public Integer getSupply_id() {
		return supply_id;
	}
	public void setSupply_id(Integer supply_id) {
		this.supply_id = supply_id;
	}
	public String getSupply_code() {
		return supply_code;
	}
	public void setSupply_code(String supply_code) {
		this.supply_code = supply_code;
	}
	public String getSup_name() {
		return sup_name;
	}
	public void setSup_name(String sup_name) {
		this.sup_name = sup_name;
	}
	public Integer getProduct_packingamount() {
		return product_packingamount;
	}
	public void setProduct_packingamount(Integer product_packingamount) {
		this.product_packingamount = product_packingamount;
	}
	public Double getSettlementPrice() {
		return settlementPrice;
	}
	public void setSettlementPrice(Double settlementPrice) {
		this.settlementPrice = settlementPrice;
	}
	public String getProduct_packingunit() {
		return product_packingunit;
	}
	public void setProduct_packingunit(String product_packingunit) {
		this.product_packingunit = product_packingunit;
	}
	public Double getProduct_saleprice1() {
		return product_saleprice1;
	}
	public void setProduct_saleprice1(Double product_saleprice1) {
		this.product_saleprice1 = product_saleprice1;
	}
	public Double getProduct_lretaillprice() {
		return product_lretaillprice;
	}
	public void setProduct_lretaillprice(Double product_lretaillprice) {
		this.product_lretaillprice = product_lretaillprice;
	}
	public Double getProduct_marketprice() {
		return product_marketprice;
	}
	public void setProduct_marketprice(Double product_marketprice) {
		this.product_marketprice = product_marketprice;
	}
	public Integer getProduct_salestop() {
		return product_salestop;
	}
	public void setProduct_salestop(Integer product_salestop) {
		this.product_salestop = product_salestop;
	}
	public Integer getProduct_giftspecialbatch() {
		return product_giftspecialbatch;
	}
	public void setProduct_giftspecialbatch(Integer product_giftspecialbatch) {
		this.product_giftspecialbatch = product_giftspecialbatch;
	}
	
}