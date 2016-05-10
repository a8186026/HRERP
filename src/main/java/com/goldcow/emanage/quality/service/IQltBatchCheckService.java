package com.goldcow.emanage.quality.service;

import java.util.List;
import java.util.Map;

import com.goldcow.emanage.util.gen.entity.QltBatchCheck;
import com.goldcow.emanage.util.gen.entity.valueObject.quality.QltBatchCheckVO;
import com.goldcow.sframe.util.service.BaseIService;
/**
 * 重点养护批次信息实体类
 * 
 * @author gaoxiang
 * @since 2015-11-17
 */
public interface IQltBatchCheckService extends BaseIService<QltBatchCheck>{

	 /**
	 * 查询重点养护批次信息-分页
	 * @param bean 查询条件
	 * @return 重点养护批次信息列表
	 */
	public Map<String, Object> list(QltBatchCheckVO bean);
	
	public QltBatchCheckVO getById(Integer id);
	
	public List<QltBatchCheckVO> listStock(QltBatchCheckVO bean , Integer product_id);
	 
}