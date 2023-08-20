package com.atmapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.atmapplication.Model.AccountModel;

@SpringBootApplication
@ComponentScan(basePackages = {"com.atmapplication.Controller"})
public class AtmApplication {

    public static void main(String[] args) {
        AccountModel acc = new AccountModel();
        SpringApplication.run(AtmApplication.class, args);
        System.out.println("Hello World");
    }
}
