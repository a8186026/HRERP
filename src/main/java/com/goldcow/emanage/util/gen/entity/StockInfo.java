package com.goldcow.emanage.util.gen.entity;

import java.util.Date;

import com.goldcow.sframe.util.DataGridModel;
import com.goldcow.sframe.util.mybatis.BaseEntity;


/**
 * 库存信息实体类
 * 
 * @author chenyuxuan
 * @version v1.0
 * @since 2015-10-12
 */

public class StockInfo extends DataGridModel implements BaseEntity {


	private static final long serialVersionUID = 1L;
	/** 库存id */
	private Integer stock_info_id;
	/** 产品序号 */
	private Integer product_id;
	/** 入库小号 */
	private Integer stock_intakeSmallNumber;
	/** 库房 */
	private Integer stock_storage;
	/** 包装量 */
	private Double stock_packunit;
	/** 批号 */
	private String stock_batchCode;
	/** 进价 */
	private Double stock_purchasePrice;
	/** 库房数量 */
	private Double stock_storageNumber;
	/** 结算价 */
	private Double stock_settlementPrice;
	/** 销售价1 */
	private Double stock_salepPrice1;
	/** 最低价 */
	private Double stock_lowPrice;
	/** 批发价 */
	private Double stock_tradeprice;
	/** 市场价 */
	private Double stock_marketPrice;
	/** 失效期 */
	private Date stock_invalidDate;
	/** 养护时间 */
	private Date stock_maintaintime;
	/** 货位号 */
	private String stock_slottingNumber;
	/** 停售 */
	private Integer stock_saleStop;
	/** 入库摘要 */
	private String stock_intakeBrief;
	/** 入库票号 */
	private String stock_intakeTicket;
	/** 买赠特殊批次 */
	private Integer stock_buyPresentSpecial;
	/** 占用数量 */
	private Double stock_occupyNumber;
	/** 生产日期 */
	private Date stock_produceDate;
	/** 灭菌批号 */
	private String stock_sterilizationbnum;
	/** 灭菌日期 */
	private Date stock_sterilizationtime;
	/** 批号小号 */
	private String stock_batchSmallCode;
	/** 调价票号 */
	private String stock_priceCode;
	/** 调价时间 */
	private Date stock_priceTime;
	/** 原进价 */
	private Double stock_oldPrice;
	/** 入库审核人 */
	private String stock_intakeCheckPerson;
	/** 入库审核时间 */
	private Date stock_intakeCheckTime;
	/** 入库审核 */
	private Integer stock_intakeCheck;
	/** 重点养护 */
	private Integer stock_majorMaintain;
	/** 调拨价 */
	private Double stock_increasePrice;
	/** 零组数 */
	private Integer stock_zeroSetNumber;
	/** 供方序号 */
	private Integer sup_id;
	/** 批次库存 */
	private Double stock_batchAmount;
	/** 批号库存 */
	private Double stock_batchNumAmount;
	/** 品种库存 */
	private Double stock_varietyAmount;
	/** 品种库房库存 */
	private Double stock_varietyStorageAmount;
	/** 停售理由 */
	private String stock_stopReason;
	/** 停售人 */
	private String stock_stopPerson;
	/** 停售时间 */
	private Date stock_stopTime;
	
	/** 批次停售解锁理由 gaoxiang*/
	private String stock_stopClearReason;
	/** 批次停售解锁人 gaoxiang*/
	private String stock_stopClearPerson;
	/** 批次停售解锁时间 gaoxiang*/
	private Date stock_stopClearTime;
	
	/** 养护时间 gaoxiang*/
	private Integer maintain_days;
	
