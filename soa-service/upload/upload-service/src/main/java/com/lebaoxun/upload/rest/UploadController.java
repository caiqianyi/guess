package com.lebaoxun.upload.rest;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lebaoxun.commons.exception.I18nMessageException;
import com.lebaoxun.commons.exception.SuccessMessage;
import com.lebaoxun.upload.core.IAuditImgService;
import com.lebaoxun.upload.core.IUploadService;
import com.lebaoxun.upload.utils.Base64Util;

@RestController
public class UploadController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource(name="localUploadService")
	private IUploadService localUploadService;
	
	@Resource
	private IAuditImgService baiduAuditImgService;
	
	@RequestMapping(value="/upload/{mode}/img",method=RequestMethod.POST)
	SuccessMessage uploadImg(@PathVariable("mode")String mode, 
			@RequestParam("namespace") String namespace, 
			@RequestParam("fileType") String fileType,
			@RequestBody String imgStr){
		String file = null;
		try{
			file = localUploadService.uploadImg(namespace, imgStr, fileType);
			
			byte[] bdtmp = localUploadService.readFileByBytes(file);
			String imgStrForBaidu = Base64Util.encode(bdtmp);
			boolean audit = baiduAuditImgService.userDefinedImage(imgStrForBaidu);
			logger.debug("uploadImg|audit={}",audit);
			if(audit){
				Map<String,String> request= new HashMap<String,String>();
				request.put("uri", file);
				return new SuccessMessage(request);
			}
		}catch(Exception e){
			logger.error("uploadImg|error={}",e.fillInStackTrace());
			if(file != null){
            	localUploadService.deleteFile(file);
            }
			if(e instanceof I18nMessageException){
				throw (I18nMessageException)e;
			}
		}
		throw new I18nMessageException("-1","图片上传失败！");
	}
	
	@RequestMapping(value="/upload/{mode}/file/delete",method=RequestMethod.GET)
	SuccessMessage deleteFile(@PathVariable("mode")String mode,
			@RequestParam("file") String file){
		if(StringUtils.isNotBlank(file)){
			logger.debug("delete|uri={}",file);
			return new SuccessMessage(localUploadService.deleteFile(file));
		}
		throw new I18nMessageException("-1","文件删除失败！");
	}
}
