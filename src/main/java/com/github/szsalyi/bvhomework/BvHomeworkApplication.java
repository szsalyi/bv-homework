package com.github.szsalyi.bvhomework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication(scanBasePackages = "com.github.szsalyi.bvhomework")
@EnableJms
public class BvHomeworkApplication {

	public static void main(String[] args) {
		SpringApplication.run(BvHomeworkApplication.class, args);
	}

}
