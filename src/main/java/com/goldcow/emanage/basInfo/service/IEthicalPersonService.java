package com.goldcow.emanage.basInfo.service;

import java.util.Map;

import com.goldcow.emanage.util.gen.entity.EthicalPerson;
import com.goldcow.sframe.util.service.BaseIService;
/**
 * 处方药人员管理
 * 
 * @author wubin
 * @version v1.0
 * @since 2015-12-30
 */
public interface IEthicalPersonService extends BaseIService<EthicalPerson>{

	/**
	 * 
	 * 查询处方药人员信息-分页
	 * @param bean 查询条件
	 * @return 处方药人员信息列表
	 */
	public Map<String, Object> lists(EthicalPerson bean);
}