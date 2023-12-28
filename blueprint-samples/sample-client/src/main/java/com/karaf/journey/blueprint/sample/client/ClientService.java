package com.karaf.journey.blueprint.sample.client;

import com.karaf.journey.blueprint.sample.common.Booking;

import java.util.List;

public interface ClientService {
  List<Booking> bookings();

  void addBooking(Booking booking);
}
