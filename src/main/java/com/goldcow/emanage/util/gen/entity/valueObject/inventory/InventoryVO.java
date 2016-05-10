package com.goldcow.emanage.util.gen.entity.valueObject.inventory;

import java.util.Date;

import com.goldcow.sframe.util.DataGridModel;
import com.goldcow.sframe.util.mybatis.BaseEntity;

public class InventoryVO extends DataGridModel implements BaseEntity {
	private static final long serialVersionUID = 1L;
	/** 序号  */
	private Integer inventory_id;
	/** 日期  */
	private Date inventory_date;
	/** 操作员  */
	private String inventory_operator;
	/** 库房编号  */
	private Integer department_id;
	/** 组号  */
	private String pro_group_no;
	/** 货位号  */
	private String pro_location_no;
	/** 库存id  */
	private Integer stock_info_id;
	/** 盘点数量  */
	private Double inventory_countedNumber;
	/** 无记录  */
	private Integer inventory_noRecord;
	/** 损益序号  */
	private Integer inventory_profitLossId;
	/** 损益处理  */
	private Integer inventory_profitLossHandle;
	/** 损益数量  */
	private Double inventory_profitLossNumber;
	/** 损益原因  */
	private String inventory_reason;
	/** 处理人  */
	private String inventory_handlePerson;
	/** 处理时间  */
	private Date inventory_handleTime;
	/** 票号  */
	private String inventory_ticket_id;
	
