package com.goldcow.emanage.quality.persist;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.goldcow.emanage.util.gen.entity.QltDefectsInfo;
import com.goldcow.emanage.util.gen.entity.valueObject.quality.QltDefectsInfoVO;
import com.goldcow.sframe.util.mybatis.BaseDao;
import com.goldcow.sframe.util.mybatis.MyBatisRepository;
/**
 * 不合格品种信息实体类
 * 
 * @author gaoxiang
 * @since 2015-11-30
 */

@MyBatisRepository
public interface QltDefectsInfoDao extends BaseDao<QltDefectsInfo> {

	/** 
	 * 获得最大不合格品票号
	 * */
	public Integer getMaxDefectsTicket(String date);
	/**
	 * 获取最大报损票号
	 * */
	public Integer getMaxDefectsProfitTicket(String date);
	
	/**
	 * 获取最大损益票号
	 * */
	public Integer getMaxDefectsBreakageTicket(String date);
	
	/**
	 * 获取最大销毁票号
	 * */
	public Integer getMaxDefectsDestructionTicket(String date);
	
	public List<QltDefectsInfoVO> lists(QltDefectsInfoVO bean);
	
	public QltDefectsInfoVO getVOById(Integer ID);
	
	/** 根据不合格品种信息操作添加日志记录*/
	public int addLog(QltDefectsInfo qltDefectsInfo);
	
	/**
	 * 批量修改库存属性 defects_draft,defects_ticketNo
	 * @param defectsInfos
	 */
	public void updateDefectsInfos(@Param(value="defects_ticketNo") String defects_ticketNo,@Param(value="defects_draft")Integer defects_draft,@Param(value="list")List<Integer> ids);
}