package com.karaf.journey.blueprint.sample.common;

import java.util.List;

public interface BookingService {
  List<Booking> bookListing();
  Booking getBookingById(Long id);
  void add(Booking booking);
}
