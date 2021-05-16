/**
  * -------------------------------------------------------------------------------------------------------------------
  * Root Project
  * -------------------------------------------------------------------------------------------------------------------
**/
lazy val root = (project in file("."))
  .settings(
    name := "myProject"
  )
  .settings(ProjectSettings.root: _*)
  .aggregate(module1, module2, config)

/**
 * -------------------------------------------------------------------------------------------------------------------
 * Modules
 * -------------------------------------------------------------------------------------------------------------------
 **/
lazy val config = (project in file("config"))
  .withId("config")
  .settings(ProjectSettings.config: _*)

lazy val module1 = (project in file("module-1"))
  .withId("module-1")
  .settings(ProjectSettings.module1: _*)
  .dependsOn(config)

lazy val module2 = (project in file("module-2"))
  .withId("module-2")
  .settings(ProjectSettings.module2: _*)
  .dependsOn(config)
