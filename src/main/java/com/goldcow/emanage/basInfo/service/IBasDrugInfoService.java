package com.goldcow.emanage.basInfo.service;

import java.util.Map;

import com.goldcow.emanage.util.gen.entity.BasDrugInfo;
import com.goldcow.sframe.util.service.BaseIService;
/**
 * 药监局药品信息
 * 
 * @author gaoxiang
 * @version v1.0
 * @since 2015-7-7
 */
public interface IBasDrugInfoService extends BaseIService<BasDrugInfo>{

	 /**
	 * 查询药监局药品信息-分页
	 * @param bean 查询条件
	 * @return 药监局药品信息列表
	 */
	public Map<String, Object> list(BasDrugInfo bean);

}