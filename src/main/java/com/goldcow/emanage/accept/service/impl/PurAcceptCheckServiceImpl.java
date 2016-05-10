package com.goldcow.emanage.accept.service.impl;


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

import com.goldcow.emanage.accept.persist.PurAcceptCheckDao;
import com.goldcow.emanage.accept.service.IPurAcceptCheckService;
import com.goldcow.emanage.accept.vo.PurSpecialVarietyCheckVO;
import com.goldcow.emanage.accept.vo.PurSupAndProVO;
import com.goldcow.emanage.product.service.IProInfoManageService;
import com.goldcow.emanage.purchase.persist.PurOrderDao;
import com.goldcow.emanage.purchase.persist.PurOrderListDao;
import com.goldcow.emanage.stock.persist.StockInfoDao;
import com.goldcow.emanage.util.gen.SysTools;
import com.goldcow.emanage.util.gen.entity.ProInfoManage;
import com.goldcow.emanage.util.gen.entity.PurAcceptCheck;
import com.goldcow.emanage.util.gen.entity.PurOrder;
import com.goldcow.emanage.util.gen.entity.PurOrderList;
import com.goldcow.emanage.util.gen.entity.StockInfo;
import com.goldcow.emanage.util.gen.entity.valueObject.accept.PurAcceptCheckVO;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Maps;

@Service
public class PurAcceptCheckServiceImpl implements IPurAcceptCheckService {
	private static Logger log = LoggerFactory.getLogger(PurAcceptCheckServiceImpl.class);

	@Autowired
	private PurAcceptCheckDao dao;
	@Autowired
	private PurOrderDao purOrderDao;
	@Autowired
	private PurOrderListDao purOrderListDao;
	@Autowired
	private StockInfoDao stockInfoDao;
	@Autowired
	protected IProInfoManageService proInfoManageService;
	/**
	 * 根据ID查询
	 * 
	 * @param id ID
	 * @return 订单收货确认信息
	 */
	@Override
	public PurAcceptCheck getById(Integer id) {
		log.debug("取得订单收货确认 => ID : " + id);
		return dao.get(id);
	}

	  /**
   	 * 根据收货表ID获取产品
   	 * 
   	 * @param id 小单ID
   	 * @return 对应的产品信息
   	 */
	@Override
    public ProInfoManage getProductByAcceptId(Integer id){
		log.debug("根据ID取得对应的产品 => ID : " + id);
		return dao.getProductByAcceptId(id);
	}
	
	/**
	 * 根据ID数组查询
	 * 
	 * @param ids ID
	 * @return 订单收货确认信息
	 */
	@Override
	public List<PurAcceptCheckVO> getByIds(List<Integer> ids) {
		log.debug("根据ids获取收货数据条数 : " + ids.size());
		return dao.getByIds(ids);
	}
	
	
	/**
	 * 查询订单收货确认列表-分页
	 * 
	 * @param bean 查询条件
	 * @return 订单收货确认列表
	 */
	@Override
	public Map<String, Object> list(PurAcceptCheckVO bean){	
		log.debug("查询所有收货订单");		
		 
		List<PurAcceptCheckVO> list = dao.list(bean);
		int count = dao.count(bean);

		// 封装分页对象
		Map<String, Object> result = Maps.newHashMap();
		result.put("total", count);
		result.put("rows", list);
				
		return result;
	}
	
	 /**
		 * 查询大单下的所有小单级对应的审核信息
		 * @param bean 查询条件
		 * @return 订单信息列表
		 */
	@Override
	public Map<String, Object> listDetail(PurAcceptCheckVO bean) {

	bean.setProduct_code(SysUtil.getSqlLikeParam(bean.getProduct_code()));
	bean.setSupply_code(SysUtil.getSqlLikeParam(bean.getSupply_code()));
	bean.setProduct_commonname(SysUtil.getSqlLikeParam(bean.getProduct_commonname()));
	
		List<PurAcceptCheckVO> list = dao.listDetail(bean);
		int count = dao.countDetail(bean);
		
		// 封装分页对象
		Map<String, Object> result = Maps.newHashMap();
		result.put("total", count);
		result.put("rows", list);
		return result;
	}
	 /**
		 * 查询大单下的所有小单级对应的审核信息
		 * @param bean 查询条件
		 * @return 订单信息列表
		 */
	@Override
	public Map<String, Object> listReject(PurAcceptCheck bean) {
		List<PurAcceptCheck> list = dao.listReject(bean);
		int count = dao.countReject(bean);

		// 封装分页对象
		Map<String, Object> result = Maps.newHashMap();
		result.put("total", count);
		result.put("rows", list);
		return result;
	}

