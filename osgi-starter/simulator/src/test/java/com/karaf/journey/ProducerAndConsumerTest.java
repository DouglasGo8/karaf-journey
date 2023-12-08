package com.karaf.journey;


import com.karaf.journey.osgi.starter.producer.RequestResponseApi;
import lombok.extern.slf4j.Slf4j;
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


@Slf4j
@RunWith(JUnit4ClassRunner.class)
@ExamReactorStrategy(value = AllConfinedStagedReactorFactory.class)
public class ProducerAndConsumerTest {
  @Inject
  BundleContext bundleContext = null;

  @Inject
  private RequestResponseApi request;

  @Configuration
  public Option[] config() {
    Option[] options = combine(options(mavenBundle("org.osgi", "org.osgi.compendium"),
            mavenBundle("asm", "asm-all"),
            mavenBundle("org.apache.aries", "org.apache.aries.util").versionAsInProject(),
            mavenBundle("org.apache.aries.blueprint", "org.apache.aries.blueprint").versionAsInProject(),
            mavenBundle("org.apache.aries.proxy", "org.apache.aries.proxy").versionAsInProject(),
            mavenBundle("asm", "asm-all"),

            junitBundles(), mavenBundle("starter", "producer").versionAsInProject(),
            mavenBundle("starter", "consumer").versionAsInProject()));

    return options;
  }

  @Test
  public void testServiceRegistration() {
    assertNotNull(request);
    assertEquals("You called the service with ding", request.getResponse("ding"));
  }

}
