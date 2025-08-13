package com.rookies4.myspringboot.runner;

import com.rookies4.myspringboot.property.MyBootProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import java.util.function.Consumer;

@Component
public class MyPropsRunner implements ApplicationRunner {
    @Value("${myboot.name}")
    private String name;

    @Value("${myboot.age}")
    private int age;

    @Autowired
    private Environment environment;

    @Autowired
    private MyBootProperties properties;

    private Logger logger = LoggerFactory.getLogger(MyPropsRunner.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("MyBootProperties.getName() = " + properties.getName());
        System.out.println("MyBootProperties.getAge() = " + properties.getAge());
        System.out.println("MyBootProperties.getFullName() = " + properties.getFullName());

        logger.info("MyBootProperties.getName() = {}", properties.getName());
        logger.info("MyBootProperties.getAge() = {}", properties.getAge());
        logger.info("MyBootProperties.getFullName() = {}", properties.getFullName());

        System.out.println("Properties myboot.name = " + name);
        System.out.println("Properties myboot.age = " + age);
        System.out.println("Properties myboot.fullName = " + environment.getProperty("myboot.fullName"));
        System.out.println(environment.getProperty("myboot.fullName"));

        System.out.println("VM Arguments = " + args.containsOption("foo"));
        System.out.println("Program Arguments = " + args.containsOption("bar"));

        for(String argName: args.getOptionNames()){
            System.out.println("아규먼트 이름 = " + argName);
        }

        args.getOptionNames().forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("Inner Class 아규먼트 이름 =  = " + s);
            }
        });

        args.getOptionNames().forEach(name -> System.out.println(name));

        // argument 생략
        args.getOptionNames().forEach(System.out::println);
    }
}