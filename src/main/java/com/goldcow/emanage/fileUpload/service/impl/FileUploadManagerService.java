/*
 * @(#)IDemoService.java
 * Copyright (c) 2007-2008 goldcow
 * All rights reserved.
 */
package com.goldcow.emanage.fileUpload.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.goldcow.emanage.fileUpload.persist.FileManangerDao;
import com.goldcow.emanage.fileUpload.service.IFileUploadManagerService;
import com.goldcow.sframe.util.FileManagerUtil;

@Service("fileUploadManagerService")
public class FileUploadManagerService implements IFileUploadManagerService {

	@Autowired
	FileManangerDao fileManangerDao;

	@Override
	public Map<String, Object> insertFile(Map<String, Object> param,MultipartFile mpFile,String filePathName) {
    	Map<String, Object> resultMap = new HashMap<String, Object>();
		//将文件信息保存到表中
		int id = fileManangerDao.insertFile(param);
		resultMap.put("id", param.get("id"));
		try {
			//保存文件到目录
			String result = FileManagerUtil.saveFileToDir(mpFile, filePathName);
			resultMap.put("filePathName", filePathName);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return resultMap;
	}

	@Override
	public Map<String, Object> fileDownload(Integer id) {
		return fileManangerDao.getMapById(id);
		
	}

	@Override
	public boolean deleteFile(Integer id) {
		boolean resultb = false;
		Map<String, Object> fileMap = fileManangerDao.getMapById(id);
		fileManangerDao.delete(id);
		String filePath = (String) fileMap.get("currentfilepathName");
		File file = new File(filePath);
		resultb = file.delete();
		return resultb;
	}

	@Override
	public List<Map<String, Object>> fileQueryByIds(List<Integer> ids) {
		if(ids==null&&"".equals(ids)){
			return new ArrayList<Map<String,Object>>();
		}
		return fileManangerDao.fileQueryByIds(ids);
	}
	
}
