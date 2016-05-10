package com.goldcow.emanage.util.gen.entity;

import java.util.Date;

import com.goldcow.sframe.util.DataGridModel;
import com.goldcow.sframe.util.mybatis.BaseEntity;

	/**
	*	*@author chenyuxuan	
	*/

	public class BasCheckLog extends DataGridModel implements BaseEntity{

	private static final long serialVersionUID = 1723414692555267333L;
	/** 日志序号 */
	private Integer log_id;
	/** 修改人 */
	private String log_modifiedperson;
	/** 修改说明 */
	private String log_modifiedspecification;
	/** 修改时间 */
	private Date log_modifiedtime;
	/** 序号 */
	private Integer log_number;
	/** 类型 */
	private String log_type;
	/** 类型序号 */
	private Integer log_typeid;
	/** 审核序号 */
	private Integer log_checkid;
	/** 审核内容 */
	private String log_checkcomment;
	/** 审核人 */
	private String log_checkperson;
	/** 审核时间 */
	private Date log_checktime;
	/** 审核结果 */
	private Integer log_checkresult;
	/** 状态 0启用 1停用 9删除 */
	private Integer status;
	/** 创建人 */
	private Integer create_user;
	/** 创建时间 */
	private Date create_time;
	/** 最后修改人 */
	private Integer last_modify_user;
	/** 最后修改时间 */
	private Date last_modify_time;
	/** 当前审批记录是否处于可修改状态（默认为0，可修改为1）*/
	private Integer check_modified;

	public Integer getLog_id(){
		return this.log_id;
	}
	public void setLog_id(Integer log_id){
		this.log_id=log_id;
	}
	public String getLog_modifiedperson(){
		return this.log_modifiedperson;
	}
	public void setLog_modifiedperson(String log_modifiedperson){
		this.log_modifiedperson=log_modifiedperson;
	}
	public String getLog_modifiedspecification() {
		return log_modifiedspecification;
	}
	public void setLog_modifiedspecification(String log_modifiedspecification) {
		this.log_modifiedspecification = log_modifiedspecification;
	}
	public Date getLog_modifiedtime(){
		return this.log_modifiedtime;
	}
	public void setLog_modifiedtime(Date log_modifiedtime){
		this.log_modifiedtime=log_modifiedtime;
	}
	public Integer getLog_number(){
		return this.log_number;
	}
	public void setLog_number(Integer log_number){
		this.log_number=log_number;
	}
	public String getLog_type(){
		return this.log_type;
	}
	public void setLog_type(String log_type){
		this.log_type=log_type;
	}
	public Integer getLog_typeid(){
		return this.log_typeid;
	}
	public void setLog_typeid(Integer log_typeid){
		this.log_typeid=log_typeid;
	}
	public Integer getLog_checkid(){
		return this.log_checkid;
	}
	public void setLog_checkid(Integer log_checkid){
		this.log_checkid=log_checkid;
	}
	public String getLog_checkcomment(){
		return this.log_checkcomment;
	}
	public void setLog_checkcomment(String log_checkcomment){
		this.log_checkcomment=log_checkcomment;
	}
	public String getLog_checkperson(){
		return this.log_checkperson;
	}
	public void setLog_checkperson(String log_checkperson){
		this.log_checkperson=log_checkperson;
	}
	public Date getLog_checktime(){
		return this.log_checktime;
	}
	public void setLog_checktime(Date log_checktime){
		this.log_checktime=log_checktime;
	}
	public Integer getLog_checkresult(){
		return this.log_checkresult;
	}
	public void setLog_checkresult(Integer log_checkresult){
		this.log_checkresult=log_checkresult;
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
	public Integer getCheck_modified() {
		return check_modified;
	}
	public void setCheck_modified(Integer check_modified) {
		this.check_modified = check_modified;
	}

}