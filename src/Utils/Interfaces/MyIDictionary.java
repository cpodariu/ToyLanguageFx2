package Utils.Interfaces;

import java.util.Collection;
import java.util.Set;

public interface MyIDictionary <K,V>{
    V put(K key, V value);
    V get(Object key);
    String toString();
    boolean containsKey(Object key);
    public V remove(Object key);
    boolean containsValue(Object value);
    Set<K> keySet();
    Collection<V> values();
    Object clone();
}