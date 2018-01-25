package Model.Statements.BaeStatements;

import Exceptions.ExpressionException;
import Model.Expressions.Exp;
import Model.PrgState;
import Utils.Interfaces.MyIDictionary;

public class AssignStmt implements IStmt {
    String label;
    Exp value;

    public String toString()
    {
        return label + " = " + value.toString();
    }

    public PrgState execute(PrgState state) throws ExpressionException
    {
        MyIDictionary<String, Integer> symTable = state.getSymTable();

        int res = value.eval(symTable, state.getHeap());
        symTable.put(label, res);

        return null;
    }

    public AssignStmt(String name, Exp exp)
    {
        label = name;
        value = exp;
    }

}