package com.goldcow.emanage.util.gen.entity;

import java.util.Date;

import com.goldcow.sframe.util.DataGridModel;
import com.goldcow.sframe.util.mybatis.BaseEntity;
/**
 * 不合格品种信息实体类
 * 
 * @author gaoxiang
 * @version v1.0 
 * @since 2015-11-30
 * 
 * @version v2.0
 * @since 2015-12-11
 */
public class QltDefectsInfo extends DataGridModel implements BaseEntity{
	private static final long serialVersionUID = 1L;
	/** 不合格品种id */
	private Integer defects_id;
	/** 产品id */
	private Integer product_id;
	/** 库存id */
	private Integer stock_info_id;
	/** 库房id 调出库房 */
	private Integer department_id;
	/** 日期 */
	private Date defects_date;
	/** 不合格数量 */
	private Double defects_number;
	/** 存放地点 */
	private String defects_storagePlace;
	/** 处理意见 */
	private String defects_suggestion;
	/** 处理结果 */
	private String defects_result;
	/** 备注 */
	private String defects_remark;
	/** 操作员 */
	private String defects_person;
	/** 票号 */
	private String defects_ticketNo;
	/** 不合格原因 */
	private String defects_reason;
	/** 质量状况 */
	private String defects_quality;
	/** 审核 */
	private Integer defects_check;
	/** 审核人 */
	private String defects_checkPerson;
	/** 审核时间 */
	private Date defects_checkTime;
	/** 审核意见 */
	private String defects_checkSuggestion;
	
	/** 损益 */
	private Integer defects_profitLoss;
	/** 损益时间 */
	private Date defects_profitLossTime;
	/** 损益原因 */
	private String defects_profitReason;
	
	/** 销毁 */
	private Integer defects_destruction;
	/** 销毁时间 */
	private Date defects_destructionTime;
	/** 销毁原因 */
	private String defects_destructionReason;
	/** 销毁方式 */
	private String defects_destructionWay;
	/** 销毁地点 */
	private String defects_destructionPlace;
	/** 销毁日期 */
	private Date defects_destructionDate;
	/** 销毁后情况 */
	private String defects_destructionSituation;
	/** 运输工具 */
	private String defects_transMode;
	/** 运输人员 */
	private String defects_transPerson; 
	/** 销毁人 */
	private String defects_destructionPerson;
	/** 销毁执行人 */
	private String defects_destructionExecutor;
	/** 销毁监督人 */
	private String defects_destructionCustodian;
	
	/** 损益票号 */
	private String defects_profitTicketNo;
	/** 报损票号 */
	private String defects_breakageTicketNo;
	/** 销毁票号 */
	private String defects_destrucTicketNo;
	
	/** 报损申请 */
	private Integer defects_breakage;
	/** 报损申请人 */
	private String defects_breakagePerson;
	/** 报损申请时间 */
	private Date defects_breakageTime;
	/** 报损原因 */
	private String defects_breakageReason;
	
	/** 采购部审核 */
	private Integer defects_purchasedCheck;
	/** 采购部审核人 */
	private String defects_purchasedPerson;
	/** 采购部审核日期 */
	private Date defects_purchasedTime;
	/** 采购部意见 */
	private String defects_purchasedOpinion;
	
	/** 质管部审核 */
	private Integer defects_qualitycdCheck;
	/** 质管部审核人 */
	private String defects_qualitycdPerson;
	/** 质管部审核日期 */
	private Date defects_qualitycdTime;
	/** 质管部意见 */
	private String defects_qualitycdOpinion;
	
	/** 储运部审核 */
	private Integer defects_storagetdCheck;
	/** 储运部审核人 */
	private String defects_storagetdPerson;
	/** 储运部审核日期 */
	private Date defects_storagetdTime;
	/** 储运部意见 */
	private String defects_storagetdOpinion;
	
	/** 质量负责人审核 */
	private Integer defects_qaCheck;
	/** 质量负责人审核人 */
	private String defects_qaPerson;
	/** 质量负责人审核日期 */
	private Date defects_qaTime;
	/** 质量负责人意见 */
	private String defects_qaOpinion;
	
