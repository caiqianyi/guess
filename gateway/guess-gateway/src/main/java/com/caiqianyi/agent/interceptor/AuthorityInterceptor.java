package com.caiqianyi.agent.interceptor;

import java.io.PrintWriter;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.caiqianyi.agent.security.GlobalToken;
import com.caiqianyi.agent.security.Oauth2SecuritySubject;
import com.caiqianyi.commons.beans.PropertyConfigurer;
import com.caiqianyi.commons.exception.SuccessMessage;
import com.google.gson.Gson;

public class AuthorityInterceptor implements HandlerInterceptor {
	
	private Logger logger = LoggerFactory.getLogger(AuthorityInterceptor.class);
	
	private Properties props = null;
	
	private PropertyConfigurer propertyConfigurer;
	
	@Resource
	private Oauth2SecuritySubject oauth2SecuritySubject;
	

    /**
     * 拦截指定接口(AuthorityPath内配置)判断所传参数区服和游戏是否属于登录用户本身权限范围内 指定接口必须传serverCode和gameId参数
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	String token = request.getHeader("Authorization");
    	GlobalToken.setToken(token);
    	logger.debug("token={}",token);
    	if(StringUtils.isBlank(token) || !oauth2SecuritySubject.checkToken(token)){
    		Gson gson = new Gson();
    		SuccessMessage _i18n = new SuccessMessage();
    		String code = "10003";
    		String msg = props.getProperty(code);
    		_i18n.setErrcode(code);
    		_i18n.setErrmsg(msg);
    		String jsonString = gson.toJson(_i18n);
    		response.setContentType("application/json;charset=utf-8");
    		PrintWriter writer = response.getWriter();
    		writer.write(jsonString);
    		writer.flush();
    		return false;
    	}
        return true;
    }

    @Resource
	public void setPropertyConfigurer(PropertyConfigurer propertyConfigurer) {
		this.propertyConfigurer = propertyConfigurer;
		this.props = this.propertyConfigurer.lazyLoadUniqueProperties("classpath*:i18n_messages.properties");
	}

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		GlobalToken.remove();
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}

}