	public Integer getStock_info_id() {
		return stock_info_id;
	}
	public void setStock_info_id(Integer stock_info_id) {
		this.stock_info_id = stock_info_id;
	}
	public Integer getProduct_id(){
		return this.product_id;
	}
	public void setProduct_id(Integer product_id){
		this.product_id=product_id;
	}
	public Integer getStock_intakeSmallNumber(){
		return this.stock_intakeSmallNumber;
	}
	public void setStock_intakeSmallNumber(Integer stock_intakeSmallNumber){
		this.stock_intakeSmallNumber=stock_intakeSmallNumber;
	}
	public Integer getStock_storage() {
		return stock_storage;
	}
	public void setStock_storage(Integer stock_storage) {
		this.stock_storage = stock_storage;
	}
	public Double getStock_packunit() {
		return stock_packunit;
	}
	public void setStock_packunit(Double stock_packunit) {
		this.stock_packunit = stock_packunit;
	}
	public String getStock_batchCode(){
		return this.stock_batchCode;
	}
	public void setStock_batchCode(String stock_batchCode){
		this.stock_batchCode=stock_batchCode;
	}
	public Double getStock_purchasePrice() {
		return stock_purchasePrice;
	}
	public void setStock_purchasePrice(Double stock_purchasePrice) {
		this.stock_purchasePrice = stock_purchasePrice;
	}
	public Double getStock_storageNumber(){
		return this.stock_storageNumber;
	}
	public void setStock_storageNumber(Double stock_storageNumber){
		this.stock_storageNumber=stock_storageNumber;
	}
	public Double getStock_settlementPrice(){
		return this.stock_settlementPrice;
	}
	public void setStock_settlementPrice(Double stock_settlementPrice){
		this.stock_settlementPrice=stock_settlementPrice;
	}
	public Double getStock_salepPrice1(){
		return this.stock_salepPrice1;
	}
	public void setStock_salepPrice1(Double stock_salepPrice1){
		this.stock_salepPrice1=stock_salepPrice1;
	}
	public Double getStock_lowPrice(){
		return this.stock_lowPrice;
	}
	public void setStock_lowPrice(Double stock_lowPrice){
		this.stock_lowPrice=stock_lowPrice;
	}
	public Double getStock_marketPrice(){
		return this.stock_marketPrice;
	}
	public void setStock_marketPrice(Double stock_marketPrice){
		this.stock_marketPrice=stock_marketPrice;
	}
	public Date getStock_invalidDate(){
		return this.stock_invalidDate;
	}
	public void setStock_invalidDate(Date stock_invalidDate){
		this.stock_invalidDate=stock_invalidDate;
	}
	public Date getStock_maintaintime(){
		return this.stock_maintaintime;
	}
	public void setStock_maintaintime(Date stock_maintaintime){
		this.stock_maintaintime=stock_maintaintime;
	}
	public String getStock_slottingNumber(){
		return this.stock_slottingNumber;
	}
	public void setStock_slottingNumber(String stock_slottingNumber){
		this.stock_slottingNumber=stock_slottingNumber;
	}
	public Integer getStock_saleStop(){
		return this.stock_saleStop;
	}
	public void setStock_saleStop(Integer stock_saleStop){
		this.stock_saleStop=stock_saleStop;
	}
	public String getStock_intakeBrief(){
		return this.stock_intakeBrief;
	}
	public void setStock_intakeBrief(String stock_intakeBrief){
		this.stock_intakeBrief=stock_intakeBrief;
	}
	public String getStock_intakeTicket(){
		return this.stock_intakeTicket;
	}
	public void setStock_intakeTicket(String stock_intakeTicket){
		this.stock_intakeTicket=stock_intakeTicket;
	}
	public Integer getStock_buyPresentSpecial(){
		return this.stock_buyPresentSpecial;
	}
	public void setStock_buyPresentSpecial(Integer stock_buyPresentSpecial){
		this.stock_buyPresentSpecial=stock_buyPresentSpecial;
	}
	public Double getStock_occupyNumber(){
		return this.stock_occupyNumber;
	}
	public void setStock_occupyNumber(Double stock_occupyNumber){
		this.stock_occupyNumber=stock_occupyNumber;
	}
	public Date getStock_produceDate(){
		return this.stock_produceDate;
	}
	public void setStock_produceDate(Date stock_produceDate){
		this.stock_produceDate=stock_produceDate;
	}
	public String getStock_sterilizationbnum(){
		return this.stock_sterilizationbnum;
	}
	public void setStock_sterilizationbnum(String stock_sterilizationbnum){
		this.stock_sterilizationbnum=stock_sterilizationbnum;
	}
	public Date getStock_sterilizationtime(){
		return this.stock_sterilizationtime;
	}
	public void setStock_sterilizationtime(Date stock_sterilizationtime){
		this.stock_sterilizationtime=stock_sterilizationtime;
	}
	public String getStock_batchSmallCode(){
		return this.stock_batchSmallCode;
	}
	public void setStock_batchSmallCode(String stock_batchSmallCode){
		this.stock_batchSmallCode=stock_batchSmallCode;
	}
	public String getStock_priceCode(){
		return this.stock_priceCode;
	}
	public void setStock_priceCode(String stock_priceCode){
		this.stock_priceCode=stock_priceCode;
	}
	public Date getStock_priceTime(){
		return this.stock_priceTime;
	}
	public void setStock_priceTime(Date stock_priceTime){
		this.stock_priceTime=stock_priceTime;
	}
	public Double getStock_oldPrice(){
		return this.stock_oldPrice;
	}
	public void setStock_oldPrice(Double stock_oldPrice){
		this.stock_oldPrice=stock_oldPrice;
	}
	public String getStock_intakeCheckPerson(){
		return this.stock_intakeCheckPerson;
	}
	public void setStock_intakeCheckPerson(String stock_intakeCheckPerson){
		this.stock_intakeCheckPerson=stock_intakeCheckPerson;
	}
	public Date getStock_intakeCheckTime(){
		return this.stock_intakeCheckTime;
	}
	public void setStock_intakeCheckTime(Date stock_intakeCheckTime){
		this.stock_intakeCheckTime=stock_intakeCheckTime;
	}
	public Integer getStock_intakeCheck(){
		return this.stock_intakeCheck;
	}
	public void setStock_intakeCheck(Integer stock_intakeCheck){
		this.stock_intakeCheck=stock_intakeCheck;
	}
	public Integer getStock_majorMaintain(){
		return this.stock_majorMaintain;
	}
	public void setStock_majorMaintain(Integer stock_majorMaintain){
		this.stock_majorMaintain=stock_majorMaintain;
	}
	public Double getStock_increasePrice(){
		return this.stock_increasePrice;
	}
	public void setStock_increasePrice(Double stock_increasePrice){
		this.stock_increasePrice=stock_increasePrice;
	}
	public Integer getStock_zeroSetNumber(){
		return this.stock_zeroSetNumber;
	}
	public void setStock_zeroSetNumber(Integer stock_zeroSetNumber){
		this.stock_zeroSetNumber=stock_zeroSetNumber;
	}
	public Integer getSup_id(){
		return this.sup_id;
	}
	public void setSup_id(Integer sup_id){
		this.sup_id=sup_id;
	}
	public Double getStock_batchAmount() {
		return stock_batchAmount;
	}
	public void setStock_batchAmount(Double stock_batchAmount) {
		this.stock_batchAmount = stock_batchAmount;
	}
	public Double getStock_batchNumAmount() {
		return stock_batchNumAmount;
	}
	public void setStock_batchNumAmount(Double stock_batchNumAmount) {
		this.stock_batchNumAmount = stock_batchNumAmount;
	}
	public Double getStock_varietyAmount() {
		return stock_varietyAmount;
	}
	public void setStock_varietyAmount(Double stock_varietyAmount) {
		this.stock_varietyAmount = stock_varietyAmount;
	}
	public Double getStock_varietyStorageAmount() {
		return stock_varietyStorageAmount;
	}
	public void setStock_varietyStorageAmount(Double stock_varietyStorageAmount) {
		this.stock_varietyStorageAmount = stock_varietyStorageAmount;
	}
	public String getStock_stopReason() {
		return stock_stopReason;
	}
	public void setStock_stopReason(String stock_stopReason) {
		this.stock_stopReason = stock_stopReason;
	}
	public String getStock_stopPerson() {
		return stock_stopPerson;
	}
	public void setStock_stopPerson(String stock_stopPerson) {
		this.stock_stopPerson = stock_stopPerson;
	}
	public Date getStock_stopTime() {
		return stock_stopTime;
	}
	public void setStock_stopTime(Date stock_stopTime) {
		this.stock_stopTime = stock_stopTime;
	}
	public String getStock_stopClearReason() {
		return stock_stopClearReason;
	}
	public void setStock_stopClearReason(String stock_stopClearReason) {
		this.stock_stopClearReason = stock_stopClearReason;
	}
	public String getStock_stopClearPerson() {
		return stock_stopClearPerson;
	}
	public void setStock_stopClearPerson(String stock_stopClearPerson) {
		this.stock_stopClearPerson = stock_stopClearPerson;
	}
	public Date getStock_stopClearTime() {
		return stock_stopClearTime;
	}
	public void setStock_stopClearTime(Date stock_stopClearTime) {
		this.stock_stopClearTime = stock_stopClearTime;
	}
	public Integer getMaintain_days() {
		return maintain_days;
	}
	public void setMaintain_days(Integer maintain_days) {
		this.maintain_days = maintain_days;
	}
	
