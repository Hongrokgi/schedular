package com.schedular.schedualr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@EnableScheduling
public class SchedualrApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchedualrApplication.class, args);
	}

}
