# SQL

## 一些相关术语

- 列：一列（数据元素）包含了相同类型的数据
- 行：一行（元组或者记录）是一组相关的数据
- 冗余：储存两倍数据，冗余降低了性能，但提高了数据的安全性
- 主键：主键是唯一的。一个数据表中只能包含一个主键。可以使用主键来查询数据
- 外键：外键用于关联两个表。
- 复合键：复合键将多个列作为一个索引键，一般用于复合索引
- 索引：使用索引可快速访问数据库表中的特定信息。索引是对数据库表中一列或多列的值进行排序的一种结构
- 参照完整性：参照的完整性要求关系中不允许引用不存在的实体。与实体完整性是关系模型必须满足的完整性约束条件，目的是保证数据的一致性。

## 数据类型

### 数值类型

- TINYINT 小整数值
- SMALLINT 大整数值
- MEDIUMINT 大整数值
- INT或INTEGER 大整数值
- BIGINT 极大整数值
- FLOAT 单精度浮点数值
- DOUBLE 双精度浮点数值
- DECIMAL 小数值

### 日期和时间类型

- DATE 日期值
- TIME 时间值或持续时间
- YEAR 年份值
- DATETIME 混合日期和时间值
- TIMESTAMP 混合日期和时间值，时间戳

### 字符串类型

- CHAR 定长字符串
- VARCHAR 变长字符串
- TINYBLOB 不超过255个字符的二进制字符串
- TINYTEXT 短文本字符串
- BLOB 二进制形式的长文本数据
- TEXT 长文本数据
- MEDIUMBLOM 二进制形式的中等长度文本数据
- MEDIUMTEXT 中等长度文本数据
- LONGBLOB 二进制形式的极大文本数据
- LONGTEXT 极大文本数据

## 创建数据表

```SQL
CREATE TABLE table_name {
    columm1 datatype,
    column2 datatype,
    ...
}
```

## 删除数据表

```SQL
DROP TABLE table_name; -- 直接删除表，不检查是否存在
DROP TABLE [IF EXISTS] table_name; -- 会检查是否存在，如果存在则删除
-- 如果只是想删除表中所有数据，但保留表的结构，可以使用TRUNCATE TABLE语句：
TRUNCATE TABLE table_name;
```

## 插入数据

```SQL
INSERT INTO table_name (column1, column2, column3, ...)
VALUES (value1, value2, value3, ...);
```

## 查询数据

通过SELECT语句来查询数据

```SQL
SELECT column1, column2, ...
FROM table_name
[WHERE condition]
[ORDER BY column_name [ASC | DESC]]
[LIMIT number];
```

- WHERE condition 用于指定过滤条件，只返回符合条件的行。
- ORDER BY column_name [ASC | DESC] 用于指定结果集的排序顺序，默认是升序（ASC）
- LIMIT number 用于限制返回的行数

## WHERE子句

如需有条件地从表中选取数据，可将WHERE子句添加到SELECT语句中。

```SQL
SELECT column1, column2, ...
FROM table_name
WHERE condition;
```

- 查询语句中可以使用一个或者多个表，表之间使用逗号，分割，并使用WHERE语句来设定查询条件
- 可以使用AND或者OR指定一个或多个条件
- WHERE子句也可以运用于SQL的DELETE或者UPDATE命令。

1. 模糊匹配条件（LIKE）: `SELECT * FROM customers WHERE first_name LIKE 'J%'`;
2. IN 条件：`SELECT * FROM contries WHERE country_code IN ('US', 'CA', 'MX')`;

## UPDATE更新

```SQL
UPDATE table_name
SET column1 = value1, column2 = value2, ...
WHERE condition;
```

- 你可以同时更新一个或多个字段
- 你可以在一个单独表中同时更新数据

```SQL
UPDATE customers
SET total_purchases = (
    SELECT SUM(amount)
    FROM orders
    WHERE orders.customer_id = customers.customer_id
)
WHERE customer_type = 'Premium';
```

## DELETE语句

```SQL
DELETE FROM table_name
WHERE condition;
```

- 如果没有指定WHERE子句，表中的所有记录将被删除
- 可以在单个表中一次性删除记录

```SQL
DELETE FROM customers
WHERE customer_id IN (
    SELECT customer_id
    FROM orders
    WHERE order_date < '2023-01-01'
);
```

通过子查询删除了orders表中在'2023-01-01'之前下的订单对应的客户。

## LIKE语句

LIKE子句是用于模糊匹配的关键字。通常与通配符一起使用，用于搜索符合某种模式的字符串。
LIKE子句中使用百分号%来表示任意字符，类似于UNIX或正则表达式中的星号*

```SQL
SELECT column1, column2, ...
FROM table_name
WHERE column_name LIKE pattern;
```

## UNION操作符

UNION操作符用于连接两个以上的SELECT语句的结果组合到一个结果集合，并去除重复的行。
UNION操作符必须由两个或多个SELECT语句组成，每个SELECT语句的列数和对应位置的数据类型必须相同。

```SQL
SELECT column1, column2, ...
FROM table1
WHERE condition1
UNION
SELECT column1, column2, ...
FROM table2
WHERE condition2
[ORDER BY column1, column2, ...];
```

## GROUP BY

GROUP BY语句根据一个或多个列对结果集进行分组。
在分组的列上我们可以使用COUNT,SUM,AVG,等函数。
GROUP BY语句是SQL查询中用于汇总和分析数据的重要工具，尤其在处理大量数据时，它能够提供有用的汇总信息。

```SQL
SELECT column1, aggregate_function(column2)
FROM table_name
WHERE condition
GROUP BY column1;
```

- column1:指定分组的列
- aggregate_function(column2): 对分组后的每个组执行的聚合函数。
- table_name：要查询的表名
- condition: 用于筛选结果的条件。

