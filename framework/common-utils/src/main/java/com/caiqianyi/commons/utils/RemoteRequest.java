package com.caiqianyi.commons.utils;

/**
 * Created by liyan on 2016/11/8.
 */

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class RemoteRequest {

    public RemoteRequest() {
    }

    /**
     * <p>获取客户端IP</p>
     * @return String
     */
    public static String getRequestIP(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }

        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        if(ip != null) {
            String[] ipArray = ip.split(",");
            if(ipArray != null && ipArray.length > 1) {
                ip = ipArray[0];
            }
        }

        return ip;
    }

    /**
     * <p>request参数转Map</p>
     * @return Map
     */
    public static Map<String, Object> getRequsetParamMap(HttpServletRequest request) {
        Map properties = request.getParameterMap();
        Iterator it = properties.entrySet().iterator();

        HashMap paramMap;
        String key;
        String value;
        for(paramMap = new HashMap(); it.hasNext(); paramMap.put(key, value)) {
            Entry entry = (Entry)it.next();
            key = (String)entry.getKey();
            Object objectValue = entry.getValue();
            if(objectValue == null) {
                value = "";
            } else if(objectValue instanceof String[]) {
                String[] e = (String[])objectValue;
                value = e[0];
            } else {
                value = objectValue.toString();
            }

            try {
                value = URLDecoder.decode(value, "utf-8");
            } catch (UnsupportedEncodingException var9) {
                value = URLDecoder.decode(value);
            }
        }

        return paramMap;
    }

    public static String getRequestData(HttpServletRequest request) {
        StringBuilder requestData = new StringBuilder();
        requestData.append("RequestInfo=RemoteAddr:").append(getRequestIP(request));
        requestData.append("--RequestURL:").append(request.getRequestURL());
        requestData.append("--Referer:").append(request.getHeader("referer"));
        requestData.append("--ParameterMap:").append(getRequsetParamMap(request));
        return requestData.toString();
    }
}