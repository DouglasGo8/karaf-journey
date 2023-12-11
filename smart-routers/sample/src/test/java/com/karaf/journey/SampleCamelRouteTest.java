package com.karaf.journey;

import org.apache.camel.test.blueprint.CamelBlueprintTestSupport;
import org.junit.Test;


public class SampleCamelRouteTest extends CamelBlueprintTestSupport {

  @Override
  protected String getBlueprintDescriptor() {
    return "/OSGI-INF/blueprint/blueprint.xml";
  }

  @Test
  public void testSampleContextRoute() throws InterruptedException {
    super.getMockEndpoint("mock:result").expectedMinimumMessageCount(1);
    //
    assertMockEndpointsSatisfied();
  }

}
