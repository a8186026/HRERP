package com.goldcow.emanage.quality.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.goldcow.emanage.util.gen.entity.QltDefectsInfo;
import com.goldcow.emanage.util.gen.entity.valueObject.quality.QltDefectsInfoVO;
import com.goldcow.sframe.util.service.BaseIService;
/**
 * 不合格品种信息实体类
 * 
 * @author gaoxiang
 * @since 2015-11-30
 */
public interface IQltDefectsInfoService extends BaseIService<QltDefectsInfo>{

	 /**
	 * 查询不合格品种信息-分页
	 * @param bean 查询条件
	 * @return 不合格品种信息列表
	 */
	public Map<String, Object> list(QltDefectsInfoVO bean);
		
	public QltDefectsInfoVO getById(Integer id);
	
	/**
	 * 获取最大不合格票号
	 * */
	public String getMaxDefectsTicket();
	
	/**
	 * 获取最大报损票号
	 * */
	public String getMaxDefectsProfitTicket();
	
	/**
	 * 获取最大损益票号
	 * */
	public String getMaxDefectsBreakageTicket();
	
	/**
	 * 获取最大销毁票号
	 * */
	public String getMaxDefectsDestructionTicket();
	/**
	 * 新增不合格品种信息 插入票号
	 * @param bean
	 * @return 保存结果
	 */
	public String save(String data, String oldDefectsTicket, HttpServletRequest request); 
	/**
	 * 不合格品种信息确认
	 * @param data 审核数据
	 * @return 保存结果
	 */
	public boolean check(String data ,HttpServletRequest request); 
	/**
	 * 不合格品种信息损益申请
	 * @param data 损益数据
	 * @return 保存结果
	 */
	public String profit(String data ,String oldProfitTicket,HttpServletRequest request);
	
	/**
	 * 不合格品种信息-采购部审核
	 * @param data 审核数据
	 * @return 保存结果
	 */
	public boolean purchasedCheck(String data ,HttpServletRequest request);
	
	/**
	 * 不合格品种信息-质管部审核
	 * @param data 审核数据
	 * @return 保存结果
	 */
	public boolean qualitycdCheck(String data ,HttpServletRequest request);
	
	/**
	 * 不合格品种信息-储运部审核
	 * @param data 审核数据
	 * @return 保存结果
	 */
	public boolean storagetdCheck(String data ,HttpServletRequest request);
	
	/**
	 * 不合格品种信息-质量负责人审核
	 * @param data 审核数据
	 * @return 保存结果
	 */
	public boolean qaCheck(String data ,HttpServletRequest request);
	
	/**
	 * 不合格品种信息-财务部审核
	 * @param data 审核数据
	 * @return 保存结果
	 */
	public boolean financialdCheck(String data ,HttpServletRequest request);
	
	/**
	 * 不合格品种信息-经理审核
	 * @param data 审核数据
	 * @return 保存结果
	 */
	public boolean managerCheck(String data ,HttpServletRequest request);
	/**
	 * 不合格品种信息-报损确认
	 * @param data 审核数据
	 * @return 保存结果
	 */
	//boolean breakageCheck(String data ,HttpServletRequest request);
	public String breakageCheck(String data ,String oldBreakageTicket, HttpServletRequest request);
	/**
	 * 不合格品种信息销毁
	 * @param bean
	 * @return 保存结果
	 */
	public QltDefectsInfo destruction(QltDefectsInfo bean, HttpServletRequest request);
	
	/**
	 * 直接报损
	 * @param data 需要操作的id
	 * @param oldIntakeTicket 前台报损票号
	 * */
	public String profitCheck(String data, String oldProfitTicket, HttpServletRequest request);
	
}