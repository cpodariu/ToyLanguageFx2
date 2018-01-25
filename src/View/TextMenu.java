package View;

import Utils.PrimitiveADT.MyDictionary;

import java.util.Scanner;

public class TextMenu {
	
	MyDictionary<String, Command> commands;
	
	public TextMenu(MyDictionary<String, Command> coms)
	{
		this.commands = coms;
	}
	
	public TextMenu()
	{
		commands = new MyDictionary<String, Command>();
	}
	
	public void addCommand(Command command)
	{
		commands.put(command.key, command);
	}
	
	public void printMenu()
	{
		for(String i : commands.keySet())
		{
			System.out.print(i + ". " +commands.get(i).getDescription() + "\n");
		}
	}
	
	public void show()
	{
		Scanner scanner = new Scanner(System.in);
		System.out.print("\n");
		while(true)
		{
			printMenu();
			
			String key = scanner.nextLine();
			Command c = commands.get(key);
			
			if(c == null)
			{
				System.out.print("invalid command\n");
			} else
				c.execute();
		}
		
	}
	
	
}