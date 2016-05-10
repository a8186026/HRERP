package com.goldcow.emanage.inventory.persist;

import java.util.List;

import com.goldcow.emanage.util.gen.entity.Inventory;
import com.goldcow.emanage.util.gen.entity.valueObject.inventory.InventoryVO;
import com.goldcow.sframe.util.mybatis.BaseDao;
import com.goldcow.sframe.util.mybatis.MyBatisRepository;
/**
 * 盘点损益申请
 * 
 * @author wangjingjing
 * @version v1.0
 * @since 2015-11-30
 */

@MyBatisRepository
public interface InventoryApplyDao extends BaseDao<Inventory> {
	 /**
	 *  
	 * @param bean 查询条件
	 * @return 订单信息列表
	 */
	public List<InventoryVO> getInventoryApplyList(InventoryVO bean);
	public Integer getInventoryCount(InventoryVO bean);
	public InventoryVO getInverntoryVOByID(Integer id);
}