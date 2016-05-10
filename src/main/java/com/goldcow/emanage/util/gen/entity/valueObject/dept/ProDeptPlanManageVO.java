package com.goldcow.emanage.util.gen.entity.valueObject.dept;

import com.goldcow.emanage.util.gen.entity.DeptPlanManage;

/**
 * 收货审核实体类
 * 
 * @author wubin
 * @version v1.1
 * @since 2015-9-25  2015-10-12
 */

	public class ProDeptPlanManageVO extends DeptPlanManage{

	private static final long serialVersionUID = 1L;
	/** 产品名称 */
	private String product_name;
	/** 规格 */
	private String product_specification;
	/** 单位 */
	private String product_unit;
	/** 产地 */
	private String product_productarea;
	/** 生产厂家 */
	private String product_factoryname;
	/** 必调  1必调 0正常*/
	private String product_musthave;
	/** 最低限量 */
	private Double product_minnunmber;
	
	public Double getProduct_minnunmber() {
		return product_minnunmber;
	}
	public void setProduct_minnunmber(Double product_minnunmber) {
		this.product_minnunmber = product_minnunmber;
	}
	public Double getProduct_maxnunmber() {
		return product_maxnunmber;
	}
	public void setProduct_maxnunmber(Double product_maxnunmber) {
		this.product_maxnunmber = product_maxnunmber;
	}
	/** 最高限量 */
	private Double product_maxnunmber;
	
	
	public String getProduct_musthave() {
		return product_musthave;
	}
	public void setProduct_musthave(String product_musthave) {
		this.product_musthave = product_musthave;
	}
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
	public String getProduct_factoryname() {
		return product_factoryname;
	}
	public void setProduct_factoryname(String product_factoryname) {
		this.product_factoryname = product_factoryname;
	}
	
}