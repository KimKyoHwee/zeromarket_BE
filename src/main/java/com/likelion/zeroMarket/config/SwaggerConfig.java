package com.likelion.zeroMarket.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
@Configuration
@EnableWebMvc
public class SwaggerConfig {  //swagger UI
    private Info swaggerInfo(){
        return new Info()
                .title("ZeroMarket API명세서")
                .version("1.0.0")
                .description("23년도 여름 멋사 중앙 해커톤 프로젝트\n" +
                        "ZeroMarket API 명세서입니다.\n");
    }

    @Bean
    public GroupedOpenApi getUserApi(){
        return GroupedOpenApi
                .builder()
                .group("user")
                .pathsToMatch("/user/**")
                .build();
    }

    @Bean
    public GroupedOpenApi getProductApi(){
        return GroupedOpenApi
                .builder()
                .group("product")
                .pathsToMatch("/product/**")
                .build();
    }

    @Bean
    public GroupedOpenApi getStoreApi(){
        return GroupedOpenApi
                .builder()
                .group("store")
                .pathsToMatch("/store/**")
                .build();
    }

    @Bean
    public GroupedOpenApi getReviewApi(){
        return GroupedOpenApi
                .builder()
                .group("review")
                .pathsToMatch("/review/**")
                .build();
    }

    @Bean
    public GroupedOpenApi getSellApi(){
        return GroupedOpenApi
                .builder()
                .group("sell")
                .pathsToMatch("/sell/**")
                .build();
    }

    @Bean
    public GroupedOpenApi getMainApi(){
        return GroupedOpenApi
                .builder()
                .group("main")
                .pathsToMatch("/main/**")
                .build();
    }
    @Bean
    public OpenAPI getOpenApi(){
        return new OpenAPI().components(new Components())
                .info(swaggerInfo());
    }
}
