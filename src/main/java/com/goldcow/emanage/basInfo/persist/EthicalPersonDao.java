package com.goldcow.emanage.basInfo.persist;

import java.util.List;

import com.goldcow.emanage.util.gen.entity.BasAccount;
import com.goldcow.emanage.util.gen.entity.EthicalPerson;
import com.goldcow.sframe.util.mybatis.BaseDao;
import com.goldcow.sframe.util.mybatis.MyBatisRepository;
/**
 * 处方药人员管理
 * 
 * @author wubin
 * @version v1.0
 * @since 2015-12-30
 */

@MyBatisRepository
public interface EthicalPersonDao extends BaseDao<EthicalPerson> {
	public List<EthicalPerson> lists(EthicalPerson bean);
}