package com.goldcow.emanage.basInfo.service;


import java.util.List;

import com.goldcow.emanage.util.gen.entity.BasCheck;
import com.goldcow.emanage.util.gen.entity.SalInfoManage;
import com.goldcow.sframe.util.service.BaseIService;


/**
 * 服务层接口
 * 
 * @author wangqi
 * @version v1.0
 * @since 2015-07-14
 */
public interface IBasCheckSaleService  extends BaseIService<BasCheck> {
	 /**
	 * 查询审批列表
	 * @param bean 查询条件
	 * @return 查询结果list
	 */	
	public List<BasCheck> getSalesCheck(BasCheck bean);
	 /**
	 * 查询所有销方
	 * @param bean 查询条件
	 * @return 查询结果list
	 */	
	public List<SalInfoManage> getSales();
	 /**
	 * 查询已有审批条目数量
	 * @param id  销方id
	 * @return 数量
	 */	
	public Integer count (BasCheck bascheck);
}