package com.goldcow.emanage.financial.service.impl;

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
import com.goldcow.emanage.financial.persist.WarehousingInvoiceDao;
import com.goldcow.emanage.financial.service.IWarehousingInvoiceService;
import com.goldcow.emanage.refund.persist.RefundDao;
import com.goldcow.emanage.stock.persist.StockInfoDao;
import com.goldcow.emanage.system.service.ISysUserService;
import com.goldcow.emanage.util.gen.entity.BasAccount;
import com.goldcow.emanage.util.gen.entity.FinancialInvoice;
import com.goldcow.emanage.util.gen.entity.FinancialInvoiceB;
import com.goldcow.emanage.util.gen.entity.PurAcceptCheck;
import com.goldcow.emanage.util.gen.entity.Refund;
import com.goldcow.emanage.util.gen.entity.StockInfo;
import com.goldcow.emanage.util.gen.entity.SysUser;
import com.goldcow.emanage.util.gen.entity.valueObject.Refund.RefundVO;
import com.goldcow.emanage.util.gen.entity.valueObject.financial.WarehousingInvoiceVO;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Maps;
/**
 * 入库购进发票
 * 
 * @author zhanxiaotong
 * @version v1.0
 * @since 2015-12-15
 */

@Service
public class WarehousingInvoiceServiceImpl implements IWarehousingInvoiceService {
	private static Logger log = LoggerFactory.getLogger(WarehousingInvoiceServiceImpl.class);
	/** 调货计划操作  */
	@Autowired
	private WarehousingInvoiceDao dao;
	@Autowired
	protected ISysUserService sysUserService;
	@Autowired
	private RefundDao refundDao;
	@Autowired
	private StockInfoDao stockDao;
	@Autowired
	private PurAcceptCheckDao purAcceptCheckDao;
	/**
	 * 获取最大发票票号
	 * */
	@Override
	public String getMaxInvoiceTicket() {
		// TODO Auto-generated method stub
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String date = dateFormat.format(new Date());
        StringBuilder out  = new StringBuilder(date);
		Integer ticketID =  dao.getMaxInvoiceTicket(date);
		if(ticketID != null){
			out.append(String.format("%04d",ticketID+1));
		}
		else{
			out.append("0001");
		}
		return "FAPIAO"+out.toString();
	}



