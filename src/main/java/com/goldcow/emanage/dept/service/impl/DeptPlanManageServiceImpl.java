package com.goldcow.emanage.dept.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldcow.emanage.dept.persist.DeptPlanManageDao;
import com.goldcow.emanage.dept.service.IDeptPlanManageService;
import com.goldcow.emanage.product.persist.ProInfoManageDao;
import com.goldcow.emanage.stock.persist.StockInfoDao;
import com.goldcow.emanage.system.persist.SysParameterDao;
import com.goldcow.emanage.system.service.ISysUserService;
import com.goldcow.emanage.util.gen.entity.DeptPlanManage;
import com.goldcow.emanage.util.gen.entity.ProInfoManage;
import com.goldcow.emanage.util.gen.entity.StockInfo;
import com.goldcow.emanage.util.gen.entity.SysParameterSub;
import com.goldcow.emanage.util.gen.entity.SysUser;
import com.goldcow.emanage.util.gen.entity.valueObject.dept.ProStockInfoVO;
import com.goldcow.sframe.util.SysUtil;
import com.google.common.collect.Maps;
/**
 * 调货计划
 * 
 * @author zhanxiaotong
 * @version v1.0
 * @since 2015-11-2
 */

@Service
public class DeptPlanManageServiceImpl implements IDeptPlanManageService {
	private static Logger log = LoggerFactory.getLogger(DeptPlanManageServiceImpl.class);
	/** 调货计划操作  */
	@Autowired
	private DeptPlanManageDao dao;
	@Autowired
	protected ISysUserService sysUserService;
	@Autowired
	protected SysParameterDao sysParameterDao;
	@Autowired
	protected StockInfoDao stockInfoDao;
	@Autowired
	protected ProInfoManageDao proInfoManageDao;
	/**
	 * 根据ID查询
	 * 
	 * @param id ID
	 * @return 调货计划
	 */
	@Override
	public DeptPlanManage getById(Integer id) {
		log.debug("取得调货计划 => ID : " + id);
		return dao.get(id);
	}
	/**
	 * 查询供方客户信息列表-分页
	 * 
	 * @param bean 查询条件
	 * @return 供方客户信息列表
	 */
	@Override
	public Map<String, Object> lists(DeptPlanManage bean){	
		log.debug("查询调货计划列表");		
		
		//插入查询条件-供方客户信息编码
		//bean.setDeptPlan_checkStatus(SysUtil.getSqlLikeParam(bean.getDeptPlan_checkStatus()));
		
		List<DeptPlanManage> list = dao.lists(bean);
		int count = dao.count(bean);

		// 封装分页对象
		Map<String, Object> result = Maps.newHashMap();
		result.put("total", count);
		result.put("rows", list);
				
		return result;
	}
	/**
	 * 新增调货计划信息
	 * 
	 * @param bean 调货计划信息
	 * @param request HttpServletRequest
	 * @return 调货计划信息
	 */
	@Override
	public boolean add(String data, HttpServletRequest request) {
		log.debug("新增调货计划");
		DeptPlanManage deptPlanManage = new DeptPlanManage();
		//获取当前user所在库房-申请库房
		SysUser user = sysUserService.getByUserId(SysUtil.getLoginUserId(request));
		String mydept = user.getDepart_id().toString();
		//获取总库房-调出库房
		SysParameterSub sysParameterSub = sysParameterDao.getSub(5);
		String headdept = sysParameterSub.getParam_sub_value();
		
		System.out.println("data:"+data);
		Double money;
		//解析前台数据
		String checks[] = data.split("\\|");//使用转义字符,否则出错
		
		if(checks[0]!=null&&!checks[0].equals("")){
			for(int i=0;i<checks.length;i++){
				System.out.println("checks:"+checks[i]);
				String result[] = checks[i].split("_");
				deptPlanManage.setDeptPlan_pro_id(Integer.parseInt(result[0]));
				deptPlanManage.setDeptPlan_exportDept(headdept);
				deptPlanManage.setDeptPlan_applyDept(mydept);
				deptPlanManage.setDeptPlan_applyNum(Double.parseDouble(result[1]));
				deptPlanManage.setDeptPlan_applyPrice(Double.parseDouble(result[2]));
				deptPlanManage.setDeptPlan_storageNumber(Double.parseDouble(result[3]));
				money = deptPlanManage.getDeptPlan_applyNum() * deptPlanManage.getDeptPlan_applyPrice();
				deptPlanManage.setDeptPlan_applyMoney(money);
				deptPlanManage.setDeptPlan_applyPerson(SysUtil.getLoginUserId(request));
				deptPlanManage.setDeptPlan_applyTime(new Date());
				deptPlanManage.setDeptPlan_checkStatus("1");//未审核
				dao.add(deptPlanManage);
			}
		}
		
		return true;
	}
	
