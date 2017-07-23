package com.jag.maven.templater.example;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import com.jag.maven.templater.ScriptUtil;
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
    String file = "Example.scala";
    String directory = "src/main/script/scala/com/jag/maven/templater/example";
    StringBuffer output = new StringBuffer();
    int exit = ScriptUtil.executeScriptScala(file, directory, "target/test-script/scala", output);
    LOG.info("Executing [" + new File(directory,  file).toString() + "]:\n" + output.toString());
    assertEquals(0, exit);
  }

}
