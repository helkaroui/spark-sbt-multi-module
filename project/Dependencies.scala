import sbt.Keys._
import sbt.{Def, _}

object Dependencies {
  lazy val scalaTest = "org.scalatest" %% "scalatest" % Versions.scalaTest
  lazy val zio = "dev.zio" %% "zio" % Versions.zio
  lazy val scopt = "com.github.scopt" %% "scopt" % Versions.scopt
  lazy val pureConfig = "com.github.pureconfig" %% "pureconfig" % Versions.pureConfig

  object Spark {
    lazy val core = "org.apache.spark" %% "spark-core" % Versions.spark % Provided
    lazy val sql = "org.apache.spark" %% "spark-sql" % Versions.spark % Provided
    lazy val mlLib = "org.apache.spark" %% "spark-mllib" % Versions.spark % Provided
    lazy val testingBase = "com.holdenkarau" %% "spark-testing-kafka-0_8" % s"${Versions.spark}_${Versions.testingBase}" % Test
  }

  lazy val configModule = depends(pureConfig, scopt)
  lazy val module1 = depends(scalaTest, zio)
  lazy val module2 = depends(scalaTest, Spark.core, Spark.sql)

  private def depends(modules: ModuleID*): Seq[Def.Setting[Seq[ModuleID]]] = Seq(libraryDependencies ++= modules)
}