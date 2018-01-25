package Model.Statements.BaeStatements;

import Exceptions.ExpressionException;
import Exceptions.FileException;
import Exceptions.HeapException;
import Model.Expressions.Exp;
import Model.PrgState;
import Utils.Interfaces.MyIStack;

public class WhileStmt implements IStmt {

	private Exp expression;
	private IStmt statement;

	public WhileStmt(Exp expression, IStmt statement) {
		this.expression = expression;
		this.statement = statement;
	}

	@Override
	public PrgState execute(PrgState state) throws ExpressionException, FileException, HeapException {
		Integer result = expression.eval(state.getSymTable(), state.getHeap());
		MyIStack<IStmt> stack = state.getStack();
		if (result != 0)
		{
			stack.push(this);
			stack.push(statement);
		}
		return null;
	}

	@Override
	public String toString() {
		return "WhileStmt(" + expression.toString() + ", "+ statement.toString() + ")";
	}
}
