package com.example.Caramelca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Calendar;
import java.util.GregorianCalendar;

@SpringBootApplication
public class CaramelcaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CaramelcaApplication.class, args);
		Calendar calendar = new GregorianCalendar();
		System.out.println(calendar);

	}

}
