package algorithms.Hash_Table;

import com.alibaba.fastjson.JSON;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class HashMap04ByCoalescedHashing<K, V> implements Map<K, V> {
    @SuppressWarnings("unused")
    private final Logger logger = LoggerFactory.getLogger(HashMap04ByCoalescedHashing.class);
    @SuppressWarnings("unchecked")
    private final Node<K, V>[] tab = new Node[8];

    @Override
    public void put(K key, V value) {
        int idx = key.hashCode() & (tab.length - 1);
        if (tab[idx] == null) {
            tab[idx] = new Node<K,V>(key, value);
            return;
        } 
        
        if (Objects.equals(tab[idx].key, key)) {
            tab[idx] = new Node<>(key, value);
            return;
        }
        
        int cursor = tab.length - 1;
        while(tab[cursor] != null && tab[cursor].key != key) {
            --cursor;
        }
        tab[cursor] = new Node<K, V>(key, value);
        
        // 将碰撞节点指向这个新节点
        while(tab[idx].idxOfNext != 0) {
            idx = tab[idx].idxOfNext;
        }
        
        tab[idx].idxOfNext = cursor;
    }

    @Override
    public V get(K key) {
        int idx = key.hashCode() & (tab.length - 1);
        while (tab[idx] != null && tab[idx].key != key) {
            idx = tab[idx].idxOfNext;
        }
        if (tab[idx] == null)
            return null;
        return tab[idx].value;
    }

    static class Node<K, V> {
        final K key;
        V value;
        int idxOfNext;

        public Node(K key, V value){
            this.key = key;
            this.value = value;
        }

        public int getIdxOfNext() {
            return idxOfNext;
        }

        public void setIdxOfNext(int idxOfNext) {
            this.idxOfNext = idxOfNext;
        }
    }

    @Override
    public String toString() {
        return "HashMap{" +
                "tab=" + JSON.toJSONString(tab) +
                '}';
    }
}