	@Override
	public void delete(Integer id, HttpServletRequest request) {
		dao.delete(id);
	}

	@Override
	public PurAcceptCheck update(PurAcceptCheck bean, HttpServletRequest request) {
		bean.setAccept_checkQualityPerson(SysUtil.getLoginUser(request).getDisplay_name());
		bean.setAccept_checkQualityDate(new Date());
		dao.update(bean);
		log.debug("修改入库信息成功 => id : " + bean.getAccept_check_id());
		return bean;
	}
	
	 /**
	 * 保存质检审核结果
	 * @param data 质检数据
	 * @return 保存结果
	 */
	@Override
	public boolean passReject(String data,HttpServletRequest request) {
		System.out.println("data:"+data);
		//解析前台数据
		String checks[] = data.split("\\|");//使用转义字符,否则出错
		System.out.println("length:"+checks.length+" ++++++ "+checks[0]);
		for(int i=0;i<checks.length;i++){
			System.out.println("checks:"+checks[i]);
			String result[] = checks[i].split("_");
			PurAcceptCheck pac = dao.get(Integer.parseInt(result[0]));
			pac.setAccept_rejectReason(SysTools.decode(result[1]));
			pac.setAccept_rejectStatus(GlobalVal.ACCEPT_STATUS.REJECT_SUCCESS);//0表示拒收审核通过	
			pac.setAccept_acceptStatus(GlobalVal.ACCEPT_STATUS.ACCEPTED);//拒收审核通过，收货状态完成，设置为0
			pac.setAccept_rejectCheckPerson(SysUtil.getLoginUser(request).getDisplay_name());
			pac.setAccept_rejectCheckDate(new Date());
			
			PurOrderList pol = purOrderListDao.get(pac.getPur_orderList_id());
			
			PurOrderList purOrderList = new PurOrderList();
			purOrderList.setId(pac.getPur_orderList_id());
			purOrderList.setArrivalNumber(pol.getArrivalNumber()-pac.getAccept_rejectNumber());
			purOrderList.setIsArrival(GlobalVal.ACCEPT_STATUS.UNARRAIVAL);
			purOrderListDao.update(purOrderList);
			
			dao.update(pac);
		}
		return true;
	}

	 /**
	 * 拒收审核不通过
	 * @param data 拒收审核数据
	 * @return 保存结果
	 */
	@Override
	public boolean failReject(String data ,HttpServletRequest request) {
		System.out.println("data:"+data);
		Double realArrivalNumber = 0.0;//实际到货数量
		//解析前台数据
		String checks[] = data.split("\\|");//使用转义字符,否则出错
		System.out.println("length:"+checks.length+" ++++++ "+checks[0]);
		for(int i=0;i<checks.length;i++){
			System.out.println("checks:"+checks[i]);
			String result[] = checks[i].split("_");
			//更新收货表数据
			PurAcceptCheck pac = dao.get(Integer.parseInt(result[0]));
			pac.setAccept_rejectReason(SysTools.decode(result[1]));
			pac.setAccept_rejectStatus(GlobalVal.ACCEPT_STATUS.REJECT_FAILED);//1表示拒收审核不通过
			pac.setAccept_acceptStatus(GlobalVal.ACCEPT_STATUS.ACCEPTED);//拒收审核不通过，收货状态完成，设置为0
			realArrivalNumber =  pac.getAccept_acceptNumber() + pac.getAccept_rejectNumber();
			pac.setAccept_acceptNumber(realArrivalNumber);//拒收审核不通过，收货数量=到货数量+拒收数量
			pac.setAccept_acceptDate(new Date());
			pac.setAccept_rejectCheckPerson(SysUtil.getLoginUser(request).getDisplay_name());
			pac.setAccept_rejectCheckDate(new Date());
			
			//更新小单表数据
			PurOrderList pol = purOrderListDao.get(pac.getPur_orderList_id());
			pac.setAccept_sum(pol.getSettlementPrice()*realArrivalNumber);//修改收货总金额
			dao.update(pac);
			
			
			
			//获取某个小单的所有接收数量
			Integer sum = dao.getSumByOrderListId(pac.getPur_orderList_id());  
			if(Double.compare(pol.getQuantity(), sum) ==0 ){
				pol.setIsArrival(GlobalVal.ACCEPT_STATUS.ARRAIVALED);
			}
			purOrderListDao.update(pol);
			
			//更新大单表数据
			PurOrder purOrder = purOrderDao.get(purOrderDao.getOrderIdByTicketid(pol.getTicket_id()));
			//获取小单所有状态
			List<PurOrderList> list = purOrderListDao.getlist(pol.getTicket_id());
			//大单收货状态  goodsReceive RECEIVED表示已经收货 UNRECEIVED表示未收货
			int goodsReceive = GlobalVal.ACCEPT_STATUS.RECEIVED;
			for(int j =0;j<list.size();j++){
				if(list.get(j).getIsArrival() != GlobalVal.ACCEPT_STATUS.ARRAIVALED){
					goodsReceive = GlobalVal.ACCEPT_STATUS.UNRECEIVE;
					//System.out.println("goodsReceive:hahahhahaha"+j);
					break;
				}
			}
			purOrder.setGoodsReceive(goodsReceive);
			purOrderDao.update(purOrder);
		}
		return true;
	}
	
