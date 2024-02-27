package com.mcsw.receiptapp;

import com.mcsw.receiptapp.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReceiptAppApplication {

	UserRepository userRepository = new UserRepository();


	public static void main(String[] args) {
		SpringApplication.run(ReceiptAppApplication.class, args);
	}

}
