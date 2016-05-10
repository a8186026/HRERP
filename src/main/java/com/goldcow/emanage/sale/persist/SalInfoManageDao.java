package com.goldcow.emanage.sale.persist;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.goldcow.emanage.util.gen.entity.SalInfoManage;
import com.goldcow.emanage.util.gen.entity.SupInfoManage;
import com.goldcow.sframe.util.mybatis.BaseDao;
import com.goldcow.sframe.util.mybatis.MyBatisRepository;
/**
 * 产品信息
 * 
 * @author chenyuxuan
 * @version v1.0
 * @since 2015-7-13
 */

@MyBatisRepository
public interface SalInfoManageDao extends BaseDao<SalInfoManage> {
	
	public int addlog(SalInfoManage salInfoManage);
	/**查找当前数据库表中最大的销方编码值(前面代表销方类别，后3位代表流水号)
	 * @param ticketNumber  代表前面销方类别
	 * @return 返回最大流水号
	 * */
	public Integer getMaxProductCode(String ticketNumber);
	
}