package com.goldcow.emanage.basInfo.persist;

import java.util.List;

import com.goldcow.emanage.util.gen.entity.BasFactoryInfo;
import com.goldcow.sframe.util.mybatis.BaseDao;
import com.goldcow.sframe.util.mybatis.MyBatisRepository;
/**
 * 厂家档案信息
 * 
 * @author gaoxiang
 * @version v1.0
 * @since 2015-6-2
 */

@MyBatisRepository
public interface BasFactoryInfoDao extends BaseDao<BasFactoryInfo> {
	public Integer getMaxFactoryCode();
	public Integer checkFactoryCodeNum(String factory_code);
}