package com.goldcow.emanage.quality.persist;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.goldcow.emanage.util.gen.entity.QltMaintainInfo;
import com.goldcow.emanage.util.gen.entity.valueObject.quality.QltMaintainInfoVO;
import com.goldcow.sframe.util.mybatis.BaseDao;
import com.goldcow.sframe.util.mybatis.MyBatisRepository;
/**
 * 养护品种信息实体类
 * 
 * @author gaoxiang
 * @since 2015-11-23
 */

@MyBatisRepository
public interface QltMaintainInfoDao extends BaseDao<QltMaintainInfo> {
	/** 
	 * 根据查询条件查询养护品种信息
	 * */
	public List<QltMaintainInfoVO> listMaintainInfoVO(@Param(value="bean") QltMaintainInfo bean, @Param(value="stock_storage") Integer stock_storage,@Param(value="product_immaintain") Integer product_immaintain,@Param(value="pro_group_no") Integer pro_group_no);
	/** 
	 * 根据查询条件得到查询信息总数
	 * */
	public int countMaintainInfoVO(@Param(value="bean") QltMaintainInfo bean, @Param(value="stock_storage") Integer stock_storage,@Param(value="product_immaintain") Integer product_immaintain,@Param(value="pro_group_no") Integer pro_group_no);
	/** 
	 * 获得最大入库票号
	 * */
	public Integer getMaxMaintainTicket(String date);
	
	/**
	 * 批量插入养护
	 * 
	 * @param qltMaintainInfos 养护对象
	 */
	public void addMaintainInfos(List<QltMaintainInfo> qltMaintainInfos);
	
	public List<QltMaintainInfoVO> lists(QltMaintainInfoVO bean);
	
	public QltMaintainInfoVO getVOById(Integer ID);
}