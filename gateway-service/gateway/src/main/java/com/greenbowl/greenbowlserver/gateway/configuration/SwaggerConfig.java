package com.greenbowl.greenbowlserver.gateway.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.List;

@Configuration
public class SwaggerConfig implements WebFluxConfigurer {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("greenbowl")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.greenbowl.greenbowlserver"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .securityContexts(Arrays.asList(securityContext()))
                .securitySchemes(Arrays.asList(apiKey()))
                .pathMapping("/api");
    }
    private ApiKey apiKey() {
        return new ApiKey("JWT", "Authorization", "header");
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("그린볼 서버 API 문서")
                .description("스마트 헬스 푸드 케어 서비스 그린볼의 API 문서입니다.\n" +
                        "AI 레시피 서비스 API : https://greenbowl.duckdns.org/api/recipes/swagger-ui/index.html\n"+
                        "냉장고 관리 서비스 API : https://greenbowl.duckdns.org/api/fridges/swagger-ui/index.html\n"+
                        "레시피 추천 서비스 API : https://greenbowl.duckdns.org/api/recommendations/swagger-ui/index.html\n"+
                        "회원 서비스 API : https://greenbowl.duckdns.org/api/users/swagger-ui/index.html\n"
                        )
                .version("1.0")
                .build();
    }
    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.any())
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[0];
        return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
    }
}
