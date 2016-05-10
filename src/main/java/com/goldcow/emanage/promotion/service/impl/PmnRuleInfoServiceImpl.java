package com.goldcow.emanage.promotion.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldcow.emanage.promotion.persist.PmnRuleInfoDao;
import com.goldcow.emanage.promotion.service.IPmnRuleInfoService;
import com.goldcow.emanage.util.gen.SysTools;
import com.goldcow.emanage.util.gen.entity.PmnRuleInfo;
import com.goldcow.emanage.util.gen.entity.PmnRuleProduct;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Maps;
/**
 * 折扣规则设定
 * 
 * @author gaoxiang
 * @since 2015-10-27
 */

@Service
public class PmnRuleInfoServiceImpl implements IPmnRuleInfoService {
	private static Logger log = LoggerFactory.getLogger(PmnRuleInfoServiceImpl.class);
	/** 折扣规则信息操作  */
	@Autowired
	private PmnRuleInfoDao dao;

	/**
	 * 根据ID查询
	 * 
	 * @param id ID
	 * @return 折扣规则信息
	 */
	@Override
	public PmnRuleInfo getById(Integer id) {
		log.debug("取得折扣规则信息 => ID : " + id);
		return dao.get(id);
	}

	/**
	 * 查询折扣规则信息列表-分页
	 * 
	 * @param bean 查询条件
	 * @return 折扣规则信息列表
	 */
	@Override
	public Map<String, Object> list(PmnRuleInfo bean){	
		log.debug("查询折扣规则信息列表");		
		
		//插入查询条件-折扣规则信息编码 
		/*bean.setEth_pzwh(SysUtil.getSqlLikeParam(SysTools.decode(bean.getEth_pzwh())));
		bean.setEth_tym(SysUtil.getSqlLikeParam(SysTools.decode(bean.getEth_tym())));*/
		
		bean.setRule_name(SysUtil.getSqlLikeParam(SysTools.decode(bean.getRule_name())));
		
		List<PmnRuleInfo> list = dao.list(bean);
		int count = dao.count(bean);

		// 封装分页对象
		Map<String, Object> result = Maps.newHashMap();
		result.put("total", count); 
		result.put("rows", list);
				
		return result;
	}

	/**
	 * 新增折扣规则信息 
	 * 
	 * @param bean 折扣规则信息 
	 * @param request HttpServletRequest
	 * @return 折扣规则信息 
	 */
	@Override
	public PmnRuleInfo add(PmnRuleInfo bean, HttpServletRequest request) {
		
		log.debug("新增折扣规则信息");
		SysUtil.checkInput(bean);
		
		bean.setRule_priority(dao.getMaxPriority()==null?1:dao.getMaxPriority());
		bean.setRule_addperson(SysUtil.getLoginUser(request).getDisplay_name());
		bean.setStatus(GlobalVal.RECORD_STATUS.ENABLE);
		bean.setRule_addtime(new Date()); 
		bean.setRule_set(","+bean.getRule_set()+",");
		dao.add(bean);
		log.debug("新增折扣规则信息成功 => id : " + bean.getRule_id());
		return bean;
	}

	/**
	 * 修改折扣规则信息 
	 * 
	 * @param bean 折扣规则信息 
	 * @param rule_id ID
	 * @return 折扣规则信息 
	 */
	@Override
	public PmnRuleInfo update(PmnRuleInfo bean, HttpServletRequest request) {
		log.debug("修改折扣规则信息");
		SysUtil.checkInput(bean);
		bean.setRule_set(","+bean.getRule_set()+",");
		dao.update(bean);
		log.debug("修改折扣规则信息成功 => id : " + bean.getRule_id());
		return bean;
	}

	/**
	 * 删除折扣规则信息
	 * 
	 * @param rule_id ID
	 * @param request HttpServletRequest
	 */
	@Override
	public void delete(Integer id, HttpServletRequest request) {
		
		log.debug("删除折扣规则信息");
		
		dao.delete(id);
		
		log.debug("删除折扣规则信息成功 => ID : " + id);
	}
	
	/**
	 * 新增产品折扣规则信息
	 * 
	 * @param bean 产品折扣规则信息
	 * @param request HttpServletRequest
	 * @return 产品折扣规则信息
	 */
	@Override
	public PmnRuleProduct addRuleProduct(PmnRuleProduct bean, HttpServletRequest request) {
		
		log.debug("新增产品折扣规则信息");
		SysUtil.checkInput(bean);
		
		bean.setStatus(GlobalVal.RECORD_STATUS.ENABLE);
		bean.setCreate_time(new Date());
		bean.setCreate_user(SysUtil.getLoginUserId(request));
		dao.addRuleProduct(bean);
		log.debug("新增产品折扣规则信息成功 => id : " + bean.getRule_product_id());
		return bean;
	}

