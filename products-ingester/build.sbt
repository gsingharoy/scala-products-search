name := "products-ingester"

version := "1.0"

scalaVersion := "2.12.3"

val circeVersion = "0.8.0"

libraryDependencies +=  "org.scalaj" %% "scalaj-http" % "2.3.0"
libraryDependencies += "com.typesafe" %  "config" % "1.3.1"

libraryDependencies ++= Seq(
  "io.circe" %% "circe-core",
  "io.circe" %% "circe-generic",
  "io.circe" %% "circe-parser"
).map(_ % circeVersion)
    