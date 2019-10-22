import Dependencies._

lazy val root = (project in file(".")).settings(
  inThisBuild(
    List(
      organization := "com.github.sebruck",
      scalaVersion := "2.13.1",
      crossScalaVersions := Seq("2.13.1", "2.12.10"),
      version := "0.4.0"
    )),
  name := "scalatest-embedded-redis",
  libraryDependencies ++= Seq(
    embeddedRedis,
    redisScala % Test,
    scalaTest % Test,
    akka % Test
  ),
  publishTo := Some(
    if (isSnapshot.value)
      Opts.resolver.sonatypeSnapshots
    else
      Opts.resolver.sonatypeStaging
  )
)
