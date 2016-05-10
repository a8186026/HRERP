package com.goldcow.emanage.quality.persist;

import java.util.List;

import com.goldcow.emanage.util.gen.entity.QltVarietyCheck;
import com.goldcow.emanage.util.gen.entity.valueObject.quality.QltVarietyCheckVO;
import com.goldcow.sframe.util.mybatis.BaseDao;
import com.goldcow.sframe.util.mybatis.MyBatisRepository;
/**
 * 重点养护品种信息实体类
 * 
 * @author gaoxiang
 * @since 2015-11-13
 */

@MyBatisRepository
public interface QltVarietyCheckDao extends BaseDao<QltVarietyCheck> {
	public List<QltVarietyCheckVO> lists(QltVarietyCheckVO bean);
	
	public QltVarietyCheckVO getVOById(Integer ID);
}