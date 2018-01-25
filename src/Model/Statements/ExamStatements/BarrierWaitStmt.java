package Model.Statements.ExamStatements;

import Exceptions.*;
import Model.Expressions.Exp;
import Model.PrgState;
import Model.Statements.BaeStatements.IStmt;
import Utils.Interfaces.MyIList;
import Utils.Interfaces.MyIStack;
import Utils.PrimitiveADT.MyList;
import javafx.util.Pair;

public class BarrierWaitStmt implements IStmt{
	private String var;
	
	public BarrierWaitStmt(String var) {
		this.var = var;
	}
	
	@Override
	public PrgState execute(PrgState state) throws ExpressionException, FileException, HeapException, StatementException, BarrierException {
		if (!state.getSymTable().containsKey(var))
			throw new StatementException("Variable not in symbol table");
		Integer foudIndex = state.getSymTable().get(var);
		
		if (!state.getBarrierTable().containsKey(foudIndex))
			throw new BarrierException("index not found in barrier table");
		Pair<Integer, MyList<Integer>> pair = state.getBarrierTable().get(foudIndex);
		
		int key = pair.getKey();
		MyIList<Integer> list = pair.getValue();
		int length = pair.getValue().size();
		
		MyIStack<IStmt> stack = state.getStack();
		
		if (key > length)
		{
			if (list.contains(Integer.valueOf(state.getId())))
				stack.push(this);
			else
			{
				list.add(state.getId());
				stack.push(this);
			}
		}
		return null;
	}
	
	public String toString()
	{
		return "await(" + var + ")";
	}
}