	@Override
	public FinancialInvoice add(FinancialInvoice bean,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void delete(Integer id, HttpServletRequest request) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public FinancialInvoice update(FinancialInvoice bean,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public FinancialInvoice getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Map<String, Object> lists(FinancialInvoice bean) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Map<String, Object> stockCheckedlists(HttpServletRequest request,WarehousingInvoiceVO bean) {
		log.debug("查询门店入库验收初始信息");		
		
		//插入查询条件-供方客户信息编码
		SysUser user = sysUserService.getByUserId(SysUtil.getLoginUserId(request));
		Integer deptID = user.getDepart_id();
		bean.setDepartment_id(deptID);
		
		List<WarehousingInvoiceVO> stockCheckedList = dao.stockCheckedlists(bean);
		int count = dao.stockCheckedlistsCount(bean);

		// 封装分页对象
		Map<String, Object> result = Maps.newHashMap();
		result.put("total", count);
		result.put("rows", stockCheckedList);
				
		return result;
	}



	@Override
	public Map<String, Object> RefundInfoList(HttpServletRequest request,WarehousingInvoiceVO bean) {
		log.debug("查询门店返货信息");		
		
		//插入查询条件-供方客户信息编码
		SysUser user = sysUserService.getByUserId(SysUtil.getLoginUserId(request));
		Integer deptID = user.getDepart_id();
		bean.setDepartment_id(deptID);
		bean.setRkfpb_cancellation(new Integer(0));
		
		List<WarehousingInvoiceVO> RefundInfoList = dao.RefundInfoList(bean);
		int count = dao.RefundInfoListCount(bean);

		// 封装分页对象
		Map<String, Object> result = Maps.newHashMap();
		result.put("total", count);
		result.put("rows", RefundInfoList);
				
		return result;
	}



	@Override
	public Map<String, Object> confirmList(HttpServletRequest request,String data) {
//		WarehousingInvoiceVO bean = null;
		List<Integer> stocks = new ArrayList<Integer>();;
		String[] result = data.split("_");
		for(int i = 0 ; i < result.length; i++){
			if(result[i] != null && result[i] != ""){
				stocks.add(Integer.parseInt(result[i]));
			}
		}
		if(stocks.size() == 0){
			return null;
		}
		List<WarehousingInvoiceVO> conbainList = dao.confirmList(stocks);
		System.out.println("conbainList.size()=" + conbainList.size());

		// 封装分页对象
		Map<String, Object> result1 = Maps.newHashMap();
		result1.put("total", conbainList.size());
		result1.put("rows", conbainList);
				
		return result1;
	}



	@Override
	public Map<String, Object> saveInvoice(HttpServletRequest request,
			String invalid, String inrefund, String instock, String info) {
		// TODO Auto-generated method stub
		//invalid= 记录发票作废的返货id= refund_id List	
		//inrefund= 记录普通返货id= refund_id List
		//instock= 记录库存id
		//data= 记录总金额
		FinancialInvoice bean = new FinancialInvoice();
		String fp_ticket = getMaxInvoiceTicket();
		if(info != null){
			System.out.println("data=" + info);
			String[] fpList = info.split("_");
			bean.setSup_id(Integer.parseInt(fpList[0]));//供方id
			bean.setRkfp_taxrate(Integer.parseInt(fpList[1]));//税率
			bean.setRkfp_code(fpList[2]);//发票号
			bean.setRkfp_sumMoney(Double.parseDouble(fpList[3]));//发票总金额
			bean.setRkfp_discount(Double.parseDouble(fpList[4]));//折让总金额
			bean.setRkfp_supplier(fpList[5]);//开票单位
			bean.setRkfp_accepter(fpList[6]);//发票名头
			bean.setRkfp_abstract(fpList[7]);//摘要
			bean.setRkfp_category(fpList[8]);//种类
			bean.setRkfp_remark(fpList[9]);//备注
			bean.setRkfp_cancellation(new Integer(1));//发票不作废
			bean.setRkfp_ticket(fp_ticket);//最大入库票号
			bean.setRkfp_creater(SysUtil.getLoginUserId(request));
			bean.setRkfp_createTime(new Date());
			
			dao.addRkfa(bean);//对于正常库存开发票，与钱无关，添加详细信息到rkfpb，cancellation记作1，该条发票信息不作废。
		}
		if(invalid != null){
			System.out.println("invalid=" + invalid);
			String[] invalidIdList = invalid.split("_");
			Refund refundInvalid =  new Refund();
			for(int i = 0 ; i < invalidIdList.length; i++){
				if(invalidIdList[i] != null && invalidIdList[i] != ""){
					refundInvalid = refundDao.get(Integer.parseInt(invalidIdList[i]));
					dao.invalid(refundInvalid.getStock_info_id());//已开过发票的返货冲掉，记作无效，cancellation从1更新为0，该条发票信息作废。
				}
			}
		}
		if(inrefund != null){
			System.out.println("inrefund=" + inrefund);
			String[] inrefundIdList = inrefund.split("_");
			Refund refund =  new Refund();
			FinancialInvoiceB fibBean = new FinancialInvoiceB();
			StockInfo si = new StockInfo();
			for(int i = 0 ; i < inrefundIdList.length; i++){
				if(inrefundIdList[i] != null && inrefundIdList[i] != ""){
					refund = refundDao.get(Integer.parseInt(inrefundIdList[i]));
					fibBean.setRkfp_ticket(fp_ticket);
					fibBean.setRkfpb_cancellation(0);
					fibBean.setRkfpb_invoicedMoney(refund.getRefund_money());
					fibBean.setRkfpb_invoicedNum(refund.getRefund_number());
					fibBean.setStock_info_id(refund.getStock_info_id());
					si = stockDao.get(refund.getStock_info_id());
					fibBean.setAccept_check_id(si.getStock_intakeSmallNumber());
					
					dao.addinrefund(fibBean);//对于普通返货的发票处理，与钱无关，添加详细信息到rkfpb，cancellation记作0，该条发票信息作废。
				}
			}
		}
		if(instock != null){
			System.out.println("instock=" + instock);
			String checks[] = instock.split("\\|");//使用转义字符,否则出错
			FinancialInvoiceB fibBean = new FinancialInvoiceB();
			PurAcceptCheck pac = new PurAcceptCheck();
			if(checks[0]!=null&&!checks[0].equals("")){
				for(int i=0;i<checks.length;i++){
					String[] instockIdList = checks[i].split("_");
					fibBean.setRkfp_ticket(fp_ticket);
					fibBean.setRkfpb_cancellation(1);
					fibBean.setStock_info_id(Integer.parseInt(instockIdList[1]));
					fibBean.setAccept_check_id(Integer.parseInt(instockIdList[0]));
					pac = purAcceptCheckDao.get(Integer.parseInt(instockIdList[0]));
					fibBean.setRkfpb_invoicedMoney(pac.getAccept_sum());
					fibBean.setRkfpb_invoicedNum(pac.getAccept_checkQualitedNumber());
					dao.addinrefund(fibBean);//对于普通返货的发票处理，与钱无关，添加详细信息到rkfpb，cancellation记作0，该条发票信息作废。
				}
			}
		}
		return null;
	}



	@Override
	public Map<String, Object> invoiceList(HttpServletRequest request,
			WarehousingInvoiceVO bean) {
		// TODO Auto-generated method stub
		System.out.println("---------------------------WarehousingInvoiceServiceImpl----------------------------");
		SysUser user = sysUserService.getByUserId(SysUtil.getLoginUserId(request));
		Integer deptID = user.getDepart_id();
		bean.setStock_storage(deptID);
		List<WarehousingInvoiceVO> invoiceList = dao.invoiceList(bean);;
		int count = invoiceList.size();
		System.out.println("---------------------------invoiceList.size()=----------------------------" + count);
		// 封装分页对象
		Map<String, Object> result = Maps.newHashMap();
		result.put("total", count);
		result.put("rows", invoiceList);
				
		return result;
	}



	@Override
	public Map<String, Object> sumInvoiceList(HttpServletRequest request,
			WarehousingInvoiceVO bean) {
		// TODO Auto-generated method stub
		SysUser user = sysUserService.getByUserId(SysUtil.getLoginUserId(request));
		Integer deptID = user.getDepart_id();
		bean.setStock_storage(deptID);
		List<WarehousingInvoiceVO> sumInvoiceList = dao.sumInvoiceList(bean);;
		int count = sumInvoiceList.size();

		// 封装分页对象
		Map<String, Object> result = Maps.newHashMap();
		result.put("total", count);
		result.put("rows", sumInvoiceList);
				
		return result;
	}



	@Override
	public void updateSumInvoice(FinancialInvoice bean,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		log.debug("修改总发票信息");

		SysUtil.checkInput(bean);
		bean.setRkfp_last_modify_user(SysUtil.getLoginUserId(request)); 
		bean.setRkfp_last_modify_time(new Date()); 

		dao.update(bean);
		log.debug("修改财务信息成功 => id : " + bean.getRkfp_id());
	}

}