	@Override
	public String toString() {
		return "StockInfo [stock_info_id=" + stock_info_id + ", product_id="
				+ product_id + ", stock_intakeSmallNumber="
				+ stock_intakeSmallNumber + ", stock_storage=" + stock_storage
				+ ", stock_packunit=" + stock_packunit + ", stock_batchCode="
				+ stock_batchCode + ", stock_purchasePrice="
				+ stock_purchasePrice + ", stock_storageNumber="
				+ stock_storageNumber + ", stock_settlementPrice="
				+ stock_settlementPrice + ", stock_salepPrice1="
				+ stock_salepPrice1 + ", stock_lowPrice=" + stock_lowPrice
				+ ", stock_marketPrice=" + stock_marketPrice
				+ ", stock_invalidDate=" + stock_invalidDate
				+ ", stock_maintaintime=" + stock_maintaintime
				+ ", stock_slottingNumber=" + stock_slottingNumber
				+ ", stock_saleStop=" + stock_saleStop + ", stock_intakeBrief="
				+ stock_intakeBrief + ", stock_intakeTicket="
				+ stock_intakeTicket + ", stock_buyPresentSpecial="
				+ stock_buyPresentSpecial + ", stock_occupyNumber="
				+ stock_occupyNumber + ", stock_produceDate="
				+ stock_produceDate + ", stock_sterilizationbnum="
				+ stock_sterilizationbnum + ", stock_sterilizationtime="
				+ stock_sterilizationtime + ", stock_batchSmallCode="
				+ stock_batchSmallCode + ", stock_priceCode=" + stock_priceCode
				+ ", stock_priceTime=" + stock_priceTime + ", stock_oldPrice="
				+ stock_oldPrice + ", stock_intakeCheckPerson="
				+ stock_intakeCheckPerson + ", stock_intakeCheckTime="
				+ stock_intakeCheckTime + ", stock_intakeCheck="
				+ stock_intakeCheck + ", stock_majorMaintain="
				+ stock_majorMaintain + ", stock_increasePrice="
				+ stock_increasePrice + ", stock_zeroSetNumber="
				+ stock_zeroSetNumber + ", sup_id=" + sup_id
				+ ", stock_batchAmount=" + stock_batchAmount
				+ ", stock_batchNumAmount=" + stock_batchNumAmount
				+ ", stock_varietyAmount=" + stock_varietyAmount
				+ ", stock_varietyStorageAmount=" + stock_varietyStorageAmount
				+ ", stock_stopReason=" + stock_stopReason
				+ ", stock_stopPerson=" + stock_stopPerson
				+ ", stock_stopTime=" + stock_stopTime + "]";
	}
	public Double getStock_tradeprice() {
		return stock_tradeprice;
	}
	public void setStock_tradeprice(Double stock_tradeprice) {
		this.stock_tradeprice = stock_tradeprice;
	}
	
	
	
}