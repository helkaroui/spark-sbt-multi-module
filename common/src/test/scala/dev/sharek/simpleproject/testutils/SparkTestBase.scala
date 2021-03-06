package dev.sharek.simpleproject.testutils

import com.holdenkarau.spark.testing.{DataFrameSuiteBase, DatasetSuiteBase, SharedSparkContext}
import com.typesafe.scalalogging.LazyLogging
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.scalatest.BeforeAndAfterAll
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.mockito.MockitoSugar

trait SparkTestBase extends AnyFlatSpec
  with Matchers
  with BeforeAndAfterAll
  with SharedSparkContext
  with DatasetSuiteBase
  with DataFrameSuiteBase
  with LazyLogging
  with MockitoSugar
  with TestTags
  with CommonTestUtils {
  implicit def ss: SparkSession = spark

  def toSeqString(df: DataFrame): Seq[String] = df.select("*").collect.map(_.toString)
}
