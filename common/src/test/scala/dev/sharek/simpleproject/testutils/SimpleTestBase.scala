package dev.sharek.simpleproject.testutils

import com.typesafe.scalalogging.LazyLogging
import org.scalatest.BeforeAndAfterAll
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.mockito.MockitoSugar

trait SimpleTestBase extends AnyFlatSpec
  with Matchers
  with BeforeAndAfterAll
  with LazyLogging
  with MockitoSugar
  with TestTags
