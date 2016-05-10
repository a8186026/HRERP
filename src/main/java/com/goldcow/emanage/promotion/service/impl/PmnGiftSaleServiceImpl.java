package com.goldcow.emanage.promotion.service.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldcow.emanage.promotion.persist.PmnGiftInfoDao;
import com.goldcow.emanage.promotion.persist.PmnGiftSaleDao;
import com.goldcow.emanage.promotion.service.IPmnGiftSaleService;
import com.goldcow.emanage.util.gen.entity.PmnGiftSale;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Maps;

@Service
public class PmnGiftSaleServiceImpl implements IPmnGiftSaleService {
	private static Logger log = LoggerFactory.getLogger(PmnGiftSaleServiceImpl.class);
	/** 买赠信息操作 */
	@Autowired
	private PmnGiftSaleDao dao;
	@Autowired
	private PmnGiftInfoDao PmnGiftInfoDao;

	/**
	 * 根据ID查询
	 * 
	 * @param id ID
	 * @return 买赠信息
	 */
	@Override
	public PmnGiftSale getById(Integer id) {
		log.debug("取得买赠信息 => ID : " + id);
		return dao.get(id);
	}

	/**
	 * 查询买赠信息列表
	 * 
	 * @param bean 查询条件
	 * @return 买赠信息列表
	 */
	@Override
	public Map<String, Object> list(PmnGiftSale bean){	
		log.debug("查询买赠信息信息列表");		

		//插入查询条件
		bean.setGift_sal_chn(SysUtil.getSqlLikeParam(bean.getGift_sal_chn()));
		bean.setGift_sal_code(SysUtil.getSqlLikeParam(bean.getGift_sal_code()));
		
		
		List<PmnGiftSale> list = dao.list(bean);
		int count = dao.count(bean);

		// 封装分页对象
		Map<String, Object> result = Maps.newHashMap();
		result.put("total", count);
		result.put("rows", list);
		return result;
	}

	/**
	 * 新增买赠信息
	 * 
	 * @param bean 买赠信息
	 * @return 买赠信息
	 */
	@Override
	public PmnGiftSale add(PmnGiftSale bean, HttpServletRequest request) {
		log.debug("新增买赠信息");
		SysUtil.checkInput(bean);
		bean.setGift_sal_retailPrice(0.0);
		bean.setGift_sal_memberPrice(0.0);
		bean.setGift_sal_commission(0.0);
		bean.setGift_sal_joinDepartment(","+bean.getGift_sal_joinDepartment()+",");
		dao.add(bean);
		log.debug("新增买赠信息成功 => id : " + bean.getGift_sal_id());
		return bean;
	}

	/**
	 * 修改日期
	 * 
	 * @param bean 日期信息
	 * @return 日期信息
	 */
	@Override
	public PmnGiftSale update(PmnGiftSale bean, HttpServletRequest request) {
		log.debug("修改买赠信息");
		SysUtil.checkInput(bean);
		bean.setGift_sal_joinDepartment(","+bean.getGift_sal_joinDepartment()+",");
		dao.update(bean);
		log.debug("修改买赠信息成功 => id : " + bean.getGift_sal_id());
		return bean;
	}

	/**
	 * 删除买赠信息
	 * 
	 * @param id ID
	 */
	@Override
	public void delete(Integer id, HttpServletRequest request) {
		log.debug("删除买赠信息");
		System.out.println("id++++++++++++++++++++++++++++"+id);
		PmnGiftInfoDao.deleteBySaleId(id);
		dao.delete(id);
		log.debug("删除买赠信息成功 => id : " + id);
	}
	
	/** 获得当前最大的买赠信息编码
	 * @return 返回最大流水号
	 * */
	@Override
	public String getMaxGiftSaleCode() {
		
		log.debug("获得当前的最大编码");
		StringBuilder out  = new StringBuilder();
		Integer product_code =  dao.getMaxGiftSaleCode();
		if(product_code != null)
		{
			out.append(String.format("%06d",product_code+1));
		}
		else
			out.append("000001");
		return out.toString();
		
	}
	
	/**
	 * 查询编号是否已存在
	 * 
	 * @param value 编号
	 * @return	0表示不存在，1表示已存在
	 */
	@Override
	public Integer checkUnique(String value){
		PmnGiftSale PmnGiftSale = new PmnGiftSale();
		PmnGiftSale.setGift_sal_code(value);
		return (dao.count(PmnGiftSale) == 0)?0:1;
	}

	@Override
	public List<PmnGiftSale> getgiftSales(PmnGiftSale bean, List<Integer> proIds) {
		log.debug("获得满赠信息");
		return dao.getGiftSales(bean, proIds);
	}

}