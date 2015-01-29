package encodings.variable;

import jmetal.core.Problem;
import jmetal.core.Variable;
import jmetal.util.JMException;
import jmetal.util.PseudoRandom;

public class ArrayPermInt extends Variable {

	private static final long serialVersionUID = 813640980697191846L;

	PermInt[] var;
	private boolean sortedByKeys;
	private boolean sortedByValues;
	private int firstMinMakespan;
	private int lastMinMakespan;
	private int firstMaxMakespan;
	private int lastMaxMakespan; 
	private int maxMakespanKey;
	private int minMakespanKey;
	
	public ArrayPermInt(int number,Problem p) {
		
		var = new PermInt[number];
		sortedByKeys=false;
		sortedByValues=false;
		maxMakespanKey=-1;
		minMakespanKey=-1;
		for(int i=0;i<number;++i)
			var[i]=new PermInt((int)p.getLowerLimit(i),(int)p.getUpperLimit(i),i );
	}

	public ArrayPermInt(ArrayPermInt v) throws JMException {
		var = new PermInt[v.var.length];
		sortedByKeys=v.sortedByKeys;
		sortedByValues=v.sortedByValues;
		maxMakespanKey=v.maxMakespanKey;
		minMakespanKey=v.minMakespanKey;
		
		for (int i = 0; i < var.length; ++i) {
			var[i] = new PermInt(v.var[i]);
		}
	}
		

	private void group(boolean values) {
		sort(var, values);
	}

	public void sort(PermInt[] var, boolean orderValues) {
		// check for empty or null array
		if (var == null || var.length == 0) {
			return;
		}
		try {
			quicksort(0, var.length - 1, orderValues);
		} catch (JMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void quicksort(int low, int high, boolean orderValues)
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
			while (checkTerm(i, orderValues) < pivot) {
				i++;
			}

			while (checkTerm(j, orderValues) > pivot) {
				j--;
			}

			if (i <= j) {
				exchange(i, j);
				i++;
				j--;

			}
		}
		// Recursion
		if (low < j)
			quicksort(low, j, orderValues);
		if (i < high)
			quicksort(i, high,  orderValues);
	}

	private double checkTerm(int index, boolean orderValues) {
		if (orderValues)
			return var[index].getValue();
		else
			return var[index].getIndex();

	}

	private void exchange(int i, int j) throws JMException {
		PermInt variables[] = (PermInt[]) var;
		double temp = variables[i].getValue();
		variables[i].setValue(variables[j].getValue());
		variables[j].setValue(temp);
		int swap = variables[i].getIndex();
		variables[i].setIndex(variables[j].getIndex());
		variables[j].setIndex(swap);
	}
	
	public PermInt logSearch(int index,boolean value){
		int i=0;
		int j=var.length-1;
		if (checkTerm(i, value)==index)return var[i];
		if (checkTerm(j, value)==index)return var[j];
		while(j-i>0){
			int pivot = (i+j)/2;
			double checked=checkTerm(pivot, value);
			if(checked==index)
			return var[pivot];
			else if(checked>index) j=pivot;
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
	
	public void sortByValues(){
		if(!sortedByValues)
		group(true);
	}
	
	public void sortByKeys(){
		if(!sortedByKeys)
		group(false);
	}
	
	public Integer getKey (int index){
		if(sortedByKeys){
			return var[index].getIndex();
		}else return null;
	}
	
	public Integer getValue (int index){
		if(sortedByKeys){
			return (int)var[index].getValue();
		}else return null;
	}
	
	public void setMinMakespan(int key){
		minMakespanKey=key;
	}
	
	
	public int getMinMakespan(){
		return minMakespanKey;
	}
	
	public void setFirstMinMakespan(int key){
		firstMinMakespan=key;
	}
	
	public int getFirstMinMakespan(){
		return firstMinMakespan;
	}
	
	public void setLastMinMakespan(int key){
		lastMinMakespan=key;
	}
	
	public int getLastMinMakespan(){
		return lastMinMakespan;
	}
	
	public void setFirstMaxMakespan(int key){
		firstMaxMakespan=key;
	}
	
	public int getFirstMaxMakespan(){
		return firstMaxMakespan;
	}
	
	public int getLastMaxMakespan(){
		return lastMaxMakespan;
	}
	
	public void setLastMaxMakespan(int key){
		lastMaxMakespan=key;
	}

	
	public void setMaxMakespan(int key){
		maxMakespanKey=key;
	}
	
	public int getMaxMakespan(){
		return maxMakespanKey;
	}
	
	public void print(){
		for(int i=0;i<var.length;i++){
			System.out.print(var[i].getIndex()+" ");
		}
		System.out.println();
		for(int i=0;i<var.length;i++){
			System.out.print((int)var[i].getValue()+" ");
		}
		System.out.println();
		System.out.println();
	}
}
