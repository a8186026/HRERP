package com.goldcow.emanage.product.persist;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.goldcow.emanage.util.gen.entity.ProInfoManage;
import com.goldcow.sframe.util.mybatis.BaseDao;
import com.goldcow.sframe.util.mybatis.MyBatisRepository;
/**
 * 产品信息
 * 
 * @author gaoxiang
 * @version v1.0
 * @since 2015-6-5
 */

@MyBatisRepository
public interface ProInfoManageDao extends BaseDao<ProInfoManage> {
	/**查找当前数据库表中最大的产品编码值(前面代表产品类别，后3位代表流水号)
	 * @param ticketNumber  代表前面产品类别
	 * @return 返回最大流水号
	 * */
	public Integer getMaxProductCode(String ticketNumber);
	
	public List<ProInfoManage> list(@Param(value="bean") ProInfoManage bean,@Param(value="code") String code);
	/** 根据产品信息操作添加日志记录*/
	public int addlog(ProInfoManage proInfoManage);
	/**
	 * 查询相似产品信息
	 * @param bean 查询条件
	 * @return 厂家档案信息列表
	 * @author wubin
	 */
	public List<ProInfoManage> listSimilar(ProInfoManage bean);
	/**
	 * 查询产品折扣信息(去除重复)
	 * @param rule_id 查询条件
	 * @return 产品折扣信息
	 * @author gaoxiang
	 */
	public List<ProInfoManage> listRuleProduct(@Param(value="bean")ProInfoManage bean , @Param(value="rule_id") Integer rule_id);
	
	/**
	 * 查询产品折扣信息
	 * @param rule_id 查询条件
	 * @return 产品折扣信息
	 * @author gaoxiang
	 */
	public List<ProInfoManage> listRuleProductInfo(@Param(value="bean")ProInfoManage bean , @Param(value="rule_id") Integer rule_id);
	public Integer getIDByProCode(String product_code);
	
	/**
	 * 批次停售信息
	 * @param bean 查询条件
	 * @return 停售产品全部批次信息
	 * @author wangjingjing
	 */
	public List<ProInfoManage> listBatchProductInfo(ProInfoManage bean);
	

}