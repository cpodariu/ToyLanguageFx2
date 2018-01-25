package Model.Statements.HeapStatements;

import Exceptions.ExpressionException;
import Exceptions.FileException;
import Exceptions.HeapException;
import Model.Expressions.Exp;
import Model.PrgState;
import Model.Statements.BaeStatements.IStmt;
import Utils.Interfaces.MyIDictionary;
import Utils.Interfaces.MyIHeap;

public class HeapWriteStmt implements IStmt {

    String varName;
    Exp expression;

    public HeapWriteStmt(String varName, Exp expression) {
        this.varName = varName;
        this.expression = expression;
    }

    @Override
    public PrgState execute(PrgState state) throws ExpressionException, FileException, HeapException {
        MyIDictionary<String, Integer> symTable = state.getSymTable();
        MyIHeap heap = state.getHeap();

        if (!symTable.containsKey(varName))
            throw new HeapException("Variable " + varName + " not in symbol table");

        Integer address = symTable.get(varName);

        if (!heap.containsKey(address))
            throw new HeapException("The given address has no corresponding allocated memory");

        heap.setValue(address, expression.eval(symTable, heap));
        return null;
    }

    @Override
    public String toString()
    {
        return "HeapWrite(" + varName + ", " + expression.toString() + ")";
    }
}
