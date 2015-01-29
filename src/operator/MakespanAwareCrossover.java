package operator;

import java.util.HashMap;

import jmetal.core.Operator;
import jmetal.core.Solution;
import jmetal.core.Variable;
import jmetal.util.Configuration;
import jmetal.util.JMException;
import encodings.variable.ArrayPermInt;
import encodings.variable.PermInt;

public class MakespanAwareCrossover extends Operator {


	private static final long serialVersionUID = 1L;


	public MakespanAwareCrossover(HashMap<String, Object> parameters) {
		super(parameters);
	}
	
	public Solution[] doCrossover(Object ob) throws JMException{
		Solution[] sol = (Solution[])ob;
		if(sol.length<2){
			Configuration.logger_.severe("HalfBalancing.execute: operator " +
		              "needs two parents");
			return null;
		}
		Variable [] par1 = sol[0].getDecisionVariables();
		Variable [] par2 = sol[1].getDecisionVariables();
		Solution[] offspring=new Solution[2];
		offspring[0]=new Solution(sol[0]);
		offspring[1]=new Solution(sol[1]);
		ArrayPermInt ap1= new ArrayPermInt((ArrayPermInt) par1[0]);
		ArrayPermInt ap2= new ArrayPermInt((ArrayPermInt) par2[0]);
		int p1M= ap1.getMaxMakespan();
		int p1m= ap1.getMinMakespan();
		int p2M= ap2.getMaxMakespan();
		int p2m= ap2.getMinMakespan();
		ap1.sortByValues();
		ap2.sortByKeys();
		ArrayPermInt op1= (ArrayPermInt)offspring[0].getDecisionVariables()[0];
		ArrayPermInt op2= (ArrayPermInt)offspring[1].getDecisionVariables()[0];
		PermInt [] o1v = (PermInt[]) op1.getDecisionVariables();
		for(int i=ap1.getFirstMaxMakespan();1<=ap1.getFirstMaxMakespan();++i){
			o1v[i].setValue(ap2.getValue(ap1.getKey(i)));
		}
		ap1.sortByKeys();
		ap2.sortByValues();
		PermInt [] o2v = (PermInt[]) op2.getDecisionVariables();
		for(int i=ap2.getFirstMaxMakespan();1<=ap2.getFirstMaxMakespan();++i){
			o2v[i].setValue(ap1.getValue(ap2.getKey(i)));
		}
		return offspring;
		
		}
	
	
	public Object execute(Object ob) throws JMException{
		return doCrossover(ob);
	}

}
