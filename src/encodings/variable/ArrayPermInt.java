package encodings.variable;

import jmetal.core.Variable;
import jmetal.util.JMException;

public class ArrayPermInt extends Variable {

	private static final long serialVersionUID = 813640980697191846L;

	PermInt[] var;
	
	
	public ArrayPermInt(int number) {
		var = new PermInt[number];
	}

	public ArrayPermInt(ArrayPermInt v) throws JMException {
		var = new PermInt[v.var.length];
		for (int i = 0; i < var.length; ++i) {
			var[i] = new PermInt(v.var[i]);
		}
	}
		

	public void group(boolean values) {
		sort(var, values);

	}

	public void sort(PermInt[] var, boolean orderValues) {
		// check for empty or null array
		if (var == null || var.length == 0) {
			return;
		}
		try {
			quicksort(0, var.length - 1, var, orderValues);
		} catch (JMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void quicksort(int low, int high, PermInt var[], boolean orderValues)
			throws JMException {
		int i = low, j = high;
		// Get the pivot element from the middle of the list

		double pivot;
		if (orderValues)
			pivot = var[low + (high - low) / 2].getValue();
		else
			pivot = var[low + (high - low) / 2].getIndex();

		// Divide into two lists
		while (i <= j) {
			while (checkTerm(i, var, orderValues) < pivot) {
				i++;
			}

			while (checkTerm(j, var, orderValues) > pivot) {
				j--;
			}

			if (i <= j) {
				exchange(i, j, var);
				i++;
				j--;

			}
		}
		// Recursion
		if (low < j)
			quicksort(low, j, var, orderValues);
		if (i < high)
			quicksort(i, high, var, orderValues);
	}

	private double checkTerm(int index, PermInt[] var, boolean orderValues) {
		if (orderValues)
			return var[index].getValue();
		else
			return var[index].getIndex();

	}

	private void exchange(int i, int j, Variable var[]) throws JMException {
		PermInt variables[] = (PermInt[]) var;
		double temp = variables[i].getValue();
		variables[i].setValue(variables[j].getValue());
		variables[j].setValue(temp);
		int swap = variables[i].getIndex();
		variables[i].setIndex(variables[j].getIndex());
		variables[j].setIndex(swap);
	}
	
	public Double logSearch(int index){
		int i=0;
		int j=var.length-1;
		if (var[i].getIndex()==index)return var[i].getValue();
		if (var[j].getIndex()==index)return var[j].getValue();
		while(j-i>0){
			int pivot = (i+j)/2;
			
			int key=var[pivot].getIndex();
			if(key==index)return var[pivot].getValue();
			else if(key>index) j=pivot;
			else i=pivot;
		}
		return null;
	}

	public Variable[] getDecisionVariables(){
		return var;
	}
	
	public Variable getVariable(int index){
		return var[index];	
	}
	
	public void setVariable(PermInt p, int index){
		var[index]=p;
	}

	@Override
	public Variable deepCopy() {

		Variable var = null;
		try {
			var = new ArrayPermInt(this);
		} catch (JMException e) {
			e.printStackTrace();
		}
		return var;

	}
}
