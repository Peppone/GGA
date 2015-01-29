package encodings.variable;

import jmetal.core.Variable;
import jmetal.encodings.variable.Int;
import jmetal.util.Configuration;
import jmetal.util.JMException;

public class PermInt extends Int{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	int index;
	
	public int getIndex(){
		return index;
	}
	
	public void setIndex(int ind){
		index=ind;
	}
	
	public PermInt (PermInt p) throws JMException{
		super(p);
		index=p.index;
		
	}
	
	public PermInt(Variable variable, int index) throws JMException{
		setLowerBound((int)variable.getLowerBound());
		setUpperBound((int)variable.getUpperBound());
		setValue((int)variable.getValue()); 
		this.index=index;
	}
	
	public PermInt (int lowerBound, int upperBound,int index){
		super(lowerBound,upperBound);
		this.index=index;
	}
	
	public Variable deepCopy(){
		try {
			return new Int(this);
		} catch (JMException e) {
			Configuration.logger_.severe("PermInt.deepCopy.execute: JMException");
			return null ;
		}
	}

}
