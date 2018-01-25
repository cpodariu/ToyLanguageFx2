package Model.Statements.BaeStatements;

import Exceptions.*;
import Model.PrgState;

public interface IStmt {
    String toString();
    PrgState execute(PrgState state) throws ExpressionException, FileException, HeapException, StatementException, BarrierException;
}
