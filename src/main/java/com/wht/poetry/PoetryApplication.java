package com.wht.poetry;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"com.wht.poetry.mapper","com.wht.poetry.dao"})
public class PoetryApplication {

	public static void main(String[] args) {
		SpringApplication.run(PoetryApplication.class, args);
	}

}
