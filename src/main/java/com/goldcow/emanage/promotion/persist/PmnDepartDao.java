package com.goldcow.emanage.promotion.persist;

import java.util.List;
import java.util.Map;





import javax.servlet.http.HttpServletRequest;

import com.goldcow.emanage.util.gen.entity.PmnDepartmentInfo;
import com.goldcow.sframe.util.mybatis.BaseDao;
import com.goldcow.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface PmnDepartDao extends BaseDao<PmnDepartmentInfo> {
	int checkDepartmentNumber = 0;

	/** 分页查询 */
	public List<PmnDepartmentInfo> list(PmnDepartmentInfo bean);
	
	
	public int count(PmnDepartmentInfo bean);
	public int countup(PmnDepartmentInfo bean);

	public Integer checkDepartmentNumber(String department_number);
	public List<PmnDepartmentInfo> uplist(PmnDepartmentInfo bean);


}