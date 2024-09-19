# Vue

- [Vue](#vue)
  - [什么是Vue](#什么是vue)
    - [Vue的核心功能](#vue的核心功能)
    - [单文件组件](#单文件组件)
  - [onMounted](#onmounted)
  - [创建一个Vue应用](#创建一个vue应用)
    - [安装依赖并启动开发服务器](#安装依赖并启动开发服务器)
    - [将应用发布到生产环境](#将应用发布到生产环境)
  - [创建应用](#创建应用)
    - [应用实列](#应用实列)
    - [挂载应用](#挂载应用)
    - [DOM中根组件的模板](#dom中根组件的模板)
  - [模板语法](#模板语法)
    - [文本插值](#文本插值)
    - [Attribute绑定](#attribute绑定)
    - [简写与同名简写](#简写与同名简写)
    - [布尔型Attribute](#布尔型attribute)
    - [动态绑定多个值](#动态绑定多个值)
    - [使用JavaScript表达式](#使用javascript表达式)
    - [参数Arguments](#参数arguments)
  - [响应式基础](#响应式基础)
    - [声明式响应基础](#声明式响应基础)
  - [\<script setup\>](#script-setup)
    - [为什么要使用ref？](#为什么要使用ref)
    - [深层响应性](#深层响应性)
    - [DOM更新时机](#dom更新时机)
    - [reactive()](#reactive)
    - [Reactive()的局限性](#reactive的局限性)
    - [数组和集合的注意事项](#数组和集合的注意事项)
  - [计算属性](#计算属性)
    - [计算属性缓存vs方法](#计算属性缓存vs方法)
  - [类与样式绑定](#类与样式绑定)
  - [条件渲染](#条件渲染)
    - [v-if](#v-if)
    - [v-else](#v-else)
    - [template上的v-if](#template上的v-if)
    - [v-show](#v-show)
  - [列表渲染](#列表渲染)
    - [v-for](#v-for)
    - [v-for 与对象](#v-for-与对象)
    - [\<template\>上的v-for](#template上的v-for)
    - [通过Key管理状态](#通过key管理状态)
    - [数组变化侦测](#数组变化侦测)
    - [展示过滤或排序后的结果](#展示过滤或排序后的结果)
  - [事件处理](#事件处理)
    - [监听事件](#监听事件)
    - [内联事件处理器](#内联事件处理器)
    - [方法事件处理器](#方法事件处理器)
    - [在内联处理器中调用方法](#在内联处理器中调用方法)
    - [事件修饰符](#事件修饰符)
  - [表单输入绑定](#表单输入绑定)
    - [基本用法](#基本用法)
      - [文本](#文本)
      - [多行文本](#多行文本)
      - [复选框](#复选框)
      - [单选按钮](#单选按钮)
      - [选择器](#选择器)
  - [生命周期钩子](#生命周期钩子)
    - [注册周期钩子](#注册周期钩子)
  - [侦听器](#侦听器)
    - [基本示例](#基本示例)
    - [侦听的数据源类型](#侦听的数据源类型)
  - [模板引用](#模板引用)
    - [访问模板引用](#访问模板引用)
    - [函数模板引用](#函数模板引用)
    - [组件上的ref](#组件上的ref)

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
需要注意的是reactive()返回的是一个原始对象的proxy，它和原始对象是不对等的

```js
const raw = {}
const proxy = reactive(raw)

// 代理对象和原对象不是完全相等的
console.log(proxy === raw) // false
```

只有代理对象是响应式的，更改原始对象不会触发更新。因此，使用Vue的响应式系统的最佳实践是仅使用你声明对象的代理版本。

为保证访问代理的一致性，对同一个原始对象调用reactive()总是会返回同样的代理对象，而对一个已存在的代理对象调用reactive()会返回其本身

```js
console.log(reactive(raw) === proxy) // true

console.log(reactive(proxy) === proxy) // true
```

这个规则对深层嵌套的对象也适用

```js
const proxy = reactive({})

const raw = {}
proxy.nested = raw

console.log(proxy.nested === raw) // false
```

### Reactive()的局限性

1. 有限的值类型：只能用于对象类型。不能持有string, number, boolean这样的原始类型
2. 不能替换整个对象: Vue的响应式跟踪是通过属性访问完成的，因此我们必须始终保持对响应式对象的引用。这意味着我们不能轻易替换响应式对象，这样的话与第一个引用的响应性连接将丢失
3. 对解构操作不友好: 当我们将响应式对象的原始类型属性解构为本地变量时，或者将该属性传递给函数时，我们将丢失响应性连接

### 数组和集合的注意事项

与reactive对象不同的是，当ref作为响应式数组或原生集合类型（如Map）中元素被访问时，它不会被解包：

```js
const books = reactive([ref('Vue 3 Guide')])
console.log(books[0].value)

const map = reactive(new Map([['count', ref(0)]]))
console.log(map.get('count').value)
```

## 计算属性

模板中的表达式虽然方便，但也只能用来做简单的操作。如果在模板中写太多逻辑，会让模板变得臃肿，难以维护。比如说，我们有这样一个包含嵌套数组的对象：

```js
const author = reactive({
    name: 'John Doe',
    books: [
        'Vue 2 - Advanced Guide',
        'Vue 3 - Basic Guide',
        'Vue 4 - The Mystery'
    ]
})
```

更重要的是，如果在模板中需要不止一次这样的计算，我们可不想将这样的代码在模板里重复好多遍。

因此我们推荐使用计算属性来描述依赖响应式状态的复杂逻辑。这是重构后的示例：

```js
<script setup>
import { reactive, computed } form 'vue'

const author = reactive({
    name: 'John Doe',
    books: [
        'Vue 2 - Advanced Guide',
        'Vue 3 - Basic Guide',
        'Vue 4 - The Mystery'
    ]
})

const publishedBookMessage = computed(() => {
    return author.books.length > 0 ? 'Yes' : 'No'
})

</script>
<template> 
    <p>Has published books:</p>
    <span> {{ publishedBookMessage }} </span>
</template>
```

### 计算属性缓存vs方法

如果调用函数也会获得和计算属性相同的结果，若我们将同样的函数定义为一个方法而不是计算属性，两种方式在结果上确实是完全相同的，然而，不同之处在于计算属性值会基于其响应式依赖被缓存。一个计算属性仅会在其响应式依赖更新时才重新计算。这意味着只要 author.books 不改变，无论多少次访问 publishedBooksMessage 都会立即返回先前的计算结果，而不用重复执行 getter 函数。

## 类与样式绑定

数据绑定的一个常见需求场景是操纵元素的 CSS class 列表和内联样式。因为 class 和 style 都是 attribute，我们可以和其他 attribute 一样使用 v-bind 将它们和动态的字符串绑定。但是，在处理比较复杂的绑定时，通过拼接生成字符串是麻烦且易出错的。因此，Vue 专门为 class 和 style 的 v-bind 用法提供了特殊的功能增强。除了字符串外，表达式的值也可以是对象或数组。

## 条件渲染

### v-if

v-if 指令用于条件性地渲染一块内容。这块内容只会在指令的表达式返回真值时才被渲染。

`<h1 v-if="awesome"> Vue is awesome!</h1>`

### v-else

你也可以使用v-else为v-if添加一个else区块

```template
<button @click="awesome = !awesome">Toggle</button>

<h1 v-if="awesome">Vue is awesome!</h1>
<h1 v-else>Oh no</h1>
```

一个 v-else 元素必须跟在一个 v-if 或者 v-else-if 元素后面，否则它将不会被识别。

### template上的v-if

因为 v-if 是一个指令，他必须依附于某个元素。但如果我们想要切换不止一个元素呢？在这种情况下我们可以在一个 <template\> 元素上使用 v-if，这只是一个不可见的包装器元素，最后渲染的结果并不会包含这个 <template\> 元素。

### v-show

另一个可以用来按条件显示一个元素的指令是 v-show。其用法基本一样：

`<h1 v-show="ok"> hello </h1>`

不同之处在于 v-show 会在 DOM 渲染中保留该元素；v-show 仅切换了该元素上名为 display 的 CSS 属性。

## 列表渲染

### v-for

我们可以使用 v-for 指令基于一个数组来渲染一个列表。v-for 指令的值需要使用 item in items 形式的特殊语法，其中 items 是源数据的数组，而 item 是迭代项的别名：

```js
const items = ref([{ message : 'Foo'}, { message: 'Bar' }])
```

```vue
<li v-for='item in items'>
   {{ item.message }}
</li>
```

对于多层嵌套的 v-for，作用域的工作方式和函数的作用域很类似。每个 v-for 作用域都可以访问到父级作用域：

```vue
<li v-for="item in items">
    <span v-for="childItem in item.children">
        {{item.message}} {{childItem}}
    </span>
</li>
```

### v-for 与对象

你也可以使用 v-for 来遍历一个对象的所有属性。遍历的顺序会基于对该对象调用 Object.values() 的返回值来决定。

```js
const myObject = reactive({
    title : 'How to do lists in Vue',
    author : 'Jane Doe',
    publishedAt : '2016-04-10'
})
```

```vue
<li v-for="value in myObject">
    {{ value }}
</li>
```

### <template\>上的v-for

与模板上的 v-if 类似，你也可以在 <template\> 标签上使用 v-for 来渲染一个包含多个元素的块。例如:

```vue
<ul>
    <template v-for="item in items">
        <li>{{ item.msg }}</li>
        <li class="divider" role="presentation"</li>
    </template>
</ul>
```

### 通过Key管理状态

Vue 默认按照“就地更新”的策略来更新通过 v-for 渲染的元素列表。当数据项的顺序改变时，Vue 不会随之移动 DOM 元素的顺序，而是就地更新每个元素，确保它们在原本指定的索引位置上渲染。

默认模式是高效的，但只适用于列表渲染输出的结果不依赖子组件或者临时DOM状态

为了给 Vue 一个提示，以便它可以跟踪每个节点的标识，从而重用和重新排序现有的元素，你需要为每个元素对应的块提供一个唯一的 key attribute：

当你使用 <template v-for、> 时，key 应该被放置在这个 <template、> 容器上：

```vue
<div v-for="item in items" :key="item.id">
    <!-- 内容 -->
</div>

<template v-for="todo in todos" :key="todo.name">
    <li>{{ todo.name }}</li>
</template>
```

推荐在任何可行的时候为 v-for 提供一个 key attribute，除非所迭代的 DOM 内容非常简单 (例如：不包含组件或有状态的 DOM 元素)，或者你想有意采用默认行为来提高性能。

key 绑定的值期望是一个基础类型的值，例如字符串或 number 类型。不要用对象作为 v-for 的 key。关于 key attribute 的更多用途细节，请参阅 key API 文档。

### 数组变化侦测

Vue 能够侦听响应式数组的变更方法，并在它们被调用时触发相关的更新。这些变更方法包括：

- push()
- pop()
- shift()
- unshift()
- splice()
- sort()
- reverse()

### 展示过滤或排序后的结果

有时，我们希望显示数组经过过滤或排序后的内容，而不实际变更或重置原始数据。在这种情况下，你可以创建返回已过滤或已排序数组的计算属性。

```js
const numbers = ref([1, 2, 3, 4, 5])

const evenNumbers = computed(() => {
    return numbers.value.filter((n) => n % 2 === 0)
})
```

```vue
<li v-for="n in evenNumbers">{{ n }}</li>
```

在计算属性中使用 reverse() 和 sort() 的时候务必小心！这两个方法将变更原始数组，计算函数中不应该这么做。请在调用这些方法之前创建一个原数组的副本。

## 事件处理

### 监听事件

我们可以使用v-on指令（简写为@）来监听DOM事件，并在事件触发时执行对应的JavaScript。用法：`v-on:click="handler" 或 @click="handler"`
事件处理器的值可以是：

1. 内联事件处理器：事件被触发时执行内联JavaScript语句
2. 方法事件处理器：一个指向组件上定义的方法的属性名或路径

### 内联事件处理器

```js
const count = ref(0)

<button @click="count++">Add 1</button>
<p>Count is {{ count }}</p>
```

### 方法事件处理器

当事件处理器的逻辑越来越复杂，内联代码的方式变得不够灵活，因此v-on可以接受一个方法名或对某个方法的调用

```js
const name = ref('Vue.js')

function greet(event) {
    alert('Hello ${name.value}!')
    if (event) {
        alert(event.target.getName)
    }
}

<button @click="greet">Greet</button>
```

### 在内联处理器中调用方法

除了直接绑定方法名外还可以在内联事件处理器中调用方法。这允许我们传入自定义参数以替代原生事件：

```js
function say(message) {
    alert(message)
}

<button @click="say('hello')">Say hello</button>
<button @click="say('byt')">Say bye</button>
```

### 事件修饰符

在处理事件时调用event.preventDefault()或event.stopPropagation()是很常见的。尽管我们可以直接在方法内调用，但如果使方法更关注于数据的处理而不是DOM的事件细节会更好。

## 表单输入绑定

在前端处理表单时，我们常常需要将表单输入框的内容同步给 JavaScript 中相应的变量。手动连接值绑定和更改事件监听器可能会很麻烦：v-model指令帮我们简化了这一步骤

```c
<input
    :value="text"
    @input="event => text = evnet.target.value">

<input v-model="text">
```

### 基本用法

#### 文本

```vue
<p> Message is {{ message }} </p>
<input v-model="message" placeholder="edit me" />
```

#### 多行文本

```vue
<span>Multiline message is:</span>
<p style="white-space :pre-line;">{{ message }}</p>
<textarea v-model="message" placeholder="add multiple lines"></textarea>
```

#### 复选框

单一的复选框，绑定布尔类型值：

```vue
<input type="checkbox" id="checkbox" v-model="checked" />
<label for="checkbox">{{ checked }}</label>
```

```vue
const checkedNames = ref([])

<div>Checked names: {{ checkedNames }}</div>
<input type="checkbox" id="jack" value="jack" v-model="checkedNames" />
<label for="jack">Jack</label>

<input type="checkbox" id="john" value="john" v-model="checkedNames" />
<label for="john">John</label>
```

在这个例子中，checkedNames数组将始终包含所有被选中的框的值。

#### 单选按钮

```vue
<div>Picked: {{ picked }}</div>

<input type="radio" id="one" value="One" v-model="picked" />
<label for="one">One</label>

<input type="radio" id="two" value="Two" v-model="picked" />
<label for="two">Two</label>
```

#### 选择器

单个选择器的示例如下：

```vue
<div>Selected: {{selected}}</div>

<select v-model="selected">
    <option disabled value="">Please select one</option>
    <option>A</option>
    <option>B</option>
    <option>C</option>
</select>
```

## 生命周期钩子

每个 Vue 组件实例在创建时都需要经历一系列的初始化步骤，比如设置好数据侦听，编译模板，挂载实例到 DOM，以及在数据改变时更新 DOM。在此过程中，它也会运行被称为生命周期钩子的函数，让开发者有机会在特定阶段运行自己的代码。

### 注册周期钩子

举例来说，onMounted 钩子可以用来在组件完成初始渲染并创建 DOM 节点后运行代码：

```vue
<script setup>
    import { onMounted } from 'vue'

    onMounted(() => {
        console.log(`the component is now mounted.`)
    })
</script>
```

![Vue生命周期图示](/Vue/img/lifePeriod.png "LifeCycle")

## 侦听器

### 基本示例

计算属性允许我们声明性地计算衍生值。然而在有些情况下，我们需要在状态变化时执行一些“副作用”：例如更改 DOM，或是根据异步操作的结果去修改另一处的状态。

```vue
<script setup>
    import { ref, watch } from 'vue'

    const question = 'ref'
    const answer = ref('Question usually contain a question mark. ;-)')
    const loading = ref(false)

    watch(question, async (newQuestion, oldQuestion) => {
        if (newQuestion.include('?')) {
            loading.value = true
            answer.value = 'Thinking...'
            try {
                const res = await fetch('https://yesno.wtf/api')
                answer.value = (await res.json(()).answer)
            } catch (error) {
                answer.value = 'Error! Could not reach the API. ' + error
            } finally {
                loading.value = false
            }
        }
    })
</script>

<template>
    <p>
        Ask you a yes/no question:
        <input v-model="question" :disabled="loading" />
    </p>
    <p>{{ answer }}</p>
</template>
```

### 侦听的数据源类型

watch的第一个参数类型可以是不同的“数据源”：它可以是一个ref（包括计算属性）、一个响应式对象、一个getter函数、或多个数据源组成的数组：

```js
const x = ref(0)
const y = ref(0)

// 单个ref
watch(x, (newX) => {
    console.log(`x is ${newX}`)
})

// getter函数
watch(
    () => x.value + y.value,
    (sum) => {
        console.log(`sum of x + y is: ${sum}`)
    }
)

// 多个来源组成的数组
watch([x, () => y.value], ([newX, newY]) => {
    console.log(`x is ${newX} and y is ${newY}`)
})
```

## 模板引用

虽然 Vue 的声明性渲染模型为你抽象了大部分对 DOM 的直接操作，但在某些情况下，我们仍然需要直接访问底层 DOM 元素。要实现这一点，我们可以使用特殊的 ref attribute：

`<input ref="input">`

ref 是一个特殊的 attribute，和 v-for 章节中提到的 key 类似。它允许我们在一个特定的 DOM 元素或子组件实例被挂载后，获得对它的直接引用。这可能很有用，比如说在组件挂载时将焦点设置到一个 input 元素上，或在一个元素上初始化一个第三方库。

### 访问模板引用

为了通过组合式 API 获得该模板引用，我们需要声明一个匹配模板 ref attribute 值的 ref：

```vue
<script setup>
    import { ref, onMounted } from 'vue'

    // 声明一个ref来存放该元素的引用
    // 必须和模板里的ref同名

    const input = ref(null)
    onMounted(() => {
        input.value.focus()
    }) 
</script>

<template>
    <input ref="input" />
</template>
```

注意，你只可以在组件挂载后才能访问模板引用。如果你想在模板中的表达式上访问 input，在初次渲染时会是 null。这是因为在初次渲染前这个元素还不存在呢！

### 函数模板引用

除了使用字符串值作名字，ref attribute 还可以绑定为一个函数，会在每次组件更新时都被调用。该函数会收到元素引用作为其第一个参数：
`<input :ref="(el) => { /* 将 el 赋值给一个数据属性或者 ref 变量*/} "`
注意我们这里需要使用动态的 :ref 绑定才能够传入一个函数。当绑定的元素被卸载时，函数也会被调用一次，此时的 el 参数会是 null。你当然也可以绑定一个组件方法而不是内联函数。

### 组件上的ref

模板引用也可以被用在一个子组件上。这种情况下引用中获得的值是组件实例：

```vue
<script setup>
    import { ref, onMounted } form 'vue'
    import Child from './Child.vue'

    const child = ref(null)

    onMounted(() => {
        // child.value 是 <Child /> 组件的实例
    })
</script>

<template>
    <Child ref="child" />
</template>
```

如果一个子组件使用的是选项式 API 或没有使用 <script setup\>，被引用的组件实例和该子组件的 this 完全一致，这意味着父组件对子组件的每一个属性和方法都有完全的访问权。这使得在父组件和子组件之间创建紧密耦合的实现细节变得很容易，当然也因此，应该只在绝对需要时才使用组件引用。大多数情况下，你应该首先使用标准的 props 和 emit 接口来实现父子组件交互。
