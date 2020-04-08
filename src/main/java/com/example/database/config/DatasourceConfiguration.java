package com.example.database.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DatasourceConfiguration {

	@Bean
	@Primary
	@ConfigurationProperties("mysql.db1.datasource")
	public DataSourceProperties firstDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean(name = "db1Datasource")
	@Primary	
	public DataSource mysqlDatasource() {
		return firstDataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
		
	}

	@Bean
    @ConfigurationProperties("mysql.db2.datasource")
    public DataSourceProperties secondDataSourceProperties() {
        return new DataSourceProperties();
    }
	
	@Bean(name = "db2Datasource")
	//@ConfigurationProperties(prefix = "mysql.db2.datasource.configuration")
	public DataSource postgresDatasource() {
		return secondDataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
	}

	@Bean(name = "db1JdbcTemplate")
	public NamedParameterJdbcTemplate mysqlJdbcTemplate(@Qualifier("db1Datasource") DataSource dataSource) {
		return new NamedParameterJdbcTemplate(dataSource);
	}

	@Bean(name = "db2JdbcTemplate")
	public NamedParameterJdbcTemplate postgresJdbcTemplate(@Qualifier("db2Datasource") DataSource dataSource) {
		return new NamedParameterJdbcTemplate(dataSource);
	}

}
