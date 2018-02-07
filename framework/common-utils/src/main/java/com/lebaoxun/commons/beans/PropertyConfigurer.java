package com.lebaoxun.commons.beans;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

/**
 * 配置文件读取类
 */
@Component
public class PropertyConfigurer{

    private static Map<String,Properties[]> map = new HashMap<String,Properties[]>();
    
    public Properties[] loadProperties(String locationPattern){
    	org.springframework.core.io.Resource[] resourcep;
		try {
			resourcep = new PathMatchingResourcePatternResolver().getResources(locationPattern);
			if(resourcep.length == 0) return null;
			Properties[] props = new Properties[resourcep.length];
			for(int i=0;i<resourcep.length;i++){
				props[i] = new Properties();
				props[i].load(resourcep[i].getInputStream());
			}
			return props;
		} catch (IOException e) {
			throw new RuntimeException("加载文件"+locationPattern+"失败， " + e.toString());
		}
    }
    
    public Properties loadUniqueProperties(String locationPattern){
    	Properties[] props = loadProperties(locationPattern);
    	if(props== null) return null;
    	if(props.length > 1)
    		throw new RuntimeException("加载"+locationPattern+"配置文件失败， 因为存在多个同名的文件。数量为：" + props.length );
    	return props[0];
    }
    
    public Properties[] lazyLoadProperties(String locationPattern){
    	if(!map.containsKey(locationPattern)){
    		Properties[] props = loadProperties(locationPattern);
    		map.put(locationPattern, props);
    	}
		return map.get(locationPattern);
    }
    
    public Properties lazyLoadUniqueProperties(String locationPattern){
    	Properties[] props = lazyLoadProperties(locationPattern);
    	if(props== null) return null;
    	if(props.length > 1)
    		throw new RuntimeException("加载"+locationPattern+"配置文件失败， 因为存在多个同名的文件。数量为：" + props.length );
    	return props[0];
    }
    
}