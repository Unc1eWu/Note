package algorithms.Sort;

import java.util.Arrays;

public class BubbleSort {
    public static int[] sort(int[] nums) {
        int[] arr = Arrays.copyOf(nums, nums.length);

        for (int i = 0; i < arr.length; i++) {
            boolean flag = true;
            // 可以将内层循环更新条件优化为 arr.length - 1 - i, 因为数组后i位已经为有序元素。w
            for (int j = 0; j < arr.length - 1; j++) {
                if (arr[j] > arr[j+1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = tmp;
                    flag = false;
                }
            }
            if (flag)
            break;
        }
        return arr;
    }
}
