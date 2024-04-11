# 四数之和

给你四个整数数组 nums1、nums2、nums3 和 nums4 ，数组长度都是 n ，请你计算有多少个元组 (i, j, k, l) 能满足：

0 <= i, j, k, l < n
nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0

示例 1：

输入：nums1 = [1,2], nums2 = [-2,-1], nums3 = [-1,2], nums4 = [0,2]
输出：2
解释：
两个元组如下：

1. (0, 0, 0, 1) -> nums1[0] + nums2[0] + nums3[0] + nums4[1] = 1 + (-2) + (-1) + 2 = 0
2. (1, 1, 0, 0) -> nums1[1] + nums2[1] + nums3[0] + nums4[0] = 2 + (-1) + (-1) + 0 = 0

## Solution and Insights

使用hashamp来完成，只要求我们求出有多少组数满足四数之和为0，所以我们可以把nums1 + nums2放在set中，key为和，value为出现次数。再对nums3 + nums4进行是否在set中出现做判断。 和为0所以满足 nums1 + nums2 = - nums3 - nums4. 即key为 -(nums3 + nums4). 取出改value加到result上，循环结束返回result。

![题解](./Assets/454.png)
