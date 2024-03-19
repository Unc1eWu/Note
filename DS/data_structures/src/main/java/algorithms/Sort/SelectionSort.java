package algorithms.Sort;

import java.util.Arrays;

public class SelectionSort {    
    public static int[] sort(int nums[]) {
        int[] arr = Arrays.copyOf(nums, nums.length);
        
        for (int i = 0; i < arr.length - 1; i++){
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }

            if (i != min) {
                int tmp = arr[i];
                arr[i] = arr[min];
                arr[min] = tmp;
            }
        }
        return arr;
    }

}
