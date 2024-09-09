# Vue

## 什么是Vue

Vue是一款构建用户界面的JavaScript框架，基于标准JavaSc，HTML和CSS构建，并提供了一套声明式、组件化的编程模型。

### Vue的核心功能

**声明式渲染：** Vue 基于标准 HTML 拓展了一套模板语法，使得我们可以声明式地描述最终输出的 HTML 和 JavaScript 状态之间的关系。
**响应性：** Vue 会自动跟踪 JavaScript 状态并在其发生变化时响应式地更新 DOM。响应式数据的实现是通过对数据对象进行劫持和监听，当数据发生改变时，依赖于该数据的视图会自动更新。

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

## 创建一个Vue应用

`npm create vue@latest || yarn create vue@latest`

上方指令安装并执行create-vue,他是Vue官方的脚手架工具。

### 安装依赖并启动开发服务器

`cd <your-project-name`
`npm install || yarn`
`npm run dev || yarn dev`

### 将应用发布到生产环境

`npm run build || yarn build`

此命令会在 ./dist 文件夹中为你的应用创建一个生产环境的构建版本。

## 创建应用

### 应用实列

每个应用都是通过createApp函数创建一个新的**应用实例**：

```vue
import { createApp } from 'vue'

const app = createApp({
    <!-- 根组件选项 -->
})
```

### 挂载应用

应用实例必须调用了`.mount()`方法后才会渲染出来。该方法接受一个容器参数，可以是一个实际的DOM元素或者是CSS选择器字符串。

`<div id = 'app'></div>`

`app.mount('#app')`

应用根组件的内容将会被渲染在容器元素内。容器元素自己不会被视为应用的一部分。

### DOM中根组件的模板

```html
<div id='app'>
    <button @click="count++">{{count}}</button>
</div>
```

```js
import {createApp} from 'vue'

const app = createApp({
    data() {
        return {
            count:0
        }
    }
})

app.mount('#app')
```

## 模板语法

### 文本插值

最基本的数据绑定形式是文本插值，使用‘Mustache’语法（双大括号）：

`<span> Message:{{msg}} </span>`

双大括号标签会被替换为相应组件实例中的msg值，每次msg属性改变它也会同步更新。

### Attribute绑定

双大括号不能再HTML attribute中使用。想要响应式地绑定一个attribute，应该使用v-bind指令

`<div v-bind:id='dynamicID'></div>`

v-bind指令将指示Vue将元素的id与组件的dynamicID属性保持一致。如果绑定的值是null或者undefined，那么该attribute将会从渲染的元素上移除。

### 简写与同名简写

因为v-bind非常常用，所以有以下简写

`<div :id='dynamicID'></div>`

如果attribute的名称与绑定的JavaScript值的名称相同，可以进一步省略语法，省略attribute的值

`<div :id></div>`

### 布尔型Attribute

布尔型attribute根据true/false值来决定是否存在于该元素上, disabled就是最常见的例子之一。

`<button :disabled="isButtonDisabled">Button</button>`

当 isButtonDisabled 为真值或一个空字符串时，元素会包含这个 disabled attribute。而当其为其他假值时 attribute 将被忽略。

### 动态绑定多个值

```js
const objectOfAttrs = {
    id: 'contained',
    class: 'wrapper',
    style: 'background-color:green'
}
```

通过不带参数的v-bind，可以将其绑定到单个元素上:

`<div v-bind="objectOfAttrs"></div>`

### 使用JavaScript表达式

Vue在所有的数据绑定中都支持完整的JavaScript表达式:

```template
{{ number + 1 }}

{{ ok ? 'YES' : 'NO'}}

{{ message.split('').reverse().join('') }}

<div :id="`list-${id}`"></div>
```

这些表达式都会被作为 JavaScript ，以当前组件实例为作用域解析执行。
在 Vue 模板内，JavaScript 表达式可以被使用在如下场景上：

- 在文本插值中 (双大括号)
- 在任何 Vue 指令 (以 v- 开头的特殊 attribute) attribute 的值中

### 参数Arguments

某些指令会需要一个“参数”，在指令名后通过一个冒号隔开做标识。例如用 v-bind 指令来响应式地更新一个 HTML attribute：

```vue
<a v-bind:href="url"> ... </a>

<!-- 简写 -->
<a :href="url"> ... </a>
```

