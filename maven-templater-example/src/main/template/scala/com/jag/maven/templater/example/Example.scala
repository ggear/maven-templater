/*
${TEMPLATE.PRE-PROCESSOR.SPACER}
${TEMPLATE.PRE-PROCESSOR.RAW_TEMPLATE}

This file is in the ${TEMPLATE.PRE-PROCESSOR.STATE} pre-processed state with template available by the
same package and file name under the modules src/main/template directory.

When editing the template directly (as indicated by the presence of the
TEMPLATE.PRE-PROCESSOR.RAW_TEMPLATE tag at the top of this file), care should
be taken to ensure the maven-resources-plugin generate-sources filtering of the
TEMPLATE.PRE-PROCESSOR tags, which comment and or uncomment blocks of the
template, leave the file in a consistent state, as a script ot library, post filtering.

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
${TEMPLATE.PRE-PROCESSOR.SPACER}
*/


// Note that if the below URL includes a SNAPSHOT version, this file has not
// been pre-processed from a RELEASE and the URL should be manually updated
// to a SNAPSHOT, timestamped version
/*
%AddJar http://52.63.86.162/artifactory/cloudera-framework-releases/com/jag/maven-templater/maven-templater-assembly/${templater.version}/maven-templater-assembly-${templater.version}.jar
*/

/*
${TEMPLATE.PRE-PROCESSOR.UNCLOSE}

package com.jag.maven.templater.example

object Example {

  def example(tokens: java.util.List[String]): String = {

${TEMPLATE.PRE-PROCESSOR.UNOPEN}
*/

import com.jag.maven.templater.TemplaterUtil

//${TEMPLATE.PRE-PROCESSOR.OPEN}
// Add dependencies dynamically
//${TEMPLATE.PRE-PROCESSOR.ADDJAROPEN}
TemplaterUtil.getDepJar(
  "com.jag.maven-templater", "maven-templater-example", "${project.version}", "jar-with-dependencies",
  "http://52.63.86.162/artifactory/cloudera-framework-releases",
  "http://52.63.86.162/artifactory/cloudera-framework-snapshots")
//${TEMPLATE.PRE-PROCESSOR.ADDJARCLOSE}
//${TEMPLATE.PRE-PROCESSOR.CLOSE}

import java.io.StringReader

import com.jag.maven.templater.example.util.ExampleUtil
import org.apache.commons.csv.CSVFormat

import scala.collection.JavaConverters._
import scala.collection.mutable.ListBuffer

//${TEMPLATE.PRE-PROCESSOR.OPEN}
// Provide example parameters
val tokens = new java.util.ArrayList[String]()
tokens.add("1")
tokens.add("2")
tokens.add("3")
//${TEMPLATE.PRE-PROCESSOR.CLOSE}

/*
${TEMPLATE.PRE-PROCESSOR.SPACER}
A silly example that takes a Java List of Strings, forms a single CSV of them,
then parses each String out again using CSVFormat and then finally builds
and returns a new CSV String from the tokens - I told you it was silly!
${TEMPLATE.PRE-PROCESSOR.SPACER}
*/

val tokensProcessed = ListBuffer[String]()
CSVFormat.RFC4180.parse(new StringReader(tokens.asScala.mkString(ExampleUtil.Delimiter))).asScala.foreach { record =>
  record.asScala.foreach { token => tokensProcessed += token }
}
val tokensString = tokensProcessed.mkString(ExampleUtil.Delimiter)
tokensString

//${TEMPLATE.PRE-PROCESSOR.OPEN}
// Test the result
if (tokens.asScala.mkString(",") != tokensString) throw new AssertionError("Expected [" + tokens.asScala.mkString(",") + "] but got [" + tokensString + "]")
//${TEMPLATE.PRE-PROCESSOR.CLOSE}

/*
${TEMPLATE.PRE-PROCESSOR.UNCLOSE}

  }
}

${TEMPLATE.PRE-PROCESSOR.UNOPEN}
*/
