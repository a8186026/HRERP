package com.goldcow.emanage.modi.vo;

/**
 * HRERP消息通知实体类
 * 
 * @author chengrongchang
 * @version 1.0.0
 */
public class OaMessageVo {
	/**
	 * 通知标题
	 */
	private String message_title;
	/**
	 * 通知标题通知链接
	 */
	private String message_link;
	/**
	 * 通知标题通知日期
	 */
	private String message_date;

	public String getMessage_title() {
		return message_title;
	}

	public void setMessage_title(String message_title) {
		this.message_title = message_title;
	}

	public String getMessage_link() {
		return message_link;
	}

	public void setMessage_link(String message_link) {
		this.message_link = message_link;
	}

	public String getMessage_date() {
		return message_date;
	}

	public void setMessage_date(String message_date) {
		this.message_date = message_date;
	}
}