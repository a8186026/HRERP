package com.goldcow.emanage.util.gen.entity.valueObject.product;

import com.goldcow.emanage.util.gen.entity.StockPrice;

/**
 * 收货审核实体类
 * 
 * @author wubin
 * @version v1.1
 * @since 2015-9-25  2015-10-12
 */

	public class ProStockPriceVO extends StockPrice{

	private static final long serialVersionUID = 1L;
	/** 库房全名 */
	private String department_fullname;
	/** 产品通用名 */
	private String product_name;
	public String getDepartment_fullname() {
		return department_fullname;
	}
	public void setDepartment_fullname(String department_fullname) {
		this.department_fullname = department_fullname;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
}