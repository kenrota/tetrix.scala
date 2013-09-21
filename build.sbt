lazy val buildSettings = Seq(
  version := "0.2.0-SNAPSHOT",
  organization := "com.eed3si9n",
  homepage := Some(url("http://eed3si9n.com")),
  licenses := Seq("MIT License" -> url("http://opensource.org/licenses/mit-license.php/")),
  scalaVersion := "2.10.2",
  scalacOptions := Seq("-deprecation", "-unchecked"),
  resolvers ++= Seq(
    Resolver.sonatypeRepo("public"),
    Resolver.typesafeRepo("releases")
  )
)

lazy val specs2version = "2.2.2"
lazy val akkaVersion = "2.2.1"
lazy val libDeps = Def.setting { Seq(
  "org.specs2" %% "specs2" % specs2version % "test",
  "com.typesafe.akka" %% "akka-actor" % akkaVersion
)}
lazy val swingDependencies = Def.setting {
  "org.scala-lang" % "scala-swing" % scalaVersion.value
}

lazy val root = (project in file(".")).
  settings(buildSettings: _*).
  settings(name := "tetrix.scala")

lazy val library = (project in file("library")).
  settings(buildSettings: _*).
  settings(
    libraryDependencies ++= libDeps.value
  )

lazy val swing = (project in file("swing")).
  settings(buildSettings: _*).
  settings(
    fork in run := true,
    libraryDependencies += swingDependencies.value
  ).
  dependsOn(library)
