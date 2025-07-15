package com.shriyansh.usermanagement;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UsermanagementApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.configure()
				.directory("./")      // project root
				.filename(".env")     // file name
				.load();

		// Set as system properties so Spring can read them
		dotenv.entries().forEach(entry ->
				System.setProperty(entry.getKey(), entry.getValue())
		);


		SpringApplication.run(UsermanagementApplication.class, args);
	}

}
