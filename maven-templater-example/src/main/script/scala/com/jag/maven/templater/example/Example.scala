/*
***
# **PRE-PROCESSED SCRIPT - DO NOT EDIT**

This file is in the *SCRIPT* pre-processed state with template available by the
same package and file name under the modules src/main/template directory.

When editing the template directly (as indicated by the presence of the
TEMPLATE.PRE-PROCESSOR.RAW_TEMPLATE tag at the top of this file), care should
be taken to ensure the maven-resources-plugin generate-sources filtering of the
TEMPLATE.PRE-PROCESSOR tags, which comment and or uncomment blocks of the
template, leave the file in a consistent state post filtering.

It is desirable that in template form, the file remains both compilable and
runnable as a script in your IDEs (eg Eclipse, IntelliJ, CDSW etc). To setup
your environment, it may be necessary to run the pre-processed script once
(eg to execute AddJar commands with dependency versions completely resolved) but
from then on the template can be used for direct editing and distribution via
the source code control system.

The library can be tested during the standard maven compile and test phases.
***
*/

/*
%AddJar http://52.63.86.162/artifactory/cloudera-framework-releases/com/jag/maven-templater/maven-templater-assembly/1.1.0-SNAPSHOT/maven-templater-assembly-1.1.0-SNAPSHOT.jar
*/

/*
**IGNORE LIBRARY BOILERPLATE - START**

package com.jag.maven.templater.example

import java.util

object Example {

  def example(tokens: util.List[String]): String = {

**IGNORE LIBRARY BOILERPLATE - FINISH**
*/

import java.io.StringReader

import com.jag.maven.templater.ScriptUtil

import scala.collection.JavaConverters._
import scala.collection.mutable.ListBuffer

//
// Add dependencies dynamically
//
//@formatter:off
kernel.magics.addJar(
ScriptUtil.getDepJar(
  "com.jag.maven-templater",
  "maven-templater-example",
  "1.1.1-SNAPSHOT",
  "jar-with-dependencies",
  "http://52.63.86.162/artifactory/cloudera-framework-releases",
  "http://52.63.86.162/artifactory/cloudera-framework-snapshots"
)
//
    + " -f")
//@formatter:on
//

import org.apache.commons.csv.CSVFormat

//
// Provide example parameters
val tokens = new java.util.ArrayList[String]()
tokens.add("1")
tokens.add("2")
tokens.add("3")
//

/*
***
A silly example that takes a Java List of Strings, forms a single CSV of them,
then parses each String out again using CSVFormat and then finally builds
and returns a new CSV String from the tokens - I told you it was silly!
***
*/

val tokensProcessed = ListBuffer[String]()
CSVFormat.RFC4180.parse(new StringReader(tokens.asScala.mkString(","))).asScala.foreach { record =>
  record.asScala.foreach { token => tokensProcessed += token }
}
val tokensString = tokensProcessed.mkString(",")
tokensString

//
// Test the result
if (tokens.asScala.mkString(",") != tokensString) throw new AssertionError("Expected [" + tokens.asScala.mkString(",") + "] but got [" + tokensString + "]")
//

/*
**IGNORE LIBRARY BOILERPLATE - START**

  }
}

**IGNORE LIBRARY BOILERPLATE - FINISH**
*/
