package com.elysantos.desafiosouth.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EnvConfig {
  @Bean
  public String externalValidator(@Value("${external-validator}") String externalValidator){
    return externalValidator;
  }
}
