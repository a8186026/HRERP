package com.goldcow.emanage.basInfo.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.goldcow.emanage.util.gen.entity.BasCheck;
import com.goldcow.emanage.util.gen.entity.SupInfoManage;
import com.goldcow.sframe.util.service.BaseIService;
/**
 * 厂家档案信息
 * 
 * @author Yuanxin
 * @version v1.0
 * @since 2015-6-5
 */

public interface IBasCheckSupplyService{

	
	public BasCheck add(BasCheck bean, HttpServletRequest request);
	public List<BasCheck> lists(BasCheck bean);
	public BasCheck update(BasCheck bean, HttpServletRequest request);
	public Integer count (BasCheck bascheck);
	public BasCheck get(Integer id);
	public void delete(Integer id, HttpServletRequest request);
	
}