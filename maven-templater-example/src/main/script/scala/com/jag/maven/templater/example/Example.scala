/*
    PRE-PROCESSED SCRIPT, DO NOT EDIT

    This file is in the SCRIPT pre-processed state with template available by the
    same file name and package under the following directory:

    /Users/graham/_/dev/graham/maven-templater/maven-templater-example/src/main/template

    When editing the template directly (as indicated by the presence of the
    ${TEMPLATE.PRE-PROCESSOR.*} tag at the top of this file), care should be
    taken to ensure the maven-resources-plugin generate-sources filtering of
    the ${TEMPLATE.PRE-PROCESSOR.*} tags, which comment and or uncomment blocks
    of the template, leave the file in a consistent state post filtering. It is
    desirable that in template form the file remains runnable and or compilable
    in at least one form (ie script or library) for ease of editing.
*/

/*
%AddJar https://repo.maven.apache.org/maven2/org/apache/commons/commons-csv/1.4/commons-csv-1.4.jar
*/

/* TEMPLATE OPEN COMMENT SCRIPT

package com.jag.maven.templater.example

import java.util

object Example {

  def example(tokens: util.List[String]): String = {

*/

import java.io.StringReader

import org.apache.commons.csv.CSVFormat

import scala.collection.JavaConverters._
import scala.collection.mutable.ListBuffer

// TEMPLATE OPEN UNCOMMENT SCRIPT

// Provide example parameters
var tokens = new java.util.ArrayList[String]()
tokens.add("1")
tokens.add("2")
tokens.add("3")

// TEMPLATE CLOSE UNCOMMENT SCRIPT

val tokensProcessed = ListBuffer[String]()
CSVFormat.RFC4180.parse(new StringReader(tokens.asScala.mkString(","))).asScala.foreach { record =>
  record.asScala.foreach { token => tokensProcessed += token }
}
tokensProcessed.mkString(",")

/* TEMPLATE OPEN COMMENT SCRIPT

  }
}

*/