	/** 财务部审核 */
	private Integer defects_financialdCheck;
	/** 财务部审核人 */
	private String defects_financialdPerson;
	/** 财务部审核日期 */
	private Date defects_financialdTime;
	/** 财务部意见 */
	private String defects_financialdOpinion;
	
	/** 经理审核 */
	private Integer defects_managerCheck;
	/** 经理审核人 */
	private String defects_managerPerson;
	/** 经理审核日期 */
	private Date defects_managerTime;
	/** 经理意见 */
	private String defects_managerOpinion;
	
	/** 打印票号 */
	private String defects_printTicketNo;
	/** 打印 */
	private Integer defects_print;
	/** 打印时间 */
	private Date defects_printTime;
	/** 打印人 */
	private String defects_printPerson;
	/** 销毁打印 */
	private Integer defects_destructionPrint;
	/** 销毁打印人 */
	private String defects_destructionPrintPerson;
	/** 销毁打印时间 */
	private Date defects_destructionPrintTime;
	/** 销毁申请原因 */
	private String defects_destrucApplyReason;
	
	/** 创建人*/
	private Integer create_user;
	/** 创建时间 */
	private Date create_time;
	/** 最后修改人*/
	private Integer last_modify_user;
	/** 最后修改时间*/
	private Date last_modify_time;
	/** 状态 0启用 1停用 9删除 */
	private Integer status;
	/** 草稿 （判断此单是否存盘 0是已存 1是未存）*/
	private Integer defects_draft;	
	
