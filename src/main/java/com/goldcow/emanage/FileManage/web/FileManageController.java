package com.goldcow.emanage.FileManage.web;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.goldcow.sframe.util.FileManagerUtil;

/**
 * 文件上传服务
 * 
 * @author Yaotaihang
 * @version v1.0
 * @since 2015-2-28
 */
@Controller
@RequestMapping(value = "/FileManage/")
public class FileManageController {

	/**
	 * 文件上传
	 * 
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return 操作结果
	 */
	@RequestMapping(value = "FileUpload", method = RequestMethod.POST)
	@ResponseBody
	public String fileUpload(HttpServletRequest request, HttpServletResponse response) {
		// 获得request中附带的文件
		List<MultipartFile> fileList = new ArrayList<MultipartFile>();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Iterator<String> fileNames = multipartRequest.getFileNames();
		while (fileNames.hasNext()) {
			String fileName = fileNames.next();
			// 上传文件
			MultipartFile file = multipartRequest.getFile(fileName);
			fileList.add(file);
		}
		// 取出目标文件
		MultipartFile imgFile = null;
		if (fileList.size() == 1) {
			imgFile = fileList.get(0);
		} else {
			System.out.println("选择了多个文件");
			return "error";
		}
		String suffix = FileManagerUtil.getFileDotSuffix(imgFile);//.+后缀
		
		// 先获得服务器的绝对路径
		String realPath = request.getSession().getServletContext().getRealPath("");
		// 创建该文件的文件名，格式：文件内容+时间戳
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");// 可以方便地修改日期格式
		// 拼接文件的绝对路径
		String realFilePath = realPath + "\\resources\\pictures\\" + dateFormat.format(now) + suffix ;
		
		// 转存文件
		// 按照希望的位置存储该文件
		try {
			imgFile.transferTo(new File(realFilePath));
		} catch (Exception e) {
			System.out.println("转存文件失败");
			e.printStackTrace();
			return "error";
		}
		return realFilePath;
	}
}