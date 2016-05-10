package com.goldcow.emanage.util.gen.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.goldcow.sframe.util.DataGridModel;
import com.goldcow.sframe.util.mybatis.BaseEntity;

public class StockPrice extends DataGridModel implements BaseEntity{
		/**
		 * @author zhanxiaotong
		 * @version v1.0
		 * @since 2015-9-15
		*/	
	private static final long serialVersionUID = -7970636854771442541L;
	
	private Integer prod_id;
	/** 序号 */
	private Integer product_id;
	/** 库房 */
	private Integer department_id;
	/** 组号 */
	private String pro_group_no;
	/** 货位号 */
	private String pro_location_no;
	/** 保管员 */
	private String pro_storeman;
	/** 包装量 */
	private Double pro_packing_quantity;
	/** 包装单位 */
	private String pro_packing_unit;
	/** 最高限量 */
	private Double pro_max_limit;
	/** 最低限量 */
	private Double pro_min_limit;
	/** 进货量 */
	private Integer pro_retail_quantity;
	/** 批发价 */
	private Double pro_trade_price;
	/** 零售价 */
	private Double pro_retail_price;
	/** 最低价 */
	private Double pro_min_price;
	/** 销售价1 */
	private Double pro_saleprice1;
	/** 销售价2 */
	private Double pro_saleprice2;
	/** 销售价3 */
	private Double pro_saleprice3;
	/** 销售价4 */
	private Double pro_saleprice4;
	/** 进价 */
	private Double pro_purchase_price;
	/** 市场价 */
	private Double pro_market_price;
	/** 成本价 */
	private Double pro_cost_price;
	/** 参考进价 */
	private Double pro_refer_purchase_price;
	/** 结算价 */
	private Double pro_final_price;
	/** 报价 */
	private Double pro_offer_price;
	/** abc类 */
	private String pro_abc_class;
	/** 会员不积分 */
	private Integer pro_member_no_integral;
	/** 会员价 */
	private Double pro_member_price;
	/** 零售提成 */
	private Double pro_retail_commission;
	/** 按零售价 */
	private Integer pro_on_retail_price;
	/** 零售最低价 */
	private Double pro_min_retail_price;
	/** 批发最低价 */
	private Double pro_min_trade_price;
	/** 任务量 */
	private Double pro_task_quantity;
	/** 零售提成最低价 */
	private Double pro_retail_commission_min_price;
	/** 状态 */
	private Integer status;
	/** 创建人 */
	private Integer create_user;
	/** 创建时间 */
	private Date create_time;
	/** 最后修改人 */
	private Integer last_modify_user;
	/** 最后修改时间 */
	private Date last_modify_time;
	
	
	public Integer getProd_id() {
		return prod_id;
	}
	public void setProd_id(Integer prod_id) {
		this.prod_id = prod_id;
	}
	public Integer getProduct_id() {
		return product_id;
	}
	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}
	public Integer getDepartment_id() {
		return department_id;
	}
	public void setDepartment_id(Integer department_id) {
		this.department_id = department_id;
	}
	public String getPro_group_no() {
		return pro_group_no;
	}
	public void setPro_group_no(String pro_group_no) {
		this.pro_group_no = pro_group_no;
	}
	public String getPro_location_no() {
		return pro_location_no;
	}
	public void setPro_location_no(String pro_location_no) {
		this.pro_location_no = pro_location_no;
	}
	public String getPro_storeman() {
		return pro_storeman;
	}
	public void setPro_storeman(String pro_storeman) {
		this.pro_storeman = pro_storeman;
	}
	public Double getPro_packing_quantity() {
		return pro_packing_quantity;
	}
	public void setPro_packing_quantity(Double pro_packing_quantity) {
		this.pro_packing_quantity = pro_packing_quantity;
	}
	public String getPro_packing_unit() {
		return pro_packing_unit;
	}
	public void setPro_packing_unit(String pro_packing_unit) {
		this.pro_packing_unit = pro_packing_unit;
	}
	public Double getPro_max_limit() {
		return pro_max_limit;
	}
	public void setPro_max_limit(Double pro_max_limit) {
		this.pro_max_limit = pro_max_limit;
	}
	public Double getPro_min_limit() {
		return pro_min_limit;
	}
	public void setPro_min_limit(Double pro_min_limit) {
		this.pro_min_limit = pro_min_limit;
	}
	public Integer getPro_retail_quantity() {
		return pro_retail_quantity;
	}
	public void setPro_retail_quantity(Integer pro_retail_quantity) {
		this.pro_retail_quantity = pro_retail_quantity;
	}
	public Double getPro_trade_price() {
		return pro_trade_price;
	}
	public void setPro_trade_price(Double pro_trade_price) {
		this.pro_trade_price = pro_trade_price;
	}
	public Double getPro_retail_price() {
		return pro_retail_price;
	}
	public void setPro_retail_price(Double pro_retail_price) {
		this.pro_retail_price = pro_retail_price;
	}
	public Double getPro_min_price() {
		return pro_min_price;
	}
	public void setPro_min_price(Double pro_min_price) {
		this.pro_min_price = pro_min_price;
	}
	public Double getPro_saleprice1() {
		return pro_saleprice1;
	}
	public void setPro_saleprice1(Double pro_saleprice1) {
		this.pro_saleprice1 = pro_saleprice1;
	}
	public Double getPro_saleprice2() {
		return pro_saleprice2;
	}
	public void setPro_saleprice2(Double pro_saleprice2) {
		this.pro_saleprice2 = pro_saleprice2;
	}
	public Double getPro_saleprice3() {
		return pro_saleprice3;
	}
	public void setPro_saleprice3(Double pro_saleprice3) {
		this.pro_saleprice3 = pro_saleprice3;
	}
	public Double getPro_saleprice4() {
		return pro_saleprice4;
	}
	public void setPro_saleprice4(Double pro_saleprice4) {
		this.pro_saleprice4 = pro_saleprice4;
	}
	public Double getPro_purchase_price() {
		return pro_purchase_price;
	}
	public void setPro_purchase_price(Double pro_purchase_price) {
		this.pro_purchase_price = pro_purchase_price;
	}
	public Double getPro_market_price() {
		return pro_market_price;
	}
	public void setPro_market_price(Double pro_market_price) {
		this.pro_market_price = pro_market_price;
	}
	public Double getPro_cost_price() {
		return pro_cost_price;
	}
	public void setPro_cost_price(Double pro_cost_price) {
		this.pro_cost_price = pro_cost_price;
	}
	public Double getPro_refer_purchase_price() {
		return pro_refer_purchase_price;
	}
	public void setPro_refer_purchase_price(Double pro_refer_purchase_price) {
		this.pro_refer_purchase_price = pro_refer_purchase_price;
	}
	public Double getPro_final_price() {
		return pro_final_price;
	}
	public void setPro_final_price(Double pro_final_price) {
		this.pro_final_price = pro_final_price;
	}
	public Double getPro_offer_price() {
		return pro_offer_price;
	}
	public void setPro_offer_price(Double pro_offer_price) {
		this.pro_offer_price = pro_offer_price;
	}
	public String getPro_abc_class() {
		return pro_abc_class;
	}
	public void setPro_abc_class(String pro_abc_class) {
		this.pro_abc_class = pro_abc_class;
	}
	public Integer getPro_member_no_integral() {
		return pro_member_no_integral;
	}
	public void setPro_member_no_integral(Integer pro_member_no_integral) {
		this.pro_member_no_integral = pro_member_no_integral;
	}
	public Double getPro_member_price() {
		return pro_member_price;
	}
	public void setPro_member_price(Double pro_member_price) {
		this.pro_member_price = pro_member_price;
	}
	public Double getPro_retail_commission() {
		return pro_retail_commission;
	}
	public void setPro_retail_commission(Double pro_retail_commission) {
		this.pro_retail_commission = pro_retail_commission;
	}
	public Integer getPro_on_retail_price() {
		return pro_on_retail_price;
	}
	public void setPro_on_retail_price(Integer pro_on_retail_price) {
		this.pro_on_retail_price = pro_on_retail_price;
	}
	public Double getPro_min_retail_price() {
		return pro_min_retail_price;
	}
	public void setPro_min_retail_price(Double pro_min_retail_price) {
		this.pro_min_retail_price = pro_min_retail_price;
	}
	public Double getPro_min_trade_price() {
		return pro_min_trade_price;
	}
	public void setPro_min_trade_price(Double pro_min_trade_price) {
		this.pro_min_trade_price = pro_min_trade_price;
	}
	public Double getPro_task_quantity() {
		return pro_task_quantity;
	}
	public void setPro_task_quantity(Double pro_task_quantity) {
		this.pro_task_quantity = pro_task_quantity;
	}
	public Double getPro_retail_commission_min_price() {
		return pro_retail_commission_min_price;
	}
	public void setPro_retail_commission_min_price(
			Double pro_retail_commission_min_price) {
		this.pro_retail_commission_min_price = pro_retail_commission_min_price;
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