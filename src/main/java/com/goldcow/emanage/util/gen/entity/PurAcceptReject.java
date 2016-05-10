package com.goldcow.emanage.util.gen.entity;

import java.util.Date;

import com.goldcow.sframe.util.DataGridModel;
import com.goldcow.sframe.util.mybatis.BaseEntity;

public class PurAcceptReject  extends DataGridModel implements BaseEntity{
	/**
	 * 收货拒收处理
	 * 
	 * @author zhanxiaotong
	 * @since15-11-27
	 * @version v1.0
	 */
	/** 序号ID */
	private Integer reject_id;
	/** 产品序号  */
	private Integer product_id;
	/** 供方序号  */
	private Integer sup_id;
	/** 批号  */
	private String reject_batchnum;
	/** 有效期  */
	private Date reject_validtime;
	/** 生产日期 */
	private Date reject_productdate;
	/** 数量  */
	private Double reject_num;
	/** 单价  */
	private Double reject_price;
	/** 添加人  */
	private Integer reject_person;
	/** 添加时间  */
	private Date reject_time;
	/** 拒收原因  */
	private String reject_reason;
	/** 质量状况  */
	private String reject_quality;
	/** 票号  */
	private String reject_ticket;
	/** 拒收状态  */
	private String reject_status;
	
	public Integer getReject_id() {
		return reject_id;
	}
	public void setReject_id(Integer reject_id) {
		this.reject_id = reject_id;
	}
	public Integer getProduct_id() {
		return product_id;
	}
	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}
	public Integer getSup_id() {
		return sup_id;
	}
	public void setSup_id(Integer sup_id) {
		this.sup_id = sup_id;
	}
	public String getReject_batchnum() {
		return reject_batchnum;
	}
	public void setReject_batchnum(String reject_batchnum) {
		this.reject_batchnum = reject_batchnum;
	}
	public Date getReject_validtime() {
		return reject_validtime;
	}
	public void setReject_validtime(Date reject_validtime) {
		this.reject_validtime = reject_validtime;
	}
	public Date getReject_productdate() {
		return reject_productdate;
	}
	public void setReject_productdate(Date reject_productdate) {
		this.reject_productdate = reject_productdate;
	}
	public Double getReject_num() {
		return reject_num;
	}
	public void setReject_num(Double reject_num) {
		this.reject_num = reject_num;
	}
	public Double getReject_price() {
		return reject_price;
	}
	public void setReject_price(Double reject_price) {
		this.reject_price = reject_price;
	}
	public Integer getReject_person() {
		return reject_person;
	}
	public void setReject_person(Integer integer) {
		this.reject_person = integer;
	}
	public Date getReject_time() {
		return reject_time;
	}
	public void setReject_time(Date reject_time) {
		this.reject_time = reject_time;
	}
	public String getReject_reason() {
		return reject_reason;
	}
	public void setReject_reason(String reject_reason) {
		this.reject_reason = reject_reason;
	}
	public String getReject_quality() {
		return reject_quality;
	}
	public void setReject_quality(String reject_quality) {
		this.reject_quality = reject_quality;
	}
	public String getReject_ticket() {
		return reject_ticket;
	}
	public void setReject_ticket(String reject_ticket) {
		this.reject_ticket = reject_ticket;
	}
	public String getReject_status() {
		return reject_status;
	}
	public void setReject_status(String reject_status) {
		this.reject_status = reject_status;
	}

}
