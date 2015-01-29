package encodings.solutionType;

import jmetal.core.Problem;
import jmetal.core.Variable;
import jmetal.encodings.solutionType.IntSolutionType;
import encodings.variable.PermInt;

public class PermIntSolutionType extends IntSolutionType{

	public PermIntSolutionType(Problem problem) {
		super(problem) ;
	} // Constructor

	/**
	 * Creates the variables of the solution
	 */

	
	public Variable[] createVariables() {
		Variable [] variables = new PermInt[problem_.getNumberOfVariables()];

		for (int var = 0; var < problem_.getNumberOfVariables(); var++)
			variables[var] = new PermInt((int)problem_.getLowerLimit(var),
					(int)problem_.getUpperLimit(var),var);    
		return variables ;
	} // createVariables

	
	public Variable[] copyVariables(Variable[] vars){
		Variable[] variables ;
	
		variables = new Variable[vars.length];

		for (int var = 0; var < vars.length; var++) {
			variables[var] = vars[var].deepCopy();
		} // for
		
		return variables ;
		
	}
			
			

}
