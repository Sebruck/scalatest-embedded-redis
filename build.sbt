import Dependencies._

lazy val root = (project in file(".")).settings(
  inThisBuild(
    List(
      organization := "com.github.sebruck",
      scalaVersion := "2.12.3",
      version := "0.3.0"
    )),
  name := "scalatest-embedded-redis",
  libraryDependencies ++= Seq(
    embeddedRedis,
    redisScala % Test,
    scalaTest % Test,
    akka % Test
  ),
  scalacOptions ++= Compiler.options,
  publishTo := Some(
    if (isSnapshot.value)
      Opts.resolver.sonatypeSnapshots
    else
      Opts.resolver.sonatypeStaging
  )
)
