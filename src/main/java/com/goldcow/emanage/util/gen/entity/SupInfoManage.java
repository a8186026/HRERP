package com.goldcow.emanage.util.gen.entity;

import java.util.Date;

import com.goldcow.sframe.util.DataGridModel;
import com.goldcow.sframe.util.mybatis.BaseEntity;

public class SupInfoManage extends DataGridModel implements BaseEntity {
	private static final long serialVersionUID = 1L;
	/** 供方id */
	private Integer sup_id;	
	/**  类别1*/
	private String sup_category1;
	/** 类别2 */
	private String sup_category2;
	/** 类别3 */
	private String sup_category3;
	/** 供方区号 */
	private String sup_areano;
	/** 供方编码 */
	private String sup_code;
	/** 供方简称 */
	private String sup_shortname;
	/** 供方全称 */
	private String sup_name;
	/** 供方性质 */
	private String sup_property;
	/** 供方拼音码 */
	private String sup_chnpy;
	/** 供方地址 */
	private String sup_address;
	/** 供方邮编 */
	private String sup_zipcode;	
	/** 手续 */
	private String sup_procedure;
	/** 联系人 */
	private String sup_ctactperson;
	/** 电话 */
	private String sup_tel;
	/** 手机 */
	private String sup_mobile;
	/** 传真 */
	private String sup_fax;
	/** 合同号 */
	private Integer sup_ctractno;
	/** 邮箱 */
	private String sup_email;
	/** msn */
	private String sup_msncode;
	/** qq */
	private String sup_qqcode;
	/** 采购员 */
	private String sup_buyname;
	/** 简介 */
	private String sup_brief;
	/** 建档日期 */
	private Date sup_fillingdate;
	/** 开户户名 */
	private String sup_accname;
	/** 开户帐号 */
	private String sup_accno;
	/** 开户银行名称 */
	private String sup_bankname;
	/** 税号 */
	private String sup_tax;
	/** 注册地址 */
	private String sup_registaddr;
	/** 汇款方式 */
	private String sup_rmtway;
	/** 结款方式 */
	private String sup_payway;
	/** 结款日期 */
	private Date sup_paydate;
	/** 保证金 */
	private Double sup_bond;
	/** 财务备注 */
	private String sup_fnlnote;
	/** 许可证号 */
	private String sup_pmtno;
	/** 许可证名称 */
	private String sup_pmtname;
	/** 许可证负责人 */
	private String sup_pmtchief;
	/** 许可证提示天数 */
	private Integer sup_pmtcueday;
	/** 许可范围 */
	private String sup_pmtscope;
	/** 许可证发证机关 */
	private String sup_pmtissauthority;
	/** 许可证有效期 */
	private Date sup_pmtvaliddate;
	/** 法人 */
	private String sup_bnlslegalperson;
	/** 注册号 */
	private String sup_bnlsregistno;
	/** 注册资金 */
	private Double sup_bnlsregistmoney;
	/** 经济性质 */
	private String sup_bnlsproperty;
	/** 经营范围 */
	private String sup_bnlsscope;
	/** 经营方式 */
	private String sup_bnlsway;
	/** 营业执照发证机关 */
	private String sup_bnlsissauthority;
	/** 执照有效期 */
	private Date sup_bnlsvaliddate;
	/** 营业执照年检截止日期 */
	private Date sup_bnlsanndeaddate;
	/** 营业执照年检备注 */
	private String sup_bnlsanndeadnote;
	/** 营业执照提示天数 */
	private Integer sup_bnlscueday;
	/** 质量认证书名 */
	private String sup_qltycatename;
	/** 质量认证书编号 */
	private String sup_qltycateno;
	/** 认证范围 */
	private String sup_qltycatescope;
	/** 质量认证书有效期 */
	private Date sup_qltycatevaliddate;
	/** 质量认证书提示天数 */
	private Integer sup_qltycatecueday;
	/** 质量认证书发证机关 */
	private String sup_qltycateissauthority;
	/** 证照备注 */
	private String sup_lsnote;
	/** 业务员名称 */
	private String sup_clkname;
	/** 业务员身份证号 */
	private String sup_clkidcard;
	/** 业务员电话 */
	private String sup_clktel;
	/** 委托书有效期*/
	private Date sup_letvaliddate;
	/** 委托书提示天数*/
	private Integer sup_letcueday;
	/** 打印格式*/
	private String sup_printway;
	/** 业务员上岗证有效期 */
	private Date sup_clkvaliddate;
	/** 业务员上岗证提示天数 */
	private Integer sup_clkcueday;
	/** 相关印章 */
	private String sup_relatedseal;	
	/** 档案号 */
	private String sup_fileno;
	/** 档案位置 */
	private String sup_filelocation;
	/** 审核 */
	private Integer sup_check;
	/** 审核人 */
	private String sup_checkname;
	/** 审核时间 */
	private Date sup_checkdate;
	/** 地区药监*/
	private String sup_localdrug;	
	/** 药监id */
	private Integer sup_drugid;
	/** 国家药监码 */
	private String sup_drugcode;
	/** 年产值 */
	private String sup_annoutput;
	/** 主要荣誉 */
	private String sup_majorhonor;
	/** 技术人员 */
	private String sup_technician;
	/** 拟供品种 */
	private String sup_intendedspecie;
	/** 主要产品 */
	private String sup_majorproduct;
	/** 质量状况 */
	private String sup_qltystatus;
	/** 质量机构 */
	private String sup_qltyinstitution;
	/** 认证情况 */
	private String sup_qltyauthen;
	/** 质量管理 */
	private String sup_qltymanage;
	/** 质量负责人 */
	private String sup_qltychief;
	/** 质量负责人性别 */
	private Integer sup_qltychiefsex;
	/** 质量负责人文化程度 */
	private String sup_qltychiefedu;
	/** 质量负责人职务 */
	private String sup_qltychiefposition;
	/** 质量负责人职称 */
	private String sup_qltychieftitle;
	/** 质量负责人工作年限 */
	private String sup_qltychiefworkyear;
	/** 质量备注 */
	private String sup_qltynote;
	/** 随货票样图片 */
	private String sup_goodticketphoto;
	/** 印章印模图片 */
	private String sup_sealmoldphoto;

