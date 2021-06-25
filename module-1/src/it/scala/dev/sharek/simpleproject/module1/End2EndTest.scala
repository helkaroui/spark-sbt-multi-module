package dev.sharek.simpleproject.module1

import dev.sharek.simpleproject.common.{Application, Args, FileConfig}
import dev.sharek.simpleproject.testutils.SparkTestBase

class End2EndTest extends SparkTestBase {

  val tempDir = createTempDirectory("End2EndTest")
  println(s"Data will be generated in $tempDir")

  "MainApp" should "run without exception" taggedAs (SlowTest) in {
    noException should be thrownBy {
      val conf = new FileConfig(
        appName = "mock app",
        sparkConfig = Map.empty,
        Application(
          savePath = tempDir
        )
      )

      val args = new Args()
      MainApp.run(args, conf)
    }
  }

  it should "write a parquet file properly" taggedAs (SlowTest) in {
    val ds = spark.read.parquet(tempDir)
    ds.show
    ds.count() shouldBe 3
  }

}
