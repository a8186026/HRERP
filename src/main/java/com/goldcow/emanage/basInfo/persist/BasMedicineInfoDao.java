package com.goldcow.emanage.basInfo.persist;



import com.goldcow.emanage.util.gen.entity.BasMedicineInfo;
import com.goldcow.sframe.util.mybatis.BaseDao;
import com.goldcow.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface BasMedicineInfoDao extends BaseDao<BasMedicineInfo> {
	public Integer getMaxMedicineCode(String ticketNumber);

}