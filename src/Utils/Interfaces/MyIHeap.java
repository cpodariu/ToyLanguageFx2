package Utils.Interfaces;

import Exceptions.ExpressionException;

import java.util.Map;

public interface MyIHeap{
    Integer allocNew(Integer value);
    void freeMemory(Integer address);
    void setValue(Integer key, Integer value) throws ExpressionException;
    void collectGarbage(MyIDictionary<String, Integer> symTable);
    Integer dereference(Integer address);
    String toString();
    Map<Integer,Integer> getContent();
    void setContent(Map<Integer, Integer> content);
    boolean containsKey(Object key);
}
