# Vue

## 什么是Vue

Vue是一款构建用户界面的JavaScript框架，基于标准JavaSc，HTML和CSS构建，并提供了一套声明式、组件化的编程模型。

### Vue的核心功能

**声明式渲染：** Vue 基于标准 HTML 拓展了一套模板语法，使得我们可以声明式地描述最终输出的 HTML 和 JavaScript 状态之间的关系。
**响应性：** Vue 会自动跟踪 JavaScript 状态并在其发生变化时响应式地更新 DOM。

### 单文件组件

在大多数启用了构建工具的Vue项目中，我们可以使用一种类似HTML格式的文件来书写Vue组件，它被称为单文件组件。Vue单文件组件将一个组件的逻辑(JavaScript)，模板(HTML)和样式(CSS)封装在同一个文件中。

```Vue
<template>
    <button @click="count++">Count is: {{ count }}</button>
</template>

<script setup>
    import {ref} from 'vue'
    const count = ref(0)
</script>
<style>
    button {
        font-weight: bold;
    }
</style>
```
