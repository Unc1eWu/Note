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

## 头文件缺少时

进到对应模块的表信息中，选择生成头文件，类型选择CPP头文件3静态并且关联编译。在对应的service的cpp文件中记得include对应的头文件名再编译上传。

## 后台service新增

在客户端的EPEA01的画面中操作

## 画面注册

在EPESOBJ中完成画面和按钮注册后若想要在网页版侧边栏显示需要将其挂载到webroot中。

## vs2013中新建表

- 模块视图->新建字段->数据项->新增一行，
- 新增时注意先进行查询是否已有字段
- 若无该字段新增时需要审核，审核中需要自己去批准一下。
- 创建表，填表信息，选表间复制，从表中添加已有字段。默认需要有创建人，公司帐套等信息、留几个备用字段
- 新建索引，命名：表名_PK然后应用到开发环境
- include文件夹中，复制一个头文件，改下文件名为：表名.h
- 生成头文件 选择cpp3.0静态，然后放入include文件夹中
- 更新头文件 include找到文件 改文件，保存，生成头文件。
- 当对表结构做了一些修改的时候需要进到对应的文件下面随意修改一下然后保存退出
然后新增的时候去选择表间复制

## 子母画面相关

访问时地址类型为

`http://localhost:5173/XGYMREC1SB?formName=XGYMREC1SBFG`

端口号后为母画面代码然后使用?formName=拼接子画面代码进行访问

## 后台查询服务编写

```c++
CDbCommand comm_inq(conn);
    CString sql = " SELECT * FROM TQMREPKG WHERE SAMPLE_STATUS = 'Y'";
    comm_inq.SetCommandText(sql);
    affectRow = comm_inq.ExecuteQuery(bcls_ret->Tables[0]);
    bcls_ret->Tables[0].set_TableName("TQMREPKG");
    comm_inq.Close();
    sprintf(s.msg, "查询成功！" );
```

comm_inq.SetCommandText(sql)方法将需要查询的sql语句传入
后面的comm_inq.ExecuteQuery则是执行sql语句
然后将查询的数据放入一个表中给表命名，需要注意此时前端mergeDataToGrid中的表名要与后台服务中的表名相同。

## 问题XGYMREYKHJ

该画面mergeDataToGrid无法正常显示，后发现客户端低代码配置中LayoutGroupFilter数据集配置未正确。

## 问题后台获取到表信息后前台grid中数据没有绑定上去

浏览器开发者工具调试看获取到的数据table名称是否和后端代码table名称相同
