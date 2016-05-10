package com.goldcow.emanage.util.gen.entity;

import java.util.Date;

import com.goldcow.sframe.util.DataGridModel;
import com.goldcow.sframe.util.mybatis.BaseEntity;
/**
 * 养护品种信息实体类
 * 
 * @author gaoxiang
 * @version v1.0
 * @since 2015-11-13
 */
public class QltMaintainInfo extends DataGridModel implements BaseEntity{
	private static final long serialVersionUID = 1L;
	/** 养护情况id */
	private Integer maintain_id;
	/** 库存id */
	private Integer stock_info_id;
	/** 产品ID */
	private Integer product_id;
	/** 检查日期 */
	private Date maintain_checkTime;
	/** 库房数量 */
	private Double maintain_number;
	/** 存放地点 */
	private String maintain_storagePlace;
	/** 养护措施 */
	private String maintain_measure;
	/** 养护时间 */
	private Date maintain_time;
	/** 养护员 */
	private String maintain_person;
	/** 养护天数 */
	private Integer maintain_days;
	/** 质量状况 */
	private String maintain_quality;
	/** 处理意见 */
	private String maintain_suggestion;
	/** 备注 */
	private String maintain_remark;
	/** 养护票号 */
	private String maintain_ticketNo;
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

	public Integer getMaintain_id(){
		return this.maintain_id;
	}
	public void setMaintain_id(Integer maintain_id){
		this.maintain_id=maintain_id;
	}
	public Integer getStock_info_id(){
		return this.stock_info_id;
	}
	public void setStock_info_id(Integer stock_info_id){
		this.stock_info_id=stock_info_id;
	}
	public Integer getProduct_id(){
		return this.product_id;
	}
	public void setProduct_id(Integer product_id){
		this.product_id=product_id;
	}
	public Date getMaintain_checkTime(){
		return this.maintain_checkTime;
	}
	public void setMaintain_checkTime(Date maintain_checkTime){
		this.maintain_checkTime=maintain_checkTime;
	}
	public Double getMaintain_number(){
		return this.maintain_number;
	}
	public void setMaintain_number(Double maintain_number){
		this.maintain_number=maintain_number;
	}
	public String getMaintain_storagePlace(){
		return this.maintain_storagePlace;
	}
	public void setMaintain_storagePlace(String maintain_storagePlace){
		this.maintain_storagePlace=maintain_storagePlace;
	}
	public String getMaintain_measure(){
		return this.maintain_measure;
	}
	public void setMaintain_measure(String maintain_measure){
		this.maintain_measure=maintain_measure;
	}
	public Date getMaintain_time(){
		return this.maintain_time;
	}
	public void setMaintain_time(Date maintain_time){
		this.maintain_time=maintain_time;
	}
	public String getMaintain_person(){
		return this.maintain_person;
	}
	public void setMaintain_person(String maintain_person){
		this.maintain_person=maintain_person;
	}
	public Integer getMaintain_days(){
		return this.maintain_days;
	}
	public void setMaintain_days(Integer maintain_days){
		this.maintain_days=maintain_days;
	}
	public String getMaintain_quality(){
		return this.maintain_quality;
	}
	public void setMaintain_quality(String maintain_quality){
		this.maintain_quality=maintain_quality;
	}
	public String getMaintain_suggestion(){
		return this.maintain_suggestion;
	}
	public void setMaintain_suggestion(String maintain_suggestion){
		this.maintain_suggestion=maintain_suggestion;
	}
	public String getMaintain_remark(){
		return this.maintain_remark;
	}
	public void setMaintain_remark(String maintain_remark){
		this.maintain_remark=maintain_remark;
	}
	public String getMaintain_ticketNo(){
		return this.maintain_ticketNo;
	}
	public void setMaintain_ticketNo(String maintain_ticketNo){
		this.maintain_ticketNo=maintain_ticketNo;
	}
	public Integer getCreate_user(){
		return this.create_user;
	}
	public void setCreate_user(Integer create_user){
		this.create_user=create_user;
	}
	public Date getCreate_time(){
		return this.create_time;
	}
	public void setCreate_time(Date create_time){
		this.create_time=create_time;
	}
	public Integer getLast_modify_user(){
		return this.last_modify_user;
	}
	public void setLast_modify_user(Integer last_modify_user){
		this.last_modify_user=last_modify_user;
	}
	public Date getLast_modify_time(){
		return this.last_modify_time;
	}
	public void setLast_modify_time(Date last_modify_time){
		this.last_modify_time=last_modify_time;
	}
	public Integer getStatus(){
		return this.status;
	}
	public void setStatus(Integer status){
		this.status=status;
	}

}