package com.goldcow.emanage.stock.service.impl;


import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldcow.emanage.stock.persist.StockInfoDao;
import com.goldcow.emanage.stock.service.IStockInfoService;
import com.goldcow.emanage.util.gen.SysTools;
import com.goldcow.emanage.util.gen.entity.StockInfo;
import com.goldcow.emanage.util.gen.entity.SysUser;
import com.goldcow.emanage.util.gen.entity.valueObject.stock.StockBatchVO;
import com.goldcow.emanage.util.gen.entity.valueObject.stock.StockInfoVO;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Maps;

@Service
public class StockInfoServiceImpl implements IStockInfoService {
	private static Logger log = LoggerFactory.getLogger(StockInfoServiceImpl.class);
	/** 库存操作 */
	@Autowired
	private StockInfoDao dao;
	
	@Override
	public StockInfo getById(Integer id) {
		log.debug("获取库房小号ID： " + id);
		return dao.get(id);
	}
	
	@Override
	public StockBatchVO getDetailById(Integer id) {
		log.debug("获取库房小号ID： " + id);
		return dao.getDetailById(id);
	}
	
	
	@Override
	public Map<String, Object> list(StockInfo bean) {
		log.debug("查询列表");
		/*bean.setMedicine_code(SysUtil.getSqlLikeParam(bean.getMedicine_code()));
		bean.setMedicine_name(SysUtil.getSqlLikeParam(bean.getMedicine_name()));
		bean.setMedicine_proname(SysUtil.getSqlLikeParam(bean.getMedicine_proname()));
		bean.setMedicine_engname(SysUtil.getSqlLikeParam(bean.getMedicine_engname()));
		bean.setMedicine_chnpy(SysUtil.getSqlLikeParam(bean.getMedicine_chnpy()));
		bean.setMedicine_note(SysUtil.getSqlLikeParam(bean.getMedicine_note()));*/
		bean.setStock_saleStop(GlobalVal.STOCK_STATUS.STOCK_START);
		
		List<StockInfo> list = dao.list(bean);
		int count = dao.count(bean);
		// 封装分页对象
		Map<String, Object> result = Maps.newHashMap();
		result.put("total", count);
		result.put("rows", list);
		return  result;	
		//return dao.list(bean);
	}
	
	
	@Override
	public Map<String, Object> listBatch(StockInfoVO bean) {
		log.debug("查询批次列表");
		
		bean.setProduct_code(SysUtil.getSqlLikeParam(bean.getProduct_code()));
		bean.setProduct_name(SysUtil.getSqlLikeParam(bean.getProduct_name()));
		bean.setStock_saleStop(GlobalVal.STOCK_STATUS.STOCK_START);
		
		List<StockInfoVO> list = dao.listBatch(bean);
		int count = dao.countBatch(bean);
		// 封装分页对象
		Map<String, Object> result = Maps.newHashMap();
		result.put("total", count);
		result.put("rows", list);
		return  result;	
	}
	
	
	@Override
	public StockInfo add(StockInfo bean, HttpServletRequest request) {
		log.debug("新增库存");
	    
		dao.add(bean);
		log.debug("新增库存成功 => id : " + bean.getStock_info_id());
		return bean;
	}
	@Override
	public void delete(Integer id, HttpServletRequest request) {
		log.debug("删除库存");
		dao.delete(id);
		log.debug("删除库存成功 => id : " + id);
	}
	@Override
	public StockInfo update(StockInfo bean, HttpServletRequest request) {
		log.debug("修改库存");
		dao.update(bean);
		log.debug("修改库存成功 => id : " + bean.getStock_info_id());
		return bean;
	}


	/**
	 * 批零插入库存
	 * 
	 * @param stocks 库存对象
	 * @author wubin
	 */
	@Override
	public void addStockInfos(List<StockInfo> stocks) {
		log.debug("批量添加库存条数 => : " + stocks.size());
		dao.addStockInfos(stocks);
		
	}
	
	
	/**
	 * 停售批次信息列表-分页
	 * 
	 * @param bean 查询条件
	 * @return 停售批次信息列表
	 * @author wangjingjing
	 */
	@Override
	public Integer saleStop(List<StockInfo> list,SysUser user) {
		for (StockInfo stockInfo : list) {
			stockInfo.setStock_stopPerson(user.getUser_id().toString());
			stockInfo.setStock_stopTime(new Date()); 
			stockInfo.setStock_saleStop(GlobalVal.STOCK_STATUS.STOCK_STOP); 
			try {
				stockInfo.setStock_stopReason(java.net.URLDecoder.decode(stockInfo.getStock_stopReason(),"UTF-8"));
				System.out.println("reason:"+java.net.URLDecoder.decode(stockInfo.getStock_stopReason(),"UTF-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			//dao.update(stockInfo);	
			}
		return 1;
	}

	/**
	 * 批次停售解锁
	 * 
	 * @param request HttpServletRequest
	 * @param id 审核通过品种的id
	 * @return 操作结果
	 */
	@Override
	public boolean saleStopClear(String data ,HttpServletRequest request) {
		System.out.println("data:"+data);
		//解析前台数据
		String checks[] = data.split("\\|");//使用转义字符,否则出错
		System.out.println("length:"+checks.length+" ++++++ "+checks[0]);
		for(int i=0;i<checks.length;i++){
			System.out.println("checks:"+checks[i]);
			String result[] = checks[i].split("_");
			//更新不合格品种数据库表数据
			StockInfo si = dao.get(Integer.parseInt(result[0]));
			si.setStock_stopClearReason(SysTools.decode(result[1]));
			si.setStock_stopClearPerson(SysUtil.getLoginUser(request).getDisplay_name()); 
			si.setStock_stopClearTime(new Date());
			si.setStock_saleStop(GlobalVal.STOCK_STATUS.STOCK_START);
			
			dao.update(si);
			
			log.debug("批次停售解锁操作成功");
		}
		return true;
	}

	@Override
	public List<StockInfo> getValidatStocks(StockInfo bean) {
		log.debug(" 获取某库房某产品有效库存 ");
		return dao.getValidatStocks(bean);
	}

	
	/**
	 * 查询库存停售批次信息
	 * 
	 * @param list 库存停售批次集合
	 * @author gaoxiang
	 */
	@Override
	public Map<String, Object> listSaleStop(StockInfo bean) {
		log.debug("查询列表");
		 
		List<StockInfo> list = dao.listSaleStop(bean);
		int count = dao.countSaleStop(bean);
		// 封装分页对象
		Map<String, Object> result = Maps.newHashMap();
		result.put("total", count);
		result.put("rows", list);
		return  result;	
		//return dao.list(bean);
	}

	@Override
	public List<StockInfo> getWholeStocks(StockInfo bean) {
		log.debug(" 获取某库房某产品库存 ");
		return dao.getwholeStocks(bean);
	}
}