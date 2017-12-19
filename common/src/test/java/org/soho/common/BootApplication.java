package org.soho.common;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * Created by zhuozl on 17-3-8.
 */
@SpringBootApplication
public class BootApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(BootApplication.class).web(true).run(args);
    }
}