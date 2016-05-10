package com.goldcow.emanage.modi.web;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goldcow.emanage.fileUpload.service.IFileUploadManagerService;
import com.goldcow.emanage.modi.service.IModiService;

/*
 * @version 2.0
 * @author
 */

@Controller
@RequestMapping(value = "/modi/interfaces")
public class ModiController {
	private static Logger log = LoggerFactory.getLogger(ModiController.class);

	@Autowired
	private IModiService modiService;
	@Autowired
	IFileUploadManagerService fileUploadManagerService;

	/**
	 * 直接预览PDF文件，从EKP平台跳转
	 * 
	 * @param id 文件id
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 */
	@RequestMapping(value = "viewPDF")
	@ResponseBody
	public void viewPDF(Integer id, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> resultMap = fileUploadManagerService.fileDownload(id);
		String pathName = (String) resultMap.get("currentfilepathName");
		File data = new File(pathName);
		String fileName = (String) resultMap.get("oldFileName");
		try {
			fileName = URLEncoder.encode(fileName, "UTF-8");
			response.reset();
			response.setHeader("Content-Disposition", "inline; filename=\"" + fileName + "\"");
			response.addHeader("Content-Length", "" + data.length());
			OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
			InputStream inputStream = new FileInputStream(data);
			byte[] buffer = new byte[1024 * 128];
			int i = -1;
			while ((i = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, i);
			}

			inputStream.close();
			outputStream.flush();
			outputStream.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}