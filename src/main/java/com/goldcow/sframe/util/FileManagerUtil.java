package com.goldcow.sframe.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.goldcow.emanage.fileUpload.service.IFileUploadManagerService;

public class FileManagerUtil {

	@Autowired
	IFileUploadManagerService fileUploadManagerService;
	
	/**
	 * 获取文件后缀  例如：txt
	 * @param file
	 * @return
	 */
	public static String getFileSuffix(MultipartFile file){
		String oriName = file.getOriginalFilename();//获取文件名称
        //文件名后缀处理---start---
        String suffix = oriName.substring(oriName.lastIndexOf(".")+1, oriName.length());//截取文件后缀
		
		return suffix;
	}
	/**
	 * 获取文件后缀例：.txt
	 * @param file
	 * @return
	 */
	public static String getFileDotSuffix(MultipartFile file){
		String oriName = file.getOriginalFilename();//获取文件名称
		//文件名后缀处理---start---
		String suffix = oriName.substring(oriName.lastIndexOf("."), oriName.length());//.+后缀
		
		return suffix;
	}
	/**
	 * 当前时间+时间戳
	 * @return
	 */
	public static String getTimeStamp(){
		Date date = new Date();
        //文件名时间戳命名
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String filetime = sdf.format(date);
        long filetimeNum = date.getTime();
        return filetime+filetimeNum;
	}
	/**
	 * 
	 * @param saveFile  需要保存的文件
	 * @param toFile   保存到哪里
	 * @throws IOException 
	 * @throws IllegalStateException 
	 * @return 成功返回路径，失败抛出异常
	 */
	public static String saveFileToDir(MultipartFile saveFile,File toFile) throws IllegalStateException, IOException{
		saveFile.transferTo(toFile);
		return toFile.getPath();
	}
	
	/**
	 * 
	 * @param saveFile  需要保存的文件
	 * @param toFile   保存到哪里
	 * @throws IOException 
	 * @throws IllegalStateException 
	 * @return 成功返回路径，失败抛出异常
	 */
	public static String saveFileToDir(MultipartFile saveFile,String toFile) throws IllegalStateException, IOException{
		saveFile.transferTo(new File(toFile));
		return toFile;
	}
	/**
	 * 自动保存文件，文件命名规则。例如yyyyMMdd13423.txt
	 * @param saveFile
	 * @param filePath
	 * @return
	 */
	public static String autoSaveFileToDir(MultipartFile saveFile,String filePath){
		//获取工程下的upload目录，用来保存文件

		        File dirPath = new File(filePath);  
		        //判断目录是否存在，不存在则创建目录
		        if (!dirPath.exists()) {  
		            dirPath.mkdirs();  
		        }
		        String _suffix = FileManagerUtil.getFileSuffix(saveFile);//截取文件后缀
		        //-----end---
		        String suffix = FileManagerUtil.getFileDotSuffix(saveFile);//.+后缀
		        //文件名以年月日yyyyMMdd+时间戳命名
		        String newFileName = FileManagerUtil.getTimeStamp()+suffix;
		        String result=null;
		        try {
		        	//保存文件
		        	result = FileManagerUtil.saveFileToDir(saveFile, filePath + "/" + newFileName);
		        } catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
		        return result;
	}
	
}
