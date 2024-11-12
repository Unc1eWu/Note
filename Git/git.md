# Git 基础操作

> [Git教程](https://www.bookstack.cn/read/git-tutorial/docs-commands-git-branch.md)

git init 将当前目录初始化为git仓库.
若想要将当前目录中的文件添加给git管理则需要git add [filename] 或者 git add . 添加当前目录中的所有文件
然后git commit -m "message"

git clone \<repo> \<directory> 将仓库克隆到指定directory
git push \<repo> \<branch> 将本地内容推送到remote仓库
> [Git操作](https://www.runoob.com/git/git-branch.html)

## Commit规范

用于说明commit的类别，只允许使用下面7个标识：

- **feat：** 新功能
- **fix：**修补bug
- **docs：**文档（documentation）
- **style：**格式（不影响代码的运行的变动）
- **refactor：**重构
- **test：**增加测试
- **chore：**构建过程或者辅助工具的变动

## Git config多用户配置

在所有平台使用一套用户信息是可以正常使用的，有时我们需要在不同的项目平台甚至不同的项目中使用不同的用户配置，就需要不同的设置方案

### 全局配置

这是每次安装git后都会提示我们设置的，设置内容存于~/.gitconfig文件中，格式如下：

```git
[user]
    name = yourName
    email = yourEmail@example.com
```

当项目没有特殊配置的时候，就会提取这个文件中的设置为提交信息。

在git安装后我们可以通过以下指令来查询，修改全局配置：

```git
git config --global user.name // 查询全局用户名
git config --global user.email // 查询全局邮箱
git config --global user.name yourName // 修改全局用户名
git config --global user.email yourEmail@example.com // 修改全局邮箱
```

### 项目配置

在每个git项目下，可以针对这个项目进行单独设置，这部分设置存于项目根目录的.ssh/config文件中。

当有项目设置时，取项目设置的内容，没有时才取全局配置。也可以通过指令查询修改项目配置，就是全局配置的指令去掉--global

### 按文件夹配置

用项目配置的方法虽然可以实现不同项目配置不同信息，但每个项目都配置一遍就太麻烦了，而且会经常出现漏配的情况，有把公司信息暴露到公共空间中风险。

使用git的Conditional Includes可以解决这个问题。

Conditional Includes可以针对文件夹来配置，配置方法如下：

在~/.gitconfig添加如下代码：

```git
[includeIf "gitdir:path/to/you/dir/"]
    path = ~/.gitconfig_self
```

gitdir后面是你想要设置的文件夹目录，使用的是glob匹配模式，记得要以/结尾，否则不会生效（以/结尾, **会被自动添加上，比如you/dir会变成you/dir/\**,这样才生效到所有子文件夹）

path的值是新配置的文件夹路径，如果有多个不同的文件夹设置，只需要继续添加配置即可，最终`~/.gitconfig`中的配置会如下：

```git
[user]
    name = youName
    email = youEmail@example.com

## 私人项目
[includeIf "gitdir:path/to/you/dir/"]
    path = ~/.gitconfig_self

## 工作项目
[includeIf "gitdir:path/to/work/dir/"]
    path = ~/.gitconfig_work
```

以上三种配置的优先级为：项目配置 > 文件夹配置 > 全局配置

## git fetch/pull

### git fetch

git fetch从远程repo拉取最新的提交历史，但是不会对当前的本地工作目录产生任何影响。即使在fetch远端仓库内容后，这些变更也不会在本地分支上有所反应。为了将获取的变更应用到本地分支，还需要手动执行git merge或者git rebase。

### git pull

git pull命令是将git fetch和git merge结合在一起。这能够让你从远端仓库拉取（fetch）变更并自动应用到当前的本地分支上。
