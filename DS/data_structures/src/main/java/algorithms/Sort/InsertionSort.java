package algorithms.Sort;

import java.util.Arrays;

public class InsertionSort {
    public static int[] sort(int[] nums) {
        int[] arr = Arrays.copyOf(nums, nums.length);

        if(arr.length < 2)
        return arr;

        for (int i = 1; i < arr.length; i++) {
           int tmp = arr[i];

           int j = i;
           while (j > 0 && tmp < arr[j - 1]) {
            arr[j] = arr[j - 1];
            j--;
           }

           if (j != i)
            arr[j] = tmp;
        }

        return arr;
    }
}
