package com.goldcow.emanage.accept.persist;

import java.util.List;

import com.goldcow.emanage.accept.vo.PurSpecialVarietyCheckVO;
import com.goldcow.emanage.accept.vo.PurSupAndProVO;
import com.goldcow.emanage.util.gen.entity.ProInfoManage;
import com.goldcow.emanage.util.gen.entity.PurAcceptCheck;
import com.goldcow.emanage.util.gen.entity.StockInfo;
import com.goldcow.emanage.util.gen.entity.valueObject.accept.PurAcceptCheckVO;
import com.goldcow.sframe.util.mybatis.BaseDao;
import com.goldcow.sframe.util.mybatis.MyBatisRepository;
/**
 * 收货审核流程
 * 
 * @author wubin
 * @version v1.0
 * @since 2015-9-24
 */

@MyBatisRepository
public interface PurAcceptCheckDao extends BaseDao<PurAcceptCheck> {
	public PurAcceptCheck getByOrderListID(Integer id);
	 /**
	 * 查询大单下的所有小单级对应的审核信息
	 * @param bean 查询条件
	 * @return 订单信息列表
	 */
	public List<PurAcceptCheckVO> listDetail(PurAcceptCheckVO bean);
	public List<PurAcceptCheck> listReject(PurAcceptCheck bean);

	/**
	 * 查询大单下的所有小单级对应的审核信息条数
	 * @param bean 查询条件
	 * @return 条数
	 */
	public int countDetail(PurAcceptCheck bean);
	/**
	 * 特殊药品审核
	 * @param bean 查询条件
	 * @return 条数
	 */
	public int specialVarietyCheck(PurAcceptCheck bean);
	public int countReject(PurAcceptCheck bean);
	public List<PurAcceptCheck> getByStatus(PurAcceptCheck bean);
	
	 /**
	 * 确认收货——查询大单下的所有小单信息
	 * @param bean 查询条件
	 * @return 订单信息列表
	 */
	public List<PurAcceptCheckVO> listOrderList(PurAcceptCheck bean);

	
	/** 获得最大入库票号
	 * @author wubin
	 * */
	public Integer getMaxIntakeTicket(String date);
	
	/** 更新票号和打印格式
	 * @param list 更新list对象
	 * @author wubin
	 * */
	public int updateTicket(List<PurAcceptCheck> list);
	

    public List<PurAcceptCheckVO> unqualified(PurAcceptCheckVO bean);
    
    /**
	 * 获取质检结果
	 */
	public int getQuantityCheckStatus(Integer id);
	
	 /**
	 * 查询所有收货订单
	 * @param bean 查询条件
	 * @return 订单信息列表
	 */
	public List<PurAcceptCheckVO> list(PurAcceptCheckVO bean);

    
    /**
	 * 根据ID数组查询
	 * 
	 * @param ids ID
	 * @return 订单收货确认信息
	 */
    public List<PurAcceptCheckVO> getByIds(List<Integer> ids);
    /**
	 * 获取某个小单的所有到货数量
	 * 
	 * @param id 小单ID
	 * @return 小单所有到货数量
	 */
    public Integer getSumByOrderListId(Integer id);
    /**
   	 * 根据收货表ID获取产品
   	 * 
   	 * @param id 小单ID
   	 * @return 对应的产品信息
   	 */
       public ProInfoManage getProductByAcceptId(Integer id);
    
    /**
     * 特殊药品页面
     */
    public List<PurSpecialVarietyCheckVO> PurSpecialVarietyList(PurSupAndProVO bean);
    public int PurSpecialVarietyListCount(PurSupAndProVO bean);
}