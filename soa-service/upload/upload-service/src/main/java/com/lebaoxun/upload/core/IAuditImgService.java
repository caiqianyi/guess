package com.lebaoxun.upload.core;


/**
 * @author caiqianyi 图片审核接口
 */
public interface IAuditImgService {
	
	/**
	 * 图像审核 URL
	 */
	boolean userDefinedURL(String imgUrl);
	
	/**
	 * 图像审核 image 图片Base64编码字符串
	 */
	boolean userDefinedImage(String image);
}
