package com.goldcow.emanage.inventory.persist;

import java.util.List;

import com.goldcow.emanage.util.gen.entity.valueObject.inventory.InventoryVO;
import com.goldcow.sframe.util.mybatis.BaseDao;
import com.goldcow.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface InventoryCheckDao  extends BaseDao<InventoryVO>{
	
	List<InventoryVO> getCheckList(InventoryVO bean);
}
