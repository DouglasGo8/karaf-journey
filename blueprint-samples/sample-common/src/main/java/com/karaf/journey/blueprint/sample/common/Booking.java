package com.karaf.journey.blueprint.sample.common;


import java.util.concurrent.ThreadLocalRandom;

public class Booking {

  private Long id;
  private String customer;
  private String flight;

  public Booking(String customer, String flight) {
    this.id = ThreadLocalRandom.current().nextLong();
    this.customer = customer;
    this.flight = flight;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCustomer() {
    return customer;
  }

  public void setCustomer(String customer) {
    this.customer = customer;
  }

  public String getFlight() {
    return flight;
  }

  public void setFlight(String flight) {
    this.flight = flight;
  }
}
