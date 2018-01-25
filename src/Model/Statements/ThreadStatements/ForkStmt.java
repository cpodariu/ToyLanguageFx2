package Model.Statements.ThreadStatements;

import Exceptions.ExpressionException;
import Exceptions.FileException;
import Exceptions.HeapException;
import Model.PrgState;
import Model.Statements.BaeStatements.IStmt;
import Utils.Interfaces.*;
import Utils.MyFileReader;
import Utils.PrimitiveADT.MyDictionary;
import Utils.PrimitiveADT.MyStack;

public class ForkStmt implements IStmt{
	private IStmt statement;
	
	static int lastThreadId = 2;
	
	public ForkStmt(IStmt statement) {
		this.statement = statement;
	}
	
	@Override
	public PrgState execute(PrgState state) throws ExpressionException, FileException, HeapException {
		MyIStack<IStmt> newExeStack = new MyStack<>();
		newExeStack.push(statement);
		
		MyIDictionary<String, Integer> newSymTable = new MyDictionary<String, Integer>(state.getSymTable());
		
		MyIList<String> newOut = state.getOut();
		
		MyIDictionary<Integer, MyFileReader> newFileTable = state.getFileTable();
		
		MyIHeap newHeap = state.getHeap();
		
		int newId = lastThreadId;
		lastThreadId++;
		
		MyIBarrierTable barrierTable = state.getBarrierTable();
		
		return new PrgState(newExeStack, newSymTable, newOut, newFileTable, newHeap, newId, barrierTable);
	}
	
	@Override
	public String toString() {
		return "fork(" + this.statement + ")";
	}
}
