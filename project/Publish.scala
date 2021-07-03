import sbt.Keys._
import sbt._
import sbt.librarymanagement.Developer


object Publish {

  lazy val ghUsername = "helkaroui"
  lazy val ghRepo = "spark-sbt-multi-module"

  lazy val settings = Seq(
    description := "Spark-sbt-multi-module template",
    licenses := List("Apache 2" -> new URL("http://www.apache.org/licenses/LICENSE-2.0.txt")),
    homepage := Some(url(s"https://github.com/$ghUsername/$ghRepo")),
    developers := List(
      Developer(
        id = ghUsername,
        name = "Hamza",
        email = "name@example.com",
        url = url("https://sharek.dev")
      )
    ),
    scmInfo := Some(
      ScmInfo(
        url(s"https://github.com/$ghUsername/$ghRepo"),
        s"scm:git@github.com:$ghUsername/$ghRepo.git"
      )
    ),
    pomIncludeRepository := { _ => false },

    publishTo := {
      val github = s"https://maven.pkg.github.com/$ghUsername/$ghRepo"
      if (isSnapshot.value) Some("snapshots" at github )
      else Some("releases" at github )
    },
    publishMavenStyle := true
  )
}
