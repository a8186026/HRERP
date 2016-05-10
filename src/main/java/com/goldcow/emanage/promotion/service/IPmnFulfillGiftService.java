package com.goldcow.emanage.promotion.service;

import java.util.List;
import java.util.Map;

import com.goldcow.emanage.util.gen.entity.PmnFulfillGift;
import com.goldcow.sframe.util.service.BaseIService;


/**
 * 服务层接口
 * 
 * @author cyx
 * @version v1.0
 * @since 2015-10-26
 */
public interface IPmnFulfillGiftService extends BaseIService<PmnFulfillGift>{
	
	/**
	 * 查询满额赠信息-分页
	 * @param bean 查询条件
	 * @return 满额赠信息列表
	 */
	public Map<String, Object> list(PmnFulfillGift bean);
	
	/**
	 * 查询满足条件的满额赠活动
	 * @param bean 查询条件
	 * @return 满额赠信息列表
	 */
	public List<PmnFulfillGift> getFullFillGifts(PmnFulfillGift bean,List<String> proIds);

}