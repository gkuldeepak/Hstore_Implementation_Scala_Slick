name := "HStore_Slick_Integration_PSQL"

version := "0.1"

scalaVersion := "2.12.7"

libraryDependencies ++= Seq("org.flywaydb" % "flyway-core" % "3.2.1",
  "com.typesafe.slick" %% "slick" % "3.3.2",
  "org.postgresql" % "postgresql" % "9.4-1201-jdbc41",
  "com.github.tminglei" %% "slick-pg" % "0.18.0",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.2.3",
  "com.h2database" % "h2" % "1.4.191",
  "mysql" % "mysql-connector-java" % "5.1.38")
