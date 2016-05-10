package com.goldcow.emanage.util.gen.entity.valueObject.sysScreening;

import java.util.List;

import com.goldcow.sframe.util.DataGridModel;
import com.goldcow.sframe.util.mybatis.BaseEntity;

/**
 * 数据筛选
 * 
 * @author wubin
 * @version v1.0
 * @since 2015-11-3
 */
public class SysScreeningVO extends DataGridModel implements BaseEntity{
	private static final long serialVersionUID = 1L;
	/** 字段 */
	private String field;
	/** 字段名称 */
	private String fieldName;
	/** 字段类型 */
	private String fieldType;
	/** 类名 */
	private String className;
	/** 关键字 */
	private String keyWord;
	/** 连接方式 */
	private String contact;
	/** 比较关系 */
	private String compare_realation;
	/** 过滤指定IDs */
	private List<Integer> ids;
	
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getFieldType() {
		return fieldType;
	}
	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getCompare_realation() {
		return compare_realation;
	}
	public void setCompare_realation(String compare_realation) {
		this.compare_realation = compare_realation;
	}
	public List<Integer> getIds() {
		return ids;
	}
	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}
}
