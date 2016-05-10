package com.goldcow.emanage.retail.service.impl;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldcow.emanage.product.persist.ProStockPriceDao;
import com.goldcow.emanage.promotion.service.IMemDayManageService;
import com.goldcow.emanage.promotion.service.IPmnFulfillGiftService;
import com.goldcow.emanage.promotion.service.IPmnGiftSaleService;
import com.goldcow.emanage.promotion.service.IPmnRuleInfoService;
import com.goldcow.emanage.retail.persist.RetailOrderBatchDao;
import com.goldcow.emanage.retail.persist.RetailOrderDao;
import com.goldcow.emanage.retail.persist.RetailOrderProductDao;
import com.goldcow.emanage.retail.service.IRetailOrderBatchService;
import com.goldcow.emanage.retail.service.IRetailOrderProductService;
import com.goldcow.emanage.retail.service.IRetailOrderService;
import com.goldcow.emanage.stock.persist.StockInfoDao;
import com.goldcow.emanage.stock.service.IStockInfoService;
import com.goldcow.emanage.system.persist.SysParameterDao;
import com.goldcow.emanage.util.gen.SysTools;
import com.goldcow.emanage.util.gen.bean.LoginUser;
import com.goldcow.emanage.util.gen.entity.MemCardManage;
import com.goldcow.emanage.util.gen.entity.MemDayManage;
import com.goldcow.emanage.util.gen.entity.PmnFulfillGift;
import com.goldcow.emanage.util.gen.entity.PmnGiftSale;
import com.goldcow.emanage.util.gen.entity.PmnRuleInfo;
import com.goldcow.emanage.util.gen.entity.RetailOrder;
import com.goldcow.emanage.util.gen.entity.RetailOrderBatch;
import com.goldcow.emanage.util.gen.entity.StockInfo;
import com.goldcow.emanage.util.gen.entity.StockPrice;
import com.goldcow.emanage.util.gen.entity.valueObject.retail.RetailOrderVO;
import com.goldcow.sframe.util.GlobalVal;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Maps;

@Service
public class RetailOrderServiceImpl implements IRetailOrderService{
	private static Logger log = LoggerFactory.getLogger(RetailOrderServiceImpl.class);
	/** 零售订单信息操作 */
	@Autowired
	private RetailOrderDao dao;
	@Autowired
	private ProStockPriceDao proStockPriceDao;
	@Autowired
	private SysParameterDao sysParameterDao;
	@Autowired
	private RetailOrderProductDao retailOrderProductDao;
	@Autowired
	private RetailOrderBatchDao retailOrderBatchDao;
	@Autowired
	private StockInfoDao stockInfoDao;
	@Autowired
	protected IRetailOrderProductService retailOrderProductService;
	@Autowired
	protected IRetailOrderBatchService retailOrderBatchService;
	@Autowired
	protected IStockInfoService stockInfoService;
	
	
	//获取满额赠信息
	@Autowired
	protected IPmnFulfillGiftService pmnFulfillGiftService;
	//获取满赠信息
	@Autowired
	protected IPmnGiftSaleService pmnGiftSaleService;
	//获取折扣规则信息
	@Autowired
	protected IPmnRuleInfoService pmnRuleInfoService;
	
	
	/**
	 * 会员日判断逻辑代码在service，只能调用service
	 * **/
	@Autowired
	protected IMemDayManageService memDayManageService;
	/**
	 * 根据ID查询
	 * 
	 * @param id ID
	 * @return 零售订单
	 */
	@Override
	public RetailOrder getById(Integer id) {
		log.debug("取得零售订单信息 => ID : " + id);
		return dao.get(id);
	}

	/**
	 * 查询零售订单信息列表
	 * 
	 * @param bean 查询条件
	 * @return 零售订单信息列表
	 */
	@Override
	public Map<String, Object> list(RetailOrder bean){	
		log.debug("查询零售订单信息信息列表");		

		List<RetailOrder> list = dao.list(bean);
		int count = dao.count(bean);

		// 封装分页对象
		Map<String, Object> result = Maps.newHashMap();
		result.put("total", count);
		result.put("rows", list);
		return result;
	}

