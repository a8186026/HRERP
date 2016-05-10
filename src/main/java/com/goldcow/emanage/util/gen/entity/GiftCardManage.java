package com.goldcow.emanage.util.gen.entity;

import java.util.Date;

import com.goldcow.sframe.util.DataGridModel;
import com.goldcow.sframe.util.mybatis.BaseEntity;
/**
 * 代金卡实体类
 * 
 * @author cyx
 * @version v1.0
 * @since 2015-12-11
 */
public class GiftCardManage extends DataGridModel implements BaseEntity{
	private static final long serialVersionUID = 1L;
	/** 代金卡id */
	private Integer gift_card_id;
	/** 代金卡满足金额 */
	private Double gift_card_fulfilAmount;
	/** 代金卡抵消金额 */
	private Double gift_card_offsetAmount;
	/** 部门Id */
	private Integer department_id;
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
	
	public Integer getGift_card_id(){
		return this.gift_card_id;
	}
	public void setGift_card_id(Integer gift_card_id){
		this.gift_card_id=gift_card_id;
	}
	public Double getGift_card_fulfilAmount(){
		return this.gift_card_fulfilAmount;
	}
	public void setGift_card_fulfilAmount(Double gift_card_fulfilAmount){
		this.gift_card_fulfilAmount=gift_card_fulfilAmount;
	}
	public Double getGift_card_offsetAmount(){
		return this.gift_card_offsetAmount;
	}
	public void setGift_card_offsetAmount(Double gift_card_offsetAmount){
		this.gift_card_offsetAmount=gift_card_offsetAmount;
	}
	public Integer getDepartment_id(){
		return this.department_id;
	}
	public void setDepartment_id(Integer department_id){
		this.department_id=department_id;
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
	public void setStatus(Integer status) {
		this.status = status;
	}
}