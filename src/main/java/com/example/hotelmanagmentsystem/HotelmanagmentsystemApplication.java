package com.example.hotelmanagmentsystem;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Hotel Management System REST API Documentation"
		)

)
public class HotelmanagmentsystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelmanagmentsystemApplication.class, args);
	}

}
