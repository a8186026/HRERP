package com.goldcow.emanage.util.gen.entity;

import java.util.Date;

import com.goldcow.sframe.util.DataGridModel;
import com.goldcow.sframe.util.mybatis.BaseEntity;

/**
 * 收货审核实体类
 * 
 * @author wubin&cyx&gx
 * @version v1.0
 * @since 2015-9-24
 */

public class PurAcceptCheck extends DataGridModel implements BaseEntity {

	private static final long serialVersionUID = 1L;
	/** 收货验收ID  */
	private Integer accept_check_id;
	/** 订单ID  */
	private Integer pur_order_id;
	/** 小单ID  */
	private Integer pur_orderList_id;
	/** 温控方式 */
	private String accept_tempCtrlMode;
	/** 温控情况  */
	private String accept_tempCtrlSituation;
	/** 起运温度  */
	private Double accept_startTemp;
	/** 到货温度  */
	private Double accept_arrivalTemp;
	/** 环境温度  */
	private Double accept_ambientTemp;
	/** 运输方式  */
	private String accept_transMode;
	/** 运输单位  */
	private String accept_transCompany;
	/** 订货金额  */
	private Double accept_sum;
	/** 组号  */
	private String accept_groupNo;
	/** 到货时间  */
	private Date accept_acceptDate;
	/** 到货数量 */
	private Double accept_arrivalNumber;
	/** 收货数量  */
	private Double accept_acceptNumber;
	/** 收货人  */
	private String accept_acceptPerson;
	/** 拒收数量  */
	private Double accept_rejectNumber;
	/** 拒收理由  */
	private String accept_rejectReason;
	/** 拒收人  */
	private String accept_rejectCheckPerson;
	/** 拒收审核日期  */
	private Date accept_rejectCheckDate;
	/** 生产批号  */
	private String accept_batchCode;
	/** 库房id  */
	private Integer department_id;
	/** 质量验收合格数量  */
	private Double accept_checkQualitedNumber;
	/** 质量验收不合格数量  */
	private Double accept_checkUnqualitedNumber;
	/** 质量验收不合格理由  */
	private String accept_checkUnqualitedReason;
	/** 处理措施  */
	private String accept_treatMeasures;
	/** 质量验收人  */
	private String accept_checkQualityPerson;
	/** 质量验收日期  */
	private Date accept_checkQualityDate;
	/** 批号小号  */
	private String accept_batchSmallCode;
	/** 有效期至  */
	private Date accept_expirationDate;
	/** 生产日期  */
	private Date accept_productionDate;
	/** 质量状况 */
	private String accept_quantityCondition;
	/** 验收结论  */
	private String accept_checkConclusion;
	/** 抽样数量  */
	private Double accept_sampleNumber;
	/** 抽样件数  */
	private Double accept_sampleUnit;
	/** 灭菌批号  */
	private String accept_sterilizationbNum;
	/** 灭菌日期  */
	private Date accept_sterilizationbDate;
	/** 随货票号  */
	private String accept_goodNumber;
	/** 质量打印格式  */
	private String accept_quantityPrintType;
	/** 随货票样  */
	private String accept_goodsticket;
	/** 印章印模  */
	private String accept_sealmoldphoto;
	/** 特殊药品验收结论  */
	private String accept_specialCheckConclusion;
	/** 特殊药品质量状况  */
	private String accept_specialQuantityCondition;
	/** 入库票号  */
	private String accept_intakeTicket;
	/** 库房验收人  */
	private String accept_checkStockPerson;
	/** 库房验收时间  */
	private Date accept_checkStockTime;
	/** 收货状态0表示已收货1表示未收货2表示待审核  */
	private Integer accept_acceptStatus;
	/** 拒收状态0表示审核通过1表示审核不通过2表示未审核  */
	private Integer accept_rejectStatus;
	
	/** 质量验收状态0表示质检完成1表示未质检2表示待质检合格审核3表示待特殊药品审核  */
	private Integer accept_quantityStatus;
	/** 不合格审核状态0表示质检合格审核通过1表示质检合格审核不通过2表示质检合格未审核  */
	private Integer accept_quantityCheckStatus;
	
