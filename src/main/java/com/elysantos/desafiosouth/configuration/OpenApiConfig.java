package com.elysantos.desafiosouth.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
  @Bean
  public OpenAPI customOpenAPI(@Value("${springdoc.version}") String appVersion,
                               @Value("${springdoc.description}") String appDescription,
                               @Value("${springdoc.title}") String appTitle) {
    return new OpenAPI().info(new Info().title(appTitle)
        .version(appVersion)
        .description(appDescription));
  }
}
