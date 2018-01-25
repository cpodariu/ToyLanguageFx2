package Model.Statements.ExamStatements;

import Exceptions.ExpressionException;
import Exceptions.FileException;
import Exceptions.HeapException;
import Model.Expressions.Exp;
import Model.PrgState;
import Model.Statements.BaeStatements.IStmt;
import Utils.Interfaces.MyIBarrierTable;
import Utils.Interfaces.MyIDictionary;

public class NewBarrierStmt implements IStmt {
	private String variable;
	private Exp expression;
	
	public NewBarrierStmt(String variable, Exp expression) {
		this.variable = variable;
		this.expression = expression;
	}
	
	@Override
	public PrgState execute(PrgState state) throws ExpressionException, FileException, HeapException {
		int res = expression.eval(state.getSymTable(), state.getHeap());
		Integer position = state.getBarrierTable().addBarrier(res);
		MyIDictionary symTable = state.getSymTable();
		symTable.put(variable, position);
		return null;
	}
	
	public String toString()
	{
		return "newBarrier(" + variable + ", " + expression.toString() + ")";
	}
}
