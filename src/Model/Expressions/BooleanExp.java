package Model.Expressions;

import Exceptions.ExpressionException;
import Utils.Interfaces.MyIDictionary;
import Utils.Interfaces.MyIHeap;

public class BooleanExp extends Exp {
	Exp right, left;
	String sign;

	public BooleanExp(Exp left, Exp right, String sign) {
		this.left = left;
		this.right = right;
		this.sign = sign;
	}

	public BooleanExp(String sign, Exp left, Exp right) {
		this.left = left;
		this.right = right;
		this.sign = sign;
	}


	public BooleanExp(Exp left, String sign, Exp right) {
		this.left = left;
		this.right = right;
		this.sign = sign;
	}

	public String toString() {
		return left.toString() + " " + sign + " " + right.toString();
	}

	@Override
	public int eval(MyIDictionary<String, Integer> symTable, MyIHeap heap) throws ExpressionException {
		int left;
		int right;
		left = this.left.eval(symTable, heap);
		right = this.right.eval(symTable, heap);

		int result = 0;

		switch (sign) {
			case "==":
				result = left - right;
				break;
			case ">":
				if (left > right)
					result = 1;
			case "<":
				if (left < right)
					result = 1;
			case ">=":
				if (left >= right)
					result = 1;
			case "<=":
				if (left <= right)
					result = 1;
			case "!=":
				if (left != right)
					result = 1;
		}
		return result;
	}


}
