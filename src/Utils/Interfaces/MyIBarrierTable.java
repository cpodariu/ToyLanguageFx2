package Utils.Interfaces;

import Utils.PrimitiveADT.MyList;
import javafx.util.Pair;

public interface MyIBarrierTable {
	int addBarrier(Integer number);
	boolean containsKey(Object key);
	Pair<Integer, MyList<Integer>> get(Object key);
}
