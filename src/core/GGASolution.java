package core;

import jmetal.core.Problem;
import jmetal.core.Solution;
import jmetal.core.Variable;
import jmetal.util.JMException;
import encodings.variable.PermInt;

public class GGASolution extends Solution {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4373015671428746086L;
	
	/**
	 * 
	 */

	public GGASolution(){
		super();

	}
	
	public GGASolution(int num){
		super(num);
	}
	
	public GGASolution(Problem p) throws ClassNotFoundException{
		super(p);
	}
	
	public GGASolution(PermInt[] var){
		super();
		setDecisionVariables(var);
	}
	
	public void group(boolean values){
		PermInt[] var= getDecisionVariables();
		sort(var,values);
		
	}

	
			public void sort(PermInt [] var, boolean orderValues) {
			    // check for empty or null array
		    if (var ==null || var.length==0){
		      return;
		    }
		    try {
				quicksort(0, var.length-1,var,orderValues);
			} catch (JMException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  }

		  private void quicksort(int low, int high,PermInt var[],boolean orderValues) throws JMException {
		    int i = low, j = high;
		    // Get the pivot element from the middle of the list
		    
		    double pivot;
		    if(orderValues)
		    	pivot= var[low + (high-low)/2].getValue();
		    else
		    	pivot=var[low+(high-low)/2].getIndex();

		    // Divide into two lists
		    while (i <= j) {
		      while (checkTerm(i,var,orderValues)< pivot) {
		        i++;
		      }

		      while (checkTerm(j,var,orderValues) > pivot) {
		        j--;
		      }

		      if (i <= j) {
		        exchange(i, j,var);
		        i++;
		        j--;
		      }
		    }
		    // Recursion
		    if (low < j)
		      quicksort(low, j,var,orderValues);
		    if (i < high)
		      quicksort(i, high,var, orderValues);
		  }
		  
		  private double checkTerm (int index, PermInt[] var, boolean orderValues){
			  if(orderValues)
			  return var[index].getValue();
			  else
				  return var[index].getIndex();
			  
		  }

		  private void exchange(int i, int j, Variable var[]) throws JMException {
			  PermInt variables [] = (PermInt [])var;
		    double temp = variables[i].getValue();
		    variables[i].setValue(variables[j].getValue());
		    variables[j].setValue(temp);
		    int swap = variables[i].getIndex();
		    variables[i].setIndex(variables[j].getIndex());
		    variables[j].setIndex(swap);   
		  }
		

	public void setDecisionVariable(PermInt [] var){
		super.setDecisionVariables(var);
	}
	
	public PermInt[] getDecisionVariables(){
		return (PermInt[])super.getDecisionVariables();
	}
	
	public void setVariable (int index, Object ob){
		
	}
	
	
	public void print (){
		PermInt var[]=(PermInt[])getDecisionVariables();
		for (int i=0;i<var.length;++i){
			System.out.print(var[i].getIndex() +" ");
		}
		System.out.println();
		for (int i=0;i<var.length;++i){
				System.out.print(var[i].getValue() +" ");
			
		}
		System.out.println();
	}
	
	public Variable getPermutation(int index){
		return getDecisionVariables()[index];
	}
	public void setPermutation(int index, PermInt v){
		getDecisionVariables()[index]=v;
		return;
	}

}
