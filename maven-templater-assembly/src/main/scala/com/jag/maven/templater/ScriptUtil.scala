package com.jag.maven.templater

import java.io.{File, PrintWriter}
import java.util

import scala.collection.mutable.ArrayBuffer
import scala.io.Source
import scala.util.control

object ScriptUtil {

  def getDepJar(group: String, artifact: String, version: String, classifier: String, releaseRepo: String, snapshotRepo: String): String = {
    val jarNames = ArrayBuffer.empty[String]
    val baseUrl = (if (version.endsWith("SNAPSHOT")) snapshotRepo else releaseRepo) + "/" + group.replace(".", "/") + "/" + artifact + "/" + version
    if (version.endsWith("SNAPSHOT"))
      control.Exception.ignoring(classOf[Exception]) {
        """<a href="(.*)">""".r.findAllIn(Source.fromURL(baseUrl).mkString).toList.map(anchor =>
          if (anchor.endsWith((if (classifier == null || classifier == "") "" else classifier) + ".jar\">")) jarNames += anchor.substring(9, anchor.length - 2))
      }
    baseUrl + "/" + (if (jarNames.nonEmpty) jarNames.sortWith(_ > _)(0) else artifact + "-" + version + (if (classifier == null || classifier == "") "" else "-" + classifier) + ".jar")
  }

  def executeScriptScala(file: String, directory: String, working: String, output: StringBuffer): Int = {
    delete(new File(working))
    new File(working).mkdirs()
    new PrintWriter(working + File.separator + file) {
      write("\nobject kernel { object magics { def addJar(dep:String): Unit = {} }}\n" + Source.fromFile(directory + File.separator + file).mkString);
      close
    }
    val process = new ProcessBuilder(util.Arrays.asList("scala", "-cp",
      if (System.getProperty("surefire.test.class.path") == null) System.getProperty("java.class.path")
      else System.getProperty("java.class.path"),
      working + File.separator + file)).start()
    process.getOutputStream.close()
    val scriptExit = process.waitFor
    if (output != null) {
      output.append(Source.fromInputStream(process.getInputStream).mkString)
      output.append(Source.fromInputStream(process.getErrorStream).mkString)
    }
    scriptExit
  }

  private def delete(directory: File): Array[(String, Boolean)] = {
    Option(directory.listFiles).map(_.flatMap(f => delete(f))).getOrElse(Array()) :+ (directory.getPath -> directory.delete)
  }

}
