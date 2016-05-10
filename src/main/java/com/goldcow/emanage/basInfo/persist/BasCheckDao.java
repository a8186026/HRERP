package com.goldcow.emanage.basInfo.persist;

import java.util.List;

import com.goldcow.emanage.util.gen.entity.BasCheck;
import com.goldcow.emanage.util.gen.entity.BasCheckLog;
import com.goldcow.sframe.util.mybatis.BaseDao;
import com.goldcow.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface BasCheckDao extends BaseDao<BasCheck> {
	public void deletecomment(BasCheck bean);
	public void addlog(BasCheckLog bean);
	public List<BasCheck> lists(Integer id);
	//获取某一对象已审批的环节数
	public Integer counts(BasCheck bascheck);
}