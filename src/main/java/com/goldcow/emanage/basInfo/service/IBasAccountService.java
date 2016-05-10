package com.goldcow.emanage.basInfo.service;

import java.util.List;
import java.util.Map;

import com.goldcow.emanage.util.gen.entity.BasAccount;
import com.goldcow.emanage.util.gen.entity.SupInfoManage;
import com.goldcow.sframe.util.service.BaseIService;
/**
 * 财务信息
 * 
 * @author wangqi
 * @version v1.0
 * @since 2015-7-1
 */
public interface IBasAccountService extends BaseIService<BasAccount>{

	/**
	 * 
	 * 查询供方客户信息-分页
	 * @param bean 查询条件
	 * @return 供方客户信息列表
	 */
	public Map<String, Object> lists(BasAccount bean);
}