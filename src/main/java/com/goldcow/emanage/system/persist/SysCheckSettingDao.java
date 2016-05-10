package com.goldcow.emanage.system.persist;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.goldcow.emanage.util.gen.entity.SysCheckSetting;
import com.goldcow.emanage.util.gen.entity.SysCheckTitle;
import com.goldcow.emanage.util.gen.entity.valueObject.SysCheck.SysCheckVO;
import com.goldcow.sframe.util.mybatis.BaseDao;
import com.goldcow.sframe.util.mybatis.MyBatisRepository;
/**
 * 审核次数设置数据层
 * 
 * @author wangqi
 * @version v1.0
 * @since 2015-6-1
 */
@MyBatisRepository
public interface SysCheckSettingDao extends BaseDao<SysCheckSetting> {



	public List<SysCheckSetting> list(SysCheckSetting bean);

	
	/**
	 * 添加审核标题
	 * 
	 * @param bean 审核标题信息
	 */
	public void addCheckTitle(SysCheckTitle bean);
	
	/**
	 * 删除审核标题
	 * 
	 * @param id 审核标题ID
	 */
	public void deleteCheckTitle(Integer id);
	
	/**
	 * 获取某个审核流程的所有审核标题
	 * 
	 * @param id 审核流程ID
	 */
	public List<SysCheckTitle> getTitlesById(Integer id);
	
	/**
	 * 更新审核标题
	 * 
	 * @param bean 审核标题bean
	 */
	public void updateTitle(SysCheckTitle bean);
	
	/**
	 * 根据编号获取正在审核的数量
	 * 
	 * @param code 审核标号
	 * @return 如果存在正在审核的，则返回false，如果没有真正审核的，则返回true
	 */
	public Integer getCheckNumber(@Param(value="code") String code);
	//public void update_user_group(SysUserGroup userGroup);

}