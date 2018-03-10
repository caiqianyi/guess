package com.lebaoxun.upload.core.impl;

import java.net.URLEncoder;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.lebaoxun.commons.exception.I18nMessageException;
import com.lebaoxun.soa.core.redis.IRedisCache;
import com.lebaoxun.upload.core.IAuditImgService;
import com.lebaoxun.upload.utils.HttpUtil;


/**
 * 百度云图像鉴定
 * @author Caiqianyi
 *
 */
@Service
public class BaiduAuditImgServiceImpl implements IAuditImgService {
	
	private static Logger logger = LoggerFactory.getLogger(BaiduAuditImgServiceImpl.class);
	@Resource
	private IRedisCache redisCache;
	// 官网获取的 API Key 更新为你注册的
	private String clientId = "OFTbPgPMTDaI3Rg0ItoIXQ7l";
    // 官网获取的 Secret Key 更新为你注册的
	private String clientSecret = "GqEjbpKoLsUzGil7s2g3xNRUvSSwG0VF";
	private RestTemplate restTemplate = new RestTemplate();
	
	//注入  ， 获取百度鉴权的token值
	public String getAccessToken() {
		try {
        	if(redisCache.exists(clientId)){
        		return redisCache.get(clientId).toString();
        	}
    		String authHost = "https://aip.baidubce.com/oauth/2.0/token?";
            String getAccessTokenUrl = authHost+"grant_type=client_credentials&client_id={client_id}&client_secret={client_secret}";
            String response = restTemplate.getForObject(getAccessTokenUrl, String.class, clientId, clientSecret);
        	// 5.解析接口返回值
            JsonObject resultObject = new JsonParser().parse(response).getAsJsonObject();
            //缓存token
            String access_token = resultObject.get("access_token").getAsString();
            long expires_in = resultObject.get("expires_in").getAsLong();
            logger.info("accessToken={},expires_in={}",access_token,expires_in);
            redisCache.set(clientId, access_token, expires_in);
            return access_token;
        } catch (Exception e) {
        	logger.error("获取token失败|error={}",e);
            e.printStackTrace(System.err);
        }
        return null;
	}
	
	/**
     * 用户头像审核
     * @param imgUrl
     * @return
     */
    public boolean userDefinedURL(String imgUrl) {
        String url = "https://aip.baidubce.com/rest/2.0/solution/v1/img_censor/user_defined";
        String param = "imgUrl="+imgUrl;
        String response;
		try {
			response = HttpUtil.post(url, getAccessToken(), param);
		} catch (Exception e) {
			e.printStackTrace();
			throw new I18nMessageException("-1","系统异常");
		}
        return check(response);
    }
    
    /**
     * 用户头像审核
     * @param image  图片Base64编码字符串
     * @return
     */
    public boolean userDefinedImage(String image) {
    	String url = "https://aip.baidubce.com/rest/2.0/solution/v1/img_censor/user_defined";
		String imgParam;
		try {
			imgParam = URLEncoder.encode(image, "UTF-8");
			String param = "image="+imgParam;
			//"{\"conclusion\":\"不合规\",\"log_id\":151151263352688,\"data\":[{\"msg\":\"存在水印码内容\",\"probability\":0.8874121,\"type\":5},{\"msg\":\"命中黑名单:自定义涉政事件敏感词\",\"probability\":0.998,\"words\":\"法轮,法轮功,法轮大法,轮功,轮功法\",\"type\":9}]}";
			String response = HttpUtil.post(url, getAccessToken(), param);
			return check(response);
		} catch (I18nMessageException e) {
			throw e;
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	throw new I18nMessageException("-1","系统异常");
	    }
		
    }
	
    /**
     * 解析审核结果
     * @return
     */
    private static boolean check(String response){
    	JsonObject ress = new JsonParser().parse(response).getAsJsonObject();
    	logger.debug("response={}",response);
    	if(response.contains("error_msg")){
        	return true;
        }
    	String result = ress.get("conclusion").getAsString();
    	if("不合规".equals(result)){
    		int res = 0; //严重【不合规】码计次
	    	JsonArray data = ress.get("data").getAsJsonArray();
	    	for(int i =0;i<data.size();i++){
	    		String type = data.get(i).getAsJsonObject().get("type").getAsString();
	    		String msg = data.get(i).getAsJsonObject().get("msg").getAsString();
	    		if(!("2".equals(type) || "5".equals(type) || "6".equals(type) || "7".equals(type)) 
	    				&& !("9".equals(type) && "广告内容".equals(msg))){
	    			logger.info("check|type={}",type);
	    			res++;
	    			result = msg;
	    		}
	    	}
	    	logger.info("check|res={},result={}",res,result);
	    	if(res > 0){ //无严重【不合规】，将审核结果归为【合规】
	    		throw new I18nMessageException("-1",result);
	    	}
    	}
    	return true;
    }
    
}
