package algorithms.Heap;

public class MinHeap extends Heap<Integer> {
    @Override
    public int compareTo(Integer firstElement, Integer secondElement) {
        return firstElement.compareTo(secondElement);
    }
}
