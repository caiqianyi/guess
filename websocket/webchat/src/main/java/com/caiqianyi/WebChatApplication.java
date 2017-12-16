package com.caiqianyi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
public class WebChatApplication {
	/*
	 * @Bean public ServletRegistrationBean
	 * dispatcherRegistration(DispatcherServlet dispatcherServlet) {
	 * ServletRegistrationBean registration = new
	 * ServletRegistrationBean(dispatcherServlet);
	 * registration.getUrlMappings().clear();
	 * registration.addUrlMappings("*.json"); return registration; }
	 */

	public static void main(String[] args) {
		SpringApplication.run(WebChatApplication.class, args);
	}

}
