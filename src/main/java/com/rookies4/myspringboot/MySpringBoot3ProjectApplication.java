package com.rookies4.myspringboot;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MySpringBoot3ProjectApplication {

	public static void main(String[] args) {
//		SpringApplication.run(MySpringBoot3ProjectApplication.class, args);

        SpringApplication application = new SpringApplication(MySpringBoot3ProjectApplication.class);
        //Application 타입을 변경하기
        application.setWebApplicationType(WebApplicationType.SERVLET);
        application.run(args);
	}

    @Bean
    public String hello() {
        return "Hello SpringBoot";
    }

}
