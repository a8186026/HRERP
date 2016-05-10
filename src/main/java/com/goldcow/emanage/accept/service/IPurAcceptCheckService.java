package com.goldcow.emanage.accept.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.goldcow.emanage.accept.vo.PurSupAndProVO;
import com.goldcow.emanage.util.gen.entity.ProInfoManage;
import com.goldcow.emanage.util.gen.entity.PurAcceptCheck;
import com.goldcow.emanage.util.gen.entity.StockInfo;
import com.goldcow.emanage.util.gen.entity.valueObject.accept.PurAcceptCheckVO;
import com.goldcow.sframe.util.service.BaseIService;
/**
 * 收货审核流程
 * 
 * @author wubin
 * @version v1.0
 * @since 2015-9-24
 */
public interface IPurAcceptCheckService extends BaseIService<PurAcceptCheck>{

	public PurAcceptCheck getByOrderListID(Integer id);
	 /**
	 * 查询所有需要审核的订单
	 * @param bean 查询条件
	 * @return 订单信息列表
	 */
	public Map<String, Object> list(PurAcceptCheckVO bean);
	 /**
		 * 查询大单下的所有小单级对应的审核信息
		 * @param bean 查询条件
		 * @return 订单信息列表
		 */
	public Map<String, Object> listDetail(PurAcceptCheckVO bean);
	/**
	 * 特殊药品审核
	 * @param bean 查询条件
	 * @return 条数
	 */
	public int specialVarietyCheck(List<PurAcceptCheck> bean);
	
	/**
	 * 获取质检结果
	 */
	public int getQuantityCheckStatus(Integer id);
	 /**
	 * 确认收货——查询大单下的所有小单信息
	 * @param bean 查询条件
	 * @return 订单信息列表
	 */
	public Map<String, Object> listOrderList(PurAcceptCheck bean);

	/**
	 * 获取最大入库票号
	 * @author wubin
	 * */
	public String getMaxIntakeTicket();
	/** 更新票号和打印格式
	 * @param list 更新list对象
	 * @author wubin
	 * */
	public void updateTicket(List<PurAcceptCheck> list);

	/**
	 * 查询不合格品
	 * @param bean 查询条件
	 * @return 订单信息列表
	 */
	public Map<String, Object> listUnqualified(PurAcceptCheckVO bean);

	/**
	 * 拒收审核通过
	 * @param data 拒收审核数据
	 * @return 保存结果
	 */
	boolean passReject(String data, HttpServletRequest request);
	/**
	 * 拒收审核不通过
	 * @param data 拒收审核数据
	 * @return 保存结果
	 */
	boolean failReject(String data ,HttpServletRequest request);
	Map<String, Object> listReject(PurAcceptCheck bean);
	boolean addUnqualified(PurAcceptCheck bean);

	
	/**
	 * 入库操作
	 * @param data 需要入库的id
	 * @param oldIntakeTicket 前台入库票号
	 * @param printType  打印格式
	 * @author wubin
	 * */
	public String intake( String data,String oldIntakeTicket,String printType,HttpServletRequest request);

	/**
	 * 根据ID数组查询
	 * 
	 * @param ids ID
	 * @return 订单收货确认信息
	 */
	public List<PurAcceptCheckVO> getByIds(List<Integer> ids);
	
	/**
	 * 特殊药品审核列表
	 * @param bean
	 * @return
	 */
	public Map<String, Object> specialVariteyList(PurSupAndProVO bean);
	
	  /**
   	 * 根据收货表ID获取产品
   	 * 
   	 * @param id 小单ID
   	 * @return 对应的产品信息
   	 */
       public ProInfoManage getProductByAcceptId(Integer id);
       /**
      	 * 根据收货表ID获取产品
      	 * 
      	 * @param id 小单ID
      	 * @return 对应的产品信息
      	 */
       public PurAcceptCheck Straightadd(PurAcceptCheck bean, HttpServletRequest request);
}