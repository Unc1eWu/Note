package algorithms;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
import algorithms.Sort.*;;

public class SortTest {
    private int[] nums = {1, 4, 3, 6, 2, 5};
    private int[] sortedNums = {1, 2, 3, 4, 5, 6};
    private final Logger logger = LoggerFactory.getLogger(SortTest.class);
   
    @Test
    public void testBubble() {
        int[] res = BubbleSort.sort(nums);
        logger.info("排序后数组:{}", Arrays.toString(res));
        Assert.assertArrayEquals(sortedNums,  res);
    }

    @Test
    public void testSelection() {
        int[] res = SelectionSort.sort(nums);
        logger.info("排序后数组:{}", Arrays.toString(res));
        Assert.assertArrayEquals(sortedNums,  res);
    }

    @Test
    public void testInsertion() {
        int[] res = InsertionSort.sort(nums);
        logger.info("排序后数组:{}", Arrays.toString(res));
        Assert.assertArrayEquals(sortedNums,  res);
    }

    @Test
    public void testMerge() {
        int[] res = MergeSort.mergeSort(nums);
        logger.info("排序后数组:{}", Arrays.toString(res));
        Assert.assertArrayEquals(sortedNums,  res);
    }

    @Test
    public void testQuick() {
        int[] res = QuickSort.sort(nums);
        logger.info("排序后数组:{}", Arrays.toString(res));
        Assert.assertArrayEquals(sortedNums,  res);
    }
}
