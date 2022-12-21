package com.elysantos.desafiosouth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class DesafioSouthApplication {

  public static void main(String[] args) {
    SpringApplication.run(DesafioSouthApplication.class, args);
  }

}
