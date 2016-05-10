package com.goldcow.emanage.util.gen.entity;

import com.goldcow.sframe.util.mybatis.BaseEntity;

/**
 * 用户与用户组关系实体类
 * 
 * @author chengrongchang
 * @version v1.0
 * @since 2014-10-23
 */
public class SysUserGroup implements BaseEntity {
	private static final long serialVersionUID = 5622134478717154207L;

	/** ID */
	private Integer user_group_id;
	/** 用户ID */
	private Integer user_id;
	/** 用户组ID */
	private Integer group_id;

	public Integer getUser_group_id() {
		return user_group_id;
	}

	public void setUser_group_id(Integer user_group_id) {
		this.user_group_id = user_group_id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Integer getGroup_id() {
		return group_id;
	}

	public void setGroup_id(Integer group_id) {
		this.group_id = group_id;
	}

}