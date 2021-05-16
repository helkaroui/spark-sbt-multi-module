package dev.sharek.ucc

import pureconfig.ConfigSource
import pureconfig.error.ConfigReaderFailures
import pureconfig.generic.auto._


case class CommonConfig(
                         host: String,
                         port: Int,
                         useHttps: Boolean
                       )

object CommonConfig {
  def apply(): CommonConfig =
    ConfigSource.default.load[CommonConfig] match {
      case Left(error: ConfigReaderFailures) => throw new Exception("Exception raised when parsing Application.conf file. Please fix " +
        "the following errors to proceed : \n" +
        error.prettyPrint(1))
      case Right(config) => config
    }
}