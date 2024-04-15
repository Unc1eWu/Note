# XML

可扩展标记语言的简写（eXtensible Markup Language），是一种数据格式表达，可用于描述非常复杂的数据结构，常用于传输和存储数据。
XML有固定的结构，首行必定是\<?xml version="1.0"?>，可以加上可选的编码。紧接着，如果以类似<!DOCTYPE note SYSTEM "book.dtd">声明的是文档定义类型（DTD：Document Type Definition），DTD是可选的。接下来是XML的文档内容，一个XML文档有且仅有一个根元素，根元素可以包含任意个子元素，元素可以包含属性，例如，\<isbn lang="CN">1234567</isbn>包含一个属性lang="CN"，且元素必须正确嵌套。如果是空元素，可以用\<tag/>表示。
XML格式能够表达结构层次，并且允许在确保格式不走样的情况下记录重复元素。

```XML
<congfig>
    <entry id = "title">
        <font>
            <name>Helvetica</name>
            <size>36</size>
        <font>
    </entry>
    <entry id = "body">
        <font>
            <name>Times Roman</name>
            <size>16</size>
        </font>
    </entry>
```

一个合法的XML文件不仅格式正确，而且他的数据结构可以正确的被DTD或者XSD验证，最简单的验证方法就是将XML文件拖进浏览器，如果格式错误，浏览器会报错。
