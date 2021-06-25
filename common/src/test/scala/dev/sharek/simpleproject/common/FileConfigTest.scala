package dev.sharek.simpleproject.common

import dev.sharek.simpleproject.testutils.SimpleTestBase

class FileConfigTest extends SimpleTestBase {

  "FileConfig" should "load application.conf by default" in {
    val conf = FileConfig()
    conf.appName shouldBe "MySparkApp"
    conf.sparkConfig should contain key "spark.serializer"
  }

  it should "load config file by name" in {
    val conf = FileConfig(Some("reference.conf"))
    conf.appName shouldBe "MySparkApp-reference"
    conf.sparkConfig should contain key "spark.serializer"
  }

  it should "throw exception if file doesn't exist" in {
    an[java.lang.Exception] should be thrownBy {
      FileConfig(Some("UNKNOWN.conf"))
    }
  }
}
