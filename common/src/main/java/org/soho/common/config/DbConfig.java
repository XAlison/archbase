package org.soho.common.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

/**
 * Created by zhuozl on 17-4-24.
 */

@Configuration
public class DbConfig {

    @Autowired private Environment env;

    //value 形式用于在config-server 中读取数据
    /*
        @Value("${from}") private String from;
        @Value("${spring.datasource.url}") private String url;
        @Value("${spring.datasource.username}") private String username;
        @Value("${spring.datasource.password}") private String password;
        @Value("${spring.datasource.driver-class-name}") private String driver;
    */

    @Bean
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();

        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));//用户名
        dataSource.setPassword(env.getProperty("spring.datasource.password"));//密码
        dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));

        /**
         dataSource.setUrl(url);
         dataSource.setUsername(username);//用户名
         dataSource.setPassword(password);//密码
         dataSource.setDriverClassName(driver);
         */
        dataSource.setInitialSize(2);
        dataSource.setMaxActive(20);
        dataSource.setMinIdle(0);
        dataSource.setMaxWait(60000);
        dataSource.setValidationQuery("SELECT 1");
        dataSource.setTestOnBorrow(false);
        dataSource.setTestWhileIdle(true);
        dataSource.setPoolPreparedStatements(false);
        return dataSource;
    }
}
