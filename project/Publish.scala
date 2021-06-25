import sbt.Keys._
import sbt._
import sbt.librarymanagement.Developer


object Publish {

  lazy val settings = Seq(
    description := "Spark-sbt-multi-module template",
    licenses := List("Apache 2" -> new URL("http://www.apache.org/licenses/LICENSE-2.0.txt")),
    homepage := Some(url("https://github.com/helkaroui/spark-sbt-multi-module")),
    developers := List(
      Developer(
        id = "helkaroui",
        name = "Hamza",
        email = "name@example.com",
        url = url("https://sharek.dev")
      )
    ),
    scmInfo := Some(
      ScmInfo(
        url("https://github.com/helkaroui/spark-sbt-multi-module"),
        "scm:git@github.com:helkaroui/spark-sbt-multi-module.git"
      )
    ),
    pomIncludeRepository := { _ => false },

    publishTo := {
      val github = "https://maven.pkg.github.com/helkaroui/spark-sbt-multi-module"
      if (isSnapshot.value) Some("snapshots" at github )
      else Some("releases" at github )
    },
    publishMavenStyle := true
  )
}