	/**
	 * 手动新增调货计划信息
	 * 
	 * @param bean 调货计划信息
	 * @param request HttpServletRequest
	 * @return 调货计划信息
	 */
	@Override
	public boolean addPlanManual(String data, HttpServletRequest request) {
		log.debug("新增调货计划");
		DeptPlanManage deptPlanManage = new DeptPlanManage();
		StockInfo stockInfo = new StockInfo();
		//获取当前user所在库房-申请库房
		SysUser user = sysUserService.getByUserId(SysUtil.getLoginUserId(request));
		String mydept = user.getDepart_id().toString();
		//获取总库房-调出库房
		SysParameterSub sysParameterSub = sysParameterDao.getSub(5);
		String headdept = sysParameterSub.getParam_sub_value();
		
		System.out.println("data:"+data);
		Double money;
		Double storageNumber;
		//解析前台数据
		String result[] = data.split("_");
		Integer product_id = proInfoManageDao.getIDByProCode(result[0]);
		deptPlanManage.setDeptPlan_pro_id(product_id);
		deptPlanManage.setDeptPlan_exportDept(headdept);
		deptPlanManage.setDeptPlan_applyDept(mydept);
		deptPlanManage.setDeptPlan_applyNum(Double.parseDouble(result[1]));
		deptPlanManage.setDeptPlan_applyPrice(Double.parseDouble(result[2]));
		deptPlanManage.setDeptPlan_storageNumber(Double.parseDouble("0"));
		stockInfo.setProduct_id(deptPlanManage.getDeptPlan_pro_id());
		stockInfo.setStock_storage(Integer.parseInt(deptPlanManage.getDeptPlan_applyDept()));
		storageNumber = stockInfoDao.getSumStorageByProductId(stockInfo);
		if(storageNumber != null){
			deptPlanManage.setDeptPlan_storageNumber(storageNumber);
		}
		
		money = deptPlanManage.getDeptPlan_applyNum() * deptPlanManage.getDeptPlan_applyPrice();
		deptPlanManage.setDeptPlan_applyMoney(money);
		deptPlanManage.setDeptPlan_applyPerson(SysUtil.getLoginUserId(request));
		deptPlanManage.setDeptPlan_applyTime(new Date());
		deptPlanManage.setDeptPlan_checkStatus("1");//未审核
		dao.add(deptPlanManage);
		return true;
	}

	/**
	 * 修改调货计划信息
	 * 
	 * @param bean 调货计划信息
	 * @param brand_id ID
	 * @return 调货计划信息
	 */
	@Override
	public boolean update(String data, HttpServletRequest request) {		
		log.debug("修改调货计划");
		DeptPlanManage deptPlanManage;
		System.out.println("data:"+data);
		Double money;
		//解析前台数据
		String checks[] = data.split("\\|");//使用转义字符,否则出错
		System.out.println("length:"+checks.length+" ++++++ "+checks[0]);
		
		if(checks[0]!=null&&!checks[0].equals("")){
			for(int i=0;i<checks.length;i++){
				System.out.println("checks:"+checks[i]);
				String result[] = checks[i].split("_");
				deptPlanManage = dao.get(Integer.parseInt(result[0]));
				deptPlanManage.setDeptPlan_applyNum(Double.parseDouble(result[1]));
				money = deptPlanManage.getDeptPlan_applyNum() * deptPlanManage.getDeptPlan_applyPrice();
				deptPlanManage.setDeptPlan_applyMoney(money);
				deptPlanManage.setDeptPlan_applyPerson(SysUtil.getLoginUserId(request));
				dao.update(deptPlanManage);
			}
		}
		
		return true;
	}

	/**
	 * 删除调货计划
	 * 
	 * @param brand_id ID
	 * @param request HttpServletRequest
	 */
	@Override
	public void delete(Integer id, HttpServletRequest request) {
		
		log.debug("删除调货计划成功 => ID : " + id);
		dao.delete(id);
	}
 
	@Override
	public Map<String, Object> stockInfoList(ProStockInfoVO bean){	
		log.debug("查询门店库存列表");		
		
		//插入查询条件-供方客户信息编码
		bean.setProduct_code(SysUtil.getSqlLikeParam(bean.getProduct_code()));
		bean.setShouldDeliveryNum(bean.getShouldDeliveryNum());
		bean.setProduct_musthave(SysUtil.getSqlLikeParam(bean.getProduct_musthave()));
		bean.setStock_storage(bean.getStock_storage());

		List<ProStockInfoVO> list = dao.stockInfoList(bean);
		int count = dao.countStockInfoList(bean);

		// 封装分页对象
		Map<String, Object> result = Maps.newHashMap();
		result.put("total", count);
		result.put("rows", list);
				
		return result;
	}
	@Override
	public void check(DeptPlanManage bean, HttpServletRequest request) {
		log.debug("审核调货计划");
		bean.setDeptPlan_checkPerson(SysUtil.getLoginUserId(request));
		bean.setDeptPlan_checkTime(new Date());
		bean.setDeptPlan_checkStatus("0");
		
		//获取计划票号
		String  deptPlan_ticket_id = getMaxDeptPlanTicket();
		bean.setDeptPlan_ticket_id(deptPlan_ticket_id);
		
		dao.update(bean);
	}
	
	/**
	 * 获取最大计划票号
	 * */
	@Override
	public String getMaxDeptPlanTicket() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String date = dateFormat.format(new Date());
        StringBuilder out  = new StringBuilder(date);
		Integer ticketID =  dao.getMaxDeptPlanTicket(date);
		if(ticketID != null){
			out.append(String.format("%04d",ticketID+1));
		}
		else{
			out.append("0001");
		}
		return "DHPlan"+out.toString();
	}
	@Override
	public DeptPlanManage update(DeptPlanManage bean, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public DeptPlanManage add(DeptPlanManage bean, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
	

}