package com.goldcow.emanage.inventory.service;

 
import java.util.List;
import java.util.Map;

import com.goldcow.emanage.util.gen.entity.Inventory;
import com.goldcow.emanage.util.gen.entity.SysUser;
import com.goldcow.emanage.util.gen.entity.valueObject.inventory.InventoryVO;
import com.goldcow.sframe.util.service.BaseIService;
/**
 * 盘点损益申请
 * 
 * @author wangjingjing
 * @version v1.0
 * @since 2015-11-30
 */
public interface InventoryApplyService extends BaseIService<Inventory>{
	/**
	 * 
	 * 查询库存信息-分页
	 * @param bean 查询条件
	 * @return 库存信息列表
	 */
	public Map<String, Object> list(InventoryVO bean);
	/**
	 * 添加损益申请
	 * 
	 * @param inventory 库存对象
	 * @author wangjingjing
	 */
	public InventoryVO getInverntoryVOByID(Integer id);
 

}