package com.example.bookservice.book.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
* AppBean
*/
@Component
public class AppBean {
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
