package org.soho.auth.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * to use remote oauth server to authentication token
 */
@SpringBootApplication
@Configuration
@EnableResourceServer
@RestController
@EnableOAuth2Client
public class ResourceApplication extends ResourceServerConfigurerAdapter {

    @Autowired
    private ResourceServerProperties sso;

    public static void main(String[] args) {
        SpringApplication.run(ResourceApplication.class, args);
    }


    @Bean
    public ResourceServerTokenServices tokenServices() {
        return new CustomUserInfoTokenServices(sso.getUserInfoUri(), sso.getClientId());
    }

    @RequestMapping("/hello")
    public String home(Principal principal) {
        return "SUCCESS，授权成功拿到资源啦.当前用户：" + principal.getName();
    }

    @RequestMapping("/hello2")
    public String home2() {
        return "Hello World";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody MultiValueMap<String, String> map) {
        return "OK";
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/hello").authenticated();
        //.antMatchers(HttpMethod.POST, "/foo").hasAuthority("FOO_WRITE");
        //you can implement it like this, but I show method invocation security on write
    }

}
