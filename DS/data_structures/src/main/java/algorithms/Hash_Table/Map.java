package algorithms.Hash_Table;

public interface Map<K, V> {
    void put(K key, V value);

    V get(K key);
}