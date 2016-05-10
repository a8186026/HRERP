package com.goldcow.emanage.util.gen.entity;

import java.util.Date;

import com.goldcow.sframe.util.DataGridModel;
import com.goldcow.sframe.util.mybatis.BaseEntity;

/**
 * 折扣规则实体类
 * 
 * @author gaoxiang
 * @version v1.0
 * @since 2015-10-27
 */
public class PmnRuleInfo extends DataGridModel implements BaseEntity{
	private static final long serialVersionUID = 1L;
	/** 规则id */
	private Integer rule_id;
	/** 规则名称 */
	private String rule_name;
	/** 规则类型 */
	private Integer rule_type;
	/** 规则设置 */
	private String rule_set;
	/** 开始日期 */
	private Date rule_startdate;
	/** 结束日期 */
	private Date rule_enddate;
	/** 开始时间 */
	private String rule_starttime;
	/** 结束时间 */
	private String rule_endtime;
	/** 数量 */
	private Double rule_number;
	/** 折扣 */
	private Double rule_discount;
	/** 规则优先序号 */
	private Integer rule_priority;
	/** 规则添加人 */
	private String rule_addperson;
	/** 规则添加时间 */
	private Date rule_addtime;
	/** 规则备注 */
	private String rule_remark;
	/** 状态 0启用 1停用 9删除 */
	private Integer status;

	public Integer getRule_id(){
		return this.rule_id;
	}
	public void setRule_id(Integer rule_id){
		this.rule_id=rule_id;
	}
	public String getRule_name(){
		return this.rule_name;
	}
	public void setRule_name(String rule_name){
		this.rule_name=rule_name;
	}
	public Integer getRule_type(){
		return this.rule_type;
	}
	public void setRule_type(Integer rule_type){
		this.rule_type=rule_type;
	}
	public String getRule_set(){
		return this.rule_set;
	}
	public void setRule_set(String rule_set){
		this.rule_set=rule_set;
	}
	public Date getRule_startdate() {
		return rule_startdate;
	}
	public void setRule_startdate(Date rule_startdate) {
		this.rule_startdate = rule_startdate;
	}
	public Date getRule_enddate() {
		return rule_enddate;
	}
	public void setRule_enddate(Date rule_enddate) {
		this.rule_enddate = rule_enddate;
	}
	public String getRule_starttime(){
		return this.rule_starttime;
	}
	public void setRule_starttime(String rule_starttime){
		this.rule_starttime=rule_starttime;
	}
	public String getRule_endtime(){
		return this.rule_endtime;
	}
	public void setRule_endtime(String rule_endtime){
		this.rule_endtime=rule_endtime;
	}
	public Double getRule_number(){
		return this.rule_number;
	}
	public void setRule_number(Double rule_number){
		this.rule_number=rule_number;
	}
	public Double getRule_discount(){
		return this.rule_discount;
	}
	public void setRule_discount(Double rule_discount){
		this.rule_discount=rule_discount;
	}
	public Integer getRule_priority(){
		return this.rule_priority;
	}
	public void setRule_priority(Integer rule_priority){
		this.rule_priority=rule_priority;
	}
	public String getRule_addperson(){
		return this.rule_addperson;
	}
	public void setRule_addperson(String rule_addperson){
		this.rule_addperson=rule_addperson;
	}
	public Date getRule_addtime(){
		return this.rule_addtime;
	}
	public void setRule_addtime(Date rule_addtime){
		this.rule_addtime=rule_addtime;
	}
	public Integer getStatus(){
		return this.status;
	}
	public void setStatus(Integer status){
		this.status=status;
	}
	public String getRule_remark() {
		return rule_remark;
	}
	public void setRule_remark(String rule_remark) {
		this.rule_remark = rule_remark;
	}
	
}