package com.finalyear.VolunteeringSystm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class VolunteeringSystmApplication {

	public static void main(String[] args) {
		SpringApplication.run(VolunteeringSystmApplication.class, args);
	}

}
