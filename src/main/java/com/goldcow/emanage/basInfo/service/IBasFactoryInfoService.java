package com.goldcow.emanage.basInfo.service;

import java.util.List;
import java.util.Map;

import com.goldcow.emanage.util.gen.entity.BasFactoryInfo;
import com.goldcow.sframe.util.service.BaseIService;
/**
 * 厂家档案信息
 * 
 * @author gaoxiang
 * @version v1.0
 * @since 2015-6-2
 */
public interface IBasFactoryInfoService extends BaseIService<BasFactoryInfo>{

	 /**
	 * 查询厂家档案信息-分页
	 * @param bean 查询条件
	 * @return 厂家档案信息列表
	 */
	public Map<String, Object> list(BasFactoryInfo bean);
	
	 /**
	 * 查询厂家编码最大值
	 * @param null
	 * @return 厂家编码最大值
	 */
	public String getMaxMedicineCode();

	/**
	 * 检验添加过程中的厂家编号是否是重复的
	 * 
	 * @param factory_code 厂家编码
	 * 
	 * @author RiverYao
	 * @since 2015-06-10
	 * */
	public Boolean checkFactoryCode(String factory_code);
}