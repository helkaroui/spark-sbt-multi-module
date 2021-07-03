package dev.sharek.simpleproject.testutils

import com.holdenkarau.spark.testing.{StreamingSuiteBase, StreamingActionBase}
import com.typesafe.scalalogging.LazyLogging
import org.scalatest.BeforeAndAfterAll
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.mockito.MockitoSugar

trait SparkDStreamTestBase extends AnyFlatSpec
  with Matchers
  with BeforeAndAfterAll
  with StreamingSuiteBase
  with StreamingActionBase
  with LazyLogging
  with MockitoSugar
  with TestTags
  with CommonTestUtils