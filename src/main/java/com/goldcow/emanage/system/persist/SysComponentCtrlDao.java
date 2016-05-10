package com.goldcow.emanage.system.persist;

import java.util.List;



import com.goldcow.emanage.util.gen.entity.SysComponentCtrl;
import com.goldcow.emanage.util.gen.entity.SysPageCtrl;
import com.goldcow.sframe.util.mybatis.BaseDao;
import com.goldcow.sframe.util.mybatis.MyBatisRepository;

@MyBatisRepository
public interface SysComponentCtrlDao extends BaseDao<SysComponentCtrl> {
	/**通过pageid 得到其中控件*/
	public List<SysComponentCtrl> lists(Integer page_id);

}