	/** 特殊药品审核状态0表示审核通过1表示审核不通过2表示特殊药品未审核  */
	private Integer accept_specialStatus;
	/** 库房验收状态1表示未入库0表示已入库  */
	private Integer accept_stockStatus;
	/** 起运地点  */
	private String accept_startPlace;
	/** 起运时间  */
	private Date accept_startTime;
	
	
	public Integer getAccept_check_id() {
		return accept_check_id;
	}
	public void setAccept_check_id(Integer accept_check_id) {
		this.accept_check_id = accept_check_id;
	}
	public Integer getPur_order_id() {
		return pur_order_id;
	}
	public void setPur_order_id(Integer pur_order_id) {
		this.pur_order_id = pur_order_id;
	}
	public Integer getPur_orderList_id() {
		return pur_orderList_id;
	}
	public void setPur_orderList_id(Integer pur_orderList_id) {
		this.pur_orderList_id = pur_orderList_id;
	}
	public String getAccept_tempCtrlMode() {
		return accept_tempCtrlMode;
	}
	public void setAccept_tempCtrlMode(String accept_tempCtrlMode) {
		this.accept_tempCtrlMode = accept_tempCtrlMode;
	}
	public String getAccept_tempCtrlSituation() {
		return accept_tempCtrlSituation;
	}
	public void setAccept_tempCtrlSituation(String accept_tempCtrlSituation) {
		this.accept_tempCtrlSituation = accept_tempCtrlSituation;
	}
	public Double getAccept_startTemp() {
		return accept_startTemp;
	}
	public void setAccept_startTemp(Double accept_startTemp) {
		this.accept_startTemp = accept_startTemp;
	}
	public Double getAccept_arrivalTemp() {
		return accept_arrivalTemp;
	}
	public void setAccept_arrivalTemp(Double accept_arrivalTemp) {
		this.accept_arrivalTemp = accept_arrivalTemp;
	}
	public Double getAccept_ambientTemp() {
		return accept_ambientTemp;
	}
	public void setAccept_ambientTemp(Double accept_ambientTemp) {
		this.accept_ambientTemp = accept_ambientTemp;
	}
	public String getAccept_transMode() {
		return accept_transMode;
	}
	public void setAccept_transMode(String accept_transMode) {
		this.accept_transMode = accept_transMode;
	}
	public String getAccept_transCompany() {
		return accept_transCompany;
	}
	public void setAccept_transCompany(String accept_transCompany) {
		this.accept_transCompany = accept_transCompany;
	}
	public Double getAccept_sum() {
		return accept_sum;
	}
	public void setAccept_sum(Double accept_sum) {
		this.accept_sum = accept_sum;
	}
	public String getAccept_groupNo() {
		return accept_groupNo;
	}
	public void setAccept_groupNo(String accept_groupNo) {
		this.accept_groupNo = accept_groupNo;
	}
	public Date getAccept_acceptDate() {
		return accept_acceptDate;
	}
	public void setAccept_acceptDate(Date accept_acceptDate) {
		this.accept_acceptDate = accept_acceptDate;
	}
	public Double getAccept_arrivalNumber() {
		return accept_arrivalNumber;
	}
	public void setAccept_arrivalNumber(Double accept_arrivalNumber) {
		this.accept_arrivalNumber = accept_arrivalNumber;
	}
	public Double getAccept_acceptNumber() {
		return accept_acceptNumber;
	}
	public void setAccept_acceptNumber(Double accept_acceptNumber) {
		this.accept_acceptNumber = accept_acceptNumber;
	}
	public String getAccept_acceptPerson() {
		return accept_acceptPerson;
	}
	public void setAccept_acceptPerson(String accept_acceptPerson) {
		this.accept_acceptPerson = accept_acceptPerson;
	}
	public Double getAccept_rejectNumber() {
		return accept_rejectNumber;
	}
	public void setAccept_rejectNumber(Double accept_rejectNumber) {
		this.accept_rejectNumber = accept_rejectNumber;
	}
	public String getAccept_rejectReason() {
		return accept_rejectReason;
	}
	public void setAccept_rejectReason(String accept_rejectReason) {
		this.accept_rejectReason = accept_rejectReason;
	}
	public String getAccept_rejectCheckPerson() {
		return accept_rejectCheckPerson;
	}
	public void setAccept_rejectCheckPerson(String accept_rejectCheckPerson) {
		this.accept_rejectCheckPerson = accept_rejectCheckPerson;
	}
	public Date getAccept_rejectCheckDate() {
		return accept_rejectCheckDate;
	}
	public void setAccept_rejectCheckDate(Date accept_rejectCheckDate) {
		this.accept_rejectCheckDate = accept_rejectCheckDate;
	}
	public String getAccept_batchCode() {
		return accept_batchCode;
	}
	public void setAccept_batchCode(String accept_batchCode) {
		this.accept_batchCode = accept_batchCode;
	}
	public Integer getDepartment_id() {
		return department_id;
	}
	public void setDepartment_id(Integer department_id) {
		this.department_id = department_id;
	}
	public Double getAccept_checkQualitedNumber() {
		return accept_checkQualitedNumber;
	}
	public void setAccept_checkQualitedNumber(Double accept_checkQualitedNumber) {
		this.accept_checkQualitedNumber = accept_checkQualitedNumber;
	}
	public Double getAccept_checkUnqualitedNumber() {
		return accept_checkUnqualitedNumber;
	}
	public void setAccept_checkUnqualitedNumber(Double accept_checkUnqualitedNumber) {
		this.accept_checkUnqualitedNumber = accept_checkUnqualitedNumber;
	}
	public String getAccept_checkUnqualitedReason() {
		return accept_checkUnqualitedReason;
	}
	public void setAccept_checkUnqualitedReason(String accept_checkUnqualitedReason) {
		this.accept_checkUnqualitedReason = accept_checkUnqualitedReason;
	}
	public String getAccept_treatMeasures() {
		return accept_treatMeasures;
	}
	public void setAccept_treatMeasures(String accept_treatMeasures) {
		this.accept_treatMeasures = accept_treatMeasures;
	}
	public String getAccept_checkQualityPerson() {
		return accept_checkQualityPerson;
	}
	public void setAccept_checkQualityPerson(String accept_checkQualityPerson) {
		this.accept_checkQualityPerson = accept_checkQualityPerson;
	}
	public Date getAccept_checkQualityDate() {
		return accept_checkQualityDate;
	}
	public void setAccept_checkQualityDate(Date accept_checkQualityDate) {
		this.accept_checkQualityDate = accept_checkQualityDate;
	}
	public String getAccept_batchSmallCode() {
		return accept_batchSmallCode;
	}
	public void setAccept_batchSmallCode(String accept_batchSmallCode) {
		this.accept_batchSmallCode = accept_batchSmallCode;
	}
	public Date getAccept_expirationDate() {
		return accept_expirationDate;
	}
	public void setAccept_expirationDate(Date accept_expirationDate) {
		this.accept_expirationDate = accept_expirationDate;
	}
	public Date getAccept_productionDate() {
		return accept_productionDate;
	}
	public void setAccept_productionDate(Date accept_productionDate) {
		this.accept_productionDate = accept_productionDate;
	}
	public String getAccept_quantityCondition() {
		return accept_quantityCondition;
	}
	public void setAccept_quantityCondition(String accept_quantityCondition) {
		this.accept_quantityCondition = accept_quantityCondition;
	}
	public String getAccept_checkConclusion() {
		return accept_checkConclusion;
	}
	public void setAccept_checkConclusion(String accept_checkConclusion) {
		this.accept_checkConclusion = accept_checkConclusion;
	}
	public Double getAccept_sampleNumber() {
		return accept_sampleNumber;
	}
	public void setAccept_sampleNumber(Double accept_sampleNumber) {
		this.accept_sampleNumber = accept_sampleNumber;
	}
	public Double getAccept_sampleUnit() {
		return accept_sampleUnit;
	}
	public void setAccept_sampleUnit(Double accept_sampleUnit) {
		this.accept_sampleUnit = accept_sampleUnit;
	}
	public String getAccept_sterilizationbNum() {
		return accept_sterilizationbNum;
	}
	public void setAccept_sterilizationbNum(String accept_sterilizationbNum) {
		this.accept_sterilizationbNum = accept_sterilizationbNum;
	}
	public Date getAccept_sterilizationbDate() {
		return accept_sterilizationbDate;
	}
	public void setAccept_sterilizationbDate(Date accept_sterilizationbDate) {
		this.accept_sterilizationbDate = accept_sterilizationbDate;
	}
	public String getAccept_goodNumber() {
		return accept_goodNumber;
	}
	public void setAccept_goodNumber(String accept_goodNumber) {
		this.accept_goodNumber = accept_goodNumber;
	}
	public String getAccept_quantityPrintType() {
		return accept_quantityPrintType;
	}
	public void setAccept_quantityPrintType(String accept_quantityPrintType) {
		this.accept_quantityPrintType = accept_quantityPrintType;
	}
	public String getAccept_goodsticket() {
		return accept_goodsticket;
	}
	public void setAccept_goodsticket(String accept_goodsticket) {
		this.accept_goodsticket = accept_goodsticket;
	}
	public String getAccept_sealmoldphoto() {
		return accept_sealmoldphoto;
	}
	public void setAccept_sealmoldphoto(String accept_sealmoldphoto) {
		this.accept_sealmoldphoto = accept_sealmoldphoto;
	}
	public String getAccept_specialCheckConclusion() {
		return accept_specialCheckConclusion;
	}
	public void setAccept_specialCheckConclusion(
			String accept_specialCheckConclusion) {
		this.accept_specialCheckConclusion = accept_specialCheckConclusion;
	}
	public String getAccept_specialQuantityCondition() {
		return accept_specialQuantityCondition;
	}
	public void setAccept_specialQuantityCondition(
			String accept_specialQuantityCondition) {
		this.accept_specialQuantityCondition = accept_specialQuantityCondition;
	}
	public String getAccept_intakeTicket() {
		return accept_intakeTicket;
	}
	public void setAccept_intakeTicket(String accept_intakeTicket) {
		this.accept_intakeTicket = accept_intakeTicket;
	}
	public String getAccept_checkStockPerson() {
		return accept_checkStockPerson;
	}
	public void setAccept_checkStockPerson(String accept_checkStockPerson) {
		this.accept_checkStockPerson = accept_checkStockPerson;
	}
	public Date getAccept_checkStockTime() {
		return accept_checkStockTime;
	}
	public void setAccept_checkStockTime(Date accept_checkStockTime) {
		this.accept_checkStockTime = accept_checkStockTime;
	}
	public Integer getAccept_acceptStatus() {
		return accept_acceptStatus;
	}
	public void setAccept_acceptStatus(Integer accept_acceptStatus) {
		this.accept_acceptStatus = accept_acceptStatus;
	}
	public Integer getAccept_rejectStatus() {
		return accept_rejectStatus;
	}
	public void setAccept_rejectStatus(Integer accept_rejectStatus) {
		this.accept_rejectStatus = accept_rejectStatus;
	}
	public Integer getAccept_quantityStatus() {
		return accept_quantityStatus;
	}
	public void setAccept_quantityStatus(Integer accept_quantityStatus) {
		this.accept_quantityStatus = accept_quantityStatus;
	}
	public Integer getAccept_quantityCheckStatus() {
		return accept_quantityCheckStatus;
	}
	public void setAccept_quantityCheckStatus(Integer accept_quantityCheckStatus) {
		this.accept_quantityCheckStatus = accept_quantityCheckStatus;
	}
	public Integer getAccept_specialStatus() {
		return accept_specialStatus;
	}
	public void setAccept_specialStatus(Integer accept_specialStatus) {
		this.accept_specialStatus = accept_specialStatus;
	}
	public Integer getAccept_stockStatus() {
		return accept_stockStatus;
	}
	public void setAccept_stockStatus(Integer accept_stockStatus) {
		this.accept_stockStatus = accept_stockStatus;
	}
	public String getAccept_startPlace() {
		return accept_startPlace;
	}
	public void setAccept_startPlace(String accept_startPlace) {
		this.accept_startPlace = accept_startPlace;
	}
	public Date getAccept_startTime() {
		return accept_startTime;
	}
	public void setAccept_startTime(Date accept_startTime) {
		this.accept_startTime = accept_startTime;
	}
	@Override
	public String toString() {
		return "PurAcceptCheck [accept_check_id=" + accept_check_id
				+ ", pur_order_id=" + pur_order_id + ", pur_orderList_id="
				+ pur_orderList_id + ", accept_tempCtrlMode="
				+ accept_tempCtrlMode + ", accept_tempCtrlSituation="
				+ accept_tempCtrlSituation + ", accept_startTemp="
				+ accept_startTemp + ", accept_arrivalTemp="
				+ accept_arrivalTemp + ", accept_ambientTemp="
				+ accept_ambientTemp + ", accept_transMode=" + accept_transMode
				+ ", accept_transCompany=" + accept_transCompany
				+ ", accept_sum=" + accept_sum + ", accept_groupNo="
				+ accept_groupNo + ", accept_acceptDate=" + accept_acceptDate
				+ ", accept_arrivalNumber=" + accept_arrivalNumber
				+ ", accept_acceptNumber=" + accept_acceptNumber
				+ ", accept_acceptPerson=" + accept_acceptPerson
				+ ", accept_rejectNumber=" + accept_rejectNumber
				+ ", accept_rejectReason=" + accept_rejectReason
				+ ", accept_rejectCheckPerson=" + accept_rejectCheckPerson
				+ ", accept_rejectCheckDate=" + accept_rejectCheckDate
				+ ", accept_batchCode=" + accept_batchCode + ", department_id="
				+ department_id + ", accept_checkQualitedNumber="
				+ accept_checkQualitedNumber
				+ ", accept_checkUnqualitedNumber="
				+ accept_checkUnqualitedNumber
				+ ", accept_checkUnqualitedReason="
				+ accept_checkUnqualitedReason + ", accept_treatMeasures="
				+ accept_treatMeasures + ", accept_checkQualityPerson="
				+ accept_checkQualityPerson + ", accept_checkQualityDate="
				+ accept_checkQualityDate + ", accept_batchSmallCode="
				+ accept_batchSmallCode + ", accept_expirationDate="
				+ accept_expirationDate + ", accept_productionDate="
				+ accept_productionDate + ", accept_quantityCondition="
				+ accept_quantityCondition + ", accept_checkConclusion="
				+ accept_checkConclusion + ", accept_sampleNumber="
				+ accept_sampleNumber + ", accept_sampleUnit="
				+ accept_sampleUnit + ", accept_sterilizationbNum="
				+ accept_sterilizationbNum + ", accept_sterilizationbDate="
				+ accept_sterilizationbDate + ", accept_goodNumber="
				+ accept_goodNumber + ", accept_quantityPrintType="
				+ accept_quantityPrintType + ", accept_goodsticket="
				+ accept_goodsticket + ", accept_sealmoldphoto="
				+ accept_sealmoldphoto + ", accept_specialCheckConclusion="
				+ accept_specialCheckConclusion
				+ ", accept_specialQuantityCondition="
				+ accept_specialQuantityCondition + ", accept_intakeTicket="
				+ accept_intakeTicket + ", accept_checkStockPerson="
				+ accept_checkStockPerson + ", accept_checkStockTime="
				+ accept_checkStockTime + ", accept_acceptStatus="
				+ accept_acceptStatus + ", accept_rejectStatus="
				+ accept_rejectStatus + ", accept_quantityStatus="
				+ accept_quantityStatus + ", accept_quantityCheckStatus="
				+ accept_quantityCheckStatus + ", accept_specialStatus="
				+ accept_specialStatus + ", accept_stockStatus="
				+ accept_stockStatus + ", accept_startPlace="
				+ accept_startPlace + ", accept_startTime=" + accept_startTime
				+ "]";
	}
	
}