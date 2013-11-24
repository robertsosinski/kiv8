name := "kiv8"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  anorm,
  "com.typesafe.slick" %% "slick"      % "2.0.0-M3",
  //"org.slf4j"           % "slf4j-nop"  % "1.6.4",
  "postgresql"          % "postgresql" % "9.1-901.jdbc4"
)

play.Project.playScalaSettings
