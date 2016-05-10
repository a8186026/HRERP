package com.goldcow.emanage.util.gen.entity.valueObject.promotion;

import com.goldcow.emanage.util.gen.entity.PmnGiftInfo;

/**
 * 收货审核实体类
 * 
 * @author cyx
 * @version v1.0
 * @since 2015-10-29
 */

public class GiftInfoVO extends PmnGiftInfo{

	private static final long serialVersionUID = 1L;
	
	/** 产品名称 */
	private String product_name;
	/** 规格 */
	private String product_specification;
	/** 单位 */
	private String product_unit;
	/** 产地 */
	private String product_productarea;
	
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getProduct_specification() {
		return product_specification;
	}
	public void setProduct_specification(String product_specification) {
		this.product_specification = product_specification;
	}
	public String getProduct_unit() {
		return product_unit;
	}
	public void setProduct_unit(String product_unit) {
		this.product_unit = product_unit;
	}
	public String getProduct_productarea() {
		return product_productarea;
	}
	public void setProduct_productarea(String product_productarea) {
		this.product_productarea = product_productarea;
	}
	
}