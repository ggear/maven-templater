package com.jag.maven.templater

import org.scalatest.{FlatSpec, Matchers}

class ScriptUtilTest extends FlatSpec with Matchers {

  "released, sans classifier, jar" should "be resolvable" in {
    assert(
      ScriptUtil.getDepJar("com.jag.maven-templater", "maven-templater-assembly", "1.0.7", "", "http://52.63.86.162/artifactory/cloudera-framework-releases", "http://52.63.86.162/artifactory/cloudera-framework-snapshots")
        ==
        "http://52.63.86.162/artifactory/cloudera-framework-releases/com/jag/maven-templater/maven-templater-assembly/1.0.7/maven-templater-assembly-1.0.7.jar"
    )
    assert(
      ScriptUtil.getDepJar("com.cloudera.framework.example", "cloudera-framework-example-3", "1.5.5-cdh5.11.1", "", "http://52.63.86.162/artifactory/cloudera-framework-releases", "http://52.63.86.162/artifactory/cloudera-framework-snapshots")
        ==
        "http://52.63.86.162/artifactory/cloudera-framework-releases/com/cloudera/framework/example/cloudera-framework-example-3/1.5.5-cdh5.11.1/cloudera-framework-example-3-1.5.5-cdh5.11.1.jar"
    )
    assert(
      ScriptUtil.getDepJar("com.cloudera.framework.example", "cloudera-framework-example-3", "1.5.5-cdh5.12.0", "", "http://52.63.86.162/artifactory/cloudera-framework-releases", "http://52.63.86.162/artifactory/cloudera-framework-snapshots")
        ==
        "http://52.63.86.162/artifactory/cloudera-framework-releases/com/cloudera/framework/example/cloudera-framework-example-3/1.5.5-cdh5.12.0/cloudera-framework-example-3-1.5.5-cdh5.12.0.jar"
    )
  }

  "snapshot, with classifier, jar" should "be resolvable" in {
    assert(
      ScriptUtil.getDepJar("com.jag.maven-templater", "maven-templater-example", "1.0.8-SNAPSHOT", "jar-with-dependencies", "http://52.63.86.162/artifactory/cloudera-framework-releases", "http://52.63.86.162/artifactory/cloudera-framework-snapshots")
        ==
        "http://52.63.86.162/artifactory/cloudera-framework-snapshots/com/jag/maven-templater/maven-templater-example/1.0.8-SNAPSHOT/maven-templater-example-1.0.8-20170722.080436-4-jar-with-dependencies.jar"
    )
  }

}