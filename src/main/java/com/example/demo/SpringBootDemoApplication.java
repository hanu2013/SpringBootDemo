package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@SpringBootApplication
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class SpringBootDemoApplication {
	
	
	@Autowired
	@Qualifier("member")
	private NamedParameterJdbcTemplate memberJdbcTemplate;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDemoApplication.class, args);
		
		
		System.out.println("Finish");
	}

}
