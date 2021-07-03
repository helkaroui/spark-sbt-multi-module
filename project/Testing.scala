import sbt.Keys._
import sbt.{Def, _}
import scoverage.ScoverageKeys._

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
  private lazy val coverageSettings = Seq(
    coverageEnabled := true,
    coverageFailOnMinimum := true,
    coverageMinimumBranchTotal := 90
  )

  lazy val settings: Seq[Def.Setting[_]] = testSettings ++ itSettings ++ coverageSettings
}
