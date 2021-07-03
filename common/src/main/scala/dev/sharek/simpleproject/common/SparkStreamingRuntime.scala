package dev.sharek.simpleproject.common

import com.typesafe.scalalogging.LazyLogging
import org.apache.spark.SparkConf
import org.apache.spark.streaming._

trait SparkStreamingRuntime extends LazyLogging {

  def run(args: Args, config: FileConfig)(implicit ssc  : StreamingContext): Unit

  def main(args: Array[String]): Unit = {
    logger.info(s"Starting streaming application ...")
    logger.info(s"Initializing streaming app with args : {}", args.mkString(", "))

    val appConfig: FileConfig = FileConfig(configurationFile)
    val parsedArgs: Args = Args(args)

    val sparkConf: SparkConf = new SparkConf()
      .setAll(appConfig.sparkConfig.toList)
      .setAppName(appConfig.appName)

    logger.whenDebugEnabled {
      println("Spark configuration : ")
      appConfig.sparkConfig.foreach { case (key, value) => println(s"$key=$value") }
    }

    implicit val ssc: StreamingContext = createStreamingContext(sparkConf)

    logger.info(s"Started Spark Streaming application {}", appConfig.appName)
    run(parsedArgs, appConfig)
    ssc.awaitTerminationOrTimeout(terminationTimeout) // Wait for the computation to terminate
    logger.info(s"Terminated Spark Streaming application {}", appConfig.appName)
  }

  def terminationTimeout: Long = -1

  def configurationFile: Option[String] = None

  def createStreamingContext(sparkConf: SparkConf) = new StreamingContext(sparkConf, batchDuration)

  def batchDuration: Duration = Seconds(1)

}
