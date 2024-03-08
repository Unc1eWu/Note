# Add Tow Numbers

You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Example 1:

**Input**: l1 = [2,4,3], l2 = [5,6,4]
**Output**: [7,0,8]
**Explanation**: 342 + 465 = 807.

Example 2:

**Input**: l1 = [0], l2 = [0]
**Output**: [0]

Example 3:

**Input**: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
**Output**: [8,9,9,9,0,0,0,1]

## Solution

思路总体上就是使用一个空表头方便后续返回相加后的list，然后使用carry记录进位，若相加的两个链表长度不一样，将较短的那条链表缺失的部分补0再进行相加。若遍历完成后carry值为1在新链表的最前方添加新节点1.

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode pre = new ListNode(0);
        ListNode cur = pre;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int x = (l1 != null) ? l1.val : 0;
            int y = (l2 != null) ? l2.val : 0;
            int sum = x + y + carry;
            carry = sum / 10;
            sum = sum % 10;
            cur.next = new ListNode(sum);
            cur = cur.next;

            if(l1.next != null) l1 = l1.next;
            if(l2.next != null) l2 = l2.next;
        }
        if (carry == 1) {
            cur.next = new ListNode(carry);
        }
        return pre.next;
    }
}

```
