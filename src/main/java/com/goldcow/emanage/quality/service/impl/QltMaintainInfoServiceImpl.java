package com.goldcow.emanage.quality.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldcow.emanage.quality.persist.QltMaintainInfoDao;
import com.goldcow.emanage.quality.service.IQltMaintainInfoService;
import com.goldcow.emanage.stock.persist.StockInfoDao;
import com.goldcow.emanage.util.gen.entity.QltMaintainInfo;
import com.goldcow.emanage.util.gen.entity.StockInfo;
import com.goldcow.emanage.util.gen.entity.valueObject.quality.QltMaintainInfoVO;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Maps;
/**
 * 养护品种信息实体类
 * 
 * @author gaoxiang
 * @since 2015-11-23
 */

@Service
public class QltMaintainInfoServiceImpl implements IQltMaintainInfoService {
	private static Logger log = LoggerFactory.getLogger(QltMaintainInfoServiceImpl.class);
	/** 养护品种信息操作  */
	@Autowired
	private QltMaintainInfoDao dao;
	@Autowired
	private StockInfoDao stockDao;

	/**
	 * 根据ID查询
	 * 
	 * @param id ID
	 * @return 养护品种信息
	 */
	@Override
	public QltMaintainInfoVO getById(Integer id) {
		log.debug("取得养护品种信息> ID : " + id);
		return dao.getVOById(id);
	}
	
	/**
	 * 查询养护品种信息列表-分页
	 * 
	 * @param bean 查询条件
	 * @return 养护品种信息列表
	 */
	@Override
	public Map<String, Object> listMaintainInfoVO(QltMaintainInfo bean, Integer stock_storage, Integer product_immaintain, Integer pro_group_no){	
		log.debug("查询养护品种信息列表");		
	
		List<QltMaintainInfoVO> list = dao.listMaintainInfoVO(bean, stock_storage, product_immaintain, pro_group_no);
		int count = dao.countMaintainInfoVO(bean,stock_storage, product_immaintain, pro_group_no);

		// 封装分页对象
		Map<String, Object> result = Maps.newHashMap();
		result.put("total", count);
		result.put("rows", list);
				
		return result;
	}
	
	/**
	 * 查询养护品种确认信息列表-分页
	 * 
	 * @param bean 查询条件
	 * @return 养护品种确认信息列表
	 */
	@Override
	public Map<String, Object> list(QltMaintainInfoVO bean){	
		log.debug("查询养护品种确认信息列表");		
		
		//插入查询条件
		bean.setMaintain_ticketNo(SysUtil.getSqlLikeParam(bean.getMaintain_ticketNo()));
	    bean.setProduct_name(SysUtil.getSqlLikeParam(bean.getProduct_name())); 
		 
		List<QltMaintainInfoVO> list = dao.lists(bean);
		int count = dao.count(bean);

		// 封装分页对象
		Map<String, Object> result = Maps.newHashMap();
		result.put("total", count);
		result.put("rows", list);
				
		return result;
	}
	/**
	 * 新增养护品种信息
	 * 
	 * @param bean 养护品种信息
	 * @param request HttpServletRequest
	 * @return 养护品种信息	 */
	@Override
	public QltMaintainInfo add(QltMaintainInfo bean, HttpServletRequest request) {
		
		log.debug("新增养护品种信息");
		SysUtil.checkInput(bean);
		
		bean.setStatus(GlobalVal.RECORD_STATUS.ENABLE);
		bean.setCreate_user(SysUtil.getLoginUserId(request));
		bean.setMaintain_person(SysUtil.getLoginUser(request).getDisplay_name());
		bean.setCreate_time(new Date()); 
		bean.setLast_modify_user(SysUtil.getLoginUserId(request));
		bean.setLast_modify_time(new Date());	
		
		dao.add(bean);
		
		log.debug("新增养护品种信息成功 => id : " + bean.getMaintain_id());
		return bean;
	}

