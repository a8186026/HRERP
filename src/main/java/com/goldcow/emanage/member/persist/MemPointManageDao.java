package com.goldcow.emanage.member.persist;



import com.goldcow.emanage.util.gen.entity.MemPointManage;
import com.goldcow.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface MemPointManageDao {
	public int add(MemPointManage bean);
//	public MemPointManage getMemPointById(Integer id);
}