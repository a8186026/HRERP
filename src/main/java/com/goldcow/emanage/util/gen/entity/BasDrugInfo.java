package com.goldcow.emanage.util.gen.entity;

import java.util.Date;

import com.goldcow.sframe.util.DataGridModel;
import com.goldcow.sframe.util.mybatis.BaseEntity;

public class BasDrugInfo extends DataGridModel implements BaseEntity{
	/**
	 * 药监局信息实体类
	 * 
	 * @author gaoxiang
	 * @version v1.0
	 * @since 2015-6-30
	 */
	private static final long serialVersionUID = -7970636854771442541L;
 
	
	/** 药监库ID */
	private Integer eth__Id;
	/** 药监编号 */
	private String eth_UnitCode;
	
	private String eth_Code;
	
	private String eth_BarCode;
	/** 药监库通用名 */
	private String eth_tym;
	/** 药监库商品名 */
	private String eth_spm;
	
	private String eth_ComponentGG;
	/** 药监库规格 */
	private String eth_gg;
	/** 药监库单位 */
	private String eth_dw;
	/** 药监库剂型 */
	private String eth_Form;
	/** 药监库生产厂家 */
	private String eth_sccj;
	/** 药监库批准文号 */
	private String eth_pzwh;
	
	private String eth_orbit;
	
	private Integer eth_Status;
	
	private String eth_memo;
	
	private String eth_ISCombine;
	
	private String eth_dwNum;
	
	private Date eth_datetime;
	
	private String eth_ClientID;
	
	private String eth_Sort;

	public Integer getEth__Id() {
		return eth__Id;
	}

	public void setEth__Id(Integer eth__Id) {
		this.eth__Id = eth__Id;
	}

	public String getEth_UnitCode() {
		return eth_UnitCode;
	}

	public void setEth_UnitCode(String eth_UnitCode) {
		this.eth_UnitCode = eth_UnitCode;
	}

	public String getEth_Code() {
		return eth_Code;
	}

	public void setEth_Code(String eth_Code) {
		this.eth_Code = eth_Code;
	}

	public String getEth_BarCode() {
		return eth_BarCode;
	}

	public void setEth_BarCode(String eth_BarCode) {
		this.eth_BarCode = eth_BarCode;
	}

	public String getEth_tym() {
		return eth_tym;
	}

	public void setEth_tym(String eth_tym) {
		this.eth_tym = eth_tym;
	}

	public String getEth_spm() {
		return eth_spm;
	}

	public void setEth_spm(String eth_spm) {
		this.eth_spm = eth_spm;
	}

	public String getEth_ComponentGG() {
		return eth_ComponentGG;
	}

	public void setEth_ComponentGG(String eth_ComponentGG) {
		this.eth_ComponentGG = eth_ComponentGG;
	}

	public String getEth_gg() {
		return eth_gg;
	}

	public void setEth_gg(String eth_gg) {
		this.eth_gg = eth_gg;
	}

	public String getEth_dw() {
		return eth_dw;
	}

	public void setEth_dw(String eth_dw) {
		this.eth_dw = eth_dw;
	}

	public String getEth_Form() {
		return eth_Form;
	}

	public void setEth_Form(String eth_Form) {
		this.eth_Form = eth_Form;
	}

	public String getEth_sccj() {
		return eth_sccj;
	}

	public void setEth_sccj(String eth_sccj) {
		this.eth_sccj = eth_sccj;
	}

 
	public String getEth_pzwh() {
		return eth_pzwh;
	}

	public void setEth_pzwh(String eth_pzwh) {
		this.eth_pzwh = eth_pzwh;
	}

	public String getEth_orbit() {
		return eth_orbit;
	}

	public void setEth_orbit(String eth_orbit) {
		this.eth_orbit = eth_orbit;
	}

	public Integer getEth_Status() {
		return eth_Status;
	}

	public void setEth_Status(Integer eth_Status) {
		this.eth_Status = eth_Status;
	}

	public String getEth_memo() {
		return eth_memo;
	}

	public void setEth_memo(String eth_memo) {
		this.eth_memo = eth_memo;
	}

	public String getEth_ISCombine() {
		return eth_ISCombine;
	}

	public void setEth_ISCombine(String eth_ISCombine) {
		this.eth_ISCombine = eth_ISCombine;
	}

	public String getEth_dwNum() {
		return eth_dwNum;
	}

	public void setEth_dwNum(String eth_dwNum) {
		this.eth_dwNum = eth_dwNum;
	}

	public Date getEth_datetime() {
		return eth_datetime;
	}

	public void setEth_datetime(Date eth_datetime) {
		this.eth_datetime = eth_datetime;
	}

	public String getEth_ClientID() {
		return eth_ClientID;
	}

	public void setEth_ClientID(String eth_ClientID) {
		this.eth_ClientID = eth_ClientID;
	}

	public String getEth_Sort() {
		return eth_Sort;
	}

	public void setEth_Sort(String eth_Sort) {
		this.eth_Sort = eth_Sort;
	}
}
