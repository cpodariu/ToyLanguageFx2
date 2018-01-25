package Utils.PrimitiveADT;

import Utils.Interfaces.MyIDictionary;

import java.util.HashMap;

public class MyDictionary<K, V> extends HashMap<K,V> implements MyIDictionary<K,V> {
	
	public MyDictionary(MyIDictionary<K, V> original) {
		for (K i : original.keySet())
		{
			this.put(i, original.get(i));
		}
	}
	
	public MyDictionary() {
		super();
	}
}