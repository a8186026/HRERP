package com.goldcow.emanage.util.gen.entity;

import java.util.Date;

import com.goldcow.sframe.util.DataGridModel;
import com.goldcow.sframe.util.mybatis.BaseEntity;
/**
 * 控件配置类
 * 
 * @author wubin
 * @version v1.1
 * @since 2015-10-20 2015-11-24
 */
public class MemDayManage extends DataGridModel implements BaseEntity{
	private static final long serialVersionUID = 1L;
	    /** 会员日ID */
		private Integer mem_day_id;
		/** 时间方式 */
		private String mem_day_timeMethod;
		/** 日期 */
		private String mem_day_date;
		/** 积分倍数 */
		private Integer mem_day_pointMultiple;
		/** 执行价格*/
		private Integer mem_day_executePrice;
		/** 非执行价格 */
		private Integer mem_day_noExecutePrice;
		/** 折扣方式 */
		private String mem_day_discountMethod;
		/** 折扣 */
		private Double mem_day_discount;
		/** 开始日期 */
		private Date mem_day_startDate;
		/** 结束日期 */
		private Date mem_day_endDate;
		/** 会员日类型 */
		private String mem_day_type;
		/** 非折扣方式 */
		private String mem_day_noDiscountMethod;
		/** 非折扣 */
		private Double mem_day_noDiscount;
		/** 会员日优先级 */
		private Integer mem_day_priority;
		/** 状态(0启用1禁用9删除) */
		private Integer status;
		/** 创建人 */
		private Integer create_user;
		/** 创建时间 */
		private Date create_time;
		/** 修改人 */
		private Integer last_modify_user;
		/** 修改时间 */
		private Date last_modify_time;
		/** 参与部门 */
		private String department_ids;
		
		public Integer getMem_day_id() {
			return mem_day_id;
		}
		public void setMem_day_id(Integer mem_day_id) {
			this.mem_day_id = mem_day_id;
		}
		public String getMem_day_timeMethod() {
			return mem_day_timeMethod;
		}
		public void setMem_day_timeMethod(String mem_day_timeMethod) {
			this.mem_day_timeMethod = mem_day_timeMethod;
		}
		public String getMem_day_date() {
			return mem_day_date;
		}
		public void setMem_day_date(String mem_day_date) {
			this.mem_day_date = mem_day_date;
		}
		public Integer getMem_day_pointMultiple() {
			return mem_day_pointMultiple;
		}
		public void setMem_day_pointMultiple(Integer mem_day_pointMultiple) {
			this.mem_day_pointMultiple = mem_day_pointMultiple;
		}
		public Integer getMem_day_executePrice() {
			return mem_day_executePrice;
		}
		public void setMem_day_executePrice(Integer mem_day_executePrice) {
			this.mem_day_executePrice = mem_day_executePrice;
		}
		public Integer getMem_day_noExecutePrice() {
			return mem_day_noExecutePrice;
		}
		public void setMem_day_noExecutePrice(Integer mem_day_noExecutePrice) {
			this.mem_day_noExecutePrice = mem_day_noExecutePrice;
		}
		public String getMem_day_discountMethod() {
			return mem_day_discountMethod;
		}
		public void setMem_day_discountMethod(String mem_day_discountMethod) {
			this.mem_day_discountMethod = mem_day_discountMethod;
		}
		public Double getMem_day_discount() {
			return mem_day_discount;
		}
		public void setMem_day_discount(Double mem_day_discount) {
			this.mem_day_discount = mem_day_discount;
		}
		public Date getMem_day_startDate() {
			return mem_day_startDate;
		}
		public void setMem_day_startDate(Date mem_day_startDate) {
			this.mem_day_startDate = mem_day_startDate;
		}
		public Date getMem_day_endDate() {
			return mem_day_endDate;
		}
		public void setMem_day_endDate(Date mem_day_endDate) {
			this.mem_day_endDate = mem_day_endDate;
		}
		public String getMem_day_type() {
			return mem_day_type;
		}
		public void setMem_day_type(String mem_day_type) {
			this.mem_day_type = mem_day_type;
		}
		public String getMem_day_noDiscountMethod() {
			return mem_day_noDiscountMethod;
		}
		public void setMem_day_noDiscountMethod(String mem_day_noDiscountMethod) {
			this.mem_day_noDiscountMethod = mem_day_noDiscountMethod;
		}
		public Double getMem_day_noDiscount() {
			return mem_day_noDiscount;
		}
		public void setMem_day_noDiscount(Double mem_day_noDiscount) {
			this.mem_day_noDiscount = mem_day_noDiscount;
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
		public String getDepartment_ids() {
			return department_ids;
		}
		public void setDepartment_ids(String department_ids) {
			this.department_ids = department_ids;
		}
		public Integer getMem_day_priority() {
			return mem_day_priority;
		}
		public void setMem_day_priority(Integer mem_day_priority) {
			this.mem_day_priority = mem_day_priority;
		}
}
