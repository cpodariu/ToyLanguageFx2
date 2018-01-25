package View;

import Controller.Controller;
import Utils.PrimitiveADT.MyDictionary;

public class RunExampleCommand extends Command{
	Controller ctrl;
	
	public RunExampleCommand(String key, String desc, Controller ctrl)
	{
		super(key, desc);
		this.ctrl = ctrl;
		description = this.ctrl.getRepository().getPrgList().get(0).getStack().toString();
	}
	
	public void execute()
	{
		try{
			ctrl.allSteps(true);
		}
		catch(Exception e)
		{
			System.out.print(e.getMessage());
		}
	}
	
	public Controller getCtrl() {
		return ctrl;
	}
	
	public String toString()
	{
		return this.ctrl.getRepository().getPrgList().get(0).getStack().toString();
	}
}