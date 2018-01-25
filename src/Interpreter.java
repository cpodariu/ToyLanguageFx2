import Controller.Controller;
import Model.*;
import Model.Expressions.ArithExp;
import Model.Expressions.ConstExp;
import Model.Expressions.HeapReadExp;
import Model.Expressions.VarExp;
import Model.Statements.BaeStatements.*;
import Model.Statements.ExamStatements.BarrierWaitStmt;
import Model.Statements.ExamStatements.NewBarrierStmt;
import Model.Statements.ExamStatements.SwitchStmt;
import Model.Statements.FileStatements.CloseRFileStmt;
import Model.Statements.FileStatements.OpenRFileStmt;
import Model.Statements.FileStatements.ReadFileStmt;
import Model.Statements.HeapStatements.HeapAllocStmt;
import Model.Statements.HeapStatements.HeapWriteStmt;
import Model.Statements.ThreadStatements.ForkStmt;
import Repo.Repository;
import Utils.Interfaces.MyIDictionary;
import Utils.PrimitiveADT.MyDictionary;
import Utils.MyFileReader;
import Utils.PrimitiveADT.MyList;
import Utils.PrimitiveADT.MyStack;
import View.Command;
import View.ExitCommand;
import View.RunExampleCommand;
import View.TextMenu;
import javafx.application.Application;
import javafx.stage.Stage;

import java.lang.invoke.ConstantCallSite;

public class Interpreter{
	
