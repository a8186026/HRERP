package com.goldcow.emanage.util.gen.entity;

import java.util.Date;

import com.goldcow.sframe.util.DataGridModel;
import com.goldcow.sframe.util.mybatis.BaseEntity;
/**
 * 满额赠信息实体类
 * 
 * @author cyx
 * @version v1.0
 * @since 2015-11-4
 */
public class PmnFulfillGift extends DataGridModel implements BaseEntity{
	private static final long serialVersionUID = 1L;
	/** 序号 */
	private Integer full_gift_id;
	/** 产品序号 */
	private Integer product_id;
	/** 单价 */
	private Double full_gift_unitPrice;
	/** 数量 */
	private Double full_gift_amount;
	/** 兑换金额 */
	private Double full_gift_exchangeAmount;
	/** 兑换规则 */
	private Integer full_gift_exchangeRule;
	/** 开始日期 */
	private Date full_gift_startDate;
	/** 结束日期 */
	private Date full_gift_endDate;
	/** 产品积分 */
	private Integer full_gift_productPoint;
	/** 零售提成 */
	private Double full_gift_retailCommission;
	/** 参与部门 */
	private String full_gift_joinDepartment;
	/** 备注 */
	private String full_gift_remark;
	/** 只会员参加 */
	private Integer full_gift_onlyMemberJoin;
	/** 特殊批次 */
	private Integer full_gift_specialBatch;
	/** 参与产品 */
	private String full_gift_joinProduct;

	public String getFull_gift_joinProduct() {
		return full_gift_joinProduct;
	}
	public void setFull_gift_joinProduct(String full_gift_joinProduct) {
		this.full_gift_joinProduct = full_gift_joinProduct;
	}
	public Integer getFull_gift_id() {
		return full_gift_id;
	}
	public void setFull_gift_id(Integer full_gift_id) {
		this.full_gift_id = full_gift_id;
	}
	public Integer getProduct_id() {
		return product_id;
	}
	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}
	public Double getFull_gift_unitPrice() {
		return full_gift_unitPrice;
	}
	public void setFull_gift_unitPrice(Double full_gift_unitPrice) {
		this.full_gift_unitPrice = full_gift_unitPrice;
	}
	public Double getFull_gift_amount() {
		return full_gift_amount;
	}
	public void setFull_gift_amount(Double full_gift_amount) {
		this.full_gift_amount = full_gift_amount;
	}
	public Double getFull_gift_exchangeAmount() {
		return full_gift_exchangeAmount;
	}
	public void setFull_gift_exchangeAmount(Double full_gift_exchangeAmount) {
		this.full_gift_exchangeAmount = full_gift_exchangeAmount;
	}
	public Integer getFull_gift_exchangeRule() {
		return full_gift_exchangeRule;
	}
	public void setFull_gift_exchangeRule(Integer full_gift_exchangeRule) {
		this.full_gift_exchangeRule = full_gift_exchangeRule;
	}
	public Date getFull_gift_startDate() {
		return full_gift_startDate;
	}
	public void setFull_gift_startDate(Date full_gift_startDate) {
		this.full_gift_startDate = full_gift_startDate;
	}
	public Date getFull_gift_endDate() {
		return full_gift_endDate;
	}
	public void setFull_gift_endDate(Date full_gift_endDate) {
		this.full_gift_endDate = full_gift_endDate;
	}
	public Integer getFull_gift_productPoint() {
		return full_gift_productPoint;
	}
	public void setFull_gift_productPoint(Integer full_gift_productPoint) {
		this.full_gift_productPoint = full_gift_productPoint;
	}
	public Double getFull_gift_retailCommission() {
		return full_gift_retailCommission;
	}
	public void setFull_gift_retailCommission(Double full_gift_retailCommission) {
		this.full_gift_retailCommission = full_gift_retailCommission;
	}
	public String getFull_gift_joinDepartment() {
		return full_gift_joinDepartment;
	}
	public void setFull_gift_joinDepartment(String full_gift_joinDepartment) {
		this.full_gift_joinDepartment = full_gift_joinDepartment;
	}
	public String getFull_gift_remark() {
		return full_gift_remark;
	}
	public void setFull_gift_remark(String full_gift_remark) {
		this.full_gift_remark = full_gift_remark;
	}
	public Integer getFull_gift_onlyMemberJoin() {
		return full_gift_onlyMemberJoin;
	}
	public void setFull_gift_onlyMemberJoin(Integer full_gift_onlyMemberJoin) {
		this.full_gift_onlyMemberJoin = full_gift_onlyMemberJoin;
	}
	public Integer getFull_gift_specialBatch() {
		return full_gift_specialBatch;
	}
	public void setFull_gift_specialBatch(Integer full_gift_specialBatch) {
		this.full_gift_specialBatch = full_gift_specialBatch;
	}

}