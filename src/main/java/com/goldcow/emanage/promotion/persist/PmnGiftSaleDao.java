package com.goldcow.emanage.promotion.persist;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.goldcow.emanage.util.gen.entity.PmnFulfillGift;
import com.goldcow.emanage.util.gen.entity.PmnGiftSale;
import com.goldcow.sframe.util.mybatis.BaseDao;
import com.goldcow.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface PmnGiftSaleDao extends BaseDao<PmnGiftSale> {
	
	/**
	 * 获得当前最大的买赠信息编码
	 * 
	 * @return 返回最大流水号
	 */
	public Integer getMaxGiftSaleCode();
	
	/**
	 * 查询满足条件的满额赠活动
	 * @param bean 查询条件
	 * @return 满额赠信息列表
	 */
	public List<PmnGiftSale> getGiftSales(@Param(value="bean")PmnGiftSale bean,@Param(value="list")List<Integer> proIds);
}