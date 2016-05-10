package com.goldcow.emanage.system.persist;

import java.util.List;

import com.goldcow.emanage.util.gen.entity.SysComponentCtrl;
import com.goldcow.emanage.util.gen.entity.SysPageCtrl;
import com.goldcow.emanage.util.gen.entity.SysUser;
import com.goldcow.emanage.util.gen.entity.SysUserHabit;
import com.goldcow.emanage.util.gen.entity.valueObject.SysUser.SysUserVO;
import com.goldcow.emanage.util.gen.entity.valueObject.SysUser.UserHabitsVO;
import com.goldcow.sframe.util.mybatis.BaseDao;
import com.goldcow.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface SysUserDao extends BaseDao<SysUser> {
	public List<SysUser> getByUserName(String user_name);
	/**
	 * 获取用户习惯
	 * 
	 * @param bean UserHabitsVO
	 * @return 某个用户的所有习惯
	 */
	public List<SysUserHabit> getHabits(UserHabitsVO bean);
	/**
	 * 保存用户习惯
	 * 
	 * @param list UserHabitsVO数组
	 */
	public Integer saveHabits(List<UserHabitsVO> list);

	public void deleteHabits(UserHabitsVO bean);
	/**
	 * 新增配置用户习惯—页面属性显示
	 * 
	 * @param request HttpServletRequest
	 * @param bean 用户习惯
	 * @return 操作结果
	 */
	public void  addUserHabits(SysUserHabit bean);
	
	/**
	 * 根据组ID 以及 User bean 信息查询所有符合结果的返回 
	 * @param 包含groupID 的 SysUserVO bean
	 */
	public List<SysUserVO> getSysUserContainGroupID(SysUserVO bean);
	public SysUser getByUserId(Integer USER_ID);


}