package org.soho.common.config;

import org.soho.common.filter.CustomFilter1;
import org.soho.common.filter.CustomFilter2;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhuozl on 17-4-21.
 */
public class WebConfig {

    @Bean
    public FilterRegistrationBean greetingFilterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setName("customFilter1");
        CustomFilter1 customFilter1 = new CustomFilter1();
        registrationBean.setFilter(customFilter1);
        registrationBean.setOrder(1);
        List<String> urlList = new ArrayList<String>();
        urlList.add("/abc");
        registrationBean.setUrlPatterns(urlList);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean helloFilterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setName("customFilter2");
        CustomFilter2 customFilter2 = new CustomFilter2();
        registrationBean.setFilter(customFilter2);
        registrationBean.setOrder(2);
        return registrationBean;
    }

    /**
    @Bean
    @Order(1)
    Filter customFilter1() {
        return new CustomFilter1();
    }

    @Bean
    @Order(2)
    public Filter customFilter2() {
        return new CustomFilter2();
    }
     */
}

