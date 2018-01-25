package Model.Expressions;

import Exceptions.ExpressionException;
import Utils.Interfaces.MyIDictionary;
import Utils.Interfaces.MyIHeap;

public abstract class Exp {
    public abstract String toString();
    public abstract int eval(MyIDictionary<String, Integer> symTable, MyIHeap heap) throws ExpressionException;
}
