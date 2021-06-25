package dev.sharek.simpleproject.common

import dev.sharek.simpleproject.testutils.SimpleTestBase

class ArgsTest extends SimpleTestBase {

  "Args" should "parse main args into object" in {
    val args = Array("-v", "-d", "--kwargs", "k1=v1,k2=v2")
    val parsedArgs = Args(args)

    parsedArgs.verbose shouldBe true
    parsedArgs.debug shouldBe true
    parsedArgs.kwargs shouldBe Map("k1" -> "v1", "k2" -> "v2")
  }

  "Args" should "throw java.lang.Exception if unknown argument is passed" in {
    an[java.lang.Exception] should be thrownBy {
      Args(Array("--unknown"))
    }
  }
}
