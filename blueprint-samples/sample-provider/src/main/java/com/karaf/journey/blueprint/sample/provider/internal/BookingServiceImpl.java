package com.karaf.journey.blueprint.sample.provider.internal;

import com.karaf.journey.blueprint.sample.common.Booking;
import com.karaf.journey.blueprint.sample.common.BookingService;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class BookingServiceImpl implements BookingService {
  private final Map<Long, Booking> bookings = new HashMap<>();

  @Override
  public List<Booking> bookListing() {
    return new LinkedList<>(this.bookings.values());
  }

  @Override
  public Booking getBookingById(Long id) {
    return this.bookings.get(id);
  }

  @Override
  public void add(Booking booking) {
    this.bookings.put(booking.getId(), booking);
  }
}
