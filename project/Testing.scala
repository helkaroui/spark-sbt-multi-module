import sbt.Keys._
import sbt.Tests.Setup
import sbt.{Def, _}


object Testing {
  private lazy val testSettings = Seq(
    fork in Test := true,
    parallelExecution in Test := false
  )

  private lazy val itSettings = inConfig(IntegrationTest)(Defaults.testSettings) ++ Seq(
    fork in IntegrationTest := true,
    parallelExecution in IntegrationTest := false,
    scalaSource in IntegrationTest := baseDirectory.value / "src/it/scala"
  )

  lazy val settings: Seq[Def.Setting[_]] = testSettings ++ itSettings

}
