package com.lpiot.ouila;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import filter.AuthFilter;

@SpringBootApplication
@ComponentScan({"com.lpiot.ouila","domain", "exceptions", "filter", "repositories", "ressources", "services"})
public class OuilaApplication {

	public static void main(String[] args) {
		SpringApplication.run(OuilaApplication.class, args);
	}
	
	@Bean
	public FilterRegistrationBean<AuthFilter> filterRegistrationBean(){
		FilterRegistrationBean<AuthFilter> registrationBean = new FilterRegistrationBean<>();
		AuthFilter authFilter = new AuthFilter();
		registrationBean.setFilter(authFilter);
		//registrationBean.addUrlPatterns("/users/*");
		registrationBean.addUrlPatterns("/subjects/*");
		registrationBean.addUrlPatterns("/presences/*");
		registrationBean.addUrlPatterns("/justifications/*");
		registrationBean.addUrlPatterns("/classes/*");
		 
		return registrationBean; 
	}

}
