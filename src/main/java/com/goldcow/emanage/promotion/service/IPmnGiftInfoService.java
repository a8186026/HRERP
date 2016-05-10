package com.goldcow.emanage.promotion.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.goldcow.emanage.util.gen.entity.PmnGiftInfo;
import com.goldcow.emanage.util.gen.entity.valueObject.promotion.GiftInfoVO;
import com.goldcow.sframe.util.service.BaseIService;


/**
 * 服务层接口
 * 
 * @author cyx
 * @version v1.0
 * @since 2015-10-25
 */
public interface IPmnGiftInfoService extends BaseIService<PmnGiftInfo>{

	/**
	 * 查询买赠信息列表
	 * 
	 * @param bean 查询条件
	 * @return 买赠信息列表
	 */
	public Map<String, Object> list(PmnGiftInfo bean);
	
	/**
	 * 查询买赠信息VO列表
	 * 
	 * @param bean 查询条件
	 * @return 买赠信息列表
	 */
	public Map<String, Object> list(GiftInfoVO bean);
	
	/**
	 * 删除信息记录
	 * 
	 * @param id 买赠产品信息id
	 * @param giftSaleId 买赠信息id
	 * @param request HttpServletRequest
	 */
	public void delete(Integer id,Integer giftSaleId,HttpServletRequest request);
}