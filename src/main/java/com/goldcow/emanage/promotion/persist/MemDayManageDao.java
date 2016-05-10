package com.goldcow.emanage.promotion.persist;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.goldcow.emanage.util.gen.entity.MemDayManage;
import com.goldcow.emanage.util.gen.entity.PmnRuleInfo;
import com.goldcow.sframe.util.mybatis.BaseDao;
import com.goldcow.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface MemDayManageDao extends BaseDao<MemDayManage> {
	/**
	 * 查询今天是否是会员日
	 * 
	 * @return 会员日信息
	 */
	public MemDayManage isMemDay(@Param(value="today")Date today,@Param(value="week")String week,
			@Param(value="day")String day,@Param(value="dept_id")String dept_id);
	
	/** 保存折扣规则信息优先级*/
	public void savePriority(List<MemDayManage> memDayManage);
	/** 获得最大优先级*/
	public Integer getMaxPriority();
}