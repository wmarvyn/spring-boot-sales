package com.wmarvyn.sales.config;

import com.wmarvyn.sales.services.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("dev")
public class DevConfig {

	@Autowired
	private DBService dbService;

	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;

	@Bean
	public boolean instantiateDatabase() throws ParseException {

		if (!"create".equals(strategy)) {
		    System.out.println("Parametro DDL setado como ===>:" + strategy + " - " + "Tabelas nao re-criadas");
		} else {
			dbService.instantiateTestDataBase();
            System.out.println("Parametro DDL setado como ===>:" + strategy + " - " + "Tabelas re-criadas");
		}

		return true;
	}
}
