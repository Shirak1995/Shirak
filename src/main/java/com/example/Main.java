package com.example;

import com.example.config.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericGroovyApplicationContext;

public class Main {
    public static void main(String[] args) {
        // Spring Java Configuration
        ApplicationContext javaContext = new AnnotationConfigApplicationContext(AppConfig.class);
        javaContext.getBean("mySingletonBean");
        javaContext.getBean("myPrototypeBean");

        // Spring XML Configuration
        ApplicationContext xmlContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        xmlContext.getBean("mySingletonBean");
        xmlContext.getBean("myPrototypeBean");

        // Spring Groovy Configuration
        GenericGroovyApplicationContext groovyContext = new GenericGroovyApplicationContext("classpath:application.groovy");
        groovyContext.getBean("mySingletonBean");
        groovyContext.getBean("myPrototypeBean");
    }
}