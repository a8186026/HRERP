package com.goldcow.emanage.sale.service;

import java.util.List;
import java.util.Map;

import com.goldcow.emanage.util.gen.entity.ProInfoManage;
import com.goldcow.emanage.util.gen.entity.SalInfoManage;
import com.goldcow.sframe.util.service.BaseIService;
/**
 * 厂家档案信息
 * 
 * @author chenyuxuan
 * @version v1.0
 * @since 2015-7-13
 */
public interface ISalInfoManageService extends BaseIService<SalInfoManage>{

	 /**
	 * 查询产品信息-分页
	 * @param bean 查询条件
	 * @return 厂家档案信息列表
	 */
	public Map<String, Object> list(SalInfoManage bean);
	/**查找当前数据库表中最大的销方编码值(前面代表产品类别，后3位代表流水号)
	 * @param ticketNumber  代表前面销方类别
	 * @return 返回最大流水号
	 * */
	public String getMaxProductCode(String ticketNumber);
}