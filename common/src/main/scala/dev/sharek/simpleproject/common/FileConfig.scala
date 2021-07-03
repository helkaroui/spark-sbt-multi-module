package dev.sharek.simpleproject.common

import pureconfig.error.ConfigReaderFailures
import pureconfig.generic.auto._
import pureconfig.{ConfigObjectSource, ConfigSource}

case class Application(
                        savePath: String = "",
                        host: String = "localhost",
                        port: Int = 8080
                      )

case class FileConfig(
                       appName: String,
                       sparkConfig: Map[String, String],
                       application: Application
                     )

object FileConfig {
  def apply(fileName: Option[String] = None): FileConfig = {
    val configSource: ConfigObjectSource = fileName match {
      case None => ConfigSource.default
      case Some(name) => ConfigSource.resources(name)
    }

    configSource.load[FileConfig] match {
      case Left(error: ConfigReaderFailures) => throw new Exception("Exception raised when parsing Application.conf file. Please fix " +
        "the following errors to proceed : \n" +
        error.prettyPrint(1))
      case Right(config) => config
    }
  }
}