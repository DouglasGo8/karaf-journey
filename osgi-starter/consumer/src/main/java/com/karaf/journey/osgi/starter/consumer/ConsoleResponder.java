package com.karaf.journey.osgi.starter.consumer;

import com.karaf.journey.osgi.starter.producer.RequestResponseApi;
import lombok.extern.slf4j.Slf4j;

import java.util.TimerTask;

@Slf4j
public class ConsoleResponder extends TimerTask {

  RequestResponseApi requestResponseApi;

  public ConsoleResponder(RequestResponseApi requestResponseApi) {
    this.requestResponseApi = requestResponseApi;
  }

  @Override
  public void run() {
    log.info("{}", requestResponseApi.getResponse("!! Time is " + System.currentTimeMillis() + " !!"));
  }
}
