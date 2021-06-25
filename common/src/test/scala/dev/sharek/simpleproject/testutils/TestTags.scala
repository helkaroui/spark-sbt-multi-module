package dev.sharek.simpleproject.testutils

import org.scalatest.Tag

trait TestTags {

  object SlowTest extends Tag("org.scalatest.tags.Slow")

  object DbTest extends Tag("com.mycompany.tags.DbTest")

}
