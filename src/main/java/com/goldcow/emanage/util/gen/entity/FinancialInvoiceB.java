package com.goldcow.emanage.util.gen.entity;

import java.util.Date;

import com.goldcow.sframe.util.DataGridModel;
import com.goldcow.sframe.util.mybatis.BaseEntity;
/**
 * 入库购进发票
 * 
 * @author zhanxiaotong
 * @version v1.0
 * @since 2015-12-15
 */
public class FinancialInvoiceB extends DataGridModel implements BaseEntity{
	private static final long serialVersionUID = 1L;
	/** 入库发票副序号*/
	private Integer rkfpb_id;
	/** 发票票号*/
	private String rkfp_ticket;
	/** 发票金额*/
	private Double rkfpb_invoicedMoney;
	/** 发票数量*/
	private Double rkfpb_invoicedNum;
	/** 是否作废*/
	private Integer rkfpb_cancellation;
	/** 创建人*/
	private Integer accept_check_id;
	/** 创建人*/
	private Integer stock_info_id;
	
	public Integer getRkfpb_id() {
		return rkfpb_id;
	}
	public void setRkfpb_id(Integer rkfpb_id) {
		this.rkfpb_id = rkfpb_id;
	}
	public String getRkfp_ticket() {
		return rkfp_ticket;
	}
	public void setRkfp_ticket(String rkfp_ticket) {
		this.rkfp_ticket = rkfp_ticket;
	}
	public Double getRkfpb_invoicedMoney() {
		return rkfpb_invoicedMoney;
	}
	public void setRkfpb_invoicedMoney(Double rkfpb_invoicedMoney) {
		this.rkfpb_invoicedMoney = rkfpb_invoicedMoney;
	}
	public Double getRkfpb_invoicedNum() {
		return rkfpb_invoicedNum;
	}
	public void setRkfpb_invoicedNum(Double rkfpb_invoicedNum) {
		this.rkfpb_invoicedNum = rkfpb_invoicedNum;
	}
	public Integer getAccept_check_id() {
		return accept_check_id;
	}
	public void setAccept_check_id(Integer accept_check_id) {
		this.accept_check_id = accept_check_id;
	}
	public Integer getRkfpb_cancellation() {
		return rkfpb_cancellation;
	}
	public void setRkfpb_cancellation(Integer rkfpb_cancellation) {
		this.rkfpb_cancellation = rkfpb_cancellation;
	}
	public Integer getStock_info_id() {
		return stock_info_id;
	}
	public void setStock_info_id(Integer stock_info_id) {
		this.stock_info_id = stock_info_id;
	}

}