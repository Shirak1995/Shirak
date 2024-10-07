package ru.vtvhw;

import org.springframework.context.support.GenericGroovyApplicationContext;

public class Application {
    public static void main(String[] args) {
        // Spring Groovy Configuration
        GenericGroovyApplicationContext groovyContext = new GenericGroovyApplicationContext("classpath:config/application.groovy");
        groovyContext.getBean("mySingletonBean");
        groovyContext.getBean("myPrototypeBean");
    }
}