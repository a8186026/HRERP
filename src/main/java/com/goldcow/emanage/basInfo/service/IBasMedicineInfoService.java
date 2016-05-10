package com.goldcow.emanage.basInfo.service;


import java.util.Map;
import com.goldcow.emanage.util.gen.entity.BasMedicineInfo;
import com.goldcow.sframe.util.service.BaseIService;


/**
 * 药品信息
 * 
 * @author chenyuxuan
 * @version v1.0
 * @since 2015-06-01
 */
public interface IBasMedicineInfoService  extends BaseIService<BasMedicineInfo> {
	
	 /**
	 * 查询药品信息-分页
	 * @param bean 查询条件
	 * @return 药品信息列表
	 */
	public Map<String, Object> list(BasMedicineInfo bean);
	
	
	/**
	 * 查询厂家编码最大值
	 * @param null
	 * @return 厂家编码最大值
	 */
	public String getMaxMedicineCode();

}