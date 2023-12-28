package com.karaf.journey.blueprint.sample.client;

import com.karaf.journey.blueprint.sample.common.Booking;
import com.karaf.journey.blueprint.sample.common.BookingService;

import java.util.List;

public class ClientServiceImpl implements ClientService{
  private BookingService bookingService;
  @Override
  public List<Booking> bookings() {
    return this.bookingService.bookListing();
  }

  @Override
  public void addBooking(Booking booking) {
    this.bookingService.add(booking);
  }

  public BookingService getBookingService() {
    return bookingService;
  }

  public void setBookingService(BookingService bookingService) {
    this.bookingService = bookingService;
  }
}
