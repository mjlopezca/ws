package com.escolar.persona;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class PersonaWsApplication {
	private final static Logger log = LogManager.getLogger(PersonaWsApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(PersonaWsApplication.class, args);
	}
}
