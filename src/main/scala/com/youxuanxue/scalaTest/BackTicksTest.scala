package com.youxuanxue.scalaTest

/**
  * @author xuejiao
  */
object BackTicksTest {

  def main(args: Array[String]): Unit = {
    test1()
    test2()
    test3()
    test4()
    test5()
    test6()
  }

  def test1(): Unit = {
    val v42 = 42
    Some(3) match {
      case Some(v42) => println("42")
      case _ => println("Not 42")
    }
  }

  def test2(): Unit = {
    val v42 = 42
    Some(3) match {
      case Some(`v42`) => println("42")
      case _ => println("Not 42")
    }
  }


  def test3(): Unit = {
    val list42 = List(42)

    List(11) match {
      case `list42` => println("list 42")
      case _ => println("Not list 42")
    }
  }

  def test4(): Unit = {
    val list42 = List(42)

    List(11) match {
      case list42 => println("list 42")
      case _ => println("Not list 42")
    }
  }

  def test5(): Unit = {
    val other = "other"
    val Foo = "foo"
    val foo = "bar"

    "foo" match {
      case other => println("other")
      case Foo => println("foo")
      case foo => println("bar")
    }
  }

  def test6(): Unit = {
    val Foo = "foo"
    val foo = "bar"

    "foo" match {
      case `foo` => println("bar")
      case Foo => println("foo")
    }
  }

}
