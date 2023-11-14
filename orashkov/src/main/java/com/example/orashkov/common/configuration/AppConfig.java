package com.example.orashkov.common.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class AppConfig {

    // for more convenient mapping
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