	/**
	 * 新增零售订单信息
	 * 
	 * @param bean 零售订单信息
	 * @return 零售订单信息
	 */
	@Override
	public RetailOrder add(RetailOrder bean, HttpServletRequest request) {

		log.debug("新增零售订单信息");
		SysUtil.checkInput(bean);
		dao.add(bean);
		log.debug("新增零售订单信息成功 => id : " + bean.getRetail_order_id());
		return bean;
	}

	/**
	 * 修改零售订单
	 * 
	 * @param bean零售订单信息
	 * @return 零售订单信息
	 */
	@Override
	public RetailOrder update(RetailOrder bean, HttpServletRequest request) {
		log.debug("修改零售订单信息");
		SysUtil.checkInput(bean);
		if(bean.getRetail_order_doseNumber()!=1){
			//批量更新订单产品表中数量，金额
			retailOrderProductDao.updateByTicketId(bean.getRetail_order_ticketId(),bean.getRetail_order_doseNumber());
			
			List<RetailOrderVO> draftList = this.getDraftInfo(bean.getRetail_order_ticketId());
			List<List<RetailOrderVO>> totallist = new ArrayList<List<RetailOrderVO>>();
			for(int i = 0; i <draftList.size(); i++){
				draftList.get(i).setOrder("stock_invalidDate");
				List<Integer> productIds = new ArrayList<Integer>();
				productIds.add(draftList.get(i).getProduct_id());
				List<RetailOrderVO> templist = this.getStockByProductId(draftList.get(i), productIds);
				Double total = 0.0;			//统计当前库存总量
				for(int j = 0; j< templist.size() ; j++){
					total+=templist.get(i).getStock_storageNumber();
				}
				if(total<draftList.get(i).getNumber()*bean.getRetail_order_doseNumber()){
					System.out.println("产品ID为"+draftList.get(i).getProduct_id()+"的库存余量不足");
					return new RetailOrder();
				}
				totallist.add(templist);
			}
			for (int j = 0; j < totallist.size(); j++){
				List<RetailOrderVO> list = totallist.get(j);			//单个产品的库存信息
				
				//插入Batch表过程中的订单余量
				Double remainNumber = draftList.get(j).getNumber()*(bean.getRetail_order_doseNumber()-1);
				StockInfo stockInfo = new StockInfo();
				for(int i = 0; i < list.size(); i++){
					RetailOrderVO retailOrderVO = new RetailOrderVO();
					retailOrderVO = draftList.get(j);
					list.get(i).setOrder_productId(retailOrderVO.getOrder_productId());
					
					//初始化库存实体，用于库存的更新操作
					stockInfo.setStock_info_id(list.get(i).getStock_info_id());
					if(list.get(i).getAccept_Number()>0.0&&list.get(i).getAccept_Number()< remainNumber){		//当前批次数量不足以支付用户需求的数量，表示全部售完
						
						//更新库存数量=占用数量
						stockInfo.setStock_storageNumber(list.get(i).getStock_occupyNumber());
						stockInfoService.update(stockInfo, request);
						
						//插入Batch表数据
						remainNumber = remainNumber-list.get(i).getAccept_Number();
						RetailOrderVO newbean1 = new RetailOrderVO();
						newbean1 = retailOrderBatchService.addVO(draftList.get(i),list.get(i).getAccept_Number(),list.get(i));
						
						
					}else if(list.get(i).getAccept_Number()>= remainNumber){				//当前批次数量足够
						//更新库存数量=总数量-需求量
						stockInfo.setStock_storageNumber(list.get(i).getStock_storageNumber()-remainNumber);
						stockInfoService.update(stockInfo, request);
						RetailOrderVO newbean2 = new RetailOrderVO();
						newbean2 = retailOrderBatchService.addVO(draftList.get(i),remainNumber,list.get(i));
						break;
					}
				}
			}
			
		}
		dao.update(bean);
		log.debug("修改零售订单信息成功 => ticketId : " + bean.getRetail_order_ticketId());
		return bean;
	}

