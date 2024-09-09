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
