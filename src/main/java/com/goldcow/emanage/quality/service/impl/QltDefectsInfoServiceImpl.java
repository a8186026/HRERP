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

import com.goldcow.emanage.quality.persist.QltDefectsInfoDao;
import com.goldcow.emanage.quality.service.IQltDefectsInfoService;
import com.goldcow.emanage.stock.persist.StockInfoDao;
import com.goldcow.emanage.util.gen.SysTools;
import com.goldcow.emanage.util.gen.entity.QltDefectsInfo;
import com.goldcow.emanage.util.gen.entity.StockInfo;
import com.goldcow.emanage.util.gen.entity.valueObject.quality.QltDefectsInfoVO;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Maps;
/**
 * 不合格品种信息实体类
 * 
 * @author gaoxiang
 * @since 2015-11-30
 */

@Service
public class QltDefectsInfoServiceImpl implements IQltDefectsInfoService {
	private static Logger log = LoggerFactory.getLogger(QltDefectsInfoServiceImpl.class);
	/** 不合格品种信息操作  */
	@Autowired
	private QltDefectsInfoDao dao;
	@Autowired
	private StockInfoDao stockDao;

	/**
	 * 根据ID查询
	 * 
	 * @param id ID
	 * @return 不合格品种信息
	 */
	@Override
	public QltDefectsInfoVO getById(Integer id) {
		log.debug("取得不合格品种信息> ID : " + id);
		return dao.getVOById(id);
	}
	
	/**
	 * 查询不合格品种确认信息列表-分页
	 * 
	 * @param bean 查询条件
	 * @return 不合格品种确认信息列表
	 */
	@Override
	public Map<String, Object> list(QltDefectsInfoVO bean){	
		log.debug("查询不合格品种确认信息列表");		
		
		//插入查询条件
	    bean.setProduct_name(SysUtil.getSqlLikeParam(bean.getProduct_name())); 
		 
		List<QltDefectsInfoVO> list = dao.lists(bean);
		int count = dao.count(bean);

		// 封装分页对象
		Map<String, Object> result = Maps.newHashMap();
		result.put("total", count);
		result.put("rows", list);
				
		return result;
	}
	/**
	 * 新增不合格品种信息
	 * 
	 * @param bean 不合格品种信息
	 * @param request HttpServletRequest
	 * @return 不合格品种信息	 */
	@Override
	public QltDefectsInfo add(QltDefectsInfo bean, HttpServletRequest request) {
		
		log.debug("新增不合格品种信息");
		SysUtil.checkInput(bean);
		
		bean.setStatus(GlobalVal.RECORD_STATUS.ENABLE);
		bean.setCreate_user(SysUtil.getLoginUserId(request));
		bean.setDefects_person(SysUtil.getLoginUser(request).getDisplay_name());
		bean.setCreate_time(new Date()); 
		bean.setLast_modify_user(SysUtil.getLoginUserId(request));
		bean.setLast_modify_time(new Date());	
		
		//初始化各个时间
		bean.setDefects_date(new Date()); 
		bean.setDefects_checkTime(new Date()); 
		bean.setDefects_profitLossTime(new Date()); 
		bean.setDefects_destructionTime(new Date()); 
		bean.setDefects_destructionDate(new Date()); 
		
		bean.setDefects_breakageTime(new Date()); 
		bean.setDefects_purchasedTime(new Date()); 
		bean.setDefects_qualitycdTime(new Date()); 
		bean.setDefects_storagetdTime(new Date()); 
		bean.setDefects_qaTime(new Date()); 
		bean.setDefects_financialdTime(new Date()); 
		bean.setDefects_managerTime(new Date()); 
		bean.setDefects_printTime(new Date()); 
		bean.setDefects_destructionPrintTime(new Date()); 
		
		//初始化各个审核状态
		bean.setDefects_check(GlobalVal.DEFECTS_STATUS.DEFECTS_UNCHECK);
		bean.setDefects_profitLoss(GlobalVal.DEFECTS_STATUS.PROFITLOSS_UNCHECK);
		bean.setDefects_breakage(GlobalVal.DEFECTS_STATUS.BREAKAGE_UNCHECK);
		bean.setDefects_destruction(GlobalVal.DEFECTS_STATUS.DESTRUCTION_UNCHECK);
		
		bean.setDefects_purchasedCheck(GlobalVal.DEFECTS_STATUS.PURCHASE_UNCHECK);
		bean.setDefects_qualitycdCheck(GlobalVal.DEFECTS_STATUS.QUALITYC_UNCHECK);
		bean.setDefects_storagetdCheck(GlobalVal.DEFECTS_STATUS.STORAGET_UNCHECK);
		bean.setDefects_qaCheck(GlobalVal.DEFECTS_STATUS.QA_UNCHECK);
		bean.setDefects_financialdCheck(GlobalVal.DEFECTS_STATUS.FINANCIAL_UNCHECK);
		bean.setDefects_managerCheck(GlobalVal.DEFECTS_STATUS.MANAGER_UNCHECK);
		bean.setDefects_draft(1);
		
		dao.add(bean);
		log.debug("新增不合格品种信息成功 => id : " + bean.getDefects_id());
		
		dao.addLog(bean);
		log.debug("新增不合格品种信息日志记录添加成功");
		
		return bean;
	}

