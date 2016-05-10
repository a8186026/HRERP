package com.goldcow.emanage.basInfo.service;

import java.util.List;
import java.util.Map;

import com.goldcow.emanage.util.gen.entity.BasLicenseInfo;
import com.goldcow.sframe.util.service.BaseIService;
/**
 * 证照信息信息
 * 
 * @author gaoxiang
 * @version v1.0
 * @since 2015-6-30
 */
public interface IBasLicenseInfoService extends BaseIService<BasLicenseInfo>{

	 /**
	 * 查询证照信息-分页
	 * @param bean 查询条件
	 * @return 证照信息列表
	 */
	public Map<String, Object> lists(BasLicenseInfo bean);

}