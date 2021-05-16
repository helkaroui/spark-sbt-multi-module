import sbtassembly.AssemblyKeys._
import sbt.Keys._

object Assembly {
  lazy val settings = Seq(
    assemblyJarName := s"${name.value.toLowerCase}_${version.value.toLowerCase}.jar"
  )
}
