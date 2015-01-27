package operator;

import java.util.HashMap;

import core.GGASolution;

import jmetal.core.Operator;
import jmetal.util.Configuration;
import jmetal.util.JMException;

public class TwoCutPointCrossover extends Operator{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TwoCutPointCrossover(HashMap<String, Object> parameters) {
		super(parameters);
		// TODO Auto-generated constructor stub
	}
	
	private Object doCrossover(Object object){
		GGASolution parents[]=(GGASolution[])object;
		if (parents.length < 2) {
		      Configuration.logger_.severe("SinglePointCrossover.execute: operator " +
		              "needs two parents");
		              
		}
		return null;
		      
	}

	@Override
	public Object execute(Object object) throws JMException {
		// TODO Auto-generated method stub
		return null;
	}

}
