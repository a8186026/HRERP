package com.goldcow.emanage.util.gen.entity;

import java.util.Date;

import com.goldcow.sframe.util.DataGridModel;
import com.goldcow.sframe.util.mybatis.BaseEntity;

/**
 * 折扣规则——产品 关联实体类
 * 
 * @author gaoxiang
 * @version v1.0
 * @since 2015-10-29
 */
public class PmnRuleProduct extends DataGridModel implements BaseEntity{
	private static final long serialVersionUID = 1L;
	/** 折扣规则——产品关联id */
	private Integer rule_product_id;
	/** 折扣规则id */
	private Integer rule_id;
	/** 产品id */
	private Integer product_id;
	/** 创建人 */
	private Integer create_user;
	/** 创建时间 */
	private Date create_time; 
	/** 状态 0启用 1停用 9删除 */
	private Integer status;
	public Integer getRule_product_id() {
		return rule_product_id;
	}
	public void setRule_product_id(Integer rule_product_id) {
		this.rule_product_id = rule_product_id;
	}
	public Integer getRule_id() {
		return rule_id;
	}
	public void setRule_id(Integer rule_id) {
		this.rule_id = rule_id;
	}
	public Integer getProduct_id() {
		return product_id;
	}
	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}