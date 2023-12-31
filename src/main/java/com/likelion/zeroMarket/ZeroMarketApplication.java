package com.likelion.zeroMarket;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@OpenAPIDefinition(servers = {@Server(url="/", description = "Default Server URL")})

@SpringBootApplication
@EnableJpaAuditing  //JpaAuditing 기능 사용
public class ZeroMarketApplication {
	/*
	public static final String APPLICATION_LOCATIONS =
			"spring.config.location="
					+ "classpath:application.yml";

	public static void main(String[] args) {
		new SpringApplicationBuilder(ZeroMarketApplication.class)
				.properties(APPLICATION_LOCATIONS)
				.run(args);
	}
	 */
	public static void main(String[] args) {
		SpringApplication.run(ZeroMarketApplication.class, args);
	}
}
