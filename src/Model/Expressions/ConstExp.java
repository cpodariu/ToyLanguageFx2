package Model.Expressions;

import Utils.Interfaces.MyIDictionary;
import Utils.Interfaces.MyIHeap;

public class ConstExp extends Exp{
    int value;

    public String toString()
    {
        return Integer.toString(value);
    }

    public int eval(MyIDictionary<String, Integer> symTable, MyIHeap heap)
    {
        return value;
    }

    public ConstExp(int value)
    {
        this.value = value;
    }


}
