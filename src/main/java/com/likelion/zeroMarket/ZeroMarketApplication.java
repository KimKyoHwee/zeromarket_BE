package com.likelion.zeroMarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class ZeroMarketApplication {
	public static final String APPLICATION_LOCATIONS =
			"spring.config.location="
					+ "classpath:application.yml";

	public static void main(String[] args) {
		new SpringApplicationBuilder(ZeroMarketApplication.class)
				.properties(APPLICATION_LOCATIONS)
				.run(args);
	}


	/*
	public static void main(String[] args) {
		SpringApplication.run(ZeroMarketApplication.class, args);
	}

	 */

}
