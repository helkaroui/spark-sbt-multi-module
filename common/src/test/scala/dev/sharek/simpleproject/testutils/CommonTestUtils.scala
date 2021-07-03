package dev.sharek.simpleproject.testutils

import java.io.File
import java.nio.file.Files

import scala.reflect.io.Directory

trait CommonTestUtils {
  def createTempDirectory(prefix: String = "scalatest"): String = Files.createTempDirectory(prefix + "-").toFile.getAbsolutePath

  def cleanTempDirectory(dirPath: String): Boolean = {
    val directory = new Directory(new File(dirPath))
    directory.deleteRecursively()
  }
}
