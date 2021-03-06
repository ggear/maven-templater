/*
***
# **PRE-PROCESSED SCRIPT - EDITS WILL BE CLOBBERED BY MAVEN BUILD**

This file is in the *SCRIPT* pre-processed state with template available by the
same package and file name under the modules src/main/template directory.

When editing the template directly (as indicated by the presence of the
TEMPLATE.PRE-PROCESSOR.RAW_TEMPLATE tag at the top of this file), care should
be taken to ensure the maven-resources-plugin generate-sources filtering of the
TEMPLATE.PRE-PROCESSOR tags, which comment and or uncomment blocks of the
template, leave the file in a consistent state, as a script ot library,
post filtering.

It is desirable that in template form, the file remains both compilable and
runnable as a script in your IDEs (eg Eclipse, IntelliJ, CDSW etc). To setup
your environment, it may be necessary to run the pre-processed script once
(eg to execute AddJar commands with dependency versions completely resolved) but
from then on the template can be used for direct editing and distribution via
the source code control system.

The library can be tested during the standard maven compile and test phases.

Note that pre-processed files will be overwritten as part of the Maven build
process. Care should be taken to either ignore and not edit these files (eg
libraries) or check them in and note changes post Maven build (eg scripts)
***
*/


// Note that if the below URL includes a SNAPSHOT version, this file has not
// been pre-processed from a RELEASE and the URL should be manually updated
// to a SNAPSHOT, timestamped version. If you are looking for an example, best
// to checkout a release, https://github.com/ggear/maven-templater/releases
// @formatter:off
/*
%AddJar http://macmini-delila:8071/artifactory/libs-release-local/com/jag/maven-templater/maven-templater-assembly/1.3.2-SNAPSHOT/maven-templater-assembly-1.3.2-SNAPSHOT.jar
*/
// @formatter:on

/*
**IGNORE LIBRARY BOILERPLATE - START**

package com.jag.maven.templater.example

object Example {

  def example(tokens: java.util.List[String]): String = {

**IGNORE LIBRARY BOILERPLATE - FINISH**
*/

import com.jag.maven.templater.TemplaterUtil

//
// Add dependencies dynamically
//
//@formatter:off
kernel.magics.addJar(
TemplaterUtil.getDepJar(
  "com.jag.maven-templater", "maven-templater-example", "1.3.2-SNAPSHOT", "jar-with-dependencies",
  "http://macmini-delila:8071/artifactory/libs-release-local",
  "http://macmini-delila:8071/artifactory/libs-snapshot-local")
//
    + " -f")
//@formatter:on
//

import java.io.StringReader

import com.jag.maven.templater.example.util.ExampleUtil
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

//
// Adding a silly comment on its own line

/*
***
A silly example that takes a Java List of Strings, forms a single CSV of them,
then parses each String out again using CSVFormat and then finally builds
and returns a new CSV String from the tokens - I told you it was silly!
***
*/

val tokensProcessed = ListBuffer[String]()
CSVFormat.RFC4180.parse(new StringReader(tokens.asScala.mkString(ExampleUtil.Delimiter))).asScala.foreach { record =>
  record.asScala.foreach { token => tokensProcessed += token }
}
val tokensString = tokensProcessed.mkString(ExampleUtil.Delimiter)
tokensString

//
// Test the result
if (tokens.asScala.mkString(",") != tokensString) throw new AssertionError("Expected [" + tokens.asScala.mkString(",") + "] but got [" +
  tokensString + "]")
//

/*
**IGNORE LIBRARY BOILERPLATE - START**

  }
}

**IGNORE LIBRARY BOILERPLATE - FINISH**
*/
