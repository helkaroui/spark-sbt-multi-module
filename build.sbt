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
  .aggregate(common, module1, module2)

/**
 * -------------------------------------------------------------------------------------------------------------------
 * Modules
 * -------------------------------------------------------------------------------------------------------------------
 **/
lazy val common = (project in file("common"))
  .settings(
    name := "myProject"
  )
  .withId("common")
  .settings(ProjectSettings.common: _*)

lazy val module1 = (project in file("module-1"))
  .withId("module-1")
  .settings(
    name := "module-1"
  )
  .settings(ProjectSettings.module1: _*)
  .dependsOn(common, module2)

lazy val module2 = (project in file("module-2"))
  .withId("module-2")
  .settings(
    name := "module-2"
  )
  .settings(ProjectSettings.module2: _*)
  .dependsOn(common)
