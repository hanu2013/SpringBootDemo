package com.example.config;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
public class NKNDatabaseConfiguration {

	@Bean
	@Primary
	@ConfigurationProperties("app.datasource.member")
	public DataSourceProperties memberDataSourceProperties() {
	    return new DataSourceProperties();
	}
	
	@Bean
	@Primary
	@ConfigurationProperties("app.datasource.member.configuration")
	public DataSource memberDataSource() {
	    return memberDataSourceProperties().initializeDataSourceBuilder()
	            .type(HikariDataSource.class).build();
	}
	
	@Bean
	@ConfigurationProperties("app.datasource.cardholder")
	public DataSourceProperties cardHolderDataSourceProperties() {
	    return new DataSourceProperties();
	}
	@Bean
	@ConfigurationProperties("app.datasource.cardholder.configuration")
	public DataSource cardholderDataSource() {
	    return cardHolderDataSourceProperties().initializeDataSourceBuilder()
	            .type(HikariDataSource.class).build();
	}
	
	public DataSourceTransactionManager memberDBSTransactionManager() {
		return new DataSourceTransactionManager(memberDataSource());
	}
	
	@Bean(value="member")
	public NamedParameterJdbcTemplate memberJdbcTemplate() {
		return new NamedParameterJdbcTemplate(memberDataSource());
	}
	
//	@Bean(name="card")
//	public NamedParameterJdbcTemplate cardJdbcTemplate() {
//		return new NamedParameterJdbcTemplate(memberDataSource());
//	}
}
