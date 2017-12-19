package org.soho.utils.gendb;

import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcTemplateUtil {
	public JdbcTemplate getJdbcTemplate(){
		JdbcTemplate jdbcTemplate = new JdbcTemplate(ConnectPool.getDataSource());
		return jdbcTemplate;
	}
}	
