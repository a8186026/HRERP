package com.goldcow.emanage.util.gen.entity;

import java.util.Date;

import com.goldcow.sframe.util.DataGridModel;
import com.goldcow.sframe.util.mybatis.BaseEntity;

/**
 * 会员卡实体类
 * 
 * @author wubin
 * @version v1.0
 * @since 2015-6-11
 */

public class MemPointManage extends DataGridModel implements BaseEntity{
	private static final long serialVersionUID = 1L;
	/** 序号 */
	private Integer mem_point_id;
	/** 会员卡号 */
	private String mem_card_number;
	/** 消费金额 */
	private Double mem_cost;
	/** 销方id */
	private Integer sal_id;
	/** 积分换钱数 */
	private Integer mem_exchangeMoney;
	/** 积分换赠品数 */
	private Integer mem_exchangeProduct;
	/** 增加积分 */
	private Integer mem_increasePoint;
	/** 减少积分 */
	private Integer mem_reducePoint;
	/** 会员卡积分 */
	private Integer mem_card_point;
	/** 赠品id*/
	private Integer product_id;
	/** 库房id */
	private Integer department_id;
	/** 创建人 */
	private Integer create_user;
	/** 创建时间 */
	private Date create_time;
	/** 状态(0加积分 1减积分 2积分换钱 3积分换赠品 9删除) */
	private Integer status;
	
	
	
	public Integer getMem_point_id() {
		return mem_point_id;
	}

	public void setMem_point_id(Integer mem_point_id) {
		this.mem_point_id = mem_point_id;
	}

	public String getMem_card_number() {
		return mem_card_number;
	}

	public void setMem_card_number(String mem_card_number) {
		this.mem_card_number = mem_card_number;
	}

	public Double getMem_cost() {
		return mem_cost;
	}

	public void setMem_cost(Double mem_cost) {
		this.mem_cost = mem_cost;
	}

	public Integer getSal_id() {
		return sal_id;
	}

	public void setSal_id(Integer sal_id) {
		this.sal_id = sal_id;
	}

	public Integer getMem_exchangeMoney() {
		return mem_exchangeMoney;
	}

	public void setMem_exchangeMoney(Integer mem_exchangeMoney) {
		this.mem_exchangeMoney = mem_exchangeMoney;
	}

	public Integer getMem_exchangeProduct() {
		return mem_exchangeProduct;
	}

	public void setMem_exchangeProduct(Integer mem_exchangeProduct) {
		this.mem_exchangeProduct = mem_exchangeProduct;
	}

	public Integer getMem_increasePoint() {
		return mem_increasePoint;
	}

	public void setMem_increasePoint(Integer mem_increasePoint) {
		this.mem_increasePoint = mem_increasePoint;
	}

	public Integer getMem_reducePoint() {
		return mem_reducePoint;
	}

	public void setMem_reducePoint(Integer mem_reducePoint) {
		this.mem_reducePoint = mem_reducePoint;
	}

	public Integer getMem_card_point() {
		return mem_card_point;
	}

	public void setMem_card_point(Integer mem_card_point) {
		this.mem_card_point = mem_card_point;
	}

	public Integer getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}

	public Integer getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(Integer department_id) {
		this.department_id = department_id;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}