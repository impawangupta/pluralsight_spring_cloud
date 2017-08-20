package com.psunix.eureka_service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableEurekaClient
@SpringBootApplication
@RestController
public class EurekaServiceApplication {

    @Value("${service.instance.name}")
	private String instanceName;

	public static void main(String[] args) {
		SpringApplication.run(EurekaServiceApplication.class, args);
	}

	@RequestMapping("/")
	public String getMessage(){
		return "Hello From the " + instanceName;
	}
}
