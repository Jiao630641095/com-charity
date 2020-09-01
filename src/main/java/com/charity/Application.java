package com.charity;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
@EnableScheduling	//开启定时任务
@ImportResource(locations={"classpath:mykaptcha.xml"})
@MapperScan("com.charity.mapper")//自动扫描mapper
public class Application {
	public static void main(String[] charity) {
		SpringApplication.run(Application.class, charity);
	}


	@Value("${adai.file_url}")
	private String fileUrl;

	@Bean
	MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setLocation(fileUrl);
		return factory.createMultipartConfig();
	}
}
