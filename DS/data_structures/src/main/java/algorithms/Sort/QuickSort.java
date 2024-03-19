package algorithms.Sort;

public class QuickSort {
    // private static int[] help;
    private static int[] arr;

    public static int[] sort(int[] nums) {
        arr = nums;
        // help = new int[nums.length];
        int l = 0;
        int r = nums.length - 1;
       return quicksort(l, r);
    }

    public static int[] quicksort(int l, int r) {
        if (l >= r) {
            return arr;
        }
        int left = l;
        int right = r;
        int pivot = arr[l];

        while (left < right) {
            while(left < right && arr[right] >= pivot) {
                right--;
            }
            if (left < right) {
                arr[left] = arr[right];
            }

            while(left < right && arr[left] <= pivot) {
                left++;
            }
            if(left < right) {
                arr[right] = arr[left];
            }

            if (left >= right) {
                arr[left] = pivot;
            }
        }
        quicksort(l, right - 1);
        quicksort(right + 1, r);
        return arr;
    }
}
