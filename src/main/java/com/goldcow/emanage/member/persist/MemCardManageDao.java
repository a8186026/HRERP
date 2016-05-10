package com.goldcow.emanage.member.persist;



import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.goldcow.emanage.util.gen.entity.MemCardManage;
import com.goldcow.emanage.util.gen.entity.valueObject.retail.RetailBatchVO;
import com.goldcow.sframe.util.mybatis.BaseDao;
import com.goldcow.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface MemCardManageDao extends BaseDao<MemCardManage> {
	public MemCardManage getMemCardByNumber(String number);
	public void addLog(MemCardManage bean);
	/**
	 * 查询会员卡购买记录
	 * 
	 * @param request HttpServletRequest
	 * @param number 会员卡号
	 * @param startTime 起始时间
	 * @param endTime 截止时间
	 * @return 操作结果
	 */
	public List<RetailBatchVO> getPurchasesByNum(@Param(value="number")String number,@Param(value="startTime")Date startTime,
			@Param(value="endTime")Date endTime,@Param(value="bean")MemCardManage bean,@Param(value="type")Integer type);
	
	public int countPurchasesByNum(@Param(value="number")String number,@Param(value="startTime")Date startTime,
			@Param(value="endTime")Date endTime,@Param(value="bean")MemCardManage bean,@Param(value="type")Integer type);
}