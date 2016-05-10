package com.goldcow.sframe.util;

public class DataGridModel implements java.io.Serializable {

	private static final long serialVersionUID = 7232798260610351343L;
	private int page; // 当前页,名字必须为page
	private int rows; // 每页大小,名字必须为rows
	private String sort; // 排序字段
	private String order; // 排序规则

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public String getSort() {
		if (sort != null) {
			String[] sorts = sort.split("\\s");
			if (sorts.length > 1) {
				return sorts[0];
			} else if (sorts.length == 0) {
				return null;
			}
		}
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public int getOffset() {
		return (page - 1) * rows;
	}
}