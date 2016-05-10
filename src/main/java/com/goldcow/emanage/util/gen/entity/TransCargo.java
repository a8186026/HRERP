package com.goldcow.emanage.util.gen.entity;	

import java.util.Date;

/**
	*	*@author yangsj
	*/

	public class TransCargo{
	private int trans_id;
	private String trans_ticketId;
	private int product_id;
	private int stockIn_id;
	private int stockOut_id;
	private int trans_operator;
	private Date trans_operateTime;
	private int dept_plan_id;
	private String trans_number;
	private String trans_unitPrice;
	private String trans_settlePrice;
	private int trans_CheckOperator;
	private Date trans_CheckOperateTime;
	private String trans_auto;

	public int getTrans_id(){
		return this.trans_id;
	}
	public void setTrans_id(int trans_id){
		this.trans_id=trans_id;
	}
	public String getTrans_ticketId(){
		return this.trans_ticketId;
	}
	public void setTrans_ticketId(String trans_ticketId){
		this.trans_ticketId=trans_ticketId;
	}
	public int getProduct_id(){
		return this.product_id;
	}
	public void setProduct_id(int product_id){
		this.product_id=product_id;
	}
	public int getStockIn_id(){
		return this.stockIn_id;
	}
	public void setStockIn_id(int stockIn_id){
		this.stockIn_id=stockIn_id;
	}
	public int getStockOut_id(){
		return this.stockOut_id;
	}
	public void setStockOut_id(int stockOut_id){
		this.stockOut_id=stockOut_id;
	}
	public int getTrans_operator(){
		return this.trans_operator;
	}
	public void setTrans_operator(int trans_operator){
		this.trans_operator=trans_operator;
	}
	public Date getTrans_operateTime(){
		return this.trans_operateTime;
	}
	public void setTrans_operateTime(Date trans_operateTime){
		this.trans_operateTime=trans_operateTime;
	}
	public int getDept_plan_id(){
		return this.dept_plan_id;
	}
	public void setDept_plan_id(int dept_plan_id){
		this.dept_plan_id=dept_plan_id;
	}
	public String getTrans_number(){
		return this.trans_number;
	}
	public void setTrans_number(String trans_number){
		this.trans_number=trans_number;
	}
	public String getTrans_unitPrice(){
		return this.trans_unitPrice;
	}
	public void setTrans_unitPrice(String trans_unitPrice){
		this.trans_unitPrice=trans_unitPrice;
	}
	public String getTrans_settlePrice(){
		return this.trans_settlePrice;
	}
	public void setTrans_settlePrice(String trans_settlePrice){
		this.trans_settlePrice=trans_settlePrice;
	}
	public int getTrans_CheckOperator(){
		return this.trans_CheckOperator;
	}
	public void setTrans_CheckOperator(int trans_CheckOperator){
		this.trans_CheckOperator=trans_CheckOperator;
	}
	public Date getTrans_CheckOperateTime(){
		return this.trans_CheckOperateTime;
	}
	public void setTrans_CheckOperateTime(Date trans_CheckOperateTime){
		this.trans_CheckOperateTime=trans_CheckOperateTime;
	}
	public String getTrans_auto(){
		return this.trans_auto;
	}
	public void setTrans_auto(String trans_auto){
		this.trans_auto=trans_auto;
	}

}