	public static MyDictionary<String, Command> getTestPrograms()
	{
		MyFileReader.currentId  = 0;
		String log = "log.txt";
//		System.out.print("Log file name: \n");
//		Scanner s = new Scanner(System.in);
//		log = s.nextLine();
		
		
		PrgState st1 = new PrgState();
		Repository r1 = new Repository(st1, log);
		Controller c1 = new Controller(r1);
		IStmt ex1 = new CompStmt(new AssignStmt("v",new ConstExp(2)), new PrintStmt(new
				VarExp("v")));
		st1.getStack().push(ex1);
		
		PrgState st2 = new PrgState();
		Repository r2 = new Repository(st2, log);
		Controller c2 = new Controller(r2);
		IStmt ex2 = new CompStmt(new AssignStmt("a", new ArithExp('+',new ConstExp(2),new
				ArithExp('*',new ConstExp(3), new ConstExp(5)))),
				new CompStmt(new AssignStmt("b",new ArithExp('+',new VarExp("a"), new
						ConstExp(1))), new PrintStmt(new VarExp("b"))));
		st2.getStack().push(ex2);
		
		PrgState st3 = new PrgState();
		Repository r3 = new Repository(st3, log);
		Controller c3 = new Controller(r3);
		IStmt ex3 = new CompStmt(new AssignStmt("a", new ArithExp('-',new ConstExp(2), new
				ConstExp(2))),
				new CompStmt(new IfStmt(new VarExp("a"),new AssignStmt("v",new ConstExp(2)), new
						AssignStmt("v", new ConstExp(3))), new PrintStmt(new VarExp("v"))));
		st3.getStack().push(ex3);
		
		
		PrgState st4 = new PrgState();
		Repository r4 = new Repository(st4, log);
		Controller c4 = new Controller(r4);
		st4.getStack().push(new CloseRFileStmt(new VarExp("var_f")));
		st4.getStack().push(new IfStmt(new VarExp("var_c"),
				new CompStmt(new ReadFileStmt(new VarExp("var_f"), "var_c"), new PrintStmt(new VarExp("var_c"))),
				new PrintStmt(new ConstExp(0))));
		st4.getStack().push(new CompStmt(new ReadFileStmt(new VarExp("var_f"), "var_c"), new PrintStmt(new VarExp("var_c"))));
		st4.getStack().push(new OpenRFileStmt("var_f", "in.txt"));

//		v=10;new(v,20);new(a,22);print(v)
		PrgState st5 = new PrgState();
		Repository r5 = new Repository(st5, log);
		Controller c5 = new Controller(r5);
		st5.getStack().push(new PrintStmt(new VarExp("v")));
		st5.getStack().push(new HeapAllocStmt("a", new ConstExp(22)));
		st5.getStack().push(new HeapAllocStmt("v", new ConstExp(20)));
		st5.getStack().push(new AssignStmt("v", new ConstExp(20)));

//		 v=10;new(v,20);new(a,22);print(100+rH(v));print(100+rH(a))
		
		PrgState st6 = new PrgState();
		Repository r6 = new Repository(st6, log);
		Controller c6 = new Controller(r6);
		st6.getStack().push(new PrintStmt(new ArithExp('+', new ConstExp(100), new HeapReadExp("a"))));
		st6.getStack().push(new PrintStmt(new ArithExp('+', new ConstExp(100), new HeapReadExp("v"))));
		st6.getStack().push(new HeapAllocStmt("a", new ConstExp(22)));
		st6.getStack().push(new HeapAllocStmt("v", new ConstExp(20)));
		st6.getStack().push(new AssignStmt("v", new ConstExp(10)));

//		v=10;new(v,20);new(a,22);wH(a,30);print(a);print(rH(a))
		
		PrgState st7 = new PrgState();
		Repository r7 = new Repository(st7, log);
		Controller c7 = new Controller(r7);
		st7.getStack().push(new PrintStmt(new HeapReadExp("a")));
		st7.getStack().push(new PrintStmt(new VarExp("a")));
		st7.getStack().push(new HeapWriteStmt("a", new ConstExp(20)));
		st7.getStack().push(new HeapAllocStmt("a", new ConstExp(22)));
		st7.getStack().push(new HeapAllocStmt("v", new ConstExp(20)));
		st7.getStack().push(new AssignStmt("v", new ConstExp(10)));

//      v=10;new(v,20);new(a,22);wH(a,30);print(a);print(rH(a));a=0
		
		
		PrgState st8 = new PrgState();
		Repository r8 = new Repository(st8, log);
		Controller c8 = new Controller(r8);
		st8.getStack().push(new AssignStmt("a", new ConstExp(1)));
		st8.getStack().push(new PrintStmt(new HeapReadExp("a")));
		st8.getStack().push(new PrintStmt(new VarExp("a")));
		st8.getStack().push(new HeapWriteStmt("a", new ConstExp(20)));
		st8.getStack().push(new HeapAllocStmt("a", new ConstExp(22)));
		st8.getStack().push(new HeapAllocStmt("v", new ConstExp(20)));
		st8.getStack().push(new AssignStmt("v", new ConstExp(10)));
		
		
		PrgState st9 = new PrgState();
		Repository r9 = new Repository(st9, log);
		Controller c9 = new Controller(r9);
		
		st9.getStack().push(new PrintStmt(new VarExp("a")));
		st9.getStack().push(new WhileStmt(new ArithExp(new VarExp("a"), new ConstExp(4), '-'),
				new CompStmt( new AssignStmt("a", new ArithExp('-', new VarExp("a"), new ConstExp(1))),
						new PrintStmt(new VarExp("a")))
		));
		st9.getStack().push(new AssignStmt("a", new ConstExp(6)));



//		v=10;new(a,22);
// fork(wH(a,30);v=32;print(v);print(rH(a)));
// print(v);print(rH(a))
		
		PrgState st10 = new PrgState();
		Repository r10 = new Repository(st10, log);
		Controller c10 = new Controller(r10);
		st10.getStack().push(new PrintStmt(new HeapReadExp("a")));
		st10.getStack().push(new PrintStmt(new VarExp("v")));
		st10.getStack().push(new ForkStmt(  new CompStmt(new HeapWriteStmt("a", new ConstExp(30)),
				new CompStmt(new AssignStmt("v", new ConstExp(32)),
						new CompStmt(new PrintStmt(new VarExp("v")),
								new PrintStmt(new HeapReadExp("a")))))));
		st10.getStack().push(new HeapAllocStmt("a", new ConstExp(22)));
		st10.getStack().push(new AssignStmt("v", new ConstExp(10)));
		
		PrgState st11 = new PrgState();
		Repository r11 = new Repository(st11, log);
		Controller c11 = new Controller(r11);
		st11.getStack().push(new PrintStmt(new ConstExp(300)));
		st11.getStack().push(new SwitchStmt(new ArithExp(new VarExp("a"), new ConstExp(10), '*'),
											new ArithExp(new VarExp("b"), new VarExp("c"), '*'),
											new CompStmt(new PrintStmt(new VarExp("a")), new PrintStmt(new VarExp("b"))),
											new ConstExp(10),
											new CompStmt(new PrintStmt(new ConstExp(100)), new PrintStmt(new ConstExp(200))),
											new PrintStmt(new ConstExp(300))
											));
		st11.getStack().push(new AssignStmt("c", new ConstExp(5)));
		st11.getStack().push(new AssignStmt("b", new ConstExp(2)));
		st11.getStack().push(new AssignStmt("a", new ConstExp(1)));
		
		PrgState st12 = new PrgState();
		Repository r12 = new Repository(st12, log);
		Controller c12 = new Controller(r12);
		st12.getStack().push(new PrintStmt(new HeapReadExp("v3")));
		st12.getStack().push(new BarrierWaitStmt("cnt"));
		st12.getStack().push(new ForkStmt(  new CompStmt(   new BarrierWaitStmt("cnt"),
											new CompStmt(new HeapWriteStmt("v2", new ArithExp('*', new HeapReadExp("v2"), new ConstExp(10))),
											new CompStmt(new HeapWriteStmt("v2", new ArithExp('*', new HeapReadExp("v2"), new ConstExp(10))),
													new PrintStmt(new HeapReadExp("v2")))))));
		st12.getStack().push(new ForkStmt(  new CompStmt(   new BarrierWaitStmt("cnt"),
															new CompStmt(new HeapWriteStmt("v1", new ArithExp('*', new HeapReadExp("v1"), new ConstExp(10))),
																	new PrintStmt(new HeapReadExp("v1"))))));
		st12.getStack().push(new NewBarrierStmt("cnt", new HeapReadExp("v2")));
		st12.getStack().push(new HeapAllocStmt("v3", new ConstExp(4)));
		st12.getStack().push(new HeapAllocStmt("v2", new ConstExp(3)));
		st12.getStack().push(new HeapAllocStmt("v1", new ConstExp(2)));
		
		MyDictionary<String, Command> commands = new MyDictionary<String, Command>();
		commands.put("1", new RunExampleCommand("1", "Example 1", c1));
		commands.put("0", new ExitCommand("0", "exit"));
		commands.put("2", new RunExampleCommand("2", "Example 2", c2));
		commands.put("3", new RunExampleCommand("3", "Example 3", c3));
		commands.put("4", new RunExampleCommand("4", "Example 4", c4));
		commands.put("5", new RunExampleCommand("5", "Example 5", c5));
		commands.put("6", new RunExampleCommand("6", "Example 6", c6));
		commands.put("7", new RunExampleCommand("7", "Example 7", c7));
		commands.put("8", new RunExampleCommand("8", "Example 8", c8));
		commands.put("9", new RunExampleCommand("9", "Example 9", c9));
		commands.put("10", new RunExampleCommand("10", "Example 10", c10));
		commands.put("11", new RunExampleCommand("11", "Example 11", c11));
		commands.put("12", new RunExampleCommand("12", "Example 12", c12));
		return commands;
	}
	
	public static void main(String[] args) {
		TextMenu t = new TextMenu(getTestPrograms());
		t.show();
	}

}
