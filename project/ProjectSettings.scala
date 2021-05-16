import sbt._
import sbt.Keys._

object ProjectSettings {

  private lazy val general = Seq(
    version := version.value,
    scalaVersion := Versions.scala,
    organization := "dev.sharek",
    organizationName := "Sharek",
    scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature", "-Xfuture", "-Xlint"),
    cancelable in Global := true //allow to use Ctrl + C in sbt prompt
  )

  private lazy val common = general ++ Testing.settings ++ Publish.settings ++ Keys.settings ++ Assembly.settings

  lazy val root = common
  lazy val module1 = common ++ Dependencies.module1
  lazy val module2 = common ++ Dependencies.module2
  lazy val config = common ++ Dependencies.configModule
}
