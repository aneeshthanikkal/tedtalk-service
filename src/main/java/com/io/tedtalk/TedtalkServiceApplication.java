package com.io.tedtalk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
@EnableWebMvc
@SpringBootApplication
public class TedtalkServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TedtalkServiceApplication.class, args);
	}

}
