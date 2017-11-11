package com.jag.maven.templater

import java.io.{File, PrintWriter}

import scala.collection.JavaConverters._
import scala.collection.mutable.ArrayBuffer
import scala.io.Source
import scala.util.control
import java.io.{File, FileInputStream, FileOutputStream}

object TemplaterUtil {

  def PREAMBLE_SCALA = "\n\nobject kernel { object magics { def addJar(dep:String): Unit = {} }}\n\n"

  def getDepJar(group: String, artifact: String, version: String, classifier: String, releaseRepo: String, snapshotRepo: String): String = {
    val jarNames = ArrayBuffer.empty[String]
    val baseUrl = (if (version.endsWith("SNAPSHOT")) snapshotRepo else releaseRepo) + "/" + group.replace(".", "/") + "/" + artifact +
      "/" + version
    if (version.endsWith("SNAPSHOT"))
      control.Exception.ignoring(classOf[Exception]) {
        """<a href="(.*)">""".r.findAllIn(Source.fromURL(baseUrl).mkString).toList.map(anchor =>
          if (anchor.endsWith((if (classifier == null || classifier == "") "" else classifier) + ".jar\">")) jarNames +=
            anchor.substring(9, anchor.length - 2))
      }
    baseUrl + "/" + (if (jarNames.nonEmpty) jarNames.sortWith(_ > _)(0) else artifact + "-" + version +
      (if (classifier == null || classifier == "") "" else "-" + classifier) + ".jar")
  }

  def executeScriptScala(environment: Option[collection.Map[String, String]], environmentDrop: Option[collection.Seq[String]],
                         virtualMachine: Option[String], file: File, parameters: Option[collection.Seq[String]], working: File,
                         preamble: Option[String], output: Option[StringBuffer]): Int = {
    var classpath = if (System.getProperty("surefire.test.class.path") == null) System.getProperty("java.class.path") else System
      .getProperty("java.class.path")
    classpath = if (classpath != null) classpath.replace(" ", "\\ ") else ""
    executeScript(List(virtualMachine.getOrElse("scala"), "-cp", classpath, working.getAbsolutePath + File.separator + file.getName) ++
      parameters.getOrElse(List.empty), environment, environmentDrop, file, working, Some(PREAMBLE_SCALA + preamble.getOrElse("")), output)
  }

  def executeScriptPython(environment: Option[collection.Map[String, String]], environmentDrop: Option[collection.Seq[String]],
                          virtualMachine: Option[String], file: File, parameters: Option[collection.Seq[String]], working: File,
                          preamble: Option[String], output: Option[StringBuffer]): Int = {
    executeScript(List(virtualMachine.getOrElse("scala"), working.getAbsolutePath + File.separator + file.getName) ++ parameters
      .getOrElse(List.empty), environment, environmentDrop, file, working, preamble, output)
  }

  def executeScript(command: collection.Seq[String], environment: Option[collection.Map[String, String]],
                    environmentDrop: Option[collection.Seq[String]], file: File, working: File,
                    preamble: Option[String], output: Option[StringBuffer]): Int = {
    delete(working)
    working.mkdirs()
    val fileExecute = new File(working, file.getName)
    new PrintWriter(fileExecute.getAbsolutePath) {
      write(preamble.getOrElse("") + "\n" + Source.fromFile(file).mkString)
      close()
    }
    file.getParentFile.listFiles.filter(_.isFile).toList.filter(additionalFile =>
      !file.getName.equals(additionalFile.getName)).foreach(additionalFile =>
      new FileOutputStream(new File(working, additionalFile.getName))
        getChannel() transferFrom(new FileInputStream(additionalFile) getChannel, 0, Long.MaxValue))
    if (!fileExecute.canExecute) fileExecute.setExecutable(true)
    val processBuilder = new ProcessBuilder(command.asJava).directory(working).redirectErrorStream(true)
    processBuilder.environment().putAll(environment.getOrElse(Map()).asJava)
    for ((environmentVariable) <- environmentDrop.getOrElse(List())) processBuilder.environment().remove(environmentVariable)
    val process = processBuilder.start()
    process.getOutputStream.close()
    val exit = process.waitFor
    if (output.isDefined) output.get.append(command.mkString(" ")).append(":\n").append(Source.fromInputStream(process.getInputStream)
      .mkString)
    exit
  }

  private def delete(directory: File): Array[(String, Boolean)] = {
    Option(directory.listFiles).map(_.flatMap(f => delete(f))).getOrElse(Array()) :+ (directory.getPath -> directory.delete)
  }

}
