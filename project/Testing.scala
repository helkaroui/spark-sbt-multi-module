import sbt.Keys._
import sbt.{Def, _}


object Testing {
  private lazy val testSettings = Seq(
    fork in test := false,
    parallelExecution in Test := true
  )

  private lazy val itSettings = inConfig(IntegrationTest)(Defaults.testSettings) ++ Seq(
    fork in IntegrationTest := true,
    parallelExecution in IntegrationTest := true,
    scalaSource in IntegrationTest := baseDirectory.value / "src/it/scala"
  )

  lazy val settings: Seq[Def.Setting[_]] = testSettings ++ itSettings

}
