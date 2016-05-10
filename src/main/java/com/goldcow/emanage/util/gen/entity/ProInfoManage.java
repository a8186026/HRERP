package com.goldcow.emanage.util.gen.entity;

import java.util.Date;

import com.goldcow.sframe.util.DataGridModel;
import com.goldcow.sframe.util.mybatis.BaseEntity;

/**
 * 产品信息实体类
 * 
 * @author gaoxiang
 * @version v1.0
 * @since 2015-6-5
 */
public class ProInfoManage extends DataGridModel implements BaseEntity {
	private static final long serialVersionUID = 1L;
	/** 产品序号 */
	private Integer product_id;
	/** 药品序号 */
	private Integer product_medicine_id;
	/** 厂家序号 */
	private Integer product_factory_id;
	/** 审核 0表示审核完成，1表示审核中，2表示审核不通过 */
	private Integer product_check;
	/** 产品编号 */
	private String product_code;
	/** 产品条码 */
	private String product_barcode;
	/** 建档日期 */
	private Date product_filingtime;
	/** 分类 */
	private String product_category;
	/** 分类2 */
	private String product_category2;
	/** 分类3 */
	private String product_category3;
	/** 分类4 */
	private String product_category4;
	/** 分类5 */
	private String product_category5;
	/** 其它分类 */
	private String product_othercategory;
	/** 其它分类2 */
	private String product_othercategory2;
	/** 其它分类3 */
	private String product_othercategory3;
	/** 其它分类4 */
	private String product_othercategory4;
	/** 其它分类5 */
	private String product_othercategory5;
	/** 通用名 */
	private String product_commonname;
	/** 通用名方式 */
	private String product_commonnametype;
	/** 剂型 */
	private String product_dosagetype;
	/** 产品名称 */
	private String product_name;
	/** 商品名 */
	private String product_proname;
	/** 拼音码 */
	private String product_chnpy;
	/** 规格 */
	private String product_specification;
	/** 单位 */
	private String product_unit;
	/** 产地 */
	private String product_productarea;
	/** 生产厂家拼音码 */
	private String product_factorychnpy;
	/** 批准文号 */
	private String product_approvalname;
	/** 批准文号 */
	private String product_approvalnum;
	/** 药监编号 */
	private String product_dsurveillanceno;	
	/** 药监id */
	private Integer product_dsurveillanceid;
	/** 税率 */
	private Double product_taxrate;
	/** 手续 */
	private String product_procedure;
	/** 中包量 */
	private Double product_middlepacking;
	/** 包装量 */
	private Integer product_packingamount;
	/** 包装单位 */
	private String product_packingunit;
	/** 装箱规格 */
	private String product_cartonsize;
	/** 配送包装量 */
	private Double product_shiphandlingnum;
	/** 货号 */
	private String product_articleno;
	/** 组号 */
	private String product_groupno;
	/** 注册商标 */
	private String product_regtrademark;
	/** 采购员 */
	private String product_buyer;
	/** 简介 */
	private String product_description;
	/** 基本备注 */
	private String product_basremark ;
	/** 注销 */
	private Integer product_cancel;
	/** 其它 */
	private Integer product_other;
	/** 其它1 */
	private Integer product_other1;
	/** 其它2 */
	private Integer product_other2;
	/** 其它3 */
	private Integer product_other3;
	/** 代销品种 */
	private Integer product_consignmentvariety;
	/** 统计品种 */
	private Integer product_statisticsvariety;
	/** 现结品种 */
	private Integer product_cashsettlevariety;
	/** 必备品种 */
	private Integer product_essentialvar;
	/** 中标品种 */
	private Integer product_bidvariety;
	/** 医保品种 */
	private Integer product_medinsuvariety;
	/** 农合品种 */
	private Integer product_acvariety;
	/** 促销品种 */
	private Integer product_promotionvariety;
	/** 特殊品种 */
	private Integer product_specialvariety;
	/** 养护品种 */
	private Integer product_maintainvariety;
	/** 处方药 */
	private Integer product_prescriptiondrug;
	/** 国家基药 */
	private Integer product_nationalbasmed;
	/** 基本药物 */
	private Integer product_basmedicine;
	/** 急救药品 */
	private Integer product_emergencymed;
	/** 入库发票 */
	private Integer product_instockinvoice;
	/** 出库发票 */
	private Integer product_outstockinvoice;
	/** 会员不积分 */
	private Integer product_mbernointegral;
	/** 会员不打折 */
	private Integer product_membernodis;
	/** 含特药复方制剂 */
	private Integer product_containspecialmed;
	/** 低价药 */
	private Integer product_lowpricemedicine;
	/** 批发价 */
	private Double product_tradeprice;
	/** 批发限价 */
	private Double product_wsalelprice;
	/** 批发最低价 */
	private Double product_lowsalelprice;
	/** 协议批发价 */
	private Double product_dealwsaleprice; 
	/** 销售价1 */
	private Double product_saleprice1;
	/** 销售价2 */
	private Double product_saleprice2;
	/** 销售价3 */
	private Double product_saleprice3;
	/** 销售价4 */
	private Double product_saleprice4;
	/** 市场价 */
	private Double product_marketprice;
	/** 零售价 */
	private Double product_retailprice;
	/** 零售限价 */
	private Double product_retaillprice;
	/** 零售最低价 */
	private Double product_lretaillprice;
	/** 协议零售价 */
	private Double product_dealretailprice;
	/** 会员价 */
	private Double product_memberprice;	
	/** 省标零售价 */
	private Double product_proretailprice;
	/** 基药中标价 */
	private Double product_basmedbidprice1;
	/** 基药中标价2 */
	private Double product_basmedbidprice2;
	/** 基药中标价3 */
	private Double product_basmedbidprice3;
	/** 中标价 */
	private Double product_bidprice;
	/** 零售提成 */
	private Double product_retailcommission;
	/** 开票提成 */
	private Double product_invoicecom;
	/** 批发提成 */
	private Double product_wholesalecom;
	/** 业务提成 */
	private Double product_businesscom;
	/** 国家限价 */
	private Double product_nationallprice;
	/** 协议进价 */
	private Double product_dealprice;
	/** 拆零组数 */
	private Integer product_tinynum;	
	/** 协议客户 */
	private String product_dealcustomer;
	/** 价格备注 */
	private String product_priceremark;
	/** 供方序号 */
	private Integer product_supplier_id;
	/** 最后进价 */
	private Double product_lastprice;
	/** 最低限量 */
	private Double product_minnunmber;
	/** 最高限量 */
	private Double product_maxnunmber;
	/** 进货量 */
	private Double product_purchasenum;
	/** 最高进价 */
	private Double product_highpurprice;
	/** 最低进价 */
	private Double product_lowpurprice;
	/** 企业GMP认证书 */
	private String product_GMPcertify;
	/** 认证时间 */
	private Date product_certificationtime;
	/** 有效期 */
	private Date product_licensevaliddate;
	/** 有效期至 */
	private Date product_licensevenddate;
	/** 提示天数 */
	private Integer product_remindday;
	/** 证照备注 */
	private String product_licenseremark;
	/** 证照有效期 */
	private Date product_licensevalidtime;
	/** 档案号 */
	private String product_filenumber;
	/** 档案位置 */
	private String product_filelocation;
	/** 限购数量 */
	private Double product_quotanum;
	/** 会员限量 */
	private Double product_memberlimit;
	/** 中标编号 */
	private String product_bidno;
	/** 中标编号2 */
	private String product_bidno2;
	/** 中标编号3 */
	private String product_bidno3;
	/** 入库锁定天数 */
	private Integer product_instocklday;
	/** 出库锁定天数 */
	private Integer product_outstocklday;
	/** 出库效期提示天数 */
	private String product_outstockrday;
	/** 国际码 */
	private String product_internationcode;
	/** 国家药监码 */
	private String product_nationaldscode;
	/** 质量标准 */
	private String product_quanlitystandard;
	/** 储存条件 */
	private String product_storagecondition;
	/** 储存库区 */
	private String product_storeplace;
	/** 低温 */
	private Double product_lowtemperature;
	/** 高温 */
	private Double product_hightemperature;
	/** 功效 */
	private String product_effect;
	/** 性状 */
	private String product_performance;
	/** 药品情况 */
	private String product_medcondition;
	/** 说明书 */
	private String product_instruction;
	/** 质量备注 */
	private String product_qualityremark;
	/** 停购 */
	private Integer product_stoppurchase;
	/** 停售 */
	private Integer product_salestop;
	/** 门店停配 */
	private Integer product_stopdistribution;
	/** 重点养护 */
	private Integer product_immaintain;
	/** 扫描药监码 */
	private Integer product_dsscancode;
	/** 打印质检单 */
	private Integer product_qacprint;
	/** 打印回执 */
	private Integer product_printreceipt;
	/** 打印方式 */
	private Integer product_printtype;
	/** 双复核验收 */
	private Integer product_doublecheck;
	/** 输入批号 */
	private Integer product_inputbatchnum;
	/** 输入有效期 */
	private Integer product_inputvalidtime;
	/** 输入生产日期 */
	private Integer product_inputproductdate;
	/** 输入灭菌批号 */
	private Integer product_sterilizationbnum;
	/** 输入灭菌日期 */
	private Integer product_sterilizationtime;
	/** 输入批准文号 */
	private Integer product_inputappnum;
	/** 审核时间 */
	private Date product_checktime;
	/** 审核人 */
	private String product_checkperson;
	/** 经营分类 */
	private String product_managecetagory;
	/** 经营分类2 */
	private String product_managecetagory2;
	/** 经营分类3 */
	private String product_managecetagory3;
	/** 经营分类4 */
	private String product_managecetagory4;
	/** 经营分类5 */
	private String product_managecetagory5;
	/** 流水号 */
	private String product_serialno;
	/** 所属部门 */
	private String product_department;
	/** 所属集团 */
	private String product_group;
	/** 业务员 */
	private String product_staff;
	/** 保管员 */
	private String product_keeper;
	/** 合同号 */
	private String product_contractno;
	/** 联系人 */
	private String product_contactperson;
	/** 验收员 */
	private String product_examiner ;
	/** 接收 */
	private String product_receive;
	/** 接收序号 */
	private Integer product_receiveno;
	/** 接收包装 */
	private Integer product_receivepackage;
	/** 添加日期 */
	private Date product_createtime;
	/** 失效日期 */
	private Date product_invaliddate;
	/** 库存数量 */
	private Double product_stocknum;
	/** 下浮 */
	private Double product_lower;
	/** 回款天数 */
	private Integer product_remoneyday;
	/** ABC类 */
	private String product_catagoryABC;
	/** 标签 */
	private String product_laber;
	/** 储位 */
	private String product_stockposition;
	/** 政策数 */
	private Integer product_policynum;
	/** 赠品数 */
	private Integer product_giftnum;
	/** 主治功能 */
	private String product_majorfunction;
	/** 主治明细 */
	private String product_majordetail;
	/** 产地拼音码 */
	private String product_proareacode;
	/** 医保类别 */
	private String product_medinsucategory;
	/** 新条码 */
	private String product_newbarcode;
	/** 原条码 */
	private String product_oldbarcode;
	/** 商标证 */
	private String product_brandlcertify;
	/** 条码证 */
	private String product_barcodecertify;
	/** OTC证书 */
	private String product_OTCcertify;
	/** 生产批文 */
	private String product_proapproval;
	/** 物价批文 */
	private String product_priceapproval;
	/** 包装批件 */
	private String product_packingapproval;
	/** 省检报告 */
	private String product_pinspectionreport;
	/** 批检报告 */
	private String product_inspectionreport;
	/** 是否积分 */
	private Integer product_integral ;
	/** 是否特价 */
	private Integer product_bargain;
	/** 是否买赠 */
	private Integer product_gift;
	/** 打折 */
	private Double product_discount;
	/** 配送包装 */
	private Integer product_shiphandling;
	/** 门店停售 */
	private Integer product_storesalestop;
	/** 首次进货时间 */
	private Date product_firststocktime;
	/** 医保编号 */
	private String product_medinsuno;
	/** 医保分类 */
	private String product_medinsuclassify;
	/** 医审状态 */
	private String product_medinsustatus;
	/** 停止计划 */
	private Integer product_planstop;
	/** 医保上传 */
	private Integer product_medinsuupload;
	/** 医保上传时间 */
	private Date product_medinsuuploadtime;
	/** 最后进货日期 */
	private Date product_laststocktime;
	/** 买赠特殊批次 */
	private Integer product_giftspecialbatch;
	/** 组合买赠特殊批次 */
	private Integer product_comgspecialbatch;
	/** 满额赠特殊批次 */
	private Integer product_ftqgspecialbatch;
	/** 添加人 */
	private String product_createperson;
	/** 原条码 */
	private String product_oldstorecode;
	/** 生产厂家 */
	private String product_factoryname;
	/** 定价日期 */
	private Date product_pricetime;
	/** 修改日期 */
	private Date product_updatetime;
	/** 审批表 */
	private Integer product_approvalform;
	/** 效期 */
	private Integer product_validdate;
	
