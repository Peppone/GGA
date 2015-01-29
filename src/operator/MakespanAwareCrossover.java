package operator;

import java.util.HashMap;

import jmetal.core.Operator;
import jmetal.core.Solution;
import jmetal.core.Variable;
import jmetal.util.Configuration;

public class MakespanAwareCrossover extends Operator {


	private static final long serialVersionUID = 1L;


	public MakespanAwareCrossover(HashMap<String, Object> parameters) {
		super(parameters);
	}
	
	public Solution[] doCrossover(Object ob){
		Solution[] sol = (Solution[])ob;
		if(sol.length<2){
			Configuration.logger_.severe("HalfBalancing.execute: operator " +
		              "needs two parents");
			return null;
		}
		Variable [] par1 = sol[0].getDecisionVariables();
		Variable [] par2 = sol[1].getDecisionVariables();
		Solution[] offspring=new Solution[2];
		
		
		return offspring;
		
	}
	
	
	public Object execute(Object ob){
		return doCrossover(ob);
	}

}
