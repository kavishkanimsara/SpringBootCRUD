package com.example.firstspring;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FirstSpringApplication {

    public static void main(String[] args) {SpringApplication.run(FirstSpringApplication.class, args);}
    /* Add Model Dto mapper*/
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
