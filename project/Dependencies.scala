import sbt.Keys._
import sbt.{Def, _}

object Dependencies {

  lazy val classDependencyCompile = "compile->compile"
  lazy val classDependencyTest = "test->test"
  lazy val classDependencyCompileTest = "test->test;compile->compile"

  lazy val zio = "dev.zio" %% "zio" % Versions.zio
  lazy val scopt = "com.github.scopt" %% "scopt" % Versions.scopt
  lazy val pureConfig = "com.github.pureconfig" %% "pureconfig" % Versions.pureConfig
  lazy val scalaLogging = "com.typesafe.scala-logging" %% "scala-logging" % Versions.scalaLogging

  lazy val common = depends(pureConfig, scopt, Testing.scalaTest, Testing.scalaTestFlatspec, Testing.mockito,
    Testing.scalaTestMockitoSuggar, scalaLogging, Spark.core, Spark.sql, Spark.streaming, Testing.testingBase)
  lazy val module1 = depends(Testing.scalaTest, Testing.scalaTestFlatspec, Testing.mockito,
    Testing.scalaTestMockitoSuggar, Testing.testingBase, scalaLogging, Spark.core, Spark.sql)
  lazy val module2 = depends(Testing.scalaTest, Testing.scalaTestFlatspec, Testing.mockito,
    Testing.scalaTestMockitoSuggar, Testing.testingBase, scalaLogging,
    Spark.core, Spark.sql, Spark.streaming
  )

  private def depends(modules: ModuleID*): Seq[Def.Setting[Seq[ModuleID]]] = Seq(libraryDependencies ++= modules)

  object Testing {
    lazy val scalaTest = "org.scalatest" %% "scalatest" % Versions.scalaTest % Test
    lazy val scalaTestFlatspec = "org.scalatest" %% "scalatest-flatspec" % Versions.scalaTest % Test
    lazy val scalaTestMockitoSuggar = "org.scalatestplus" %% "mockito-3-4" % "3.2.9.0" % Test
    lazy val testingBase = "com.holdenkarau" %% "spark-testing-base" % s"${Versions.spark}_${Versions.testingBase}" % Test
    lazy val mockito = "org.mockito" % "mockito-core" % Versions.mockito % Test
    // lazy val testingBaseWithKafak = "com.holdenkarau" %% "spark-testing-kafka-0_8" % "3.0.0_0.14.0" % "test"
  }

  object Spark {
    lazy val core = "org.apache.spark" %% "spark-core" % Versions.spark % Provided
    lazy val sql = "org.apache.spark" %% "spark-sql" % Versions.spark % Provided
    lazy val streaming = "org.apache.spark" %% "spark-streaming" % Versions.spark % Provided
    lazy val mlLib = "org.apache.spark" %% "spark-mllib" % Versions.spark % Provided
  }
}