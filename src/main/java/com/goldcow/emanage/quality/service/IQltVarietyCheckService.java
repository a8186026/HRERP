package com.goldcow.emanage.quality.service;

import java.util.Map;

import com.goldcow.emanage.util.gen.entity.QltVarietyCheck;
import com.goldcow.emanage.util.gen.entity.valueObject.quality.QltVarietyCheckVO;
import com.goldcow.sframe.util.service.BaseIService;
/**
 * 重点养护品种信息实体类
 * 
 * @author gaoxiang
 * @since 2015-11-13
 */
public interface IQltVarietyCheckService extends BaseIService<QltVarietyCheck>{

	 /**
	 * 查询重点养护品种信息-分页
	 * @param bean 查询条件
	 * @return 重点养护品种信息列表
	 */
	public Map<String, Object> list(QltVarietyCheckVO bean);
	
	public QltVarietyCheckVO getById(Integer id);
	 
}