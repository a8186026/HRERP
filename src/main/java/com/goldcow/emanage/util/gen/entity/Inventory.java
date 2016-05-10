package com.goldcow.emanage.util.gen.entity;

import java.util.Date;

import com.goldcow.sframe.util.DataGridModel;
import com.goldcow.sframe.util.mybatis.BaseEntity;

public class Inventory extends DataGridModel implements BaseEntity {
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Inventory [inventory_id=" + inventory_id + ", inventory_date="
				+ inventory_date + ", inventory_operator=" + inventory_operator
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
				+ ", inventory_check_status=" + inventory_check_status
				+ ", inventory_check_person=" + inventory_check_person
				+ ", inventory_check_time=" + inventory_check_time
				+ ", inventory_profitLossMoney=" + inventory_profitLossMoney
				+ ", inventory_apply_time=" + inventory_apply_time + "]";
	}
	
	
	

}
