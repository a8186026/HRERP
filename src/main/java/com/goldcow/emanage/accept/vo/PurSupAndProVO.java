package com.goldcow.emanage.accept.vo;

import com.goldcow.sframe.util.DataGridModel;
import com.goldcow.sframe.util.mybatis.BaseEntity;

/**
 * 特殊药品审核
 * 
 * @version v1.0
 * @since 2015-6-5
 */
public class PurSupAndProVO extends DataGridModel implements BaseEntity{
	private static final long serialVersionUID = 1L;
	/** 供貨商id */
	private String supply_id;
	/** 商品id */
	private String product_id;
	
	public String getSupply_id() {
		return supply_id;
	}
	public void setSupply_id(String supply_id) {
		this.supply_id = supply_id;
	}
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	@Override
	public String toString() {
		return "PurSupAndProVO [supply_id=" + supply_id + ", product_id="
				+ product_id + "]";
	}

}