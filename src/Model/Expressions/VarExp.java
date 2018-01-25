package Model.Expressions;

import Exceptions.ExpressionException;
import Utils.Interfaces.MyIDictionary;
import Utils.Interfaces.MyIHeap;

public class VarExp extends Exp{

    String label;

    public VarExp(String label)
    {
        this.label = label;
    }

    public String toString()
    {
        return label;
    }

    public int eval(MyIDictionary<String, Integer> symTable, MyIHeap heap) throws  ExpressionException
    {
        if (!symTable.containsKey(label))
            throw new ExpressionException("Label " + label + " not in symbol table");
        return symTable.get(label);
    }
}