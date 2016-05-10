package com.goldcow.emanage.util.gen.entity;

import java.util.Date;

import com.goldcow.sframe.util.DataGridModel;
import com.goldcow.sframe.util.mybatis.BaseEntity;

/**
 * 药品名称实体类
 * 
 * @author gaoxiang
 * @version v1.0
 * @since 2015-6-2
 */
public class BasMedicineInfo extends DataGridModel implements BaseEntity {
	private static final long serialVersionUID = -6839480669389995082L;
	/** 药品序号ID */
	private Integer medicine_id;
	/** 药品编码 */
	private String medicine_code;
	/** 药品名称 */
	private String medicine_name;
	/** 药品学名 */
	private String medicine_proname;
	/** 药品英文名 */
	private String medicine_engname;
	/** 药品拼音码 */
	private String medicine_chnpy;
	/** 药品功效 */
	private String medicine_effect;
	/** 药品注意事项 */
	private String medicine_note;
	/** 药品简介 */
	private String medicine_description;
	/** 药品更新时间 */
	private Date medicine_update_time;
	/** 标识 */
	private Integer flag;	
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
	
	public Integer getMedicine_id() {
		return medicine_id;
	}
	public void setMedicine_id(Integer medicine_id) {
		this.medicine_id = medicine_id;
	}
	public String getMedicine_code() {
		return medicine_code;
	}
	public void setMedicine_code(String medicine_code) {
		this.medicine_code = medicine_code;
	}
	public String getMedicine_name() {
		return medicine_name;
	}
	public void setMedicine_name(String medicine_name) {
		this.medicine_name = medicine_name;
	}
	public String getMedicine_proname() {
		return medicine_proname;
	}
	public void setMedicine_proname(String medicine_proname) {
		this.medicine_proname = medicine_proname;
	}
	public String getMedicine_engname() {
		return medicine_engname;
	}
	public void setMedicine_engname(String medicine_engname) {
		this.medicine_engname = medicine_engname;
	}
	public String getMedicine_chnpy() {
		return medicine_chnpy;
	}
	public void setMedicine_chnpy(String medicine_chnpy) {
		this.medicine_chnpy = medicine_chnpy;
	}
	public String getMedicine_effect() {
		return medicine_effect;
	}
	public void setMedicine_effect(String medicine_effect) {
		this.medicine_effect = medicine_effect;
	}
	public String getMedicine_note() {
		return medicine_note;
	}
	public void setMedicine_note(String medicine_note) {
		this.medicine_note = medicine_note;
	}
	public String getMedicine_description() {
		return medicine_description;
	}
	public void setMedicine_description(String medicine_description) {
		this.medicine_description = medicine_description;
	}
	public Date getMedicine_update_time() {
		return medicine_update_time;
	}
	public void setMedicine_update_time(Date medicine_update_time) {
		this.medicine_update_time = medicine_update_time;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
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

