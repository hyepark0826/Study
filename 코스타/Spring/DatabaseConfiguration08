package com.example.demo.config;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
//application.yml파일을 application.properties파일로 변경
@PropertySource("classpath:/application.properties")
public class DatabaseConfiguration {
	@Bean
	@ConfigurationProperties(prefix="spring.datasource.hikari") //dependency추가
	public HikariConfig hikariConfig() {
		return new HikariConfig();
	}
	
	@Bean	
	public DataSource dataSource() throws Exception {
		DataSource dataSource = new HikariDataSource(hikariConfig());
		return dataSource;
	}
	
	@Autowired
	private ApplicationContext applicationContext;
	
	
}