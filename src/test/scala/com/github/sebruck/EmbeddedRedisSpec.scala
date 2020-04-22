package com.github.sebruck

import akka.actor.ActorSystem
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.flatspec.AsyncFlatSpec
import org.scalatest.matchers.should.Matchers
import redis.RedisClient
import redis.actors.NoConnectionException

import scala.concurrent.Future

class EmbeddedRedisSpec
    extends AsyncFlatSpec
    with Matchers
    with EmbeddedRedis
    with ScalaFutures {

  implicit val sys = ActorSystem("test")
  val givenPort = getFreePort

  behavior of "withRedis"
  it should "start redis on a random free port" in withRedis() { port =>
    val redis = RedisClient(port = port)
    redis.ping().futureValue shouldBe "PONG"
  }

  it should "start redis on a given port" in withRedis(givenPort) { port =>
    val redis = RedisClient(port = port)
    redis.ping().futureValue shouldBe "PONG"
  }

  it should "stop redis after execution" in {
    val port = getFreePort
    withRedis(port)(identity)
    val redis = RedisClient(port = port)
    recoverToSucceededIf[NoConnectionException.type](redis.ping())
  }

  behavior of "withRedisAsync"
  it should "start redis on a random free port" in withRedisAsync() { port =>
    val redis = RedisClient(port = port)
    redis.ping().map(_ shouldBe "PONG")
  }

  it should "start redis on a given port" in withRedisAsync(givenPort) { port =>
    val redis = RedisClient(port = port)
    redis.ping().map(_ shouldBe "PONG")
  }

  it should "stop redis after execution" in {
    val port = getFreePort

    withRedis(port)(Future.successful).flatMap { _ =>
      val redis = RedisClient(port = port)
      recoverToSucceededIf[NoConnectionException.type](redis.ping())
    }
  }

  behavior of "startRedis and stopRedis"

  it should "properly start and stop redis" in {
    val port = getFreePort

    val redisServer = startRedis(port)
    val redis = RedisClient(port = port)

    for {
      _ <- redis.ping()
      _ = stopRedis(redisServer)
      assertion <- recoverToSucceededIf[Exception](redis.ping())
    } yield assertion
  }
}