	/**
	 * 删除零售订单信息
	 * 
	 * @param id ID
	 */
	@Override
	public void delete(Integer id, HttpServletRequest request) {
		log.debug("删除零售订单信息");
		System.out.println("id++++++++++++++++++++++++++++"+id);
		dao.delete(id);
		log.debug("删除零售订单信息成功 => id : " + id);
	}
	
	/** 获得当前最大的零售订单编码
	 * @return 返回最大流水号
	 * */
	@Override
	public String getMaxRetailOrderCode(String currentDate,HttpServletRequest request) {
		
		log.debug("获得当前操作员的最大编码");
		
		//Integer currentUser = SysUtil.getLoginUserId(request);
		Integer product_code =  dao.getMaxRetailOrderCode(currentDate);
		StringBuilder out  = new StringBuilder();
		out.append("LS"+currentDate);
		
		System.out.println("11111"+product_code);
		if(product_code != null)
		{
			out.append(String.format("%04d",product_code+1));
		}
		else
			out.append("0001");
		return out.toString();
		
	}

	@Override
	public void deleteByTicketId(String ticketId) {
		log.debug("删除零售订单信息");
		System.out.println("ticketId++++++++++++++++++++++++++++"+ticketId);
		RetailOrderBatch retailOrderBatch = new RetailOrderBatch();
		retailOrderBatch.setRetail_order_ticketId(ticketId);
		List<RetailOrderBatch> list = retailOrderBatchDao.list(retailOrderBatch);
		stockInfoDao.addStorageNumber(list);
		dao.deleteByTicketId(ticketId);
		log.debug("删除零售订单信息成功 => ticketId : " + ticketId);
	}

	@Override
	public List<RetailOrderVO> getStockByProductId(RetailOrderVO retailOrderVO,List<Integer> product_ids) {
		return dao.getStockByProductId(retailOrderVO,product_ids);
	}

	@Override
	public List<RetailOrderVO> getRetailOrderVO(RetailOrderVO retailOrderVO,String ids,String type,MemCardManage memCard){
		List<RetailOrderVO> groupList = new ArrayList<RetailOrderVO>();
		//为拼音码，编码添加%
		retailOrderVO.setProduct_chnpy(SysTools.getSqlLikeParam(retailOrderVO.getProduct_chnpy()));
		retailOrderVO.setProduct_code(SysTools.getSqlLikeParam(retailOrderVO.getProduct_code()));
		retailOrderVO.setProduct_name(SysTools.getSqlLikeParam(retailOrderVO.getProduct_name()));
		
		//获取是否是会员日
		Integer isMemDay = 1;//默认为非会员日
		Integer pointMultiple = 1;
		//会员折扣，默认值100
		Double memDiscount = 100.0;
		
		MemDayManage mdm = memDayManageService.isMemDay(retailOrderVO.getStock_storage());
		if(mdm!=null){
			if(memCard.getMem_card_discount()!=null&&!memCard.getMem_card_discount().equals("")){
				memDiscount = (mdm.getMem_day_discountMethod().equals(GlobalVal.COMBOBOX.MEMBER_DISCOUNT))?memCard.getMem_card_discount():mdm.getMem_day_discount();
			}else
				memDiscount = (mdm.getMem_day_noDiscountMethod().equals(GlobalVal.COMBOBOX.MEMBER_DISCOUNT))?memDiscount:mdm.getMem_day_noDiscount();
			//设置是否会员日，积分倍数
			isMemDay = 0;
			pointMultiple = mdm.getMem_day_pointMultiple();
		}else{
			if(memCard.getMem_card_discount()!=null&&!memCard.getMem_card_discount().equals(""))
				memDiscount = memCard.getMem_card_discount();
		}
		
		
		
		//指定某些产品查询
		List<Integer> product_ids = new ArrayList<Integer>();
		if(ids!=null&&!ids.equals("")){
			String[] pro_ids = ids.split("_");
			for(int i=0;i<pro_ids.length;i++){
				product_ids.add(Integer.parseInt(pro_ids[i]));
			}
		}
		
		//根据产品ID排序
		retailOrderVO.setOrder("product_id");
		System.out.println(product_ids.size());
		List<RetailOrderVO> list = this.getStockByProductId(retailOrderVO,product_ids);

		if(type.equals("product")){
			System.out.println("product");
			if(list != null&&list.size() != 0){
				RetailOrderVO bean = list.get(0);
				bean.setStock_info_id(null);
				
				for (int i = 1;i < list.size(); i++) {
					//在进行产品查询时，将所有的库存id置为null，能防止前台提交时的数据中含有库存id，导致出错
					list.get(i).setStock_info_id(null);
					if(list.get(i).getProduct_id() == list.get(i-1).getProduct_id()){
						bean.setStock_storageNumber(bean.getStock_storageNumber()+list.get(i).getStock_storageNumber());
						bean.setAccept_Number(bean.getAccept_Number()+list.get(i).getAccept_Number());
					}else{
						this.setProperty(bean, pointMultiple, isMemDay, memDiscount);	
						groupList.add(bean);
						//生成下一条数据
						bean = list.get(i);
					}
				}		
				this.setProperty(bean, pointMultiple, isMemDay, memDiscount);	
				groupList.add(bean);
			}
			return groupList;
		}else if(type.equals("stock")){
			System.out.println("stock");
			for(int i=0;i<list.size();i++){
				RetailOrderVO bean = list.get(i);
				this.setProperty(bean, pointMultiple, isMemDay, memDiscount);
			}
			return list;
		}else
			System.out.println("前台参数出错，请检查参数!!!!");
		return list;
	}
	
	
	
