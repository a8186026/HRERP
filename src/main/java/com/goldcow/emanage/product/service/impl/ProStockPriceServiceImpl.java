package com.goldcow.emanage.product.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldcow.emanage.product.persist.ProStockPriceDao;
import com.goldcow.emanage.product.service.IProStockPriceService;
import com.goldcow.emanage.util.gen.entity.ProInfoManage;
import com.goldcow.emanage.util.gen.entity.StockPrice;
import com.goldcow.emanage.util.gen.entity.valueObject.product.ProStockPriceVO;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Maps;
/**
 * 调价
 * 
 * @author zhanxiaotong
 * @version v1.0
 * @since 2015-9-15
 */
@Service
public class ProStockPriceServiceImpl implements IProStockPriceService {
	public static Logger log = LoggerFactory.getLogger(ProStockPriceServiceImpl.class);
	@Autowired
	private ProStockPriceDao dao;
	
	/**
	 * 新增分店定价
	 * 
	 * @param bean 产品信息信息
	 * @param request HttpServletRequest
	 * @return 产品信息信息
	 */
	@Override
	public StockPrice add(StockPrice bean, HttpServletRequest request) {
		
		log.debug("新增产品信息");
		SysUtil.checkInput(bean);
		bean.setStatus(GlobalVal.RECORD_STATUS.ENABLE);
		bean.setCreate_user(SysUtil.getLoginUserId(request));
		bean.setCreate_time(new Date()); 
		bean.setLast_modify_user(SysUtil.getLoginUserId(request));
		bean.setLast_modify_time(new Date());
		dao.add(bean);
		log.debug("新增分店定价信息成功 => id : " + bean.getProd_id());
		
		return bean;
	}

	@Override
	public void delete(Integer id, HttpServletRequest request) {
		log.debug("删除分店定价信息");
		dao.delete(id);
	}

	@Override
	public StockPrice update(StockPrice bean, HttpServletRequest request) {
		log.debug("修改分店价格");
		SysUtil.checkInput(bean);
		
		if(bean.getProd_id()==-1)
			add(bean,request);
		else{
			//添加修改日志
			updateLog(bean.getProd_id(),request);
			
			bean.setLast_modify_user(SysUtil.getLoginUserId(request));
			bean.setLast_modify_time(new Date());
			dao.update(bean);
			
		}
		return bean;
	}

	@Override
	public StockPrice getById(Integer id) {
		return dao.get(id);
	}

	@Override
	public Map<String, Object> lists(StockPrice bean) {
		log.debug("查询分店信息列表");		
		List<StockPrice> list = dao.list(bean);
		int count = dao.count(bean);

		// 封装分页对象
		Map<String, Object> result = Maps.newHashMap();
		result.put("total", count);
		result.put("rows", list);
		
		
		return result;
	}

	@Override
	public void updateLog(Integer id, HttpServletRequest request) {
		log.debug("修改分店价格日志-->ID:"+id);
		StockPrice stockP = dao.get(id);
		dao.updateLog(stockP);
	}

	@Override
	public List<ProStockPriceVO> getDeptsByPro(StockPrice bean,String product_name) {
		log.debug("查询分店信息列表");
		List<ProStockPriceVO> list= dao.getDeptsByPro(bean);
		for(int i =0;i<list.size();i++){
			list.get(i).setProduct_name(product_name);
		}
		return list;
	}

	@Override
	public List<StockPrice> getStockPriceList(StockPrice bean) {
		return dao.getStockPriceList(bean);
	}

}
