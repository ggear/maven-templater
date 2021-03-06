package com.jag.maven.templater

import org.scalatest.{FlatSpec, Matchers}

class TemplaterUtilTest extends FlatSpec with Matchers {

  "A released dependency, with classifier" should "be resolvable" in {
    assert(
      TemplaterUtil.getDepJar("com.jag.maven-templater", "maven-templater-example", "1.0.8", "jar-with-dependencies", "http://52.63.86" +
        ".162/artifactory/cloudera-framework-releases", "http://macmini-delila:8071/artifactory/libs-snapshots-local")
        ==
        "http://52.63.86.162/artifactory/cloudera-framework-releases/com/jag/maven-templater/maven-templater-example/1.0" +
          ".8/maven-templater-example-1.0.8-jar-with-dependencies.jar"
    )
  }

  "A released dependency, without classifier" should "be resolvable" in {
    assert(
      TemplaterUtil.getDepJar("org.apache.commons", "commons-csv", "1.4", "", "https://repo1.maven.org/maven2", "https://repo1.maven" +
        ".org/maven2")
        ==
        "https://repo1.maven.org/maven2/org/apache/commons/commons-csv/1.4/commons-csv-1.4.jar"
    )
    assert(
      TemplaterUtil.getDepJar("com.jag.maven-templater", "maven-templater-assembly", "1.0.7", "", "http://52.63.86" +
        ".162/artifactory/cloudera-framework-releases", "http://macmini-delila:8071/artifactory/libs-snapshots-local")
        ==
        "http://52.63.86.162/artifactory/cloudera-framework-releases/com/jag/maven-templater/maven-templater-assembly/1.0" +
          ".7/maven-templater-assembly-1.0.7.jar"
    )
    assert(
      TemplaterUtil.getDepJar("com.cloudera.framework.example", "cloudera-framework-example-3", "1.5.5-cdh5.11.1", "", "http://52.63.86" +
        ".162/artifactory/cloudera-framework-releases", "http://macmini-delila:8071/artifactory/libs-snapshots-local")
        ==
        "http://52.63.86.162/artifactory/cloudera-framework-releases/com/cloudera/framework/example/cloudera-framework-example-3/1.5" +
          ".5-cdh5.11.1/cloudera-framework-example-3-1.5.5-cdh5.11.1.jar"
    )
    assert(
      TemplaterUtil.getDepJar("com.cloudera.framework.example", "cloudera-framework-example-3", "1.5.5-cdh5.12.0", "", "http://52.63.86" +
        ".162/artifactory/cloudera-framework-releases", "http://macmini-delila:8071/artifactory/libs-snapshots-local")
        ==
        "http://52.63.86.162/artifactory/cloudera-framework-releases/com/cloudera/framework/example/cloudera-framework-example-3/1.5" +
          ".5-cdh5.12.0/cloudera-framework-example-3-1.5.5-cdh5.12.0.jar"
    )
  }

  "A snapshot dependency, with classifier" should "be resolvable" in {
    assert(
      TemplaterUtil.getDepJar("com.jag.maven-templater", "maven-templater-example", "1.0.8-SNAPSHOT", "jar-with-dependencies", "http://52" +
        ".63.86.162/artifactory/cloudera-framework-releases", "http://macmini-delila:8071/artifactory/libs-snapshots-local")
        ==
        "http://macmini-delila:8071/artifactory/libs-snapshots-local/com/jag/maven-templater/maven-templater-example/1.0" +
          ".8-SNAPSHOT/maven-templater-example-1.0.8-SNAPSHOT-jar-with-dependencies.jar"
    )
  }

}
