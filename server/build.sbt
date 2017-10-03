name := "server"

version := "1.0"

scalaVersion := "2.12.3"

val circeVersion = "0.8.0"

libraryDependencies +=  "org.scalaj" %% "scalaj-http" % "2.3.0"
libraryDependencies += "com.typesafe" %  "config" % "1.3.1"
libraryDependencies += "org.scalatest"%% "scalatest" % "3.0.1" % Test


libraryDependencies ++= Seq(
  "io.circe" %% "circe-core",
  "io.circe" %% "circe-generic",
  "io.circe" %% "circe-generic-extras",
  "io.circe" %% "circe-parser"
).map(_ % circeVersion)
    