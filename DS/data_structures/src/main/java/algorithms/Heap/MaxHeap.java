package algorithms.Heap;

public class MaxHeap extends Heap<Integer> {
    @Override
    public int compareTo(Integer firstElement, Integer secondElement) {
        return secondElement.compareTo(firstElement);
    }
}
