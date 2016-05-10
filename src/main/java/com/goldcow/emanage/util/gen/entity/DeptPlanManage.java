package com.goldcow.emanage.util.gen.entity;

import java.util.Date;

import com.goldcow.sframe.util.DataGridModel;
import com.goldcow.sframe.util.mybatis.BaseEntity;
/**
 * 调货计划
 * 
 * @author 战晓桐
 * @version v1.0
 * @since 2015-10-30
 */
public class DeptPlanManage extends DataGridModel implements BaseEntity{
	private static final long serialVersionUID = 1L;
	/** 序号 */
	private Integer deptPlan_id;
	/** 票号 */
	private String deptPlan_ticket_id;
	/** 调出库房 */
	private String deptPlan_exportDept;
	/** 申请库房 */
	private String deptPlan_applyDept;
	/** 申请人 */
	private Integer deptPlan_applyPerson;
	/** 申请时间 */
	private Date deptPlan_applyTime;
	/** 产品序号 */
	private Integer deptPlan_pro_id;
	/** 库房数量 */
	private Integer deptPlan_deptNum;
	/** 申请数量 */
	private Double deptPlan_applyNum;
	/** 申请单价 */
	private Double deptPlan_applyPrice;
	/** 申请金额 */
	private Double deptPlan_applyMoney;
	/** 摘要 */
	private String deptPlan_summary;
	/** 日期 */
	private Date deptPlan_date;
	/** 配货状态 */
	private String deptPlan_allocationStatus;
	/** 配货方式 */
	private String deptPlan_allocationMethod;
	/** 配货序号 */
	private Integer deptPlan_allocation_id;
	/** 配货数量 */
	private Double deptPlan_allocationNum;
	/** 配货时间 */
	private Date deptPlan_allocationDate;
	/** 配货人 */
	private String deptPlan_allocationPerson;
	/** 配货说明 */
	private String deptPlan_allocationInstruction;
	/** 供方序号 */
	private Integer deptPlan_supply_id;
	/** 联系人 */
	private String deptPlan_contactPerson;
	/** 审核状态 */
	private String deptPlan_checkStatus;
	/** 审核人 */
	private Integer deptPlan_checkPerson;
	/** 审核时间 */
	private Date deptPlan_checkTime;
	/** 处理状态 */
	private String deptPlan_handleStatus;
	/** 处理人 */
	private String deptPlan_handlePerson;
	/** 处理时间 */
	private Date deptPlan_handleTime;
	/** 处理说明 */
	private String deptPlan_handleInstruction;
	/** 库房数量 */
	private Double deptPlan_storageNumber;
	
