package com.goldcow.emanage.util.gen.entity;

import java.util.Date;

import com.goldcow.sframe.util.DataGridModel;
import com.goldcow.sframe.util.mybatis.BaseEntity;

	/**
	 * 销方信息管理实体类
	 * 
	 * @author chenyuxuan
	 * @version v1.0
	 * @since 2015-7-6
	 */

public class SalInfoManage extends DataGridModel implements BaseEntity {
	private static final long serialVersionUID = 1L;
	/** 销方序号 */
	private Integer sal_id;
	/** 类别 */
	private String sal_type;
	/** 区号 */
	private String sal_areacode;
	/** 销方编码 */
	private String sal_code;
	/** 会员卡号 */
	private String sal_memcardno;
	/** 简称 */
	private String sal_shortname;
	/** 客户全称 */
	private String sal_customername;
	/** 业务员 */
	private String sal_salesman;
	/** 性质 */
	private String sal_property;
	/** 所属部门 */
	private String sal_department;
	/** 拼音码 */
	private String sal_chnpy;
	/** 应收 */
	private Double sal_receivable;
	/** 供方序号 */
	private Integer sal_sup_id;
	/** 信用额度 */
	private Double sal_creditline;
	/** 联系人 */
	private String sal_contactperson;
	/** 电话 */
	private String sal_phone;
	/** 手机 */
	private String sal_telephone;
	/** 地址 */
	private String sal_address;
	/** 邮编 */
	private String sal_zipcode;
	/** 保证金 */
	private Double sal_margin;
	/** 开户行 */
	private String sal_accountbank;
	/** 帐号 */
	private String sal_accountno;
	/** 税号 */
	private String sal_tax;
	/** 添加日期 */
	private Date sal_createtime;
	/** 修改日期 */
	private Date sal_updatetime;
	/** 简介 */
	private String sal_description;
	/** 执行价格 */
	private String sal_strikeprice;
	/** 结算方式 */
	private String sal_settlestyle;
	/** 合同号 */
	private String sal_contractno;
	/** 回款率 */
	private Integer sal_remoneyrate;
	/** 回款天数 */
	private Integer sal_remoneyday;
	/** 手续 */
	private String sal_procedure;
	/** 注销 */
	private Integer sal_cancel;
	/** 全部可用 */
	private Integer sal_allusable;
	/** 禁售剂型 */
	private String sal_lockeddosage;
	/** 失效日期 */
	private Date sal_invailddate;
	/** 金额结款 */
	private Integer sal_amountsettle;
	/** 票据结款 */
	private Integer sal_billsettle;
	/** 品种结款 */
	private Integer sal_varietysettle;
	/** 货到结款 */
	private Integer sal_arrivalsettle;
	/** 天数结款 */
	private Integer sal_daysettle;
	/** 接收序号 */
	private Integer sal_receiveno;
	/** 远程 */
	private Integer sal_longdistance;
	/** 密码 */
	private String sal_password;
	/** 站点数 */
	private Integer sal_stationno;
	/** 自动调拨 */
	private Integer sal_autoallocation;
	/** 客户 */
	private Integer sal_customer;
	/** 结款方式 */
	private String sal_paymentway;
	/** [B] */
	private String sal_A;
	/** [A] */
	private String sal_B;
	/** 接收 */
	private Integer sal_receive;
	/** 区域 */
	private String sal_area;
	/** 小区域 */
	private String sal_smallarea;
	/** 区域排序 */
	private String sal_areasort;
	/** 结算价输入 */
	private Integer sal_inputsettleprice;
	/** 客户订货时间 */
	private Date sal_customerordertime;
	/** 认证范围 */
	private String sal_qltycatescope;
	/** 委托书失效期 */
	private Date sal_proxy;
	/** 所属客户 */
	private Integer sal_belongscustomer;
	/** 电子信箱 */
	private String sal_email;
	/** 许可证号 */
	private String sal_permitno;
	/** 许可证名称 */
	private String sal_permitname;
	/** 许可证负责人 */
	private String sal_permitchief;
	/** 许可证发证机关 */
	private String sal_permitissuingauthority;
	/** 许可证有效期 */
	private Date sal_permitvailddate;
	/** 法人 */
	private String sal_legalperson;
	/** 注册号 */
	private String sal_registrationno;
	/** 注册资金 */
	private String sal_registeredmoney;
	/** 经济性质 */
	private String sal_economyproperty;
	/** 经营方式 */
	private String sal_bnslicenseway;
	/** 经营范围 */
	private String sal_bnslicensescope;
	/** 执照有效期 */
	private Date sal_bnslicensevailddate;
	/** 经营禁售 */
	private String sal_operationlock;
	/** 药监ID */
	private Integer sal_drugid;
	/** 传真 */
	private String sal_fax;
	/** 办税人员 */
	private String sal_taxperson;
	/** 营业执照发证机关 */
	private String sal_bnslicenseissuingauthority;
	/** 许可范围 */
	private String sal_permitscope;
	/** 零售库房 */
	private String sal_retailstore;
	/** 审核 */
	private Integer sal_check;
	/** 审核人 */
	private String sal_checkperson;
	/** 审核时间 */
	private Date sal_checktime;
	/** 最后销售日期 */
	private Date sal_lastsaledate;
	/** 类别2 */
	private String sal_category2;
	/** 类别3 */
	private String sal_category3;
	/** MSN */
	private String sal_msn;
	/** QQ */
	private String sal_qq;
	/** 开户户名 */
	private String sal_accountname;
	/** 汇款方式 */
	private String sal_remittanceway;
	/** 结款日期 */
	private String sal_paymentdate;
	/** 一般纳税人 */
	private Integer sal_commontaxpayer;
	/** 财务备注 */
	private String sal_financialnote;
	/** 许可证提示天数 */
	private Integer sal_permitcueday;
	/** 营业执照年检截止 */
	private Date sal_bnslicenseannualdeadline;
	/** 营业执照提示天数 */
	private Integer sal_bnslicensecueday;
	/** 营业执照年检备注 */
	private String sal_bnslicenseannualnote;
	/** 质量认证书名 */
	private String sal_qltycatename;
	/** 质量认证书编号 */
	private String sal_qltycateno;
	/** 质量证书有效期 */
	private Date sal_qltycatevailddate;
	/** 质量证书提示天数 */
	private Integer sal_qltycatecueday;
	/** 质量证书发证机关 */
	private String sal_qltycateissuingauthority;
	/** 证照备注 */
	private String sal_licensenote;
	/** 业务员身份证 */
	private String sal_clerkcard;
	/** 业务员电话 */
	private String sal_clerktel;
	/** 业务员学历证 */
	private Integer sal_clerkedu;
	/** 业务员上岗证有效期 */
	private Date sal_clerkvaliddate;
	/** 业务员上岗证提示天数 */
	private Integer sal_clerkcueday;
	/** 随货票样 */
	private Integer sal_goodsticket;
	/** 相关印章 */
	private String sal_relatedseal;
	/** 印章印模 */
	private Integer sal_sealmold;
	/** 档案号 */
	private String sal_fileno;
	/** 档案位置 */
	private String sal_filelocation;
	/** 国家药监码 */
	private String sal_drugcode;
	/** 年产值 */
	private String sal_annualoutput;
	/** 技术人员 */
	private String sal_techinician;
	/** 主要荣誉 */
	private String sal_majorhonor;
	/** 拟供品种 */
	private String sal_intendedspecies;
	/** 主要产品 */
	private String sal_majorproduct;
	/** 质量状况 */
	private String sal_qualitystatus;
	/** 质量机构 */
	private String sal_qualityinstitution;
	/** 认证情况 */
	private String sal_qualityauthentication;
	/** 质量管理 */
	private String sal_qualitymanage;
	/** 质量负责人 */
	private String sal_qa;
	/** 质量负责人性别 */
	private String sal_qasex;
	/** 质量负责人文化程度 */
	private String sal_qaedu;
	/** 质量负责人职称 */
	private String sal_qatitle;
	/** 质量负责人职务 */
	private String sal_qaposition;
	/** 质量负责人工作年限 */
	private String sal_qaworkyear;
	/** 质量备注 */
	private String sal_qualitynote;
	/** 建档日期 */
	private Date sal_fillingdate;
	/** 质检单 */
	private String sal_qacprint;
	/** 其它 */
	private Integer sal_other;
	/** 其它1 */
	private Integer sal_other1;
	/** 其它2 */
	private Integer sal_other2;
	/** 其它3 */
	private Integer sal_other3;
	/** 印章印模图片 */
	private String sal_sealmoldphoto;
	/** 注册地址 */
	private String sal_registeraddr;
	/** 添加人 */
	private String sal_addperson;
	/** 收货人 */
	private String sal_receiptperson;
	/** 提货人 */
	private String sal_deliverperson;
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
	/** 提示天数 */
	private String sal_salcueday;
	/** 委托区域 */
	private String sal_salentrustarea;
	/** 开户账号 */
	private String sal_accountnumber;
	/** 业务员委托区域 */
	private String sal_clerkretrustarea;
	/** 身份证 */
	private String sal_idnumber;
	/** 是否为一般纳税人 */
	private String sal_istaxpayer;
	/** 地区药监 */
	private String sal_localdrug;
	/** 查询号 */
	private String sal_finddrugid;
	

