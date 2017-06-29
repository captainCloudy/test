package com.wishbuild.saas.test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableDiscoveryClient
@Controller
@EnableWebMvc
@SpringBootApplication
@MapperScan(basePackages = "com.wishbuild.saas.test.respository.mapper")
public class TestApplication extends WebMvcConfigurerAdapter {

	/*@Bean
	@LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();
	}*/

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}

	@RequestMapping("/")
	String home() {
		return "redirect:countries";
	}

	@RequestMapping("/test")
	String test() {
		return "redirect:testTest";
	}
}
