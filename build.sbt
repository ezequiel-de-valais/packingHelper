name := "Project"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaCore,
  javaJdbc,
  javaEbean,
  cache,
  "mysql" % "mysql-connector-java" % "5.1.38"
)

play.Project.playJavaSettings

aggregate in Test := false

sbt.Keys.fork in Test := false

aggregate in Test := false

javaOptions in Test += "-Dtest.timeout=300000 --XX:-OmitStackTraceInFastThrow"