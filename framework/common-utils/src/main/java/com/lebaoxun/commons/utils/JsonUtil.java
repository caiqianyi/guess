package com.lebaoxun.commons.utils;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/***
 * 关于GSON的工具类
 * @author xhl
 *
 */
public class JsonUtil {
	
	/**
	 * 将json转化为map
	 * @param jsonStr
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String,Object> json2Map(String jsonStr){		
		return JSON.parseObject(jsonStr, Map.class);
		
	}
	
	public static String bean2Json(Object object) {
		return JSONObject.toJSONString(object,SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
	}
	
	public static Map<String,Object> jsonObject2Map(String jsonStr){		
		
		if(!"".equals(jsonStr) && jsonStr!=null){
			Gson gson=new Gson();
			Map<String,Object> retMap = gson.fromJson(jsonStr,  
	                new TypeToken<Map<String,Object>>() {  
	                }.getType()); 
			return retMap;
		}
		return null;
		
	}
	
	
	/**
	 * 将json转化为List<map>
	 * @param jsonStr
	 * @return
	 */
	public static List<Map<String,String>> json2ListMap(String jsonStr){		
		
		if(!"".equals(jsonStr) && jsonStr!=null){
			Gson gson=new Gson();
			List<Map<String,String>> retList = gson.fromJson(jsonStr,  
	                new TypeToken<List<Map<String,String>>>() {  
	                }.getType()); 
			return retList;
		}
		return null;
		
	}
	
}
