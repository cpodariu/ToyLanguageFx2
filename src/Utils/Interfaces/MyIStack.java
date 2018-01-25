package Utils.Interfaces;

import Model.Statements.BaeStatements.IStmt;

import java.util.List;

public interface MyIStack<T> {
    public T push(T elem);
    public T pop();
    public String toString();
    public boolean isEmpty();
    public List<String> getAllStatements();
}