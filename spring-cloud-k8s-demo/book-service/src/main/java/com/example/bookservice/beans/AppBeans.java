package com.example.bookservice.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
* AppBean
*/
@Component
public class AppBeans {
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
