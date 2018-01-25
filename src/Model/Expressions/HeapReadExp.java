package Model.Expressions;

import Exceptions.ExpressionException;
import Utils.Interfaces.MyIDictionary;
import Utils.Interfaces.MyIHeap;

public class HeapReadExp  extends  Exp{
    String varName;

    public HeapReadExp(String varName) {
        this.varName = varName;
    }

    @Override
    public String toString() {
        return "read(" + varName + ")";
    }

    @Override
    public int eval(MyIDictionary<String, Integer> symTable, MyIHeap heap) throws ExpressionException {
        return heap.dereference(symTable.get(varName));
    }
}
