package com.lebaoxun.upload.core;

public interface IUploadService {
	
	byte[] readFileByBytes(String filePath);
	
	String uploadImg(String namespace, String imgStr, String fileType);
	
	boolean deleteFile(String file);
	
}
