package com.goldcow.emanage.retail.persist;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.goldcow.emanage.util.gen.entity.RetailOrderBatch;
import com.goldcow.emanage.util.gen.entity.valueObject.retail.RetailOrderVO;
import com.goldcow.sframe.util.mybatis.BaseDao;
import com.goldcow.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface RetailOrderBatchDao extends BaseDao<RetailOrderBatch> {
	/** 获得当前销售订单产品最大的批次编号
	 * @param 订单票号,序号
	 * @return 返回最大小号
	 * */
	public Integer getMaxOrderBatchId(RetailOrderVO bean);
	
	
	
	/** 删除产品批次表中的数据，然后增加库存
	 * @param bean 产品批次信息
	 * @return 返回最大小号
	 * */
	public void deleteAndAdd(RetailOrderBatch bean);
	/** 更新已卖产品兑换信息
	 * @param ids 更新的产品
	 * @param ticket_id 票号
	 * */
	public void updateOrderBatch(@Param(value="list")List<Integer> ids ,@Param(value="ticket_id")String ticket_id);
}