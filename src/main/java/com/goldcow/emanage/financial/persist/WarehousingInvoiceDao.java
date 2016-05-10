package com.goldcow.emanage.financial.persist;

import java.util.List;

import com.goldcow.emanage.util.gen.entity.FinancialInvoice;
import com.goldcow.emanage.util.gen.entity.FinancialInvoiceB;
import com.goldcow.emanage.util.gen.entity.StockInfo;
import com.goldcow.emanage.util.gen.entity.valueObject.financial.WarehousingInvoiceVO;
import com.goldcow.sframe.util.mybatis.BaseDao;
import com.goldcow.sframe.util.mybatis.MyBatisRepository;
/**
 * 入库购进发票
 * 
 * @author zhanxiaotong
 * @version v1.0
 * @since 2015-12-15
 */
@MyBatisRepository
public interface WarehousingInvoiceDao extends BaseDao<FinancialInvoice> {
	public List<FinancialInvoice> lists(FinancialInvoice bean);

	
	public Integer getMaxInvoiceTicket(String date);
	
	public List<WarehousingInvoiceVO> stockCheckedlists(WarehousingInvoiceVO bean);
	public int stockCheckedlistsCount(WarehousingInvoiceVO bean);


	public List<WarehousingInvoiceVO> RefundInfoList(WarehousingInvoiceVO bean);
	public int RefundInfoListCount(WarehousingInvoiceVO bean);


	public List<WarehousingInvoiceVO> confirmList(List<Integer> stocks);

//新添发票汇总信息
	public void addRkfa(FinancialInvoice bean);


	public void invalid(int accept_id);


	public void addinrefund(FinancialInvoiceB fibBean);


	public List<WarehousingInvoiceVO> invoiceList(WarehousingInvoiceVO bean);


	public List<WarehousingInvoiceVO> sumInvoiceList(WarehousingInvoiceVO bean);;

}