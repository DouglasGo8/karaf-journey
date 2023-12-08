package com.karaf.journey;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HelloBean implements Hello {
  private String say = "Hello World";

  @Override
  public String hello() {
    var sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    return say + " at " + sdf.format(new Date());
  }

  public String getSay() {
    return say;
  }

  public void setSay(String say) {
    this.say = say;
  }
}
