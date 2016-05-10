package com.goldcow.sframe.util.fileUpdate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

public class CreateFileUtil {

	/**
	 * 将文件保存到指定路径下
	 * @param filePath
	 * @param file
	 * @author lukai
	 * @throws IOException 
	 */
	public static File createFileByFilePaht(String filePath,MultipartFile file) throws IOException {
		
		File filedir = new File(filePath);
		if (!filedir.exists()) {
			filedir.mkdirs();
		}
		if(file!=null){
			String oldFileName = file.getOriginalFilename();
			String fileSuffix = oldFileName.substring(oldFileName.lastIndexOf("."), oldFileName.length());
			UUID uuid = UUID.randomUUID();
			String fileName = uuid.toString()+fileSuffix;//获取新文件名
			filePath = filePath+File.separator+fileName;
		}
		File saveFile = new File(filePath);
		FileOutputStream fos = new FileOutputStream(saveFile);
		fos.write(file.getBytes());
		fos.flush();
		fos.close();
		return saveFile;
	}
	
}
