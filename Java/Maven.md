# Maven

Maven就是是专门为Java项目打造的管理和构建工具，它的主要功能有：

* 提供了一套标准化的项目结构；
* 提供了一套标准化的构建流程（编译，测试，打包，发布……）；
* 提供了一套依赖管理机制。

简单来说，Apache Maven 最大的核心功能是帮你管理 Jar 包。不知道你是否在最开始学习 Java 开发时，如果有需要依赖其他 Jar 包，则需要把那个 Jar 复制到工程中，并且还需要在 Eclipse 里配置加载 Jar 包才能使用。而现在有 Maven 这样的工具后，一切都变得简单了。工程所需的 Jar 包，只需要配置 POM 就可以直接使用拉取和使用。

## 命令操作

* clean：清理，执行该命令会删除项目路径下的target文件，但是不会删除本地的maven仓库已经生成的jar文件。
* validate：验证，验证工程正确性，所需信息完整否。
* compile：编译，大伙都知道java的识别文件是.class，编译生成class文件,编译命令，只编译选定的目标，不管之前是否已经编译过，会在你的项目路径下生成一个target目录，在该目录中包含一个classes文件夹，里面全是生成的class文件及字节码文件。与build区别：只编译选定的目标，不管之前是否已经编译过。
* test：测试，单元测试。
* package：打包，将工程文件打包为指定的格式，例如JAR，WAR等（看你项目的pom文件，里面的packaging标签就是来指定打包类型的）。这个命令会在你的项目路径下一个target目录，并且拥有compile命令的功能进行编译，同时会在target目录下生成项目的jar/war文件。如果a项目依赖于b项目，打包b项目时，只会打包到b项目下target下，编译a项目时就会报错，因为找不到所依赖的b项目，说明a项目在本地仓库是没有找到它所依赖的b项目，这时就用到install命令。
* verify：核实，主要是对package检查是否有效、符合标准。
* install：安装，将包安装至本地仓库，以让其它项目依赖。该命令包含了package命令功能，不但会在项目路径下生成class文件和jar包，同时会在你的本地maven仓库生成jar文件，供其他项目使用（如果没有设置过maven本地仓库，一般在用户/.m2目录下。如果a项目依赖于b项目，那么install b项目时，会在本地仓库同时生成pom文件和jar文件，解决了上面打包package出错的问题）。
* build：建造，功能类似compile，区别是对整个项目进行编译。与compile区别及特点：是对整个工程进行彻底的重新编译，而不管是否已经编译过。Build过程往往会生成发布包，这个具体要看对IDE的配置了，Build在实际中应用很少，因为开发时候基本上不用，发布生产时候一般都用ANT等工具来发布。Build因为要全部编译，还要执行打包等额外工 作，因此时间较长。
* site：站点，生成项目的站点文档。
* deploy：配置部署，复制到远程仓库。前提需要在工程 POM 和 Maven 里配置上相关的信息以及账号。

解决找不到脚手架的问题 [Maven-Archetype Catalog](https://blog.csdn.net/tjsahwj/article/details/84836861)
Maven的安装 [Maven安装以及IDEA配置](https://blog.csdn.net/weixin_45573560/article/details/133003556)

## 依赖管理

maven定义的几种依赖关系：

1. compile - 编译时需要用到该jar包 commons-logging
2. test - 测试时需要用到该jar包  junit
3. runtime - 运行时需要用到该jar包，但编译时不需要  mysql
4. provided - 编译时需要用到，但运行时由某个服务器或者JDK提供  servlet-api

## 唯一ID

maven使用三个变量确定一个jar包的依赖：

* groupID 属于组织的名称
* artifactID 该jar包的名称
* version 该jar包的版本

## 模块管理

一个大项目分为多个模块时，每个模块下有对应的pom.xml文件管理该模块的依赖，最后在根目录包含一个pom.xml去管理内部每个模块的依赖。
