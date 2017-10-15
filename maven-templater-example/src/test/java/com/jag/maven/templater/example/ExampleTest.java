package com.jag.maven.templater.example;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import com.jag.maven.templater.TemplaterUtil;
import org.apache.log4j.Logger;
import org.junit.Test;

public class ExampleTest {

  private static final Logger LOG = Logger.getLogger(ExampleTest.class);

  @Test
  public void testScalaLibrary() {
    assertEquals("1,2,3", Example.example(Arrays.asList("1", "2", "3")));
  }

  @Test
  public void testScalaScript() throws IOException, InterruptedException {
    StringBuffer output = new StringBuffer();
    int exit = TemplaterUtil.executeScriptScala(scala.Option.apply(null), scala.Option.apply(null), new File
        ("src/main/script/scala/com/jag/maven/templater/example/Example.scala"),
      scala.Option.apply(null), new File("target/test-script/scala"), scala.Option.apply(null), scala.Option.apply(output));
    LOG.info(output.toString());
    assertEquals(0, exit);
  }

}
