package com.goldcow.emanage.promotion.persist;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.goldcow.emanage.util.gen.entity.PmnFulfillGift;
import com.goldcow.sframe.util.mybatis.BaseDao;
import com.goldcow.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface PmnFulfillGiftDao extends BaseDao<PmnFulfillGift> {
	/**
	 * 查询满足条件的满额赠活动
	 * @param bean 查询条件
	 * @return 满额赠信息列表
	 */
	public List<PmnFulfillGift> getFullFillGifts(@Param(value="bean")PmnFulfillGift bean,@Param(value="list")List<String> proIds);
}