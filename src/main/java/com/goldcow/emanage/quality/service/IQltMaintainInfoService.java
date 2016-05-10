package com.goldcow.emanage.quality.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.goldcow.emanage.util.gen.entity.QltMaintainInfo;
import com.goldcow.emanage.util.gen.entity.valueObject.quality.QltMaintainInfoVO;
import com.goldcow.sframe.util.service.BaseIService;
/**
 * 养护品种信息实体类
 * 
 * @author gaoxiang
 * @since 2015-11-23
 */
public interface IQltMaintainInfoService extends BaseIService<QltMaintainInfo>{

	 /**
	 * 查询养护品种确认信息-分页
	 * @param bean 查询条件
	 * @return 养护品种确认信息列表
	 */
	public Map<String, Object> listMaintainInfoVO(QltMaintainInfo bean,Integer stock_storage,Integer  product_immaintain,Integer pro_group_no);
	 /**
	 * 查询养护品种信息-分页
	 * @param bean 查询条件
	 * @return 养护品种信息列表
	 */
	public Map<String, Object> list(QltMaintainInfoVO bean);
		
	public QltMaintainInfoVO getById(Integer id);
	
	/**
	 * 获取最大养护票号
	 * */
	public String getMaxMaintainTicket();
	/**
	 * 养护操作
	 * @param data 需要养护的id
	 * @param oldMaintainTicket 前台养护票号
	 * */
	public String maintain( String data, String oldMaintainTicket, HttpServletRequest request);
	 
}