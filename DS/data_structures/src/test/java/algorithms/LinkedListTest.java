package algorithms;

// import java.util.logging.Logger;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import algorithms.Linked_List.LinkedList;
import algorithms.Linked_List.List;

public class LinkedListTest {
    @SuppressWarnings("unused")
    private final Logger logger = LoggerFactory.getLogger(LinkedListTest.class);
    
    @Test
    public void test_linked_list() {
        List<String> list = new LinkedList<>();
        // 添加元素
        list.add("a");
        list.addFirst("b");
        list.addLast("c");
        // 打印列表
        list.printLinkList();
        // 头插元素
        list.addFirst("d");
        // 删除元素
        list.remove("b");
        // 打印列表
        list.printLinkList();
    }
}