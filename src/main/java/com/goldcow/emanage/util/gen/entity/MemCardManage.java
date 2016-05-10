package com.goldcow.emanage.util.gen.entity;

import java.util.Date;

import com.goldcow.sframe.util.DataGridModel;
import com.goldcow.sframe.util.mybatis.BaseEntity;

/**
 * 会员卡实体类
 * 
 * @author wubin
 * @version v1.0
 * @since 2015-6-11
 */

public class MemCardManage extends DataGridModel implements BaseEntity{
	private static final long serialVersionUID = 1L;
	/** 会员卡ID */
	private Integer mem_card_id;
	/** 会员卡类别 */
	private String mem_card_type;
	/** 会员卡号 */
	private String mem_card_number;
	/** 会员卡姓名 */
	private String mem_card_name;
	/** 会员卡拼音码 */
	private String mem_card_chn;
	/** 会员卡性别 */
	private Integer mem_card_gender;
	/** 会员卡累计消费 */
	private Float mem_card_allCost;
	/** 会员卡积分消费 */
	private Integer mem_card_pointCost;
	/** 记分金额(多少钱一积分)废弃，属性转移到产品表中 */
	private Float mem_card_acountMoney;
	/** 会员卡初始金额 */
	private Float mem_card_startMoney;
	/** 会员卡积分 */
	private Integer mem_card_point;
	/** 积分抵现(1积分抵多少钱）*/
	private Float mem_point_toMoney;
	/** 会员卡扣率 */
	private Double mem_card_discount;
	/** 会员卡最低扣率 */
	private Double mem_lowest_discount;
	/** 会员卡条码 */
	private String mem_card_barCode;
	/** 会员卡电话 */
	private String mem_card_tel;
	/** 会员卡手机 */
	private String mem_card_cellPhone;
	/** 会员卡地址 */
	private String mem_card_address;
	/** 会员卡邮编 */
	private String mem_card_postCode;
	/** 会员卡身份证 */
	private String mem_card_idCard;
	/** 会员卡申请日期 */
	private Date mem_card_applyDate;
	/** 会员卡失效日期 */
	private Date mem_card_failedDate;
	/** 会员卡业务1 */
	private String mem_card_business1;
	/** 会员卡业务2 */
	private String mem_card_business2;
	/** 会员卡业务3 */
	private String mem_card_business3;
	/** 会员卡已兑积分 */
	private Integer mem_card_usedPoint;
	/** 会员卡原始积分 */
	private Integer mem_card_originalPoint;
	/** 会员卡简介 */
	private String mem_card_indroduction;
	/** 会员卡所属部门 */
	private String mem_card_dept;
	/** 会员卡代金卡 */
	private Integer mem_card_giftCard;
	/** 会员卡追加积分 */
	private Integer mem_card_addPoint;
	/** 会员卡病史 */
	private String mem_card_history;
	/** 会员卡持卡 */
	private String mem_card_holder;
	/** 会员卡单位 */
	private String mem_card_unit;
	/** 会员卡证件类型 */
	private String mem_card_mold;
	/** 会员卡证件号码 */
	private String mem_card_credentialNumber;
	/** 会员卡过敏史 */
	private String mem_card_allergicHistory;
	/** 会员卡备注 */
	private String mem_card_remark;
	/** 会员卡修改次数 */
	private Integer mem_card_modifyNumber;
	/** 会员卡年龄 */
	private Integer mem_card_age;
	/** 会员卡上次代金时间 */
	private Date mem_card_lastGiftTime;
	/** 状态(0启用1停用9删除) */
	private Integer status;
	/** 创建人 */
	private Integer create_user;
	/** 创建时间 */
	private Date create_time;
	/** 最后修改人 */
	private Integer last_modify_user;
	/** 最后修改时间 */
	private Date last_modify_time;
	/** 会员卡生日 */
	private Date mem_card_birthday;
	public Integer getMem_card_id() {
		return mem_card_id;
	}
	public void setMem_card_id(Integer mem_card_id) {
		this.mem_card_id = mem_card_id;
	}
	public String getMem_card_type() {
		return mem_card_type;
	}
	public void setMem_card_type(String mem_card_type) {
		this.mem_card_type = mem_card_type;
	}
	public String getMem_card_number() {
		return mem_card_number;
	}
	public void setMem_card_number(String mem_card_number) {
		this.mem_card_number = mem_card_number;
	}
	public String getMem_card_name() {
		return mem_card_name;
	}
	public void setMem_card_name(String mem_card_name) {
		this.mem_card_name = mem_card_name;
	}
	public String getMem_card_chn() {
		return mem_card_chn;
	}
	public void setMem_card_chn(String mem_card_chn) {
		this.mem_card_chn = mem_card_chn;
	}
	public Integer getMem_card_gender() {
		return mem_card_gender;
	}
	public void setMem_card_gender(Integer mem_card_gender) {
		this.mem_card_gender = mem_card_gender;
	}
	public Float getMem_card_acountMoney() {
		return mem_card_acountMoney;
	}
	public void setMem_card_acountMoney(Float mem_card_acountMoney) {
		this.mem_card_acountMoney = mem_card_acountMoney;
	}
	public Integer getMem_card_point() {
		return mem_card_point;
	}
	public void setMem_card_point(Integer mem_card_point) {
		this.mem_card_point = mem_card_point;
	}
	public Float getMem_point_toMoney() {
		return mem_point_toMoney;
	}
	public void setMem_point_toMoney(Float mem_point_toMoney) {
		this.mem_point_toMoney = mem_point_toMoney;
	}
	public Double getMem_card_discount() {
		return mem_card_discount;
	}
	public void setMem_card_discount(Double mem_card_discount) {
		this.mem_card_discount = mem_card_discount;
	}
	public Double getMem_lowest_discount() {
		return mem_lowest_discount;
	}
	public void setMem_lowest_discount(Double mem_lowest_discount) {
		this.mem_lowest_discount = mem_lowest_discount;
	}
	public String getMem_card_barCode() {
		return mem_card_barCode;
	}
	public void setMem_card_barCode(String mem_card_barCode) {
		this.mem_card_barCode = mem_card_barCode;
	}
	public String getMem_card_tel() {
		return mem_card_tel;
	}
	public void setMem_card_tel(String mem_card_tel) {
		this.mem_card_tel = mem_card_tel;
	}
	public String getMem_card_cellPhone() {
		return mem_card_cellPhone;
	}
	public void setMem_card_cellPhone(String mem_card_cellPhone) {
		this.mem_card_cellPhone = mem_card_cellPhone;
	}
	public String getMem_card_address() {
		return mem_card_address;
	}
	public void setMem_card_address(String mem_card_address) {
		this.mem_card_address = mem_card_address;
	}
	public String getMem_card_postCode() {
		return mem_card_postCode;
	}
	public void setMem_card_postCode(String mem_card_postCode) {
		this.mem_card_postCode = mem_card_postCode;
	}
	public String getMem_card_idCard() {
		return mem_card_idCard;
	}
	public void setMem_card_idCard(String mem_card_idCard) {
		this.mem_card_idCard = mem_card_idCard;
	}
	public Date getMem_card_applyDate() {
		return mem_card_applyDate;
	}
	public void setMem_card_applyDate(Date mem_card_applyDate) {
		this.mem_card_applyDate = mem_card_applyDate;
	}
	public Date getMem_card_failedDate() {
		return mem_card_failedDate;
	}
	public void setMem_card_failedDate(Date mem_card_failedDate) {
		this.mem_card_failedDate = mem_card_failedDate;
	}
	public String getMem_card_business1() {
		return mem_card_business1;
	}
	public void setMem_card_business1(String mem_card_business1) {
		this.mem_card_business1 = mem_card_business1;
	}
	public String getMem_card_business2() {
		return mem_card_business2;
	}
	public void setMem_card_business2(String mem_card_business2) {
		this.mem_card_business2 = mem_card_business2;
	}
	public String getMem_card_business3() {
		return mem_card_business3;
	}
	public void setMem_card_business3(String mem_card_business3) {
		this.mem_card_business3 = mem_card_business3;
	}
	public Integer getMem_card_usedPoint() {
		return mem_card_usedPoint;
	}
	public void setMem_card_usedPoint(Integer mem_card_usedPoint) {
		this.mem_card_usedPoint = mem_card_usedPoint;
	}
	public Integer getMem_card_originalPoint() {
		return mem_card_originalPoint;
	}
	public void setMem_card_originalPoint(Integer mem_card_originalPoint) {
		this.mem_card_originalPoint = mem_card_originalPoint;
	}
	public String getMem_card_indroduction() {
		return mem_card_indroduction;
	}
	public void setMem_card_indroduction(String mem_card_indroduction) {
		this.mem_card_indroduction = mem_card_indroduction;
	}
	public String getMem_card_dept() {
		return mem_card_dept;
	}
	public void setMem_card_dept(String mem_card_dept) {
		this.mem_card_dept = mem_card_dept;
	}
	public Integer getMem_card_giftCard() {
		return mem_card_giftCard;
	}
	public void setMem_card_giftCard(Integer mem_card_giftCard) {
		this.mem_card_giftCard = mem_card_giftCard;
	}
	public Integer getMem_card_addPoint() {
		return mem_card_addPoint;
	}
	public void setMem_card_addPoint(Integer mem_card_addPoint) {
		this.mem_card_addPoint = mem_card_addPoint;
	}
	public String getMem_card_history() {
		return mem_card_history;
	}
	public void setMem_card_history(String mem_card_history) {
		this.mem_card_history = mem_card_history;
	}
	public String getMem_card_holder() {
		return mem_card_holder;
	}
	public void setMem_card_holder(String mem_card_holder) {
		this.mem_card_holder = mem_card_holder;
	}
	public String getMem_card_unit() {
		return mem_card_unit;
	}
	public void setMem_card_unit(String mem_card_unit) {
		this.mem_card_unit = mem_card_unit;
	}
	public String getMem_card_mold() {
		return mem_card_mold;
	}
	public void setMem_card_mold(String mem_card_mold) {
		this.mem_card_mold = mem_card_mold;
	}
	public String getMem_card_credentialNumber() {
		return mem_card_credentialNumber;
	}
	public void setMem_card_credentialNumber(String mem_card_credentialNumber) {
		this.mem_card_credentialNumber = mem_card_credentialNumber;
	}
	public String getMem_card_allergicHistory() {
		return mem_card_allergicHistory;
	}
	public void setMem_card_allergicHistory(String mem_card_allergicHistory) {
		this.mem_card_allergicHistory = mem_card_allergicHistory;
	}
	public String getMem_card_remark() {
		return mem_card_remark;
	}
	public void setMem_card_remark(String mem_card_remark) {
		this.mem_card_remark = mem_card_remark;
	}
	public Integer getMem_card_modifyNumber() {
		return mem_card_modifyNumber;
	}
	public void setMem_card_modifyNumber(Integer mem_card_modifyNumber) {
		this.mem_card_modifyNumber = mem_card_modifyNumber;
	}
	public Integer getMem_card_age() {
		return mem_card_age;
	}
	public void setMem_card_age(Integer mem_card_age) {
		this.mem_card_age = mem_card_age;
	}
	public Date getMem_card_lastGiftTime() {
		return mem_card_lastGiftTime;
	}
	public void setMem_card_lastGiftTime(Date mem_card_lastGiftTime) {
		this.mem_card_lastGiftTime = mem_card_lastGiftTime;
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
	public Date getMem_card_birthday() {
		return mem_card_birthday;
	}
	public void setMem_card_birthday(Date mem_card_birthday) {
		this.mem_card_birthday = mem_card_birthday;
	}
	public Float getMem_card_allCost() {
		return mem_card_allCost;
	}
	public void setMem_card_allCost(Float mem_card_allCost) {
		this.mem_card_allCost = mem_card_allCost;
	}
	public Integer getMem_card_pointCost() {
		return mem_card_pointCost;
	}
	public void setMem_card_pointCost(Integer mem_card_pointCost) {
		this.mem_card_pointCost = mem_card_pointCost;
	}
	public Float getMem_card_startMoney() {
		return mem_card_startMoney;
	}
	public void setMem_card_startMoney(Float mem_card_startMoney) {
		this.mem_card_startMoney = mem_card_startMoney;
	}
	
}