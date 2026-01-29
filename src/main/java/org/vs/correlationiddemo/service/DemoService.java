package org.vs.correlationiddemo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class DemoService {

  private static final Logger log = LoggerFactory.getLogger(DemoService.class);

  @Async
  public void runAsyncTask() {
    log.info("Running async task");
    try {
      Thread.sleep(500);
    } catch (InterruptedException ignored) {}
    log.info("Async task completed");
  }
}
