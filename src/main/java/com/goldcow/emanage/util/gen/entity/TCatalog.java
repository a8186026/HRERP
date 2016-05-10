package com.goldcow.emanage.util.gen.entity;

import java.util.Date;

import com.goldcow.sframe.util.DataGridModel;
import com.goldcow.sframe.util.mybatis.BaseEntity;
/**
 * 地区药监实体类
 * 
 * @author wangqi
 * @version v1.0
 * @since 2015-7-9
 */

public class TCatalog extends DataGridModel implements BaseEntity{
	
		private static final long serialVersionUID = -5510818182861429294L;
		/* 药监ID（主键）*/
		private Integer ca_id;
		private String ca_code;
		/* 客户名称 */
		private String ca_gysmc;
		/* 许可证号(也是返回值) */
		private String ca_xkzbh;
		private Date ca_xkzxq;
		private Date ca_xkzfzrq;
		private String ca_xkzOrgan;
		/* 法人 */
		private String ca_frdb;
		/* 企业负责人 */
		private String ca_qyfzr;
		private String ca_zlfzr;
		/* 地址  */
		private String ca_zcdz;
		private String ca_ckdz;
		private String ca_postcode;
		private String ca_fw;
		private String ca_gspbh;
		private Date ca_gspxq;
		private Date ca_gspfzrq;
		private String ca_Form;
		private String ca_yyzzbh;
		private String ca_wsxkzbh;
		private String ca_EnterpriseProperty;
		private String ca_RegisteredCapital;
		private Date ca_FoundDate;
		private String ca_LinkMan;
		private String ca_phone;
		private String ca_flag;
		private Integer ca_status;
		private Date ca_datetime;
		private String ca_memory;
		private String ca_ClientId;

		public Integer getCa_id(){
			return this.ca_id;
		}
		public void setCa_id(Integer ca_id){
			this.ca_id=ca_id;
		}
		public String getCa_code(){
			return this.ca_code;
		}
		public void setCa_code(String ca_code){
			this.ca_code=ca_code;
		}
		public String getCa_gysmc(){
			return this.ca_gysmc;
		}
		public void setCa_gysmc(String ca_gysmc){
			this.ca_gysmc=ca_gysmc;
		}
		public String getCa_xkzbh(){
			return this.ca_xkzbh;
		}
		public void setCa_xkzbh(String ca_xkzbh){
			this.ca_xkzbh=ca_xkzbh;
		}
		public Date getCa_xkzxq(){
			return this.ca_xkzxq;
		}
		public void setCa_xkzxq(Date ca_xkzxq){
			this.ca_xkzxq=ca_xkzxq;
		}
		public Date getCa_xkzfzrq(){
			return this.ca_xkzfzrq;
		}
		public void setCa_xkzfzrq(Date ca_xkzfzrq){
			this.ca_xkzfzrq=ca_xkzfzrq;
		}
		public String getCa_xkzOrgan(){
			return this.ca_xkzOrgan;
		}
		public void setCa_xkzOrgan(String ca_xkzOrgan){
			this.ca_xkzOrgan=ca_xkzOrgan;
		}
		public String getCa_frdb(){
			return this.ca_frdb;
		}
		public void setCa_frdb(String ca_frdb){
			this.ca_frdb=ca_frdb;
		}
		public String getCa_qyfzr(){
			return this.ca_qyfzr;
		}
		public void setCa_qyfzr(String ca_qyfzr){
			this.ca_qyfzr=ca_qyfzr;
		}
		public String getCa_zlfzr(){
			return this.ca_zlfzr;
		}
		public void setCa_zlfzr(String ca_zlfzr){
			this.ca_zlfzr=ca_zlfzr;
		}
		public String getCa_zcdz(){
			return this.ca_zcdz;
		}
		public void setCa_zcdz(String ca_zcdz){
			this.ca_zcdz=ca_zcdz;
		}
		public String getCa_ckdz(){
			return this.ca_ckdz;
		}
		public void setCa_ckdz(String ca_ckdz){
			this.ca_ckdz=ca_ckdz;
		}
		public String getCa_postcode(){
			return this.ca_postcode;
		}
		public void setCa_postcode(String ca_postcode){
			this.ca_postcode=ca_postcode;
		}
		public String getCa_fw(){
			return this.ca_fw;
		}
		public void setCa_fw(String ca_fw){
			this.ca_fw=ca_fw;
		}
		public String getCa_gspbh(){
			return this.ca_gspbh;
		}
		public void setCa_gspbh(String ca_gspbh){
			this.ca_gspbh=ca_gspbh;
		}
		public Date getCa_gspxq(){
			return this.ca_gspxq;
		}
		public void setCa_gspxq(Date ca_gspxq){
			this.ca_gspxq=ca_gspxq;
		}
		public Date getCa_gspfzrq(){
			return this.ca_gspfzrq;
		}
		public void setCa_gspfzrq(Date ca_gspfzrq){
			this.ca_gspfzrq=ca_gspfzrq;
		}
		public String getCa_Form(){
			return this.ca_Form;
		}
		public void setCa_Form(String ca_Form){
			this.ca_Form=ca_Form;
		}
		public String getCa_yyzzbh(){
			return this.ca_yyzzbh;
		}
		public void setCa_yyzzbh(String ca_yyzzbh){
			this.ca_yyzzbh=ca_yyzzbh;
		}
		public String getCa_wsxkzbh(){
			return this.ca_wsxkzbh;
		}
		public void setCa_wsxkzbh(String ca_wsxkzbh){
			this.ca_wsxkzbh=ca_wsxkzbh;
		}
		public String getCa_EnterpriseProperty(){
			return this.ca_EnterpriseProperty;
		}
		public void setCa_EnterpriseProperty(String ca_EnterpriseProperty){
			this.ca_EnterpriseProperty=ca_EnterpriseProperty;
		}
		public String getCa_RegisteredCapital(){
			return this.ca_RegisteredCapital;
		}
		public void setCa_RegisteredCapital(String ca_RegisteredCapital){
			this.ca_RegisteredCapital=ca_RegisteredCapital;
		}
		public Date getCa_FoundDate(){
			return this.ca_FoundDate;
		}
		public void setCa_FoundDate(Date ca_FoundDate){
			this.ca_FoundDate=ca_FoundDate;
		}
		public String getCa_LinkMan(){
			return this.ca_LinkMan;
		}
		public void setCa_LinkMan(String ca_LinkMan){
			this.ca_LinkMan=ca_LinkMan;
		}
		public String getCa_phone(){
			return this.ca_phone;
		}
		public void setCa_phone(String ca_phone){
			this.ca_phone=ca_phone;
		}
		public String getCa_flag(){
			return this.ca_flag;
		}
		public void setCa_flag(String ca_flag){
			this.ca_flag=ca_flag;
		}
		public Integer getCa_status(){
			return this.ca_status;
		}
		public void setCa_status(Integer ca_status){
			this.ca_status=ca_status;
		}
		public Date getCa_datetime(){
			return this.ca_datetime;
		}
		public void setCa_datetime(Date ca_datetime){
			this.ca_datetime=ca_datetime;
		}
		public String getCa_memory(){
			return this.ca_memory;
		}
		public void setCa_memory(String ca_memory){
			this.ca_memory=ca_memory;
		}
		public String getCa_ClientId(){
			return this.ca_ClientId;
		}
		public void setCa_ClientId(String ca_ClientId){
			this.ca_ClientId=ca_ClientId;
		}

	}