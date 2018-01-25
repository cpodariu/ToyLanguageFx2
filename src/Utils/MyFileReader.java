package Utils;

import java.io.BufferedReader;
import java.io.IOException;

public class MyFileReader {
	private String fileName;
	private BufferedReader buffReader;
	public static int currentId;
	
	public String getFileName() {
		return this.fileName;
	}
	
	public MyFileReader(String file, BufferedReader reader)
	{
		fileName = file;
		buffReader = reader;
	}
	
	public String toString()
	{
		return fileName;
	}
	
	public void close()
	{
		try{
			buffReader.close();
		}
		catch(IOException e)
		{
			System.out.print(e.getMessage());
		}
	}
	
	public Integer readNextInt()
	{
		try
		{
			String s;
			s = buffReader.readLine();
			return Integer.parseInt(s);
		}
		catch(IOException e)
		{
			System.out.print(e.getMessage());
		}
		return null;
	}
}
