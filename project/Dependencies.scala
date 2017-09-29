import sbt._

object Dependencies {
  lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.0.3"
  lazy val embeddedRedis = "com.github.kstyrc" % "embedded-redis" % "0.6"
  lazy val redisScala = "com.github.etaty" %% "rediscala" % "1.8.0"
  lazy val akka = "com.typesafe.akka" % "akka-actor" % "2.5.4"
}
