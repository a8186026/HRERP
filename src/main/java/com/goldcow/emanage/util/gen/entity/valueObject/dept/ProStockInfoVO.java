package com.goldcow.emanage.util.gen.entity.valueObject.dept;

import com.goldcow.emanage.util.gen.entity.ProInfoManage;

/**
 * 收货审核实体类
 * 
 * @author wubin
 * @version v1.1
 * @since 2015-9-25  2015-10-12
 */

	public class ProStockInfoVO extends ProInfoManage{

	private static final long serialVersionUID = 1L;
	/** 库房数量 */
	private Double stock_storageNumber;
	/** 应调数量 */
	private Double shouldDeliveryNum;
	/** 库房id */
	private Integer stock_storage;
	
	public Integer getStock_storage() {
		return stock_storage;
	}
	public void setStock_storage(Integer stock_storage) {
		this.stock_storage = stock_storage;
	}
	public Double getStock_storageNumber() {
		return stock_storageNumber;
	}
	public void setStock_storageNumber(Double stock_storageNumber) {
		this.stock_storageNumber = stock_storageNumber;
	}
	public Double getShouldDeliveryNum() {
		return shouldDeliveryNum;
	}
	public void setShouldDeliveryNum(Double shouldDeliveryNum) {
		this.shouldDeliveryNum = shouldDeliveryNum;
	}	
}