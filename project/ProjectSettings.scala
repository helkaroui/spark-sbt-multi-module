import sbt.Keys._
import sbt._

object ProjectSettings {

  lazy val root = commonSettings
  lazy val module1 = commonSettings ++ Dependencies.module1
  lazy val module2 = commonSettings ++ Dependencies.module2
  lazy val common = commonSettings ++ Dependencies.common

  private lazy val general = Seq(
    version := version.value,
    scalaVersion := Versions.scala,
    organization := "dev.sharek",
    organizationName := "Sharek",
    developers := List(Developer("helkaroui", "Hamza EL KAROUI", "helkarou@gmail.com", new URL("http://sharek.dev"))),
    scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature", "-Xfuture", "-Xlint"),
    javaOptions ++= Seq("-Xms512M", "-Xmx2048M", "-XX:MaxPermSize=2048M", "-XX:+CMSClassUnloadingEnabled"),
    cancelable in Global := true //allow to use Ctrl + C in sbt prompt
  )
  private lazy val commonSettings = general ++ Testing.settings ++ Publish.settings ++ Keys.settings ++ Assembly.settings
}
