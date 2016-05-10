package com.goldcow.emanage.system.persist;

import java.util.List;
import java.util.Map;

import com.goldcow.emanage.util.gen.entity.SysParameter;
import com.goldcow.emanage.util.gen.entity.SysParameterSub;
import com.goldcow.sframe.util.mybatis.BaseDao;
import com.goldcow.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface SysParameterDao extends BaseDao<SysParameter> {

	public List<SysParameter> getByCode(String code);
	public List<Map<String, Object>> mapList(SysParameter bean);
//	public SysParameter getDocumentCodeType(String document_type);
//	public SysParameter getByCodeAndValue(Map<String, Object> paramMap);
	public SysParameter getByParameterBean(SysParameter bean);
	public SysParameterSub getSub(Integer id);
	public void saveSub(SysParameterSub bean);
	public void updateSub(SysParameterSub bean);
	
	/**
	 * 根据系统参数主表的编码获得所有的子表信息
	 * 
	 * 荣斌 2014-11-13日添加
	 * 
	 * @param code
	 * @return
	 */
	public List<SysParameterSub> getByParamCode(String code);
}