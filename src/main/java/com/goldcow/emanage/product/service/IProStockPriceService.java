package com.goldcow.emanage.product.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.goldcow.emanage.util.gen.entity.ProInfoManage;
import com.goldcow.emanage.util.gen.entity.StockPrice;
import com.goldcow.emanage.util.gen.entity.valueObject.product.ProStockPriceVO;
import com.goldcow.sframe.util.service.BaseIService;


/**
 * 调价
 * 
 * @author zhanxiaotong
 * @version v1.0
 * @since 2015-9-15
 */
public interface IProStockPriceService extends BaseIService<StockPrice>{

	public Map<String, Object> lists(StockPrice bean);
	
	

	public void updateLog(Integer id, HttpServletRequest request);//调价日志方法
	

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
	 * @param commonName 产品通用名
	 * @return map
	 */
	public List<ProStockPriceVO> getDeptsByPro(StockPrice bean,String product_name);
}
