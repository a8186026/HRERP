package com.goldcow.sframe.util;

import java.util.Date;

import org.springframework.context.ApplicationContext;

import com.goldcow.platform.syscontext.PlatformGlobalVar;

public class GlobalVal {
	private GlobalVal() {}
	public static ApplicationContext ctx;

	public static final String USER_SESSION = "userSession";
	public static final String ALL_DEFAULT_VAL = "0";
	public static final String ADMIN_NAME = PlatformGlobalVar.SYS_PROPERTIES.get("adminName");// 访问sys.properties属性
	public static final String ADMIN_PASS_WD = PlatformGlobalVar.SYS_PROPERTIES.get("adminPassWd");
	public static final String TOKEN = "SPRING_TOKEN";
	public static final String TOKEN_BAK = "SPRING_TOKEN_BAK";
	public static final Integer SCALE = 8;

	/**
	 * 最高级菜单的定义
	 */
	public final static String UNIT_LEVEL = "1";
	public final static String P_DEPT_ID = "0";

	/**
	 * 工作流模板名称定义
	 */
	public static final String WORKFLOW_OFFICIAL_DOCUMENT = "oaDispatchProcess";

	/**
	 * 接口平台地址
	 */
	public static String MODI_ADDRESS = null;// "http://192.168.6.212:4080/modi/restful/simple";
	
	public static void setMODI_ADDRESS(String mODI_ADDRESS) {
		MODI_ADDRESS = mODI_ADDRESS;
	}

	/**
	 * 业务角色
	 */
	public static class BUSINESS_ROLE {
		/** 公文签发 */
		public static final Integer OFFICE_DOCUMENT_01 = 3003;
		/** 公文签收 */
		public static final Integer OFFICE_DOCUMENT_02 = 3004;
		/** 查看全部通讯录 */
		public static final Integer CONTACTS_ALL = 3005;
	}
	
	public static class MENU_FUNCTION {
		/** 查看 */
		public static final int VIEW = 1;
		/** 新增 */
		public static final int ADD = 1 << 1;
		/** 修改 */
		public static final int UPDATE = 1 << 2;
		/** 删除 */
		public static final int DELETE = 1 << 3;
		/** 提交审批 */
		public static final int SUBMIT = 1 << 4;
		/** 审批 */
		public static final int CHECK = 1 << 5;
		/** 管理 */
		public static final int MANAGE = 1 << 6;
	}
	
	public static class STATIC_MENU {
		/** 登录 */
		public static final String LOGIN_PAGE = "/redirect:/login.html";
		/** 无权限提示页 */
		public static final String NO_RIGHT_PAGE = "errors/noRightPage.jsp";
	}
	
	public static class RECORD_STATUS {
		/** 启用 */
		public static final int ENABLE = 0;
		/** 信用 */
		public static final int DISABLE = 1;
		/** 删除 */
		public static final int DELETED = 9;
	}

	/**
	 * 一楼行政前台名称定义
	 */
	public static final String OFFICE_ASSETS_POSITION = "办公资产负责人";
	public static final String OFFICE_ASSETS_POSITION1 = "1楼行政前台";
	public static final String OFFICE_ASSETS_POSITION2 = "2楼行政前台";
	public static final String OFFICE_ASSETS_POSITION3 = "3楼行政前台";
	public static final String OFFICE_ASSETS_POSITION4 = "4楼行政前台";
	public static final String OFFICE_ASSETS_POSITION5 = "5楼行政前台";
	public static final String OFFICE_ASSETS_POSITION6 = "6楼行政前台";

	public static void init() {
		// System.out.println(ADMIN_NAME);
	}

	public static final boolean DEV_MODE = true;
	
	//收货流程状态标识
	public static class ACCEPT_STATUS {
		/** 收货状态 accept_acceptStatus 0表示已收货1表示未收货2表示待审核*/
		public static final int ACCEPTED = 0;
		public static final int UNACCEPT = 1;
		public static final int WAIT_REJECT_CHECK = 2;
		
		
		/** 拒收状态 accept_rejectStatus 0表示审核通过1表示审核不通过2表示未审核  */
		public static final int REJECT_SUCCESS = 0;
		public static final int REJECT_FAILED = 1;
		public static final int UNREJECT = 2;
		
		/** 质量验收状态 accept_quantityStatus 0表示质检完成1表示未质检2表示待质检合格审核3表示待特殊药品审核  */
		public static final int QUANTITIED = 0;
		public static final int UNQUANTITY = 1;
		public static final int WAIT_QUANTITY_CHECK = 2;
		public static final int WAIT_SPECIAL_CHECK = 3;
		
		/** 合格审核状态 accept_quantityCheckStatus 0表示质检合格审核通过1表示质检合格审核不通过2表示质检合格未审核  */
		public static final int QUANTIY_CHECKE_SUCCESS = 0;
		public static final int QUANTIY_CHECKE_FAILED = 1;
		public static final int UNQUANTITY_CHECK = 2;
		
