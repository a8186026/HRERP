package com.goldcow.emanage.basInfo.service;

import java.util.Map;

import com.goldcow.emanage.util.gen.entity.CompanyInfo;
import com.goldcow.sframe.util.service.BaseIService;
/**
 * 公司信息
 * 
 * @author wubin
 * @version v1.0
 * @since 2015-1-4
 */
public interface ICompanyInfoService extends BaseIService<CompanyInfo>{

	/**
	 * 
	 * 查询公司信息-分页
	 * @param bean 查询条件
	 * @return 公司信息列表
	 */
	public Map<String, Object> lists(CompanyInfo bean);
}