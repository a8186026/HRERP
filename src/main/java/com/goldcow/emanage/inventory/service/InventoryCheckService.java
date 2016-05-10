package com.goldcow.emanage.inventory.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.goldcow.emanage.util.gen.entity.valueObject.inventory.InventoryVO;
import com.goldcow.sframe.util.service.BaseIService;
/**
 * 盘点损益审核
 * @author zhanxiaotong
 * @version v1.0
 * @since 2015-12-3
 */
public interface InventoryCheckService extends BaseIService<InventoryVO>{
	
	public Map<String, Object> Checklist(InventoryVO bean);
	
	public void checked(Integer inventory_id, HttpServletRequest request);
}