这里 href 就是一个参数，它告诉 v-bind 指令将表达式 url 的值绑定到元素的 href attribute 上。在简写中，参数前的一切 (例如 v-bind:) 都会被缩略为一个 : 字符。

另一个例子是 v-on 指令，它将监听 DOM 事件：

```vue
<a v-on:click="doSomething"> ... </a>

<!-- 简写 -->
<a @click="doSomething"> ... </a>
```

这里的参数是要监听的事件名称：click。v-on 有一个相应的缩写，即 @ 字符。

## 响应式基础

### 声明式响应基础

ref()

```js
import { ref } from 'vue'

const count = ref(0)
```

ref()接受参数，并将其包裹在一个带有 .value 属性的 ref 对象中返回：

```js
const count = ref(0)

console.log(count) // { value: 0 }
console.log(count.value) // 0

count.value++
console.log(count.value) // 1
```

要在组件模板中访问 ref，请从组件的 setup() 函数中声明并返回它们：

```js
import { ref } from 'vue'

export default {
    // `setup` 是一个特殊的钩子，专门用于组合式 API。
    setup() {
        const count = ref(0)

        // 将 ref 暴露给模板
        return {
            count
        }
    }
}
```

对于更复杂的逻辑，我们可以再同一作用域内声明更改ref的函数，并将它们作为方法与状态一起公开：

```js
import { ref } from 'vue'

export default {
  setup() {
    const count = ref(0)

    function increment() {
      // 在 JavaScript 中需要 .value
      count.value++
    }

    // 不要忘记同时暴露 increment 函数
    return {
      count,
      increment
    }
  }
}
```

然后暴露的方法可以作为事件监听器：

```vue
<button @click="increment">
    {{ count }}
</button>
```

## <script setup\>

在 setup() 函数中手动暴露大量的状态和方法非常繁琐。幸运的是，我们可以通过使用单文件组件 (SFC) 来避免这种情况。我们可以使用 <script setup\> 来大幅度地简化代码：

```vue
<script setup>
    import { ref } from 'vue'

    const count = ref(0)

    function increment() {
        count.value++
    }
</script>

<template>
    <button @click="increment">
        {{ count }}
    </button>
</template>
```

setup函数中的顶层的导入、声明的变量和函数可在同一组件的模板中直接使用。你可以理解为模板是在同一作用域内声明的一个 JavaScript 函数它可以自然可以访问与它一起声明的所有内容。

### 为什么要使用ref？

当你在模板中使用了一个 ref，然后改变了这个 ref 的值时，Vue 会自动检测到这个变化，并且相应地更新 DOM。这是通过一个基于依赖追踪的响应式系统实现的。当一个组件首次渲染时，Vue 会**追踪**在渲染过程中使用的每一个 ref。然后，当一个 ref 被修改时，它会**触发**追踪它的组件的一次重新渲染。

### 深层响应性

Ref可以持有任何类型的值，包括深层嵌套的对象、数组或者JS内置的数据结构，比如 **Map**

Ref会使他的值具有深层响应性。这意味着即使改变嵌套对象或者数组时，变化也会被检测到：

```js
import { ref } from 'vue'

const obj = ref({
  nested: { count: 0 },
  arr: ['foo', 'bar']
})

function mutateDeeply() {
  // 以下都会按照期望工作
  obj.value.nested.count++
  obj.value.arr.push('baz')
}
```

### DOM更新时机

当你修改了响应式状态的时候，DOM会被自动更新。但需要注意的是，DOM更新不是同步的。Vue组件会在“next tick”更新周期中缓冲所有状态的修改，以确保不管你进行了多少次状态修改，每个组件只会执行一次。

```js
import { nextTick } from 'vue'

async function increment() {
    count.value++
    await nextTick()
}
```

### reactive()

还有另一种声明响应式状态的方式，即使用 reactive() API。与将内部值包装在特殊对象中的 ref 不同，reactive() 将使对象本身具有响应性：

```js
import { reactive } from 'vue'

const state = reactive({ count:0})
```

在模板中使用：

```vue
<button @click="state.count++">
    {{ state.count }}
</button>
```

响应式对象是 JavaScript 代理，其行为就和普通对象一样。不同的是，Vue 能够拦截对响应式对象所有属性的访问和修改，以便进行依赖追踪和触发更新。
