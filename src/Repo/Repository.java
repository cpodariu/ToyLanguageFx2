package Repo;

import Model.Statements.BaeStatements.IStmt;
import Model.PrgState;
import Utils.Interfaces.MyIList;
import Utils.PrimitiveADT.MyDictionary;
import Utils.PrimitiveADT.MyList;
import Utils.PrimitiveADT.MyStack;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Repository implements RepositoryInterface {
//	PrgState state;
	List<PrgState> states = new ArrayList<PrgState>();
	String logFilePath;
	PrintWriter logFileWriter;
	
	public Repository(PrgState st) {
		this(st, "/home/cpodariu/Desktop/Fictioal-Interpreter/LogFiles/test.txt");
		
	}
	
	public Repository() {
		this(new PrgState(), "/home/cpodariu/Desktop/Fictioal-Interpreter/LogFiles/test.txt");
	}

	public Repository(PrgState state, String logFilePath)
	{
		this.states.add(state);
		this.logFilePath = logFilePath;
		try{
			this.logFileWriter = new PrintWriter(new BufferedWriter(new FileWriter(this.logFilePath, true)));
		}
		catch(IOException e)
		{
			System.out.print(e.getMessage());
		}
	}

	@Override
	public List<PrgState> getPrgList() {
		return this.states;
	}

	@Override
	public void setPrgList(List<PrgState> list) {
		this.states = list;
	}
	
	

	@Override
	public void logPrgStateExec(PrgState state) {
		logFileWriter.write(state.toString());
		logFileWriter.flush();
	}
}