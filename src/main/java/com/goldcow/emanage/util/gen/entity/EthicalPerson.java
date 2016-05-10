package com.goldcow.emanage.util.gen.entity;

import java.util.Date;

import com.goldcow.sframe.util.DataGridModel;
import com.goldcow.sframe.util.mybatis.BaseEntity;

/**
 * @author wubin
 * @since 2015-12-30
 */

public class EthicalPerson extends DataGridModel implements BaseEntity{

	private static final long serialVersionUID = 1L;

	/**  处方药人员ID */
	private Integer ethical_personId;
	/**  处方药人员编码 */
	private String ethical_personCode;
	/**  处方药人员姓名 */
	private String ethical_personName;
	/**  处方药人员密码 */
	private String ethical_personPassword;
	/**  状态 */
	private Integer status;
	/**  创建时间 */
	private Date create_time;
	/**  创建人 */
	private Integer create_user;
	/**  最后修改时间  */
	private Date last_modify_time;
	/**  创建人 */
	private Integer last_modify_user;
	
	public Integer getEthical_personId() {
		return ethical_personId;
	}
	public void setEthical_personId(Integer ethical_personId) {
		this.ethical_personId = ethical_personId;
	}
	public String getEthical_personCode() {
		return ethical_personCode;
	}
	public void setEthical_personCode(String ethical_personCode) {
		this.ethical_personCode = ethical_personCode;
	}
	public String getEthical_personName() {
		return ethical_personName;
	}
	public void setEthical_personName(String ethical_personName) {
		this.ethical_personName = ethical_personName;
	}
	public String getEthical_personPassword() {
		return ethical_personPassword;
	}
	public void setEthical_personPassword(String ethical_personPassword) {
		this.ethical_personPassword = ethical_personPassword;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Integer getCreate_user() {
		return create_user;
	}
	public void setCreate_user(Integer create_user) {
		this.create_user = create_user;
	}
	public Date getLast_modify_time() {
		return last_modify_time;
	}
	public void setLast_modify_time(Date last_modify_time) {
		this.last_modify_time = last_modify_time;
	}
	public Integer getLast_modify_user() {
		return last_modify_user;
	}
	public void setLast_modify_user(Integer last_modify_user) {
		this.last_modify_user = last_modify_user;
	}
}
