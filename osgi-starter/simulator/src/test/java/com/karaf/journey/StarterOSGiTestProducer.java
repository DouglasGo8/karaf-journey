package com.karaf.journey;

import com.karaf.journey.osgi.starter.producer.RequestResponseApi;
import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.Configuration;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.spi.reactors.AllConfinedStagedReactorFactory;
import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy;
import org.osgi.framework.BundleContext;

import javax.inject.Inject;

import static org.junit.Assert.*;
import static org.ops4j.pax.exam.CoreOptions.*;
import static org.ops4j.pax.exam.OptionUtils.combine;

@RunWith(JUnit4ClassRunner.class)
@ExamReactorStrategy(value = AllConfinedStagedReactorFactory.class)
public class StarterOSGiTestProducer {
  //
  @Inject
  BundleContext bundleContext = null;

  @Inject
  private RequestResponseApi request;

  @Configuration
  public Option[] config() {
    Option[] options = combine(options(junitBundles(), mavenBundle("starter", "producer")));

    return options;
  }

  @Test
  public void testServiceRegistration() {
    assertNotNull(request);
    assertEquals("You called the service with ding", request.getResponse("ding"));
  }

}