	/**
	 * 修改不合格品种信息
	 * 
	 * @param bean 不合格品种信息
	 * @param brand_id ID
	 * @return 不合格品种信息
	 */
	@Override
	public QltDefectsInfo update(QltDefectsInfo bean, HttpServletRequest request) {
		log.debug("修改不合格品种信息");
		SysUtil.checkInput(bean);
		
		bean.setDefects_date(new Date()); 
		bean.setDefects_person(SysUtil.getLoginUser(request).getDisplay_name());
		bean.setLast_modify_user(SysUtil.getLoginUserId(request)); 
		bean.setLast_modify_time(new Date()); 
		
		dao.update(bean);
		log.debug("修改不合格品种信息成功 => id : " + bean.getDefects_id());
		
		dao.addLog(bean);
		log.debug("修改不合格品种信息日志记录添加成功");
		
		return bean;
	}

	/**
	 * 删除不合格品种信息
	 * 
	 * @param brand_id ID
	 * @param request HttpServletRequest
	 */
	@Override
	public void delete(Integer id, HttpServletRequest request) {
		
		QltDefectsInfo bean = this.getById(id);
		
		dao.delete(id);
		log.debug("删除不合格品种信息成功 => ID : " + id);
		
		dao.addLog(bean);
		log.debug("删除不合格品种信息日志记录添加成功");
	}
	
	/**
	 * 获取最大不合格票号
	 * */
	@Override
	public String getMaxDefectsTicket() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String date = dateFormat.format(new Date());
        StringBuilder out  = new StringBuilder(date);
		Integer ticketID =  dao.getMaxDefectsTicket(date);
		if(ticketID != null){
			out.append(String.format("%04d",ticketID+1));
		}
		else{
			out.append("0001");
		}
		
