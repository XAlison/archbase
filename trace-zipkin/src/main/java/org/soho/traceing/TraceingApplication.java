package org.soho.traceing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin.server.EnableZipkinServer;

/**
 * Created by zhuozl on 17-4-28.
 */
@SpringBootApplication
@EnableZipkinServer
public class TraceingApplication {
    public static void main(String[] args) {
        SpringApplication.run(TraceingApplication.class, args);
    }


}