	public String getSal_finddrugid() {
		return sal_finddrugid;
	}
	public void setSal_finddrugid(String sal_finddrugid) {
		this.sal_finddrugid = sal_finddrugid;
	}
	public String getSal_localdrug() {
		return sal_localdrug;
	}
	public void setSal_localdrug(String sal_localdrug) {
		this.sal_localdrug = sal_localdrug;
	}
	public String getSal_istaxpayer() {
		return sal_istaxpayer;
	}
	public void setSal_istaxpayer(String sal_istaxpayer) {
		this.sal_istaxpayer = sal_istaxpayer;
	}
	public Integer getSal_id(){
		return this.sal_id;
	}
	public void setSal_id(Integer sal_id){
		this.sal_id=sal_id;
	}
	public String getSal_type(){
		return this.sal_type;
	}
	public void setSal_type(String sal_type){
		this.sal_type=sal_type;
	}
	public String getSal_areacode(){
		return this.sal_areacode;
	}
	public void setSal_areacode(String sal_areacode){
		this.sal_areacode=sal_areacode;
	}
	public String getSal_code(){
		return this.sal_code;
	}
	public void setSal_code(String sal_code){
		this.sal_code=sal_code;
	}
	public String getSal_memcardno(){
		return this.sal_memcardno;
	}
	public void setSal_memcardno(String sal_memcardno){
		this.sal_memcardno=sal_memcardno;
	}

