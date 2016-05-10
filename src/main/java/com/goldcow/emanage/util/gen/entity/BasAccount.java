package com.goldcow.emanage.util.gen.entity;

import java.util.Date;

import com.goldcow.sframe.util.DataGridModel;
import com.goldcow.sframe.util.mybatis.BaseEntity;
/**
 * 其他开户信息实体类
 * 
 * @author wangqi
 * @version v1.0
 * @since 2015-6-30
 */
public class BasAccount extends DataGridModel implements BaseEntity{
	private static final long serialVersionUID = 1L;
	/** 开户id*/
	private Integer acc_id;
	/** 开户名称*/
	private String acc_name;
	/** 开户账号*/
	private String acc_no;
	/** 开户银行*/
	private String acc_bank;
	/** 注册地址*/
	private String acc_addr;
	/** 开户关联ID,包括产品id，供方id等*/
	private Integer acc_type_id;
	/** 结款方式*/
	private String acc_payway;
	/** 付款方式*/
	private String acc_rmtway;
	/** 结款日期*/
	private Date acc_paydate;
	/** 保证金*/
	private Float acc_bond;
	/** 是否为一般纳税人*/
	private Integer acc_istaxpayer;
	/** 税号*/
	private String acc_tax;
	/** */
	private String acc_fnlnote;
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
	
	private String acc_type;

	public String getAcc_type() {
		return acc_type;
	}
	public void setAcc_type(String acc_type) {
		this.acc_type = acc_type;
	}
	public Integer getAcc_id(){
		return this.acc_id;
	}
	public void setAcc_id(Integer acc_id){
		this.acc_id=acc_id;
	}
	public String getAcc_name(){
		return this.acc_name;
	}
	public void setAcc_name(String acc_name){
		this.acc_name=acc_name;
	}
	public String getAcc_no(){
		return this.acc_no;
	}
	public void setAcc_no(String acc_no){
		this.acc_no=acc_no;
	}
	public String getAcc_bank(){
		return this.acc_bank;
	}
	public void setAcc_bank(String acc_bank){
		this.acc_bank=acc_bank;
	}
	public String getAcc_addr(){
		return this.acc_addr;
	}
	public void setAcc_addr(String acc_addr){
		this.acc_addr=acc_addr;
	}
	public Integer getAcc_type_id(){
		return this.acc_type_id;
	}
	public void setAcc_type_id(Integer acc_type_id){
		this.acc_type_id=acc_type_id;
	}
	public String getAcc_payway(){
		return this.acc_payway;
	}
	public void setAcc_payway(String acc_payway){
		this.acc_payway=acc_payway;
	}
	public String getAcc_rmtway(){
		return this.acc_rmtway;
	}
	public void setAcc_rmtway(String acc_rmtway){
		this.acc_rmtway=acc_rmtway;
	}
	public Date getAcc_paydate(){
		return this.acc_paydate;
	}
	public void setAcc_paydate(Date acc_paydate){
		this.acc_paydate=acc_paydate;
	}
	public Float getAcc_bond(){
		return this.acc_bond;
	}
	public void setAcc_bond(Float acc_bond){
		this.acc_bond=acc_bond;
	}
	public Integer getAcc_istaxpayer(){
		return this.acc_istaxpayer;
	}
	public void setAcc_istaxpayer(Integer acc_istaxpayer){
		this.acc_istaxpayer=acc_istaxpayer;
	}
	public String getAcc_tax(){
		return this.acc_tax;
	}
	public void setAcc_tax(String acc_tax){
		this.acc_tax=acc_tax;
	}
	public String getAcc_fnlnote(){
		return this.acc_fnlnote;
	}
	public void setAcc_fnlnote(String acc_fnlnote){
		this.acc_fnlnote=acc_fnlnote;
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