package com.goldcow.emanage.system.persist;

import java.util.List;


import com.goldcow.emanage.util.gen.entity.SysPageCtrl;
import com.goldcow.sframe.util.mybatis.BaseDao;
import com.goldcow.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface SysPageCtrlDao extends BaseDao<SysPageCtrl> {
	public List<SysPageCtrl> lists(SysPageCtrl bean);
	
}