package com.goldcow.emanage.modi.persist;

import java.util.List;
import java.util.Map;

import com.goldcow.emanage.util.gen.entity.SysUser;
import com.goldcow.sframe.util.mybatis.BaseDao;
import com.goldcow.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface PmPersonnelDao extends BaseDao<SysUser> {
	List<SysUser> getListByEntity(SysUser SysUser);
	List<SysUser> getByUserId(int user_id);
	List<SysUser> getByEncryptionid(String personnel_encryptionid);
	List<SysUser> getUserWithRole(Map<String, Object> param);
}