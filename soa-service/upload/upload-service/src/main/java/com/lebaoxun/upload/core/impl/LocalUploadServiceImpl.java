package com.lebaoxun.upload.core.impl;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import sun.misc.BASE64Decoder;

import com.lebaoxun.commons.exception.I18nMessageException;
import com.lebaoxun.upload.core.IUploadService;

@Service("localUploadService")
public class LocalUploadServiceImpl implements IUploadService{
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Value("${application.web.baseDir:/www/upload}")
	private String baseDir;
	
	@Value("${application.web.host:http://localhost:81}")
	private String staticsHost;
	
	@Override
	public byte[] readFileByBytes(String filePath) {
		try{
			String uri = filePath;
			if(filePath.startsWith("http://")){
				uri = filePath.substring(filePath.indexOf("/","http://".length()),filePath.length());
			}
			logger.debug("readFileByBytes|uri={}",uri);
			File file = new File(baseDir+uri);
			if (!file.exists()) {
				throw new FileNotFoundException(filePath);
			} else {
				ByteArrayOutputStream bos = new ByteArrayOutputStream((int) file.length());
				BufferedInputStream in = null;
				try {
					in = new BufferedInputStream(new FileInputStream(file));
					short bufSize = 1024;
					byte[] buffer = new byte[bufSize];
					int len1;
					while (-1 != (len1 = in.read(buffer, 0, bufSize))) {
						bos.write(buffer, 0, len1);
					}
					byte[] var7 = bos.toByteArray();
					return var7;
				} finally {
					try {
						if (in != null) {
							in.close();
						}
					} catch (IOException var14) {
						var14.printStackTrace();
					}
					
					bos.close();
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			throw new I18nMessageException("500");
		}
    }
	
	@Override
	public String uploadImg(String namespace, String imgStr, String fileType){
		String imgFileTempPath = "";
		try {
			String base64String = imgStr.substring(imgStr.indexOf(",")+1);
			// Base64解码
			BASE64Decoder decoder = new BASE64Decoder();
			byte[] b = decoder.decodeBuffer(base64String);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}
			File file = new File(baseDir);
			if(!file.isDirectory()){
				file.mkdirs();
			}
			
			File file2 = new File(baseDir+ "/" + namespace + "/");
			if(!file2.isDirectory()){
				file2.mkdirs();
			}
			// 生成jpeg图片
			imgFileTempPath = "/" + namespace +"/"+ System.currentTimeMillis() + "." + fileType;//新生成的图片
			OutputStream out = new FileOutputStream(baseDir + imgFileTempPath);
			out.write(b);
			out.flush();
			out.close();
			return staticsHost+imgFileTempPath;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return imgFileTempPath;	
	}
	
	@Override
	public boolean deleteFile(String file){
		String uri = file;
		if(file.startsWith("http://")){
			uri = file.substring(file.indexOf("/","http://".length()),file.length());
		}
		File f = new File(baseDir+uri);
		if(f.exists() && f.isFile()){
			return f.delete();
		}
		return false;
	}
}
