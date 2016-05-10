package com.goldcow.emanage.basInfo.persist;

import java.util.List;

import com.goldcow.emanage.util.gen.entity.BasAccount;
import com.goldcow.emanage.util.gen.entity.CompanyInfo;
import com.goldcow.sframe.util.mybatis.BaseDao;
import com.goldcow.sframe.util.mybatis.MyBatisRepository;
/**
 * 公司信息
 * 
 * @author wubin
 * @version v1.0
 * @since 2016-1-4
 */

@MyBatisRepository
public interface CompanyInfoDao extends BaseDao<CompanyInfo> {
	public List<CompanyInfo> lists(CompanyInfo bean);

}