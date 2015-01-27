package core;

import jmetal.core.Solution;
import jmetal.core.Variable;
import jmetal.encodings.variable.Int;
import jmetal.util.JMException;

abstract class GGASolution extends Solution {

	
	private int[] permutation;
	
	/**
	 * 
	 */
	public GGASolution(){
		super();
		int length=getDecisionVariables().length;
		permutation=new int[length];
		for(int i=0;i<length;++i){
			permutation[i]=i;
		}
	}
	private static final long serialVersionUID = 1L;
	
	public void group(){
		Variable[] var= (Int[])getDecisionVariables();
		sort(var);
		
	}

		  public void sort(Variable [] var) {
		    // check for empty or null array
		    if (var ==null || var.length==0){
		      return;
		    }
		    try {
				quicksort(0, var.length,var);
			} catch (JMException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  }

		  private void quicksort(int low, int high,Variable var[]) throws JMException {
		    int i = low, j = high;
		    // Get the pivot element from the middle of the list
		    double pivot = var[low + (high-low)/2].getValue();

		    // Divide into two lists
		    while (i <= j) {
		      while ((int)var[i].getValue() < pivot) {
		        i++;
		      }

		      while ((int)var[j].getValue() > pivot) {
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
		      quicksort(low, j,var);
		    if (i < high)
		      quicksort(i, high,var);
		  }

		  private void exchange(int i, int j, Variable var[]) throws JMException {
		    double temp = var[i].getValue();
		    var[i].setValue(var[j].getValue());
		    var[j].setValue(temp);
		    int swap = permutation[i];
		    permutation[i]=permutation[j];
		    permutation[j]=swap;   
		  }
		


	public void setVariable (int index, Object ob){
		
	}
	
	
	public void print (){
		for (int i=0;i<permutation.length;++i){
			System.out.print(permutation[i] +" ");
		}
		System.out.println();
		for (int i=0;i<permutation.length;++i){
			Variable var[]=getDecisionVariables();
			try {
				System.out.print(var[i].getValue() +" ");
			} catch (JMException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public Variable getPermutation(int index){
		return getDecisionVariables()[index];
	}
	public void setPermutation(int index, Variable v){
		getDecisionVariables()[index]=v;
		return;
	}

}
