package dev.sharek.simpleproject.common

import com.typesafe.scalalogging.LazyLogging
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

trait SparkRuntime extends LazyLogging {

  def configurationFile: Option[String] = None

  def run(args: Args, config: FileConfig)(implicit spark: SparkSession): Unit

  def main(args: Array[String]): Unit = {
    logger.info(s"Starting application ...")
    logger.info(s"Initializing app with args : {}", args.mkString(", "))

    val appConfig: FileConfig = FileConfig(configurationFile)
    val parsedArgs: Args = Args(args)

    val sparkConf: SparkConf = new SparkConf()
      .setAll(appConfig.sparkConfig.toList)
      .setAppName(appConfig.appName)

    logger.whenDebugEnabled {
      println("Spark configuration : ")
      appConfig.sparkConfig.foreach { case (key, value) => println(s"$key=$value") }
    }

    implicit val sparkSession: SparkSession = SparkSession
      .builder()
      .config(sparkConf)
      .getOrCreate()

    logger.info(s"Started Spark Batch application {}", appConfig.appName)
    run(parsedArgs, appConfig)
    logger.info(s"Terminated Spark Batch application {}", appConfig.appName)
  }

}
