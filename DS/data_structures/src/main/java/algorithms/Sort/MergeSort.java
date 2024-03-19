package algorithms.Sort;


public class MergeSort {
    private static int[] nums;
    private static int[] help;

    public static int[] mergeSort(int[] arr) {
        // int[] left = Arrays.copyOfRange(nums, 0, nums.length >> 1);
        // int[] right  = Arrays.copyOfRange(nums, nums.length >> 1 + 1, nums.length - 1);
        nums = arr;
        help = new int[nums.length];
        if (arr.length > 1) {
            // sort(0, arr.length - 1);
            sort2();
        }
       return nums;
    }


    // 递归版本
    public static void sort(int l, int r) {
        if (l == r) {
            return;
        }

        int m = (l + r) >> 1;
        sort(l, m);
        sort(m + 1, r);
        merge(l, m, r);


    }

    // 非递归版本
    public static void sort2() {
        for (int l, m, r, step = 1; step < nums.length; step <<= 1) {
            l = 0;
            while(l < nums.length) {
                m = l + step - 1;
                if (m + 1 >= nums.length) {
                    break;
                }
                r = Math.min(nums.length - 1, l + (step << 1) - 1);
                merge(l, m, r);
                l = r + 1;
            }
        }
    }

    public static void merge(int l, int m, int r) {
        int i = l;
        int a = l;
        int b = m + 1;
        while (a <= m && b <= r) {
            help[i++] = (nums[a] <= nums[b]) ? nums[a++] : nums[b++];
        }

        while (a <= m){
            help[i++] = nums[a++];
        } 

        while(b <= r) {
            help[i++] = nums[b++];
        }

        for(i = l; i <= r; i++) {
            nums[i] = help[i];
        }
    }

    // public static int[] merge(int[] left, int[] right) {
    //     int res[] = new int[left.length + right.length];
    //     int j = 0, i = 0, k = 0;
    //     while (j < left.length && i < right.length) {
    //         if (left[j] < right[i]) {
    //             res[k] = left[j];
    //             k++;
    //             j++;
    //         } else {
    //             res[k] = right[i];
    //             k++;
    //             i++;
    //         }
    //     }
    //     if (j == left.length) {
    //         while (i < right.length) {
    //             res[k++] = right[i++];
    //         }
    //     } else {
    //         while (j < left.length) {
    //             res[k++] = left[j++];
    //         }
    //     }

    //     return res;
    // }
}
