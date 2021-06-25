# Spark SBT MultiModule Model
A sample project to bootstrap all scala/spark project with multiple sbt modules.

Please change the module names, dependencies, project settings accordingly.

Files to change: build.sbt, ProjectSettings, Dependencies and Versions (if needed).

## Features
- Publish the artifact to GitHub Packages
- Uses LazyLogging from [Lightbend](https://github.com/lightbend/scala-logging)
- Uses [HOCON](https://github.com/lightbend/config/blob/master/HOCON.md#hocon-human-optimized-config-object-notation) format for configuration file


## Plugins
- sbt-scalafmt 
- sbt-assembly
- sbt-dynver
- sbt-docker