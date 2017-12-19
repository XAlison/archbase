package org.soho.gateway;

import org.soho.gateway.filter.AccessFilter;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by zhuozl on 17-3-8.
 */
@EnableZuulProxy
@SpringCloudApplication
@EnableSwagger2
public class GateWayApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(GateWayApplication.class).web(true).run(args);
    }


    @Bean
    public Docket newsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("ORDER")
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.soho.gateway.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring REST Sample with Swagger(title)")
                .description("Spring REST Sample with Swagger(description)")
                .contact("zhuozl")
                .license("Apache License Version 2.0(license)")
                .licenseUrl("https://github.com/zhuozl")
                .version("1.0")
                .build();
    }

    @Bean
    public AccessFilter accessFilter() {
        return new AccessFilter();
    }
}