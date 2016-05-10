package com.goldcow.emanage.basInfo.persist;

import java.util.List;

import com.goldcow.emanage.util.gen.entity.BasAccount;
import com.goldcow.emanage.util.gen.entity.TCatalog;
import com.goldcow.sframe.util.mybatis.BaseDao;
import com.goldcow.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface BasLocalDrugDao extends BaseDao<TCatalog> {

}