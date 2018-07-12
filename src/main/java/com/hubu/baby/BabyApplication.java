package com.hubu.baby;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hubu.baby.mapper")
public class BabyApplication {

	public static void main(String[] args) {
		SpringApplication.run(BabyApplication.class, args);
	}
}
