package com.goldcow.emanage.util.gen.entity.valueObject.SupInfoManage;

import com.goldcow.emanage.util.gen.entity.SupInfoManage;

//YuechenWANG
public class SupInfoManageVO extends SupInfoManage {
	private static final long serialVersionUID = 1L;
	
	int refund_register;
	int refund_payCheck;
	
	
	public int getRefund_register() {
		return refund_register;
	}
	public void setRefund_register(int refund_register) {
		this.refund_register = refund_register;
	}
	public int getRefund_payCheck() {
		return refund_payCheck;
	}
	public void setRefund_payCheck(int refund_payCheck) {
		this.refund_payCheck = refund_payCheck;
	}
	@Override
	public String toString() {
		return "SupInfoManageVO [refund_register=" + refund_register
				+ ", refund_payCheck=" + refund_payCheck + "]";
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