	public Integer getDefects_id() {
		return defects_id;
	}
	public void setDefects_id(Integer defects_id) {
		this.defects_id = defects_id;
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
	public Integer getDepartment_id() {
		return department_id;
	}
	public void setDepartment_id(Integer department_id) {
		this.department_id = department_id;
	}
	public Date getDefects_date() {
		return defects_date;
	}
	public void setDefects_date(Date defects_date) {
		this.defects_date = defects_date;
	}
	public Double getDefects_number() {
		return defects_number;
	}
	public void setDefects_number(Double defects_number) {
		this.defects_number = defects_number;
	}
	public String getDefects_storagePlace() {
		return defects_storagePlace;
	}
	public void setDefects_storagePlace(String defects_storagePlace) {
		this.defects_storagePlace = defects_storagePlace;
	}
	public String getDefects_suggestion() {
		return defects_suggestion;
	}
	public void setDefects_suggestion(String defects_suggestion) {
		this.defects_suggestion = defects_suggestion;
	}
	public String getDefects_result() {
		return defects_result;
	}
	public void setDefects_result(String defects_result) {
		this.defects_result = defects_result;
	}
	public String getDefects_remark() {
		return defects_remark;
	}
	public void setDefects_remark(String defects_remark) {
		this.defects_remark = defects_remark;
	}
	public String getDefects_person() {
		return defects_person;
	}
	public void setDefects_person(String defects_person) {
		this.defects_person = defects_person;
	}
	public String getDefects_ticketNo() {
		return defects_ticketNo;
	}
	public void setDefects_ticketNo(String defects_ticketNo) {
		this.defects_ticketNo = defects_ticketNo;
	}
	public String getDefects_reason() {
		return defects_reason;
	}
	public void setDefects_reason(String defects_reason) {
		this.defects_reason = defects_reason;
	}
	public String getDefects_quality() {
		return defects_quality;
	}
	public void setDefects_quality(String defects_quality) {
		this.defects_quality = defects_quality;
	}
	public Integer getDefects_check() {
		return defects_check;
	}
	public void setDefects_check(Integer defects_check) {
		this.defects_check = defects_check;
	}
	public String getDefects_checkPerson() {
		return defects_checkPerson;
	}
	public void setDefects_checkPerson(String defects_checkPerson) {
		this.defects_checkPerson = defects_checkPerson;
	}
	public Date getDefects_checkTime() {
		return defects_checkTime;
	}
	public void setDefects_checkTime(Date defects_checkTime) {
		this.defects_checkTime = defects_checkTime;
	}
	public String getDefects_checkSuggestion() {
		return defects_checkSuggestion;
	}
	public void setDefects_checkSuggestion(String defects_checkSuggestion) {
		this.defects_checkSuggestion = defects_checkSuggestion;
	}
	public Integer getDefects_profitLoss() {
		return defects_profitLoss;
	}
	public void setDefects_profitLoss(Integer defects_profitLoss) {
		this.defects_profitLoss = defects_profitLoss;
	}
	public Date getDefects_profitLossTime() {
		return defects_profitLossTime;
	}
	public void setDefects_profitLossTime(Date defects_profitLossTime) {
		this.defects_profitLossTime = defects_profitLossTime;
	}
	public String getDefects_profitReason() {
		return defects_profitReason;
	}
	public void setDefects_profitReason(String defects_profitReason) {
		this.defects_profitReason = defects_profitReason;
	}
	public Integer getDefects_destruction() {
		return defects_destruction;
	}
	public void setDefects_destruction(Integer defects_destruction) {
		this.defects_destruction = defects_destruction;
	}
	public Date getDefects_destructionTime() {
		return defects_destructionTime;
	}
	public void setDefects_destructionTime(Date defects_destructionTime) {
		this.defects_destructionTime = defects_destructionTime;
	}
	public String getDefects_destructionReason() {
		return defects_destructionReason;
	}
	public void setDefects_destructionReason(String defects_destructionReason) {
		this.defects_destructionReason = defects_destructionReason;
	}
	public String getDefects_destructionWay() {
		return defects_destructionWay;
	}
	public void setDefects_destructionWay(String defects_destructionWay) {
		this.defects_destructionWay = defects_destructionWay;
	}
	public String getDefects_destructionPlace() {
		return defects_destructionPlace;
	}
	public void setDefects_destructionPlace(String defects_destructionPlace) {
		this.defects_destructionPlace = defects_destructionPlace;
	}
	public Date getDefects_destructionDate() {
		return defects_destructionDate;
	}
	public void setDefects_destructionDate(Date defects_destructionDate) {
		this.defects_destructionDate = defects_destructionDate;
	}
	public String getDefects_destructionSituation() {
		return defects_destructionSituation;
	}
	public void setDefects_destructionSituation(String defects_destructionSituation) {
		this.defects_destructionSituation = defects_destructionSituation;
	}
	public String getDefects_transMode() {
		return defects_transMode;
	}
	public void setDefects_transMode(String defects_transMode) {
		this.defects_transMode = defects_transMode;
	}
	public String getDefects_transPerson() {
		return defects_transPerson;
	}
	public void setDefects_transPerson(String defects_transPerson) {
		this.defects_transPerson = defects_transPerson;
	}
	public String getDefects_destructionPerson() {
		return defects_destructionPerson;
	}
	public void setDefects_destructionPerson(String defects_destructionPerson) {
		this.defects_destructionPerson = defects_destructionPerson;
	}
	public String getDefects_destructionExecutor() {
		return defects_destructionExecutor;
	}
	public void setDefects_destructionExecutor(String defects_destructionExecutor) {
		this.defects_destructionExecutor = defects_destructionExecutor;
	}
	public String getDefects_destructionCustodian() {
		return defects_destructionCustodian;
	}
	public void setDefects_destructionCustodian(String defects_destructionCustodian) {
		this.defects_destructionCustodian = defects_destructionCustodian;
	}
	public String getDefects_profitTicketNo() {
		return defects_profitTicketNo;
	}
	public void setDefects_profitTicketNo(String defects_profitTicketNo) {
		this.defects_profitTicketNo = defects_profitTicketNo;
	}
	public String getDefects_destrucTicketNo() {
		return defects_destrucTicketNo;
	}
	public void setDefects_destrucTicketNo(String defects_destrucTicketNo) {
		this.defects_destrucTicketNo = defects_destrucTicketNo;
	}
	public String getDefects_breakageTicketNo() {
		return defects_breakageTicketNo;
	}
	public void setDefects_breakageTicketNo(String defects_breakageTicketNo) {
		this.defects_breakageTicketNo = defects_breakageTicketNo;
	}
	public Integer getDefects_breakage() {
		return defects_breakage;
	}
	public void setDefects_breakage(Integer defects_breakage) {
		this.defects_breakage = defects_breakage;
	}
	public String getDefects_breakagePerson() {
		return defects_breakagePerson;
	}
	public void setDefects_breakagePerson(String defects_breakagePerson) {
		this.defects_breakagePerson = defects_breakagePerson;
	}
	public Date getDefects_breakageTime() {
		return defects_breakageTime;
	}
	public void setDefects_breakageTime(Date defects_breakageTime) {
		this.defects_breakageTime = defects_breakageTime;
	}
	public String getDefects_breakageReason() {
		return defects_breakageReason;
	}
	public void setDefects_breakageReason(String defects_breakageReason) {
		this.defects_breakageReason = defects_breakageReason;
	}
	public Integer getDefects_purchasedCheck() {
		return defects_purchasedCheck;
	}
	public void setDefects_purchasedCheck(Integer defects_purchasedCheck) {
		this.defects_purchasedCheck = defects_purchasedCheck;
	}
	public String getDefects_purchasedPerson() {
		return defects_purchasedPerson;
	}
	public void setDefects_purchasedPerson(String defects_purchasedPerson) {
		this.defects_purchasedPerson = defects_purchasedPerson;
	}
	public Date getDefects_purchasedTime() {
		return defects_purchasedTime;
	}
	public void setDefects_purchasedTime(Date defects_purchasedTime) {
		this.defects_purchasedTime = defects_purchasedTime;
	}
	public String getDefects_purchasedOpinion() {
		return defects_purchasedOpinion;
	}
	public void setDefects_purchasedOpinion(String defects_purchasedOpinion) {
		this.defects_purchasedOpinion = defects_purchasedOpinion;
	}
	public Integer getDefects_qualitycdCheck() {
		return defects_qualitycdCheck;
	}
	public void setDefects_qualitycdCheck(Integer defects_qualitycdCheck) {
		this.defects_qualitycdCheck = defects_qualitycdCheck;
	}
	public String getDefects_qualitycdPerson() {
		return defects_qualitycdPerson;
	}
	public void setDefects_qualitycdPerson(String defects_qualitycdPerson) {
		this.defects_qualitycdPerson = defects_qualitycdPerson;
	}
	public Date getDefects_qualitycdTime() {
		return defects_qualitycdTime;
	}
	public void setDefects_qualitycdTime(Date defects_qualitycdTime) {
		this.defects_qualitycdTime = defects_qualitycdTime;
	}
	public String getDefects_qualitycdOpinion() {
		return defects_qualitycdOpinion;
	}
	public void setDefects_qualitycdOpinion(String defects_qualitycdOpinion) {
		this.defects_qualitycdOpinion = defects_qualitycdOpinion;
	}
	public Integer getDefects_storagetdCheck() {
		return defects_storagetdCheck;
	}
	public void setDefects_storagetdCheck(Integer defects_storagetdCheck) {
		this.defects_storagetdCheck = defects_storagetdCheck;
	}
	public String getDefects_storagetdPerson() {
		return defects_storagetdPerson;
	}
	public void setDefects_storagetdPerson(String defects_storagetdPerson) {
		this.defects_storagetdPerson = defects_storagetdPerson;
	}
	public Date getDefects_storagetdTime() {
		return defects_storagetdTime;
	}
	public void setDefects_storagetdTime(Date defects_storagetdTime) {
		this.defects_storagetdTime = defects_storagetdTime;
	}
	public String getDefects_storagetdOpinion() {
		return defects_storagetdOpinion;
	}
	public void setDefects_storagetdOpinion(String defects_storagetdOpinion) {
		this.defects_storagetdOpinion = defects_storagetdOpinion;
	}
	public Integer getDefects_qaCheck() {
		return defects_qaCheck;
	}
	public void setDefects_qaCheck(Integer defects_qaCheck) {
		this.defects_qaCheck = defects_qaCheck;
	}
	public String getDefects_qaPerson() {
		return defects_qaPerson;
	}
	public void setDefects_qaPerson(String defects_qaPerson) {
		this.defects_qaPerson = defects_qaPerson;
	}
	public Date getDefects_qaTime() {
		return defects_qaTime;
	}
	public void setDefects_qaTime(Date defects_qaTime) {
		this.defects_qaTime = defects_qaTime;
	}
	public String getDefects_qaOpinion() {
		return defects_qaOpinion;
	}
	public void setDefects_qaOpinion(String defects_qaOpinion) {
		this.defects_qaOpinion = defects_qaOpinion;
	}
	public Integer getDefects_financialdCheck() {
		return defects_financialdCheck;
	}
	public void setDefects_financialdCheck(Integer defects_financialdCheck) {
		this.defects_financialdCheck = defects_financialdCheck;
	}
	public String getDefects_financialdPerson() {
		return defects_financialdPerson;
	}
	public void setDefects_financialdPerson(String defects_financialdPerson) {
		this.defects_financialdPerson = defects_financialdPerson;
	}
	public Date getDefects_financialdTime() {
		return defects_financialdTime;
	}
	public void setDefects_financialdTime(Date defects_financialdTime) {
		this.defects_financialdTime = defects_financialdTime;
	}
	public String getDefects_financialdOpinion() {
		return defects_financialdOpinion;
	}
	public void setDefects_financialdOpinion(String defects_financialdOpinion) {
		this.defects_financialdOpinion = defects_financialdOpinion;
	}
	public Integer getDefects_managerCheck() {
		return defects_managerCheck;
	}
	public void setDefects_managerCheck(Integer defects_managerCheck) {
		this.defects_managerCheck = defects_managerCheck;
	}
	public String getDefects_managerPerson() {
		return defects_managerPerson;
	}
	public void setDefects_managerPerson(String defects_managerPerson) {
		this.defects_managerPerson = defects_managerPerson;
	}
	public Date getDefects_managerTime() {
		return defects_managerTime;
	}
	public void setDefects_managerTime(Date defects_managerTime) {
		this.defects_managerTime = defects_managerTime;
	}
	public String getDefects_managerOpinion() {
		return defects_managerOpinion;
	}
	public void setDefects_managerOpinion(String defects_managerOpinion) {
		this.defects_managerOpinion = defects_managerOpinion;
	}
	public String getDefects_printTicketNo() {
		return defects_printTicketNo;
	}
	public void setDefects_printTicketNo(String defects_printTicketNo) {
		this.defects_printTicketNo = defects_printTicketNo;
	}
	public Integer getDefects_print() {
		return defects_print;
	}
	public void setDefects_print(Integer defects_print) {
		this.defects_print = defects_print;
	}
	public Date getDefects_printTime() {
		return defects_printTime;
	}
	public void setDefects_printTime(Date defects_printTime) {
		this.defects_printTime = defects_printTime;
	}
	public String getDefects_printPerson() {
		return defects_printPerson;
	}
	public void setDefects_printPerson(String defects_printPerson) {
		this.defects_printPerson = defects_printPerson;
	}
	public Integer getDefects_destructionPrint() {
		return defects_destructionPrint;
	}
	public void setDefects_destructionPrint(Integer defects_destructionPrint) {
		this.defects_destructionPrint = defects_destructionPrint;
	}
	public String getDefects_destructionPrintPerson() {
		return defects_destructionPrintPerson;
	}
	public void setDefects_destructionPrintPerson(
			String defects_destructionPrintPerson) {
		this.defects_destructionPrintPerson = defects_destructionPrintPerson;
	}
	public Date getDefects_destructionPrintTime() {
		return defects_destructionPrintTime;
	}
	public void setDefects_destructionPrintTime(Date defects_destructionPrintTime) {
		this.defects_destructionPrintTime = defects_destructionPrintTime;
	}
	public String getDefects_destrucApplyReason() {
		return defects_destrucApplyReason;
	}
	public void setDefects_destrucApplyReason(String defects_destrucApplyReason) {
		this.defects_destrucApplyReason = defects_destrucApplyReason;
	}
	public Integer getCreate_user() {
		return create_user;
	}
	public void setCreate_user(Integer create_user) {
		this.create_user = create_user;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Integer getLast_modify_user() {
		return last_modify_user;
	}
	public void setLast_modify_user(Integer last_modify_user) {
		this.last_modify_user = last_modify_user;
	}
	public Date getLast_modify_time() {
		return last_modify_time;
	}
	public void setLast_modify_time(Date last_modify_time) {
		this.last_modify_time = last_modify_time;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getDefects_draft() {
		return defects_draft;
	}
	public void setDefects_draft(Integer defects_draft) {
		this.defects_draft = defects_draft;
	}
}