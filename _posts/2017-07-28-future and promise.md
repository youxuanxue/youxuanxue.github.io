---
layout: post
title: "future and promise" 
date: 2017-07-28
---

# future

A Future is a placeholder object for a value that may not yet exist.

占位符对象（placeholder object）

[API](https://www.scala-lang.org/api/current/scala/concurrent/Future.html) guidelines

## Definition
```
trait Future[+T] extends Awaitable[T]
```

## partial function
「因为偏函数具有 isDefinedAt方法， onFailure方法只有在特定的Throwable类型对象中被定义才会触发。」

偏函数的 isDefinedAt 方法？

## basic
- non-blocking
- immutable
- Either\[T, Throwable\]

## callbacks
- onComplete
- foreach
- andThen

multiple andThen calls are in order.

# combinators／Transformations
futures 的组合器： map, flatMap, collect, filter, for... 

「Future的组合器功能是纯函数式的，每种组合器都会返回一个与原Future相关的新Future对象。」

## for-comprehension

The for-comprehension combinator is similar to flatMap instead of Future.sequence.

The former is sequential and the latter is concurrent. 

The test code is ```FutureForComprehensionTest```

## recover
recoverWith is to recover as flatMap is to map

Definition in ```Future.scala```
```
 def recover[U >: T](pf: PartialFunction[Throwable, U])(implicit executor: ExecutionContext): Future[U] = {
    val p = Promise[U]()
    onComplete { v => p complete (v recover pf) }
    p.future
  }
  
  def recoverWith[U >: T](pf: PartialFunction[Throwable, Future[U]])(implicit executor: ExecutionContext): Future[U] = {
      val p = Promise[U]()
      onComplete {
        case Failure(t) => try pf.applyOrElse(t, (_: Throwable) => this).onComplete(p.complete)(internalExecutor) catch { case NonFatal(t) => p failure t }
        case other => p complete other
      }
      p.future
    }
```

## filter & filterWith
filter 的结果要么是原来的 future，要么是 NoSuchElementException。

这里的一个陷进是对 Future[List[Any]] 这种 result 是 collection 的情况。filter 不能对 result 做过滤。

## fallbackTo
fallbackTo first or else 

# promise

While futures are defined as a type of read-only placeholder object created for 
a result which doesn’t yet exist, a promise can be thought of as a writable, 
single-assignment container, which completes a future. 

## Definition

```scala
trait Promise[T] extends AnyRef
```
promise 只赋值一次。The test code is ```TryCompleteWithFuture``
