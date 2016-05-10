package com.goldcow.emanage.promotion.persist;

import java.util.List;

import com.goldcow.emanage.util.gen.entity.PmnGiftInfo;
import com.goldcow.emanage.util.gen.entity.valueObject.promotion.GiftInfoVO;
import com.goldcow.sframe.util.mybatis.BaseDao;
import com.goldcow.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface PmnGiftInfoDao extends BaseDao<PmnGiftInfo> {
	
	/**
	 * 查询买赠信息VO列表
	 * 
	 * @param bean 查询条件
	 * @return 买赠信息列表
	 */
	public List<GiftInfoVO> listVO(GiftInfoVO parameters);
	
	
	/**
	 * 批量删除买赠信息包含的买赠产品信息
	 * 
	 * @param id 买赠信息id
	 */
	public void deleteBySaleId(Integer id);

}