package algorithms.Queue;
/* 
 * 阻塞队列接口
 */
public interface BlockingQueue<E> extends Queue<E> {
    boolean add(E e);

    boolean offer(E e);
}
