package operator;

import java.util.HashMap;

import jmetal.core.Operator;
import jmetal.core.Solution;
import jmetal.core.Variable;
import jmetal.util.Configuration;
import jmetal.util.JMException;
import core.GGASolution;
import encodings.variable.PermInt;

public class HalfBalancingCrossover extends Operator{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HalfBalancingCrossover(HashMap<String, Object> parameters) {
		super(parameters);
		// TODO Auto-generated constructor stub
	}
	
	private Object doCrossover(Object object) throws JMException{
		Solution parents[]=(Solution[])object;
		if (parents.length < 2) {
		      Configuration.logger_.severe("HalfBalancing.execute: operator " +
		              "needs two parents");
		      return null;
		}
		GGASolution first = new GGASolution((PermInt[])parents[0].getDecisionVariables());
		GGASolution second = new GGASolution((PermInt[])parents[1].getDecisionVariables());
		first.group(true);
		second.group(false);
		Solution[] offspring=new Solution[2];
		offspring[0]=new Solution(parents[0]);
		offspring[1]=new Solution(parents[0]);
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
				Variable[] offsVar=offspring[offIndex].getDecisionVariables();
				offsVar[i].setValue(value);
				offspring[offIndex].setDecisionVariables(offsVar);
			
		}
		
		return offspring;
		      
	}

	@Override
	public Object execute(Object object) throws JMException {
		return doCrossover(object);
		
	}

}
