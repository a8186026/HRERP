/*
 * @(#)IDemoService.java
 * Copyright (c) 2007-2008 goldcow
 * All rights reserved.
 */
package com.goldcow.emanage.fileUpload.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface IFileUploadManagerService {
	public Map<String, Object> insertFile(Map<String, Object> param,MultipartFile mpFile,String filePathName);
	public Map<String, Object> fileDownload(Integer id);
	public boolean deleteFile(Integer id);
	public List<Map<String, Object>> fileQueryByIds(List<Integer> ids);
}
