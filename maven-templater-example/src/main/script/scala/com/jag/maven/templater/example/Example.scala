/****
# **PRE-PROCESSED SCRIPT - DO NOT EDIT**

This file is in the *SCRIPT* pre-processed state with template available by the
same package and file name under the modules src/main/template directory.

When editing the template directly (as indicated by the presence of the
TEMPLATE.PRE-PROCESSOR.RAW_TEMPLATE tag at the top of this file), care should
be taken to ensure the maven-resources-plugin generate-sources filtering of the
TEMPLATE.PRE-PROCESSOR tags, which comment and or uncomment blocks of the
template, leave the file in a consistent state post filtering. It is desirable
that in template form, the file remains runnable and or compilable as a script
or library for ease of editing.
****/

/*
%AddJar https://repo.maven.apache.org/maven2/org/apache/commons/commons-csv/1.4/commons-csv-1.4.jar
*/

/***IGNORE LIBRARY BOILERPLATE - START**
package com.jag.maven.templater.example

import java.util

object Example {

  def example(tokens: util.List[String]): String = {
**IGNORE LIBRARY BOILERPLATE - FINISH***/

import java.io.StringReader

import org.apache.commons.csv.CSVFormat

import scala.collection.JavaConverters._
import scala.collection.mutable.ListBuffer

//
// Provide example parameters
val tokens = new java.util.ArrayList[String]()
tokens.add("1")
tokens.add("2")
tokens.add("3")
//

/****
A silly example that takes a Java List of Strings, forms a single CSV of them,
then parses each String out again using CSVFormat and then finally builds
and returns a new CSV String from the tokens - I told you it was silly!
****/

val tokensProcessed = ListBuffer[String]()
CSVFormat.RFC4180.parse(new StringReader(tokens.asScala.mkString(","))).asScala.foreach { record =>
  record.asScala.foreach { token => tokensProcessed += token }
}
tokensProcessed.mkString(",")

/***IGNORE LIBRARY BOILERPLATE - START**
  }
}
**IGNORE LIBRARY BOILERPLATE - FINISH***/
