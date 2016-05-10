package com.goldcow.emanage.system.persist;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.goldcow.emanage.util.gen.entity.SysComponentCtrl;
import com.goldcow.emanage.util.gen.entity.SysPageCtrl;
import com.goldcow.emanage.util.gen.entity.SysUser;
import com.goldcow.emanage.util.gen.entity.SysUserHabit;
import com.goldcow.emanage.util.gen.entity.valueObject.SysUser.SysUserVO;
import com.goldcow.emanage.util.gen.entity.valueObject.SysUser.UserHabitsVO;
import com.goldcow.emanage.util.gen.entity.valueObject.sysScreening.SysScreeningVO;
import com.goldcow.sframe.util.mybatis.BaseDao;
import com.goldcow.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface SysScreeningDao {
	/**
	 * 数据筛选
	 * 
	 * @param list 参数
	 * @param sqlName 数据库名
	 * @return 实体bean list
	 * @author wubin
	 * @since 2015-11-3
	 */
	public List<Object> search(@Param(value="list")List<SysScreeningVO> list,@Param(value="sqlName")String sqlName);
	/**
	 * 根据数据库名称查询主键
	 * 
	 * @param sqlName 数据库名
	 * @return String 主键名称
	 * @author wubin
	 * @since 2015-11-3
	 */
	public String getPK(String sqlName);
}