package com.goldcow.emanage.accept.persist;

import java.util.List;

import com.goldcow.emanage.util.gen.entity.PurAcceptReject;
import com.goldcow.sframe.util.mybatis.BaseDao;
import com.goldcow.sframe.util.mybatis.MyBatisRepository;
/**
 * 收货拒收处理
 * 
 * @author zhanxiaotong
 * @since15-11-27
 * @version v1.0
 */

@MyBatisRepository
public interface PurAcceptRejectDao extends BaseDao<PurAcceptReject> {
	public List<PurAcceptReject> lists(PurAcceptReject bean);

	public Integer getMaxRejectTicket(String date);

}