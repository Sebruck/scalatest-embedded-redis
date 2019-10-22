# scalatest-embedded-redis

With this project you can easily use an 
[embedded redis](https://github.com/kstyrc/embedded-redis) in your unit tests.

It is inspired from [scalatest-embedmongo](https://github.com/SimplyScala/scalatest-embedmongo).

## Usage

### Getting started 

Add the following to your `build.sbt` file.

```scala
libraryDependencies += "com.github.sebruck" %% "scalatest-embedded-redis" % "0.4.0"
``` 

For a working example have a look at the [tests](https://github.com/Sebruck/scalatest-embedded-redis/blob/master/src/main/scala/com/github/sebruck/EmbeddedRedis.scala).

# One redis instance per test for synchronous suites
```scala
import com.github.sebruck.EmbeddedRedis
import org.scalatest.FunSuite

class MyTest extends FunSuite with EmbeddedRedis {

  test("something with redis") {
    withRedis() { port =>
      // ...
      succeed
    }
  }
}
```

# One redis instance per test for asynchronous suites
```scala
import com.github.sebruck.EmbeddedRedis
import org.scalatest.AsyncFunSuite

import scala.concurrent.Future

class MyTest extends AsyncFunSuite with EmbeddedRedis {

  test("something with redis") {
    withRedisAsync() { port =>
      // ...
      Future.successful(succeed)
    }
  }
} 
```

# One redis instance per suite

```scala
import com.github.sebruck.EmbeddedRedis
import org.scalatest.{BeforeAndAfterAll, FunSuite}
import redis.embedded.RedisServer

class MyTest extends FunSuite with EmbeddedRedis with BeforeAndAfterAll {

  var redis: RedisServer = null
  var redisPort: Int = null

  override def beforeAll() = {
    redis = startRedis() // A random free port is chosen
    redisPort = redis.ports().get(0)
  }

  test("something with redis") {
    // ...
  }

  override def afterAll() = {
    stopRedis(redis)
  }
}
```