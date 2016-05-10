package com.goldcow.emanage.util.gen.entity;

import java.util.Date;

import com.goldcow.sframe.util.DataGridModel;
import com.goldcow.sframe.util.mybatis.BaseEntity;

public class BasPictureInfo extends DataGridModel implements BaseEntity{
	/**
	 * 图片信息实体类
	 * 
	 * @author gaoxiang
	 * @version v1.0
	 * @since 2015-6-30
	 */
	private static final long serialVersionUID = -7970636854771442541L;
	/** 图片ID */
	private Integer picture_id;
	/** 图片关联ID,包括产品id，供方id等 */
	private Integer picture_type_id;
	/** 图片类型,包括产品图片，供方图片等 */
	private String picture_type;
	/** 图片名称 */
	private String picture_name;
	/** 图片位置 */
	private String picture_position;
	/** 图片备注 */
	private String picture_remark;
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
	public Integer getPicture_id() {
		return picture_id;
	}
	public void setPicture_id(Integer picture_id) {
		this.picture_id = picture_id;
	}
	public Integer getPicture_type_id() {
		return picture_type_id;
	}
	public void setPicture_type_id(Integer picture_type_id) {
		this.picture_type_id = picture_type_id;
	}
	public String getPicture_type() {
		return picture_type;
	}
	public void setPicture_type(String picture_type) {
		this.picture_type = picture_type;
	}
	public String getPicture_name() {
		return picture_name;
	}
	public void setPicture_name(String picture_name) {
		this.picture_name = picture_name;
	}
	public String getPicture_position() {
		return picture_position;
	}
	public void setPicture_position(String picture_position) {
		this.picture_position = picture_position;
	}
	public String getPicture_remark() {
		return picture_remark;
	}
	public void setPicture_remark(String picture_remark) {
		this.picture_remark = picture_remark;
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
