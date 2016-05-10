package com.goldcow.emanage.product.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldcow.emanage.product.persist.ProInfoManageDao;
import com.goldcow.emanage.product.service.IProInfoManageService;
import com.goldcow.emanage.util.gen.SysTools;
import com.goldcow.emanage.util.gen.entity.ProInfoManage;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Maps;
/**
 * 产品信息
 * 
 * @author gaoxiang
 * @version v1.0
 * @since 2015-6-5
 */

@Service
public class ProInfoManageServiceImpl implements IProInfoManageService {
	private static Logger log = LoggerFactory.getLogger(ProInfoManageServiceImpl.class);
	/** 产品信息操作  */
	@Autowired
	private ProInfoManageDao dao;

	/**
	 * 根据ID查询
	 * 
	 * @param id ID
	 * @return 产品信息
	 */
	@Override
	public ProInfoManage getById(Integer id) {
		log.debug("取得产品信息 => ID : " + id);
		ProInfoManage pim = dao.get(id);
		return pim;
	}

	/**
	 * 查询产品信息列表-分页
	 * 
	 * @param bean 查询条件
	 * @return 产品信息列表
	 */
	@Override
	public Map<String, Object> list(ProInfoManage bean,String code){	
		log.debug("查询产品信息列表");		
		bean.setProduct_chnpy(SysUtil.getSqlLikeParam(SysTools.decode(bean.getProduct_chnpy())));
		//插入查询条件-产品编号
		if(bean.getProduct_code()==null||bean.getProduct_code()=="")
			bean.setProduct_code(null);
		else
			bean.setProduct_code(SysUtil.getSqlLikeParam(bean.getProduct_code())); 
		if(code!=null&&!code.equals(""))
			code = code +"%";//原来的写成这样啊code = "'"+code +"%'";不用加单引号的啊！！！坑死我了
		else
			code = null;
		
		bean.setProduct_name(SysUtil.getSqlLikeParam(bean.getProduct_name())); 
 
		List<ProInfoManage> list = dao.list(bean,code);
		int count = dao.count(bean);

		// 封装分页对象
		Map<String, Object> result = Maps.newHashMap();
		result.put("total", count);
		result.put("rows", list);
				
		return result;
	}
	
	/**
	 * 新增产品信息信息
	 * 
	 * @param bean 产品信息信息
	 * @param request HttpServletRequest
	 * @return 产品信息信息
	 */
	@Override
	public ProInfoManage add(ProInfoManage bean, HttpServletRequest request) {
		
		log.debug("新增产品信息");
		SysUtil.checkInput(bean);
		
		bean.setProduct_lastprice(Double.parseDouble("0"));
		bean.setProduct_highpurprice(Double.parseDouble("0"));
		bean.setProduct_lowpurprice(Double.parseDouble("0"));
		bean.setProduct_stocknum(Double.parseDouble("0"));
		bean.setProduct_lower(Double.parseDouble("0"));
		bean.setProduct_discount(Double.parseDouble("0"));

		bean.setProduct_licensevenddate(new Date());
		bean.setProduct_createtime(new Date());
		bean.setProduct_invaliddate(new Date());
		bean.setProduct_firststocktime(new Date());
		bean.setProduct_medinsuuploadtime(new Date());
		bean.setProduct_laststocktime(new Date());
		bean.setProduct_pricetime(new Date());
		bean.setProduct_updatetime(new Date());
		
		bean.setStatus(GlobalVal.RECORD_STATUS.ENABLE);
		bean.setCreate_user(SysUtil.getLoginUserId(request));
		bean.setCreate_time(new Date()); 
		bean.setLast_modify_user(SysUtil.getLoginUserId(request));
		bean.setLast_modify_time(new Date());	

		dao.add(bean);
		log.debug("新增产品信息成功 => id : " + bean.getProduct_id());
		
		
		dao.addlog(bean);
		log.debug("新增记录插入日志成功 => id : " + bean.getProduct_id());
		return bean;
	}

