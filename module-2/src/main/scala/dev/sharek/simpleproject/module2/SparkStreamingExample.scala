package dev.sharek.simpleproject.module2

import dev.sharek.simpleproject.common.{Args, FileConfig, SparkStreamingRuntime}
import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.streaming.{Duration, Seconds, StreamingContext}

object SparkStreamingExample extends SparkStreamingRuntime {
  override def batchDuration: Duration = Seconds(5)

  override def run(args: Args, config: FileConfig)(implicit ssc : StreamingContext): Unit = {
    val lines = ssc.socketTextStream("127.0.0.1", config.application.port)

    val wordCounts: DStream[String] = lines
      .flatMap(_.split(" "))
      .map(word => (word, 1))
      .reduceByKey(_ + _)
      .map{case (word, count) => s"$word,$count"}
      .map(s => {println(s); s})

    wordCounts.print()

    wordCounts.repartition(1).saveAsTextFiles(config.application.savePath)

    ssc.start() // Start the computation
  }
}