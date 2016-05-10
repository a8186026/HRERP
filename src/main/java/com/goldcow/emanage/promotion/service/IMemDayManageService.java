package com.goldcow.emanage.promotion.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.goldcow.emanage.util.gen.entity.MemDayManage;
import com.goldcow.emanage.util.gen.entity.PurAcceptCheck;
import com.goldcow.sframe.util.service.BaseIService;


/**
 * 服务层接口
 * 
 * @author ljx
 * @version v1.0
 * @since 
 */
public interface IMemDayManageService extends BaseIService<MemDayManage>{

	/**
	 * 查询页面
	 * 
	 * @param bean 查询条件
	 * @return 页面列表
	 */
	public Map<String, Object> list(MemDayManage bean);
	/**
	 * 查询今天是否是会员日
	 * 
	 * @return 会员日信息
	 */
	public MemDayManage isMemDay(Integer dept_id);
	String savePriority(String data, HttpServletRequest request);
	
}