# Spark SBT Multi Module Example
![](https://img.shields.io/github/v/tag/helkaroui/spark-sbt-multi-module?sort=date)
![Maintenance](https://img.shields.io/badge/Maintained%3F-yes-green.svg)

A sample project to bootstrap all scala/spark project with multiple sbt modules.

Please change the module names, dependencies, project settings accordingly.

Files to change: build.sbt, ProjectSettings, Dependencies and Versions (if needed).

## Features
- Publish the artifact to GitHub Packages
- Uses LazyLogging from [Lightbend](https://github.com/lightbend/scala-logging)
- Uses [HOCON](https://github.com/lightbend/config/blob/master/HOCON.md#hocon-human-optimized-config-object-notation) format for configuration file
- Uses scopt to parse command line arguments


## Plugins
- sbt-scalafmt 
- sbt-assembly
- sbt-dynver
- sbt-docker

## How to publish packages 

create file :
nano ~/.sbt/1.0/.credentials

```shell script
realm=GitHub Package Registry
host=maven.pkg.github.com
user=helkaroui
password=xxxxxxxxxxxxxxxx
```


then run `publish` in sbt


[![Ask Me Anything !](https://img.shields.io/badge/Ask%20me-anything-1abc9c.svg)](https://GitHub.com/Naereen/ama)