		/** 特殊药品审核状态  accept_specialStatus 0表示审核通过1表示审核不通过2表示特殊药品未审核  */
		public static final int SPECIAL_CHECKE_SUCCESS = 0;
		public static final int SPECIAL_CHECKE_FAILED = 1;
		public static final int UNSPECIAL_CHECK = 2;
		
		/** 库房验收状态 0表示已入库 1表示未入库  */
		public static final int STOCKED = 0;
		public static final int UNSTOCK= 1;
		
		/** 小单到货状态 isArriva 0表示到货已完成 1表示到货未完成 2表示等待拒收审核 */
		public static final int ARRAIVALED = 0;
		public static final int UNARRAIVAL = 1;
		public static final int WAIT_REJECTED = 2;
		
		/** 大单收货状态  goodsReceive 0表示已收货 1表示未收货 */
		public static final int RECEIVED = 0;
		public static final int UNRECEIVE = 1;
		
		/** 大单审核状态  checkStatus 0表示已审核 1表示未审核 */
		public static final int ORDER_CHECKED = 0;
		public static final int ORDER_UNCHECKED = 1;
	}
	
	public static class REFUND_STATUS {
		//登记状态
		public static final int REFUND_REGISTER_SUCCESS=0;
		public static final int REFUND_UNREGISTER=1;
		
		//审核状态
		public static final int REFUND_CHECK_SUCCESS=0;
		public static final int REFUND_UNCHECK=1;
		
		//付货状态
		public static final int REFUND_DELIVERY_SUCCESS=0;
		public static final int REFUND_UNDELIVERY=1;
		
		//取消返货
		public static final int REFUND_CANCEL_SUCCESS=0;
		public static final int REFUND_UNCANCEL=1;
		
		//直接禁用掉该项申请则直接将数据库中的status置为9
		public static final int REFUND_STATUS_CANCEL=9;
		
	}
	
	public static class STOCK_STATUS {
		//停售状态,1停售，0启用
		public static final int STOCK_STOP=1;
		public static final int STOCK_START=0;
	}
	//不合格品种流程状态标识
	public static class DEFECTS_STATUS {
	
		/** 不合格品种确认  defects_check  0表示审核通过   1表示未审核  */
		public static final int DEFECTS_CHECK = 0;
		public static final int DEFECTS_UNCHECK = 1;
		
		/** 损益状态  defects_profitLoss  0表示审核通过   1表示未审核  */
		public static final int PROFITLOSS_CHECK = 0;
		public static final int PROFITLOSS_UNCHECK = 1;
		
		/** 报损申请状态  defects_breakage  0表示审核通过    1表示未审核  */
		public static final int BREAKAGE_CHECK = 0;
		public static final int BREAKAGE_UNCHECK = 1;
		
		/** 销毁状态  defects_destruction  0表示审核通过  1表示未审核  */
		public static final int DESTRUCTION_CHECK = 0;
		public static final int DESTRUCTION_UNCHECK = 1;
		
		/** 采购部审核  defects_purchasedCheck  0表示审核通过   1表示未审核  */
		public static final int PURCHASE_CHECK = 0;
		public static final int PURCHASE_UNCHECK = 1;
		
		/** 质管部审核   defects_qualitycdCheck  0表示审核通过   1表示未审核  */
		public static final int QUALITYC_CHECK = 0;
		public static final int QUALITYC_UNCHECK = 1;
		
		/** 储运部审核   defects_storagetdCheck	 0表示审核通过   1表示未审核  */
		public static final int STORAGET_CHECK = 0;
		public static final int STORAGET_UNCHECK = 1;
		
		/** 质量负责人审核  defects_qaCheck  0表示审核通过   1表示未审核  */
		public static final int QA_CHECK = 0;
		public static final int QA_UNCHECK = 1;
		
		/** 财务部审核   defects_financialdCheck  0表示审核通过   1表示未审核  */
		public static final int FINANCIAL_CHECK = 0;
		public static final int FINANCIAL_UNCHECK = 1;
		
		/** 经理审核   defects_managerCheck	 0表示审核通过   1表示未审核  */
		public static final int MANAGER_CHECK = 0;
		public static final int MANAGER_UNCHECK = 1;
		 
	}
		
	//下拉框键值对应(数据库对应的ID)
	public static class COMBOBOX{
		//执行价格
		public static final int RETAIL_PRICE = 651;
		public static final int MEMBER_PRICE = 652;
		public static final int SALE_PRICE1 = 653;
		public static final int SALE_PRICE2 = 961;
		public static final int SALE_PRICE3 = 962;
		public static final int SALE_PRICE4 = 963;
		
		//折扣方式
		public static final int MEMBER_DISCOUNT = 632;
		public static final int CUSTOMIZE_DISCOUNT = 633;
	}
}
