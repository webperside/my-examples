package com.hamidsultanzadeh.ticketservice.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.hamidsultanzadeh")
public class CustomBeanFactory {

    @Bean
    public ModelMapper getModelMapper(){
        return new ModelMapper();
    }
}
