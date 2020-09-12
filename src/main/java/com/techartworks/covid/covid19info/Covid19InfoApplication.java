package com.techartworks.covid.covid19info;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEncryptableProperties
public class Covid19InfoApplication {

	public static void main(String[] args) {
		SpringApplication.run(Covid19InfoApplication.class, args);
	}

}
