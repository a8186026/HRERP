package com.goldcow.emanage.product.persist;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.goldcow.emanage.util.gen.entity.ProPriceTag;
import com.goldcow.emanage.util.gen.entity.WholeSaleOrderBatch;
import com.goldcow.sframe.util.mybatis.BaseDao;
import com.goldcow.sframe.util.mybatis.MyBatisRepository;
/**
 * 价签打印申请
 * 
 * @author cyx
 * @version v1.0
 * @since 2016-1-7
 */

@MyBatisRepository
public interface ProPriceTagDao extends BaseDao<ProPriceTag> {
	
	public List<ProPriceTag> list(@Param(value="bean")ProPriceTag bean, @Param(value="start_time")Date start_time,@Param(value="end_time")Date end_time);

	/**
	 * 查询指定品种
	 * @param bean 查询条件
	 * @return 零售订单批次信息列表
	 */
	public List<ProPriceTag> querySpecifyProduct(@Param(value="start_time")Date start_time,@Param(value="end_time")Date end_time, 
				@Param(value="department_id")Integer department_id, @Param(value="medinsuvariety")Integer medinsuvariety);
}