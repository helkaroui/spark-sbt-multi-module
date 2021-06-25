package dev.sharek.simpleproject.module1

import dev.sharek.simpleproject.common.{Args, FileConfig, SparkRuntime}
import org.apache.spark.sql.{Dataset, SaveMode, SparkSession}

object MainApp extends SparkRuntime {

  case class Record(name: String, age: Int)

  override def run(args: Args, config: FileConfig)(implicit spark: SparkSession): Unit = {
    import spark.implicits._
    val ds: Dataset[Record] = Seq(Record("Voldemort", 72), Record("Ron Weasley", 18), Record("Fleur Delacour", 18)).toDS

    logger.info("Dataset size {}", ds.count())
    ds.write.mode(SaveMode.Overwrite).parquet(config.application.savePath)
  }

}