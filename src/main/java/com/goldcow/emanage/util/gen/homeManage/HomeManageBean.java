/*
 * 日期				修改人				版本号				修改内容
 * 2014/11/10                        刘小铭                                               1.0                                                        创建 	
 * */
package com.goldcow.emanage.util.gen.homeManage;



import java.util.Date;

import com.goldcow.sframe.util.mybatis.BaseEntity;


public class HomeManageBean implements BaseEntity{

	/**
	 * 首页显示内容实体
	 */
	private static final long serialVersionUID = 1L;
	//首页显示内容主键
	public Integer homeManageId;
	//显示类型
	public Integer homeManageType;
	//添加时间
	public Date homeManageTime;
	//是否显示
	public Integer homeManageShowType;
	//显示内容
	public String homeManageContent;
	//创建人ID
	public Integer createUser;
	//创建时间
	public Date createTime;
	//修改人ID
	public Integer lastModifyUser;
	//修改时间
	public Date lastModifyTime;
	//是否有效
	public Integer enableFlag;
/*****************************************************************************************/
	public Integer getHomeManageId() {
		return homeManageId;
	}
	public void setHomeManageId(Integer homeManageId) {
		this.homeManageId = homeManageId;
	}
	public Integer getHomeManageType() {
		return homeManageType;
	}
	public void setHomeManageType(Integer homeManageType) {
		this.homeManageType = homeManageType;
	}
	public Date getHomeManageTime() {
		return homeManageTime;
	}
	public void setHomeManageTime(Date homeManageTime) {
		this.homeManageTime = homeManageTime;
	}
	public Integer getHomeManageShowType() {
		return homeManageShowType;
	}
	public void setHomeManageShowType(Integer homeManageShowType) {
		this.homeManageShowType = homeManageShowType;
	}
	public String getHomeManageContent() {
		return homeManageContent;
	}
	public void setHomeManageContent(String homeManageContent) {
		this.homeManageContent = homeManageContent;
	}
	public Integer getCreateUser() {
		return createUser;
	}
	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getLastModifyUser() {
		return lastModifyUser;
	}
	public void setLastModifyUser(Integer lastModifyUser) {
		this.lastModifyUser = lastModifyUser;
	}
	public Date getLastModifyTime() {
		return lastModifyTime;
	}
	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}
	public Integer getEnableFlag() {
		return enableFlag;
	}
	public void setEnableFlag(Integer enableFlag) {
		this.enableFlag = enableFlag;
	}
	

	
	

}
