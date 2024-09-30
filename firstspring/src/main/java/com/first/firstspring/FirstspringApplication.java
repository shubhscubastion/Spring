package com.first.firstspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.first.firstspring.model.Alien;
import com.first.firstspring.model.Laptop;
import com.first.firstspring.service.LaptopService;

@SpringBootApplication
public class FirstspringApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(FirstspringApplication.class, args);

		LaptopService service = context.getBean(LaptopService.class);

		Laptop lap = context.getBean(Laptop.class);
		service.addLaptop(lap);

		Alien obj = context.getBean(Alien.class);
		obj.code();

	}

}
