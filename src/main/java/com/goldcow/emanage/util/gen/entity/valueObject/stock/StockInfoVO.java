package com.goldcow.emanage.util.gen.entity.valueObject.stock;

import com.goldcow.emanage.util.gen.entity.StockInfo;


/**
 * 库存批次信息VO
 * 
 * @author wubin
 * @version v1.0
 * @since 2016-1-5
 */

public class StockInfoVO extends StockInfo {
	private static final long serialVersionUID = 1L;
	/** 产品编号 */
	private String product_code;
	/** 产品名称 */
	private String product_name;
	/** 通用名 */
	private String product_commonname;
	public String getProduct_code() {
		return product_code;
	}
	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getProduct_commonname() {
		return product_commonname;
	}
	public void setProduct_commonname(String product_commonname) {
		this.product_commonname = product_commonname;
	}
	
}