```SQL
SELECT customer_id, SUM(order_amount) AS total_amount
FROM orders
GROUP BY customer_id;
```

使用GROUP BY customer_id 将结果按customer_id列分组，然后使用SUM(order_amount)计算每个组中order_amount列的总和

## JOIN的使用

我们可以在SELECT,UPDATE和DELETE语句中使用JOIN来联合多表从查询。
JOIN的功能大致分为如下三类：

- INNER JOIN（内连接，或等值连接）：获取两个表中字段匹配关系的记录
- LEFT JOIN（左连接）：获取左表所有记录，即使右表没有对应的匹配记录。
- RIGHT JOIN（右连接）：与LEFT JOIN相反，用于获取右表所有记录，即使左表没有对应匹配的记录。

### INNNER JOIN

INNER JOIN 返回两个表中满足连接条件的匹配行

```SQL
SElECT column1, column2, ...
FORM table1
INNER JOIN table2 ON table1.column_name = table2.column_name;
```

```SQL
SELECT o.order_id, c.customer_name
FROM orders AS o
INNER JOIN customers AS c ON o.customer_id = c.customer_id;
```

### LEFT JOIN

LEFT JOIN 返回左表的所有行，并包括右表中匹配的行，如果右表中没有匹配的行，将返回NULL值

```SQL
SELECT column1, column2, ...
FROM table1
LEFT JOIN table2 ON table1.column_name = table2.column_name;
```

```SQL
SELECT c.customer_id, c.customer_name, o.order_id
FROM customers AS c
LEFT JOIN orders AS o ON c.customer_id = o.customer_id;
```

选择客户表中的客户ID和客户名称，并包括左表customers中的所有行，以及匹配的订单ID

### RIGHT JOIN

RIGHT JOIN 返回右表的所有行，并包括左表中匹配的行，如果左表中没有匹配的行，将返回NULL值

```SQL
SELECT column1, column2, ...
FROM table1
RIGHT JOIN table2 on table1.column_name = table2.column_name;
```

## SQL 高级教程

### SQL IN

```SQL
SELECT columm1, column2, ...
FORM table_name
WHERE column IN (value1, value2, ...);
```

IN操作符允许我们在WHERE子句中规定多个值

### SQL BETWEEN

BETWEEN操作符选取介于两个值之间的数据范围内的值，这些值可以是数值、文本或者日期

```SQL
SELECT column1, column2, ...
FROM table_name
WHERE column BETWEEN value1 AND value2;
```

### SQL连接(JOIN)

SQL join 用于把来自两个或多个表的行结合起来

- INNER JOIN 返回两个表中满足连接条件的记录（交集）。
- LEFT JOIN 返回左表中的所有记录，即使右表中没有匹配的记录（保留左表）。
- RIGHT JOIN 返回右表中的所有记录，即使左表中没有匹配的记录（保留右表）。
- FULL OUTER JOIN 返回两个表的并集，包含匹配和不匹配的记录。
- CROSS JOIN 返回两个表的笛卡尔积，每条左表记录与每条右表记录进行组合。
- SELF JOIN 将一个表与自身连接。
- NATURAL JOIN 基于同名字段自动匹配连接的表。

#### SQL LEFT JOIN

```SQL
SELECT Customers.Name, Orders.Product
FROM Customers
LEFT JOIN Orders
ON Customers.CustomerID = Orders.CustomerID;
```

### SQL 约束（Constraint）

SQL约束用于规定表中的数据规则
如果存在违反约束二点数据行为，行为会被约束终止

```SQL
CREATE TABLE table_name
(
    column_name1 data_type(size) constraint_name,
    column_name2 data_type(size) constraint_name,
    column_name3 data_type(size) constraint_name,
    ...
);
```

在SQL中，有如下约束：

- NOT NULL - 指示某列不能储存NULL值
- UNIQUE - 保证某列的每行必须有唯一的值
- PRIMARY KEY - NOT NULL 和 UNIQUE 的结合。确保某列有唯一标识，有助于更容易更快速地找到表中的一个特定的记录。
- FOREIGN KEY - 保证一个表中的数据匹配另一个表中的值得参照完整性
- CHECK - 保证列中的值符合指定得条件
- DEFAULT - 规定没有给列赋值时得默认值
- INDEX - 用于快速访问数据库表中得数据。

```SQL
-- NOT NULL
CREATE TABLE Students (
    StudentID INT NOT NULL,
    LastName VARCHAR(50) NOT NULL,
    FirstName VARCHAR(50),
    Age INT
);

-- UNIQUE
CREATE TABLE Employees (
    EmployeeID INT NOT NULL UNIQUE,
    LastName VARCHAR(50) NOT NULL,
    FirstName VARCHAR(50),
    Email VARCHAR(100) UNIQUE
);

-- PRIMARY KEY
CREATE TABLE Orders (
    OrderID INT NOT NULL PRIMARY KEY,
    OrderNumber INT NOT NULL,
    OrderDate DATE NOT NULL
);

-- FOREIGN KEY 确保一个表中的值匹配另一个表中的值，从而建立两表之间的关系
CREATE TABLE Orders (
    OrderID INT NOT NULL PRIMARY KEY,
    OrderNumber INT NOT NULL,
    CustomerID INT,
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);
```

### SQL FOREIGN KEY 约束

一个表中的FOREIGN KEY 指向另一个表中的UNIQUE KEY（唯一约束的键）

```SQL
CREATE TABLE Orders
(
    O_ID INT NOT NULL,
    OrderNo INT NOT NULL,
    P_ID int,
    PRIMARY KEY (O_ID),
    FOREIGN KEY (P_ID) REFERENCES Persons(P_ID)
);
```
