package Model.Statements.BaeStatements;

import Exceptions.ExpressionException;
import Exceptions.FileException;
import Exceptions.HeapException;
import Model.PrgState;

public interface IStmt {
    String toString();
    PrgState execute(PrgState state) throws ExpressionException, FileException, HeapException;
}
