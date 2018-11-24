package com.escolar.persona;

import org.apache.catalina.mapper.Mapper;
import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersonaConfiguracion {
    @Bean 
    public DozerBeanMapper beanMapper() {
        return new DozerBeanMapper();  
    }
}
