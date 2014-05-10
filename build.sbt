import sbt._
import Keys._

name := "NDMailApi"

version := "1.0"

scalaVersion := "2.11.0"

resolvers += "spray-releases" at "http://repo.spray.io"

libraryDependencies ++= Seq(
  "com.typesafe.akka"   %% "akka-actor"         % "2.3.2",
  "com.typesafe.akka"   %% "akka-slf4j"         % "2.3.2",
  "ch.qos.logback"       % "logback-classic"    % "1.1.2",
  "io.spray"             % "spray-can"          % "1.3.1",
  "io.spray"             % "spray-routing"      % "1.3.1",
  "io.spray"            %% "spray-json"         % "1.2.5"
)