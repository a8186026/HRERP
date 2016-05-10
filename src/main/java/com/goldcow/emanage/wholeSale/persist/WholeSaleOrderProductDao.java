package com.goldcow.emanage.wholeSale.persist;

import com.goldcow.emanage.util.gen.entity.WholeSaleOrderProduct;
import com.goldcow.sframe.util.mybatis.BaseDao;
import com.goldcow.sframe.util.mybatis.MyBatisRepository;
/**
 * 批发订单产品
 * 
 * @author cyx
 * @version v1.0
 * @since 2015-12-24
 */

@MyBatisRepository
public interface WholeSaleOrderProductDao extends BaseDao<WholeSaleOrderProduct> {

}