	public String getSal_shortname() {
		return sal_shortname;
	}
	public void setSal_shortname(String sal_shortname) {
		this.sal_shortname = sal_shortname;
	}
	public String getSal_customername(){
		return this.sal_customername;
	}
	public void setSal_customername(String sal_customername){
		this.sal_customername=sal_customername;
	}
	public String getSal_salesman(){
		return this.sal_salesman;
	}
	public void setSal_salesman(String sal_salesman){
		this.sal_salesman=sal_salesman;
	}
	public String getSal_property(){
		return this.sal_property;
	}
	public void setSal_property(String sal_property){
		this.sal_property=sal_property;
	}
	public String getSal_department(){
		return this.sal_department;
	}
	public void setSal_department(String sal_department){
		this.sal_department=sal_department;
	}
	public String getSal_chnpy() {
		return sal_chnpy;
	}
	public void setSal_chnpy(String sal_chnpy) {
		this.sal_chnpy = sal_chnpy;
	}
	public Double getSal_receivable() {
		return sal_receivable;
	}
	public void setSal_receivable(Double sal_receivable) {
		this.sal_receivable = sal_receivable;
	}
	public Integer getSal_sup_id(){
		return this.sal_sup_id;
	}
	public void setSal_sup_id(Integer sal_sup_id){
		this.sal_sup_id=sal_sup_id;
	}
	public Double getSal_creditline() {
		return sal_creditline;
	}
	public void setSal_creditline(Double sal_creditline) {
		this.sal_creditline = sal_creditline;
	}
	public String getSal_contactperson(){
		return this.sal_contactperson;
	}
	public void setSal_contactperson(String sal_contactperson){
		this.sal_contactperson=sal_contactperson;
	}
	public String getSal_phone(){
		return this.sal_phone;
	}
	public void setSal_phone(String sal_phone){
		this.sal_phone=sal_phone;
	}
	public String getSal_telephone(){
		return this.sal_telephone;
	}
	public void setSal_telephone(String sal_telephone){
		this.sal_telephone=sal_telephone;
	}
	public String getSal_address(){
		return this.sal_address;
	}
	public void setSal_address(String sal_address){
		this.sal_address=sal_address;
	}
	public String getSal_zipcode(){
		return this.sal_zipcode;
	}
	public void setSal_zipcode(String sal_zipcode){
		this.sal_zipcode=sal_zipcode;
	}

