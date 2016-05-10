package com.goldcow.emanage.financial.service;

import java.util.Map;








import javax.servlet.http.HttpServletRequest;

import com.goldcow.emanage.util.gen.entity.FinancialInvoice;
import com.goldcow.emanage.util.gen.entity.valueObject.financial.WarehousingInvoiceVO;
import com.goldcow.sframe.util.service.BaseIService;
/**
 * 入库购进发票
 * 
 * @author zhanxiaotong
 * @version v1.0
 * @since 2015-12-15
 */
public interface IWarehousingInvoiceService extends BaseIService<FinancialInvoice>{
	
	public Map<String, Object> lists(FinancialInvoice bean);
	public Map<String, Object> stockCheckedlists(HttpServletRequest request,WarehousingInvoiceVO bean);
	public Map<String, Object> RefundInfoList(HttpServletRequest request,WarehousingInvoiceVO bean);
	public String getMaxInvoiceTicket();
	public Map<String, Object> confirmList(HttpServletRequest request, String data);
	public Map<String, Object> saveInvoice(HttpServletRequest request,
			String invalid, String inrefund, String instock, String info);
	public Map<String, Object> invoiceList(HttpServletRequest request,
			WarehousingInvoiceVO bean);
	public Map<String, Object> sumInvoiceList(HttpServletRequest request,
			WarehousingInvoiceVO bean);
	public void updateSumInvoice(FinancialInvoice bean,
			HttpServletRequest request);
}