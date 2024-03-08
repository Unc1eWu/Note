# 二分查找

二分查找（Binary Search）是一种高效的搜索算法，用于在已排序的数组或列表中查找特定元素的位置。它的基本思想是每次将搜索范围缩小一半，直到找到目标元素或确定目标元素不存在为止。

## 以下是二分查找的基本思路：

* 初始化：首先，确定搜索范围的左右边界，通常是整个数组或列表。
* 循环迭代：在每一轮迭代中，通过计算左右边界的中间位置，确定中间元素，并将其与目标元素进行比较。
* 比较中间元素：
如果中间元素与目标元素相等，则找到了目标元素，返回其位置。
如果中间元素大于目标元素，则说明目标元素可能在中间元素的左侧，因此将搜索范围缩小到左半部分。
如果中间元素小于目标元素，则说明目标元素可能在中间元素的右侧，因此将搜索范围缩小到右半部分。
* 更新搜索范围：根据中间元素与目标元素的比较结果，更新搜索范围的左右边界，继续迭代搜索。
* 终止条件：重复以上步骤直到找到目标元素或确定目标元素不存在。如果搜索范围为空（左边界大于右边界），则目标元素不存在于数组或列表中。

```java
    public static int lowerBound(int[] nums, int target) {
        int left = 0, right = nums.length - 1; // 闭区间 [left, right]
        while (left <= right) { //区间不为空
            int mid = (left + right) / 2;
            if(nums[mid] < target) {
                left = mid + 1; // [mid + 1, right]
            } else {
                right = mid - 1; // [left, mid - 1]
            }
        }
        return left;
    }
    public static int lowerBound2(int[] nums, int target) {
        int left = 0, right = nums.length; // 左闭右开 [left, right)
        while (left < right) { // 区间不为空
            int mid = (left + right) / 2;
            if(nums[mid] < target) {
                left = mid + 1; // [mid + 1, right)
            } else {
                right = mid; // [left, mid)
            }
        }
        return left;
    }

    public static int lowerBound3(int[] nums, int target) {
        int left = -1, right = nums.length; // 开区间 (left, right)
        while (left + 1 < right) { // 区间不为空
            int mid = (left + right) / 2;
            if(nums[mid] < target) {
                left = mid; // (mid, right)
            } else {
                right = mid; // (left, mid)
            }
        }
        return right;
    }

```

> [二分查找红蓝染色法](https://www.bilibili.com/video/BV1AP41137w7/?vd_source=03c1eabcf2fb00d437570efda606f6f3)

> - 核心要素
    - 注意区间开闭，三种都可以
    - 循环结束条件：当前区间内没有元素
    - 下一次二分查找区间：不能再查找(区间不包含)mid，防止死循环
    - 返回值：大于等于target的第一个下标（注意循环不变量）
    - 有序数组中二分查找的四种类型（下面的转换仅适用于数组中都是整数）
    1. 第一个大于等于x的下标： low_bound(x)
    2. 第一个大于x的下标：可以转换为`第一个大于等于 x+1 的下标` ，low_bound(x+1)
    3. 最后一个一个小于x的下标：可以转换为`第一个大于等于 x 的下标` 的`左边位置`, low_bound(x) - 1;
    4. 最后一个小于等于x的下标：可以转换为`第一个大于等于 x+1 的下标` 的 `左边位置`, low_bound(x+1) - 1;
 >
