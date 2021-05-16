import sbt.Keys._
import sbt._
import sbt.librarymanagement.Developer

object Publish {

  // Uncomment the following for publishing to Sonatype.
  // See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for more detail.

  lazy val settings = Seq(
    description := "Some descripiton about your project.",
    licenses := List("Apache 2" -> new URL("http://www.apache.org/licenses/LICENSE-2.0.txt")),
    homepage := Some(url("https://github.com/example/project")),
    developers := List(
      Developer(
        id = "Your identifier",
        name = "your name",
        email = "name@example.com",
        url = url("https://sharek.dev")
      )
    ),
    scmInfo := Some(
      ScmInfo(
        url("https://github.com/your-account/your-project"),
        "scm:git@github.com:your-account/your-project.git"
      )
    ),
    pomIncludeRepository := { _ => false },
    publishTo := {
      val nexus = "https://oss.sonatype.org/"
      if (isSnapshot.value) Some("snapshots" at nexus + "content/repositories/snapshots")
      else Some("releases" at nexus + "service/local/staging/deploy/maven2")
    },
    publishMavenStyle := true
  )
}
