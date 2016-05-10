package com.goldcow.emanage.basInfo.persist;

import java.util.List;

import com.goldcow.emanage.util.gen.entity.BasAccount;
import com.goldcow.sframe.util.mybatis.BaseDao;
import com.goldcow.sframe.util.mybatis.MyBatisRepository;
/**
 * 财务信息
 * 
 * @author wangqi
 * @version v1.0
 * @since 2015-7-1
 */

@MyBatisRepository
public interface BasAccountDao extends BaseDao<BasAccount> {
	public List<BasAccount> lists(BasAccount bean);

}