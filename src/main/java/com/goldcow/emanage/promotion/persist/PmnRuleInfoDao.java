package com.goldcow.emanage.promotion.persist;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.goldcow.emanage.util.gen.entity.MemDayManage;
import com.goldcow.emanage.util.gen.entity.PmnFulfillGift;
import com.goldcow.emanage.util.gen.entity.PmnRuleInfo;
import com.goldcow.emanage.util.gen.entity.PmnRuleProduct;
import com.goldcow.emanage.util.gen.entity.StockInfo;
import com.goldcow.sframe.util.mybatis.BaseDao;
import com.goldcow.sframe.util.mybatis.MyBatisRepository;
/**
 * 折扣规则设定
 * 
 * @author gaoxiang
 * @since 2015-10-27
 */

@MyBatisRepository
public interface PmnRuleInfoDao extends BaseDao<PmnRuleInfo> {
	/** 添加产品折扣规则*/
	public int addRuleProduct(PmnRuleProduct pmnRuleProduct);
	/** 修改产品折扣规则*/
	public int updateRuleProduct(PmnRuleProduct pmnRuleProduct);
	/** 获得最大优先级*/
	public Integer getMaxPriority();
	/** 批量添加产品折扣规则信息*/
	public void addRuleProducts(List<PmnRuleProduct> pmnRuleProducts);
	/** 根据rule_id删除产品折扣规则信息*/
	public void deleteRuleProduct(PmnRuleProduct pmnRuleProduct);
	/** 保存折扣规则信息优先级*/
	public void savePriority(List<PmnRuleInfo> pmnRuleInfos);
	 
	/**
	 * 查询满足条件的折扣规则
	 * @param bean 查询条件
	 * @return 折扣规则信息列表
	 */
	public PmnRuleInfo isRuleProduct(@Param(value="today")Date today,@Param(value="week")String week,
			@Param(value="day")String day,@Param(value="product_id")Integer product_id);
	}