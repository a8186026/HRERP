package com.goldcow.emanage.supply.service;

import java.util.List;
import java.util.Map;

import com.goldcow.emanage.util.gen.entity.SupInfoManage;
import com.goldcow.sframe.util.service.BaseIService;
/**
 * 供方客户信息
 * 
 * @author wangqi
 * @version v1.0
 * @since 2015-6-3
 */
public interface ISupInfoManageService extends BaseIService<SupInfoManage>{

	/**
	 * 
	 * 查询供方客户信息-分页
	 * @param bean 查询条件
	 * @return 供方客户信息列表
	 */
	public Map<String, Object> list(SupInfoManage bean);
	
	public List<SupInfoManage> lists(SupInfoManage bean);
}