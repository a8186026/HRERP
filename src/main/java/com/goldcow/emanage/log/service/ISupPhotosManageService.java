package com.goldcow.emanage.log.service;

import java.util.List;

import com.goldcow.emanage.util.gen.entity.SupInfoManage;
import com.goldcow.sframe.util.service.BaseIService;

public interface ISupPhotosManageService extends BaseIService<SupInfoManage> {
	
	/** 分页查询 */
	public List<SupInfoManage> list(SupInfoManage bean);
	

}