	/** 图片名称 */
	private String product_picname;
	/** 图片备注 */
	private String product_picremark;
	/** 器材图片url */
	private String product_picture;
	
	/** 状态 0启用 1停用 9删除 */
	private Integer status;
	/** 创建人 */
	private Integer create_user;
	/** 创建时间 */
	private Date create_time;
	/** 最后修改人 */
	private Integer last_modify_user;
	/** 最后修改时间 */
	private Date last_modify_time;
	/** 必调  1必调 0正常*/
	private String product_musthave;
	/** 价格格式 */
	private String product_amountFormat;
	/** 数量格式 */
	private String product_numberFormat;
	/** 记分金额 */
	private Double product_moneyPerPoint;
	
	public Double getProduct_moneyPerPoint() {
		return product_moneyPerPoint;
	}
	public void setProduct_moneyPerPoint(Double product_moneyPerPoint) {
		this.product_moneyPerPoint = product_moneyPerPoint;
	}
	public String getProduct_amountFormat() {
		return product_amountFormat;
	}
	public void setProduct_amountFormat(String product_amountFormat) {
		this.product_amountFormat = product_amountFormat;
	}
	public String getProduct_numberFormat() {
		return product_numberFormat;
	}
	public void setProduct_numberFormat(String product_numberFormat) {
		this.product_numberFormat = product_numberFormat;
	}
	public String getProduct_musthave() {
		return product_musthave;
	}
	public void setProduct_musthave(String product_musthave) {
		this.product_musthave = product_musthave;
	}
	public Integer getProduct_id() {
		return product_id;
	}
	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}
	public Integer getProduct_medicine_id() {
		return product_medicine_id;
	}
	public void setProduct_medicine_id(Integer product_medicine_id) {
		this.product_medicine_id = product_medicine_id;
	}
	public Integer getProduct_factory_id() {
		return product_factory_id;
	}
	public void setProduct_factory_id(Integer product_factory_id) {
		this.product_factory_id = product_factory_id;
	}
	public Integer getProduct_check() {
		return product_check;
	}
	public void setProduct_check(Integer product_check) {
		this.product_check = product_check;
	}
	public String getProduct_code() {
		return product_code;
	}
	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}
	public String getProduct_barcode() {
		return product_barcode;
	}
	public void setProduct_barcode(String product_barcode) {
		this.product_barcode = product_barcode;
	}
	public Date getProduct_filingtime() {
		return product_filingtime;
	}
	public void setProduct_filingtime(Date product_filingtime) {
		this.product_filingtime = product_filingtime;
	}
	public String getProduct_category() {
		return product_category;
	}
	public void setProduct_category(String product_category) {
		this.product_category = product_category;
	}
	public String getProduct_category2() {
		return product_category2;
	}
	public void setProduct_category2(String product_category2) {
		this.product_category2 = product_category2;
	}
	public String getProduct_category3() {
		return product_category3;
	}
	public void setProduct_category3(String product_category3) {
		this.product_category3 = product_category3;
	}
	public String getProduct_category4() {
		return product_category4;
	}
	public void setProduct_category4(String product_category4) {
		this.product_category4 = product_category4;
	}
	public String getProduct_category5() {
		return product_category5;
	}
	public void setProduct_category5(String product_category5) {
		this.product_category5 = product_category5;
	}
	public String getProduct_othercategory() {
		return product_othercategory;
	}
	public void setProduct_othercategory(String product_othercategory) {
		this.product_othercategory = product_othercategory;
	}
	public String getProduct_othercategory2() {
		return product_othercategory2;
	}
	public void setProduct_othercategory2(String product_othercategory2) {
		this.product_othercategory2 = product_othercategory2;
	}
	public String getProduct_othercategory3() {
		return product_othercategory3;
	}
	public void setProduct_othercategory3(String product_othercategory3) {
		this.product_othercategory3 = product_othercategory3;
	}
	public String getProduct_othercategory4() {
		return product_othercategory4;
	}
	public void setProduct_othercategory4(String product_othercategory4) {
		this.product_othercategory4 = product_othercategory4;
	}
	public String getProduct_othercategory5() {
		return product_othercategory5;
	}
	public void setProduct_othercategory5(String product_othercategory5) {
		this.product_othercategory5 = product_othercategory5;
	}
	public String getProduct_commonname() {
		return product_commonname;
	}
	public void setProduct_commonname(String product_commonname) {
		this.product_commonname = product_commonname;
	}
	public String getProduct_commonnametype() {
		return product_commonnametype;
	}
	public void setProduct_commonnametype(String product_commonnametype) {
		this.product_commonnametype = product_commonnametype;
	}
	public String getProduct_dosagetype() {
		return product_dosagetype;
	}
	public void setProduct_dosagetype(String product_dosagetype) {
		this.product_dosagetype = product_dosagetype;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getProduct_proname() {
		return product_proname;
	}
	public void setProduct_proname(String product_proname) {
		this.product_proname = product_proname;
	}
	public String getProduct_chnpy() {
		return product_chnpy;
	}
	public void setProduct_chnpy(String product_chnpy) {
		this.product_chnpy = product_chnpy;
	}
	public String getProduct_specification() {
		return product_specification;
	}
	public void setProduct_specification(String product_specification) {
		this.product_specification = product_specification;
	}
	public String getProduct_unit() {
		return product_unit;
	}
	public void setProduct_unit(String product_unit) {
		this.product_unit = product_unit;
	}
	public String getProduct_productarea() {
		return product_productarea;
	}
	public void setProduct_productarea(String product_productarea) {
		this.product_productarea = product_productarea;
	}
	public String getProduct_factorychnpy() {
		return product_factorychnpy;
	}
	public void setProduct_factorychnpy(String product_factorychnpy) {
		this.product_factorychnpy = product_factorychnpy;
	}
	public String getProduct_approvalname() {
		return product_approvalname;
	}
	public void setProduct_approvalname(String product_approvalname) {
		this.product_approvalname = product_approvalname;
	}
	public String getProduct_approvalnum() {
		return product_approvalnum;
	}
	public void setProduct_approvalnum(String product_approvalnum) {
		this.product_approvalnum = product_approvalnum;
	}
	public String getProduct_dsurveillanceno() {
		return product_dsurveillanceno;
	}
	public void setProduct_dsurveillanceno(String product_dsurveillanceno) {
		this.product_dsurveillanceno = product_dsurveillanceno;
	}
	public Integer getProduct_dsurveillanceid() {
		return product_dsurveillanceid;
	}
	public void setProduct_dsurveillanceid(Integer product_dsurveillanceid) {
		this.product_dsurveillanceid = product_dsurveillanceid;
	}
	public Double getProduct_taxrate() {
		return product_taxrate;
	}
	public void setProduct_taxrate(Double product_taxrate) {
		this.product_taxrate = product_taxrate;
	}
	public String getProduct_procedure() {
		return product_procedure;
	}
	public void setProduct_procedure(String product_procedure) {
		this.product_procedure = product_procedure;
	}
	public Double getProduct_middlepacking() {
		return product_middlepacking;
	}
	public void setProduct_middlepacking(Double product_middlepacking) {
		this.product_middlepacking = product_middlepacking;
	}
	public Integer getProduct_packingamount() {
		return product_packingamount;
	}
	public void setProduct_packingamount(Integer product_packingamount) {
		this.product_packingamount = product_packingamount;
	}
	public String getProduct_packingunit() {
		return product_packingunit;
	}
	public void setProduct_packingunit(String product_packingunit) {
		this.product_packingunit = product_packingunit;
	}
	public String getProduct_cartonsize() {
		return product_cartonsize;
	}
	public void setProduct_cartonsize(String product_cartonsize) {
		this.product_cartonsize = product_cartonsize;
	}
	public Double getProduct_shiphandlingnum() {
		return product_shiphandlingnum;
	}
	public void setProduct_shiphandlingnum(Double product_shiphandlingnum) {
		this.product_shiphandlingnum = product_shiphandlingnum;
	}
	public String getProduct_articleno() {
		return product_articleno;
	}
	public void setProduct_articleno(String product_articleno) {
		this.product_articleno = product_articleno;
	}
	public String getProduct_groupno() {
		return product_groupno;
	}
	public void setProduct_groupno(String product_groupno) {
		this.product_groupno = product_groupno;
	}
	public String getProduct_regtrademark() {
		return product_regtrademark;
	}
	public void setProduct_regtrademark(String product_regtrademark) {
		this.product_regtrademark = product_regtrademark;
	}
	public String getProduct_buyer() {
		return product_buyer;
	}
	public void setProduct_buyer(String product_buyer) {
		this.product_buyer = product_buyer;
	}
	public String getProduct_description() {
		return product_description;
	}
	public void setProduct_description(String product_description) {
		this.product_description = product_description;
	}
	public String getProduct_basremark() {
		return product_basremark;
	}
	public void setProduct_basremark(String product_basremark) {
		this.product_basremark = product_basremark;
	}
	public Integer getProduct_cancel() {
		return product_cancel;
	}
	public void setProduct_cancel(Integer product_cancel) {
		this.product_cancel = product_cancel;
	}
	public Integer getProduct_other() {
		return product_other;
	}
	public void setProduct_other(Integer product_other) {
		this.product_other = product_other;
	}
	public Integer getProduct_other1() {
		return product_other1;
	}
	public void setProduct_other1(Integer product_other1) {
		this.product_other1 = product_other1;
	}
	public Integer getProduct_other2() {
		return product_other2;
	}
	public void setProduct_other2(Integer product_other2) {
		this.product_other2 = product_other2;
	}
	public Integer getProduct_other3() {
		return product_other3;
	}
	public void setProduct_other3(Integer product_other3) {
		this.product_other3 = product_other3;
	}
	public Integer getProduct_consignmentvariety() {
		return product_consignmentvariety;
	}
	public void setProduct_consignmentvariety(Integer product_consignmentvariety) {
		this.product_consignmentvariety = product_consignmentvariety;
	}
	public Integer getProduct_statisticsvariety() {
		return product_statisticsvariety;
	}
	public void setProduct_statisticsvariety(Integer product_statisticsvariety) {
		this.product_statisticsvariety = product_statisticsvariety;
	}
	public Integer getProduct_cashsettlevariety() {
		return product_cashsettlevariety;
	}
	public void setProduct_cashsettlevariety(Integer product_cashsettlevariety) {
		this.product_cashsettlevariety = product_cashsettlevariety;
	}
	public Integer getProduct_essentialvar() {
		return product_essentialvar;
	}
	public void setProduct_essentialvar(Integer product_essentialvar) {
		this.product_essentialvar = product_essentialvar;
	}
	public Integer getProduct_bidvariety() {
		return product_bidvariety;
	}
	public void setProduct_bidvariety(Integer product_bidvariety) {
		this.product_bidvariety = product_bidvariety;
	}
	public Integer getProduct_medinsuvariety() {
		return product_medinsuvariety;
	}
	public void setProduct_medinsuvariety(Integer product_medinsuvariety) {
		this.product_medinsuvariety = product_medinsuvariety;
	}
	public Integer getProduct_acvariety() {
		return product_acvariety;
	}
	public void setProduct_acvariety(Integer product_acvariety) {
		this.product_acvariety = product_acvariety;
	}
	public Integer getProduct_promotionvariety() {
		return product_promotionvariety;
	}
	public void setProduct_promotionvariety(Integer product_promotionvariety) {
		this.product_promotionvariety = product_promotionvariety;
	}
	public Integer getProduct_specialvariety() {
		return product_specialvariety;
	}
	public void setProduct_specialvariety(Integer product_specialvariety) {
		this.product_specialvariety = product_specialvariety;
	}
	public Integer getProduct_maintainvariety() {
		return product_maintainvariety;
	}
	public void setProduct_maintainvariety(Integer product_maintainvariety) {
		this.product_maintainvariety = product_maintainvariety;
	}
	public Integer getProduct_prescriptiondrug() {
		return product_prescriptiondrug;
	}
	public void setProduct_prescriptiondrug(Integer product_prescriptiondrug) {
		this.product_prescriptiondrug = product_prescriptiondrug;
	}
	public Integer getProduct_nationalbasmed() {
		return product_nationalbasmed;
	}
	public void setProduct_nationalbasmed(Integer product_nationalbasmed) {
		this.product_nationalbasmed = product_nationalbasmed;
	}
	public Integer getProduct_basmedicine() {
		return product_basmedicine;
	}
	public void setProduct_basmedicine(Integer product_basmedicine) {
		this.product_basmedicine = product_basmedicine;
	}
	public Integer getProduct_emergencymed() {
		return product_emergencymed;
	}
	public void setProduct_emergencymed(Integer product_emergencymed) {
		this.product_emergencymed = product_emergencymed;
	}
	public Integer getProduct_instockinvoice() {
		return product_instockinvoice;
	}
	public void setProduct_instockinvoice(Integer product_instockinvoice) {
		this.product_instockinvoice = product_instockinvoice;
	}
	public Integer getProduct_outstockinvoice() {
		return product_outstockinvoice;
	}
	public void setProduct_outstockinvoice(Integer product_outstockinvoice) {
		this.product_outstockinvoice = product_outstockinvoice;
	}
	public Integer getProduct_mbernointegral() {
		return product_mbernointegral;
	}
	public void setProduct_mbernointegral(Integer product_mbernointegral) {
		this.product_mbernointegral = product_mbernointegral;
	}
	public Integer getProduct_membernodis() {
		return product_membernodis;
	}
	public void setProduct_membernodis(Integer product_membernodis) {
		this.product_membernodis = product_membernodis;
	}
	public Integer getProduct_containspecialmed() {
		return product_containspecialmed;
	}
	public void setProduct_containspecialmed(Integer product_containspecialmed) {
		this.product_containspecialmed = product_containspecialmed;
	}
	public Integer getProduct_lowpricemedicine() {
		return product_lowpricemedicine;
	}
	public void setProduct_lowpricemedicine(Integer product_lowpricemedicine) {
		this.product_lowpricemedicine = product_lowpricemedicine;
	}
	public Double getProduct_tradeprice() {
		return product_tradeprice;
	}
	public void setProduct_tradeprice(Double product_tradeprice) {
		this.product_tradeprice = product_tradeprice;
	}
	public Double getProduct_wsalelprice() {
		return product_wsalelprice;
	}
	public void setProduct_wsalelprice(Double product_wsalelprice) {
		this.product_wsalelprice = product_wsalelprice;
	}
	public Double getProduct_lowsalelprice() {
		return product_lowsalelprice;
	}
	public void setProduct_lowsalelprice(Double product_lowsalelprice) {
		this.product_lowsalelprice = product_lowsalelprice;
	}
	public Double getProduct_dealwsaleprice() {
		return product_dealwsaleprice;
	}
	public void setProduct_dealwsaleprice(Double product_dealwsaleprice) {
		this.product_dealwsaleprice = product_dealwsaleprice;
	}
	public Double getProduct_saleprice1() {
		return product_saleprice1;
	}
	public void setProduct_saleprice1(Double product_saleprice1) {
		this.product_saleprice1 = product_saleprice1;
	}
	public Double getProduct_saleprice2() {
		return product_saleprice2;
	}
	public void setProduct_saleprice2(Double product_saleprice2) {
		this.product_saleprice2 = product_saleprice2;
	}
	public Double getProduct_saleprice3() {
		return product_saleprice3;
	}
	public void setProduct_saleprice3(Double product_saleprice3) {
		this.product_saleprice3 = product_saleprice3;
	}
	public Double getProduct_saleprice4() {
		return product_saleprice4;
	}
	public void setProduct_saleprice4(Double product_saleprice4) {
		this.product_saleprice4 = product_saleprice4;
	}
	public Double getProduct_marketprice() {
		return product_marketprice;
	}
	public void setProduct_marketprice(Double product_marketprice) {
		this.product_marketprice = product_marketprice;
	}
	public Double getProduct_retailprice() {
		return product_retailprice;
	}
	public void setProduct_retailprice(Double product_retailprice) {
		this.product_retailprice = product_retailprice;
	}
	public Double getProduct_retaillprice() {
		return product_retaillprice;
	}
	public void setProduct_retaillprice(Double product_retaillprice) {
		this.product_retaillprice = product_retaillprice;
	}
	public Double getProduct_lretaillprice() {
		return product_lretaillprice;
	}
	public void setProduct_lretaillprice(Double product_lretaillprice) {
		this.product_lretaillprice = product_lretaillprice;
	}
	public Double getProduct_dealretailprice() {
		return product_dealretailprice;
	}
	public void setProduct_dealretailprice(Double product_dealretailprice) {
		this.product_dealretailprice = product_dealretailprice;
	}
	public Double getProduct_memberprice() {
		return product_memberprice;
	}
	public void setProduct_memberprice(Double product_memberprice) {
		this.product_memberprice = product_memberprice;
	}
	public Double getProduct_proretailprice() {
		return product_proretailprice;
	}
	public void setProduct_proretailprice(Double product_proretailprice) {
		this.product_proretailprice = product_proretailprice;
	}
	public Double getProduct_basmedbidprice1() {
		return product_basmedbidprice1;
	}
	public void setProduct_basmedbidprice1(Double product_basmedbidprice1) {
		this.product_basmedbidprice1 = product_basmedbidprice1;
	}
	public Double getProduct_basmedbidprice2() {
		return product_basmedbidprice2;
	}
	public void setProduct_basmedbidprice2(Double product_basmedbidprice2) {
		this.product_basmedbidprice2 = product_basmedbidprice2;
	}
	public Double getProduct_basmedbidprice3() {
		return product_basmedbidprice3;
	}
	public void setProduct_basmedbidprice3(Double product_basmedbidprice3) {
		this.product_basmedbidprice3 = product_basmedbidprice3;
	}
	public Double getProduct_bidprice() {
		return product_bidprice;
	}
	public void setProduct_bidprice(Double product_bidprice) {
		this.product_bidprice = product_bidprice;
	}
	public Double getProduct_retailcommission() {
		return product_retailcommission;
	}
	public void setProduct_retailcommission(Double product_retailcommission) {
		this.product_retailcommission = product_retailcommission;
	}
	public Double getProduct_invoicecom() {
		return product_invoicecom;
	}
	public void setProduct_invoicecom(Double product_invoicecom) {
		this.product_invoicecom = product_invoicecom;
	}
	public Double getProduct_wholesalecom() {
		return product_wholesalecom;
	}
	public void setProduct_wholesalecom(Double product_wholesalecom) {
		this.product_wholesalecom = product_wholesalecom;
	}
	public Double getProduct_businesscom() {
		return product_businesscom;
	}
	public void setProduct_businesscom(Double product_businesscom) {
		this.product_businesscom = product_businesscom;
	}
	public Double getProduct_nationallprice() {
		return product_nationallprice;
	}
	public void setProduct_nationallprice(Double product_nationallprice) {
		this.product_nationallprice = product_nationallprice;
	}
	public Double getProduct_dealprice() {
		return product_dealprice;
	}
	public void setProduct_dealprice(Double product_dealprice) {
		this.product_dealprice = product_dealprice;
	}
	public Integer getProduct_tinynum() {
		return product_tinynum;
	}
	public void setProduct_tinynum(Integer product_tinynum) {
		this.product_tinynum = product_tinynum;
	}
	public String getProduct_dealcustomer() {
		return product_dealcustomer;
	}
	public void setProduct_dealcustomer(String product_dealcustomer) {
		this.product_dealcustomer = product_dealcustomer;
	}
	public String getProduct_priceremark() {
		return product_priceremark;
	}
	public void setProduct_priceremark(String product_priceremark) {
		this.product_priceremark = product_priceremark;
	}
	public Integer getProduct_supplier_id() {
		return product_supplier_id;
	}
	public void setProduct_supplier_id(Integer product_supplier_id) {
		this.product_supplier_id = product_supplier_id;
	}
	public Double getProduct_lastprice() {
		return product_lastprice;
	}
	public void setProduct_lastprice(Double product_lastprice) {
		this.product_lastprice = product_lastprice;
	}
	public Double getProduct_minnunmber() {
		return product_minnunmber;
	}
	public void setProduct_minnunmber(Double product_minnunmber) {
		this.product_minnunmber = product_minnunmber;
	}
	public Double getProduct_maxnunmber() {
		return product_maxnunmber;
	}
	public void setProduct_maxnunmber(Double product_maxnunmber) {
		this.product_maxnunmber = product_maxnunmber;
	}
	public Double getProduct_purchasenum() {
		return product_purchasenum;
	}
	public void setProduct_purchasenum(Double product_purchasenum) {
		this.product_purchasenum = product_purchasenum;
	}
	public Double getProduct_highpurprice() {
		return product_highpurprice;
	}
	public void setProduct_highpurprice(Double product_highpurprice) {
		this.product_highpurprice = product_highpurprice;
	}
	public Double getProduct_lowpurprice() {
		return product_lowpurprice;
	}
	public void setProduct_lowpurprice(Double product_lowpurprice) {
		this.product_lowpurprice = product_lowpurprice;
	}
	public String getProduct_GMPcertify() {
		return product_GMPcertify;
	}
	public void setProduct_GMPcertify(String product_GMPcertify) {
		this.product_GMPcertify = product_GMPcertify;
	}
	public Date getProduct_certificationtime() {
		return product_certificationtime;
	}
	public void setProduct_certificationtime(Date product_certificationtime) {
		this.product_certificationtime = product_certificationtime;
	}
	public Date getProduct_licensevaliddate() {
		return product_licensevaliddate;
	}
	public void setProduct_licensevaliddate(Date product_licensevaliddate) {
		this.product_licensevaliddate = product_licensevaliddate;
	}
	public Date getProduct_licensevenddate() {
		return product_licensevenddate;
	}
	public void setProduct_licensevenddate(Date product_licensevenddate) {
		this.product_licensevenddate = product_licensevenddate;
	}
	public Integer getProduct_remindday() {
		return product_remindday;
	}
	public void setProduct_remindday(Integer product_remindday) {
		this.product_remindday = product_remindday;
	}
	public String getProduct_licenseremark() {
		return product_licenseremark;
	}
	public void setProduct_licenseremark(String product_licenseremark) {
		this.product_licenseremark = product_licenseremark;
	}
	public Date getProduct_licensevalidtime() {
		return product_licensevalidtime;
	}
	public void setProduct_licensevalidtime(Date product_licensevalidtime) {
		this.product_licensevalidtime = product_licensevalidtime;
	}
	public String getProduct_filenumber() {
		return product_filenumber;
	}
	public void setProduct_filenumber(String product_filenumber) {
		this.product_filenumber = product_filenumber;
	}
	public String getProduct_filelocation() {
		return product_filelocation;
	}
	public void setProduct_filelocation(String product_filelocation) {
		this.product_filelocation = product_filelocation;
	}
	public Double getProduct_quotanum() {
		return product_quotanum;
	}
	public void setProduct_quotanum(Double product_quotanum) {
		this.product_quotanum = product_quotanum;
	}
	public Double getProduct_memberlimit() {
		return product_memberlimit;
	}
	public void setProduct_memberlimit(Double product_memberlimit) {
		this.product_memberlimit = product_memberlimit;
	}
	public String getProduct_bidno() {
		return product_bidno;
	}
	public void setProduct_bidno(String product_bidno) {
		this.product_bidno = product_bidno;
	}
	public String getProduct_bidno2() {
		return product_bidno2;
	}
	public void setProduct_bidno2(String product_bidno2) {
		this.product_bidno2 = product_bidno2;
	}
	public String getProduct_bidno3() {
		return product_bidno3;
	}
	public void setProduct_bidno3(String product_bidno3) {
		this.product_bidno3 = product_bidno3;
	}
	public Integer getProduct_instocklday() {
		return product_instocklday;
	}
	public void setProduct_instocklday(Integer product_instocklday) {
		this.product_instocklday = product_instocklday;
	}
	public Integer getProduct_outstocklday() {
		return product_outstocklday;
	}
	public void setProduct_outstocklday(Integer product_outstocklday) {
		this.product_outstocklday = product_outstocklday;
	}
	public String getProduct_outstockrday() {
		return product_outstockrday;
	}
	public void setProduct_outstockrday(String product_outstockrday) {
		this.product_outstockrday = product_outstockrday;
	}
	public String getProduct_internationcode() {
		return product_internationcode;
	}
	public void setProduct_internationcode(String product_internationcode) {
		this.product_internationcode = product_internationcode;
	}
	public String getProduct_nationaldscode() {
		return product_nationaldscode;
	}
	public void setProduct_nationaldscode(String product_nationaldscode) {
		this.product_nationaldscode = product_nationaldscode;
	}
	public String getProduct_quanlitystandard() {
		return product_quanlitystandard;
	}
	public void setProduct_quanlitystandard(String product_quanlitystandard) {
		this.product_quanlitystandard = product_quanlitystandard;
	}
	public String getProduct_storagecondition() {
		return product_storagecondition;
	}
	public void setProduct_storagecondition(String product_storagecondition) {
		this.product_storagecondition = product_storagecondition;
	}
	public String getProduct_storeplace() {
		return product_storeplace;
	}
	public void setProduct_storeplace(String product_storeplace) {
		this.product_storeplace = product_storeplace;
	}
	public Double getProduct_lowtemperature() {
		return product_lowtemperature;
	}
	public void setProduct_lowtemperature(Double product_lowtemperature) {
		this.product_lowtemperature = product_lowtemperature;
	}
	public Double getProduct_hightemperature() {
		return product_hightemperature;
	}
	public void setProduct_hightemperature(Double product_hightemperature) {
		this.product_hightemperature = product_hightemperature;
	}
	public String getProduct_effect() {
		return product_effect;
	}
	public void setProduct_effect(String product_effect) {
		this.product_effect = product_effect;
	}
	public String getProduct_performance() {
		return product_performance;
	}
	public void setProduct_performance(String product_performance) {
		this.product_performance = product_performance;
	}
	public String getProduct_medcondition() {
		return product_medcondition;
	}
	public void setProduct_medcondition(String product_medcondition) {
		this.product_medcondition = product_medcondition;
	}
	public String getProduct_instruction() {
		return product_instruction;
	}
	public void setProduct_instruction(String product_instruction) {
		this.product_instruction = product_instruction;
	}
	public String getProduct_qualityremark() {
		return product_qualityremark;
	}
	public void setProduct_qualityremark(String product_qualityremark) {
		this.product_qualityremark = product_qualityremark;
	}
	public Integer getProduct_stoppurchase() {
		return product_stoppurchase;
	}
	public void setProduct_stoppurchase(Integer product_stoppurchase) {
		this.product_stoppurchase = product_stoppurchase;
	}
	public Integer getProduct_salestop() {
		return product_salestop;
	}
	public void setProduct_salestop(Integer product_salestop) {
		this.product_salestop = product_salestop;
	}
	public Integer getProduct_stopdistribution() {
		return product_stopdistribution;
	}
	public void setProduct_stopdistribution(Integer product_stopdistribution) {
		this.product_stopdistribution = product_stopdistribution;
	}
	public Integer getProduct_immaintain() {
		return product_immaintain;
	}
	public void setProduct_immaintain(Integer product_immaintain) {
		this.product_immaintain = product_immaintain;
	}
	public Integer getProduct_dsscancode() {
		return product_dsscancode;
	}
	public void setProduct_dsscancode(Integer product_dsscancode) {
		this.product_dsscancode = product_dsscancode;
	}
	public Integer getProduct_qacprint() {
		return product_qacprint;
	}
	public void setProduct_qacprint(Integer product_qacprint) {
		this.product_qacprint = product_qacprint;
	}
	public Integer getProduct_printreceipt() {
		return product_printreceipt;
	}
	public void setProduct_printreceipt(Integer product_printreceipt) {
		this.product_printreceipt = product_printreceipt;
	}
	public Integer getProduct_printtype() {
		return product_printtype;
	}
	public void setProduct_printtype(Integer product_printtype) {
		this.product_printtype = product_printtype;
	}
	public Integer getProduct_doublecheck() {
		return product_doublecheck;
	}
	public void setProduct_doublecheck(Integer product_doublecheck) {
		this.product_doublecheck = product_doublecheck;
	}
	public Integer getProduct_inputbatchnum() {
		return product_inputbatchnum;
	}
	public void setProduct_inputbatchnum(Integer product_inputbatchnum) {
		this.product_inputbatchnum = product_inputbatchnum;
	}
	public Integer getProduct_inputvalidtime() {
		return product_inputvalidtime;
	}
	public void setProduct_inputvalidtime(Integer product_inputvalidtime) {
		this.product_inputvalidtime = product_inputvalidtime;
	}
	public Integer getProduct_inputproductdate() {
		return product_inputproductdate;
	}
	public void setProduct_inputproductdate(Integer product_inputproductdate) {
		this.product_inputproductdate = product_inputproductdate;
	}
	public Integer getProduct_sterilizationbnum() {
		return product_sterilizationbnum;
	}
	public void setProduct_sterilizationbnum(Integer product_sterilizationbnum) {
		this.product_sterilizationbnum = product_sterilizationbnum;
	}
	public Integer getProduct_sterilizationtime() {
		return product_sterilizationtime;
	}
	public void setProduct_sterilizationtime(Integer product_sterilizationtime) {
		this.product_sterilizationtime = product_sterilizationtime;
	}
	public Integer getProduct_inputappnum() {
		return product_inputappnum;
	}
	public void setProduct_inputappnum(Integer product_inputappnum) {
		this.product_inputappnum = product_inputappnum;
	}
	public Date getProduct_checktime() {
		return product_checktime;
	}
	public void setProduct_checktime(Date product_checktime) {
		this.product_checktime = product_checktime;
	}
	public String getProduct_checkperson() {
		return product_checkperson;
	}
	public void setProduct_checkperson(String product_checkperson) {
		this.product_checkperson = product_checkperson;
	}
	public String getProduct_managecetagory() {
		return product_managecetagory;
	}
	public void setProduct_managecetagory(String product_managecetagory) {
		this.product_managecetagory = product_managecetagory;
	}
	public String getProduct_managecetagory2() {
		return product_managecetagory2;
	}
	public void setProduct_managecetagory2(String product_managecetagory2) {
		this.product_managecetagory2 = product_managecetagory2;
	}
	public String getProduct_managecetagory3() {
		return product_managecetagory3;
	}
	public void setProduct_managecetagory3(String product_managecetagory3) {
		this.product_managecetagory3 = product_managecetagory3;
	}
	public String getProduct_managecetagory4() {
		return product_managecetagory4;
	}
	public void setProduct_managecetagory4(String product_managecetagory4) {
		this.product_managecetagory4 = product_managecetagory4;
	}
	public String getProduct_managecetagory5() {
		return product_managecetagory5;
	}
	public void setProduct_managecetagory5(String product_managecetagory5) {
		this.product_managecetagory5 = product_managecetagory5;
	}
	public String getProduct_serialno() {
		return product_serialno;
	}
	public void setProduct_serialno(String product_serialno) {
		this.product_serialno = product_serialno;
	}
	public String getProduct_department() {
		return product_department;
	}
	public void setProduct_department(String product_department) {
		this.product_department = product_department;
	}
	public String getProduct_group() {
		return product_group;
	}
	public void setProduct_group(String product_group) {
		this.product_group = product_group;
	}
	public String getProduct_staff() {
		return product_staff;
	}
	public void setProduct_staff(String product_staff) {
		this.product_staff = product_staff;
	}
	public String getProduct_keeper() {
		return product_keeper;
	}
	public void setProduct_keeper(String product_keeper) {
		this.product_keeper = product_keeper;
	}
	public String getProduct_contractno() {
		return product_contractno;
	}
	public void setProduct_contractno(String product_contractno) {
		this.product_contractno = product_contractno;
	}
	public String getProduct_contactperson() {
		return product_contactperson;
	}
	public void setProduct_contactperson(String product_contactperson) {
		this.product_contactperson = product_contactperson;
	}
	public String getProduct_examiner() {
		return product_examiner;
	}
	public void setProduct_examiner(String product_examiner) {
		this.product_examiner = product_examiner;
	}
	public String getProduct_receive() {
		return product_receive;
	}
	public void setProduct_receive(String product_receive) {
		this.product_receive = product_receive;
	}
	public Integer getProduct_receiveno() {
		return product_receiveno;
	}
	public void setProduct_receiveno(Integer product_receiveno) {
		this.product_receiveno = product_receiveno;
	}
	public Integer getProduct_receivepackage() {
		return product_receivepackage;
	}
	public void setProduct_receivepackage(Integer product_receivepackage) {
		this.product_receivepackage = product_receivepackage;
	}
	public Date getProduct_createtime() {
		return product_createtime;
	}
	public void setProduct_createtime(Date product_createtime) {
		this.product_createtime = product_createtime;
	}
	public Date getProduct_invaliddate() {
		return product_invaliddate;
	}
	public void setProduct_invaliddate(Date product_invaliddate) {
		this.product_invaliddate = product_invaliddate;
	}
	public Double getProduct_stocknum() {
		return product_stocknum;
	}
	public void setProduct_stocknum(Double product_stocknum) {
		this.product_stocknum = product_stocknum;
	}
	public Double getProduct_lower() {
		return product_lower;
	}
	public void setProduct_lower(Double product_lower) {
		this.product_lower = product_lower;
	}
	public Integer getProduct_remoneyday() {
		return product_remoneyday;
	}
	public void setProduct_remoneyday(Integer product_remoneyday) {
		this.product_remoneyday = product_remoneyday;
	}
	public String getProduct_catagoryABC() {
		return product_catagoryABC;
	}
	public void setProduct_catagoryABC(String product_catagoryABC) {
		this.product_catagoryABC = product_catagoryABC;
	}
	public String getProduct_laber() {
		return product_laber;
	}
	public void setProduct_laber(String product_laber) {
		this.product_laber = product_laber;
	}
	public String getProduct_stockposition() {
		return product_stockposition;
	}
	public void setProduct_stockposition(String product_stockposition) {
		this.product_stockposition = product_stockposition;
	}
	public Integer getProduct_policynum() {
		return product_policynum;
	}
	public void setProduct_policynum(Integer product_policynum) {
		this.product_policynum = product_policynum;
	}
	public Integer getProduct_giftnum() {
		return product_giftnum;
	}
	public void setProduct_giftnum(Integer product_giftnum) {
		this.product_giftnum = product_giftnum;
	}
	public String getProduct_majorfunction() {
		return product_majorfunction;
	}
	public void setProduct_majorfunction(String product_majorfunction) {
		this.product_majorfunction = product_majorfunction;
	}
	public String getProduct_majordetail() {
		return product_majordetail;
	}
	public void setProduct_majordetail(String product_majordetail) {
		this.product_majordetail = product_majordetail;
	}
	public String getProduct_proareacode() {
		return product_proareacode;
	}
	public void setProduct_proareacode(String product_proareacode) {
		this.product_proareacode = product_proareacode;
	}
	public String getProduct_medinsucategory() {
		return product_medinsucategory;
	}
	public void setProduct_medinsucategory(String product_medinsucategory) {
		this.product_medinsucategory = product_medinsucategory;
	}
	public String getProduct_newbarcode() {
		return product_newbarcode;
	}
	public void setProduct_newbarcode(String product_newbarcode) {
		this.product_newbarcode = product_newbarcode;
	}
	public String getProduct_oldbarcode() {
		return product_oldbarcode;
	}
	public void setProduct_oldbarcode(String product_oldbarcode) {
		this.product_oldbarcode = product_oldbarcode;
	}
	public String getProduct_brandlcertify() {
		return product_brandlcertify;
	}
	public void setProduct_brandlcertify(String product_brandlcertify) {
		this.product_brandlcertify = product_brandlcertify;
	}
	public String getProduct_barcodecertify() {
		return product_barcodecertify;
	}
	public void setProduct_barcodecertify(String product_barcodecertify) {
		this.product_barcodecertify = product_barcodecertify;
	}
	public String getProduct_OTCcertify() {
		return product_OTCcertify;
	}
	public void setProduct_OTCcertify(String product_OTCcertify) {
		this.product_OTCcertify = product_OTCcertify;
	}
	public String getProduct_proapproval() {
		return product_proapproval;
	}
	public void setProduct_proapproval(String product_proapproval) {
		this.product_proapproval = product_proapproval;
	}
	public String getProduct_priceapproval() {
		return product_priceapproval;
	}
	public void setProduct_priceapproval(String product_priceapproval) {
		this.product_priceapproval = product_priceapproval;
	}
	public String getProduct_packingapproval() {
		return product_packingapproval;
	}
	public void setProduct_packingapproval(String product_packingapproval) {
		this.product_packingapproval = product_packingapproval;
	}
	public String getProduct_pinspectionreport() {
		return product_pinspectionreport;
	}
	public void setProduct_pinspectionreport(String product_pinspectionreport) {
		this.product_pinspectionreport = product_pinspectionreport;
	}
	public String getProduct_inspectionreport() {
		return product_inspectionreport;
	}
	public void setProduct_inspectionreport(String product_inspectionreport) {
		this.product_inspectionreport = product_inspectionreport;
	}
	public Integer getProduct_integral() {
		return product_integral;
	}
	public void setProduct_integral(Integer product_integral) {
		this.product_integral = product_integral;
	}
	public Integer getProduct_bargain() {
		return product_bargain;
	}
	public void setProduct_bargain(Integer product_bargain) {
		this.product_bargain = product_bargain;
	}
	public Integer getProduct_gift() {
		return product_gift;
	}
	public void setProduct_gift(Integer product_gift) {
		this.product_gift = product_gift;
	}
	public Double getProduct_discount() {
		return product_discount;
	}
	public void setProduct_discount(Double product_discount) {
		this.product_discount = product_discount;
	}
	public Integer getProduct_shiphandling() {
		return product_shiphandling;
	}
	public void setProduct_shiphandling(Integer product_shiphandling) {
		this.product_shiphandling = product_shiphandling;
	}
	public Integer getProduct_storesalestop() {
		return product_storesalestop;
	}
	public void setProduct_storesalestop(Integer product_storesalestop) {
		this.product_storesalestop = product_storesalestop;
	}
	public Date getProduct_firststocktime() {
		return product_firststocktime;
	}
	public void setProduct_firststocktime(Date product_firststocktime) {
		this.product_firststocktime = product_firststocktime;
	}
	public String getProduct_medinsuno() {
		return product_medinsuno;
	}
	public void setProduct_medinsuno(String product_medinsuno) {
		this.product_medinsuno = product_medinsuno;
	}
	public String getProduct_medinsuclassify() {
		return product_medinsuclassify;
	}
	public void setProduct_medinsuclassify(String product_medinsuclassify) {
		this.product_medinsuclassify = product_medinsuclassify;
	}
	public String getProduct_medinsustatus() {
		return product_medinsustatus;
	}
	public void setProduct_medinsustatus(String product_medinsustatus) {
		this.product_medinsustatus = product_medinsustatus;
	}
	public Integer getProduct_planstop() {
		return product_planstop;
	}
	public void setProduct_planstop(Integer product_planstop) {
		this.product_planstop = product_planstop;
	}
	public Integer getProduct_medinsuupload() {
		return product_medinsuupload;
	}
	public void setProduct_medinsuupload(Integer product_medinsuupload) {
		this.product_medinsuupload = product_medinsuupload;
	}
	public Date getProduct_medinsuuploadtime() {
		return product_medinsuuploadtime;
	}
	public void setProduct_medinsuuploadtime(Date product_medinsuuploadtime) {
		this.product_medinsuuploadtime = product_medinsuuploadtime;
	}
	public Date getProduct_laststocktime() {
		return product_laststocktime;
	}
	public void setProduct_laststocktime(Date product_laststocktime) {
		this.product_laststocktime = product_laststocktime;
	}
	public Integer getProduct_giftspecialbatch() {
		return product_giftspecialbatch;
	}
	public void setProduct_giftspecialbatch(Integer product_giftspecialbatch) {
		this.product_giftspecialbatch = product_giftspecialbatch;
	}
	public Integer getProduct_comgspecialbatch() {
		return product_comgspecialbatch;
	}
	public void setProduct_comgspecialbatch(Integer product_comgspecialbatch) {
		this.product_comgspecialbatch = product_comgspecialbatch;
	}
	public Integer getProduct_ftqgspecialbatch() {
		return product_ftqgspecialbatch;
	}
	public void setProduct_ftqgspecialbatch(Integer product_ftqgspecialbatch) {
		this.product_ftqgspecialbatch = product_ftqgspecialbatch;
	}
	public String getProduct_createperson() {
		return product_createperson;
	}
	public void setProduct_createperson(String product_createperson) {
		this.product_createperson = product_createperson;
	}
	public String getProduct_oldstorecode() {
		return product_oldstorecode;
	}
	public void setProduct_oldstorecode(String product_oldstorecode) {
		this.product_oldstorecode = product_oldstorecode;
	}
	public String getProduct_factoryname() {
		return product_factoryname;
	}
	public void setProduct_factoryname(String product_factoryname) {
		this.product_factoryname = product_factoryname;
	}
	public Date getProduct_pricetime() {
		return product_pricetime;
	}
	public void setProduct_pricetime(Date product_pricetime) {
		this.product_pricetime = product_pricetime;
	}
	public Date getProduct_updatetime() {
		return product_updatetime;
	}
	public void setProduct_updatetime(Date product_updatetime) {
		this.product_updatetime = product_updatetime;
	}
	public Integer getProduct_approvalform() {
		return product_approvalform;
	}
	public void setProduct_approvalform(Integer product_approvalform) {
		this.product_approvalform = product_approvalform;
	}
	public Integer getProduct_validdate() {
		return product_validdate;
	}
	public void setProduct_validdate(Integer product_validdate) {
		this.product_validdate = product_validdate;
	}
	public String getProduct_picname() {
		return product_picname;
	}
	public void setProduct_picname(String product_picname) {
		this.product_picname = product_picname;
	}
	public String getProduct_picremark() {
		return product_picremark;
	}
	public void setProduct_picremark(String product_picremark) {
		this.product_picremark = product_picremark;
	}
	public String getProduct_picture() {
		return product_picture;
	}
	public void setProduct_picture(String product_picture) {
		this.product_picture = product_picture;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getCreate_user() {
		return create_user;
	}
	public void setCreate_user(Integer create_user) {
		this.create_user = create_user;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Integer getLast_modify_user() {
		return last_modify_user;
	}
	public void setLast_modify_user(Integer last_modify_user) {
		this.last_modify_user = last_modify_user;
	}
	public Date getLast_modify_time() {
		return last_modify_time;
	}
	public void setLast_modify_time(Date last_modify_time) {
		this.last_modify_time = last_modify_time;
	}
	
	
}