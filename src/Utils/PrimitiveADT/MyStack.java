package Utils.PrimitiveADT;

import Utils.Interfaces.MyIList;
import Utils.Interfaces.MyIStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class MyStack<T> extends Stack<T> implements MyIStack<T> {

    public String toString()
    {
        List<T> l = new ArrayList<T>(this);
        Collections.reverse(l);
        return l.toString();
    }
    
    @Override
    public List<String> getAllStatements() {
        List<String> list = new MyList<String>();
        for (T e:this)
        {
            list.add(e.toString());
        }
        Collections.reverse(list);
        return list;
    }
}
