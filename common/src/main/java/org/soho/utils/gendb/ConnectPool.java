package org.soho.utils.gendb;

import com.alibaba.druid.pool.DruidDataSource;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectPool {
	private static DruidDataSource dataSource = null;
	static {

		try {
			dataSource = new DruidDataSource();
			Properties properties = new Properties();
			InputStream is = ConnectPool.class.getClassLoader()
					.getResourceAsStream("bootstrap.properties");
			properties.load(is);

            dataSource.setUrl(properties.getProperty("spring.datasource.url"));
            dataSource.setUsername(properties.getProperty("spring.datasource.username"));//用户名
            dataSource.setPassword(properties.getProperty("spring.datasource.password"));//密码
            dataSource.setDriverClassName(properties.getProperty("spring.datasource.driver-class-name"));

            dataSource.setInitialSize(2);
            dataSource.setMaxActive(20);
            dataSource.setMinIdle(0);
            dataSource.setMaxWait(60000);
            dataSource.setValidationQuery("SELECT 1");
            dataSource.setTestOnBorrow(false);
            dataSource.setTestWhileIdle(true);
            dataSource.setPoolPreparedStatements(false);


        } catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static synchronized Connection getConnection() {
		Connection con = null;
		try {
			con = dataSource.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return con;
	}
	
	public static DataSource getDataSource(){
		return dataSource;
	}

}
