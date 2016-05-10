package com.goldcow.emanage.sale.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldcow.emanage.sale.persist.SalInfoManageDao;
import com.goldcow.emanage.sale.service.ISalInfoManageService;
import com.goldcow.emanage.util.gen.entity.SalInfoManage;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Maps;
/**
 * 产品信息
 * 
 * @author chenyuxuan
 * @version v1.0
 * @since 2015-7-13
 */

@Service
public class SalInfoManageServiceImpl implements ISalInfoManageService {
	private static Logger log = LoggerFactory.getLogger(SalInfoManageServiceImpl.class);
	/** 产品信息操作  */
	@Autowired
	private SalInfoManageDao dao;

	/**
	 * 根据ID查询
	 * 
	 * @param id ID
	 * @return 产品信息
	 */
	@Override
	public SalInfoManage getById(Integer id) {
		log.debug("取得销方信息 => ID : " + id);
		return dao.get(id);
	}

	/**
	 * 查询产品信息列表-分页
	 * 
	 * @param bean 查询条件
	 * @return 产品信息列表
	 */
	@Override
	public Map<String, Object> list(SalInfoManage bean){	
		log.debug("查询销方信息列表");		
		
		bean.setSal_code(SysUtil.getSqlLikeParam(bean.getSal_code()));
		bean.setSal_customername(SysUtil.getSqlLikeParam(bean.getSal_customername()));
		bean.setSal_shortname(SysUtil.getSqlLikeParam(bean.getSal_shortname()));
		bean.setSal_chnpy(SysUtil.getSqlLikeParam(bean.getSal_chnpy()));
		List<SalInfoManage> list = dao.list(bean);
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
	@SuppressWarnings("deprecation")
	@Override
	public SalInfoManage add(SalInfoManage bean, HttpServletRequest request) {
		
		log.debug("新增销方信息");
		SysUtil.checkInput(bean);
				
		bean.setSal_customerordertime(new Date(1970,1,1,0,0,0));			//客户订货时间
		bean.setSal_lastsaledate(new Date(1970,1,1,0,0,0));			//最后销售日期
		bean.setSal_clerkvaliddate(new Date(1970,1,1,0,0,0));			//业务员上岗证有效期

		
		bean.setSal_receivable(new Double(0));			//应收
		
		bean.setSal_createtime(new Date());
		bean.setSal_updatetime(new Date());
		bean.setStatus(GlobalVal.RECORD_STATUS.ENABLE);
		bean.setCreate_user(SysUtil.getLoginUserId(request));
		bean.setCreate_time(new Date()); 
		bean.setLast_modify_user(SysUtil.getLoginUserId(request));
		bean.setLast_modify_time(new Date());	
		dao.add(bean);
		log.debug("新增销方信息成功 => id : " + bean.getSal_id());
		
		dao.addlog(bean);
		log.debug("新增销方信息日志成功 => id : " + bean.getSal_id());
		
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
	public SalInfoManage update(SalInfoManage bean, HttpServletRequest request) {
		log.debug("修改销方信息");
		SysUtil.checkInput(bean);
		bean.setSal_updatetime(new Date());
		bean.setLast_modify_user(SysUtil.getLoginUserId(request)); 
		bean.setLast_modify_time(new Date()); 

		dao.update(bean);
		log.debug("修改销方信息成功 => id : " + bean.getSal_id());
		
		//增加日志信息
		SalInfoManage sal  = this.getById(bean.getSal_id());
		dao.addlog(sal);
		log.debug("新增销方信息日志成功 => id : " + sal.getSal_id());
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
		
		log.debug("删除销方信息");
		
		SalInfoManage bean = new SalInfoManage();	
		bean.setSal_id(id);
		bean.setStatus(GlobalVal.RECORD_STATUS.DELETED);
		bean.setLast_modify_user(SysUtil.getLoginUserId(request)); 
		bean.setLast_modify_time(new Date()); 	
		
		//增加日志信息
		SalInfoManage sal  = this.getById(bean.getSal_id());
		dao.addlog(sal);
		log.debug("新增销方信息日志成功 => id : " + sal.getSal_id());
		
		//删除销方
		dao.update(bean);
		log.debug("删除销方信息成功 => ID : " + id);
		

	}
	
	
	/**查找当前数据库表中最大的销方编码值(前面代表销方类别，后3位代表流水号)
	 * @param ticketNumber  代表前面销方类别
	 * @return 返回最大流水号
	 * */
	@Override
	public String getMaxProductCode(String ticketNumber) {
		
		log.debug("获得当前销方最大的编码");
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
}