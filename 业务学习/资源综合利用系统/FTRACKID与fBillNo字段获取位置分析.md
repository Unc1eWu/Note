# FTRACKID 与 fBillNo 字段获取位置分析

## 概述

在资源综合利用系统中，`FTRACKID` 和 `fBillNo` 是两个重要的业务标识字段，用于物流跟踪和业务记录的唯一标识。本文档详细分析了这两个字段在系统中的获取位置和使用场景。

## 字段定义

### FTRACKID

- **含义**：物流跟踪号
- **用途**：作为业务主键，用于匹配和关联业务记录
- **存储位置**：TYMREC1 表的 FTRACKID 字段

### fBillNo

- **含义**：运单号
- **用途**：与 FTRACKID 相同，用于业务记录匹配
- **存储位置**：TYMREC1 表的 FBILLNO 字段

## 获取位置详细分析

### 1. TADC02 电文接收（计量实绩）

**文件位置**：`server/Dev/YM/YMRE/p_ymre_18460/cm_tadc02_rcv.cpp`

**获取代码**：

```cpp
CString FTRACKID = "";//物流跟踪号 当同与其他电文的 FBILLNO
if (bcls_rec->Tables[0].Columns.Contains("FTRACKID"))
    FTRACKID = bcls_rec->Tables[0].Rows[0]["FTRACKID"].ToString().Trim();
Log::Trace("", "", "FTRACKID=[{0}]", FTRACKID);
```

**存储代码**：

```cpp
TYMREC1["FTRACKID"] = FTRACKID;
TYMREC1_TEMP["FTRACKID"] = FTRACKID;
```

**业务场景**：接收计量实绩时，通过 FTRACKID 关联业务记录

### 2. TADC01 电文接收（装车确认）

**文件位置**：`server/Dev/YM/YMRE/p_ymre_18460/cm_tadc01_rcv.cpp`

**获取代码**：

```cpp
CString FTRACKID = "";//物流跟踪号 当同与其他电文的 FBILLNO
if (bcls_rec->Tables[0].Columns.Contains("FTRACKID"))
    FTRACKID = bcls_rec->Tables[0].Rows[0]["FTRACKID"].ToString().Trim();
Log::Trace("", "", "FTRACKID=[{0}]", FTRACKID);
```

**存储代码**：

```cpp
TYMREC1["FBILLNO"] = FTRACKID;
TYMREC1_TEMP["FBILLNO"] = FTRACKID;
```

**业务场景**：装车确认时，通过 FTRACKID 更新相关业务状态

### 3. TADC03 电文接收（卸车确认）

**文件位置**：`server/Dev/YM/YMRE/p_ymre_18460/cm_tadc03_rcv.cpp`

**获取代码**：

```cpp
CString fBillNo = ""; // 运单号 必填
if (bcls_rec->Tables[0].Columns.Contains("fBillNo"))
    fBillNo = bcls_rec->Tables[0].Rows[0]["fBillNo"].ToString().Trim();
Log::Trace("", "", "fBillNo=[{0}]", fBillNo);
```

**匹配逻辑**：

```cpp
TYMREC1["FTRACKID"] = fBillNo;
if (TYMREC1.Query("FTRACKID") > 0) // 找到匹配记录
```

**业务场景**：卸车确认时，通过 fBillNo 匹配业务记录进行状态更新

### 4. TADC04 电文接收（装车确认）

**文件位置**：`server/Dev/YM/YMRE/p_ymre_18460/cm_tadc04_rcv.cpp`

**获取代码**：

```cpp
CString fBillNo = ""; // 运单号 必填
if (bcls_rec->Tables[0].Columns.Contains("fBillNo"))
    fBillNo = bcls_rec->Tables[0].Rows[0]["fBillNo"].ToString().Trim();
Log::Trace("", "", "fBillNo=[{0}]", fBillNo);
```

**匹配逻辑**：

```cpp
TYMREC1["FTRACKID"] = fBillNo;
if (TYMREC1.QueryCount("FTRACKID") > 0) // 找到匹配记录
```

**业务场景**：装车确认时，通过 fBillNo 匹配业务记录进行状态更新

## 数据流转关系

### 1. 字段映射关系

外部系统 fBillNo → 系统内部 FTRACKID → TYMREC1.FTRACKID
外部系统 FTRACKID → 系统内部 FTRACKID → TYMREC1.FTRACKID

### 2. 业务处理流程

1. **外部系统**发送电文，携带 fBillNo 或 FTRACKID
2. **电文接收模块**解析字段值
3. **业务处理模块**通过字段值匹配 TYMREC1 表记录
4. **状态更新**根据业务逻辑更新相关字段

## 关键业务逻辑

### 1. 字段验证

- fBillNo 为空时，系统会返回错误，不允许继续处理
- FTRACKID 为空时，系统会记录日志但允许继续处理

### 2. 记录匹配

- 通过 FTRACKID 字段在 TYMREC1 表中查找匹配记录
- 如果找到匹配记录，则进行相应的业务处理
- 如果未找到匹配记录，则返回错误信息

### 3. 状态更新

- 装车确认：更新 LOAD_CONFM_FLAG、LOAD_CONFM_TIME 等字段
- 卸车确认：更新 UNLOAD_CONFM_FLAG、UNLOAD_CONFM_TIME 等字段
- 计量实绩：更新 GROSS_WT、TARE_WT、NET_WT 等字段

## 数据库表结构

### TYMREC1 表相关字段

```sql
FTRACKID    VARCHAR(50)  -- 物流跟踪号
FBILLNO     VARCHAR(50)  -- 运单号
WEIGH_NO    VARCHAR(50)  -- 磅单号
WORK_SEQ_NO VARCHAR(50)  -- 作业流水号
```

## 注意事项

### 1. 字段一致性

- FTRACKID 和 fBillNo 在业务上具有相同的含义
- 不同电文可能使用不同的字段名，但指向同一个业务概念

### 2. 必填字段处理

- fBillNo 在某些电文中是必填字段，为空时会阻止业务处理
- FTRACKID 通常作为可选字段处理

### 3. 错误处理

- 当无法找到匹配记录时，系统会返回明确的错误信息
- 建议在业务处理前进行字段值的有效性检查

## 相关文件清单

| 文件名 | 路径 | 功能描述 |
|--------|------|----------|
| cm_tadc01_rcv.cpp | server/Dev/YM/YMRE/p_ymre_18460/ | 装车确认接收 |
| cm_tadc02_rcv.cpp | server/Dev/YM/YMRE/p_ymre_18460/ | 计量实绩接收 |
| cm_tadc03_rcv.cpp | server/Dev/YM/YMRE/p_ymre_18460/ | 卸车确认接收 |
| cm_tadc04_rcv.cpp | server/Dev/YM/YMRE/p_ymre_18460/ | 装车确认接收 |
| xdcdb01.h | server/Include/ | 数据库表结构定义 |

## 总结

FTRACKID 和 fBillNo 字段在资源综合利用系统中扮演着重要的业务标识角色，通过这两个字段实现了不同系统间的数据关联和业务状态同步。理解这些字段的获取位置和使用场景，对于系统维护和业务开发具有重要意义。
