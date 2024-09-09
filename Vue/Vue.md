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

## onMounted

在Vue3中立即调用函数和在onMounted钩子中调用该函数的主要差别在于他们的执行时机以及可以访问的Vue上下文组件之间的差异。

1. **立即调用函数**：当你在组件的*setup*函数中声明并立即调用一个函数的时，这个函数会在组件的初始化立即执行。这意味着他会在组件的其他生命周期钩子之前执行，甚至在模板和DOM被渲染之前。这对于初始化或者状态不依赖于DOM的逻辑是有用的。然而，由于此时组件尚未挂在，所以无法访问到dom元素，同时也无法保证所有的子组件或者异步数据已加载完成。
2. **使用onMounted**：*onMounted*是Vue的一个生命周期钩子，它会在组件的DOM已经挂载到页面上调用。这意味着当你在onMounted钩子中调用函数的时候，你可以安全的执行DOM操作或者执行依赖于已挂载组件的操作。此时，组件已完成了初始渲染，因此对于需要访问或者修改DOM的操作，*onMounted*是一个理想的位置。
