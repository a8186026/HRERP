package com.goldcow.emanage.member.service;


import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.goldcow.emanage.util.gen.entity.MemCardManage;
import com.goldcow.emanage.util.gen.entity.valueObject.MemCard.MemCardVO;
import com.goldcow.emanage.util.gen.entity.valueObject.retail.RetailBatchVO;
import com.goldcow.sframe.util.service.BaseIService;


/**
 * 服务层接口
 * 
 * @author wubin
 * @version v1.0
 * @since 2015-06-01
 */
public interface IMemCardManageService  extends BaseIService<MemCardManage> {
	public Map<String, Object> list(MemCardManage bean);
	/**
	 * 启用会员卡
	 * 
	 * @param request HttpServletRequest
	 * @param id 会员卡ID
	 * @return 操作结果
	 */
	public MemCardManage enable(Integer id, HttpServletRequest request);
	/**
	 * 停用会员卡
	 * 
	 * @param request HttpServletRequest
	 * @param id 会员卡ID
	 * @return 操作结果
	 */
	public MemCardManage disable(Integer id, HttpServletRequest request);
	
	/**
	 * 批量添加会员卡
	 * 
	 * @param request HttpServletRequest
	 * @param bean 会员卡VO
	 * @return 操作结果
	 */
	public void addMembers(MemCardVO bean, HttpServletRequest request);
	
	/**
	 * 通过会员卡号获取会员卡信息
	 * 
	 * @param number 会员卡号
	 * @return 操作结果
	 */
	public MemCardManage getMemCardByNumber(String number);
	
	/**
	 * 查询会员卡购买记录
	 * 
	 * @param request HttpServletRequest
	 * @param number 会员卡号
	 * @return 操作结果
	 */
	public Map<String, Object> getPurchasesByNum(String number,Date startTime,Date endTime,MemCardManage bean,Integer type);
}