	/**
	 * 修改养护品种信息
	 * 
	 * @param bean 养护品种信息
	 * @param brand_id ID
	 * @return 养护品种信息
	 */
	@Override
	public QltMaintainInfo update(QltMaintainInfo bean, HttpServletRequest request) {
		log.debug("修改养护品种信息");
		SysUtil.checkInput(bean);
		
		bean.setMaintain_time(new Date()); 
		bean.setMaintain_person(SysUtil.getLoginUser(request).getDisplay_name());
		bean.setLast_modify_user(SysUtil.getLoginUserId(request)); 
		bean.setLast_modify_time(new Date()); 

		dao.update(bean);
		log.debug("修改养护品种信息成功 => id : " + bean.getMaintain_id());
		return bean;
	}

	/**
	 * 删除养护品种信息
	 * 
	 * @param brand_id ID
	 * @param request HttpServletRequest
	 */
	@Override
	public void delete(Integer id, HttpServletRequest request) {
		
		log.debug("删除养护品种信息");
		
		dao.delete(id);
		
		log.debug("删除养护品种信息成功 => ID : " + id);
		 
	}
	
	/**
	 * 获取最大入库票号
	 * */
	@Override
	public String getMaxMaintainTicket() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String date = dateFormat.format(new Date());
        StringBuilder out  = new StringBuilder(date);
		Integer ticketID =  dao.getMaxMaintainTicket(date);
		if(ticketID != null){
			out.append(String.format("%04d",ticketID+1));
		}
		else{
			out.append("0001");
		}
		return "Y"+out.toString();
	}

	/**
	 * 养护操作
	 * @param data 需要养护的id
	 * @param oldMaintainTicket 前台养护票号
	 * @param request
	 * */
	@Override
	public String maintain(String data, String oldMaintainTicket, HttpServletRequest request) {
		//需要添加的List
		List<QltMaintainInfo> qltMaintainInfos = new ArrayList<QltMaintainInfo>();

		List<StockInfo> stockInfos = new ArrayList<StockInfo>();
				
		//需要查询的IDs
		List<Integer> ids = new ArrayList<Integer>();
		
		//获取养护单号
		String maintain_ticketNo = getMaxMaintainTicket();
		
		System.out.println("data::"+data);
		String datas[] = data.split("\\|");
		System.out.println("data.length::"+datas.length);
		
		for(int i=0;i<datas.length;i++){
			String subdatas[] = datas[i].split("_");
			System.out.println("subdatas.length::"+subdatas.length);
			//往数据库添加养护票号
			QltMaintainInfo qmi = new QltMaintainInfo();
			
			qmi.setMaintain_ticketNo(maintain_ticketNo);
			
			qmi.setStock_info_id(Integer.parseInt(subdatas[0]));
			qmi.setProduct_id(Integer.parseInt(subdatas[1]));
			qmi.setMaintain_number(Double.parseDouble(subdatas[2]));
			qmi.setMaintain_checkTime(new Date());
			qmi.setMaintain_time(new Date());
			qmi.setMaintain_storagePlace(subdatas[3]);
			qmi.setMaintain_days(Integer.parseInt(subdatas[4]));

			qmi.setCreate_user(SysUtil.getLoginUserId(request));
			qmi.setCreate_time(new Date());
			qmi.setLast_modify_user(SysUtil.getLoginUserId(request));
			qmi.setLast_modify_time(new Date());
			qmi.setStatus(GlobalVal.RECORD_STATUS.ENABLE);
			
			qltMaintainInfos.add(qmi);
			
			ids.add(Integer.parseInt(subdatas[0]));
		}
		
		dao.addMaintainInfos(qltMaintainInfos);
		
		for(int i=0;i<qltMaintainInfos.size();i++){
			//填充入库对象
			QltMaintainInfo qmi = qltMaintainInfos.get(i);
			StockInfo stockInfo = new StockInfo();
			stockInfo.setStock_maintaintime(qmi.getMaintain_time());
			stockInfo.setMaintain_days(qmi.getMaintain_days());
			stockInfo.setStock_info_id(qmi.getStock_info_id());
			
			stockInfos.add(stockInfo);
		}
		stockDao.updateStockInfos(stockInfos);
		
		//如果新票号和前台票号不同，则传入最新票号到前台显示
		if(!oldMaintainTicket.equals(maintain_ticketNo)){
			return maintain_ticketNo;
		}
		return null;
	}
}