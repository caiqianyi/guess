package com.lebaoxun.upload.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lebaoxun.commons.exception.SuccessMessage;
import com.lebaoxun.upload.service.hystrix.UploadServiceServiceHystrix;

@FeignClient(value="upload-service",fallback=UploadServiceServiceHystrix.class)
public interface IUploadService {
	
	@RequestMapping(value="/upload/{mode}/img",method=RequestMethod.POST)
	SuccessMessage uploadImg(@PathVariable("mode")String mode, 
			@RequestParam("namespace") String namespace, 
			@RequestParam("fileType") String fileType,
			@RequestBody String imgStr);
	
	@RequestMapping(value="/upload/{mode}/file/delete",method=RequestMethod.GET)
	SuccessMessage deleteFile(@PathVariable("mode")String mode,
			@RequestParam("file") String file);
}
