package org.soho.utils.gendb;

import java.io.InputStream;
import java.util.Properties;

public class GdaoConfig {
	private static Properties properties;
	static {

		try {
			properties = new Properties();
			InputStream is = ConnectPool.class.getClassLoader()
					.getResourceAsStream("gdao.properties");
			properties.load(is);
			// dataSource.setIdleConnectionTestPeriod(Integer.parseInt(properties.getProperty("idleConnectionTestPeriod")));
			// dataSource.setMaxConnectionAge(Integer.parseInt(properties.getProperty("maxConnectionAge")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getDaoPkg(){
		return properties.getProperty("pkg.dao");
	}
}
