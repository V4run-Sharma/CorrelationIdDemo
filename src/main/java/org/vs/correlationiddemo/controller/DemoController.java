package org.vs.correlationiddemo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.vs.correlationiddemo.service.DemoService;

@RestController
@RequestMapping("/demo")
public class DemoController {

  private static final Logger log = LoggerFactory.getLogger(DemoController.class);

  private final RestTemplate restTemplate;
  private final WebClient.Builder webClientBuilder;
  private final DemoService demoService;

  public DemoController(RestTemplate restTemplate,
                        WebClient.Builder webClientBuilder,
                        DemoService demoService) {
    this.restTemplate = restTemplate;
    this.webClientBuilder = webClientBuilder;
    this.demoService = demoService;
  }

  @GetMapping("/sync")
  public String sync() {
    log.info("Handling /sync request");

    // RestTemplate call (to same app for simplicity)
    log.info("Making RestTemplate call to /downstream");
    String restResult = restTemplate.getForObject(
        "http://localhost:8090/demo/downstream",
        String.class
    );

    // Async task
    log.info("Starting async task");
    demoService.runAsyncTask();

    // WebClient call
    log.info("Making WebClient call to /downstream");
    String webClientResult = webClientBuilder.build()
        .get()
        .uri("http://localhost:8090/demo/downstream")
        .retrieve()
        .bodyToMono(String.class)
        .block();

    log.info("sync-ok | rest={} | webclient={}", restResult, webClientResult);
    return "sync-ok | rest=" + restResult + " | webclient=" + webClientResult;
  }

  @GetMapping("/downstream")
  public String downstream() {
    log.info("Handling /downstream request");
    return "downstream-ok";
  }
}
