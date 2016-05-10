package com.goldcow.emanage.util.gen.entity;

import java.util.Date;

import com.goldcow.sframe.util.DataGridModel;
import com.goldcow.sframe.util.mybatis.BaseEntity;
/**
 * 重点养护批次信息实体类
 * 
 * @author gaoxiang
 * @version v1.0
 * @since 2015-11-13
 */
public class QltBatchCheck extends DataGridModel implements BaseEntity{
	private static final long serialVersionUID = 1L;
	/** 重点养护批次id */
	private Integer batch_checkId;
	/** 库存id */
	private Integer stock_info_id;
	/** 产品id */
	private Integer product_id;
	/** 确认时间  */
	private Date batch_checkTime;
	/** 确认人 */
	private String batch_checkPerson;
	/** 确认理由 */
	private String batch_checkReason;
	/** 养护重点 */
	private String batch_majorMaintain;
	/** 备注 */
	private String batch_remark;
	/** 批号 */
	private String batch_no;
	/** 开始时间 */
	private Date batch_startTime;
	/** 结束时间 */
	private Date batch_endTime;
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

	public Integer getBatch_checkId(){
		return this.batch_checkId;
	}
	public void setBatch_checkId(Integer batch_checkId){
		this.batch_checkId=batch_checkId;
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
	public Date getBatch_checkTime(){
		return this.batch_checkTime;
	}
	public void setBatch_checkTime(Date batch_checkTime){
		this.batch_checkTime=batch_checkTime;
	}
	public String getBatch_checkPerson(){
		return this.batch_checkPerson;
	}
	public void setBatch_checkPerson(String batch_checkPerson){
		this.batch_checkPerson=batch_checkPerson;
	}
	public String getBatch_checkReason(){
		return this.batch_checkReason;
	}
	public void setBatch_checkReason(String batch_checkReason){
		this.batch_checkReason=batch_checkReason;
	}
	public String getBatch_majorMaintain(){
		return this.batch_majorMaintain;
	}
	public void setBatch_majorMaintain(String batch_majorMaintain){
		this.batch_majorMaintain=batch_majorMaintain;
	}
	public String getBatch_remark(){
		return this.batch_remark;
	}
	public void setBatch_remark(String batch_remark){
		this.batch_remark=batch_remark;
	}
	public String getBatch_no(){
		return this.batch_no;
	}
	public void setBatch_no(String batch_no){
		this.batch_no=batch_no;
	}
	public Date getBatch_startTime() {
		return batch_startTime;
	}
	public void setBatch_startTime(Date batch_startTime) {
		this.batch_startTime = batch_startTime;
	}
	public Date getBatch_endTime() {
		return batch_endTime;
	}
	public void setBatch_endTime(Date batch_endTime) {
		this.batch_endTime = batch_endTime;
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