package com.goldcow.emanage.supply.persist;

import java.util.List;

import com.goldcow.emanage.util.gen.entity.SupInfoManage;
import com.goldcow.sframe.util.mybatis.BaseDao;
import com.goldcow.sframe.util.mybatis.MyBatisRepository;
/**
 * 
 * 供方客户信息
 * 
 * @author wangqi
 * @version v1.0
 * @since 2015-6-8
 */

@MyBatisRepository
public interface SupInfoManageDao extends BaseDao<SupInfoManage> {
	/** 分页查询 */
	public List<SupInfoManage> list(SupInfoManage bean);
	
	public int count(SupInfoManage bean);
	
	public int addlog(SupInfoManage supInfoManage);
	 
}