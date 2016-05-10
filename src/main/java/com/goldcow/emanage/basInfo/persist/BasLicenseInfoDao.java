package com.goldcow.emanage.basInfo.persist;

import java.util.List;

import com.goldcow.emanage.util.gen.entity.BasLicenseInfo;
import com.goldcow.sframe.util.mybatis.BaseDao;
import com.goldcow.sframe.util.mybatis.MyBatisRepository;
/**
 * 证照信息
 * 
 * @author gaoxiang
 * @version v1.0
 * @since 2015-6-30
 */

@MyBatisRepository
public interface BasLicenseInfoDao extends BaseDao<BasLicenseInfo> {
	public List<BasLicenseInfo> lists(BasLicenseInfo bean);
}