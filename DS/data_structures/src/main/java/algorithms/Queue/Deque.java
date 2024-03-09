package algorithms.Queue;

/* 
 * 双端队列接口
 */
public interface Deque<E> extends Queue<E>{
    void addFirst(E e);
    
    void addLast(E e);
}
