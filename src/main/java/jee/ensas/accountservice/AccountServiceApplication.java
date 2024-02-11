package jee.ensas.accountservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.reactive.function.client.WebClient;
import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;

@SpringBootApplication
@EnableEurekaClient
@EnableHystrix
public class AccountServiceApplication {

	@Bean
	@LoadBalanced
	public WebClient.Builder getWebClientBuilder() {
		return  WebClient.builder();
	}

	public static void main(String[] args) {
		SpringApplication.run(AccountServiceApplication.class, args);
	}

}
