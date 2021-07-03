package dev.sharek.simpleproject.module2

import dev.sharek.simpleproject.common.{Application, Args, FileConfig}
import dev.sharek.simpleproject.testutils.{SocketUtil, SparkStreamingTestBase}
import org.scalatest.concurrent.PatienceConfiguration.Interval

import scala.concurrent.duration._

class SparkStreamingExampleTest extends SparkStreamingTestBase {

  private var temDir: String = _

  override def beforeAll(): Unit = {
    super.beforeAll()
    temDir = createTempDirectory("SparkStreamingExampleTest") + "/"
    println(s"Temp dir: $temDir ")
  }

  override def afterAll(): Unit = {
    super.afterAll()
    cleanTempDirectory(temDir)
  }

  "SparkStreamingExample" should "" in {

    val socketPort = 10101

    val conf = new FileConfig(
      appName = "Streaming-app-test",
      sparkConfig = Map.empty,
      Application(
        savePath = temDir,
        port = socketPort
      )
    )

    val args = new Args()

    val serverThread = new Thread(new SocketUtil(socketPort, Array("two words")))
    serverThread.start()

    SparkStreamingExample.run(args, conf)

    eventually(timeout(10 seconds), Interval(2 seconds)) {
      val wFile = ssc.sparkContext.textFile(temDir + "*/*")
      println(wFile.count())
      wFile.count() shouldBe 2
    }
  }

}
