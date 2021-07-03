package dev.sharek.simpleproject.testutils

import com.typesafe.scalalogging.LazyLogging
import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Duration, Seconds, StreamingContext}
import org.scalatest.BeforeAndAfterAll
import org.scalatest.concurrent.Eventually
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.mockito.MockitoSugar

trait SparkStreamingTestBase extends AnyFlatSpec
  with Matchers
  with BeforeAndAfterAll
  with Eventually
  with LazyLogging
  with MockitoSugar
  with TestTags
  with CommonTestUtils {

  override def beforeAll() {
    val conf = new SparkConf()
      .setMaster(master).setAppName(appName)
      .set("spark.ui.enabled", "false")
      .set("spark.driver.host", "localhost")

    ssc = new StreamingContext(conf, batchDuration)
  }

  def master = "local[*]"

  implicit var ssc: StreamingContext = _
  private implicit val batchDuration: Duration = Seconds(5)

  def appName = "spark-streaming-test"

  override def afterAll(): Unit = {
    if (ssc != null) {
      ssc.stop(stopSparkContext = true, stopGracefully = true)
    }
  }

}