	/** 库房名称  */
	private String department_fullname;
	/** 产品编号  */
	private String product_code;
	/** 产品序号  */
	private Integer product_id;
	/** 产品名称  */
	private String product_name;
	/** 批号选择  */
	/** 规格  */
	private String product_specification;
	/** 单位  */
	private Double product_unit;
	/** 产地  */
	private String product_productarea;
	/** 零售价  */
	private Double product_retailprice;
	/** 失效期  */
	private Date stock_invalidDate;
	/** 进价  */
	private Double stock_purchasePrice;
	/** 入库小号  */
	private String stock_intakeSmallNumber;
	/** 入库票号  */
	private String stock_intakeTicket;
	/** 供方  */
	private Integer sup_id;
	/** 库房数量  */
	private Double stock_storageNumber;
	/** 损益总额  */
	/** 批次批准文号  */
	private String product_approvalnum;
	/** 审核状态  */
	private Integer inventory_check_status;
	/** 审核人  */
	private Integer inventory_check_person;
	/** 审核时间  */
	private Date inventory_check_time;
	/** 损益金额  */
	private Double inventory_profitLossMoney;
	/** 申请时间  */
	private Date inventory_apply_time;
	/** 批号  */
	private String stock_batchCode;
	/** 包装单位  */
	private String product_packingunit;

 
	public String getProduct_packingunit() {
		return product_packingunit;
	}
	public void setProduct_packingunit(String product_packingunit) {
		this.product_packingunit = product_packingunit;
	}
	public Integer getInventory_id() {
		return inventory_id;
	}
	public void setInventory_id(Integer inventory_id) {
		this.inventory_id = inventory_id;
	}
	public Date getInventory_date() {
		return inventory_date;
	}
	public void setInventory_date(Date inventory_date) {
		this.inventory_date = inventory_date;
	}
	public String getInventory_operator() {
		return inventory_operator;
	}
	public void setInventory_operator(String inventory_operator) {
		this.inventory_operator = inventory_operator;
	}
	public Integer getDepartment_id() {
		return department_id;
	}
	public void setDepartment_id(Integer department_id) {
		this.department_id = department_id;
	}
	public String getPro_group_no() {
		return pro_group_no;
	}
	public void setPro_group_no(String pro_group_no) {
		this.pro_group_no = pro_group_no;
	}
	public String getPro_location_no() {
		return pro_location_no;
	}
	public void setPro_location_no(String pro_location_no) {
		this.pro_location_no = pro_location_no;
	}
	public Integer getStock_info_id() {
		return stock_info_id;
	}
	public void setStock_info_id(Integer stock_info_id) {
		this.stock_info_id = stock_info_id;
	}
	public Double getInventory_countedNumber() {
		return inventory_countedNumber;
	}
	public void setInventory_countedNumber(Double inventory_countedNumber) {
		this.inventory_countedNumber = inventory_countedNumber;
	}
	public Integer getInventory_noRecord() {
		return inventory_noRecord;
	}
	public void setInventory_noRecord(Integer inventory_noRecord) {
		this.inventory_noRecord = inventory_noRecord;
	}
	public Integer getInventory_profitLossId() {
		return inventory_profitLossId;
	}
	public void setInventory_profitLossId(Integer inventory_profitLossId) {
		this.inventory_profitLossId = inventory_profitLossId;
	}
	public Integer getInventory_profitLossHandle() {
		return inventory_profitLossHandle;
	}
	public void setInventory_profitLossHandle(Integer inventory_profitLossHandle) {
		this.inventory_profitLossHandle = inventory_profitLossHandle;
	}
	public Double getInventory_profitLossNumber() {
		return inventory_profitLossNumber;
	}
	public void setInventory_profitLossNumber(Double inventory_profitLossNumber) {
		this.inventory_profitLossNumber = inventory_profitLossNumber;
	}
	public String getInventory_reason() {
		return inventory_reason;
	}
	public void setInventory_reason(String inventory_reason) {
		this.inventory_reason = inventory_reason;
	}
	public String getInventory_handlePerson() {
		return inventory_handlePerson;
	}
	public void setInventory_handlePerson(String inventory_handlePerson) {
		this.inventory_handlePerson = inventory_handlePerson;
	}
	public Date getInventory_handleTime() {
		return inventory_handleTime;
	}
	public void setInventory_handleTime(Date inventory_handleTime) {
		this.inventory_handleTime = inventory_handleTime;
	}
	public String getInventory_ticket_id() {
		return inventory_ticket_id;
	}
	public void setInventory_ticket_id(String inventory_ticket_id) {
		this.inventory_ticket_id = inventory_ticket_id;
	}
	public String getDepartment_fullname() {
		return department_fullname;
	}
	public void setDepartment_fullname(String department_fullname) {
		this.department_fullname = department_fullname;
	}
	public String getProduct_code() {
		return product_code;
	}
	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}
	public Integer getProduct_id() {
		return product_id;
	}
	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
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
	public Double getProduct_unit() {
		return product_unit;
	}
	public void setProduct_unit(Double product_unit) {
		this.product_unit = product_unit;
	}
	public String getProduct_productarea() {
		return product_productarea;
	}
	public void setProduct_productarea(String product_productarea) {
		this.product_productarea = product_productarea;
	}
	public Double getProduct_retailprice() {
		return product_retailprice;
	}
	public void setProduct_retailprice(Double product_retailprice) {
		this.product_retailprice = product_retailprice;
	}
	public Date getStock_invalidDate() {
		return stock_invalidDate;
	}
	public void setStock_invalidDate(Date stock_invalidDate) {
		this.stock_invalidDate = stock_invalidDate;
	}
	public Double getStock_purchasePrice() {
		return stock_purchasePrice;
	}
	public void setStock_purchasePrice(Double stock_purchasePrice) {
		this.stock_purchasePrice = stock_purchasePrice;
	}
	public String getStock_intakeSmallNumber() {
		return stock_intakeSmallNumber;
	}
	public void setStock_intakeSmallNumber(String stock_intakeSmallNumber) {
		this.stock_intakeSmallNumber = stock_intakeSmallNumber;
	}
	public String getStock_intakeTicket() {
		return stock_intakeTicket;
	}
	public void setStock_intakeTicket(String stock_intakeTicket) {
		this.stock_intakeTicket = stock_intakeTicket;
	}
	public Integer getSup_id() {
		return sup_id;
	}
	public void setSup_id(Integer sup_id) {
		this.sup_id = sup_id;
	}
	public Double getStock_storageNumber() {
		return stock_storageNumber;
	}
	public void setStock_storageNumber(Double stock_storageNumber) {
		this.stock_storageNumber = stock_storageNumber;
	}
	public String getProduct_approvalnum() {
		return product_approvalnum;
	}
	public void setProduct_approvalnum(String product_approvalnum) {
		this.product_approvalnum = product_approvalnum;
	}
	public Integer getInventory_check_status() {
		return inventory_check_status;
	}
	public void setInventory_check_status(Integer inventory_check_status) {
		this.inventory_check_status = inventory_check_status;
	}
	public Integer getInventory_check_person() {
		return inventory_check_person;
	}
	public void setInventory_check_person(Integer inventory_check_person) {
		this.inventory_check_person = inventory_check_person;
	}
	public Date getInventory_check_time() {
		return inventory_check_time;
	}
	public void setInventory_check_time(Date inventory_check_time) {
		this.inventory_check_time = inventory_check_time;
	}
	public Double getInventory_profitLossMoney() {
		return inventory_profitLossMoney;
	}
	public void setInventory_profitLossMoney(Double inventory_profitLossMoney) {
		this.inventory_profitLossMoney = inventory_profitLossMoney;
	}
	public Date getInventory_apply_time() {
		return inventory_apply_time;
	}
	public void setInventory_apply_time(Date inventory_apply_time) {
		this.inventory_apply_time = inventory_apply_time;
	}
	public String getStock_batchCode() {
		return stock_batchCode;
	}
	public void setStock_batchCode(String stock_batchCode) {
		this.stock_batchCode = stock_batchCode;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "InventoryVO [inventory_id=" + inventory_id
				+ ", inventory_date=" + inventory_date
				+ ", inventory_operator=" + inventory_operator
				+ ", department_id=" + department_id + ", pro_group_no="
				+ pro_group_no + ", pro_location_no=" + pro_location_no
				+ ", stock_info_id=" + stock_info_id
				+ ", inventory_countedNumber=" + inventory_countedNumber
				+ ", inventory_noRecord=" + inventory_noRecord
				+ ", inventory_profitLossId=" + inventory_profitLossId
				+ ", inventory_profitLossHandle=" + inventory_profitLossHandle
				+ ", inventory_profitLossNumber=" + inventory_profitLossNumber
				+ ", inventory_reason=" + inventory_reason
				+ ", inventory_handlePerson=" + inventory_handlePerson
				+ ", inventory_handleTime=" + inventory_handleTime
				+ ", inventory_ticket_id=" + inventory_ticket_id
				+ ", department_fullname=" + department_fullname
				+ ", product_code=" + product_code + ", product_id="
				+ product_id + ", product_name=" + product_name
				+ ", product_specification=" + product_specification
				+ ", product_unit=" + product_unit + ", product_productarea="
				+ product_productarea + ", product_retailprice="
				+ product_retailprice + ", stock_invalidDate="
				+ stock_invalidDate + ", stock_purchasePrice="
				+ stock_purchasePrice + ", stock_intakeSmallNumber="
				+ stock_intakeSmallNumber + ", stock_intakeTicket="
				+ stock_intakeTicket + ", sup_id=" + sup_id
				+ ", stock_storageNumber=" + stock_storageNumber
				+ ", product_approvalnum=" + product_approvalnum
				+ ", inventory_check_status=" + inventory_check_status
				+ ", inventory_check_person=" + inventory_check_person
				+ ", inventory_check_time=" + inventory_check_time
				+ ", inventory_profitLossMoney=" + inventory_profitLossMoney
				+ ", inventory_apply_time=" + inventory_apply_time
				+ ", stock_batchCode=" + stock_batchCode + "]";
	}
	
	
	
	
}
