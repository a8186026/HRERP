package com.goldcow.emanage.util.gen.entity;

import java.util.Date;

import com.goldcow.sframe.util.DataGridModel;
import com.goldcow.sframe.util.mybatis.BaseEntity;
/**
 * 赠品信息实体类
 * 
 * @author cyx
 * @version v1.0
 * @since 2015-10-26
 */
public class PmnGiftInfo extends DataGridModel implements BaseEntity{
	private static final long serialVersionUID = 1L;
	/** 赠品信息id */
	private Integer gift_id;
	/** 品种序号 */
	private Integer gift_varietyId;
	/** 产品序号 */
	private Integer gift_productId;
	/** 数量 */
	private Double gift_amount;
	/** 零售价 */
	private Double gift_retailPrice;
	/** 会员价 */
	private Double gift_memberPrice;
	/** 提成 */
	private Double gift_commission;
	/** 特殊批次 */
	private Integer gift_specialBatch;
	/** 买赠信息id */
	private Integer gift_sal_id; 

	public Integer getGift_sal_id() {
		return gift_sal_id;
	}
	public void setGift_sal_id(Integer gift_sal_id) {
		this.gift_sal_id = gift_sal_id;
	}
	public Integer getGift_id(){
		return this.gift_id;
	}
	public void setGift_id(Integer gift_id){
		this.gift_id=gift_id;
	}
	public Integer getGift_varietyId(){
		return this.gift_varietyId;
	}
	public void setGift_varietyId(Integer gift_varietyId){
		this.gift_varietyId=gift_varietyId;
	}
	public Integer getGift_productId(){
		return this.gift_productId;
	}
	public void setGift_productId(Integer gift_productId){
		this.gift_productId=gift_productId;
	}
	public Double getGift_amount(){
		return this.gift_amount;
	}
	public void setGift_amount(Double gift_amount){
		this.gift_amount=gift_amount;
	}
	public Double getGift_retailPrice(){
		return this.gift_retailPrice;
	}
	public void setGift_retailPrice(Double gift_retailPrice){
		this.gift_retailPrice=gift_retailPrice;
	}
	public Double getGift_memberPrice(){
		return this.gift_memberPrice;
	}
	public void setGift_memberPrice(Double gift_memberPrice){
		this.gift_memberPrice=gift_memberPrice;
	}
	public Double getGift_commission(){
		return this.gift_commission;
	}
	public void setGift_commission(Double gift_commission){
		this.gift_commission=gift_commission;
	}
	public Integer getGift_specialBatch(){
		return this.gift_specialBatch;
	}
	public void setGift_specialBatch(Integer gift_specialBatch){
		this.gift_specialBatch=gift_specialBatch;
	}

}