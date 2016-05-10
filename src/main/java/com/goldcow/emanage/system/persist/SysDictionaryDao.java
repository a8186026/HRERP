package com.goldcow.emanage.system.persist;

import java.util.List;
import java.util.Map;

import com.goldcow.emanage.util.gen.entity.SysDictionary;
import com.goldcow.emanage.util.gen.entity.SysDictionarySub;
import com.goldcow.sframe.util.mybatis.BaseDao;
import com.goldcow.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface SysDictionaryDao extends BaseDao<SysDictionary> {

	public List<SysDictionarySub> getByCode(String code);
	public SysDictionarySub getByCodeAndValue(Map<String, Object> paramMap);
	public List<Map<String, Object>> mapList(SysDictionary bean);
	public SysDictionary getDocumentCodeType(String document_type);
	public SysDictionary getByDictionaryBean(SysDictionary bean);
	public SysDictionarySub getSub(Integer id);
	public void saveSub(SysDictionarySub bean);
	public void updateSub(SysDictionarySub bean);
	public void deleteSub(Integer id);
}