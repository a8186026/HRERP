package com.goldcow.emanage.basInfo.service;

import java.util.List;
import java.util.Map;

import com.goldcow.emanage.util.gen.entity.BasPictureInfo;
import com.goldcow.sframe.util.service.BaseIService;
/**
 * 图片信息
 * 
 * @author gaoxiang
 * @version v1.0
 * @since 2015-6-30
 */
public interface IBasPictureInfoService extends BaseIService<BasPictureInfo>{

	 /**
	 * 查询图片信息-分页
	 * @param bean 查询条件
	 * @return 图片信息列表
	 */
	public Map<String, Object> lists(BasPictureInfo bean);

}