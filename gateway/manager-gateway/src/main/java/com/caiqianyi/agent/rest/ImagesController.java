package com.caiqianyi.agent.rest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.caiqianyi.agent.constants.WebConstants;
import com.caiqianyi.commons.utils.CommonUtil;
import com.caiqianyi.soa.core.redis.IRedisCache;

@RestController
@RequestMapping("/images")
public class ImagesController extends BaseController{
	
	private Logger logger = LoggerFactory.getLogger(ImagesController.class);
	
	@Resource
	private IRedisCache redisCache;
	
	/**
	 * 生成验证码
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value="/captcha.png",method=RequestMethod.GET)
	void captcha(String emid) throws IOException {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		
		//验证码等级
		String code = CommonUtil.getVerifyCode(output, 1);
		logger.debug("code={}",code);
		//当前页面刷验证码的次数
		
		if(StringUtils.isNotBlank(emid)){
			redisCache.set("agent:vfcode:"+emid, code, 20*60L);
		}else{
			request.getSession().setAttribute(WebConstants.SYS_VERIFYCODE, code);
		}
		try {
			ServletOutputStream out = response.getOutputStream();
			output.writeTo(out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
