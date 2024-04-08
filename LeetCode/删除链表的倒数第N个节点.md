# 删除链表的倒数第N个节点

给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。

## Solution and Insights

开始看成是删除链表的第N个节点，那就比较简单使用count计数然后两个指针，fast指针先走一步后，fast指针和slow指针同步往下更新。直到计数为0。

但题目为倒数第N个节点，思路还是类似采用双指针法，我们要明确删除倒数第N个节点那么我们要找到倒数第N个节点的前一个节点。让fast指针先走N步后同步更新fast指针和slow指针这样当fast指针指向null的时候slow指针指向的就是要删除的节点，但我们需要找到他的前一个节点，所以fast指针应当走N+1步，然后slow指针指向的节点就是我们操作的节点。

```java
ListNode dummy = new ListNode();
dummy.next = head;

ListNode fast = dummy;
ListNode slow = dummy;

for(int i = 0; i < n + 1; i++){
    fast = fast.next;
}
while(fast != null) {
    fast = fast.next;
    slow = slow.next;
}
slow.next = slow.next.next;
return dummy.next;
```
