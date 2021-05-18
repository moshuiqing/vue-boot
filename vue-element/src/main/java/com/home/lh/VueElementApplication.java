package com.home.lh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan 
@MapperScan("**.mapper") // 扫描mybaits注解包 使用后无需再@mapper
public class VueElementApplication {

	public static void main(String[] args) {
		SpringApplication.run(VueElementApplication.class, args);
	}

}
