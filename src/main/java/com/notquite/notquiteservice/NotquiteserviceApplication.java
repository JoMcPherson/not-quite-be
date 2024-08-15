package com.notquite.notquiteservice;

import com.notquite.notquiteservice.filter.CustomCorsFilter;
import com.notquite.notquiteservice.services.ScheduledTasks;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaAuditing
@EnableScheduling
public class NotquiteserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotquiteserviceApplication.class, args);
	}

	ScheduledTasks scheduledTasks;

	@Bean
	public FilterRegistrationBean<CustomCorsFilter> corsFilterRegistrationBean() {
		FilterRegistrationBean<CustomCorsFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new CustomCorsFilter());
		registrationBean.addUrlPatterns("/*");
		registrationBean.setOrder(0);
		return registrationBean;
	}
}
