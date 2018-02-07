package com.lebaoxun.agent.rest;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lebaoxun.commons.exception.SuccessMessage;
import com.lebaoxun.upload.service.IUploadService;

@RestController
public class UploadController {

	@Resource
	private IUploadService uploadService;
	
	@RequestMapping(value="/upload/img",method=RequestMethod.POST)
	SuccessMessage uploadImg(@RequestParam(value="namespace",required=false,defaultValue="test") String namespace,
			@RequestParam("fileType") String fileType,
			@RequestParam("imgStr") String imgStr){
		return uploadService.uploadImg("local", namespace, fileType, imgStr);
	}
}
