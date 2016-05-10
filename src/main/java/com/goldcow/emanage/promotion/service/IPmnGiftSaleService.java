package com.goldcow.emanage.promotion.service;

import java.util.List;
import java.util.Map;

import com.goldcow.emanage.util.gen.entity.PmnFulfillGift;
import com.goldcow.emanage.util.gen.entity.PmnGiftSale;
import com.goldcow.sframe.util.service.BaseIService;


/**
 * 服务层接口
 * 
 * @author cyx
 * @version v1.0
 * @since 2015-10-26
 */
public interface IPmnGiftSaleService extends BaseIService<PmnGiftSale>{
	
	/**
	 * 查询买赠信息-分页
	 * @param bean 查询条件
	 * @return 买赠信息列表
	 */
	public Map<String, Object> list(PmnGiftSale bean);
	
	/** 获得当前最大的买赠信息编码
	 * @return 返回最大流水号
	 * */
	public String getMaxGiftSaleCode();
	
	/**
	 * 查询编号是否已存在
	 * 
	 * @param value 编号
	 * @return	0表示不存在，1表示已存在
	 */
	public Integer checkUnique(String value);
	/**
	 * 查询满足条件的满赠活动
	 * @param bean 查询条件
	 * @return 满赠信息列表
	 */
	public List<PmnGiftSale> getgiftSales(PmnGiftSale bean,List<Integer> proIds);
}