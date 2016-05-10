package com.goldcow.emanage.util.gen.entity;

import java.util.Date;

import com.goldcow.sframe.util.DataGridModel;
import com.goldcow.sframe.util.mybatis.BaseEntity;

public class TestBasSupply extends DataGridModel implements BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7970636854771442541L;
	/** ID */
	private Integer suppply_id;
	/** 审核类型，包括产品审核，供方审核，销方审核等 */
	private String supply_name;
	/** 审核类型id 产品-1 供方-2 */
	private String supply_code;
	/** 审核id  */
	private String supply_number;
	/** 审核意见*/
	private String supply_type;
	private String supply_person;
	private String supply_telephone;
	
	private String supply_permission_no;
	private String supply_permission_name;
	private String supply_permission_wide;
	public Integer getSuppply_id() {
		return suppply_id;
	}
	public void setSuppply_id(Integer suppply_id) {
		this.suppply_id = suppply_id;
	}
	public String getSupply_name() {
		return supply_name;
	}
	public void setSupply_name(String supply_name) {
		this.supply_name = supply_name;
	}
	public String getSupply_code() {
		return supply_code;
	}
	public void setSupply_code(String supply_code) {
		this.supply_code = supply_code;
	}
	public String getSupply_number() {
		return supply_number;
	}
	public void setSupply_number(String supply_number) {
		this.supply_number = supply_number;
	}
	public String getSupply_type() {
		return supply_type;
	}
	public void setSupply_type(String supply_type) {
		this.supply_type = supply_type;
	}
	public String getSupply_person() {
		return supply_person;
	}
	public void setSupply_person(String supply_person) {
		this.supply_person = supply_person;
	}
	public String getSupply_telephone() {
		return supply_telephone;
	}
	public void setSupply_telephone(String supply_telephone) {
		this.supply_telephone = supply_telephone;
	}
	public String getSupply_permission_no() {
		return supply_permission_no;
	}
	public void setSupply_permission_no(String supply_permission_no) {
		this.supply_permission_no = supply_permission_no;
	}
	public String getSupply_permission_name() {
		return supply_permission_name;
	}
	public void setSupply_permission_name(String supply_permission_name) {
		this.supply_permission_name = supply_permission_name;
	}
	public String getSupply_permission_wide() {
		return supply_permission_wide;
	}
	public void setSupply_permission_wide(String supply_permission_wide) {
		this.supply_permission_wide = supply_permission_wide;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
