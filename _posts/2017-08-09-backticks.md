---
layout: post
title: backtick 
date: 2017-08-09
---

backtick 在 scala 中的用法大致有三类。

# 1. custom identifier
在 \` \` 中间的所有字符组成一个标识符。可以用作变量名，方法名。

但是要注意的是，如果标识符作为 `Enumeration` 的 `val`，那么 `toString` 的时候会进行编码。

 比如：`card.click` become `card$u002Eclick`
  
# 2. same to system library identifier
如果想要用和 java library，scala library 同名的标识符，需要用 \` \` 括起来。

# 3. 在 match 的时候指代外部的已有变量
如果想要 match 已有变量的值，而不仅仅是类型，则需要用同名的外部变量并用\` \` 括起来。

test case 参见 `BackTicksTest.scala`
