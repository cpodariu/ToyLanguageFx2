package Utils;

import Utils.Interfaces.MyIBarrierTable;
import Utils.Interfaces.MyIDictionary;
import Utils.PrimitiveADT.MyDictionary;
import Utils.PrimitiveADT.MyList;
import javafx.util.Pair;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyBarrierTable extends MyDictionary<Integer, Pair<Integer, MyList<Integer>>> implements MyIBarrierTable {
	Lock lock = new ReentrantLock();
	
	@Override
	public int addBarrier(Integer number) {
		
		Integer i = 1;
		lock.lock();
		while (this.containsKey(i))
			i++;
		this.put(i, new Pair<Integer, MyList<Integer>>(number, new MyList<Integer>()));
		lock.unlock();
		return i;
	}
	
	@Override
	public boolean containsKey(Object key)
	{
		lock.lock();
		boolean ret = super.containsKey(key);
		lock.unlock();
		return ret;
	}
	
	@Override
	public Pair<Integer, MyList<Integer>> get(Object key)
	{
		lock.lock();
		Pair<Integer, MyList<Integer>> ret = super.get(key);
		lock.unlock();
		return ret;
	}
}
