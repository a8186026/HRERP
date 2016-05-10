package com.goldcow.emanage.util.gen.entity.valueObject.MemCard;

import com.goldcow.sframe.util.DataGridModel;
import com.goldcow.sframe.util.mybatis.BaseEntity;

/**
 * 批量添加会员卡
 * 
 * @author wubin
 * @version v1.0
 * @since 2015-6-23
 */
public class MemCardVO extends DataGridModel implements BaseEntity{
	private static final long serialVersionUID = -7970636854771442541L;
	/** 会员卡类型 */
	private String mem_card_type;
	/** 会员卡初始姓名 */
	private String mem_card_name;
	/** 会员卡初始积分 */
	private Integer mem_card_startPoint;
	/** 会员卡号标识*/
	private String mem_card_identify;
	/** 会员卡起始卡号 */
	private String mem_card_start;
	/** 会员卡生成类型 */
	private Integer type;
	/** 会员卡结束卡号或数量 */
	private String mem_card_endOrNumber;
	public String getMem_card_type() {
		return mem_card_type;
	}
	public void setMem_card_type(String mem_card_type) {
		this.mem_card_type = mem_card_type;
	}
	public String getMem_card_name() {
		return mem_card_name;
	}
	public void setMem_card_name(String mem_card_name) {
		this.mem_card_name = mem_card_name;
	}
	public Integer getMem_card_startPoint() {
		return mem_card_startPoint;
	}
	public void setMem_card_startPoint(Integer mem_card_startPoint) {
		this.mem_card_startPoint = mem_card_startPoint;
	}
	public String getMem_card_identify() {
		return mem_card_identify;
	}
	public void setMem_card_identify(String mem_card_identify) {
		this.mem_card_identify = mem_card_identify;
	}
	public String getMem_card_start() {
		return mem_card_start;
	}
	public void setMem_card_start(String mem_card_start) {
		this.mem_card_start = mem_card_start;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getMem_card_endOrNumber() {
		return mem_card_endOrNumber;
	}
	public void setMem_card_endOrNumber(String mem_card_endOrNumber) {
		this.mem_card_endOrNumber = mem_card_endOrNumber;
	}
	
}
