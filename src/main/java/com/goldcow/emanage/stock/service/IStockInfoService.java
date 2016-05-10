package com.goldcow.emanage.stock.service;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.goldcow.emanage.util.gen.entity.StockInfo;
import com.goldcow.emanage.util.gen.entity.SysUser;
import com.goldcow.emanage.util.gen.entity.valueObject.stock.StockBatchVO;
import com.goldcow.emanage.util.gen.entity.valueObject.stock.StockInfoVO;
import com.goldcow.sframe.util.service.BaseIService;


/**
 * 服务层接口
 * 
 * @author cyx
 * @version v1.0
 * @since 2015-10-13
 */
public interface IStockInfoService  extends BaseIService<StockInfo> {
	
	/**
	 * 
	 * 查询某批次详细信息
	 * @param id 库存ID
	 * @return 批次详细信息
	 */
	public StockBatchVO getDetailById(Integer id);
	/**
	 * 
	 * 查询库存信息-分页
	 * @param bean 查询条件
	 * @return 库存信息列表
	 */
	public Map<String, Object> list(StockInfo bean);
	/**
	 * 
	 * 查询库存信息-分页
	 * @param bean 查询条件
	 * @return 库存信息列表
	 */
	public Map<String, Object> listBatch(StockInfoVO bean);
	
	/**
	 * 批零插入库存
	 * 
	 * @param stocks 库存对象
	 * @author wubin
	 */
	public void addStockInfos(List<StockInfo> stocks);
	/**
	 * 停售库存批次
	 * 
	 * @param stocks 库存对象
	 * @author wangjingjing
	 */
	public Integer saleStop(List<StockInfo> list,SysUser user);
	
	/**
	 * 库存批次停售解锁
	 * @param data 审核数据
	 * @return 保存结果
	 */
	public boolean saleStopClear(String data ,HttpServletRequest request);
	/**
	 *  获取某库房某产品有效库存
	 * 
	 * @param stocks 库存对象
	 * @author wubin
	 */
	public List<StockInfo> getValidatStocks(StockInfo bean);
	public List<StockInfo> getWholeStocks(StockInfo bean);
	
	/**
	 * 查询库存停售批次信息
	 * 
	 * @param list 库存停售批次集合
	 * @author gaoxiang
	 */
	public Map<String, Object> listSaleStop(StockInfo bean);

}