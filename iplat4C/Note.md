# 记录iplat4c开发中遇到的问题

## invalid token

打开界面到 <http://10.25.102.18:8098/#/home，> f12在application中复制signature token然后复制到.env.development中

## 低代码开发页面配置

页面为SI011X

## 数据集

新增模块，新增表，建完之后从数据字典引用，有时子表创建选择数据字典时注意owner
数据集中配置表关系

## 数据源

到对应模块下新增数据源，并在后端编写对应服务，在填写数据时填入对应service名称。
![新增数据源](/iplat4C/img/e6c8239364d847b4fdae88fde4dfe5a.png "数据源")

## 画面布局

创建新页面->新增画面

新增模块，新建画面，然后画面属性配置。 layout部分从数据集引用，gridcolumns顺序需与网页顺序一致，caption默认是用数据库中表字段名，需按照情况调整

## 注意事项

**画面名称必须为：LayoutGroupFilter，gridView1 / 2.**

然后去EPESOBJ进行画面注册以及按钮配置

querySQL在可以使用控件数据源以及grid数据源, service的话则直接调用服务号对应的service

EPED54 / 56引用

表关系：

## 日志查询

EPEL01然后搜索对应的服务号

## 信融框架可视化构建工具

<http://10.25.145.54:10003/codeGenerator_2_5/#/>

## vs2013中的解决方案

项目包含在解决方案中。尽管名称如此，但解决方案不是“答案”。解决方案只是一个容器，用于包含一个或多个相关项目，以及生成信息、Visual Studio窗口设置和不与特定项目关联的任何杂项文件。

Visual Studio采用.sln的文件类型来储存解决方案设置。将项目、项目项和解决方案项组织到解决方案中。
