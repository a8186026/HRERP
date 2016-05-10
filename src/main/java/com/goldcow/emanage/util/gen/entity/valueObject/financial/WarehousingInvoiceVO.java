package com.goldcow.emanage.util.gen.entity.valueObject.financial;

import java.util.Date;

import com.goldcow.emanage.util.gen.entity.FinancialInvoice;
/**
 * 入库购进发票
 * 
 * @author zhanxiaotong
 * @version v1.0
 * @since 2015-12-16
 */
public class WarehousingInvoiceVO  extends FinancialInvoice{

	private static final long serialVersionUID = 1L;
	/** 供方全称 */
	private String sup_name;
	/** 联系人 */
	private String sup_ctactperson;
	/** 电话 */
	private String sup_tel;
	/** 产品名称 */
	private String product_name;
	/** 规格 */
	private String product_specification;
	/** 单位 */
	private String product_unit;
	/** 订货金额  */
	private Double accept_sum;
	/** 质量验收合格数量  */
	private Double accept_checkQualitedNumber;
	/** 部门序号ID */
	private Integer department_id;
	/** 起始日期*/
	private Date begin_time;
	/** 结束日期*/
	private Date end_time;
	/** 入库票号 */
	private String stock_intakeTicket;
	/** 产品序号 */
	private Integer product_id;
	/** 入库小号 */
	private Integer stock_intakeSmallNumber;
	/** 序号 */
	private Integer refund_id;
	/** 票号 */
	private String refund_ticketId;
	/** 收货验收ID  */
	private Integer accept_check_id;
	/** 入库发票副序号ID  */
	private Integer rkfpb_id;
	/** 发票金额*/
	private Double rkfpb_invoicedMoney;
	/** 发票数量*/
	private Double rkfpb_invoicedNum;
	/** 退货原因 */
	private String refund_reason;
	/** 负责人 */
	private String refund_chiefPerson;
	/** 库房 */
	private Integer stock_storage;
	/** 批号 */
	private String stock_batchCode;
	/** 退货数量 */
	private Double refund_number;
	/** 退货金额 */
	private Double refund_money;
	/** 入库审核时间 */
	private Date stock_intakeCheckTime;
	/** 库房id */
	private Integer stock_info_id;
	/** 是否作废*/
	private Integer rkfpb_cancellation;
	
	
	
	public Integer getStock_info_id() {
		return stock_info_id;
	}
	public void setStock_info_id(Integer stock_info_id) {
		this.stock_info_id = stock_info_id;
	}
	public String getSup_name() {
		return sup_name;
	}
	public void setSup_name(String sup_name) {
		this.sup_name = sup_name;
	}
	public String getSup_ctactperson() {
		return sup_ctactperson;
	}
	public void setSup_ctactperson(String sup_ctactperson) {
		this.sup_ctactperson = sup_ctactperson;
	}
	public String getSup_tel() {
		return sup_tel;
	}
	public void setSup_tel(String sup_tel) {
		this.sup_tel = sup_tel;
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
	public String getProduct_unit() {
		return product_unit;
	}
	public void setProduct_unit(String product_unit) {
		this.product_unit = product_unit;
	}
	public Double getAccept_checkQualitedNumber() {
		return accept_checkQualitedNumber;
	}
	public void setAccept_checkQualitedNumber(Double accept_checkQualitedNumber) {
		this.accept_checkQualitedNumber = accept_checkQualitedNumber;
	}
	public Double getAccept_sum() {
		return accept_sum;
	}
	public void setAccept_sum(Double accept_sum) {
		this.accept_sum = accept_sum;
	}
	public Integer getDepartment_id() {
		return department_id;
	}
	public void setDepartment_id(Integer department_id) {
		this.department_id = department_id;
	}
	public Date getBegin_time() {
		return begin_time;
	}
	public void setBegin_time(Date begin_time) {
		this.begin_time = begin_time;
	}
	public Date getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}
	public Integer getProduct_id() {
		return product_id;
	}
	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
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
	public Integer getAccept_check_id() {
		return accept_check_id;
	}
	public void setAccept_check_id(Integer accept_check_id) {
		this.accept_check_id = accept_check_id;
	}
	public Integer getRkfpb_id() {
		return rkfpb_id;
	}
	public void setRkfpb_id(Integer rkfpb_id) {
		this.rkfpb_id = rkfpb_id;
	}
	public Double getRkfpb_invoicedMoney() {
		return rkfpb_invoicedMoney;
	}
	public void setRkfpb_invoicedMoney(Double rkfpb_invoicedMoney) {
		this.rkfpb_invoicedMoney = rkfpb_invoicedMoney;
	}
	public Double getRkfpb_invoicedNum() {
		return rkfpb_invoicedNum;
	}
	public void setRkfpb_invoicedNum(Double rkfpb_invoicedNum) {
		this.rkfpb_invoicedNum = rkfpb_invoicedNum;
	}
	public String getRefund_reason() {
		return refund_reason;
	}
	public void setRefund_reason(String refund_reason) {
		this.refund_reason = refund_reason;
	}
	public String getRefund_chiefPerson() {
		return refund_chiefPerson;
	}
	public void setRefund_chiefPerson(String refund_chiefPerson) {
		this.refund_chiefPerson = refund_chiefPerson;
	}
	public Integer getStock_storage() {
		return stock_storage;
	}
	public void setStock_storage(Integer stock_storage) {
		this.stock_storage = stock_storage;
	}
	public String getStock_batchCode() {
		return stock_batchCode;
	}
	public void setStock_batchCode(String stock_batchCode) {
		this.stock_batchCode = stock_batchCode;
	}
	public Double getRefund_money() {
		return refund_money;
	}
	public void setRefund_money(Double refund_money) {
		this.refund_money = refund_money;
	}
	public Double getRefund_number() {
		return refund_number;
	}
	public void setRefund_number(Double refund_number) {
		this.refund_number = refund_number;
	}
	public Date getStock_intakeCheckTime() {
		return stock_intakeCheckTime;
	}
	public void setStock_intakeCheckTime(Date stock_intakeCheckTime) {
		this.stock_intakeCheckTime = stock_intakeCheckTime;
	}
	public Integer getRkfpb_cancellation() {
		return rkfpb_cancellation;
	}
	public void setRkfpb_cancellation(Integer rkfpb_cancellation) {
		this.rkfpb_cancellation = rkfpb_cancellation;
	}

	
}
