package com.goldcow.emanage.accept.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.goldcow.emanage.util.gen.entity.PurAcceptReject;
import com.goldcow.sframe.util.service.BaseIService;
/**
 * 收货拒收处理
 * 
 * @author zhanxiaotong
 * @since15-11-27
 * @version v1.0
 */
public interface IPurAcceptRejectService extends BaseIService<PurAcceptReject>{

	/**
	 * 
	 * 查询供方客户信息-分页
	 * @param bean 查询条件
	 * @return 供方客户信息列表
	 */
	public Map<String, Object> lists(PurAcceptReject bean);

	boolean add(String data, HttpServletRequest request);

	public void submit(Integer reject_id, HttpServletRequest request);

	String getMaxRejectTicket();
}