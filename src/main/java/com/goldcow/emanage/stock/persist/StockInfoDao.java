package com.goldcow.emanage.stock.persist;

import java.util.List;
import java.util.Map;

import com.goldcow.emanage.util.gen.entity.BasCheck;
import com.goldcow.emanage.util.gen.entity.RetailOrderBatch;
import com.goldcow.emanage.util.gen.entity.StockInfo;
import com.goldcow.emanage.util.gen.entity.valueObject.stock.StockBatchVO;
import com.goldcow.emanage.util.gen.entity.valueObject.stock.StockInfoVO;
import com.goldcow.sframe.util.mybatis.BaseDao;
import com.goldcow.sframe.util.mybatis.MyBatisRepository;

/**
 * 厂家档案信息
 * 
 * @author cyx
 * @version v1.0
 * @since 2015-10-13
 */

@MyBatisRepository
public interface StockInfoDao extends BaseDao<StockInfo> {
	/**
	 * 
	 * 查询某批次详细信息
	 * @param id 库存ID
	 * @return 批次详细信息
	 */
	public StockBatchVO getDetailById(Integer id);
	/**
	 * 批量插入库存
	 * 
	 * @param stocks 库存对象
	 * @author wubin
	 */
	public void addStockInfos(List<StockInfo> stocks);
	
	/**
	 * 
	 * 查询库存信息-分页
	 * @param bean 查询条件
	 * @return 库存信息列表
	 * @author wubin
	 */
	public List<StockInfoVO> listBatch(StockInfoVO bean);
	
	
	/**
	 * 
	 * 查询库存总数
	 * @param bean 查询条件
	 * @return 库存总数
	 * @author wubin
	 */
	public int countBatch(StockInfoVO bean);
	
	/**
	 * 批量修改库存属性 maintain_time,maintain_days
	 * 
	 * @param stocks 库存对象
	 * @author gaoxiang
	 */
	public void updateStockInfos(List<StockInfo> stocks);
	/**
	 * 批量获取库存数量的和
	 * 
	 * @param productIds 产品id的集合
	 * @author cyx
	 */
	public List<Double> getByProductIds(List<Integer> productIds);
	/**
	 * 批量获取库存数量的和
	 * 
	 * @param queryStocks 查询条件的stock集合
	 * @author cyx
	 */
	public List<Double> getByStockInfos(List<StockInfo> queryStocks);
	
	public Double getSumStorageByProductId(StockInfo stockInfo);
	/**
	 * 查询有效库存
	 * 
	 * @param stocks 库存对象
	 * @author wubin
	 */
	public List<StockInfo> getValidatStocks(StockInfo bean);
	public List<StockInfo> getwholeStocks(StockInfo bean);
	
	
	/**
	 * 批量增加库存
	 * 
	 * @param list 批次订单集合
	 * @author cyx
	 */
	public void addStorageNumber(List<RetailOrderBatch> list);
	
	/**
	 * 查询库存停售批次信息
	 * 
	 * @param list 库存停售批次集合
	 * @author gaoxiang
	 */
	public List<StockInfo> listSaleStop(StockInfo bean);
	/**
	 * 获取库存停售批次数量
	 * 
	 * @author gaoxiang
	 */
	public Integer countSaleStop(StockInfo bean);
	
}