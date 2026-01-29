package org.vs.correlationiddemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class CorrelationIdDemoApplication {

  private static final Logger log = LoggerFactory.getLogger(CorrelationIdDemoApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(CorrelationIdDemoApplication.class, args);
    log.info("CorrelationIdDemoApplication started at http://localhost:8090");
  }

}