	public Double getDeptPlan_storageNumber() {
		return deptPlan_storageNumber;
	}
	public void setDeptPlan_storageNumber(Double deptPlan_storageNumber) {
		this.deptPlan_storageNumber = deptPlan_storageNumber;
	}
	public Integer getDeptPlan_id(){
		return this.deptPlan_id;
	}
	public void setDeptPlan_id(Integer deptPlan_id){
		this.deptPlan_id=deptPlan_id;
	}
	public String getDeptPlan_ticket_id(){
		return this.deptPlan_ticket_id;
	}
	public void setDeptPlan_ticket_id(String deptPlan_ticket_id){
		this.deptPlan_ticket_id=deptPlan_ticket_id;
	}
	public String getDeptPlan_exportDept(){
		return this.deptPlan_exportDept;
	}
	public void setDeptPlan_exportDept(String deptPlan_exportDept){
		this.deptPlan_exportDept=deptPlan_exportDept;
	}
	public String getDeptPlan_applyDept(){
		return this.deptPlan_applyDept;
	}
	public void setDeptPlan_applyDept(String deptPlan_applyDept){
		this.deptPlan_applyDept=deptPlan_applyDept;
	}
	public Integer getDeptPlan_applyPerson(){
		return this.deptPlan_applyPerson;
	}
	public void setDeptPlan_applyPerson(Integer deptPlan_applyPerson){
		this.deptPlan_applyPerson=deptPlan_applyPerson;
	}
	public Date getDeptPlan_applyTime(){
		return this.deptPlan_applyTime;
	}
	public void setDeptPlan_applyTime(Date deptPlan_applyTime){
		this.deptPlan_applyTime=deptPlan_applyTime;
	}
	public Integer getDeptPlan_pro_id(){
		return this.deptPlan_pro_id;
	}
	public void setDeptPlan_pro_id(Integer deptPlan_pro_id){
		this.deptPlan_pro_id=deptPlan_pro_id;
	}
	public Integer getDeptPlan_deptNum(){
		return this.deptPlan_deptNum;
	}
	public void setDeptPlan_deptNum(Integer deptPlan_deptNum){
		this.deptPlan_deptNum=deptPlan_deptNum;
	}
	public Double getDeptPlan_applyNum(){
		return this.deptPlan_applyNum;
	}
	public void setDeptPlan_applyNum(Double deptPlan_applyNum){
		this.deptPlan_applyNum=deptPlan_applyNum;
	}
	public Double getDeptPlan_applyPrice(){
		return this.deptPlan_applyPrice;
	}
	public void setDeptPlan_applyPrice(Double deptPlan_applyPrice){
		this.deptPlan_applyPrice=deptPlan_applyPrice;
	}
	public Double getDeptPlan_applyMoney(){
		return this.deptPlan_applyMoney;
	}
	public void setDeptPlan_applyMoney(Double money){
		this.deptPlan_applyMoney=money;
	}
	public String getDeptPlan_summary(){
		return this.deptPlan_summary;
	}
	public void setDeptPlan_summary(String deptPlan_summary){
		this.deptPlan_summary=deptPlan_summary;
	}
	public Date getDeptPlan_date(){
		return this.deptPlan_date;
	}
	public void setDeptPlan_date(Date deptPlan_date){
		this.deptPlan_date=deptPlan_date;
	}
	public String getDeptPlan_allocationStatus(){
		return this.deptPlan_allocationStatus;
	}
	public void setDeptPlan_allocationStatus(String deptPlan_allocationStatus){
		this.deptPlan_allocationStatus=deptPlan_allocationStatus;
	}
	public String getDeptPlan_allocationMethod(){
		return this.deptPlan_allocationMethod;
	}
	public void setDeptPlan_allocationMethod(String deptPlan_allocationMethod){
		this.deptPlan_allocationMethod=deptPlan_allocationMethod;
	}
	public Integer getDeptPlan_allocation_id(){
		return this.deptPlan_allocation_id;
	}
	public void setDeptPlan_allocation_id(Integer deptPlan_allocation_id){
		this.deptPlan_allocation_id=deptPlan_allocation_id;
	}
	public Double getDeptPlan_allocationNum(){
		return this.deptPlan_allocationNum;
	}
	public void setDeptPlan_allocationNum(Double deptPlan_allocationNum){
		this.deptPlan_allocationNum=deptPlan_allocationNum;
	}
	public Date getDeptPlan_allocationDate(){
		return this.deptPlan_allocationDate;
	}
	public void setDeptPlan_allocationDate(Date deptPlan_allocationDate){
		this.deptPlan_allocationDate=deptPlan_allocationDate;
	}
	public String getDeptPlan_allocationPerson(){
		return this.deptPlan_allocationPerson;
	}
	public void setDeptPlan_allocationPerson(String deptPlan_allocationPerson){
		this.deptPlan_allocationPerson=deptPlan_allocationPerson;
	}
	public String getDeptPlan_allocationInstruction(){
		return this.deptPlan_allocationInstruction;
	}
	public void setDeptPlan_allocationInstruction(String deptPlan_allocationInstruction){
		this.deptPlan_allocationInstruction=deptPlan_allocationInstruction;
	}
	public Integer getDeptPlan_supply_id(){
		return this.deptPlan_supply_id;
	}
	public void setDeptPlan_supply_id(Integer deptPlan_supply_id){
		this.deptPlan_supply_id=deptPlan_supply_id;
	}
	public String getDeptPlan_contactPerson(){
		return this.deptPlan_contactPerson;
	}
	public void setDeptPlan_contactPerson(String deptPlan_contactPerson){
		this.deptPlan_contactPerson=deptPlan_contactPerson;
	}
	public String getDeptPlan_checkStatus(){
		return this.deptPlan_checkStatus;
	}
	public void setDeptPlan_checkStatus(String deptPlan_checkStatus){
		this.deptPlan_checkStatus=deptPlan_checkStatus;
	}
	public Integer getDeptPlan_checkPerson(){
		return this.deptPlan_checkPerson;
	}
	public void setDeptPlan_checkPerson(Integer deptPlan_checkPerson){
		this.deptPlan_checkPerson=deptPlan_checkPerson;
	}
	public Date getDeptPlan_checkTime(){
		return this.deptPlan_checkTime;
	}
	public void setDeptPlan_checkTime(Date deptPlan_checkTime){
		this.deptPlan_checkTime=deptPlan_checkTime;
	}
	public String getDeptPlan_handleStatus(){
		return this.deptPlan_handleStatus;
	}
	public void setDeptPlan_handleStatus(String deptPlan_handleStatus){
		this.deptPlan_handleStatus=deptPlan_handleStatus;
	}
	public String getDeptPlan_handlePerson(){
		return this.deptPlan_handlePerson;
	}
	public void setDeptPlan_handlePerson(String deptPlan_handlePerson){
		this.deptPlan_handlePerson=deptPlan_handlePerson;
	}
	public Date getDeptPlan_handleTime(){
		return this.deptPlan_handleTime;
	}
	public void setDeptPlan_handleTime(Date deptPlan_handleTime){
		this.deptPlan_handleTime=deptPlan_handleTime;
	}
	public String getDeptPlan_handleInstruction(){
		return this.deptPlan_handleInstruction;
	}
	public void setDeptPlan_handleInstruction(String deptPlan_handleInstruction){
		this.deptPlan_handleInstruction=deptPlan_handleInstruction;
	}

}