package com.first.firstspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class FirstspringApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(FirstspringApplication.class, args);

		Alien obj = context.getBean(Alien.class);
		obj.code();

	}

}