		return "B"+out.toString();
	}
	
	/**
	 * 获取最大报损票号
	 * */
	@Override
	public String getMaxDefectsProfitTicket() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String date = dateFormat.format(new Date());
        StringBuilder out  = new StringBuilder(date);
		Integer ticketID =  dao.getMaxDefectsProfitTicket(date);
		if(ticketID != null){
			out.append(String.format("%04d",ticketID+1));
		}
		else{
			out.append("0001");
		}
		
		return "E"+out.toString();
	}
	
	/**
	 * 获取最大损益票号
	 * */
	@Override
	public String getMaxDefectsBreakageTicket() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String date = dateFormat.format(new Date());
        StringBuilder out  = new StringBuilder(date);
		Integer ticketID =  dao.getMaxDefectsBreakageTicket(date);
		if(ticketID != null){
			out.append(String.format("%04d",ticketID+1));
		}
		else{
			out.append("0001");
		}
		
		return "S"+out.toString();
	}
	
	/**
	 * 获取最大销毁票号
	 * */
	@Override
	public String getMaxDefectsDestructionTicket() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String date = dateFormat.format(new Date());
        StringBuilder out  = new StringBuilder(date);
		Integer ticketID =  dao.getMaxDefectsDestructionTicket(date);
		if(ticketID != null){
			out.append(String.format("%04d",ticketID+1));
		}
		else{
			out.append("0001");
		}
		
		return "X"+out.toString();
	}
	
	/**
	 * 新增不合格品种信息
	 * 
	 * @param bean 不合格品种信息
	 * @param request HttpServletRequest
	 * @return 不合格品种信息	 
	 */
	@Override
	public String save(String data, String oldDefectsTicket,  HttpServletRequest request) {
		//需要添加的List
		List<Integer> ids = new ArrayList<Integer>();
		
		//获取票号
		String defects_ticketNo = getMaxDefectsTicket();
		
		System.out.println("data::"+data);
		String datas[] = data.split("_");
		System.out.println("data.length::"+datas.length);
		if(!datas[0].equals("")){
			for(int i=0;i<datas.length;i++)
				ids.add(Integer.parseInt(datas[i]));
		}
		
		dao.updateDefectsInfos(defects_ticketNo,0,ids);
		
		//如果新票号和前台票号不同，则传入最新票号到前台显示
		if(!oldDefectsTicket.equals(defects_ticketNo)){
			return defects_ticketNo;
		}
		
		return null;
	}
	
	/**
	 * 不合格品种信息确认通过
	 * 
	 * @param request HttpServletRequest
	 * @param id 审核通过品种的id
	 * @return 操作结果
	 */
	@Override
	public boolean check(String data ,HttpServletRequest request) {
		System.out.println("data:"+data);
		//解析前台数据
		String checks[] = data.split("\\|");//使用转义字符,否则出错
		System.out.println("length:"+checks.length+" ++++++ "+checks[0]);
		for(int i=0;i<checks.length;i++){
			System.out.println("checks:"+checks[i]);
			String result[] = checks[i].split("_");
			//更新不合格品种数据库表数据
			QltDefectsInfo qdi = dao.get(Integer.parseInt(result[0]));
			qdi.setDefects_checkSuggestion(SysTools.decode(result[1]));
			qdi.setDefects_checkPerson(SysUtil.getLoginUser(request).getDisplay_name());
			qdi.setDefects_checkTime(new Date());
			qdi.setDefects_check(GlobalVal.DEFECTS_STATUS.DEFECTS_CHECK);
			
			dao.update(qdi);
			
			dao.addLog(qdi);
			log.debug("不合格品种信息确认日志记录添加成功");
		}
		return true;
	}
	/**
	 * 不合格品种信息报损申请
	 * 
	 * @param request HttpServletRequest
	 * @param id 审核通过品种的id
	 * @return 操作结果
	 */
	@Override
	public String profit(String data ,String oldProfitTicket,HttpServletRequest request) {
		//获取报损票号
		String defects_profitTicketNo = getMaxDefectsProfitTicket();
				
		System.out.println("data:"+data);
		//解析前台数据
		String checks[] = data.split("\\|");//使用转义字符,否则出错
		System.out.println("length:"+checks.length+" ++++++ "+checks[0]);
		for(int i=0;i<checks.length;i++){
			System.out.println("checks:"+checks[i]);
			String result[] = checks[i].split("_");
			//更新不合格品种数据库表数据
			QltDefectsInfo qdi = dao.get(Integer.parseInt(result[0]));
			qdi.setDefects_profitReason(SysTools.decode(result[1]));
			qdi.setDefects_profitLossTime(new Date());
			qdi.setDefects_profitLoss(GlobalVal.DEFECTS_STATUS.PROFITLOSS_CHECK);
			qdi.setDefects_profitTicketNo(defects_profitTicketNo);
			
			dao.update(qdi);
			
			dao.addLog(qdi);
			log.debug("不合格品种信息损益申请日志记录添加成功");
			
			StockInfo si = stockDao.get(qdi.getStock_info_id());
			si.setStock_occupyNumber(si.getStock_occupyNumber() + qdi.getDefects_number());
			stockDao.update(si);
			
			log.debug("修改该批号产品占用数量");
		}
		
		//如果新票号和前台票号不同，则传入最新票号到前台显示
		if(!oldProfitTicket.equals(defects_profitTicketNo)){
			return defects_profitTicketNo;
		}
		return null;
	}
	
	/**
	 * 不合格品种信息-采购部审核
	 * 
	 * @param request HttpServletRequest
	 * @param id 审核通过品种的id
	 * @return 操作结果
	 */
	@Override
	public boolean purchasedCheck(String data ,HttpServletRequest request) {
		System.out.println("data:"+data);
		//解析前台数据
		String checks[] = data.split("\\|");//使用转义字符,否则出错
		System.out.println("length:"+checks.length+" ++++++ "+checks[0]);
		for(int i=0;i<checks.length;i++){
			System.out.println("checks:"+checks[i]);
			String result[] = checks[i].split("_");
			//更新不合格品种数据库表数据
			QltDefectsInfo qdi = dao.get(Integer.parseInt(result[0]));
			qdi.setDefects_purchasedOpinion(SysTools.decode(result[1]));
			qdi.setDefects_purchasedTime(new Date());
			qdi.setDefects_purchasedPerson(SysUtil.getLoginUser(request).getDisplay_name()); 
			qdi.setDefects_purchasedCheck(GlobalVal.DEFECTS_STATUS.PURCHASE_CHECK);
			
			dao.update(qdi);
			
			dao.addLog(qdi);
			log.debug("不合格品种信息-采购部审核-日志记录添加成功");
		}
		return true;
	}
	
	/**
	 * 不合格品种信息-质管部审核
	 * 
	 * @param request HttpServletRequest
	 * @param id 审核通过品种的id
	 * @return 操作结果
	 */
	@Override
	public boolean qualitycdCheck(String data ,HttpServletRequest request) {
		System.out.println("data:"+data);
		//解析前台数据
		String checks[] = data.split("\\|");//使用转义字符,否则出错
		System.out.println("length:"+checks.length+" ++++++ "+checks[0]);
		for(int i=0;i<checks.length;i++){
			System.out.println("checks:"+checks[i]);
			String result[] = checks[i].split("_");
			//更新不合格品种数据库表数据
			QltDefectsInfo qdi = dao.get(Integer.parseInt(result[0]));
			qdi.setDefects_qualitycdOpinion(SysTools.decode(result[1]));
			qdi.setDefects_qualitycdTime(new Date());
			qdi.setDefects_qualitycdPerson(SysUtil.getLoginUser(request).getDisplay_name()); 
			qdi.setDefects_qualitycdCheck(GlobalVal.DEFECTS_STATUS.QUALITYC_CHECK);

			dao.update(qdi);
			
			dao.addLog(qdi);
			log.debug("不合格品种信息-质管部审核-日志记录添加成功");
		}
		return true;
	}
	
	
	/**
	 * 不合格品种信息-储运部审核
	 * 
	 * @param request HttpServletRequest
	 * @param id 审核通过品种的id
	 * @return 操作结果
	 */
	@Override
	public boolean storagetdCheck(String data ,HttpServletRequest request) {
		System.out.println("data:"+data);
		//解析前台数据
		String checks[] = data.split("\\|");//使用转义字符,否则出错
		System.out.println("length:"+checks.length+" ++++++ "+checks[0]);
		for(int i=0;i<checks.length;i++){
			System.out.println("checks:"+checks[i]);
			String result[] = checks[i].split("_");
			//更新不合格品种数据库表数据
			QltDefectsInfo qdi = dao.get(Integer.parseInt(result[0]));
			qdi.setDefects_storagetdOpinion(SysTools.decode(result[1]));
			qdi.setDefects_storagetdTime(new Date());
			qdi.setDefects_storagetdPerson(SysUtil.getLoginUser(request).getDisplay_name()); 
			qdi.setDefects_storagetdCheck(GlobalVal.DEFECTS_STATUS.STORAGET_CHECK);
			
			dao.update(qdi);
			
			dao.addLog(qdi);
			log.debug("不合格品种信息-储运部审核-日志记录添加成功");
		}
		return true;
	}
	
	/**
	 * 不合格品种信息-质量负责人审核
	 * 
	 * @param request HttpServletRequest
	 * @param id 审核通过品种的id
	 * @return 操作结果
	 */
	@Override
	public boolean qaCheck(String data ,HttpServletRequest request) {
		System.out.println("data:"+data);
		//解析前台数据
		String checks[] = data.split("\\|");//使用转义字符,否则出错
		System.out.println("length:"+checks.length+" ++++++ "+checks[0]);
		for(int i=0;i<checks.length;i++){
			System.out.println("checks:"+checks[i]);
			String result[] = checks[i].split("_");
			//更新不合格品种数据库表数据
			QltDefectsInfo qdi = dao.get(Integer.parseInt(result[0]));
			qdi.setDefects_qaOpinion(SysTools.decode(result[1]));
			qdi.setDefects_qaTime(new Date());
			qdi.setDefects_qaPerson(SysUtil.getLoginUser(request).getDisplay_name()); 
			qdi.setDefects_qaCheck(GlobalVal.DEFECTS_STATUS.QA_CHECK);
	 
			dao.update(qdi);
			
			dao.addLog(qdi);
			log.debug("不合格品种信息-质量负责人审核-日志记录添加成功");
		}
		return true;
	}
	
	/**
	 * 不合格品种信息-财务部审核
	 * 
	 * @param request HttpServletRequest
	 * @param id 审核通过品种的id
	 * @return 操作结果
	 */
	@Override
	public boolean financialdCheck(String data ,HttpServletRequest request) {
		System.out.println("data:"+data);
		//解析前台数据
		String checks[] = data.split("\\|");//使用转义字符,否则出错
		System.out.println("length:"+checks.length+" ++++++ "+checks[0]);
		for(int i=0;i<checks.length;i++){
			System.out.println("checks:"+checks[i]);
			String result[] = checks[i].split("_");
			//更新不合格品种数据库表数据
			QltDefectsInfo qdi = dao.get(Integer.parseInt(result[0]));
			qdi.setDefects_financialdOpinion(SysTools.decode(result[1]));
			qdi.setDefects_financialdTime(new Date());
			qdi.setDefects_financialdPerson(SysUtil.getLoginUser(request).getDisplay_name()); 
			qdi.setDefects_financialdCheck(GlobalVal.DEFECTS_STATUS.FINANCIAL_CHECK);
			
			dao.update(qdi);
			
			dao.addLog(qdi);
			log.debug("不合格品种信息-财务部审核-日志记录添加成功");
		}
		return true;
	}
	
	/**
	 * 不合格品种信息-经理审核
	 * 
	 * @param request HttpServletRequest
	 * @param id 审核通过品种的id
	 * @return 操作结果
	 */
	@Override
	public boolean managerCheck(String data ,HttpServletRequest request) {
		System.out.println("data:"+data);
		//解析前台数据
		String checks[] = data.split("\\|");//使用转义字符,否则出错
		System.out.println("length:"+checks.length+" ++++++ "+checks[0]);
		for(int i=0;i<checks.length;i++){
			System.out.println("checks:"+checks[i]);
			String result[] = checks[i].split("_");
			//更新不合格品种数据库表数据
			QltDefectsInfo qdi = dao.get(Integer.parseInt(result[0]));
			qdi.setDefects_managerOpinion(SysTools.decode(result[1]));
			qdi.setDefects_managerTime(new Date());
			qdi.setDefects_managerPerson(SysUtil.getLoginUser(request).getDisplay_name()); 
			qdi.setDefects_managerCheck(GlobalVal.DEFECTS_STATUS.MANAGER_CHECK);
			
			dao.update(qdi);
			
			dao.addLog(qdi);
			log.debug("不合格品种信息-经理审核-日志记录添加成功");
		}
		return true;
	}
	
	/**
	 * 不合格品种信息-报损确认
	 * 
	 * @param request HttpServletRequest
	 * @param id 审核通过品种的id
	 * @return 操作结果
	 */
	@Override
	public String breakageCheck(String data ,String oldBreakageTicket, HttpServletRequest request) {
		//获取损益票号
		String defects_breakageTicketNo = getMaxDefectsBreakageTicket();
		
		System.out.println("data:"+data);
		//解析前台数据
		String checks[] = data.split("\\|");//使用转义字符,否则出错
		System.out.println("length:"+checks.length+" ++++++ "+checks[0]);
		for(int i=0;i<checks.length;i++){
			System.out.println("checks:"+checks[i]);
			String result[] = checks[i].split("_");
			//更新不合格品种数据库表数据
			QltDefectsInfo qdi = dao.get(Integer.parseInt(result[0]));
			qdi.setDefects_breakageReason(SysTools.decode(result[1]));
			qdi.setDefects_breakageTime(new Date());
			qdi.setDefects_breakagePerson(SysUtil.getLoginUser(request).getDisplay_name()); 
			qdi.setDefects_breakage(GlobalVal.DEFECTS_STATUS.BREAKAGE_CHECK);
			qdi.setDefects_breakageTicketNo(defects_breakageTicketNo);
			
			dao.update(qdi);
			
			dao.addLog(qdi);
			log.debug("不合格品种信息-报损确认-日志记录添加成功");
			
			StockInfo si = stockDao.get(qdi.getStock_info_id());
			si.setStock_occupyNumber(si.getStock_occupyNumber() - qdi.getDefects_number());
			si.setStock_storageNumber(si.getStock_storageNumber() - qdi.getDefects_number());
			stockDao.update(si);
			
			log.debug("修改该批号产品占用数量和库存数量");
		}
		//如果新票号和前台票号不同，则传入最新票号到前台显示
		if(!oldBreakageTicket.equals(defects_breakageTicketNo)){
			return defects_breakageTicketNo;
		}
		
		return null;
	}
	
	/**
	 * 不合格品种信息销毁通过
	 * 
	 * @param request HttpServletRequest
	 * @param id 审核通过品种的id
	 * @return 操作结果
	 */
	@Override
	public QltDefectsInfo destruction(QltDefectsInfo bean, HttpServletRequest request) {
		log.debug("修改不合格品种信息");
		/*//获取销毁票号
		String defects_destrucTicketNo = getMaxDefectsDestructionTicket();
		//如果新票号和前台票号不同，则传入最新票号到前台显示
		if(!oldDestrucTicket.equals(defects_destrucTicketNo)){
			return defects_destrucTicketNo;
		}*/
				
		SysUtil.checkInput(bean);
		
		bean.setDefects_destruction(GlobalVal.DEFECTS_STATUS.DESTRUCTION_CHECK);
		bean.setDefects_destructionTime(new Date());
		bean.setDefects_destructionPerson(SysUtil.getLoginUser(request).getDisplay_name()); 
		//bean.setDefects_destrucTicketNo(defects_destrucTicketNo);
		 
		dao.update(bean);
		log.debug("销毁不合格品种信息修改成功 => id : " + bean.getDefects_id());
		
		dao.addLog(dao.get(bean.getDefects_id()));
		log.debug("销毁不合格品种信息日志记录添加成功");
		
		return bean;
	}

	/**
	 * 直接报损
	 * @param data 需要操作的id
	 * @param oldIntakeTicket 前台报损票号
	 * */
	@Override
	public String profitCheck(String data, String oldProfitTicket, HttpServletRequest request) {
		//获取报损票号
		String defects_profitTicketNo = getMaxDefectsProfitTicket();
				
		System.out.println("data:"+data);
		//解析前台数据
		String checks[] = data.split("\\|");//使用转义字符,否则出错
		System.out.println("length:"+checks.length+" ++++++ "+checks[0]);
		for(int i=0;i<checks.length;i++){
			System.out.println("checks:"+checks[i]);
			String result[] = checks[i].split("_");
			//更新不合格品种数据库表数据
			QltDefectsInfo qdi = dao.get(Integer.parseInt(result[0]));
			
			qdi.setDefects_profitReason(result[1]);
			qdi.setDefects_profitLossTime(new Date());
			qdi.setDefects_profitLoss(GlobalVal.DEFECTS_STATUS.PROFITLOSS_CHECK);
			qdi.setDefects_profitTicketNo(defects_profitTicketNo);
			
			//采购部审核
			qdi.setDefects_purchasedOpinion("审核通过");
			qdi.setDefects_purchasedTime(new Date());
			qdi.setDefects_purchasedPerson(SysUtil.getLoginUser(request).getDisplay_name()); 
			qdi.setDefects_purchasedCheck(GlobalVal.DEFECTS_STATUS.PURCHASE_CHECK);
			//质管部审核
			qdi.setDefects_qualitycdOpinion("审核通过");
			qdi.setDefects_qualitycdTime(new Date());
			qdi.setDefects_qualitycdPerson(SysUtil.getLoginUser(request).getDisplay_name()); 
			qdi.setDefects_qualitycdCheck(GlobalVal.DEFECTS_STATUS.QUALITYC_CHECK);
			//储运部审核
			qdi.setDefects_storagetdOpinion("审核通过");
			qdi.setDefects_storagetdTime(new Date());
			qdi.setDefects_storagetdPerson(SysUtil.getLoginUser(request).getDisplay_name()); 
			qdi.setDefects_storagetdCheck(GlobalVal.DEFECTS_STATUS.STORAGET_CHECK);
			//质量负责人审核
			qdi.setDefects_qaOpinion("审核通过");
			qdi.setDefects_qaTime(new Date());
			qdi.setDefects_qaPerson(SysUtil.getLoginUser(request).getDisplay_name()); 
			qdi.setDefects_qaCheck(GlobalVal.DEFECTS_STATUS.QA_CHECK);
			//财务部审核
			qdi.setDefects_financialdOpinion("审核通过");
			qdi.setDefects_financialdTime(new Date());
			qdi.setDefects_financialdPerson(SysUtil.getLoginUser(request).getDisplay_name()); 
			qdi.setDefects_financialdCheck(GlobalVal.DEFECTS_STATUS.FINANCIAL_CHECK);
			//经理审核
			qdi.setDefects_managerOpinion("审核通过");
			qdi.setDefects_managerTime(new Date());
			qdi.setDefects_managerPerson(SysUtil.getLoginUser(request).getDisplay_name()); 
			qdi.setDefects_managerCheck(GlobalVal.DEFECTS_STATUS.MANAGER_CHECK);
			
			dao.update(qdi);
			
			dao.addLog(qdi);
			log.debug("不合格品种信息-直接报损-日志记录添加成功");
			
			StockInfo si = stockDao.get(qdi.getStock_info_id());
			si.setStock_storageNumber(si.getStock_storageNumber() - qdi.getDefects_number());
			stockDao.update(si);
			log.debug("修改该批号产品库存数量");
			
		}
		
		//如果新票号和前台票号不同，则传入最新票号到前台显示
		if(!oldProfitTicket.equals(defects_profitTicketNo)){
			return defects_profitTicketNo;
		}
		
		return null;
	}
}