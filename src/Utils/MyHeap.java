package Utils;


import Exceptions.ExpressionException;
import Utils.Interfaces.MyIDictionary;
import Utils.Interfaces.MyIHeap;
import Utils.PrimitiveADT.MyDictionary;

import java.util.Map;
import java.util.Set;

public class MyHeap extends MyDictionary<Integer, Integer> implements MyIHeap{

    private Integer nextKey = 1;

    @Override
    public Integer allocNew(Integer value) {
        this.put(nextKey, value);
        nextKey++;
        return (nextKey - 1);
    }

    @Override
    public void freeMemory(Integer address) {
        this.remove(address);
    }

    @Override
    public void setValue(Integer key, Integer value) throws ExpressionException {
        if(!containsKey(key))
            throw new ExpressionException("You are trying to access unallocated memory.");
        put(key, value);
    }

    @Override
    public void collectGarbage(MyIDictionary<String, Integer> symTable) {
        for (Integer key : symTable.values())
        {
            if (this.containsKey(key))
                freeMemory(key);
        }
    }

    @Override
    public Integer dereference(Integer address) {
        return this.get(address);
    }

    @Override
    public Map<Integer, Integer> getContent() {
        return this;
    }

    @Override
    public void setContent(Map<Integer, Integer> content) {
        this.clear();
        this.putAll(content);
    }

}