	@Override
	public Map<String, Object> listOrderList(PurAcceptCheck bean) {
		List<PurAcceptCheckVO> list = dao.listOrderList(bean);
		int count = dao.countDetail(bean);

		// 封装分页对象
		Map<String, Object> result = Maps.newHashMap();
		result.put("total", count);
		result.put("rows", list);
		return result;	
	}
	 /**
	 * 添加收货确认信息
	 * @param bean 查询条件
	 * @return 订单信息列表
	 */
	@Override
	public PurAcceptCheck add(PurAcceptCheck bean, HttpServletRequest request) {
		// TODO Auto-generated method stub
		log.debug("添加收货订单信息");
		SysUtil.checkInput(bean);
		
		bean.setAccept_acceptDate(new Date());
		bean.setAccept_rejectCheckDate(new Date());
		bean.setAccept_checkQualityDate(new Date());
		bean.setAccept_expirationDate(new Date());
		bean.setAccept_productionDate(new Date());
		bean.setAccept_sterilizationbDate(new Date());
		bean.setAccept_checkStockTime(new Date());
		bean.setAccept_acceptPerson(SysUtil.getLoginUser(request).getDisplay_name());
		
		//初始化状态码 拒收状态 
		if(bean.getAccept_rejectNumber() == 0)
			bean.setAccept_rejectStatus(GlobalVal.ACCEPT_STATUS.REJECT_SUCCESS);
		else
			bean.setAccept_rejectStatus(GlobalVal.ACCEPT_STATUS.UNREJECT);
			
		//初始化状态码 质量验收状态 未质检
		bean.setAccept_quantityStatus(GlobalVal.ACCEPT_STATUS.UNQUANTITY);
		//初始化状态码 不合格审核状态 未审核
		bean.setAccept_quantityCheckStatus(GlobalVal.ACCEPT_STATUS.UNQUANTITY_CHECK);
		
		//初始化状态码 库房验收状态 未入库
		bean.setAccept_stockStatus(GlobalVal.ACCEPT_STATUS.UNSTOCK);
		//收货数量=到货数量-拒收数量
		bean.setAccept_acceptNumber(bean.getAccept_arrivalNumber()-bean.getAccept_rejectNumber());
		
		PurOrderList purOrderList =  purOrderListDao.get(bean.getPur_orderList_id());
		
		//purOrderList.setArrivalNumber((double)bean.getAccept_arrivalNumber());
		
		int purOrderId = purOrderDao.getOrderIdByTicketid(purOrderList.getTicket_id());
		//通过小单中产品ID查询该产品是否是特殊品种，并进行初始化状态码
		if(purOrderListDao.getSpecialvariety(purOrderList.getId()) ==1){
			//如果该产品是特殊品种 初始化状态码 特殊药品审核状态 未审核
			bean.setAccept_specialStatus(GlobalVal.ACCEPT_STATUS.UNSPECIAL_CHECK);
		}else{
			//如果该产品不是特殊品种 初始化状态码 特殊药品审核状态 审核通过
			bean.setAccept_specialStatus(GlobalVal.ACCEPT_STATUS.SPECIAL_CHECKE_SUCCESS);
		}
		
		bean.setPur_order_id(purOrderId);
		
		double acceptNumber = bean.getAccept_arrivalNumber()+purOrderList.getArrivalNumber();
		purOrderList.setArrivalNumber(acceptNumber);
		
		if((double)acceptNumber == purOrderList.getQuantity() && bean.getAccept_rejectNumber() == 0){
			//如果收货数量与小单订货数量相等，并且拒收数量为零，则收货完成，将小单接收状态改为已接收。将收货bean的接收状态改为接收完成
			purOrderList.setIsArrival(GlobalVal.ACCEPT_STATUS.ARRAIVALED);
			bean.setAccept_acceptStatus(GlobalVal.ACCEPT_STATUS.ACCEPTED);
			
		}else if((double)acceptNumber != purOrderList.getQuantity() && bean.getAccept_rejectNumber() == 0){
			//如果收货数量与小单订货数量不相等，并且拒收数量为零，则收货完成，将小单接收状态改为未接收，并插入小单的收货数量。将收货bean的接收状态改为接收完成
			purOrderList.setIsArrival(GlobalVal.ACCEPT_STATUS.UNARRAIVAL);
			bean.setAccept_acceptStatus(GlobalVal.ACCEPT_STATUS.ACCEPTED);;

		}else if((double)acceptNumber == purOrderList.getQuantity() && bean.getAccept_rejectNumber() != 0){
			//如果收货数量与小单订货数量相等，并且拒收数量不为零，将小单接收状态改为待接收。将收货bean的接收状态改为等待拒收审核
			purOrderList.setIsArrival(GlobalVal.ACCEPT_STATUS.WAIT_REJECTED);
			bean.setAccept_acceptStatus(GlobalVal.ACCEPT_STATUS.UNACCEPT);
			bean.setAccept_rejectStatus(GlobalVal.ACCEPT_STATUS.UNREJECT);
			
		}else if((double)acceptNumber != purOrderList.getQuantity() && bean.getAccept_rejectNumber() != 0){
			//如果收货数量与小单订货数量不相等，并且拒收数量不为零，将小单接收状态改为待接收。将小单接收状态改为已接受。将收货bean的接收状态改为等待拒收审核
			purOrderList.setIsArrival(GlobalVal.ACCEPT_STATUS.UNARRAIVAL);
			bean.setAccept_acceptStatus(GlobalVal.ACCEPT_STATUS.UNACCEPT);
			bean.setAccept_rejectStatus(GlobalVal.ACCEPT_STATUS.UNREJECT);
		}
		
		bean.setAccept_sum(bean.getAccept_acceptNumber()*purOrderList.getUnitprice());
		dao.add(bean);
		purOrderListDao.update(purOrderList);
	
		
		List<PurOrderList> list = purOrderListDao.getlist(purOrderList.getTicket_id());
		int goodsReceive = GlobalVal.ACCEPT_STATUS.RECEIVED;
		for(int i =0;i<list.size();i++){
			if(list.get(i).getIsArrival() != GlobalVal.ACCEPT_STATUS.ARRAIVALED){
				goodsReceive = GlobalVal.ACCEPT_STATUS.UNRECEIVE;
				break;
			}
		}
		
		PurOrder purOrder = purOrderDao.get(purOrderDao.getOrderIdByTicketid(purOrderList.getTicket_id()));
		purOrder.setGoodsReceive(goodsReceive);
		purOrderDao.update(purOrder);
		log.debug("添加收货订单信息成功 => id : " + bean.getAccept_check_id());
		return bean;
	}
	