	public void setProperty(RetailOrderVO bean, Integer pointMultiple, Integer isMemDay, Double memDiscount){
		//设置单价
		StockPrice stockPrice = new StockPrice();
		stockPrice.setDepartment_id(bean.getStock_storage());
		stockPrice.setProduct_id(bean.getProduct_id());
		List<StockPrice> liststockPrice = proStockPriceDao.lists(stockPrice);
		if(liststockPrice == null||liststockPrice.size()==0)
			bean.setOrder_product_unitPrice(bean.getProduct_retailprice());
		else
			bean.setOrder_product_unitPrice(liststockPrice.get(0).getPro_retail_price());
		//查询折扣规则
		PmnRuleInfo pmnRuleInfo =  this.getRuleInfo(bean.getProduct_id());
		if(pmnRuleInfo!=null){
			bean.setOrder_product_unitPrice(bean.getOrder_product_unitPrice()*pmnRuleInfo.getRule_discount());
		}
		//设置会员折后价
		bean.setOrder_product_memUnitPrice(bean.getOrder_product_unitPrice()*memDiscount/100.0);
		//设置减差价
		bean.setMemDescCost(bean.getOrder_product_unitPrice()-bean.getOrder_product_memUnitPrice());
		//设置能开价
		bean.setAccept_Amount(bean.getOrder_product_unitPrice()*bean.getDeparment_acceptAmountDiscount());
		//设置积分倍数
		bean.setOrder_batch_pointMutiple(pointMultiple);
		//设置是否会员日
		bean.setIs_memberDay(isMemDay);
	}

	@Override
	public Map<String, Object> getGiftSales(HttpServletRequest request,String member_id,String products) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		List<Integer> productIds = new ArrayList<Integer>();//产品IDs
		
		Integer isMember = 0;//是否是会员
		LoginUser loginUser = SysUtil.getLoginUserInfos(request);//获取当前登陆用户
		Integer dept_id = loginUser.getSysUser().getDepart_id();//获取当前部门ID
		
		if(member_id!=null&&!member_id.equals(""))
			isMember = 1;
		
