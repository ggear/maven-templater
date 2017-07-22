package com.jag.maven.templater

import scala.collection.mutable.ArrayBuffer
import scala.io.Source

object ScriptUtil {

  def getDepJar(group: String, artifact: String, version: String, classifier: String, releaseRepo: String, snapshotRepo: String): String = {
    val isSnapshot = version.endsWith("SNAPSHOT")
    val artifactUrl = (if (isSnapshot) snapshotRepo else releaseRepo) + "/" + group.replace(".", "/") + "/" + artifact + "/" + version
    val jarUrls = ArrayBuffer.empty[String]
    if (isSnapshot)
      """<a href="(.*)">""".r.findAllIn(Source.fromURL(artifactUrl).mkString).toList.map(anchor =>
        if (anchor.endsWith((if (classifier == null || classifier == "") "" else classifier) + ".jar\">")) jarUrls += anchor.substring(9, anchor.length - 2))
    artifactUrl + "/" + (if (jarUrls.nonEmpty) jarUrls.sortWith(_ > _)(0) else artifact + "-" + version + (if (classifier == null || classifier == "") "" else "-" + classifier) + ".jar")
  }

}
