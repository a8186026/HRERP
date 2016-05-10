package com.goldcow.emanage.quality.persist;

import java.util.List;

import com.goldcow.emanage.util.gen.entity.QltBatchCheck;
import com.goldcow.emanage.util.gen.entity.valueObject.quality.QltBatchCheckVO;
import com.goldcow.sframe.util.mybatis.BaseDao;
import com.goldcow.sframe.util.mybatis.MyBatisRepository;
/**
 * 重点养护批次信息实体类
 * 
 * @author gaoxiang
 * @since 2015-11-17
 */

@MyBatisRepository
public interface QltBatchCheckDao extends BaseDao<QltBatchCheck> {
	public List<QltBatchCheckVO> lists(QltBatchCheckVO bean);
	
	public QltBatchCheckVO getVOById(Integer ID);
	
	public List<QltBatchCheckVO> listStock(QltBatchCheckVO bean);
}