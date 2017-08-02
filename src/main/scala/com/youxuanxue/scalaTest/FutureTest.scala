package com.youxuanxue.scalaTest

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.{Await, Future, Promise}
/**
  * @author xuejiao
  */
trait FutureTest {
  def generateFuture(index: Int, timeout: Long = 3000): Future[Int] = {
    Future {
      Thread.sleep(timeout)
      println(index)
      index
    }
  }
}

object FutureForComprehensionTest  extends FutureTest {
  def main(args: Array[String]): Unit = {
   try Await.result(sequenceFuture(), 10.seconds)
   catch {
     case ex: Throwable =>
        println(s"sequenceFuture failure: $ex")
    }

    try Await.result(forComprehensionFuture(), 60.seconds)
      catch {
        case ex: Throwable =>
          println(s"forComprehensionFuture failure: $ex")
      }
  }

  def forComprehensionFuture(): Future[Unit] = {
    val start = System.currentTimeMillis()
    for {
      v1 <- generateFuture(1)
      v2 <- generateFuture(2)
      v3 <- generateFuture(3)
      v4 <- generateFuture(4)
      v5 <- generateFuture(5)
      v6 <- generateFuture(6)
      v7 <- generateFuture(7)
    } yield {
      println(s"forComprehension cost ${System.currentTimeMillis() - start}ms")
    }
  }

  def sequenceFuture(): Future[Unit] = {
    val start = System.currentTimeMillis()
    Future.sequence(
      Seq(
        generateFuture(1),
        generateFuture(2),
        generateFuture(3),
        generateFuture(4),
        generateFuture(5),
        generateFuture(6),
        generateFuture(7)
      )
    )
      .map { _ =>
        println(s"sequenceFuture cost ${System.currentTimeMillis() - start}ms")
      }
  }

}

object TryCompleteWithFuture extends FutureTest {
  def main(args: Array[String]): Unit = {
    val testing = Promise[Int]
    try {

      testing.tryCompleteWith(generateFuture(1, 100))

      testing.tryCompleteWith(generateFuture(2, 100))

      testing.tryCompleteWith(generateFuture(3, 1000))

      while (!testing.isCompleted) {
        Thread.sleep(100)
      }

      // print 1, 2
      // 说明：Promise 的 future 只赋值一次。但是每次 tryCompleteWith 都会去计算新 future 的值。
    }
    catch {
      case ex: Exception =>
        println(ex)
    }

  }

}
