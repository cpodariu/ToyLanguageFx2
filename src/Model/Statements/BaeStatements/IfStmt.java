package Model.Statements.BaeStatements;

import Exceptions.ExpressionException;
import Model.Expressions.Exp;
import Model.PrgState;

public class IfStmt implements IStmt {

    Exp condition;
    IStmt thenS;
    IStmt elseS;

    public IfStmt(Exp exp, IStmt iStmt, IStmt elseS)
    {
        this.condition = exp;
        this.thenS = iStmt;
        this.elseS = elseS;
    }

    public String toString()
    {
        return "(If " + condition.toString() + " then " + thenS.toString() + " else " + elseS.toString()+ ")";
    }

    public PrgState execute(PrgState state) throws ExpressionException
    {
        int result = condition.eval(state.getSymTable(), state.getHeap());

        if(result != 0)
            state.getStack().push(thenS);
        else
            state.getStack().push(elseS);
        return null;
    }

}

