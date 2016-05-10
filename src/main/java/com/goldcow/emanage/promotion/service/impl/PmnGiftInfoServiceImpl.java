package com.goldcow.emanage.promotion.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldcow.emanage.promotion.persist.PmnGiftInfoDao;
import com.goldcow.emanage.promotion.persist.PmnGiftSaleDao;
import com.goldcow.emanage.promotion.service.IPmnGiftInfoService;
import com.goldcow.emanage.util.gen.entity.PmnGiftInfo;
import com.goldcow.emanage.util.gen.entity.PmnGiftSale;
import com.goldcow.emanage.util.gen.entity.valueObject.promotion.GiftInfoVO;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Maps;

@Service
public class PmnGiftInfoServiceImpl implements IPmnGiftInfoService {
	private static Logger log = LoggerFactory.getLogger(PmnGiftInfoServiceImpl.class);
	/** 赠品信息操作 */
	@Autowired
	private PmnGiftInfoDao dao;
	@Autowired
	private PmnGiftSaleDao PmnGiftSaleDao;
	/**
	 * 根据ID查询
	 * 
	 * @param id ID
	 * @return 买赠信息
	 */
	@Override
	public PmnGiftInfo getById(Integer id) {
		log.debug("取得赠品信息 => ID : " + id);
		return dao.get(id);
	}

	/**
	 * 查询买赠信息列表
	 * 
	 * @param bean 查询条件
	 * @return 买赠信息列表
	 */
	@Override
	public Map<String, Object> list(PmnGiftInfo bean){	
		log.debug("查询赠品信息信息列表");		

		List<PmnGiftInfo> list = dao.list(bean);
		int count = dao.count(bean);

		// 封装分页对象
		Map<String, Object> result = Maps.newHashMap();
		result.put("total", count);
		result.put("rows", list);
		return result;
	}
	
	/**
	 * 查询买赠信息VO列表
	 * 
	 * @param bean 查询条件
	 * @return 买赠信息列表
	 */
	@Override
	public Map<String, Object> list(GiftInfoVO bean){	
		log.debug("查询赠品信息信息VO列表");		

		List<GiftInfoVO> list = dao.listVO(bean);
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
	public PmnGiftInfo add(PmnGiftInfo bean, HttpServletRequest request) {
		log.debug("新增赠品信息");
		SysUtil.checkInput(bean);
		
		
		//update PmnGiftSale information
		PmnGiftSale PmnGiftSale = PmnGiftSaleDao.get(bean.getGift_sal_id());
		PmnGiftSale.setGift_sal_retailPrice(PmnGiftSale.getGift_sal_retailPrice()+bean.getGift_retailPrice()*bean.getGift_amount());
		PmnGiftSale.setGift_sal_memberPrice(PmnGiftSale.getGift_sal_memberPrice()+bean.getGift_memberPrice()*bean.getGift_amount());
		PmnGiftSale.setGift_sal_commission(PmnGiftSale.getGift_sal_commission()+bean.getGift_commission()*bean.getGift_amount());
		if(PmnGiftSale.getGift_sal_joinProductId() == null||PmnGiftSale.getGift_sal_joinProductId().equals("")){
			PmnGiftSale.setGift_sal_joinProductId(String.valueOf(bean.getGift_productId()));
		}
		else{
			PmnGiftSale.setGift_sal_joinProductId(PmnGiftSale.getGift_sal_joinProductId()+","+bean.getGift_productId());
		}
		
		
		PmnGiftSaleDao.update(PmnGiftSale);
		dao.add(bean);
		log.debug("新增赠品信息成功 => id : " + bean.getGift_id());
		return bean;
	}

	/**
	 * 修改日期
	 * 
	 * @param bean 日期信息
	 * @return 日期信息
	 */
	@Override
	public PmnGiftInfo update(PmnGiftInfo bean, HttpServletRequest request) {
		log.debug("修改赠品信息");
		SysUtil.checkInput(bean);
		dao.update(bean);
		log.debug("修改赠品信息成功 => id : " + bean.getGift_id());
		return bean;
	}

	/**
	 * 删除买赠信息
	 * 
	 * @param id ID
	 */
	@Override
	public void delete(Integer id, HttpServletRequest request) {
		log.debug("删除赠品信息");
		System.out.println("id++++++++++++++++++++++++++++"+id);
		dao.delete(id);
		log.debug("删除赠品信息成功 => id : " + id);
	}

	@Override
	public void delete(Integer id, Integer giftSaleId,HttpServletRequest request) {
		log.debug("删除赠品信息");
		System.out.println("id++++++++++++++++++++++++++++"+id);
		PmnGiftSale sale = PmnGiftSaleDao.get(giftSaleId);
		PmnGiftInfo info = dao.get(id);
		sale.setGift_sal_retailPrice(sale.getGift_sal_retailPrice() - info.getGift_retailPrice()*info.getGift_amount());
		sale.setGift_sal_memberPrice(sale.getGift_sal_memberPrice() - info.getGift_memberPrice()*info.getGift_amount());
		sale.setGift_sal_commission(sale.getGift_sal_commission() - info.getGift_commission()*info.getGift_amount());
		
		dao.delete(id);
		log.debug("删除赠品信息成功 => id : " + id);
		GiftInfoVO vo = new GiftInfoVO();
		vo.setGift_sal_id(giftSaleId);
		List<GiftInfoVO> list = dao.listVO(vo);
		String productId = "";
		for(int i = 0;i<list.size()-1;i++){
			productId += list.get(i).getGift_productId();
			productId += ",";
		}
		if(list.size()!= 0)
			productId += list.get(list.size()-1).getGift_productId();
		
		
		sale.setGift_sal_joinProductId(productId);
		PmnGiftSaleDao.update(sale);
	}	
}