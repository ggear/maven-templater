package com.jag.maven.templater.example;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Arrays;

import org.junit.Test;

public class ExampleTest {

  @Test
  public void testLibrary() {
    assertEquals("1,2,3", Example.example(Arrays.asList("1", "2", "3")));
  }

  @Test
  public void testScript() throws IOException, InterruptedException {
    assertEquals(0, executeScript("src/main/script/scala/com/jag/maven/templater/example/Example.scala"));
    assertEquals(0, executeScript("src/main/template/scala/com/jag/maven/templater/example/Example.scala"));
  }

  private int executeScript(String script) throws IOException, InterruptedException {
    return new ProcessBuilder(Arrays.asList(
      "scala",
      "-cp",
      System.getProperty("surefire.test.class.path") == null ? System.getProperty("java.class.path") : System.getProperty("java.class.path"),
      script)
    ).start().waitFor();
  }

}
