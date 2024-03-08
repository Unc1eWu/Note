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
    public static int binarySearch(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (mid == target) {
                return mid;
            } else if (mid > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

```