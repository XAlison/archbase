package org.soho;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by zhuozl on 17-4-27.
 */
@SpringBootApplication
@EnableRedisHttpSession
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableHystrix
@EnableSwagger2
@EnableResourceServer
public class PayApplication {

    public static void main(String[] args) {
        SpringApplication.run(PayApplication.class, args);
    }

    @Bean
    public Docket newsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("PAY")
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.soho.pay.controller"))
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
    public AlwaysSampler defaultSampler(){
        return new AlwaysSampler();
    }

}
