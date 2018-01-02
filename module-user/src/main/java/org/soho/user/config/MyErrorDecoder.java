package org.soho.user.config;

import feign.Response;
import org.springframework.context.annotation.Configuration;

/**
 * Created by zhuozl on 18-1-2.
 */
@Configuration
public class MyErrorDecoder implements feign.codec.ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {

        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXx");
        System.out.println(response.toString());
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXx");
        return new Exception(methodKey, null);
    }
}