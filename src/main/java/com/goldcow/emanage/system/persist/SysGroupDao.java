package com.goldcow.emanage.system.persist;

import java.util.List;

import com.goldcow.emanage.util.gen.entity.SysGroup;
import com.goldcow.emanage.util.gen.entity.SysUser;
import com.goldcow.emanage.util.gen.entity.SysUserGroup;
import com.goldcow.sframe.util.mybatis.BaseDao;
import com.goldcow.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface SysGroupDao extends BaseDao<SysGroup> {
	public void clearDefault();
	public SysGroup getUserGroup(Integer user_id);
	public SysGroup getDefaultGroup();
	
	public void deleteUser(SysUserGroup userGroup);
	public void addUser(SysUserGroup userGroup);
	
	public List<SysUser> getGroupUsers(Integer id);
	public List<SysUser> getUsersForAdd(Integer id);
	
	
	//public void update_user_group(SysUserGroup userGroup);
}