package com.karaf.journey.osgi.starter.producer.impl;

import com.karaf.journey.osgi.starter.producer.RequestResponseApi;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RequestResponseService implements RequestResponseApi {
  @Override
  public String getResponse(String request) {
    log.info("!!! RequestResponseService invoked !!!");
    return ("You called the service with " + request);
  }
}
