package com.karaf.journey.osgi.starter.consumer;

import com.karaf.journey.osgi.starter.producer.RequestResponseApi;

import java.util.Timer;

public class SimpleResponseConsumer {

  private RequestResponseApi requestResponseApi;

  public void init() {
    var timer = new Timer();
    //
    timer.scheduleAtFixedRate(new ConsoleResponder(requestResponseApi), 500l, 5000l);
  }

  public void setRequest(RequestResponseApi request) {
    this.requestResponseApi = request;
  }
}