	/**
	 * 修改产品折扣规则信息 
	 * 
	 * @param bean 产品折扣规则信息 
	 * @param rule_id ID
	 * @return 产品折扣规则信息 
	 */
	@Override
	public PmnRuleProduct updateRuleProduct(PmnRuleProduct bean, HttpServletRequest request) {
		log.debug("修改产品折扣规则信息");
		SysUtil.checkInput(bean);
		dao.updateRuleProduct(bean);
		log.debug("修改产品折扣规则信息成功 => id : " + bean.getRule_product_id());
		return bean;
	}
	
	/**
	 *批量添加产品折扣规则信息 
	 * 
	 * @param bean 产品折扣规则信息 
	 * @param String data, Integer rule_id
	 * @return 产品折扣规则信息 
	 */
	@Override
	public String chooseProducts(String data, Integer rule_id , HttpServletRequest request) {
		//需要添加的List
		List<PmnRuleProduct> pmnRuleProducts = new ArrayList<PmnRuleProduct>();
		
		String datas[] = data.split("_");
		
		for(int i = 0;i < datas.length; i++){
			
			PmnRuleProduct pmnRuleProduct = new PmnRuleProduct();
			pmnRuleProduct.setRule_id(rule_id);
			pmnRuleProduct.setProduct_id(Integer.parseInt(datas[i]));
			
			pmnRuleProduct.setCreate_time(new Date());
			pmnRuleProduct.setCreate_user(SysUtil.getLoginUserId(request));
			pmnRuleProduct.setStatus(GlobalVal.RECORD_STATUS.ENABLE);
			 
			pmnRuleProducts.add(pmnRuleProduct);
		}
		dao.addRuleProducts(pmnRuleProducts);
		
		return null;
	}
	
	/**
	 * 删除产品折扣规则信息
	 * 
	 * @param rule_id ID
	 * @param request HttpServletRequest
	 */
	@Override
	public void deleteRuleProduct(Integer rule_id, Integer product_id , HttpServletRequest request) {
		
		log.debug("删除折扣规则信息");
		PmnRuleProduct pmnRuleProduct = new PmnRuleProduct();
		pmnRuleProduct.setRule_id(rule_id);
		pmnRuleProduct.setProduct_id(product_id);
		dao.deleteRuleProduct(pmnRuleProduct);
		log.debug("删除折扣规则信息成功 => ID : " + rule_id);
		
	}
	
	/**
	 * 保存折扣规则信息优先级顺序
	 * @param String data
	 * @return PmnRuleProduct
	 */
	@Override
	public String savePriority(String data, HttpServletRequest request) {
		//需要添加的List
		List<PmnRuleInfo> pmnRuleInfos = new ArrayList<PmnRuleInfo>();
			
		System.out.println("data::"+data);	
		String datas[] = data.split("\\|"); 
		System.out.println("data.length::"+datas.length);
		
		for(int i = 0;i < datas.length; i++){
			
			String subdatas[] = datas[i].split("_");
			System.out.println("subdatas.length::"+subdatas[0]);
			System.out.println("subdatas.length::"+subdatas[1]);
			//往数据库添加入库票号和打印格式
			PmnRuleInfo pmnRuleInfo = new PmnRuleInfo(); 
			pmnRuleInfo.setRule_id(Integer.parseInt(subdatas[0]));
			pmnRuleInfo.setRule_priority(Integer.parseInt(subdatas[1]));
			 
			pmnRuleInfos.add(pmnRuleInfo);
		}
		dao.savePriority(pmnRuleInfos);
		
		return null;
	}

	/**
	 * 查询满足条件的折扣规则
	 * @param bean 查询条件
	 * @return 折扣规则信息列表
	 */
	@Override
	public PmnRuleInfo isRuleProduct(Integer product_id) {
		//获取日期时间
		Date date = new Date();
		String week = null;
		int weekDay = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
		switch(weekDay){
		case 1:
			week = "%日%";
			break;
		case 2:
			week = "%一%";
			break;
		case 3:
			week = "%二%";
			break;
		case 4:
			week = "%三%";
			break;
		case 5:
			week = "%四%";
			break;
		case 6:
			week = "%五%";
			break;
		case 7:
			week = "%六%";
			break;
		}
		return dao.isRuleProduct(date, week, "%,"+Calendar.getInstance().get(Calendar.DATE)+",%",product_id);
	}
	
}