package Utils.Interfaces;

public interface MyIList<T> {
    boolean add(T elem);
    T get(int pos);
    String toString();
    boolean contains(Object o);
}