# Two Sum

Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

You can return the answer in any order.

Example 1:

Input: nums = [2,7,11,15], target = 9
Output: [0,1]
Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
Example 2:

Input: nums = [3,2,4], target = 6
Output: [1,2]
Example 3:

Input: nums = [3,3], target = 6
Output: [0,1]

## Solution

1. 最开始的思路肯定是brute force， 遍历nums这个数组，对于元素i 寻找是否有元素k存在使得nums[k] == target - nums[i]。这种情况下时间复杂度为O($n^2$)

2. 第二种思路就是使用hash table优化时间复杂度，先建立一个hash table将数组中的所有元素储存进去将数组每位的值作为hash table的key，数组索引位置的值作为value。第二次遍历的时候找当前元素i在数组中的数值和target的余数是否存在在hash table中，并且hash table中的key值不为i本身。如果找到了就return当前的i以及从hash table中读取的complement的key值。

    ```java
    class Solution {
        public int[] twoSum(int[] nums, int target) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                map.put(nums[i], i);
            }
            for (int i = 0; i < nums.length; i++) {
                int complement = target - nums[i];
                if (map.containsKey(complement) && map.get(complement) != i) {
                    return new int[] { i, map.get(complement) };
                }
            }
            // In case there is no solution, we'll just return null
            return null;
        }
    }
    ```
