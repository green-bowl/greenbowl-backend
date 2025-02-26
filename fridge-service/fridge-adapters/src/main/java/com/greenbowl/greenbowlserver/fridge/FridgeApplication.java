package com.greenbowl.greenbowlserver.fridge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class FridgeApplication {
    public static void main(String[] args) {
        SpringApplication.run(FridgeApplication.class, args);
    }
}
