package com.goldcow.emanage.basInfo.service;


import java.util.List;
import java.util.Map;

import com.goldcow.emanage.util.gen.entity.BasCheck;
import com.goldcow.emanage.util.gen.entity.BasMedicineInfo;
import com.goldcow.emanage.util.gen.entity.ProInfoManage;
import com.goldcow.sframe.util.service.BaseIService;


/**
 * 服务层接口
 * 
 * @author chenyuxuan
 * @version v1.0
 * @since 2015-06-01
 */
public interface IBasCheckProductService  extends BaseIService<BasCheck> {
	 /**
	 * 查询审批列表
	 * @param bean 查询条件
	 * @return 查询结果list
	 */	
	public List<BasCheck> getProductsCheck(BasCheck bean);
	 /**
	 * 查询所有产品
	 * @param bean 查询条件
	 * @return 查询结果list
	 */	
	public List<ProInfoManage> getProducts();
	 /**
	 * 查询已有审批条目数量
	 * @param id  产品id
	 * @return 数量
	 */	
	public Integer count (BasCheck bascheck);
}