	@Override
	public Map<String, Object> listUnqualified(PurAcceptCheckVO bean) {
		List<PurAcceptCheckVO> list = dao.unqualified(bean);
		int count = dao.countDetail(bean);

		// 封装分页对象
		Map<String, Object> result = Maps.newHashMap();
		result.put("total", count);
		result.put("rows", list);
		return result;	
	}
	/**
	 * 获取最大入库票号
	 * @author wubin
	 * */
	@Override
	public String getMaxIntakeTicket() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String date = dateFormat.format(new Date());
        StringBuilder out  = new StringBuilder(date);
		Integer ticketID =  dao.getMaxIntakeTicket(date);
		if(ticketID != null){
			out.append(String.format("%04d",ticketID+1));
		}
		else{
			out.append("0001");
		}
		return "I"+out.toString();
	}

	/** 批量更新票号和打印格式
	 * @param list 更新list对象
	 * @author wubin
	 * */
	@Override
	public void updateTicket(List<PurAcceptCheck> list) {
		dao.updateTicket(list);
		log.debug("批量更新票号和打印格式条数 =>  : " + list.size());
	}	

	@Override
	public boolean addUnqualified(PurAcceptCheck bean) {
		 dao.add(bean);
		return true;
	}

	@Override
	public int getQuantityCheckStatus(Integer id) {
		return dao.getQuantityCheckStatus(id);
	}

	/**
	 * 入库操作
	 * @param data 需要入库的id
	 * @param oldIntakeTicket 前台入库票号
	 * @param printType  打印格式
	 * @param request
	 * @author wubin
	 * */
	@Override
	public String intake(String data,String oldIntakeTicket,String printType,HttpServletRequest request) {
		//需要查询的IDs
		List<Integer> ids = new ArrayList<Integer>();
		//需要更新的对象
		List<PurAcceptCheck> pacs = new ArrayList<PurAcceptCheck>();
		//获取入库单号
		String  accept_intakeTicket = getMaxIntakeTicket();
		//需要入库的List
		List<StockInfo> stockInfos = new ArrayList<StockInfo>();
		//需要入库的批次库存
		List<Double> quantitys = new ArrayList<Double>();
		//productIds
		List<Integer> productIds = new ArrayList<Integer>();
		//departmentIds
		List<Integer> departmentIds = new ArrayList<Integer>();
		//只包含查询条件的stockInfos
		List<StockInfo> queryStocks = new ArrayList<StockInfo>();
		//stock_varietyAmounts
		List<Double> varietyAmounts = new ArrayList<Double>();
		//stock_varietyStorageAmounts
		List<Double> varietyStorageAmounts = new ArrayList<Double>();
		
		System.out.println("data::"+data);
		String datas[] = data.split("\\|");
		System.out.println("data.length::"+datas.length);
		for(int i=0;i<datas.length;i++){
			String subdatas[] = datas[i].split("_");
			System.out.println("subdatas.length::"+subdatas.length);
			//往数据库添加入库票号和打印格式
			PurAcceptCheck pac = new PurAcceptCheck();
			pac.setAccept_check_id(Integer.parseInt(subdatas[0]));
			pac.setAccept_intakeTicket(accept_intakeTicket);
			pac.setAccept_quantityPrintType(printType);
			pac.setAccept_stockStatus(GlobalVal.ACCEPT_STATUS.STOCKED);
			pac.setAccept_checkStockPerson(SysUtil.getLoginUser(request).getDisplay_name());
			pac.setAccept_checkStockTime(new Date());
			pacs.add(pac);
			
			//封装IDs,productIds,departmentIds,queryStocks
			quantitys.add(Double.parseDouble(subdatas[1]));
			productIds.add(Integer.parseInt(subdatas[2]));
			departmentIds.add(Integer.parseInt(subdatas[3]));
			ids.add(Integer.parseInt(subdatas[0]));
			StockInfo stock = new StockInfo();
			stock.setProduct_id(Integer.parseInt(subdatas[2]));
			stock.setStock_storage(Integer.parseInt(subdatas[3]));
			queryStocks.add(stock);
		}
		
		varietyAmounts = stockInfoDao.getByProductIds(productIds);
		varietyStorageAmounts = stockInfoDao.getByStockInfos(queryStocks);
		
		//更新accept数据库表(多条数据)
		updateTicket(pacs);
		
		//根据ID获取数据库数据
		List<PurAcceptCheckVO> pacVO = getByIds(ids);
		for(int i=0;i<pacVO.size();i++){
			//填充入库对象
			PurAcceptCheckVO pacvo = pacVO.get(i);
			StockInfo stockInfo = new StockInfo();
			stockInfo.setProduct_id(pacvo.getProduct_id());
			stockInfo.setStock_intakeSmallNumber(pacvo.getAccept_check_id());
			stockInfo.setStock_storage(pacvo.getDepartment_id());
			stockInfo.setStock_packunit((double)pacvo.getProduct_packingamount());
			stockInfo.setStock_batchCode(pacvo.getAccept_batchCode());
			stockInfo.setStock_purchasePrice(pacvo.getUnitprice());
			stockInfo.setStock_storageNumber((double)pacvo.getAccept_checkQualitedNumber());
			stockInfo.setStock_settlementPrice(pacvo.getSettlementPrice());
			stockInfo.setStock_salepPrice1(pacvo.getProduct_saleprice1());
			stockInfo.setStock_lowPrice(pacvo.getProduct_lretaillprice());
			stockInfo.setStock_marketPrice(pacvo.getProduct_marketprice());
			stockInfo.setStock_invalidDate(pacvo.getAccept_expirationDate());
			stockInfo.setStock_maintaintime(new Date());
			//货位号未插入,待办,调价相关3个暂不处理(需求核对调价相关属性无需初始化)
			stockInfo.setStock_priceTime(new Date());
			
			stockInfo.setStock_saleStop(pacvo.getProduct_salestop());
			stockInfo.setStock_intakeTicket(pacvo.getAccept_intakeTicket());
			stockInfo.setStock_buyPresentSpecial(pacvo.getProduct_giftspecialbatch());
			stockInfo.setStock_occupyNumber(0.0);
			stockInfo.setStock_produceDate(pacvo.getAccept_productionDate());
			stockInfo.setStock_sterilizationbnum(pacvo.getAccept_sterilizationbNum());
			stockInfo.setStock_sterilizationtime(pacvo.getAccept_sterilizationbDate());
			stockInfo.setStock_batchSmallCode(pacvo.getAccept_batchSmallCode());
			stockInfo.setStock_intakeCheckPerson(SysUtil.getLoginUser(request).getDisplay_name());
			stockInfo.setStock_intakeCheckTime(new Date());
			stockInfo.setStock_intakeCheck(1);
			stockInfo.setSup_id(pacvo.getSupply_id());
			
			//添加四个数值
			stockInfo.setStock_batchNumAmount(pacvo.getAccept_checkQualitedNumber());
			stockInfo.setStock_batchAmount(quantitys.get(i));
			stockInfo.setStock_varietyAmount(varietyAmounts.get(i)+pacvo.getAccept_checkQualitedNumber());
			stockInfo.setStock_varietyStorageAmount(varietyStorageAmounts.get(i)+pacvo.getAccept_checkQualitedNumber());
			
			stockInfos.add(stockInfo);
			//往产品表插入最后进价
			ProInfoManage pim = new ProInfoManage();
			pim.setProduct_id(pacvo.getProduct_id());
			pim.setProduct_lastprice(pacvo.getSettlementPrice());
			proInfoManageService.update(pim, request);
		}
		
		//批量添加库存
		stockInfoDao.addStockInfos(stockInfos);
		//如果新票号和前台票号不同，则传入最新票号到前台显示
		if(!oldIntakeTicket.equals(accept_intakeTicket)){
			return accept_intakeTicket;
		}
		return null;
	}

	@Override
	public Map<String, Object> specialVariteyList(PurSupAndProVO bean) {
		// TODO Auto-generated method stub
		log.debug("查询特殊药品");
		List<PurSpecialVarietyCheckVO>  list=dao.PurSpecialVarietyList(bean);
		int count=dao.PurSpecialVarietyListCount(bean);
		// 封装分页对象
		Map<String, Object> result = Maps.newHashMap();
		result.put("total", count);
		result.put("rows", list);
		return result;
	}	
	
	/*
	 *特殊药品审核
	 *
	 */
	@Override
	public int specialVarietyCheck(List<PurAcceptCheck> list) {
		for (PurAcceptCheck bean : list) {
			int status=dao.getQuantityCheckStatus(bean.getAccept_check_id());
		    if(status!=GlobalVal.ACCEPT_STATUS.UNQUANTITY_CHECK){
		    	bean.setAccept_quantityStatus(GlobalVal.ACCEPT_STATUS.QUANTITIED);
		    }else{
		    	bean.setAccept_quantityStatus(GlobalVal.ACCEPT_STATUS.WAIT_QUANTITY_CHECK);
		    }
		    dao.specialVarietyCheck(bean);
		}
		return 1;
	}

	@Override
	public PurAcceptCheck Straightadd(PurAcceptCheck bean, HttpServletRequest request) {
		log.debug("添加收货订单信息");
	
		bean.setAccept_startTime(new Date());
	
		dao.add(bean);
		return bean;

		
	}

	@Override
	public PurAcceptCheck getByOrderListID(Integer id) {
		// TODO Auto-generated method stub
		return dao.getByOrderListID(id);
	}
}