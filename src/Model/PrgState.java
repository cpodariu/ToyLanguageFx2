package Model;

import Exceptions.ExpressionException;
import Exceptions.FileException;
import Exceptions.HeapException;
import Model.Statements.BaeStatements.IStmt;
import Utils.Interfaces.MyIHeap;
import Utils.MyFileReader;
import Utils.Interfaces.MyIDictionary;
import Utils.Interfaces.MyIList;
import Utils.Interfaces.MyIStack;
import Utils.MyHeap;
import Utils.PrimitiveADT.MyDictionary;
import Utils.PrimitiveADT.MyList;
import Utils.PrimitiveADT.MyStack;

public class PrgState {
    private MyIStack<IStmt> exeStack;
    private MyIDictionary<String,Integer> symTable;
    private MyIList<String> out;
    private MyIDictionary<Integer, MyFileReader> fileTable;
    private MyIHeap heap;
	private int id = 1;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

    public PrgState()
    {
        exeStack = new MyStack<IStmt>();
        symTable = new MyDictionary<String, Integer>();
        out = new MyList<String>();
        fileTable = new MyDictionary<Integer, MyFileReader>();
        heap = new MyHeap();
        id = 1;
    }

    public PrgState(MyIStack<IStmt> exeStack,MyIDictionary<String,Integer> symTable, MyIList<String> out )
    {
        this.exeStack = exeStack;
        this.symTable = symTable;
        this.out = out;
    }
    
    public PrgState(MyIStack<IStmt> exeStack,MyIDictionary<String,Integer> symTable, MyIList<String> out, MyIDictionary<Integer, MyFileReader>fileTable)
    {
        this.exeStack = exeStack;
        this.symTable = symTable;
        this.out = out;
        this.fileTable = fileTable;
    }


	public PrgState(MyIStack<IStmt> exeStack,MyIDictionary<String,Integer> symTable, MyIList<String> out, MyIDictionary<Integer, MyFileReader>fileTable, MyIHeap heap)
	{
        this.exeStack = exeStack;
        this.symTable = symTable;
	    this.out = out;
	    this.fileTable = fileTable;
	    this.heap = heap;
    }
	
	public PrgState(MyIStack<IStmt> exeStack, MyIDictionary<String,Integer> symTable, MyIList<String> out, MyIDictionary<Integer, MyFileReader>fileTable, MyIHeap heap, int id)
	{
		this.exeStack = exeStack;
		this.symTable = symTable;
		this.out = out;
		this.fileTable = fileTable;
		this.heap = heap;
		this.id = id;
	}

    public MyIStack<IStmt> getStack()
    {
        return exeStack;
    }

    public MyIDictionary<String, Integer> getSymTable()
    {
        return symTable;
    }

    public MyIList<String> getOut()
    {
        return out;
    }

    public String toString()
    {
        String res = "===Thread nr " + this.id + " ====\n" +
		        "\nExe Stack:\n" + exeStack.toString() +
                "\nSym Table:\n" + symTable.toString() +
                "\nOut:\n" + out.toString() +
                "\nFiles:\n" + fileTable.toString() +
                "\nHeap:\n" + heap.toString() + "\n";
        return res;
    }
	
	public MyIDictionary<Integer, MyFileReader> getFileTable() {
		return fileTable;
	}

    public MyIHeap getHeap() {
        return heap;
    }

    public void setHeap(MyIHeap heap) {
        this.heap = heap;
    }

    public boolean isNotCompleted(){
    	return !this.exeStack.isEmpty();
    }


	public PrgState oneStep(PrgState state) throws HeapException, FileException, ExpressionException, Exception {
		MyIStack<IStmt> stk = state.getStack();
		if(stk.isEmpty()) throw new Exception();
		IStmt crtStmt = stk.pop();
		return crtStmt.execute(state);
	}
}
