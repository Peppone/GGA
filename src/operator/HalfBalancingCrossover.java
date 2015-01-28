package operator;

import java.util.HashMap;

import jmetal.core.Operator;
import jmetal.core.Problem;
import jmetal.core.Solution;
import jmetal.core.SolutionType;
import jmetal.util.Configuration;
import jmetal.util.JMException;
import core.GGASolution;
import encodings.variable.PermInt;

public class HalfBalancingCrossover extends Operator{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static SolutionType soltype;
	private static Problem problem;

	public HalfBalancingCrossover(HashMap<String, Object> parameters) {
		super(parameters);
		soltype=(SolutionType) parameters.get("solutionType");
		problem=(Problem)parameters.get("problem");
		//soltype=new PermSolutionType(null);
		// TODO Auto-generated constructor stub
	}
	
	private Object doCrossover(Object object) throws JMException, ClassNotFoundException{
		Solution parents[]=(Solution[])object;
		if (parents.length < 2) {
		      Configuration.logger_.severe("HalfBalancing.execute: operator " +
		              "needs two parents");
		      return null;
		}
		GGASolution first = new GGASolution((PermInt[])(parents[0].getDecisionVariables()));
		GGASolution second = new GGASolution((PermInt[])(parents[1].getDecisionVariables()));
		first.group(true);
		second.group(false);
		Solution[] offspring=new Solution[2];
		parents[0].setType(soltype);
		parents[1].setType(soltype);
	/*	offspring[0]=new Solution(parents[0]);
		offspring[1]=new Solution(parents[1]);
		offspring[0].setType(soltype);
		offspring[1].setType(soltype);
		*/
		offspring[0]=new Solution(problem);
		offspring[1]=new Solution(problem);
		PermInt[] var1 = first.getDecisionVariables();
		PermInt[] var2 = second.getDecisionVariables();
		for(int i=0;i<first.getDecisionVariables().length;++i){
				int index=var1[i].getIndex();
				Double value=second.logSearch(index);
				if (value==null){
				      Configuration.logger_.severe("HalfBalancing.execute: operator " +
				              "Internal Indexing Error");
				      return null;
				}
				int offIndex;
				if(i%2==0) offIndex=0;
				else offIndex=1;
				PermInt[] offsVar=(PermInt[]) (offspring[offIndex].getDecisionVariables());
				offsVar[i].setValue(value);
				offspring[offIndex].setDecisionVariables(offsVar);
			
		}
		GGASolution firstOff = new GGASolution((PermInt[])(offspring[0].getDecisionVariables()));
		GGASolution secondOff = new GGASolution((PermInt[])(offspring[1].getDecisionVariables()));
		firstOff.group(false);
		secondOff.group(false);
		return offspring;
		      
	}

	@Override
	public Object execute(Object object) throws JMException {
		try {
			return doCrossover(object);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

}