		String product[] = products.split("_");
		if(!product[0].equals("")&&!product[0].equals("null")){
			//赋值
			productIds.add(Integer.parseInt(product[0]));
		}
		
		//获取满赠信息
		PmnGiftSale pgs = new PmnGiftSale();
		pgs.setGift_sal_onlyMemberJoin(isMember);
		pgs.setGift_sal_joinDepartment("%,"+dept_id+",%");
		List<PmnGiftSale> pgss = pmnGiftSaleService.getgiftSales(pgs, productIds);
		map.put("pgss", pgss);
		
		return map;
	}
		
	
	@Override
	public Map<String, Object> getFullFillGifts(HttpServletRequest request,String member_id,String data) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		List<String> proIds = new ArrayList<String>();//产品IDs
		List<Integer> productIds = new ArrayList<Integer>();//产品IDs
		
		List<Integer> isSpecialBatchs = new ArrayList<Integer>();//是否买赠特殊批次
		Integer isMember = 0;//是否是会员
		LoginUser loginUser = SysUtil.getLoginUserInfos(request);//获取当前登陆用户
		Integer dept_id = loginUser.getSysUser().getDepart_id();//获取当前部门ID
		
		
		if(member_id!=null&&!member_id.equals(""))
			isMember = 1;
		
		String datas[] = data.split("\\|");
		if(!datas[0].equals("")){
			//赋值
			for(int i=0;i<datas.length;i++){
				String subdatas[] = datas[i].split("_");
				productIds.add(Integer.parseInt(subdatas[0]));
				proIds.add("%,"+subdatas[0]+",%");
				//isSpecialBatchs.add(Integer.parseInt(subdatas[1]));
			}
		}
		
		
		
		
		//获取满额赠信息
		PmnFulfillGift pfg = new PmnFulfillGift();
		pfg.setFull_gift_onlyMemberJoin(isMember);
		pfg.setFull_gift_joinDepartment("%,"+dept_id+",%");
		List<PmnFulfillGift> pfgs = pmnFulfillGiftService.getFullFillGifts(pfg, proIds);
		map.put("pfgs", pfgs);
		
		//获取满赠信息
		PmnGiftSale pgs = new PmnGiftSale();
		pgs.setGift_sal_onlyMemberJoin(isMember);
		pgs.setGift_sal_joinDepartment("%,"+dept_id+",%");
		List<PmnGiftSale> pgss = pmnGiftSaleService.getgiftSales(pgs, productIds);
		map.put("pgss", pgss);
		return map;
	}
	
	public PmnRuleInfo getRuleInfo(Integer product_id){
		return pmnRuleInfoService.isRuleProduct(product_id);
	}
	@Override
	public List<RetailOrderVO> getDraftInfo(String sale_order_ticketId) {
		return dao.getDraftInfo(sale_order_ticketId);
	}
	
	/** 获得当前销售订单包含的产品数量
	 * @param 订单票号
	 * @return 数量
	 * */
	public Integer countProduct(String retail_order_ticketId){
		return dao.countProduct(retail_order_ticketId);
		
	}
	
	@Override
	public List<RetailOrderVO> salePromotion(String product_id, String number,
			String discountedPrice, String retail_order_ticketId,String retail_type, HttpServletRequest request) {
		
		LoginUser loginUser = SysUtil.getLoginUserInfos(request);//获取当前登陆用户
		Integer dept_id = loginUser.getSysUser().getDepart_id();//获取当前部门ID

		String[] product_ids = product_id.split("_");
		String[] numbers = number.split("_");
		String[] discountedPrices = discountedPrice.split("_");
		List<List<RetailOrderVO>> totallist = new ArrayList<List<RetailOrderVO>>();
		for (int j = 0; j<product_ids.length; j++) {
			RetailOrderVO bean = new RetailOrderVO();
			bean.setStock_storage(dept_id);
			bean.setOrder("stock_invalidDate");
			List<Integer> productIds = new ArrayList<Integer>();
			productIds.add(Integer.parseInt(product_ids[j]));
			List<RetailOrderVO> templist = this.getStockByProductId(bean, productIds);
			Double total = 0.0;			//统计当前库存总量
			for(int i = 0; i< templist.size() ; i++){
				total+=templist.get(i).getStock_storageNumber();
				templist.get(i).setRetail_order_ticketId(retail_order_ticketId);
				templist.get(i).setOrder_product_unitPrice(Double.parseDouble(discountedPrices[j]));
				templist.get(i).setOrder_product_discountAmount(0.0);
				templist.get(i).setMemDescCost(0.0);
				templist.get(i).setDiscountedPrice(Double.parseDouble(discountedPrices[j]));
				templist.get(i).setOrder_batch_pointMutiple(1);
			}
			if(total<Double.parseDouble(numbers[j])){
				System.out.println("产品ID为"+product_ids[j]+"的库存余量不足");
				return new ArrayList<RetailOrderVO>();
			}
			totallist.add(templist);
		}
		List<RetailOrderVO> soldProductBatch = new ArrayList<RetailOrderVO>();
		for (int j = 0; j < totallist.size(); j++){
			List<RetailOrderVO> list = totallist.get(j);			//单个产品的库存信息
			
			//销售类型
			list.get(0).setOrder_product_saleType(retail_type);
			//订单产品表中插入该条记录
			RetailOrderVO bean  = retailOrderProductService.addVO(list.get(0),Double.parseDouble(numbers[j]));
			
			//插入Batch表过程中的订单余量
			Double remainNumber = Double.parseDouble(numbers[j]);
			StockInfo stockInfo = new StockInfo();
			for(int i = 0; i < list.size(); i++){
				RetailOrderVO retailOrderVO = new RetailOrderVO();
				retailOrderVO = bean;
				list.get(i).setOrder_productId(retailOrderVO.getOrder_productId());
				
				//初始化库存实体，用于库存的更新操作
				stockInfo.setStock_info_id(list.get(i).getStock_info_id());
				if(list.get(i).getAccept_Number()>0.0&&list.get(i).getAccept_Number()< remainNumber){		//当前批次数量不足以支付用户需求的数量，表示全部售完
					
					//更新库存数量=占用数量
					stockInfo.setStock_storageNumber(list.get(i).getStock_occupyNumber());
					stockInfoService.update(stockInfo, request);
					
					//插入Batch表数据
					remainNumber = remainNumber-list.get(i).getAccept_Number();
					RetailOrderVO newbean1 = new RetailOrderVO();
					//赠品兑换金额都是总金额
					list.get(i).setOrder_batch_fullGiftAmount(list.get(i).getAccept_Number()*list.get(i).getDiscountedPrice());
					list.get(i).setOrder_batch_fullGiftExchange(list.get(i).getAccept_Number()*list.get(i).getDiscountedPrice());
					//销售类型
					list.get(i).setOrder_product_saleType(retail_type);
					
					newbean1 = retailOrderBatchService.addVO(list.get(i),list.get(i).getAccept_Number(),list.get(i));
					soldProductBatch.add(newbean1);
					
					System.out.println("stock_info_id:"+soldProductBatch.get(0).getStock_info_id());
					
				}else if(list.get(i).getAccept_Number()>= remainNumber){				//当前批次数量足够
					//更新库存数量=总数量-需求量
					stockInfo.setStock_storageNumber(list.get(i).getStock_storageNumber()-remainNumber);
					stockInfoService.update(stockInfo, request);
					RetailOrderVO newbean2 = new RetailOrderVO();
					//赠品兑换金额都是总金额
					list.get(i).setOrder_batch_fullGiftAmount(remainNumber*list.get(i).getDiscountedPrice());
					list.get(i).setOrder_batch_fullGiftExchange(remainNumber*list.get(i).getDiscountedPrice());
					
					
					newbean2 = retailOrderBatchService.addVO(list.get(i),remainNumber,list.get(i));
					soldProductBatch.add(newbean2);
					break;
				}
			}
		}
		return soldProductBatch;
	}
}