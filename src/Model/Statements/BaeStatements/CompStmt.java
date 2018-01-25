package Model.Statements.BaeStatements;

import Model.PrgState;
import Utils.Interfaces.MyIStack;

public class CompStmt implements IStmt {
    IStmt first;
    IStmt second;

    public CompStmt(IStmt first, IStmt second)
    {
        this.first = first;
        this.second = second;
    }

    public String toString()
    {
        return "( " + first.toString() + "; " + second.toString() + " )";
    }

    public PrgState execute(PrgState state)
    {
        MyIStack<IStmt> s = state.getStack();

        s.push(second);
        s.push(first);

        return null;
    }


}