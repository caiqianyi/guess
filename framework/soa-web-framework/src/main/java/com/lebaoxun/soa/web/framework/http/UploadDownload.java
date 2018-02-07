/**
 * 
 */
package com.lebaoxun.soa.web.framework.http;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

/**
 * 上传下载
 * @author xjc
 *
 */
public class UploadDownload {

	public static void dounload(HttpServletRequest request, HttpServletResponse response, String storeName) throws Exception{
		
		request.setCharacterEncoding("UTF-8");  
		BufferedInputStream bis = null;  
		BufferedOutputStream bos = null;  
  
		//获取项目根目录
		String ctxPath = request.getSession().getServletContext().getRealPath("");  
		
		//获取下载文件路径  (storeName 文件名称)
		String downLoadPath = ctxPath+"/download/"+ storeName;  
  
		//获取文件的长度
		long fileLength = new File(downLoadPath).length();  

		//设置文件输出类型
		response.setContentType("application/octet-stream");  
		response.setHeader("Content-disposition", "attachment; filename=" + new String(storeName.getBytes("utf-8"), "ISO8859-1")); 
		//设置输出长度
		response.setHeader("Content-Length", String.valueOf(fileLength));  
		//获取输入流
		bis = new BufferedInputStream(new FileInputStream(downLoadPath));  
		//输出流
		bos = new BufferedOutputStream(response.getOutputStream());  
		byte[] buff = new byte[2048];  
		int bytesRead;  
		while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {  
			bos.write(buff, 0, bytesRead);  
		}  
		//关闭流
		bis.close();  
		bos.close(); 
	}
	
	public static Map<String, String> upload(MultipartFile file, HttpServletRequest request){
		
		Map<String, String> resMap = new HashMap<String, String>();
		
		String s = UUID.randomUUID().toString();
		
		String replace = s.replace('-', '_');
		
		String fileName = file.getOriginalFilename();
		String newName = replace + "." + fileName.substring(fileName.lastIndexOf(".") + 1);
		
		
		String path = request.getSession().getServletContext().getRealPath("upfile") + "/";
		String pa = path+newName;
		resMap.put("path", path+newName);
		
		try {
			
			file.transferTo(new File(path+newName));
			resMap.put("flag", "VIP0000");
			
		} catch (IllegalStateException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return resMap;
	}
	
}
