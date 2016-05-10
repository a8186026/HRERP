package com.goldcow.emanage.util.gen.entity;

import java.util.Date;

import com.goldcow.sframe.util.DataGridModel;
import com.goldcow.sframe.util.mybatis.BaseEntity;

/**
 * 厂家档案实体类
 * 
 * @author gaoxiang
 * @version v1.0
 * @since 2015-6-2
 */
public class BasFactoryInfo extends DataGridModel implements BaseEntity {
	private static final long serialVersionUID = 1L;
	/** 厂家序号ID */
	private Integer factory_id;
	/** 厂家档案号 */
	private String factory_filenumber;
	/** 厂家编码 */
	private String factory_code;
	/** 厂家区号 */
	private String factory_areacode;
	/** 厂家名称 */
	private String factory_name;
	/** 厂家简称 */
	private String factory_shortname;
    /** 厂家拼音码 */
	private String factory_chnpy;	
	/** 厂家网址 */
	private String factory_website;
	/** 厂家地址 */
	private String factory_address;
	/** 厂家邮编 */
	private String factory_zipcode;
	/** 厂家电话 */
	private String factory_tel;
	/** 厂家联系人 */
	private String factory_contactperson;
	/** 厂家负责人 */
	private String factory_chiefperson;
	/** 厂家生产品种 */
	private String factory_productvariety;
	/** 厂家简介 */
	private String factory_description;
	/** 厂家证照 */
	private String factory_license;
	/** 标识 */
	private Integer factory_flag;	
	/** 状态 0启用 1停用 9删除 */
	private Integer status;
	/** 创建人 */
	private Integer create_user;
	/** 创建时间 */
	private Date create_time;
	/** 最后修改人 */
	private Integer last_modify_user;
	/** 最后修改时间 */
	private Date last_modify_time;
	
	public Integer getFactory_id() {
		return factory_id;
	}
	public void setFactory_id(Integer factory_id) {
		this.factory_id = factory_id;
	}
	public String getFactory_filenumber() {
		return factory_filenumber;
	}
	public void setFactory_filenumber(String factory_filenumber) {
		this.factory_filenumber = factory_filenumber;
	}
	public String getFactory_code() {
		return factory_code;
	}
	public void setFactory_code(String factory_code) {
		this.factory_code = factory_code;
	}
	public String getFactory_areacode() {
		return factory_areacode;
	}
	public void setFactory_areacode(String factory_areacode) {
		this.factory_areacode = factory_areacode;
	}
	public String getFactory_name() {
		return factory_name;
	}
	public void setFactory_name(String factory_name) {
		this.factory_name = factory_name;
	}
	public String getFactory_shortname() {
		return factory_shortname;
	}
	public void setFactory_shortname(String factory_shortname) {
		this.factory_shortname = factory_shortname;
	}
	public String getFactory_chnpy() {
		return factory_chnpy;
	}
	public void setFactory_chnpy(String factory_chnpy) {
		this.factory_chnpy = factory_chnpy;
	}
	public String getFactory_website() {
		return factory_website;
	}
	public void setFactory_website(String factory_website) {
		this.factory_website = factory_website;
	}
	public String getFactory_address() {
		return factory_address;
	}
	public void setFactory_address(String factory_address) {
		this.factory_address = factory_address;
	}
	public String getFactory_zipcode() {
		return factory_zipcode;
	}
	public void setFactory_zipcode(String factory_zipcode) {
		this.factory_zipcode = factory_zipcode;
	}
	public String getFactory_tel() {
		return factory_tel;
	}
	public void setFactory_tel(String factory_tel) {
		this.factory_tel = factory_tel;
	}
	public String getFactory_contactperson() {
		return factory_contactperson;
	}
	public void setFactory_contactperson(String factory_contactperson) {
		this.factory_contactperson = factory_contactperson;
	}
	public String getFactory_chiefperson() {
		return factory_chiefperson;
	}
	public void setFactory_chiefperson(String factory_chiefperson) {
		this.factory_chiefperson = factory_chiefperson;
	}
	public String getFactory_productvariety() {
		return factory_productvariety;
	}
	public void setFactory_productvariety(String factory_productvariety) {
		this.factory_productvariety = factory_productvariety;
	}
	public String getFactory_description() {
		return factory_description;
	}
	public void setFactory_description(String factory_description) {
		this.factory_description = factory_description;
	}
	public String getFactory_license() {
		return factory_license;
	}
	public void setFactory_license(String factory_license) {
		this.factory_license = factory_license;
	}
	public Integer getFactory_flag() {
		return factory_flag;
	}
	public void setFactory_flag(Integer factory_flag) {
		this.factory_flag = factory_flag;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
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
	public Integer getLast_modify_user() {
		return last_modify_user;
	}
	public void setLast_modify_user(Integer last_modify_user) {
		this.last_modify_user = last_modify_user;
	}
	public Date getLast_modify_time() {
		return last_modify_time;
	}
	public void setLast_modify_time(Date last_modify_time) {
		this.last_modify_time = last_modify_time;
	}
	
}

