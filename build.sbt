name := "try-it-all-with-kafka"

version := "0.1"

scalaVersion := "2.12.7"

val jsonVersion = "2.6.10"

libraryDependencies += "org.apache.kafka" %% "kafka" % "2.4.0"
libraryDependencies += "com.typesafe" % "config" % "1.3.3"

libraryDependencies += "com.typesafe.play" %% "play-json" % jsonVersion exclude("ch.qos.logback", "logback-classic")