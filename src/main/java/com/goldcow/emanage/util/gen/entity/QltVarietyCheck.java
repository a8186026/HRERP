package com.goldcow.emanage.util.gen.entity;

import java.util.Date;

import com.goldcow.sframe.util.DataGridModel;
import com.goldcow.sframe.util.mybatis.BaseEntity;
/**
 * 重点养护品种信息实体类
 * 
 * @author gaoxiang
 * @version v1.0
 * @since 2015-11-13
 */
public class QltVarietyCheck extends DataGridModel implements BaseEntity{
	private static final long serialVersionUID = 1L;
	/** 重点养护品种id */
	private Integer variety_checkId;
	/** 产品id */
	private Integer product_id;
	/** 确认时间  */
	private Date variety_checkTime;
	/** 确认人 */
	private String variety_checkPerson;
	/** 确认理由 */
	private String variety_checkReason;
	/** 养护重点 */
	private String variety_majorMaintain;
	/** 备注 */
	private String variety_remark; 
	/** 开始时间 */
	private Date variety_startTime;
	/** 结束时间 */
	private Date variety_endTime;
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
	public Integer getVariety_checkId() {
		return variety_checkId;
	}
	public void setVariety_checkId(Integer variety_checkId) {
		this.variety_checkId = variety_checkId;
	}
	public Integer getProduct_id() {
		return product_id;
	}
	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}
	public Date getVariety_checkTime() {
		return variety_checkTime;
	}
	public void setVariety_checkTime(Date variety_checkTime) {
		this.variety_checkTime = variety_checkTime;
	}
	public String getVariety_checkPerson() {
		return variety_checkPerson;
	}
	public void setVariety_checkPerson(String variety_checkPerson) {
		this.variety_checkPerson = variety_checkPerson;
	}
	public String getVariety_checkReason() {
		return variety_checkReason;
	}
	public void setVariety_checkReason(String variety_checkReason) {
		this.variety_checkReason = variety_checkReason;
	}
	public String getVariety_majorMaintain() {
		return variety_majorMaintain;
	}
	public void setVariety_majorMaintain(String variety_majorMaintain) {
		this.variety_majorMaintain = variety_majorMaintain;
	}
	public String getVariety_remark() {
		return variety_remark;
	}
	public void setVariety_remark(String variety_remark) {
		this.variety_remark = variety_remark;
	}
	public Date getVariety_startTime() {
		return variety_startTime;
	}
	public void setVariety_startTime(Date variety_startTime) {
		this.variety_startTime = variety_startTime;
	}
	public Date getVariety_endTime() {
		return variety_endTime;
	}
	public void setVariety_endTime(Date variety_endTime) {
		this.variety_endTime = variety_endTime;
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

	
}