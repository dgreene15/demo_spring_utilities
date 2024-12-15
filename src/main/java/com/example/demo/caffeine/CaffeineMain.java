package com.example.demo.caffeine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// todo: move this to different repo.
@SpringBootApplication
public class CaffeineMain implements CommandLineRunner {
	
	@Autowired
	CaffeineDemo demo;

	public static void main(String[] args) {
		SpringApplication.run(CaffeineMain.class, args);
	}
	
	public void run(String... args) {
		System.out.println("calling first time from cache");
		demo.getDataWithAnnotation(4);
		demo.getDataWithManager(4);
		
		System.out.println("calling multiple times from cache");
		demo.getDataWithAnnotation(4);
		demo.getDataWithAnnotation(4);
		demo.getDataWithAnnotation(4);
	}

}
