package com.caiqianyi.soa.web.framework.cfg;

import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Configuration;

import com.caiqianyi.soa.web.framework.interceptor.MybatisPageInterceptor;

@Configuration
@AutoConfigureAfter(SqlSessionFactory.class)
public class PageAnnotaionScanner implements InitializingBean{
	
	private Logger logger = LoggerFactory.getLogger(PageAnnotaionScanner.class);
	
	@Value(value = "${page.annotation.enable:false}")
	private boolean enable;

	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	@Override
	public void afterPropertiesSet() throws Exception {
		logger.debug("enable={}",enable);
		if (enable) {
			
			MybatisPageInterceptor mpi = new MybatisPageInterceptor();
			mpi.setDialect("mysql");
			mpi.setPageSqlId("forPager");
			sqlSessionFactory.getConfiguration().addInterceptor(mpi);
		}
		
	}
}
