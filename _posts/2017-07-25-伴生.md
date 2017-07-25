---
layout: post
title: 伴生
date: 2017-07-25
---

Scala 中同一个文件内的同名 object 和 class 互为伴生，即伴生对象和伴生类。

伴生之间可以访问对方的 private 变量。

通过伴生，可以将 class 定义为 private，并在 object 中创建和访问，从而实现单例。

当然，object 本身也是一种单例。