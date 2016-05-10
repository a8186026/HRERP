package com.goldcow.emanage.util.gen.entity;

import com.goldcow.sframe.util.mybatis.BaseEntity;
/**
 * 页面控件权限控制的xml的实体类
 * 
 * @author Yao
 * @version v1.0
 * @since 2015-05-19
 */
public class SysCtrlPermissionCtrl implements BaseEntity{
	private static final long serialVersionUID = -3992263243152679571L;
	/** 控件ID */
	private String CtrlID;
	/** 控件所在控件组的外层DIV的ID */
	private String CtrlDivID;
	/** 控件类别 */
	private String CtrlType;
	/** 控件是否可写  */
	private String ReadOnly;
	/** 控件是否可见  */
	private String Hidden;
	/** 控件是否必填 */
	private String Written;
	
	
	/**
	 * 将固定格式String[]转换为对象
	 * @param ctrl 固定格式的String*/
	public Boolean createCtrl(String[] ctrl){
		try{
			this.setCtrlID(ctrl[0]);
			this.setCtrlDivID(ctrl[1]);
			this.setCtrlType(ctrl[2]);
			this.setReadOnly(ctrl[3]);
			this.setHidden(ctrl[4]);
			this.setWritten(ctrl[5]);
		}catch(Exception e){
			System.out.println("控件转换异常："+e);
			return false;
		}
		return true;
	}
	
	
	
	
	public String getCtrlID() {
		return CtrlID;
	}
	public void setCtrlID(String ctrlID) {
		CtrlID = ctrlID;
	}
	public String getCtrlDivID() {
		return CtrlDivID;
	}
	public void setCtrlDivID(String ctrlDivID) {
		CtrlDivID = ctrlDivID;
	}
	public String getReadOnly() {
		return ReadOnly;
	}
	public void setReadOnly(String readOnly) {
		ReadOnly = readOnly;
	}
	public String getHidden() {
		return Hidden;
	}
	public void setHidden(String hidden) {
		Hidden = hidden;
	}
	public String getWritten() {
		return Written;
	}
	public void setWritten(String written) {
		Written = written;
	}
	public String getCtrlType() {
		return CtrlType;
	}
	public void setCtrlType(String ctrlType) {
		CtrlType = ctrlType;
	}
	
}
