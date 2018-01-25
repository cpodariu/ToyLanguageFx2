package Model.Statements.BaeStatements;

import Exceptions.ExpressionException;
import Model.Expressions.Exp;
import Model.PrgState;
import Utils.Interfaces.MyIList;

public class PrintStmt implements IStmt {
    
    Exp exp;
    
    public PrintStmt(Exp exp)
    {
        this.exp = exp;
    }

    public PrgState execute(PrgState state) throws ExpressionException
    {
        MyIList<String> out = state.getOut();

        out.add(Integer.toString(exp.eval(state.getSymTable(), state.getHeap())));
        return null;
    }

    public String toString()
    {
        return "Print(" + exp.toString() + ")";
    }
}