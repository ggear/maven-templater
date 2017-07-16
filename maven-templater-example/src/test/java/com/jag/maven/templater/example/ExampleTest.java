package com.jag.maven.templater.example;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class ExampleTest {

  @Test
  public void testLibrary() {
    assertEquals("1,2,3", Example.example(Arrays.asList("1", "2", "3")));
  }

}
