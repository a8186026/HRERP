package com.goldcow.emanage.basInfo.persist;

import java.util.List;

import com.goldcow.emanage.util.gen.entity.BasAccount;
import com.goldcow.emanage.util.gen.entity.BasPictureInfo;
import com.goldcow.sframe.util.mybatis.BaseDao;
import com.goldcow.sframe.util.mybatis.MyBatisRepository;
/**
 * 图片信息
 * 
 * @author gaoxiang
 * @version v1.0
 * @since 2015-6-30
 */

@MyBatisRepository
public interface BasPictureInfoDao extends BaseDao<BasPictureInfo> {
	public List<BasPictureInfo> lists(BasPictureInfo bean);

}