	/** 应付天数 */
	private Integer sup_paydays;
	/** 是否为一般纳税人 */
	private Integer sup_istaxpayer;
	/** 应付（不显示） */
	private Double sup_shouldpay;
	/** 接收（不显示） */
	private String sup_receive;
	/** 最后进货日期（不显示） */
	private Date sup_lastpurchasedate;
	/** 源编码（不显示） */ 
	private String sup_sourcecode;
	/** 预付款（不显示） */
	private Double sup_advancepay;	
	
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
	private Integer sup_other1;
	private Integer sup_other2;
	private Integer sup_other3;
	private Integer sup_other4;
	/* 查找地区药监ＩＤ */
	private String sup_finddrugid;
	public Integer getSup_id() {
		return sup_id;
	}
	public void setSup_id(Integer sup_id) {
		this.sup_id = sup_id;
	}
	public String getSup_category1() {
		return sup_category1;
	}
	public void setSup_category1(String sup_category1) {
		this.sup_category1 = sup_category1;
	}
	public String getSup_category2() {
		return sup_category2;
	}
	public void setSup_category2(String sup_category2) {
		this.sup_category2 = sup_category2;
	}
	public String getSup_category3() {
		return sup_category3;
	}
	public void setSup_category3(String sup_category3) {
		this.sup_category3 = sup_category3;
	}
	public String getSup_areano() {
		return sup_areano;
	}
	public void setSup_areano(String sup_areano) {
		this.sup_areano = sup_areano;
	}
	public String getSup_code() {
		return sup_code;
	}
	public void setSup_code(String sup_code) {
		this.sup_code = sup_code;
	}
	public String getSup_shortname() {
		return sup_shortname;
	}
	public void setSup_shortname(String sup_shortname) {
		this.sup_shortname = sup_shortname;
	}
	public String getSup_name() {
		return sup_name;
	}
	public void setSup_name(String sup_name) {
		this.sup_name = sup_name;
	}
	public String getSup_property() {
		return sup_property;
	}
	public void setSup_property(String sup_property) {
		this.sup_property = sup_property;
	}
	public String getSup_chnpy() {
		return sup_chnpy;
	}
	public void setSup_chnpy(String sup_chnpy) {
		this.sup_chnpy = sup_chnpy;
	}
	public String getSup_address() {
		return sup_address;
	}
	public void setSup_address(String sup_address) {
		this.sup_address = sup_address;
	}
	public String getSup_zipcode() {
		return sup_zipcode;
	}
	public void setSup_zipcode(String sup_zipcode) {
		this.sup_zipcode = sup_zipcode;
	}
	public String getSup_procedure() {
		return sup_procedure;
	}
	public void setSup_procedure(String sup_procedure) {
		this.sup_procedure = sup_procedure;
	}
	public String getSup_ctactperson() {
		return sup_ctactperson;
	}
	public void setSup_ctactperson(String sup_ctactperson) {
		this.sup_ctactperson = sup_ctactperson;
	}
	public String getSup_tel() {
		return sup_tel;
	}
	public void setSup_tel(String sup_tel) {
		this.sup_tel = sup_tel;
	}
	public String getSup_mobile() {
		return sup_mobile;
	}
	public void setSup_mobile(String sup_mobile) {
		this.sup_mobile = sup_mobile;
	}
	public String getSup_fax() {
		return sup_fax;
	}
	public void setSup_fax(String sup_fax) {
		this.sup_fax = sup_fax;
	}
	public Integer getSup_ctractno() {
		return sup_ctractno;
	}
	public void setSup_ctractno(Integer sup_ctractno) {
		this.sup_ctractno = sup_ctractno;
	}
	public String getSup_email() {
		return sup_email;
	}
	public void setSup_email(String sup_email) {
		this.sup_email = sup_email;
	}
	public String getSup_msncode() {
		return sup_msncode;
	}
	public void setSup_msncode(String sup_msncode) {
		this.sup_msncode = sup_msncode;
	}
	public String getSup_qqcode() {
		return sup_qqcode;
	}
	public void setSup_qqcode(String sup_qqcode) {
		this.sup_qqcode = sup_qqcode;
	}
	public String getSup_buyname() {
		return sup_buyname;
	}
	public void setSup_buyname(String sup_buyname) {
		this.sup_buyname = sup_buyname;
	}
	public String getSup_brief() {
		return sup_brief;
	}
	public void setSup_brief(String sup_brief) {
		this.sup_brief = sup_brief;
	}
	public Date getSup_fillingdate() {
		return sup_fillingdate;
	}
	public void setSup_fillingdate(Date sup_fillingdate) {
		this.sup_fillingdate = sup_fillingdate;
	}
	public String getSup_accname() {
		return sup_accname;
	}
	public void setSup_accname(String sup_accname) {
		this.sup_accname = sup_accname;
	}
	public String getSup_accno() {
		return sup_accno;
	}
	public void setSup_accno(String sup_accno) {
		this.sup_accno = sup_accno;
	}
	public String getSup_bankname() {
		return sup_bankname;
	}
	public void setSup_bankname(String sup_bankname) {
		this.sup_bankname = sup_bankname;
	}
	public String getSup_tax() {
		return sup_tax;
	}
	public void setSup_tax(String sup_tax) {
		this.sup_tax = sup_tax;
	}
	public String getSup_registaddr() {
		return sup_registaddr;
	}
	public void setSup_registaddr(String sup_registaddr) {
		this.sup_registaddr = sup_registaddr;
	}
	public String getSup_rmtway() {
		return sup_rmtway;
	}
	public void setSup_rmtway(String sup_rmtway) {
		this.sup_rmtway = sup_rmtway;
	}
	public String getSup_payway() {
		return sup_payway;
	}
	public void setSup_payway(String sup_payway) {
		this.sup_payway = sup_payway;
	}
	public Date getSup_paydate() {
		return sup_paydate;
	}
	public void setSup_paydate(Date sup_paydate) {
		this.sup_paydate = sup_paydate;
	}
	public Double getSup_bond() {
		return sup_bond;
	}
	public void setSup_bond(Double sup_bond) {
		this.sup_bond = sup_bond;
	}
	public String getSup_fnlnote() {
		return sup_fnlnote;
	}
	public void setSup_fnlnote(String sup_fnlnote) {
		this.sup_fnlnote = sup_fnlnote;
	}
	public String getSup_pmtno() {
		return sup_pmtno;
	}
	public void setSup_pmtno(String sup_pmtno) {
		this.sup_pmtno = sup_pmtno;
	}
	public String getSup_pmtname() {
		return sup_pmtname;
	}
	public void setSup_pmtname(String sup_pmtname) {
		this.sup_pmtname = sup_pmtname;
	}
	public String getSup_pmtchief() {
		return sup_pmtchief;
	}
	public void setSup_pmtchief(String sup_pmtchief) {
		this.sup_pmtchief = sup_pmtchief;
	}
	public Integer getSup_pmtcueday() {
		return sup_pmtcueday;
	}
	public void setSup_pmtcueday(Integer sup_pmtcueday) {
		this.sup_pmtcueday = sup_pmtcueday;
	}
	public String getSup_pmtscope() {
		return sup_pmtscope;
	}
	public void setSup_pmtscope(String sup_pmtscope) {
		this.sup_pmtscope = sup_pmtscope;
	}
	public String getSup_pmtissauthority() {
		return sup_pmtissauthority;
	}
	public void setSup_pmtissauthority(String sup_pmtissauthority) {
		this.sup_pmtissauthority = sup_pmtissauthority;
	}
	public Date getSup_pmtvaliddate() {
		return sup_pmtvaliddate;
	}
	public void setSup_pmtvaliddate(Date sup_pmtvaliddate) {
		this.sup_pmtvaliddate = sup_pmtvaliddate;
	}
	public String getSup_bnlslegalperson() {
		return sup_bnlslegalperson;
	}
	public void setSup_bnlslegalperson(String sup_bnlslegalperson) {
		this.sup_bnlslegalperson = sup_bnlslegalperson;
	}
	public String getSup_bnlsregistno() {
		return sup_bnlsregistno;
	}
	public void setSup_bnlsregistno(String sup_bnlsregistno) {
		this.sup_bnlsregistno = sup_bnlsregistno;
	}
	public Double getSup_bnlsregistmoney() {
		return sup_bnlsregistmoney;
	}
	public void setSup_bnlsregistmoney(Double sup_bnlsregistmoney) {
		this.sup_bnlsregistmoney = sup_bnlsregistmoney;
	}
	public String getSup_bnlsproperty() {
		return sup_bnlsproperty;
	}
	public void setSup_bnlsproperty(String sup_bnlsproperty) {
		this.sup_bnlsproperty = sup_bnlsproperty;
	}
	public String getSup_bnlsscope() {
		return sup_bnlsscope;
	}
	public void setSup_bnlsscope(String sup_bnlsscope) {
		this.sup_bnlsscope = sup_bnlsscope;
	}
	public String getSup_bnlsway() {
		return sup_bnlsway;
	}
	public void setSup_bnlsway(String sup_bnlsway) {
		this.sup_bnlsway = sup_bnlsway;
	}
	public String getSup_bnlsissauthority() {
		return sup_bnlsissauthority;
	}
	public void setSup_bnlsissauthority(String sup_bnlsissauthority) {
		this.sup_bnlsissauthority = sup_bnlsissauthority;
	}
	public Date getSup_bnlsvaliddate() {
		return sup_bnlsvaliddate;
	}
	public void setSup_bnlsvaliddate(Date sup_bnlsvaliddate) {
		this.sup_bnlsvaliddate = sup_bnlsvaliddate;
	}
	public Date getSup_bnlsanndeaddate() {
		return sup_bnlsanndeaddate;
	}
	public void setSup_bnlsanndeaddate(Date sup_bnlsanndeaddate) {
		this.sup_bnlsanndeaddate = sup_bnlsanndeaddate;
	}
	public String getSup_bnlsanndeadnote() {
		return sup_bnlsanndeadnote;
	}
	public void setSup_bnlsanndeadnote(String sup_bnlsanndeadnote) {
		this.sup_bnlsanndeadnote = sup_bnlsanndeadnote;
	}
	public Integer getSup_bnlscueday() {
		return sup_bnlscueday;
	}
	public void setSup_bnlscueday(Integer sup_bnlscueday) {
		this.sup_bnlscueday = sup_bnlscueday;
	}
	public String getSup_qltycatename() {
		return sup_qltycatename;
	}
	public void setSup_qltycatename(String sup_qltycatename) {
		this.sup_qltycatename = sup_qltycatename;
	}
	public String getSup_qltycateno() {
		return sup_qltycateno;
	}
	public void setSup_qltycateno(String sup_qltycateno) {
		this.sup_qltycateno = sup_qltycateno;
	}
	public String getSup_qltycatescope() {
		return sup_qltycatescope;
	}
	public void setSup_qltycatescope(String sup_qltycatescope) {
		this.sup_qltycatescope = sup_qltycatescope;
	}
	public Date getSup_qltycatevaliddate() {
		return sup_qltycatevaliddate;
	}
	public void setSup_qltycatevaliddate(Date sup_qltycatevaliddate) {
		this.sup_qltycatevaliddate = sup_qltycatevaliddate;
	}
	public Integer getSup_qltycatecueday() {
		return sup_qltycatecueday;
	}
	public void setSup_qltycatecueday(Integer sup_qltycatecueday) {
		this.sup_qltycatecueday = sup_qltycatecueday;
	}
	public String getSup_qltycateissauthority() {
		return sup_qltycateissauthority;
	}
	public void setSup_qltycateissauthority(String sup_qltycateissauthority) {
		this.sup_qltycateissauthority = sup_qltycateissauthority;
	}
	public String getSup_lsnote() {
		return sup_lsnote;
	}
	public void setSup_lsnote(String sup_lsnote) {
		this.sup_lsnote = sup_lsnote;
	}
	public String getSup_clkname() {
		return sup_clkname;
	}
	public void setSup_clkname(String sup_clkname) {
		this.sup_clkname = sup_clkname;
	}
	public String getSup_clkidcard() {
		return sup_clkidcard;
	}
	public void setSup_clkidcard(String sup_clkidcard) {
		this.sup_clkidcard = sup_clkidcard;
	}
	public String getSup_clktel() {
		return sup_clktel;
	}
	public void setSup_clktel(String sup_clktel) {
		this.sup_clktel = sup_clktel;
	}
	public Date getSup_letvaliddate() {
		return sup_letvaliddate;
	}
	public void setSup_letvaliddate(Date sup_letvaliddate) {
		this.sup_letvaliddate = sup_letvaliddate;
	}
	public Integer getSup_letcueday() {
		return sup_letcueday;
	}
	public void setSup_letcueday(Integer sup_letcueday) {
		this.sup_letcueday = sup_letcueday;
	}
	public String getSup_printway() {
		return sup_printway;
	}
	public void setSup_printway(String sup_printway) {
		this.sup_printway = sup_printway;
	}
	public Date getSup_clkvaliddate() {
		return sup_clkvaliddate;
	}
	public void setSup_clkvaliddate(Date sup_clkvaliddate) {
		this.sup_clkvaliddate = sup_clkvaliddate;
	}
	public Integer getSup_clkcueday() {
		return sup_clkcueday;
	}
	public void setSup_clkcueday(Integer sup_clkcueday) {
		this.sup_clkcueday = sup_clkcueday;
	}
	public String getSup_relatedseal() {
		return sup_relatedseal;
	}
	public void setSup_relatedseal(String sup_relatedseal) {
		this.sup_relatedseal = sup_relatedseal;
	}
	public String getSup_fileno() {
		return sup_fileno;
	}
	public void setSup_fileno(String sup_fileno) {
		this.sup_fileno = sup_fileno;
	}
	public String getSup_filelocation() {
		return sup_filelocation;
	}
	public void setSup_filelocation(String sup_filelocation) {
		this.sup_filelocation = sup_filelocation;
	}
	public Integer getSup_check() {
		return sup_check;
	}
	public void setSup_check(Integer sup_check) {
		this.sup_check = sup_check;
	}
	public String getSup_checkname() {
		return sup_checkname;
	}
	public void setSup_checkname(String sup_checkname) {
		this.sup_checkname = sup_checkname;
	}
	public Date getSup_checkdate() {
		return sup_checkdate;
	}
	public void setSup_checkdate(Date sup_checkdate) {
		this.sup_checkdate = sup_checkdate;
	}
	public String getSup_localdrug() {
		return sup_localdrug;
	}
	public void setSup_localdrug(String sup_localdrug) {
		this.sup_localdrug = sup_localdrug;
	}
	public Integer getSup_drugid() {
		return sup_drugid;
	}
	public void setSup_drugid(Integer sup_drugid) {
		this.sup_drugid = sup_drugid;
	}
	public String getSup_drugcode() {
		return sup_drugcode;
	}
	public void setSup_drugcode(String sup_drugcode) {
		this.sup_drugcode = sup_drugcode;
	}
	public String getSup_annoutput() {
		return sup_annoutput;
	}
	public void setSup_annoutput(String sup_annoutput) {
		this.sup_annoutput = sup_annoutput;
	}
	public String getSup_majorhonor() {
		return sup_majorhonor;
	}
	public void setSup_majorhonor(String sup_majorhonor) {
		this.sup_majorhonor = sup_majorhonor;
	}
	public String getSup_technician() {
		return sup_technician;
	}
	public void setSup_technician(String sup_technician) {
		this.sup_technician = sup_technician;
	}
	public String getSup_intendedspecie() {
		return sup_intendedspecie;
	}
	public void setSup_intendedspecie(String sup_intendedspecie) {
		this.sup_intendedspecie = sup_intendedspecie;
	}
	public String getSup_majorproduct() {
		return sup_majorproduct;
	}
	public void setSup_majorproduct(String sup_majorproduct) {
		this.sup_majorproduct = sup_majorproduct;
	}
	public String getSup_qltystatus() {
		return sup_qltystatus;
	}
	public void setSup_qltystatus(String sup_qltystatus) {
		this.sup_qltystatus = sup_qltystatus;
	}
	public String getSup_qltyinstitution() {
		return sup_qltyinstitution;
	}
	public void setSup_qltyinstitution(String sup_qltyinstitution) {
		this.sup_qltyinstitution = sup_qltyinstitution;
	}
	public String getSup_qltyauthen() {
		return sup_qltyauthen;
	}
	public void setSup_qltyauthen(String sup_qltyauthen) {
		this.sup_qltyauthen = sup_qltyauthen;
	}
	public String getSup_qltymanage() {
		return sup_qltymanage;
	}
	public void setSup_qltymanage(String sup_qltymanage) {
		this.sup_qltymanage = sup_qltymanage;
	}
	public String getSup_qltychief() {
		return sup_qltychief;
	}
	public void setSup_qltychief(String sup_qltychief) {
		this.sup_qltychief = sup_qltychief;
	}
	public Integer getSup_qltychiefsex() {
		return sup_qltychiefsex;
	}
	public void setSup_qltychiefsex(Integer sup_qltychiefsex) {
		this.sup_qltychiefsex = sup_qltychiefsex;
	}
	public String getSup_qltychiefedu() {
		return sup_qltychiefedu;
	}
	public void setSup_qltychiefedu(String sup_qltychiefedu) {
		this.sup_qltychiefedu = sup_qltychiefedu;
	}
	public String getSup_qltychiefposition() {
		return sup_qltychiefposition;
	}
	public void setSup_qltychiefposition(String sup_qltychiefposition) {
		this.sup_qltychiefposition = sup_qltychiefposition;
	}
	public String getSup_qltychieftitle() {
		return sup_qltychieftitle;
	}
	public void setSup_qltychieftitle(String sup_qltychieftitle) {
		this.sup_qltychieftitle = sup_qltychieftitle;
	}
	public String getSup_qltychiefworkyear() {
		return sup_qltychiefworkyear;
	}
	public void setSup_qltychiefworkyear(String sup_qltychiefworkyear) {
		this.sup_qltychiefworkyear = sup_qltychiefworkyear;
	}
	public String getSup_qltynote() {
		return sup_qltynote;
	}
	public void setSup_qltynote(String sup_qltynote) {
		this.sup_qltynote = sup_qltynote;
	}
	public String getSup_goodticketphoto() {
		return sup_goodticketphoto;
	}
	public void setSup_goodticketphoto(String sup_goodticketphoto) {
		this.sup_goodticketphoto = sup_goodticketphoto;
	}
	public String getSup_sealmoldphoto() {
		return sup_sealmoldphoto;
	}
	public void setSup_sealmoldphoto(String sup_sealmoldphoto) {
		this.sup_sealmoldphoto = sup_sealmoldphoto;
	}
	public Integer getSup_paydays() {
		return sup_paydays;
	}
	public void setSup_paydays(Integer sup_paydays) {
		this.sup_paydays = sup_paydays;
	}
	public Integer getSup_istaxpayer() {
		return sup_istaxpayer;
	}
	public void setSup_istaxpayer(Integer sup_istaxpayer) {
		this.sup_istaxpayer = sup_istaxpayer;
	}
	public Double getSup_shouldpay() {
		return sup_shouldpay;
	}
	public void setSup_shouldpay(Double sup_shouldpay) {
		this.sup_shouldpay = sup_shouldpay;
	}
	public String getSup_receive() {
		return sup_receive;
	}
	public void setSup_receive(String sup_receive) {
		this.sup_receive = sup_receive;
	}
	public Date getSup_lastpurchasedate() {
		return sup_lastpurchasedate;
	}
	public void setSup_lastpurchasedate(Date sup_lastpurchasedate) {
		this.sup_lastpurchasedate = sup_lastpurchasedate;
	}
	public String getSup_sourcecode() {
		return sup_sourcecode;
	}
	public void setSup_sourcecode(String sup_sourcecode) {
		this.sup_sourcecode = sup_sourcecode;
	}
	public Double getSup_advancepay() {
		return sup_advancepay;
	}
	public void setSup_advancepay(Double sup_advancepay) {
		this.sup_advancepay = sup_advancepay;
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
	public Integer getSup_other1() {
		return sup_other1;
	}
	public void setSup_other1(Integer sup_other1) {
		this.sup_other1 = sup_other1;
	}
	public Integer getSup_other2() {
		return sup_other2;
	}
	public void setSup_other2(Integer sup_other2) {
		this.sup_other2 = sup_other2;
	}
	public Integer getSup_other3() {
		return sup_other3;
	}
	public void setSup_other3(Integer sup_other3) {
		this.sup_other3 = sup_other3;
	}
	public Integer getSup_other4() {
		return sup_other4;
	}
	public void setSup_other4(Integer sup_other4) {
		this.sup_other4 = sup_other4;
	}
	public String getSup_finddrugid() {
		return sup_finddrugid;
	}
	public void setSup_finddrugid(String sup_finddrugid) {
		this.sup_finddrugid = sup_finddrugid;
	}
	
	

}
