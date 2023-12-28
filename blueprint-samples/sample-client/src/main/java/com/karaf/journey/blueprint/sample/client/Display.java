package com.karaf.journey.blueprint.sample.client;

import com.karaf.journey.blueprint.sample.common.Booking;

public class Display {
  private ClientService clientService;

  private BookingDisplayThread thread;
  private boolean bookingThreadStarted = false;

  /**
   * This setter is used by Blueprint to inject the client service.
   */
  public void setClientService(ClientService clientService) {
    this.clientService = clientService;
  }

  /**
   * Init method used to start the thread.
   */
  public void init() {
    BookingDisplayThread thread = new BookingDisplayThread(clientService);
    thread.setDaemon(true);
    thread.start();
  }

  /**
   * Destroy method used to stop the thread.
   */
  public void destroy() {
    thread.terminate();
  }


  private static final class BookingDisplayThread extends Thread {
    private ClientService clientService;
    private volatile boolean running = true;

    public BookingDisplayThread(ClientService clientService) {
      this.clientService = clientService;
    }

    @Override
    public void run() {
      while (running) {
        try {
          // TODO test
          final Booking booking = new Booking("John Doo", "AF3030");
          clientService.addBooking(booking);
          //System.out.println("**** FIRED ****");
          System.out.println(displayBookings());
          Thread.sleep(5000);
        } catch (Exception ex) {

        }
      }
    }

    private String displayBookings() {
      StringBuilder builder = new StringBuilder();
      for (Booking booking : clientService.bookings()) {
        builder.append(booking.getId())
                .append(" | ")
                .append(booking.getCustomer())
                .append(" | ")
                .append(booking.getFlight()).append("\n");
      }
      return builder.toString();
    }

    public void terminate() {
      running = false;
    }

  }
}
