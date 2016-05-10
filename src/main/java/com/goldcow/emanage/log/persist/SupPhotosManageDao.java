package com.goldcow.emanage.log.persist;

import java.util.List;

import com.goldcow.emanage.util.gen.entity.SupInfoManage;
import com.goldcow.sframe.util.mybatis.BaseDao;
import com.goldcow.sframe.util.mybatis.MyBatisRepository;


@MyBatisRepository
public interface SupPhotosManageDao extends BaseDao<SupInfoManage> {
	
	public List<SupInfoManage> list(SupInfoManage bean);

}