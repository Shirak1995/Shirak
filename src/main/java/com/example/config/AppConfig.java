package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {

    @Bean
    @Scope("singleton")
    public MySingletonBean mySingletonBean() {
        return new MySingletonBean();
    }

    @Bean
    @Scope("prototype")
    public MyPrototypeBean myPrototypeBean() {
        return new MyPrototypeBean();
    }
}

class MySingletonBean {
    public MySingletonBean() {
        System.out.println("MySingletonBean created");
    }
}

class MyPrototypeBean {
    public MyPrototypeBean() {
        System.out.println("MyPrototypeBean created java");
    }
}
