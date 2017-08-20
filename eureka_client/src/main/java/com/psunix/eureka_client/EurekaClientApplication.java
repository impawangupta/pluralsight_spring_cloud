package com.psunix.eureka_client;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient
@SpringBootApplication
@RestController
public class EurekaClientApplication {

	@Autowired
	private DiscoveryClient discoveryClient;

	@Autowired
	private EurekaClient eurekaClient;

	@Autowired
    private RestTemplateBuilder restTemplateBuilder;

	public static void main(String[] args) {
		SpringApplication.run(EurekaClientApplication.class, args);
	}

	@RequestMapping("/")
    public String getMessageFromService(){
        RestTemplate restTemplate = restTemplateBuilder.build();
        InstanceInfo service = eurekaClient.getNextServerFromEureka("eureka_service", false);
        String homePageUrl = service.getHomePageUrl();
        ResponseEntity<String> entity = restTemplate.exchange(homePageUrl, HttpMethod.GET, null, String.class);
        return  entity.getBody();
   }


}