	public Double getSal_margin() {
		return sal_margin;
	}
	public void setSal_margin(Double sal_margin) {
		this.sal_margin = sal_margin;
	}
	public String getSal_accountbank(){
		return this.sal_accountbank;
	}
	public void setSal_accountbank(String sal_accountbank){
		this.sal_accountbank=sal_accountbank;
	}
	public String getSal_accountno(){
		return this.sal_accountno;
	}
	public void setSal_accountno(String sal_accountno){
		this.sal_accountno=sal_accountno;
	}
	public String getSal_tax(){
		return this.sal_tax;
	}
	public void setSal_tax(String sal_tax){
		this.sal_tax=sal_tax;
	}
	public Date getSal_createtime(){
		return this.sal_createtime;
	}
	public void setSal_createtime(Date sal_createtime){
		this.sal_createtime=sal_createtime;
	}
	public Date getSal_updatetime(){
		return this.sal_updatetime;
	}
	public void setSal_updatetime(Date sal_updatetime){
		this.sal_updatetime=sal_updatetime;
	}
	public String getSal_description(){
		return this.sal_description;
	}
	public void setSal_description(String sal_description){
		this.sal_description=sal_description;
	}
	public String getSal_strikeprice(){
		return this.sal_strikeprice;
	}
	public void setSal_strikeprice(String sal_strikeprice){
		this.sal_strikeprice=sal_strikeprice;
	}
	public String getSal_settlestyle(){
		return this.sal_settlestyle;
	}
	public void setSal_settlestyle(String sal_settlestyle){
		this.sal_settlestyle=sal_settlestyle;
	}
	public String getSal_contractno(){
		return this.sal_contractno;
	}
	public void setSal_contractno(String sal_contractno){
		this.sal_contractno=sal_contractno;
	}
	public Integer getSal_remoneyrate(){
		return this.sal_remoneyrate;
	}
	public void setSal_remoneyrate(Integer sal_remoneyrate){
		this.sal_remoneyrate=sal_remoneyrate;
	}
	public Integer getSal_remoneyday(){
		return this.sal_remoneyday;
	}
	public void setSal_remoneyday(Integer sal_remoneyday){
		this.sal_remoneyday=sal_remoneyday;
	}
	public String getSal_fileno(){
		return this.sal_fileno;
	}
	public void setSal_fileno(String sal_fileno){
		this.sal_fileno=sal_fileno;
	}
	public String getSal_procedure(){
		return this.sal_procedure;
	}
	public void setSal_procedure(String sal_procedure){
		this.sal_procedure=sal_procedure;
	}
	public Integer getSal_cancel(){
		return this.sal_cancel;
	}
	public void setSal_cancel(Integer sal_cancel){
		this.sal_cancel=sal_cancel;
	}
	public Integer getSal_allusable(){
		return this.sal_allusable;
	}
	public void setSal_allusable(Integer sal_allusable){
		this.sal_allusable=sal_allusable;
	}
	public String getSal_lockeddosage(){
		return this.sal_lockeddosage;
	}
	public void setSal_lockeddosage(String sal_lockeddosage){
		this.sal_lockeddosage=sal_lockeddosage;
	}
	public String getSal_bnslicensescope(){
		return this.sal_bnslicensescope;
	}
	public void setSal_bnslicensescope(String sal_bnslicensescope){
		this.sal_bnslicensescope=sal_bnslicensescope;
	}
	public Date getSal_invailddate(){
		return this.sal_invailddate;
	}
	public void setSal_invailddate(Date sal_invailddate){
		this.sal_invailddate=sal_invailddate;
	}
	public Integer getSal_amountsettle(){
		return this.sal_amountsettle;
	}
	public void setSal_amountsettle(Integer sal_amountsettle){
		this.sal_amountsettle=sal_amountsettle;
	}
	public Integer getSal_billsettle(){
		return this.sal_billsettle;
	}
	public void setSal_billsettle(Integer sal_billsettle){
		this.sal_billsettle=sal_billsettle;
	}
	public Integer getSal_varietysettle(){
		return this.sal_varietysettle;
	}
	public void setSal_varietysettle(Integer sal_varietysettle){
		this.sal_varietysettle=sal_varietysettle;
	}
	public Integer getSal_arrivalsettle(){
		return this.sal_arrivalsettle;
	}
	public void setSal_arrivalsettle(Integer sal_arrivalsettle){
		this.sal_arrivalsettle=sal_arrivalsettle;
	}
	public Integer getSal_daysettle(){
		return this.sal_daysettle;
	}
	public void setSal_daysettle(Integer sal_daysettle){
		this.sal_daysettle=sal_daysettle;
	}
	public Integer getSal_receiveno(){
		return this.sal_receiveno;
	}
	public void setSal_receiveno(Integer sal_receiveno){
		this.sal_receiveno=sal_receiveno;
	}
	public Integer getSal_longdistance(){
		return this.sal_longdistance;
	}
	public void setSal_longdistance(Integer sal_longdistance){
		this.sal_longdistance=sal_longdistance;
	}
	public String getSal_password(){
		return this.sal_password;
	}
	public void setSal_password(String sal_password){
		this.sal_password=sal_password;
	}
	public Integer getSal_stationno(){
		return this.sal_stationno;
	}
	public void setSal_stationno(Integer sal_stationno){
		this.sal_stationno=sal_stationno;
	}
	public Integer getSal_autoallocation(){
		return this.sal_autoallocation;
	}
	public void setSal_autoallocation(Integer sal_autoallocation){
		this.sal_autoallocation=sal_autoallocation;
	}
	public Integer getSal_customer(){
		return this.sal_customer;
	}
	public void setSal_customer(Integer sal_customer){
		this.sal_customer=sal_customer;
	}
	public String getSal_paymentway(){
		return this.sal_paymentway;
	}
	public void setSal_paymentway(String sal_paymentway){
		this.sal_paymentway=sal_paymentway;
	}
	public String getSal_A(){
		return this.sal_A;
	}
	public void setSal_A(String sal_A){
		this.sal_A=sal_A;
	}
	public String getSal_B(){
		return this.sal_B;
	}
	public void setSal_B(String sal_B){
		this.sal_B=sal_B;
	}
	public Integer getSal_receive(){
		return this.sal_receive;
	}
	public void setSal_receive(Integer sal_receive){
		this.sal_receive=sal_receive;
	}
	public String getSal_area(){
		return this.sal_area;
	}
	public void setSal_area(String sal_area){
		this.sal_area=sal_area;
	}
	public String getSal_smallarea(){
		return this.sal_smallarea;
	}
	public void setSal_smallarea(String sal_smallarea){
		this.sal_smallarea=sal_smallarea;
	}
	public String getSal_areasort(){
		return this.sal_areasort;
	}
	public void setSal_areasort(String sal_areasort){
		this.sal_areasort=sal_areasort;
	}
	public Integer getSal_inputsettleprice(){
		return this.sal_inputsettleprice;
	}
	public void setSal_inputsettleprice(Integer sal_inputsettleprice){
		this.sal_inputsettleprice=sal_inputsettleprice;
	}
	public Date getSal_customerordertime(){
		return this.sal_customerordertime;
	}
	public void setSal_customerordertime(Date sal_customerordertime){
		this.sal_customerordertime=sal_customerordertime;
	}
	public String getSal_qltycatescope(){
		return this.sal_qltycatescope;
	}
	public void setSal_qltycatescope(String sal_qltycatescope){
		this.sal_qltycatescope=sal_qltycatescope;
	}
	public Date getSal_proxy(){
		return this.sal_proxy;
	}
	public void setSal_proxy(Date sal_proxy){
		this.sal_proxy=sal_proxy;
	}
	public Integer getSal_belongscustomer(){
		return this.sal_belongscustomer;
	}
	public void setSal_belongscustomer(Integer sal_belongscustomer){
		this.sal_belongscustomer=sal_belongscustomer;
	}
	public String getSal_email(){
		return this.sal_email;
	}
	public void setSal_email(String sal_email){
		this.sal_email=sal_email;
	}
	public String getSal_permitno(){
		return this.sal_permitno;
	}
	public void setSal_permitno(String sal_permitno){
		this.sal_permitno=sal_permitno;
	}
	public String getSal_permitname(){
		return this.sal_permitname;
	}
	public void setSal_permitname(String sal_permitname){
		this.sal_permitname=sal_permitname;
	}
	public String getSal_permitchief(){
		return this.sal_permitchief;
	}
	public void setSal_permitchief(String sal_permitchief){
		this.sal_permitchief=sal_permitchief;
	}
	public String getSal_permitissuingauthority(){
		return this.sal_permitissuingauthority;
	}
	public void setSal_permitissuingauthority(String sal_permitissuingauthority){
		this.sal_permitissuingauthority=sal_permitissuingauthority;
	}
	public Date getSal_permitvailddate(){
		return this.sal_permitvailddate;
	}
	public void setSal_permitvailddate(Date sal_permitvailddate){
		this.sal_permitvailddate=sal_permitvailddate;
	}
	public String getSal_legalperson(){
		return this.sal_legalperson;
	}
	public void setSal_legalperson(String sal_legalperson){
		this.sal_legalperson=sal_legalperson;
	}
	public String getSal_registrationno(){
		return this.sal_registrationno;
	}
	public void setSal_registrationno(String sal_registrationno){
		this.sal_registrationno=sal_registrationno;
	}
	public String getSal_registeredmoney(){
		return this.sal_registeredmoney;
	}
	public void setSal_registeredmoney(String sal_registeredmoney){
		this.sal_registeredmoney=sal_registeredmoney;
	}
	public String getSal_bnslicenseway(){
		return this.sal_bnslicenseway;
	}
	public void setSal_bnslicenseway(String sal_bnslicenseway){
		this.sal_bnslicenseway=sal_bnslicenseway;
	}
	public String getSal_qltycateno(){
		return this.sal_qltycateno;
	}
	public void setSal_qltycateno(String sal_qltycateno){
		this.sal_qltycateno=sal_qltycateno;
	}
	public Date getSal_bnslicensevailddate(){
		return this.sal_bnslicensevailddate;
	}
	public void setSal_bnslicensevailddate(Date sal_bnslicensevailddate){
		this.sal_bnslicensevailddate=sal_bnslicensevailddate;
	}
	public String getSal_operationlock(){
		return this.sal_operationlock;
	}
	public void setSal_operationlock(String sal_operationlock){
		this.sal_operationlock=sal_operationlock;
	}
	public Integer getSal_drugid(){
		return this.sal_drugid;
	}
	public void setSal_drugid(Integer sal_drugid){
		this.sal_drugid=sal_drugid;
	}
	public String getSal_fax(){
		return this.sal_fax;
	}
	public void setSal_fax(String sal_fax){
		this.sal_fax=sal_fax;
	}
	public String getSal_taxperson(){
		return this.sal_taxperson;
	}
	public void setSal_taxperson(String sal_taxperson){
		this.sal_taxperson=sal_taxperson;
	}
	public String getSal_bnslicenseissuingauthority(){
		return this.sal_bnslicenseissuingauthority;
	}
	public void setSal_bnslicenseissuingauthority(String sal_bnslicenseissuingauthority){
		this.sal_bnslicenseissuingauthority=sal_bnslicenseissuingauthority;
	}
	public String getSal_economyproperty(){
		return this.sal_economyproperty;
	}
	public void setSal_economyproperty(String sal_economyproperty){
		this.sal_economyproperty=sal_economyproperty;
	}
	public String getSal_permitscope(){
		return this.sal_permitscope;
	}
	public void setSal_permitscope(String sal_permitscope){
		this.sal_permitscope=sal_permitscope;
	}
	public String getSal_retailstore(){
		return this.sal_retailstore;
	}
	public void setSal_retailstore(String sal_retailstore){
		this.sal_retailstore=sal_retailstore;
	}
	public Integer getSal_check(){
		return this.sal_check;
	}
	public void setSal_check(Integer sal_check){
		this.sal_check=sal_check;
	}
	public String getSal_checkperson(){
		return this.sal_checkperson;
	}
	public void setSal_checkperson(String sal_checkperson){
		this.sal_checkperson=sal_checkperson;
	}
	public Date getSal_checktime(){
		return this.sal_checktime;
	}
	public void setSal_checktime(Date sal_checktime){
		this.sal_checktime=sal_checktime;
	}
	public Date getSal_lastsaledate(){
		return this.sal_lastsaledate;
	}
	public void setSal_lastsaledate(Date sal_lastsaledate){
		this.sal_lastsaledate=sal_lastsaledate;
	}
	public String getSal_category2(){
		return this.sal_category2;
	}
	public void setSal_category2(String sal_category2){
		this.sal_category2=sal_category2;
	}
	public String getSal_category3(){
		return this.sal_category3;
	}
	public void setSal_category3(String sal_category3){
		this.sal_category3=sal_category3;
	}
	public String getSal_msn(){
		return this.sal_msn;
	}
	public void setSal_msn(String sal_msn){
		this.sal_msn=sal_msn;
	}
	public String getSal_qq(){
		return this.sal_qq;
	}
	public void setSal_qq(String sal_qq){
		this.sal_qq=sal_qq;
	}
	public String getSal_accountname(){
		return this.sal_accountname;
	}
	public void setSal_accountname(String sal_accountname){
		this.sal_accountname=sal_accountname;
	}
	public String getSal_remittanceway(){
		return this.sal_remittanceway;
	}
	public void setSal_remittanceway(String sal_remittanceway){
		this.sal_remittanceway=sal_remittanceway;
	}
	public String getSal_paymentdate(){
		return this.sal_paymentdate;
	}
	public void setSal_paymentdate(String sal_paymentdate){
		this.sal_paymentdate=sal_paymentdate;
	}
	public Integer getSal_commontaxpayer(){
		return this.sal_commontaxpayer;
	}
	public void setSal_commontaxpayer(Integer sal_commontaxpayer){
		this.sal_commontaxpayer=sal_commontaxpayer;
	}
	public String getSal_financialnote(){
		return this.sal_financialnote;
	}
	public void setSal_financialnote(String sal_financialnote){
		this.sal_financialnote=sal_financialnote;
	}
	public Integer getSal_permitcueday(){
		return this.sal_permitcueday;
	}
	public void setSal_permitcueday(Integer sal_permitcueday){
		this.sal_permitcueday=sal_permitcueday;
	}
	public Date getSal_bnslicenseannualdeadline(){
		return this.sal_bnslicenseannualdeadline;
	}
	public void setSal_bnslicenseannualdeadline(Date sal_bnslicenseannualdeadline){
		this.sal_bnslicenseannualdeadline=sal_bnslicenseannualdeadline;
	}
	public Integer getSal_bnslicensecueday(){
		return this.sal_bnslicensecueday;
	}
	public void setSal_bnslicensecueday(Integer sal_bnslicensecueday){
		this.sal_bnslicensecueday=sal_bnslicensecueday;
	}
	public String getSal_bnslicenseannualnote(){
		return this.sal_bnslicenseannualnote;
	}
	public void setSal_bnslicenseannualnote(String sal_bnslicenseannualnote){
		this.sal_bnslicenseannualnote=sal_bnslicenseannualnote;
	}
	public String getSal_qltycatename(){
		return this.sal_qltycatename;
	}
	public void setSal_qltycatename(String sal_qltycatename){
		this.sal_qltycatename=sal_qltycatename;
	}
	public Date getSal_qltycatevailddate(){
		return this.sal_qltycatevailddate;
	}
	public void setSal_qltycatevailddate(Date sal_qltycatevailddate){
		this.sal_qltycatevailddate=sal_qltycatevailddate;
	}
	public Integer getSal_qltycatecueday(){
		return this.sal_qltycatecueday;
	}
	public void setSal_qltycatecueday(Integer sal_qltycatecueday){
		this.sal_qltycatecueday=sal_qltycatecueday;
	}
	public String getSal_qltycateissuingauthority(){
		return this.sal_qltycateissuingauthority;
	}
	public void setSal_qltycateissuingauthority(String sal_qltycateissuingauthority){
		this.sal_qltycateissuingauthority=sal_qltycateissuingauthority;
	}
	public String getSal_licensenote(){
		return this.sal_licensenote;
	}
	public void setSal_licensenote(String sal_licensenote){
		this.sal_licensenote=sal_licensenote;
	}
	public String getSal_clerkcard(){
		return this.sal_clerkcard;
	}
	public void setSal_clerkcard(String sal_clerkcard){
		this.sal_clerkcard=sal_clerkcard;
	}
	public String getSal_clerktel(){
		return this.sal_clerktel;
	}
	public void setSal_clerktel(String sal_clerktel){
		this.sal_clerktel=sal_clerktel;
	}
	public Integer getSal_clerkedu(){
		return this.sal_clerkedu;
	}
	public void setSal_clerkedu(Integer sal_clerkedu){
		this.sal_clerkedu=sal_clerkedu;
	}
	public Date getSal_clerkvaliddate(){
		return this.sal_clerkvaliddate;
	}
	public void setSal_clerkvaliddate(Date sal_clerkvaliddate){
		this.sal_clerkvaliddate=sal_clerkvaliddate;
	}
	public Integer getSal_clerkcueday(){
		return this.sal_clerkcueday;
	}
	public void setSal_clerkcueday(Integer sal_clerkcueday){
		this.sal_clerkcueday=sal_clerkcueday;
	}
	public Integer getSal_goodsticket(){
		return this.sal_goodsticket;
	}
	public void setSal_goodsticket(Integer sal_goodsticket){
		this.sal_goodsticket=sal_goodsticket;
	}
	public String getSal_relatedseal(){
		return this.sal_relatedseal;
	}
	public void setSal_relatedseal(String sal_relatedseal){
		this.sal_relatedseal=sal_relatedseal;
	}
	public Integer getSal_sealmold(){
		return this.sal_sealmold;
	}
	public void setSal_sealmold(Integer sal_sealmold){
		this.sal_sealmold=sal_sealmold;
	}
	public String getSal_filelocation(){
		return this.sal_filelocation;
	}
	public void setSal_filelocation(String sal_filelocation){
		this.sal_filelocation=sal_filelocation;
	}
	public String getSal_drugcode(){
		return this.sal_drugcode;
	}
	public void setSal_drugcode(String sal_drugcode){
		this.sal_drugcode=sal_drugcode;
	}
	public String getSal_annualoutput(){
		return this.sal_annualoutput;
	}
	public void setSal_annualoutput(String sal_annualoutput){
		this.sal_annualoutput=sal_annualoutput;
	}
	public String getSal_techinician(){
		return this.sal_techinician;
	}
	public void setSal_techinician(String sal_techinician){
		this.sal_techinician=sal_techinician;
	}
	public String getSal_majorhonor(){
		return this.sal_majorhonor;
	}
	public void setSal_majorhonor(String sal_majorhonor){
		this.sal_majorhonor=sal_majorhonor;
	}
	public String getSal_intendedspecies(){
		return this.sal_intendedspecies;
	}
	public void setSal_intendedspecies(String sal_intendedspecies){
		this.sal_intendedspecies=sal_intendedspecies;
	}
	public String getSal_majorproduct(){
		return this.sal_majorproduct;
	}
	public void setSal_majorproduct(String sal_majorproduct){
		this.sal_majorproduct=sal_majorproduct;
	}
	public String getSal_qualitystatus(){
		return this.sal_qualitystatus;
	}
	public void setSal_qualitystatus(String sal_qualitystatus){
		this.sal_qualitystatus=sal_qualitystatus;
	}
	public String getSal_qualityinstitution(){
		return this.sal_qualityinstitution;
	}
	public void setSal_qualityinstitution(String sal_qualityinstitution){
		this.sal_qualityinstitution=sal_qualityinstitution;
	}
	public String getSal_qualityauthentication(){
		return this.sal_qualityauthentication;
	}
	public void setSal_qualityauthentication(String sal_qualityauthentication){
		this.sal_qualityauthentication=sal_qualityauthentication;
	}
	public String getSal_qualitymanage(){
		return this.sal_qualitymanage;
	}
	public void setSal_qualitymanage(String sal_qualitymanage){
		this.sal_qualitymanage=sal_qualitymanage;
	}
	public String getSal_qa(){
		return this.sal_qa;
	}
	public void setSal_qa(String sal_qa){
		this.sal_qa=sal_qa;
	}
	public String getSal_qasex(){
		return this.sal_qasex;
	}
	public void setSal_qasex(String sal_qasex){
		this.sal_qasex=sal_qasex;
	}
	public String getSal_qaedu(){
		return this.sal_qaedu;
	}
	public void setSal_qaedu(String sal_qaedu){
		this.sal_qaedu=sal_qaedu;
	}
	public String getSal_qatitle(){
		return this.sal_qatitle;
	}
	public void setSal_qatitle(String sal_qatitle){
		this.sal_qatitle=sal_qatitle;
	}
	public String getSal_qaposition(){
		return this.sal_qaposition;
	}
	public void setSal_qaposition(String sal_qaposition){
		this.sal_qaposition=sal_qaposition;
	}
	public String getSal_qaworkyear(){
		return this.sal_qaworkyear;
	}
	public void setSal_qaworkyear(String sal_qaworkyear){
		this.sal_qaworkyear=sal_qaworkyear;
	}
	public String getSal_qualitynote(){
		return this.sal_qualitynote;
	}
	public void setSal_qualitynote(String sal_qualitynote){
		this.sal_qualitynote=sal_qualitynote;
	}
	public Date getSal_fillingdate(){
		return this.sal_fillingdate;
	}
	public void setSal_fillingdate(Date sal_fillingdate){
		this.sal_fillingdate=sal_fillingdate;
	}
	public String getSal_qacprint(){
		return this.sal_qacprint;
	}
	public void setSal_qacprint(String sal_qacprint){
		this.sal_qacprint=sal_qacprint;
	}
	public Integer getSal_other(){
		return this.sal_other;
	}
	public void setSal_other(Integer sal_other){
		this.sal_other=sal_other;
	}
	public Integer getSal_other1(){
		return this.sal_other1;
	}
	public void setSal_other1(Integer sal_other1){
		this.sal_other1=sal_other1;
	}
	public Integer getSal_other2(){
		return this.sal_other2;
	}
	public void setSal_other2(Integer sal_other2){
		this.sal_other2=sal_other2;
	}
	public Integer getSal_other3(){
		return this.sal_other3;
	}
	public void setSal_other3(Integer sal_other3){
		this.sal_other3=sal_other3;
	}
	public String getSal_sealmoldphoto(){
		return this.sal_sealmoldphoto;
	}
	public void setSal_sealmoldphoto(String sal_sealmoldphoto){
		this.sal_sealmoldphoto=sal_sealmoldphoto;
	}
	public String getSal_registeraddr(){
		return this.sal_registeraddr;
	}
	public void setSal_registeraddr(String sal_registeraddr){
		this.sal_registeraddr=sal_registeraddr;
	}
	public String getSal_addperson(){
		return this.sal_addperson;
	}
	public void setSal_addperson(String sal_addperson){
		this.sal_addperson=sal_addperson;
	}
	public String getSal_receiptperson(){
		return this.sal_receiptperson;
	}
	public void setSal_receiptperson(String sal_receiptperson){
		this.sal_receiptperson=sal_receiptperson;
	}
	public String getSal_deliverperson(){
		return this.sal_deliverperson;
	}
	public void setSal_deliverperson(String sal_deliverperson){
		this.sal_deliverperson=sal_deliverperson;
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
	public String getSal_salcueday() {
		return sal_salcueday;
	}
	public void setSal_salcueday(String sal_salcueday) {
		this.sal_salcueday = sal_salcueday;
	}
	public String getSal_salentrustarea() {
		return sal_salentrustarea;
	}
	public void setSal_salentrustarea(String sal_salentrustarea) {
		this.sal_salentrustarea = sal_salentrustarea;
	}
	public String getSal_accountnumber() {
		return sal_accountnumber;
	}
	public void setSal_accountnumber(String sal_accountnumber) {
		this.sal_accountnumber = sal_accountnumber;
	}
	public String getSal_clerkretrustarea() {
		return sal_clerkretrustarea;
	}
	public void setSal_clerkretrustarea(String sal_clerkretrustarea) {
		this.sal_clerkretrustarea = sal_clerkretrustarea;
	}
	public String getSal_idnumber() {
		return sal_idnumber;
	}
	public void setSal_idnumber(String sal_idnumber) {
		this.sal_idnumber = sal_idnumber;
	}

}