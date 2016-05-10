package com.goldcow.emanage.product.persist;

import java.util.List;
import java.util.Map;

import com.goldcow.emanage.util.gen.entity.StockPrice;
import com.goldcow.emanage.util.gen.entity.valueObject.product.ProStockPriceVO;
import com.goldcow.sframe.util.mybatis.BaseDao;
import com.goldcow.sframe.util.mybatis.MyBatisRepository;

/**
 * 调价
 * 
 * @author zhanxiaotong
 * @version v1.0
 * @since 2015-9-15
 */
@MyBatisRepository
public interface ProStockPriceDao extends BaseDao<StockPrice>{
	public List<StockPrice> lists(StockPrice bean);

	public void updateLog(StockPrice stockP);
	
	
	/**
	 * 查询调价信息
	 * 
	 * @author wubin
	 * @param bean 产品参数
	 * @return list
	 */
	public List<StockPrice> getStockPriceList(StockPrice bean);
	/**
	 * 查询某个产品的所有库房价格
	 * 
	 * @author wubin
	 * @param bean 产品参数
	 * @return list
	 */
	public List<ProStockPriceVO> getDeptsByPro(StockPrice bean);
}
