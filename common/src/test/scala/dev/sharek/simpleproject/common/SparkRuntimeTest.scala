package dev.sharek.simpleproject.common

import dev.sharek.simpleproject.testutils.SparkTestBase
import org.apache.spark.sql.SparkSession

class SparkRuntimeTest extends SparkTestBase {

  "SparkRunTime" should "run without exception" in {
    noException should be thrownBy {
      object SimpleSparkApp extends SparkRuntime {
        override def run(args: Args, config: FileConfig)(implicit spark: SparkSession): Unit = {
          import spark.implicits._
          val ds = Seq(("Harry", 15), ("Dumbledore", 150), ("Hermione", 15)).toDS()
          logger.info("Total persons count: {}", ds.count())
        }
      }

      val conf = new FileConfig(
        appName = "mock app",
        sparkConfig = Map.empty,
        application = Application("")
      )
      val args = new Args()
      SimpleSparkApp.run(args, conf)
    }
  }

  "SparkTestingBase" should "create df" in {
    import spark.implicits._

    val ds = Seq(("Harry", 15), ("Dumbledore", 150), ("Hermione", 15)).toDS()
    println(ds.count())
  }

}
