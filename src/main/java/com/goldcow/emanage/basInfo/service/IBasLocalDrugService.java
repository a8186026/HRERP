package com.goldcow.emanage.basInfo.service;

import java.util.Map;

import com.goldcow.emanage.util.gen.entity.TCatalog;
import com.goldcow.sframe.util.service.BaseIService;
/**
 * 药监局药品信息
 * 
 * @author wangqi
 * @version v1.0
 * @since 2015-7-9
 */
public interface IBasLocalDrugService extends BaseIService<TCatalog>{

	 /**
	 * 查询药监局药品信息-分页
	 * @param bean 查询条件
	 * @return 药监局药品信息列表
	 */
	public Map<String, Object> list(TCatalog bean);

}