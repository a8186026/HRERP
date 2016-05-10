package com.goldcow.emanage.product.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.goldcow.emanage.util.gen.entity.ProPriceTag;
import com.goldcow.sframe.util.service.BaseIService;


/**
 * 服务层接口
 * 
 * @author cyx
 * @version v1.0
 * @since 2015-1-7
 */
public interface IProPriceTagService extends BaseIService<ProPriceTag>{
	
	/**
	 * 查询价签打印申请-分页
	 * @param bean 查询条件
	 * @return 价签打印申请列表
	 */
	public Map<String, Object> list(ProPriceTag bean,Date start_time,Date end_time, HttpServletRequest request);
	
	/**
	 * 查询指定品种
	 * @param bean 查询条件
	 * @return 零售订单批次信息列表
	 */
	public List<ProPriceTag> querySpecifyProduct(Date start_time,Date end_time, Integer department_id, Integer medinsuvariety);
	

}