	/**
	 * 修改产品信息信息
	 * 
	 * @param bean 产品信息信息
	 * @param brand_id ID
	 * @return 产品信息信息
	 */
	@Override
	public ProInfoManage update(ProInfoManage bean, HttpServletRequest request) {
		log.debug("修改产品信息");
		
		SysUtil.checkInput(bean);
		
		bean.setLast_modify_user(SysUtil.getLoginUserId(request)); 
		bean.setLast_modify_time(new Date()); 

		
		dao.update(bean);
		log.debug("修改产品信息成功 => id : " + bean.getProduct_id());
		
		ProInfoManage pro = dao.get(bean.getProduct_id());
		System.out.println(pro.toString());
		dao.addlog(pro);
		log.debug("修改记录插入日志成功 => id : " + pro.getProduct_id());
		return bean;
	}

	/**
	 * 删除产品信息
	 * 
	 * @param brand_id ID
	 * @param request HttpServletRequest
	 */
	@Override
	public void delete(Integer id, HttpServletRequest request) {
		
		log.debug("删除产品信息");
		
/*		ProInfoManage bean = new ProInfoManage();
		
		bean.setProduct_id(id);
		bean.setStatus(GlobalVal.RECORD_STATUS.DELETED);
		bean.setLast_modify_user(SysUtil.getLoginUserId(request)); 
		bean.setLast_modify_time(new Date()); 
		
		dao.update(bean);
		log.debug("删除产品信息成功 => ID : " + id);
		
		dao.addlog(bean);
		log.debug("删除记录插入日志成功 => id : " + id);*/
		
		ProInfoManage bean = this.getById(id);
		
		dao.delete(id);
		log.debug("删除产品信息成功 => ID : " + id);
		
		dao.addlog(bean);
		log.debug("删除记录插入日志成功 => id : " + id);
		
		
	}

	/**查找当前数据库表中最大的产品编码值(前面代表产品类别，后3位代表流水号)
	 * @param ticketNumber  代表前面产品类别
	 * @return 返回最大流水号
	 * */
	@Override
	public String getMaxProductCode(String ticketNumber) {
		
		log.debug("获得当前产品最大的编码");
        StringBuilder out  = new StringBuilder(ticketNumber);
		Integer product_code =  dao.getMaxProductCode(ticketNumber);
		if(product_code != null)
		{
			out.append(String.format("%03d",product_code+1));
		}
		else
			out.append("001");
		return out.toString();
		
	}
	
	 /**
	 * 查询相似产品信息
	 * @param bean 查询条件
	 * @return 厂家档案信息列表
	 * @author wubin
	 */
	@Override
	public List<ProInfoManage> listSimilar(ProInfoManage bean) {
		log.debug("查询相似产品信息 -->ID:"+bean.getProduct_id());
		return dao.listSimilar(bean);
	}
	
	/**
	 * 查询产品折扣信息(去除重复)
	 * @param rule_id 查询条件
	 * @return 产品折扣信息
	 * @author gaoxiang
	 */
	@Override
	public List<ProInfoManage> listRuleProduct(ProInfoManage bean, Integer rule_id) {
		//查询产品名称
		bean.setProduct_code(SysUtil.getSqlLikeParam(bean.getProduct_code()));
		bean.setProduct_name(SysUtil.getSqlLikeParam(bean.getProduct_name())); 
		log.debug("查询产品折扣信息  -->ID:"+ rule_id);
		return dao.listRuleProduct(bean, rule_id);
	}
	
	/**
	 * 查询产品折扣信息
	 * @param rule_id 查询条件
	 * @return 产品折扣信息
	 * @author gaoxiang
	 */
	@Override
	public List<ProInfoManage> listRuleProductInfo(ProInfoManage bean, Integer rule_id) {
		//查询产品名称
		log.debug("查询产品折扣信息  -->ID:"+ rule_id);
		return dao.listRuleProductInfo(bean, rule_id);
	}
}