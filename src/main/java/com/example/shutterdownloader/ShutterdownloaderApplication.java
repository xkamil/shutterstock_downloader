package com.example.shutterdownloader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ShutterdownloaderApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShutterdownloaderApplication.class, args);
	}

}
