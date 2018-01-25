package Model.Statements.FileStatements;

import Exceptions.FileException;
import Model.PrgState;
import Model.Statements.BaeStatements.IStmt;
import Utils.MyFileReader;
import Utils.Interfaces.MyIDictionary;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class OpenRFileStmt implements IStmt {
	String variableId;
	String fileName;
	
	public OpenRFileStmt(String id, String f)
	{
		variableId = id;
		fileName = f;
	}
	
	boolean checkIfOpen(String fileName, MyIDictionary<Integer,MyFileReader> fileTable)
	{
		for(Integer i : fileTable.keySet())
		{
			if(fileName.equals(fileTable.get(i).toString()))
				return true;
		}
		return false;
	}
	
	public PrgState execute(PrgState prgState) throws FileException
	{
		MyIDictionary<Integer,MyFileReader> fileTable = prgState.getFileTable();
		MyIDictionary<String,Integer> symTable = prgState.getSymTable();
		
		if(checkIfOpen(fileName,fileTable))
		{
			throw new FileException(fileName);
		}
		
		int fileId = MyFileReader.currentId;
		MyFileReader.currentId++;
		MyFileReader reader;
		
		try
		{
			reader = new MyFileReader(fileName,new BufferedReader(new FileReader(fileName)));
			fileTable.put(fileId, reader);
		}
		catch(IOException e)
		{
			System.out.print(e.getMessage());
		}
		
		if(symTable.containsKey(variableId))
		{
			int id = symTable.get(variableId);
			MyFileReader r = fileTable.get(id);
			r.close();
			fileTable.remove(id);
		}
		symTable.put(variableId, fileId);
		
		return null;
	}
	
	public String toString()
	{
		return " Open(" + variableId + ",\"" + fileName + "\")";
	}
	
}