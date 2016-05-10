package com.goldcow.emanage.transCargo.persist;

import java.util.List;

import com.goldcow.emanage.util.gen.entity.TransCargo;
import com.goldcow.sframe.util.mybatis.BaseDao;
import com.goldcow.sframe.util.mybatis.MyBatisRepository;
/**
 * 调库
 * 
 * @author dht
 * @version v1.0
 * @since 2015-7-1
 */

@MyBatisRepository
public interface TransCargoDao extends BaseDao<TransCargo> {

}