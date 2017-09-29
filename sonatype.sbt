// Your profile name of the sonatype account. The default is the same with the organization value
sonatypeProfileName := "com.github.sebruck"

// To sync with Maven central, you need to supply the following information:
publishMavenStyle := true

publishArtifact in Test := false

// License of your choice
licenses := Seq(
  "MIT" -> url("http://www.opensource.org/licenses/mit-license.php"))
homepage := Some(url("https://github.com/Sebruck/scalatest-embedded-redis/"))
scmInfo := Some(
  ScmInfo(
    url("https://github.com/Sebruck/scalatest-embedded-redis/"),
    "scm:git@github.com:sebruck/scalatest-embedded-redis.git"
  )
)
developers := List(
  Developer(id = "sebruck",
            name = "Sebastian Bruckner",
            email = "sebbruck@googlemail.com",
            url = url("https://github.com/Sebruck"))
)
