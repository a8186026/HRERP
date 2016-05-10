package com.goldcow.emanage.modi.vo;

/**
 * HRERP新闻公告实体类
 * 
 * @author chengrongchang
 * @version 1.0.0
 */
public class OaNewsBulletinVO {

	/** 新闻公告ID */
	private Integer news_id;
	/** 新闻公告标题 */
	private String news_title;
	/** 新闻公告日期 */
	private String news_date;
	/** 新闻公告正文 */
	private String news_content;
	/** 发布人 */
	private String create_person;
	/** 发布部门 */
	private String create_person_dept;

	public Integer getNews_id() {
		return news_id;
	}

	public void setNews_id(Integer news_id) {
		this.news_id = news_id;
	}

	public String getNews_title() {
		return news_title;
	}

	public void setNews_title(String news_title) {
		this.news_title = news_title;
	}

	public String getNews_date() {
		return news_date;
	}

	public void setNews_date(String news_date) {
		this.news_date = news_date;
	}

	public String getNews_content() {
		return news_content;
	}

	public void setNews_content(String news_content) {
		this.news_content = news_content;
	}

	public String getCreate_person() {
		return create_person;
	}

	public void setCreate_person(String create_person) {
		this.create_person = create_person;
	}

	public String getCreate_person_dept() {
		return create_person_dept;
	}

	public void setCreate_person_dept(String create_person_dept) {
		this.create_person_dept = create_person_dept;
	}
}