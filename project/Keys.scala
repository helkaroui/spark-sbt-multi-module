import sbt.{Def, Task, TaskKey}

object Keys {
  lazy val printHi = TaskKey[Unit]("prints-hi")

  lazy val settings: Seq[Def.Setting[_]] = Seq(
    printHi := println("Hi from task !")
  )
}
