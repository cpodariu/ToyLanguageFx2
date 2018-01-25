package Model.Statements.FileStatements;

import Exceptions.ExpressionException;
import Model.Expressions.Exp;
import Model.PrgState;
import Model.Statements.BaeStatements.IStmt;
import Utils.MyFileReader;

public class ReadFileStmt implements IStmt {
	Exp var_id;
	String label;
	
	public ReadFileStmt(Exp id, String l)
	{
		label = l;
		var_id = id;
	}
	
	public PrgState execute(PrgState state) throws ExpressionException
	{
		int result = 0;
		
		result = var_id.eval(state.getSymTable(), state.getHeap());
		
		MyFileReader reader = state.getFileTable().get(result);
		
		Integer buff;
		
		buff = reader.readNextInt();
		
		state.getSymTable().put(label, buff);
		
		return null;
	}
	
	
	
	public String toString()
	{
		return "Read(" + var_id.toString() + ", " + label + ")";
	}
	
}
