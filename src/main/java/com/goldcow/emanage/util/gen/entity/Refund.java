package com.goldcow.emanage.util.gen.entity;
import java.util.Date;

import com.goldcow.sframe.util.DataGridModel;
import com.goldcow.sframe.util.mybatis.BaseEntity;

	/**
	*	*@author 
	*/

	public class Refund  extends DataGridModel implements BaseEntity{
		
	private static final long serialVersionUID = 1L;
	/** 序号 */
	private Integer refund_id;
	/** 票号 */
	private String refund_ticketId;
	/** 供方序号 */
	private Integer sup_id;
 
	/** 产品序号 */
	private Integer product_id;
	/**库存序号*/
	private Integer stock_info_id;
	/** 返货登记状态码 0-未完成 1-已完成*/
	private Integer refund_register;
	
	/** 登记时间 */
	private Date refund_registerDate;
	/** 负责人 */
	private String refund_chiefPerson;
	/** 退货原因 */
	private String refund_reason;
	/** 返货登记操作员 */
	private String refund_operator;
	/** 记帐方式 */
	private String refund_accountMethod;
	/** 记帐 */
	private Integer refund_account;
	/** 记帐票号 */
	private String refund_accountId;
	/** 记帐时间 */
	private Date refund_accountTime;
	/** 记帐人 */
	private String refund_accountPerson;
	/** 复核 */
	private Integer refund_review;
	/** 复核时间 */
	private Date refund_reviewTime;
	/** 复核人 */
	private String refund_reviewPerson;
	/** 退货数量 */
	private Double refund_number;
	/**退货单价*/
	private Double refund_unitPrice;
	/** 进价 */
	private Double stock_purchasePrice;
	/** 结算价 */
	private Double refund_settlePrice;
	/** 退货金额 */
	private Double refund_money;
	/** 出库审核 */
	private Integer refund_deliveryCheck;
	/** 出库时间 */
	private Date refund_deliveryTime;
	/** 保管员 */
	private String refund_custodian;
	/** 结帐 */
	private Integer refund_checkout;
	/** 结帐时间 */
	private Date refund_checkoutTime;
	/** 结帐人 */
	private String refund_checkoutPerson;
	/** 日期 */
	private Date refund_date;
	/** 发票号 */
	private String refund_invoiceNumber;
	/** 发票金额 */
	private Double refund_invoiceMoney;
	/** 无票金额 */
	private Double refund_noinvoiceMoney;
	/** 发票数量 */
	private Double refund_invoiceQuantity;
	/** 发票税率 */
	private Integer refund_invoiceTaxRate;
	/** 扣点 */
	private Double refund_discountPoint;
	/** 零售价 */
	private Double product_retailprice;
	/** 打印次数 */
	private Integer refund_printTimes;
	/** 接收 */
	private Integer refund_receive;
	/** 门店退货申请票号 */
	private String refund_storeApplyNumber;
	/** 付货审核 状态 0-审核完成  1-未开始审核*/
	private Integer refund_payCheck;
	/** 付货审核人 */
	private String refund_payCheckPerson;
	/** 付货审核时间 */
	private Date refund_payCheckTime;
	/** 状态码  0启用 1停用 9删除 */
	private Integer status;
	/**返货流程状态 1-返货登记完成  2-返货审核完成  0-返货付货完成 返货结束    */
	private Integer refund_status;
	/**取消返货状态码 */
	private Integer refund_cancel;
	/**取消返货操作员 */
	private String refund_cancelPerson;
	/**取消返货时间 */
	private Date refund_cancelDate;
	/**返货付货操作员 */
	private String refund_deliveryPerson;
	public Integer getRefund_id() {
		return refund_id;
	}
	public void setRefund_id(Integer refund_id) {
		this.refund_id = refund_id;
	}
	public String getRefund_ticketId() {
		return refund_ticketId;
	}
	public void setRefund_ticketId(String refund_ticketId) {
		this.refund_ticketId = refund_ticketId;
	}
	public Integer getSup_id() {
		return sup_id;
	}
	public void setSup_id(Integer sup_id) {
		this.sup_id = sup_id;
	}
	public Integer getProduct_id() {
		return product_id;
	}
	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}
	public Integer getStock_info_id() {
		return stock_info_id;
	}
	public void setStock_info_id(Integer stock_info_id) {
		this.stock_info_id = stock_info_id;
	}
	public Integer getRefund_register() {
		return refund_register;
	}
	public void setRefund_register(Integer refund_register) {
		this.refund_register = refund_register;
	}
	public Date getRefund_registerDate() {
		return refund_registerDate;
	}
	public void setRefund_registerDate(Date refund_registerDate) {
		this.refund_registerDate = refund_registerDate;
	}
	public String getRefund_chiefPerson() {
		return refund_chiefPerson;
	}
	public void setRefund_chiefPerson(String refund_chiefPerson) {
		this.refund_chiefPerson = refund_chiefPerson;
	}
	public String getRefund_reason() {
		return refund_reason;
	}
	public void setRefund_reason(String refund_reason) {
		this.refund_reason = refund_reason;
	}
	public String getRefund_operator() {
		return refund_operator;
	}
	public void setRefund_operator(String refund_operator) {
		this.refund_operator = refund_operator;
	}
	public String getRefund_accountMethod() {
		return refund_accountMethod;
	}
	public void setRefund_accountMethod(String refund_accountMethod) {
		this.refund_accountMethod = refund_accountMethod;
	}
	public Integer getRefund_account() {
		return refund_account;
	}
	public void setRefund_account(Integer refund_account) {
		this.refund_account = refund_account;
	}
	public String getRefund_accountId() {
		return refund_accountId;
	}
	public void setRefund_accountId(String refund_accountId) {
		this.refund_accountId = refund_accountId;
	}
	public Date getRefund_accountTime() {
		return refund_accountTime;
	}
	public void setRefund_accountTime(Date refund_accountTime) {
		this.refund_accountTime = refund_accountTime;
	}
	public String getRefund_accountPerson() {
		return refund_accountPerson;
	}
	public void setRefund_accountPerson(String refund_accountPerson) {
		this.refund_accountPerson = refund_accountPerson;
	}
	public Integer getRefund_review() {
		return refund_review;
	}
	public void setRefund_review(Integer refund_review) {
		this.refund_review = refund_review;
	}
	public Date getRefund_reviewTime() {
		return refund_reviewTime;
	}
	public void setRefund_reviewTime(Date refund_reviewTime) {
		this.refund_reviewTime = refund_reviewTime;
	}
	public String getRefund_reviewPerson() {
		return refund_reviewPerson;
	}
	public void setRefund_reviewPerson(String refund_reviewPerson) {
		this.refund_reviewPerson = refund_reviewPerson;
	}
	public Double getRefund_number() {
		return refund_number;
	}
	public void setRefund_number(Double refund_number) {
		this.refund_number = refund_number;
	}
	public Double getRefund_unitPrice() {
		return refund_unitPrice;
	}
	public void setRefund_unitPrice(Double refund_unitPrice) {
		this.refund_unitPrice = refund_unitPrice;
	}
	public Double getStock_purchasePrice() {
		return stock_purchasePrice;
	}
	public void setStock_purchasePrice(Double stock_purchasePrice) {
		this.stock_purchasePrice = stock_purchasePrice;
	}
	public Double getRefund_settlePrice() {
		return refund_settlePrice;
	}
	public void setRefund_settlePrice(Double refund_settlePrice) {
		this.refund_settlePrice = refund_settlePrice;
	}
	public Double getRefund_money() {
		return refund_money;
	}
	public void setRefund_money(Double refund_money) {
		this.refund_money = refund_money;
	}
	public Integer getRefund_deliveryCheck() {
		return refund_deliveryCheck;
	}
	public void setRefund_deliveryCheck(Integer refund_deliveryCheck) {
		this.refund_deliveryCheck = refund_deliveryCheck;
	}
	public Date getRefund_deliveryTime() {
		return refund_deliveryTime;
	}
	public void setRefund_deliveryTime(Date refund_deliveryTime) {
		this.refund_deliveryTime = refund_deliveryTime;
	}
	public String getRefund_custodian() {
		return refund_custodian;
	}
	public void setRefund_custodian(String refund_custodian) {
		this.refund_custodian = refund_custodian;
	}
	public Integer getRefund_checkout() {
		return refund_checkout;
	}
	public void setRefund_checkout(Integer refund_checkout) {
		this.refund_checkout = refund_checkout;
	}
	public Date getRefund_checkoutTime() {
		return refund_checkoutTime;
	}
	public void setRefund_checkoutTime(Date refund_checkoutTime) {
		this.refund_checkoutTime = refund_checkoutTime;
	}
	public String getRefund_checkoutPerson() {
		return refund_checkoutPerson;
	}
	public void setRefund_checkoutPerson(String refund_checkoutPerson) {
		this.refund_checkoutPerson = refund_checkoutPerson;
	}
	public Date getRefund_date() {
		return refund_date;
	}
	public void setRefund_date(Date refund_date) {
		this.refund_date = refund_date;
	}
	public String getRefund_invoiceNumber() {
		return refund_invoiceNumber;
	}
	public void setRefund_invoiceNumber(String refund_invoiceNumber) {
		this.refund_invoiceNumber = refund_invoiceNumber;
	}
	public Double getRefund_invoiceMoney() {
		return refund_invoiceMoney;
	}
	public void setRefund_invoiceMoney(Double refund_invoiceMoney) {
		this.refund_invoiceMoney = refund_invoiceMoney;
	}
	public Double getRefund_noinvoiceMoney() {
		return refund_noinvoiceMoney;
	}
	public void setRefund_noinvoiceMoney(Double refund_noinvoiceMoney) {
		this.refund_noinvoiceMoney = refund_noinvoiceMoney;
	}
	public Double getRefund_invoiceQuantity() {
		return refund_invoiceQuantity;
	}
	public void setRefund_invoiceQuantity(Double refund_invoiceQuantity) {
		this.refund_invoiceQuantity = refund_invoiceQuantity;
	}
	public Integer getRefund_invoiceTaxRate() {
		return refund_invoiceTaxRate;
	}
	public void setRefund_invoiceTaxRate(Integer refund_invoiceTaxRate) {
		this.refund_invoiceTaxRate = refund_invoiceTaxRate;
	}
	public Double getRefund_discountPoint() {
		return refund_discountPoint;
	}
	public void setRefund_discountPoint(Double refund_discountPoint) {
		this.refund_discountPoint = refund_discountPoint;
	}
	public Double getProduct_retailprice() {
		return product_retailprice;
	}
	public void setProduct_retailprice(Double product_retailprice) {
		this.product_retailprice = product_retailprice;
	}
	public Integer getRefund_printTimes() {
		return refund_printTimes;
	}
	public void setRefund_printTimes(Integer refund_printTimes) {
		this.refund_printTimes = refund_printTimes;
	}
	public Integer getRefund_receive() {
		return refund_receive;
	}
	public void setRefund_receive(Integer refund_receive) {
		this.refund_receive = refund_receive;
	}
	public String getRefund_storeApplyNumber() {
		return refund_storeApplyNumber;
	}
	public void setRefund_storeApplyNumber(String refund_storeApplyNumber) {
		this.refund_storeApplyNumber = refund_storeApplyNumber;
	}
	public Integer getRefund_payCheck() {
		return refund_payCheck;
	}
	public void setRefund_payCheck(Integer refund_payCheck) {
		this.refund_payCheck = refund_payCheck;
	}
	public String getRefund_payCheckPerson() {
		return refund_payCheckPerson;
	}
	public void setRefund_payCheckPerson(String refund_payCheckPerson) {
		this.refund_payCheckPerson = refund_payCheckPerson;
	}
	public Date getRefund_payCheckTime() {
		return refund_payCheckTime;
	}
	public void setRefund_payCheckTime(Date refund_payCheckTime) {
		this.refund_payCheckTime = refund_payCheckTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getRefund_status() {
		return refund_status;
	}
	public void setRefund_status(Integer refund_status) {
		this.refund_status = refund_status;
	}
	public Integer getRefund_cancel() {
		return refund_cancel;
	}
	public void setRefund_cancel(Integer refund_cancel) {
		this.refund_cancel = refund_cancel;
	}
	public String getRefund_cancelPerson() {
		return refund_cancelPerson;
	}
	public void setRefund_cancelPerson(String refund_cancelPerson) {
		this.refund_cancelPerson = refund_cancelPerson;
	}
	public Date getRefund_cancelDate() {
		return refund_cancelDate;
	}
	public void setRefund_cancelDate(Date refund_cancelDate) {
		this.refund_cancelDate = refund_cancelDate;
	}
	public String getRefund_deliveryPerson() {
		return refund_deliveryPerson;
	}
	public void setRefund_deliveryPerson(String refund_deliveryPerson) {
		this.refund_deliveryPerson = refund_deliveryPerson;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Refund [refund_id=" + refund_id + ", refund_ticketId="
				+ refund_ticketId + ", sup_id=" + sup_id + ", product_id="
				+ product_id + ", stock_info_id=" + stock_info_id
				+ ", refund_register=" + refund_register
				+ ", refund_registerDate=" + refund_registerDate
				+ ", refund_chiefPerson=" + refund_chiefPerson
				+ ", refund_reason=" + refund_reason + ", refund_operator="
				+ refund_operator + ", refund_accountMethod="
				+ refund_accountMethod + ", refund_account=" + refund_account
				+ ", refund_accountId=" + refund_accountId
				+ ", refund_accountTime=" + refund_accountTime
				+ ", refund_accountPerson=" + refund_accountPerson
				+ ", refund_review=" + refund_review + ", refund_reviewTime="
				+ refund_reviewTime + ", refund_reviewPerson="
				+ refund_reviewPerson + ", refund_number=" + refund_number
				+ ", refund_unitPrice=" + refund_unitPrice
				+ ", stock_purchasePrice=" + stock_purchasePrice
				+ ", refund_settlePrice=" + refund_settlePrice
				+ ", refund_money=" + refund_money + ", refund_deliveryCheck="
				+ refund_deliveryCheck + ", refund_deliveryTime="
				+ refund_deliveryTime + ", refund_custodian="
				+ refund_custodian + ", refund_checkout=" + refund_checkout
				+ ", refund_checkoutTime=" + refund_checkoutTime
				+ ", refund_checkoutPerson=" + refund_checkoutPerson
				+ ", refund_date=" + refund_date + ", refund_invoiceNumber="
				+ refund_invoiceNumber + ", refund_invoiceMoney="
				+ refund_invoiceMoney + ", refund_noinvoiceMoney="
				+ refund_noinvoiceMoney + ", refund_invoiceQuantity="
				+ refund_invoiceQuantity + ", refund_invoiceTaxRate="
				+ refund_invoiceTaxRate + ", refund_discountPoint="
				+ refund_discountPoint + ", product_retailprice="
				+ product_retailprice + ", refund_printTimes="
				+ refund_printTimes + ", refund_receive=" + refund_receive
				+ ", refund_storeApplyNumber=" + refund_storeApplyNumber
				+ ", refund_payCheck=" + refund_payCheck
				+ ", refund_payCheckPerson=" + refund_payCheckPerson
				+ ", refund_payCheckTime=" + refund_payCheckTime + ", status="
				+ status + ", refund_status=" + refund_status
				+ ", refund_cancel=" + refund_cancel + ", refund_cancelPerson="
				+ refund_cancelPerson + ", refund_cancelDate="
				+ refund_cancelDate + ", refund_deliveryPerson="
				+ refund_deliveryPerson + "]";
	}
	
	
	

}