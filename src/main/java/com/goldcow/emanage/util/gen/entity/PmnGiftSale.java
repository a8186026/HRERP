package com.goldcow.emanage.util.gen.entity;

import java.util.Date;

import com.goldcow.sframe.util.DataGridModel;
import com.goldcow.sframe.util.mybatis.BaseEntity;
/**
 * 买赠信息实体类
 * 
 * @author cyx
 * @version v1.0
 * @since 2015-10-26
 */
public class PmnGiftSale extends DataGridModel implements BaseEntity{
	private static final long serialVersionUID = 1L;
	/** 买赠信息id */
	private Integer gift_sal_id;   
	/** 编号 */
	private String gift_sal_code;
	/** 名称 */
	private String gift_sal_name;
	/** 零售价 */
	private Double gift_sal_retailPrice;
	/** 会员价 */
	private Double gift_sal_memberPrice;
	/** 提成 */
	private Double gift_sal_commission;
	/** 拼音码 */
	private String gift_sal_chn;
	/** 参与部门 */
	private String gift_sal_joinDepartment;
	/** 参与产品序号 */
	private String gift_sal_joinProductId;
	/** 开始日期 */
	private Date gift_sal_startDate;
	/** 结束日期 */
	private Date gift_sal_endDate;
	/** 说明 */
	private String gift_sal_introduction;
	/** 满额赠系数 */
	private Double gift_sal_fullGiveCoefficient;
	/** 只会员参加 */
	private Integer gift_sal_onlyMemberJoin;
	/** 产品积分 */
	private Integer gift_sal_productPoint;
	/** 活动标题 */
	private String gift_sal_activityTitle;

	public Integer getGift_sal_id(){
		return this.gift_sal_id;
	}
	public void setGift_sal_id(Integer gift_sal_id){
		this.gift_sal_id=gift_sal_id;
	}
	public String getGift_sal_code(){
		return this.gift_sal_code;
	}
	public void setGift_sal_code(String gift_sal_code){
		this.gift_sal_code=gift_sal_code;
	}
	public String getGift_sal_name(){
		return this.gift_sal_name;
	}
	public void setGift_sal_name(String gift_sal_name){
		this.gift_sal_name=gift_sal_name;
	}
	public Double getGift_sal_retailPrice(){
		return this.gift_sal_retailPrice;
	}
	public void setGift_sal_retailPrice(Double gift_sal_retailPrice){
		this.gift_sal_retailPrice=gift_sal_retailPrice;
	}
	public Double getGift_sal_memberPrice(){
		return this.gift_sal_memberPrice;
	}
	public void setGift_sal_memberPrice(Double gift_sal_memberPrice){
		this.gift_sal_memberPrice=gift_sal_memberPrice;
	}
	public Double getGift_sal_commission(){
		return this.gift_sal_commission;
	}
	public void setGift_sal_commission(Double gift_sal_commission){
		this.gift_sal_commission=gift_sal_commission;
	}
	public String getGift_sal_chn(){
		return this.gift_sal_chn;
	}
	public void setGift_sal_chn(String gift_sal_chn){
		this.gift_sal_chn=gift_sal_chn;
	}
	public String getGift_sal_joinDepartment(){
		return this.gift_sal_joinDepartment;
	}
	public void setGift_sal_joinDepartment(String gift_sal_joinDepartment){
		this.gift_sal_joinDepartment=gift_sal_joinDepartment;
	}
	public String getGift_sal_joinProductId(){
		return this.gift_sal_joinProductId;
	}
	public void setGift_sal_joinProductId(String gift_sal_joinProductId){
		this.gift_sal_joinProductId=gift_sal_joinProductId;
	}
	public Date getGift_sal_startDate(){
		return this.gift_sal_startDate;
	}
	public void setGift_sal_startDate(Date gift_sal_startDate){
		this.gift_sal_startDate=gift_sal_startDate;
	}
	public Date getGift_sal_endDate(){
		return this.gift_sal_endDate;
	}
	public void setGift_sal_endDate(Date gift_sal_endDate){
		this.gift_sal_endDate=gift_sal_endDate;
	}
	public String getGift_sal_introduction(){
		return this.gift_sal_introduction;
	}
	public void setGift_sal_introduction(String gift_sal_introduction){
		this.gift_sal_introduction=gift_sal_introduction;
	}
	public Double getGift_sal_fullGiveCoefficient(){
		return this.gift_sal_fullGiveCoefficient;
	}
	public void setGift_sal_fullGiveCoefficient(Double gift_sal_fullGiveCoefficient){
		this.gift_sal_fullGiveCoefficient=gift_sal_fullGiveCoefficient;
	}
	public Integer getGift_sal_onlyMemberJoin(){
		return this.gift_sal_onlyMemberJoin;
	}
	public void setGift_sal_onlyMemberJoin(Integer gift_sal_onlyMemberJoin){
		this.gift_sal_onlyMemberJoin=gift_sal_onlyMemberJoin;
	}
	public Integer getGift_sal_productPoint(){
		return this.gift_sal_productPoint;
	}
	public void setGift_sal_productPoint(Integer gift_sal_productPoint){
		this.gift_sal_productPoint=gift_sal_productPoint;
	}
	public String getGift_sal_activityTitle(){
		return this.gift_sal_activityTitle;
	}
	public void setGift_sal_activityTitle(String gift_sal_activityTitle){
		this.gift_sal_activityTitle=gift_sal_activityTitle;
	}

}